import com.gateway.Gateway;
import com.gateway.model.request.Authorization;
import com.gateway.model.request.Data;
import com.gateway.model.Request;
import com.gateway.operation.transaction.Sms;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import javax.validation.*;
import java.io.IOException;
import java.util.Set;
import java.util.UUID;


public class Main {

    public static String dataString = "{\n" +
            "        \"command-data\": {\n" +
            "            \"payment-method-type\": \"cc\",\n" +
            "            \"payment-method-data-source\": \"inside\"\n" +
            "        },\n" +
            "        \"general-data\": {\n" +
            "            \"customer-data\": {\n" +
            "                \"email\": \"client@example.com\",\n" +
            "                \"phone\": \"37129968364\",\n" +
            "                \"billing-address\": {\n" +
            "                    \"country\": \"LV\",\n" +
            "                    \"state\": \"Latvia\",\n" +
            "                    \"city\": \"Riga\",\n" +
            "                    \"street\": \"Ropazu 10\",\n" +
            "                    \"house\": \"10\",\n" +
            "                    \"flat\": \"204\",\n" +
            "                    \"zip\": \"LV-1003\"\n" +
            "                },\n" +
            "                \"shipping-address\": {\n" +
            "                    \"country\": \"LV\",\n" +
            "                    \"state\": \"Latvia\",\n" +
            "                    \"city\": \"Riga\",\n" +
            "                    \"street\": \"Ropazu 10\",\n" +
            "                    \"house\": \"10\",\n" +
            "                    \"flat\": \"204\",\n" +
            "                    \"zip\": \"LV-1003\"\n" +
            "                }\n" +
            "            },\n" +
            "            \"order-data\": {\n" +
            "                \"merchant-transaction-id\": null,\n" +
            "                \"merchant-side-url\": \"http://www.transactpro.lv\",\n" +
            "                \"order-id\": \"1235456789\",\n" +
            "                \"order-description\": \"Services subscription.\"\n" +
            "            }\n" +
            "        },\n" +
            "        \"payment-method-data\": {\n" +
            "            \"pan\": \"5583420021372457\",\n" +
            "            \"exp-mm-yy\": \"06/20\",\n" +
            "            \"cardholder-name\": \"John Doe\"\n" +
            "        },\n" +
            "        \"money-data\": {\n" +
            "            \"amount\": 100,\n" +
            "            \"currency\": \"USD\"\n" +
            "        },\n" +
            "        \"system\": {\n" +
            "            \"user-ip\": null,\n" +
            "            \"x-forwarded-for\": null\n" +
            "        }\n" +
            "    }";

    public static void main(String[] args) {
        System.out.println("================== START ==================");
        Gateway gw = new Gateway("http://uriel.vg.fpngw3.env/v3.0");
        gw.getAuthorization()
                .setAccountGuid("f44e7b6e-4bf1-419c-bb32-689a36a51e51")
                .setSecretKey("3b13d5a0cc1f46a88230d638e1069e6e");

        Sms sms = new Sms();

        try {
            gw.process(sms);
        } catch (IOException e) {
            e.printStackTrace();
        }

/*        Request request = new Request();
        request.setAuthorization(new Authorization()
                .setAccountGuid("f44e7b6e-4bf1-419c-bb32-689a36a51e51")
                .setSecretKey("3b13d5a0cc1f46a88230d638e1069e6e"))
        .setData(new Data().setCommand(new Command()));
                System.out.println(gson.toJson(request));*/
//        Gson gson = new GsonBuilder()
//                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
//                .create();
//
//
//        HttpClient httpClient = HttpClientBuilder.create().build();
//
//        Authorization auth = new Authorization()
//                .setAccountGuid("c5a39508-e00d-42ae-a961-756805ec9e46")
//                .setSecretKey("8XY0ujBrVSkNpLeZ3GKAJPRniOxFza9D");
//        Gateway gw = new Gateway("http://uriel.vg.fpngw3.env/v3.0", auth, httpClient);
//
//        Data data = gson.fromJson(dataString, Data.class);
//
//        data.getSystem().setUserIp("8.8.8.8");
//        data.getGeneral().getOrder().setMerchantTransactionId(UUID.randomUUID().toString());
////        data.getGeneral().getOrder().setMerchantUrl("tran");
//
//        Request request = new Request()
//                .setData(data);
//
//        request.getData().getMoney().setAmount(null);
//
//        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
//  //      Configuration<?> config = Validation.byDefaultProvider().configure();
//
////        ValidatorFactory validatorFactory = config.buildValidatorFactory();
//
//        Validator validator = validatorFactory.getValidator();
////        validatorFactory.close();
//        Set<ConstraintViolation<Request>> violations = validator.validate(request);
//
//        if (violations.size() == 0) {
//            System.out.println("No violations.");
//        } else {
//            System.out.printf("%s violations:%n", violations.size());
////            violations.stream()
////                    .forEach(ValidAnnotationExample::printError);
//        }
//        validator.forExecutables()
//        SmsValidation sms = new SmsValidation();
//        sms.setRequest(request);
//        sms.isValid(validator);
//        try {
//            gw.request(request);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }
}
