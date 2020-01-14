# Transact Pro Gateway v3 Java client library

This library provide ability to make requests to Transact Pro Gateway API v3.

### Installation

[com.github.transactpro:gateway](https://search.maven.org/artifact/com.github.transactpro/gateway/)

#### Maven

```xml
<dependency>
  <groupId>com.github.transactpro</groupId>
  <artifactId>gateway</artifactId>
  <version>1.1.1</version>
</dependency>
```

#### Gradle

```groovy
implementation 'com.github.transactpro:gateway:1.1.1'
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
- Verification
  - 3-D Secure enrollment
  - Complete card verification
- Tokenization
  - Create payment data token
  
### Basic usage
```java

import com.github.transactpro.gateway.Gateway;
import com.github.transactpro.gateway.operation.transaction.Sms;
import com.github.transactpro.gateway.model.request.data.Money;
import com.github.transactpro.gateway.model.request.data.System;
import com.github.transactpro.gateway.model.request.data.general.Customer;
import com.github.transactpro.gateway.model.request.data.general.Order;
import com.github.transactpro.gateway.model.request.data.general.customer.Address;


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
        } catch (IOException e) {
            // Do something with IOException
        } catch (ValidationException e) {
            // Do something with ValidationException
        }
        
        // Results
        sms.getResponse().getBody();
        sms.getResponse().getStatusCode();
        sms.getResponse().getHeaders();
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

### Requirements

- This library works with Java 8 or above.

### Submit bugs and feature requests

Bugs and feature request are tracked on [GitHub](https://github.com/TransactPRO/gw3-java-client/issues)

### License

This library is licensed under the MIT License - see the `LICENSE` file for details.
