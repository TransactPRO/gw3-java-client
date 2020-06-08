package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.RefundsResponse;
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

class RefundsTest {

    private Refunds operation;

    @BeforeEach
    void setUp() {
        operation = new Refunds();
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
    void parseRefundsResponse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expectedDateFinished1 = sdf.parse("2020-06-09 10:18:15");
        Date expectedDateFinished2 = sdf.parse("2020-06-09 10:18:22");

        String body = "{\"transactions\":[{\"gateway-transaction-id\":\"a2975c68-e235-40a4-87a9-987824c2090a\",\"refunds\":" +
                "[{\"account-guid\":\"bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b\",\"account-id\":108,\"acq-terminal-id\":\"5800978\"," +
                "\"acq-transaction-id\":\"1128894405863338\",\"amount\":10,\"approval-code\":\"1299034\",\"cardholder-name\":\"John Doe\"," +
                "\"currency\":\"EUR\",\"date-finished\":\"2020-06-09 10:18:15\",\"eci-sli\":\"960\",\"gateway-transaction-id\":" +
                "\"508fd8b9-3f78-486b-812b-2756f44e1bc6\",\"merchant-transaction-id\":\"aaa1\",\"status-code\":13,\"status-code-general\":11," +
                "\"status-text\":\"REFUND SUCCESS\",\"status-text-general\":\"REFUND FAILED\"},{\"account-guid\":" +
                "\"bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b\",\"account-id\":108,\"acq-terminal-id\":\"5800978\",\"acq-transaction-id\":" +
                "\"0508080614087693\",\"amount\":20,\"approval-code\":\"7117603\",\"cardholder-name\":\"John Doe\",\"currency\":\"EUR\"," +
                "\"date-finished\":\"2020-06-09 10:18:22\",\"eci-sli\":\"690\",\"gateway-transaction-id\":\"191228b8-fd2d-47c8-8ff7-d28ba799cdb4\"," +
                "\"merchant-transaction-id\":\"\",\"status-code\":13,\"status-code-general\":13,\"status-text\":\"REFUND SUCCESS\"," +
                "\"status-text-general\":\"REFUND SUCCESS\"}]},{\"error\":{\"code\":400,\"message\":" +
                "\"Failed to fetch data for transaction with gateway id: a2975c68-e235-40a4-87a9-987824c20900\"}," +
                "\"gateway-transaction-id\":\"a2975c68-e235-40a4-87a9-987824c20900\"}]}";

        Response<RefundsResponse> response = operation.createResponse(200, body, null);
        RefundsResponse parsedResponse = response.parse();
        assertEquals(2, parsedResponse.getTransactions().size());

        RefundsResponse.Element tr1 = parsedResponse.getTransactions().get(0);
        assertEquals("a2975c68-e235-40a4-87a9-987824c2090a", tr1.getGatewayTransactionId());
        assertEquals(2, tr1.getRefunds().size());

        TransactionInfo refund1 = tr1.getRefunds().get(0);
        assertEquals("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", refund1.getAccountGuid());
        assertEquals("5800978", refund1.getAcqTerminalId());
        assertEquals("1128894405863338", refund1.getAcqTransactionId());
        assertEquals(10, refund1.getAmount());
        assertEquals("1299034", refund1.getApprovalCode());
        assertEquals("John Doe", refund1.getCardholderName());
        assertEquals("EUR", refund1.getCurrency());
        assertEquals(expectedDateFinished1, refund1.getDateFinished());
        assertEquals("960", refund1.getEciSli());
        assertEquals("508fd8b9-3f78-486b-812b-2756f44e1bc6", refund1.getGatewayTransactionId());
        assertEquals("aaa1", refund1.getMerchantTransactionId());
        assertEquals(Status.REFUND_SUCCESS, refund1.getStatusCode());
        assertEquals(Status.REFUND_FAILED, refund1.getStatusCodeGeneral());
        assertEquals("REFUND SUCCESS", refund1.getStatusText());
        assertEquals("REFUND FAILED", refund1.getStatusTextGeneral());

        TransactionInfo refund2 = tr1.getRefunds().get(1);
        assertEquals("bc501eda-e2a1-4e63-9a1e-7a7f6ff4813b", refund2.getAccountGuid());
        assertEquals("5800978", refund2.getAcqTerminalId());
        assertEquals("0508080614087693", refund2.getAcqTransactionId());
        assertEquals(20, refund2.getAmount());
        assertEquals("7117603", refund2.getApprovalCode());
        assertEquals("John Doe", refund2.getCardholderName());
        assertEquals("EUR", refund2.getCurrency());
        assertEquals(expectedDateFinished2, refund2.getDateFinished());
        assertEquals("690", refund2.getEciSli());
        assertEquals("191228b8-fd2d-47c8-8ff7-d28ba799cdb4", refund2.getGatewayTransactionId());
        assertEquals("", refund2.getMerchantTransactionId());
        assertEquals(Status.REFUND_SUCCESS, refund2.getStatusCode());
        assertEquals(Status.REFUND_SUCCESS, refund2.getStatusCodeGeneral());
        assertEquals("REFUND SUCCESS", refund2.getStatusText());
        assertEquals("REFUND SUCCESS", refund2.getStatusTextGeneral());

        RefundsResponse.Element tr2 = parsedResponse.getTransactions().get(1);
        assertEquals("a2975c68-e235-40a4-87a9-987824c20900", tr2.getGatewayTransactionId());
        assertEquals(400, tr2.getError().getCode());
        assertEquals("Failed to fetch data for transaction with gateway id: a2975c68-e235-40a4-87a9-987824c20900", tr2.getError().getMessage());
    }
}