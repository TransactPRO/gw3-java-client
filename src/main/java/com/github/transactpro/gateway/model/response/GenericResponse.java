package com.github.transactpro.gateway.model.response;

import com.github.transactpro.gateway.model.response.parts.Error;
import lombok.Getter;

@Getter
public class GenericResponse {
    private Error error;
}
