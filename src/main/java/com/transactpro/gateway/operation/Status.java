package com.transactpro.gateway.operation;

/**
 * Representation of gateway operation status codes.
 */
public enum Status {
    INIT(1, "Initial transaction status upon creation"),
    SENT2BANK(2, "Data sent to bank but repsonce not yet received"),
    DMS_HOLD_OK(3, "Valid dms hold"),
    DMS_HOLD_FAILED(4, "Failed dms hold"),
    SMS_FAILED(5, "Failed sms"),
    DMS_CHARGE_FAILED(6, "Failed dms charge on previous dms hold"),
    SUCCESS(7, "DMS&SMS, common flag to optimize requests to DB"),
    EXPIRED(8, "STATUS_INIT and STATUS_SENT2BANK, closed by garbage collector (once per 24 hours?)"),
    HOLD_EXPIRED(9, "STATUS_DMS_HOLD_OK, closed by garbage collector (28 days after)"),
    REFUND_FAILED(11, "Failed refund"),
    REFUND_PENDING(12, "Request for refund is received, but acquirer not yet accepted it"),
    REFUND_SUCCESS(13, "decided to separate it from tr success"),
    CARDHOLDER_ONSITE(14, "user enters cc data on our site, this status supposed to be between STATUS_INIT and STATUS_SENT2BANK"),
    DMS_CANCELED_OK(15, "DMS step 1 canceled ok"),
    DMS_CANCELED_FAILED(16, "DMS step 1 canceled not ok"),
    REVERSED(17, "Reversed transaction"),
    INPUT_VALIDATION_FAILED(18, "Invalid data provided in request"),
    BR_VALIDATION_FAILED(19, "One of business rules failed"),
    TG_SELECT_FAILED(20, "Can not select terminal group"),
    T_SELECT_FAILED(21, "Can not select terminal"),
    INIT_PARAMS_INVALID(22, "Wrong data sent in json request"),
    DECLINED_BY_BR_ACTION(23, "Business rules declined transaction"),
    CALLBACK_URL_GENERATED(24, "Gateway generated url to be used for callback and sent it in response"),
    WAITING_CARD_FORM_FILL(25, "Gateway is waiting for inside form submit"),
    MPI_URL_GENERATED(26, "URL for 3D auth form is prepared"),
    WAITING_MPI(27, "Gateway is waiting for 3D auth process"),
    MPI_FAILED(28, "3D auth failed"),
    MPI_NOT_REACHABLE(29, "MPI service for 3D auth is not available"),
    CARD_FORM_URL_SENT(30, "Merchant have received url for inside form but client has not landed on it yet"),
    MPI_AUTH_ERROR(31, "3D was not successful"),
    ACQUIRER_NOT_REACHABLE(32, "Acquirer unavailable"),
    REVERSAL_FAILED(33, "Reversal process failed");

    private final Integer code;
    private final String message;

    Status(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * Gets status enum by code.
     *
     * @param code from gateway response
     * @return Status enum
     */
    public static Status getByCode(Integer code) {
        for (Status v : values()) {
            if (v.code.equals(code)) {
                return v;
            }
        }
        return null;
    }
}
