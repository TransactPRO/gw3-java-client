# Transact Pro Gateway v3 Java client library

This library provide ability to make requests to Transact Pro Gateway API v3.

### Installation

[com.github.transactpro:gateway](https://search.maven.org/artifact/com.github.transactpro/gateway/)

#### Maven

```xml
<dependency>
  <groupId>com.github.transactpro</groupId>
  <artifactId>gateway</artifactId>
  <version>1.3.5</version>
</dependency>
```

#### Gradle

```groovy
implementation 'com.github.transactpro:gateway:1.3.5'
```

## Documentation

This `README` provide introduction to the library usage.

### Supported operations

Available operations:
- Transactions
  - CANCEL
  - DMS CHARGE
  - DMS HOLD
  - MOTO DMS
  - MOTO SMS
  - INIT RECURRENT DMS
  - RECURRENT DMS
  - INIT RECURRENT SMS
  - RECURRENT SMS
  - REFUND
  - REVERSAL
  - SMS
  - Credit
  - P2P
  - B2P
- Information
  - HISTORY
  - RECURRENTS
  - REFUNDS
  - RESULT
  - STATUS
  - LIMITS
- Verification
  - 3-D Secure enrollment
  - Complete card verification
- Tokenization
  - Create payment data token
- Callback processing
  - verify callback data sign
- Reporting
  - Get transactions report in CSV format
  
### Basic usage
```java

import com.github.transactpro.gateway.Gateway;
import com.github.transactpro.gateway.model.request.data.PaymentMethod;
import com.github.transactpro.gateway.operation.transaction.Sms;
import com.github.transactpro.gateway.model.exception.ResponseException;
import com.github.transactpro.gateway.model.digest.exception.DigestMismatchException;
import com.github.transactpro.gateway.model.digest.exception.DigestMissingException;
import com.github.transactpro.gateway.model.request.data.Money;
import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.general.Customer;
import com.github.transactpro.gateway.model.request.data.general.Order;
import com.github.transactpro.gateway.model.request.data.general.customer.Address;
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.model.response.constants.Status;


public class Main {
    
    public static void main(String[] args) {
        
        Gateway gw = new Gateway(
         "xxxxxxxx-xxxxxxxx-xxxxxxxxxx",
         "8XY0ujBrVSkNpLe",
         "https://payment.gateway.com/v3.0"
        );
 
        Sms sms = new Sms();
     
        Address address = new Address()
            .setCity("Chalon-sur-Saône")
            .setCountry("FR")
            .setFlat("10")
            .setHouse("10")
            .setState("France")
            .setStreet("Rue Garibaldi")
            .setZip("71100");
        
        Money money = new Money()
                .setAmount(100)
                .setCurrency("EUR");
                
        Order order = new Order()
                .setDescription("Payment")
                .setId("Order ID")
                .setMerchantId("some-user-ID-in-eshop-system")
                .setMerchantReferringName("Test payment")
                .setMerchantTransactionId("33")
                .setMerchantUrl("https://domain.com/custom-url/")
                .setRecipientName("John Smith");
        
        Customer customer = new Customer()
                .setEmail("test@test.domain")
                .setBirthDate("01/00")
                .setPhone("123456789")
                .setBillingAddress(address)
                .setShippingAddress(address);        
        
        System system = new System()
                .setUserIp("127.0.0.1");
               
        PaymentMethod paymentMethod = new PaymentMethod()
                .setCardholderName("John Doe")
                .setPan("4111111111111111")
                .setCvv("123")
                .setExpMmYy("09/31");

        sms.setCustomer(customer)
                .setPayment(paymentMethod)
                .setMoney(money)
                .setOrder(order)
                .setSystem(system);   
        
        try {
            gw.process(sms);

            PaymentResponse parsedSmsResponse = sms.getResponse().parse();
            if (parsedSmsResponse.getError() != null && parsedSmsResponse.getError().getCode() != 0) {
                // Process Gateway error
            } else if (parsedSmsResponse.getGw().getRedirectUrl() != null) {
                // Redirect user to received URL
            }
        } catch (IOException e) {
            // Do something with IOException
        } catch (ValidationException e) {
            // Do something with ValidationException
        } catch (DigestMissingException | DigestMismatchException e) {
            // Do something with digest errors
        } catch (ResponseException e) {
            // Do something with unexpected response exception (like unexpected format)
        } catch (Exception e) {
            // Do something with unexpected error
        } finally {
            // Results
            java.lang.System.out.println(sms.getResponse().getBody());
            java.lang.System.out.println(sms.getResponse().getStatusCode());
            java.lang.System.out.println(sms.getResponse().getHeaders());
        }
    }
}
```

### Card verification
```java
import com.github.transactpro.gateway.model.request.data.command.CardVerificationMode;
import com.github.transactpro.gateway.model.request.data.Command;
import com.github.transactpro.gateway.operation.verify.VerifyCard;

// create a payment to init card verification process
Command command = new Command().setCardVerification(CardVerificationMode.INIT);
sms.setCustomer(customer).setCommand(command);

// complete card verification passing gateway transaction ID from initial payment
VerifyCard verification = new VerifyCard().setDataGatewayTransactionId(gatewayTransactionId);
gw.process(verification);
System.out.println(operation.getResponse().getStatusCode() == 200 ? "SUCCESS" : "FAILURE");

// send a payment with flag to accept only verified cards
Command command = new Command().setCardVerification(CardVerificationMode.VERIFY);
sms.setCustomer(customer).setCommand(command);
```

### Payment data tokenization
```java
import com.github.transactpro.gateway.model.request.data.command.PaymentMethodDataSource;
import com.github.transactpro.gateway.operation.token.CreateToken;

// option 1: create a payment with flag to save payment data
Command command = new Command().setPaymentMethodDataSource(PaymentMethodDataSource.DATA_SOURCE_SAVE_TO_GATEWAY);
sms.setCommand(command);

// option 2: send "create token" request with payment data
CreateToken operation = new CreateToken();

Money money = new Money().setCurrency("EUR");
Order order = new Order().setDescription("Payment");

PaymentMethod paymentMethod = new PaymentMethod()
        .setCardholderName("John Doe")
        .setPan("4111111111111111")
        .setExpMmYy("09/31");

operation.setPayment(paymentMethod).setMoney(money).setOrder(order);

// send a payment with flag to load payment data by token
Command command = new Command()
        .setPaymentMethodDataSource(PaymentMethodDataSource.DATA_SOURCE_USE_GATEWAY_SAVED_CARDHOLDER_INITIATED)
        .setPaymentMethodDataToken("<initial gateway-transaction-id>");
sms.setCommand(command);
```

### Callback validation

```java
import com.github.transactpro.gateway.model.digest.ResponseDigest;
import com.github.transactpro.gateway.model.exception.ResponseException;
import com.github.transactpro.gateway.model.response.CallbackResult;
import com.github.transactpro.gateway.model.response.GatewayResponse;


ResponseDigest responseDigest = new ResponseDigest(signFromPost);
responseDigest.setOriginalUri(paymentOperation.getResponse().getDigest().getUri());       // optional, set if available
responseDigest.setOriginalCnonce(paymentOperation.getResponse().getDigest().getCnonce()); // optional, set if available
responseDigest.setBody(jsonFromPost);
responseDigest.verify("xxxxxxxx-xxxxxxxx-xxxxxxxxxx", "8XY0ujBrVSkNpLe");

CallbackResult parsedResult = GatewayResponse.fromJson(jsonFromPost, CallbackResult.class);
System.out.println(parsedResult.getResultData().getGw().getGatewayTransactionId());
```

### Transactions report loading

```java
import com.github.transactpro.gateway.Gateway;
import com.github.transactpro.gateway.model.response.CsvResponse;
import com.github.transactpro.gateway.operation.reporting.Report;


// NB. Merchant GUID/secret must be used instead of account GUID/secret!
Gateway gw = new Gateway(
 "xxxxxxxx-xxxxxxxx-xxxxxxxxxx",
 "8XY0ujBrVSkNpLe",
 "https://payment.gateway.com/v3.0"
);

Report operation = new Report()
        .setCreatedFrom(java.lang.System.currentTimeMillis() / 1000L - 86400L)
        .setFinishedTo(java.lang.System.currentTimeMillis() / 1000L);

gw.process(operation);

Response<CsvResponse> responce = operation.getResponse();
CsvResponse parsedCsv = responce.parse();
for (CSVRecord record : parsedCsv) {
    System.out.println(record.toMap());
}
```

### Customization

If you need to load an HTML form from Gateway instead of cardholder browser redirect, a special operation type may be used:

```java
import com.github.transactpro.gateway.model.response.PaymentResponse;
import com.github.transactpro.gateway.operation.helpers.RetrieveForm;

PaymentResponse parsedSmsResponse = paymentOperation.getResponse().parse();
RetrieveForm retrieveFormOperation = new RetrieveForm(paymentResponse);
gw.process(retrieveFormOperation);
System.out.println(retrieveFormOperation.getResponse().getBody());
```

### Requirements

- This library works with Java 8 or above.

### Submit bugs and feature requests

Bugs and feature request are tracked on [GitHub](https://github.com/TransactPRO/gw3-java-client/issues)

### License

This library is licensed under the MIT License - see the `LICENSE` file for details.
