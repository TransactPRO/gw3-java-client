package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.ResultResponse;
import com.github.transactpro.gateway.model.response.constants.Status;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

import static org.junit.jupiter.api.Assertions.*;

class ResultTest {

    private Result operation;

    @BeforeEach
    void setUp() {
        operation = new Result();
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
    void parseResultResponse() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        Date expectedDateCreated = sdf.parse("2020-06-10 08:37:22");
        Date expectedDateFinished = sdf.parse("2020-06-10 08:37:23");

        String body = "{\"transactions\":[{\"date-created\":\"2020-06-10 08:37:22\",\"date-finished\":\"2020-06-10 08:37:23\"," +
                "\"gateway-transaction-id\":\"b552fe8c-0fe3-4982-b2d6-9c37fa96dc58\",\"result-data\":{\"acquirer-details\":" +
                "{\"eci-sli\":\"736\",\"result-code\":\"000\",\"status-description\":\"Approved\",\"status-text\":\"Approved\"," +
                "\"terminal-mid\":\"5800978\",\"transaction-id\":\"8225174463086463\"},\"error\":{},\"gw\":" +
                "{\"gateway-transaction-id\":\"b552fe8c-0fe3-4982-b2d6-9c37fa96dc58\",\"original-gateway-transaction-id\":" +
                "\"096a93f4-c4d9-4b46-bbe9-22e30031f2d2\",\"parent-gateway-transaction-id\":\"096a93f4-c4d9-4b46-bbe9-22e30031f2d2\"," +
                "\"status-code\":15,\"status-text\":\"CANCELLED\"}}},{\"error\":{\"code\":400,\"message\":" +
                "\"Failed to get transaction result for transaction with gateway id: 965ffd17-1874-48d0-89f3-f2c2f06bf749\"}," +
                "\"gateway-transaction-id\":\"965ffd17-1874-48d0-89f3-f2c2f06bf749\"}]}";

        Response<ResultResponse> response = operation.createResponse(200, body, null);
        ResultResponse parsedResponse = response.parse();
        assertEquals(2, parsedResponse.getTransactions().size());

        ResultResponse.Element tr1 = parsedResponse.getTransactions().get(0);
        assertEquals("b552fe8c-0fe3-4982-b2d6-9c37fa96dc58", tr1.getGatewayTransactionId());
        assertEquals(expectedDateCreated, tr1.getDateCreated());
        assertEquals(expectedDateFinished, tr1.getDateFinished());

        assertNotNull(tr1.getResultData().getAcquirerDetails());
        assertNull(tr1.getResultData().getAcquirerDetails().getDynamicDescriptor());
        assertEquals("736", tr1.getResultData().getAcquirerDetails().getEciSli());
        assertEquals("000", tr1.getResultData().getAcquirerDetails().getResultCode());
        assertEquals("Approved", tr1.getResultData().getAcquirerDetails().getStatusDescription());
        assertEquals("Approved", tr1.getResultData().getAcquirerDetails().getStatusText());
        assertEquals("5800978", tr1.getResultData().getAcquirerDetails().getTerminalMid());
        assertEquals("8225174463086463", tr1.getResultData().getAcquirerDetails().getTransactionId());

        assertNotNull(tr1.getResultData().getGw());
        assertEquals("b552fe8c-0fe3-4982-b2d6-9c37fa96dc58", tr1.getResultData().getGw().getGatewayTransactionId());
        assertEquals("096a93f4-c4d9-4b46-bbe9-22e30031f2d2", tr1.getResultData().getGw().getOriginalGatewayTransactionId());
        assertEquals("096a93f4-c4d9-4b46-bbe9-22e30031f2d2", tr1.getResultData().getGw().getParentGatewayTransactionId());
        assertEquals(Status.DMS_CANCELED_OK, tr1.getResultData().getGw().getStatusCode());
        assertEquals("CANCELLED", tr1.getResultData().getGw().getStatusText());

        ResultResponse.Element tr2 = parsedResponse.getTransactions().get(1);
        assertEquals("965ffd17-1874-48d0-89f3-f2c2f06bf749", tr2.getGatewayTransactionId());
        assertEquals(400, tr2.getError().getCode());
        assertEquals("Failed to get transaction result for transaction with gateway id: 965ffd17-1874-48d0-89f3-f2c2f06bf749", tr2.getError().getMessage());
    }
}