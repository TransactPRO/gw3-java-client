package com.gateway.operation.verify;

import com.gateway.model.Request;
import com.gateway.validation.EnrollGroup;
import org.junit.jupiter.api.*;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class Enrolled3DTest {

    private Enrolled3D enrolled3D;

    @BeforeEach
    public void setUp() {
        enrolled3D = new Enrolled3D();
    }

    @AfterEach
    void tearDown() {
        enrolled3D = null;
    }

    @Test
    void getRequestUri() {
        assertTrue(enrolled3D.getRequestUri().startsWith("/"));
    }

    @Test
    void getValidationGroups() {
        assertEquals(EnrollGroup.class, enrolled3D.getValidationGroups());
    }

    @Test
    void validEnrolled3D() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        enrolled3D.setDataCurrency("EUR")
                .setDataPan("4111-1111-1111-1111")
                .setDataTerminalMid("Test");

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(enrolled3D.getRequest(), enrolled3D.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void invalidEnrolled3D() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(enrolled3D.getRequest(), enrolled3D.getValidationGroups());
        assertFalse(constraintViolations.isEmpty());
    }
}