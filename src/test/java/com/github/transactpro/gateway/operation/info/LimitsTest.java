package com.github.transactpro.gateway.operation.info;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.LimitsResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.groups.Default;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class LimitsTest {

    private Limits operation;

    @BeforeEach
    void setUp() {
        operation = new Limits();
    }

    @AfterEach
    void tearDown() {
        operation = null;
    }

    @Test
    void getRequestUri() {
        assertEquals("/limits", operation.getRequestUri());
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

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void parseLimitsResponse() {
        String body = "{\"childs\":[{\"childs\":[{\"childs\":[{\"counters\":[{\"counter-type\":\"TR_SUCCESS_AMOUNT\",\"currency\":\"EUR\"," +
                "\"limit\":5000000,\"payment-method-subtype\":\"all\",\"payment-method-type\":\"all\",\"value\":28410}," +
                "{\"counter-type\":\"TR_SUCCESS_COUNT\",\"currency\":\"EUR\",\"limit\":20000,\"payment-method-subtype\":\"all\"," +
                "\"payment-method-type\":\"all\",\"value\":992}],\"acq-terminal-id\":\"5800978\",\"title\":\"Test T1\",\"type\":\"terminal\"}]," +
                "\"counters\":[{\"counter-type\":\"TR_SUCCESS_AMOUNT\",\"currency\":\"EUR\",\"limit\":5000000,\"payment-method-subtype\":\"all\"," +
                "\"payment-method-type\":\"all\",\"value\":2400}],\"title\":\"Test TG\",\"type\":\"terminal-group\"}],\"counters\":" +
                "[{\"counter-type\":\"TR_SUCCESS_AMOUNT\",\"currency\":\"EUR\",\"limit\":5000000,\"payment-method-subtype\":\"all\"," +
                "\"payment-method-type\":\"all\",\"value\":2400}],\"title\":\"Test ACC\",\"type\":\"account\"}],\"counters\":" +
                "[{\"counter-type\":\"TR_SUCCESS_AMOUNT\",\"currency\":\"EUR\",\"limit\":5000000,\"payment-method-subtype\":\"all\"," +
                "\"payment-method-type\":\"all\",\"value\":2400}],\"title\":\"Test M\",\"type\":\"merchant\"}";

        Response<LimitsResponse> response = operation.createResponse(200, body, null);
        LimitsResponse merchant = response.parse();

        assertEquals("merchant", merchant.getType());
        assertEquals("Test M", merchant.getTitle());
        assertNull(merchant.getAcqTerminalId());
        assertEquals(1, merchant.getChildren().size());
        assertEquals(1, merchant.getLimits().size());
        assertEquals("TR_SUCCESS_AMOUNT", merchant.getLimits().get(0).getCounterType());
        assertEquals("EUR", merchant.getLimits().get(0).getCurrency());
        assertEquals(5000000, merchant.getLimits().get(0).getLimit());
        assertEquals(2400, merchant.getLimits().get(0).getValue());
        assertEquals("all", merchant.getLimits().get(0).getPaymentMethodType());
        assertEquals("all", merchant.getLimits().get(0).getPaymentMethodSubtype());

        LimitsResponse account = merchant.getChildren().get(0);
        assertEquals("account", account.getType());
        assertEquals("Test ACC", account.getTitle());
        assertNull(account.getAcqTerminalId());
        assertEquals(1, account.getChildren().size());
        assertEquals(1, account.getLimits().size());
        assertEquals("TR_SUCCESS_AMOUNT", account.getLimits().get(0).getCounterType());
        assertEquals("EUR", account.getLimits().get(0).getCurrency());
        assertEquals(5000000, account.getLimits().get(0).getLimit());
        assertEquals(2400, account.getLimits().get(0).getValue());
        assertEquals("all", account.getLimits().get(0).getPaymentMethodType());
        assertEquals("all", account.getLimits().get(0).getPaymentMethodSubtype());

        LimitsResponse terminalGroup = account.getChildren().get(0);
        assertEquals("terminal-group", terminalGroup.getType());
        assertEquals("Test TG", terminalGroup.getTitle());
        assertNull(terminalGroup.getAcqTerminalId());
        assertEquals(1, terminalGroup.getChildren().size());
        assertEquals(1, terminalGroup.getLimits().size());
        assertEquals("TR_SUCCESS_AMOUNT", terminalGroup.getLimits().get(0).getCounterType());
        assertEquals("EUR", terminalGroup.getLimits().get(0).getCurrency());
        assertEquals(5000000, terminalGroup.getLimits().get(0).getLimit());
        assertEquals(2400, terminalGroup.getLimits().get(0).getValue());
        assertEquals("all", terminalGroup.getLimits().get(0).getPaymentMethodType());
        assertEquals("all", terminalGroup.getLimits().get(0).getPaymentMethodSubtype());

        LimitsResponse terminal = terminalGroup.getChildren().get(0);
        assertEquals("terminal", terminal.getType());
        assertEquals("Test T1", terminal.getTitle());
        assertEquals("5800978", terminal.getAcqTerminalId());
        assertNull(terminal.getChildren());
        assertEquals(2, terminal.getLimits().size());

        assertEquals("TR_SUCCESS_AMOUNT", terminal.getLimits().get(0).getCounterType());
        assertEquals("EUR", terminal.getLimits().get(0).getCurrency());
        assertEquals(5000000, terminal.getLimits().get(0).getLimit());
        assertEquals(28410, terminal.getLimits().get(0).getValue());
        assertEquals("all", terminal.getLimits().get(0).getPaymentMethodType());
        assertEquals("all", terminal.getLimits().get(0).getPaymentMethodSubtype());

        assertEquals("TR_SUCCESS_COUNT", terminal.getLimits().get(1).getCounterType());
        assertEquals("EUR", terminal.getLimits().get(1).getCurrency());
        assertEquals(20000, terminal.getLimits().get(1).getLimit());
        assertEquals(992, terminal.getLimits().get(1).getValue());
        assertEquals("all", terminal.getLimits().get(1).getPaymentMethodType());
        assertEquals("all", terminal.getLimits().get(1).getPaymentMethodSubtype());
    }
}
