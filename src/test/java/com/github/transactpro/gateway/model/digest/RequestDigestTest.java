package com.github.transactpro.gateway.model.digest;

import org.apache.hc.client5.http.utils.Base64;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RequestDigestTest {
    @Test
    protected void createHeader() throws IOException, NoSuchAlgorithmException, InvalidKeyException {
        String expected = "Digest username=bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b, uri=\"/v3.0/sms\", algorithm=SHA-256, " +
                "cnonce=\"MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg==\", qop=auth-int, " +
                "response=\"a21df219fd9bb2efb71554eb9ebb47f6a7a61769a289f9ab4fcbe41d7544e28d\"";

        RequestDigest instance = new RequestDigest();
        instance.init();
        assertEquals(Digest.Algorithm.SHA256, instance.getAlgorithm());
        assertEquals(Digest.QOP.AUTH_INT, instance.getQop());
        assertEquals(43, instance.getCnonce().length);

        instance.setUsername("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b");
        instance.setSecret("agHJSthpTPfKEORLDynBuIl07i4sYVmw");
        instance.setUri("https://some.url/v3.0/sms");
        instance.setBody(
                "{\"auth-data\":{},\"data\":{\"command-data\":{},\"general-data\":{\"customer-data\":{\"email\":\"test@test.domain\",\"birth-date\":\"01/00\","+
                        "\"phone\":\"123456789\",\"billing-address\":{\"country\":\"FR\",\"state\":\"FR\",\"city\":\"Chalon-sur-Saône\",\"street\":\"Rue Garibaldi\","+
                        "\"house\":\"10\",\"flat\":\"10\",\"zip\":\"71100\"},\"shipping-address\":{\"country\":\"FR\",\"state\":\"FR\",\"city\":\"Chalon-sur-Saône\","+
                        "\"street\":\"Rue Garibaldi\",\"house\":\"10\",\"flat\":\"10\",\"zip\":\"71100\"}},\"order-data\":{\"merchant-transaction-id\":\"\","+
                        "\"order-id\":\"Order ID\",\"order-description\":\"Payment\",\"merchant-side-url\":\"https://domain.com/custom-url/\","+
                        "\"merchant-referring-name\":\"Test payment\",\"custom3d-return-url\":\"https://domain.com\"}},\"payment-method-data\":"+
                        "{\"pan\":\"4111111111111111\",\"exp-mm-yy\":\"09/31\",\"cvv\":\"123\",\"cardholder-name\":\"John Doe\"},\"money-data\":"+
                        "{\"amount\":100,\"currency\":\"EUR\"},\"system\":{\"user-ip\":\"127.0.0.1\"}}}"
        );
        instance.cnonce = Base64.decodeBase64("MTU5MTYyNTA2MzqydV+lpoF4ZtfSAifxoUretZdAzGaZa97iRogrQ8K/yg==");
        String actual = instance.createHeader();

        assertEquals(expected, actual);
    }
}