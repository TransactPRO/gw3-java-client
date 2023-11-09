package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.StatusResponse;
import com.github.transactpro.gateway.model.response.constants.CardFamily;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StatusTest {

    private Status operation;

    @BeforeEach
    void setUp() {
        operation = new Status();
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
    void parseStatusResponse() {
        String body = "{\"transactions\":[{\"gateway-transaction-id\":\"cd7b8bdf-3c78-4540-95d0-68018d2aba97\",\"status\":" +
                "[{\"card-mask\":\"534219*5267\",\"card-family\":\"MC\",\"gateway-transaction-id\":\"cd7b8bdf-3c78-4540-95d0-68018d2aba97\"," +
                "\"status-code\":7,\"status-code-general\":8,\"status-text\":\"SUCCESS\",\"status-text-general\":\"EXPIRED\"}]}," +
                "{\"gateway-transaction-id\":\"37908991-789b-4d79-8c6a-f90ba0ce12b6\",\"status\":[" +
                "{\"gateway-transaction-id\":\"37908991-789b-4d79-8c6a-f90ba0ce12b6\",\"status-code\":8,\"status-code-general\":7," +
                "\"status-text\":\"EXPIRED\",\"status-text-general\":\"SUCCESS\"}]}," +
                "{\"error\":{\"code\":400,\"message\":\"Failed to fetch data for transaction with gateway id: 99900000-789b-4d79-8c6a-f90ba0ce12b0\"}," +
                "\"gateway-transaction-id\":\"99900000-789b-4d79-8c6a-f90ba0ce12b0\"}]}";

        Response<StatusResponse> response = operation.createResponse(200, body, null);
        StatusResponse parsedResponse = response.parse();
        assertEquals(3, parsedResponse.getTransactions().size());

        StatusResponse.Element tr1 = parsedResponse.getTransactions().get(0);
        assertEquals("cd7b8bdf-3c78-4540-95d0-68018d2aba97", tr1.getGatewayTransactionId());
        assertEquals("cd7b8bdf-3c78-4540-95d0-68018d2aba97", tr1.getStatus().get(0).getGatewayTransactionId());
        assertEquals(com.github.transactpro.gateway.model.response.constants.Status.SUCCESS, tr1.getStatus().get(0).getStatusCode());
        assertEquals(com.github.transactpro.gateway.model.response.constants.Status.EXPIRED, tr1.getStatus().get(0).getStatusCodeGeneral());
        assertEquals("SUCCESS", tr1.getStatus().get(0).getStatusText());
        assertEquals("EXPIRED", tr1.getStatus().get(0).getStatusTextGeneral());
        assertEquals(CardFamily.MASTER_CARD, tr1.getStatus().get(0).getCardFamily());
        assertEquals("534219*5267", tr1.getStatus().get(0).getCardMask());

        StatusResponse.Element tr2 = parsedResponse.getTransactions().get(1);
        assertEquals("37908991-789b-4d79-8c6a-f90ba0ce12b6", tr2.getGatewayTransactionId());
        assertEquals("37908991-789b-4d79-8c6a-f90ba0ce12b6", tr2.getStatus().get(0).getGatewayTransactionId());
        assertEquals(com.github.transactpro.gateway.model.response.constants.Status.EXPIRED, tr2.getStatus().get(0).getStatusCode());
        assertEquals(com.github.transactpro.gateway.model.response.constants.Status.SUCCESS, tr2.getStatus().get(0).getStatusCodeGeneral());
        assertEquals("EXPIRED", tr2.getStatus().get(0).getStatusText());
        assertEquals("SUCCESS", tr2.getStatus().get(0).getStatusTextGeneral());

        StatusResponse.Element tr3 = parsedResponse.getTransactions().get(2);
        assertEquals("99900000-789b-4d79-8c6a-f90ba0ce12b0", tr3.getGatewayTransactionId());
        assertEquals(400, tr3.getError().getCode());
        assertEquals("Failed to fetch data for transaction with gateway id: 99900000-789b-4d79-8c6a-f90ba0ce12b0", tr3.getError().getMessage());
    }
}