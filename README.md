# Transact Pro Gateway v3 Java client library

This library provide ability to make requests to Transact Pro Gateway API v3.

### Installation

[com.github.transactpro:gateway](https://search.maven.org/artifact/com.github.transactpro/gateway/)

#### Maven

```xml
<dependency>
  <groupId>com.github.transactpro</groupId>
  <artifactId>gateway</artifactId>
  <version>1.0.1</version>
</dependency>
```

#### Gradle

```groovy
implementation 'com.github.transactpro:gateway:1.0.1'
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
  
### Basic usage
```java

import Gateway;
import Sms;
import Money;
import System;
import Customer;
import Order;
import Address;


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
                .setCustom3dReturnUrl("https://domain.com")
                .setDescription("Payment")
                .setId("Order ID")
                .setMerchantId("FGSSW2aas")
                .setMerchantReferringName("Test payment")
                .setMerchantTransactionId("33")
                .setMerchantUrl("https://domain.com/custom-url/")
                .setRecipientName("John Smith");
        
        Customer customer = new Customer()
                .setEmail("test@test.domain")
                .setBirthDate("01/00");        
        
        System system = new System()
                .setUserIp("127.0.0.1");
               
        sms.setCustomer(customer)
                .setCustomerBillingAddress(address)
                .setCustomerShippingAddress(address)
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

### Requirements

- This library works with Java 11 or above.

### Submit bugs and feature requests

Bugs and feature request are tracked on [GitHub](https://github.com/TransactPRO/gw3-java-client/issues)

### License

This library is licensed under the MIT License - see the `LICENSE` file for details.
