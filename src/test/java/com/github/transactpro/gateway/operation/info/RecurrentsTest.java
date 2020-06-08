package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.RecurringTransactionsResponse;
import com.github.transactpro.gateway.model.response.constants.Status;
import com.github.transactpro.gateway.model.response.parts.TransactionInfo;
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

class RecurrentsTest {

    private Recurrents operation;

    @BeforeEach
    void setUp() {
        operation = new Recurrents();
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
    void parseRecurringTransactionsResponse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expectedDateFinished = sdf.parse("2020-06-09 09:56:53");

        String body = "{\"transactions\":[{\"error\":{\"code\":400,\"message\":\"Failed to fetch data for transaction with gateway id: " +
                "9e09bad0-5704-4b78-bf6a-c612f0101900\"},\"gateway-transaction-id\":\"9e09bad0-5704-4b78-bf6a-c612f0101900\"}," +
                "{\"gateway-transaction-id\":\"9e09bad0-5704-4b78-bf6a-c612f010192a\",\"recurrents\":[{\"account-guid\":" +
                "\"bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b\",\"account-id\":108,\"acq-terminal-id\":\"5800978\",\"acq-transaction-id\":" +
                "\"7435540948424227\",\"amount\":100,\"approval-code\":\"4773442\",\"cardholder-name\":\"John Doe\",\"currency\":\"EUR\"," +
                "\"date-finished\":\"2020-06-09 09:56:53\",\"eci-sli\":\"464\",\"gateway-transaction-id\":\"a2975c68-e235-40a4-87a9-987824c2090a\"," +
                "\"merchant-transaction-id\":\"52a9990bad03e15417c70ef11a8103e1\",\"status-code\":7,\"status-code-general\":13," +
                "\"status-text\":\"SUCCESS\",\"status-text-general\":\"REFUND SUCCESS\"}]}]}";

        Response<RecurringTransactionsResponse> response = operation.createResponse(200, body, null);
        RecurringTransactionsResponse parsedResponse = response.parse();
        assertEquals(2, parsedResponse.getTransactions().size());

        RecurringTransactionsResponse.Element tr1 = parsedResponse.getTransactions().get(0);
        assertEquals("9e09bad0-5704-4b78-bf6a-c612f0101900", tr1.getGatewayTransactionId());
        assertEquals(400, tr1.getError().getCode());
        assertEquals("Failed to fetch data for transaction with gateway id: 9e09bad0-5704-4b78-bf6a-c612f0101900", tr1.getError().getMessage());

        RecurringTransactionsResponse.Element tr2 = parsedResponse.getTransactions().get(1);
        assertEquals("9e09bad0-5704-4b78-bf6a-c612f010192a", tr2.getGatewayTransactionId());
        assertEquals(1, tr2.getSubsequent().size());

        TransactionInfo info1 = tr2.getSubsequent().get(0);
        assertEquals("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", info1.getAccountGuid());
        assertEquals("5800978", info1.getAcqTerminalId());
        assertEquals("7435540948424227", info1.getAcqTransactionId());
        assertEquals(100, info1.getAmount());
        assertEquals("4773442", info1.getApprovalCode());
        assertEquals("John Doe", info1.getCardholderName());
        assertEquals("EUR", info1.getCurrency());
        assertEquals(expectedDateFinished, info1.getDateFinished());
        assertEquals("464", info1.getEciSli());
        assertEquals("a2975c68-e235-40a4-87a9-987824c2090a", info1.getGatewayTransactionId());
        assertEquals("52a9990bad03e15417c70ef11a8103e1", info1.getMerchantTransactionId());
        assertEquals(Status.SUCCESS, info1.getStatusCode());
        assertEquals(Status.REFUND_SUCCESS, info1.getStatusCodeGeneral());
        assertEquals("SUCCESS", info1.getStatusText());
        assertEquals("REFUND SUCCESS", info1.getStatusTextGeneral());
    }
}