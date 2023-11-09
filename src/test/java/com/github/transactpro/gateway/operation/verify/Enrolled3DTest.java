package com.github.transactpro.gateway.operation.verify;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.EnrollmentResponse;
import com.github.transactpro.gateway.model.response.constants.Enrollment;
import com.github.transactpro.gateway.validation.EnrollGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Set;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class Enrolled3DTest {

    private Enrolled3D operation;

    @BeforeEach
    void setUp() {
        operation = new Enrolled3D();
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
        assertEquals(EnrollGroup.class, operation.getValidationGroups());
    }

    @Test
    void validOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        operation.setDataCurrency("EUR")
                .setDataPan("4111-1111-1111-1111")
                .setDataTerminalMid("Test");

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());
    }

    @Test
    void invalidOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertFalse(constraintViolations.isEmpty());
    }

    @ParameterizedTest
    @MethodSource("enrollmentTestData")
    void parseEnrollmentResponse(String body, Enrollment expectedResult) {
        Response<EnrollmentResponse> response = operation.createResponse(200, body, null);
        EnrollmentResponse parsedResponse = response.parse();
        assertEquals(expectedResult, parsedResponse.getEnrollment());
    }

    private static Stream<Arguments> enrollmentTestData() {
        return Stream.of(
                Arguments.of("{\"enrollment\":\"y\"}", Enrollment.YES),
                Arguments.of("{\"enrollment\":\"n\"}", Enrollment.NO),
                Arguments.of("{\"enrollment\":\"abracadabra\"}", null)
        );
    }
}