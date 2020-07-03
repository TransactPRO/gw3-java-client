package com.github.transactpro.gateway.operation;

import com.github.transactpro.gateway.adapters.CardVerificationModeSerializer;
import com.github.transactpro.gateway.adapters.PaymentMethodDataSourceSerializer;
import com.github.transactpro.gateway.model.Request;
import com.github.transactpro.gateway.model.Response;
import com.github.transactpro.gateway.model.request.data.command.CardVerificationMode;
import com.github.transactpro.gateway.model.request.data.command.PaymentMethodDataSource;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;

import javax.validation.groups.Default;
import java.util.Map;

/**
 * Base class for operations - info, transaction, verify
 */
public abstract class Operation<ResponseT> implements Operable {
    @Getter
    protected String requestUri;

    protected Request request;
    protected Class<ResponseT> responseType;
    @Getter
    protected Response<ResponseT> response;
    @Getter
    protected String method = "POST";

    protected final Gson jsonParser;

    protected Operation() {
        this.request = buildRequest();
        this.request.init();

        this.jsonParser = buildJsonParser();
    }

    protected Request buildRequest() {
        return new Request();
    }

    public Request getRequest() {
        return request;
    }

    protected Gson buildJsonParser() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
                .registerTypeAdapter(CardVerificationMode.class, new CardVerificationModeSerializer())
                .registerTypeAdapter(PaymentMethodDataSource.class, new PaymentMethodDataSourceSerializer())
                .create();
    }

    public Operation<ResponseT> getOperation() {
        return this;
    }

    public Response<ResponseT> createResponse(Integer statusCode, String body, Map<String, String> headers) {
        response = new Response<>(responseType, statusCode, body, headers);
        return response;
    }

    public String getRequestPayload() {
        if (method.equals("GET")) {
            return "";
        }

        return jsonParser.toJson(request);
    }

    /**
     * Default validation group
     *
     * @return validation group for Javax validation
     */
    @Override
    public Class<?> getValidationGroups() {
        return Default.class;
    }
}
