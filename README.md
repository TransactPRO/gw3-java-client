# Transact Pro Gateway v3 Java client library

This library provide ability to make requests to Transact Pro Gateway API v3.

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
import Address;
import Sms;

public class Main {
    
    public static void main(String[] args) {
        
        Gateway gw = new Gateway(
         "xxxxxxxx-xxxxxxxx-xxxxxxxxxx",
         "8XY0ujBrVSkNpLe",
         "https://payment.gateway.com/v3.0"
        );
 
        Sms sms = new Sms();
     
        Address address = new Address()
         .setCity("Paris")
         .setCountry("FR")
         .setFlat("33")
         .setHouse("333")
         .setState("Paris")
         .setStreet("Test street")
         .setZip("781010");
 
        sms.setOrderMerchantUrl("https://my-domain.com")
         .setOrderDescription("Online shop order #33")
         .setOrderMerchantTransactionId("33")
         .setMoneyAmount(1000)
         .setMoneyCurrency("EUR")
         .setCustomerBillingAddress(address)
         .setCustomerShippingAddress(address)
         .setCustomerPhone("81111111111")
         .setCustomerEmail("test@test.io")
         .setCustomerBirthDate("1999/01/25")
         .setSystemUserIp("127.0.0.1");
 
        try {
         gw.process(sms);
        } catch (IOException e) {
         // Do something with IOException
        } catch (ValidationException e) {
         // Do something with ValidationException
        }
    }
}
```

### Requirements

- This library works with Java 11 or above.

### Submit bugs and feature requests

Bugs and feature request are tracked on [GitHub](https://github.com/TransactPRO/gw3-java-client/issues)

### License

This library is licensed under the MIT License - see the `LICENSE` file for details.
