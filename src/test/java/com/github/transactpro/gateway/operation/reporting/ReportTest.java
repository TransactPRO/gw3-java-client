package com.github.transactpro.gateway.operation.reporting;

import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.response.CsvResponse;
import com.github.transactpro.gateway.validation.ReportGroup;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.apache.commons.csv.CSVRecord;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    private Report operation;

    @BeforeEach
    void setUp() {
        operation = new Report();
    }

    @AfterEach
    void tearDown() {
        operation = null;
    }

    @Test
    void getRequestUri() {
        assertEquals("/report", operation.getRequestUri());
    }

    @Test
    void getValidationGroups() {
        assertEquals(ReportGroup.class, operation.getValidationGroups());
    }

    @Test
    void validOperationAllFields() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        operation.setCreatedFrom(1L)
                .setCreatedTo(2L)
                .setFinishedFrom(3L)
                .setFinishedTo(4L);

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());

        assertEquals(new Long(1), operation.getCreatedFrom());
        assertEquals(new Long(2), operation.getCreatedTo());
        assertEquals(new Long(3), operation.getFinishedFrom());
        assertEquals(new Long(4), operation.getFinishedTo());
    }

    @Test
    void validOperationMinimumFields() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertTrue(constraintViolations.isEmpty());

        assertNull(operation.getCreatedFrom());
        assertNull(operation.getCreatedTo());
        assertNull(operation.getFinishedFrom());
        assertNull(operation.getFinishedTo());
    }

    @Test
    void invalidOperation() {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        validatorFactory.close();

        operation.setCreatedFrom(-1L)
                .setCreatedTo(0L)
                .setFinishedTo(0L)
                .setFinishedFrom(-3L);

        Set<ConstraintViolation<Request>> constraintViolations = validator.validate(operation.getRequest(), operation.getValidationGroups());
        assertFalse(constraintViolations.isEmpty());
        assertEquals(4, constraintViolations.size());
    }

    @Test
    void parseCsvResponse() {
        ArrayList<HashMap<String, String>> expected = new ArrayList<HashMap<String, String>>() {{
            add(new HashMap<String, String>() {{
                put("aaa", "1");
                put("bbb", "2");
                put("ccc", "3");
            }});
            add(new HashMap<String, String>() {{
                put("aaa", "xxx");
                put("bbb", "yyyy");
                put("ccc", "zzz");
            }});
        }};

        String body = "aaa,bbb,ccc\n"+
                "1,2,3\n"+
                "xxx,yyyy,zzz\n"+
                "\n";

        Response<CsvResponse> response = operation.createResponse(200, body, null);
        CsvResponse parsedResponse = response.parse();

        Iterator<HashMap<String, String>> expectedIterator = expected.iterator();
        for (CSVRecord record : parsedResponse) {
            assertTrue(expectedIterator.hasNext(), "Parsed response contains extra values");
            HashMap<String, String> rowExpected = expectedIterator.next();

            for (Map.Entry<String, String> entry : rowExpected.entrySet()) {
                assertTrue(record.isMapped(entry.getKey()), "Missing value for " + entry.getKey() + " in row " + record.toString());
                assertEquals(entry.getValue(), record.get(entry.getKey()), "Wrong value for " + entry.getKey() + " in row " + record.toString());
            }
        }

        assertFalse(expectedIterator.hasNext(), "Not all expected values are present");
    }
}