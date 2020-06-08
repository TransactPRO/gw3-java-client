package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.HistoryResponse;
import com.github.transactpro.gateway.model.response.constants.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.validation.groups.Default;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HistoryTest {

    private History operation;

    @BeforeEach
    void setUp() {
        operation = new History();
    }

    @AfterEach
    void tearDown() {
        operation = null;
    }

    @Test
    void getRequestUri() {
        assertTrue(operation.getRequestUri().startsWith("/"));
    }

    @Test
    void getValidationGroups() {
        assertEquals(Default.class, operation.getValidationGroups());
    }

    @Test
    void validOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        String[] transactionIds = {"123", "321"};
        operation.setCommandGatewayTransactionIds(transactionIds)
                .setCommandMerchantTransactionIds(transactionIds);

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void parseHistoryResponse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expectedDate1 = sdf.parse("2020-06-09 09:56:53");
        Date expectedDate2 = sdf.parse("2020-06-09 09:57:53");

        String body = "{\"transactions\":[{\"error\":{\"code\":400,\"message\":\"Failed to fetch data for transaction with gateway id: " +
                "a2975c68-e235-40a4-87a9-987824c20000\"},\"gateway-transaction-id\":\"a2975c68-e235-40a4-87a9-987824c20000\"}," +
                "{\"gateway-transaction-id\":\"a2975c68-e235-40a4-87a9-987824c2090a\",\"history\":[{\"date-updated\":\"2020-06-09 09:56:53\"," +
                "\"status-code-new\":2,\"status-code-old\":1,\"status-text-new\":\"SENT TO BANK\",\"status-text-old\":\"INIT\"}," +
                "{\"date-updated\":\"2020-06-09 09:57:53\",\"status-code-new\":7,\"status-code-old\":2,\"status-text-new\":\"SUCCESS\"," +
                "\"status-text-old\":\"SENT TO BANK\"}]}]}";

        Response<HistoryResponse> response = operation.createResponse(200, body, null);
        HistoryResponse parsedResponse = response.parse();
        assertEquals(2, parsedResponse.getTransactions().size());

        HistoryResponse.Element tr1 = parsedResponse.getTransactions().get(0);
        assertEquals("a2975c68-e235-40a4-87a9-987824c20000", tr1.getGatewayTransactionId());
        assertEquals(400, tr1.getError().getCode());
        assertEquals("Failed to fetch data for transaction with gateway id: a2975c68-e235-40a4-87a9-987824c20000", tr1.getError().getMessage());

        HistoryResponse.Element tr2 = parsedResponse.getTransactions().get(1);
        assertEquals("a2975c68-e235-40a4-87a9-987824c2090a", tr2.getGatewayTransactionId());
        assertEquals(2, tr2.getHistory().size());

        HistoryResponse.HistoryEvent event1 = tr2.getHistory().get(0);
        assertEquals(expectedDate1, event1.getDateUpdated());
        assertEquals(Status.INIT, event1.getStatusCodeOld());
        assertEquals(Status.SENT2BANK, event1.getStatusCodeNew());
        assertEquals("INIT", event1.getStatusTextOld());
        assertEquals("SENT TO BANK", event1.getStatusTextNew());

        HistoryResponse.HistoryEvent event2 = tr2.getHistory().get(1);
        assertEquals(expectedDate2, event2.getDateUpdated());
        assertEquals(Status.SENT2BANK, event2.getStatusCodeOld());
        assertEquals(Status.SUCCESS, event2.getStatusCodeNew());
        assertEquals("SENT TO BANK", event2.getStatusTextOld());
        assertEquals("SUCCESS", event2.getStatusTextNew());
    }
}