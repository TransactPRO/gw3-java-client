package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.constants.Enrollment;
import lombok.Getter;

@Getter
public class EnrollmentResponse extends GenericResponse {
    private Enrollment enrollment;
}
