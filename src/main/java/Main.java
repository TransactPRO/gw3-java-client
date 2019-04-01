import com.gateway.Gateway;
import com.gateway.model.request.Authorization;
import com.gateway.model.request.Data;
import com.gateway.model.Request;
import com.gateway.model.request.data.general.customer.Address;
import com.gateway.operation.transaction.DmsHold;
import com.gateway.operation.transaction.Refund;
import com.gateway.operation.transaction.Sms;

import javax.validation.*;
import java.io.IOException;
import java.util.Currency;
import java.util.Set;
import java.util.UUID;


public class Main {


    public static void main(String[] args) {
        System.out.println("================== START ==================");

        Set<Currency> currencies = Currency.getAvailableCurrencies();
        //        String[] cc = Locale.getISOCountries();
        //        System.out.println(cc.length);
        //        System.out.println(currencies);

        Gateway gw = new Gateway("http://uriel.vg.fpngw3.env/v3.0");
        gw.getAuthorization().setAccountGuid("c5a39508-e00d-42ae-a961-756805ec9e46")
                .setSecretKey("8XY0ujBrVSkNpLeZ3GKAJPRniOxFza9D");

        Sms sms = Main.createSmsTransaction();

        try {
            gw.process(sms);
            if (!sms.isSuccessful()) {
                System.out.println("================== RESULT ==================");
                System.out.println(sms.getError().getMessage());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        DmsHold dmsHold = new DmsHold();
        try {
            gw.process(dmsHold);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Refund refund = new Refund();
        try {
            gw.process(refund);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static Sms createSmsTransaction() {
        Sms sms = new Sms();
        Address address = new Address()
                .setCity("Riga")
                .setCountry("LV")
                .setFlat("33")
                .setHouse("333")
                .setState("Yes")
                .setStreet("Lenina")
                .setZip("LV 1010");
//        sms.setMoneyCurrency()
//        sms.setMoneyAmount(100).setMoneyAmount();
//        sms.setMoneyAmount(100).setMoneyCurrency("EUR");

//        sms.getRequest().getData().getMoney().setAmount(1000).setCurrency("EUR");

//        sms.setMoneyAmount(1000);


//        sms.getRequest().getData().getSystem().setUserIp("10.0.2.2");

//        Address address = new Address()
//                .setCity("Riga")
//                .setCountry("LV")
//                .setFlat("33")
//                .setHouse("333")
//                .setState("Yes")
//                .setStreet("Lenina")
//                .setZip("LV 1010");

        sms.setOrderMerchantUrl("http://pipec.com")
                .setOrderDescription("Money for Trump")
                .setOrderMerchantTransactionId(UUID.randomUUID().toString())
                .setMoneyAmount(1000)
                .setMoneyCurrency("EUR")
                .setCustomerBillingAddress(address)
                .setCustomerShippingAddress(address);

//        sms.getRequest().getData().getGeneral().getCustomer()
//                .setEmail("vitalik@test.io")
//                .setPhone("26171717")
//                .setBirthDate("Yes")
//                .setBillingAddress(address)
//                .setShippingAddress(address);

        return sms;
    }
}
