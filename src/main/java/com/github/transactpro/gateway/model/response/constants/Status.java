package com.github.transactpro.gateway.model.response.constants;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public enum Status {
    @SerializedName("1")
    INIT(1),
    @SerializedName("2")
    SENT2BANK(2),
    @SerializedName("3")
    DMS_HOLD_OK(3),
    @SerializedName("4")
    DMS_HOLD_FAILED(4),
    @SerializedName("5")
    SMS_FAILED(5),
    @SerializedName("6")
    DMS_CHARGE_FAILED(6),
    @SerializedName("7")
    SUCCESS(7),
    @SerializedName("8")
    EXPIRED(8),
    @SerializedName("9")
    HOLD_EXPIRED(9),
    @SerializedName("11")
    REFUND_FAILED(11),
    @SerializedName("12")
    REFUND_PENDING(12),
    @SerializedName("13")
    REFUND_SUCCESS(13),
    @SerializedName("14")
    CARDHOLDER_ONSITE(14),
    @SerializedName("15")
    DMS_CANCELED_OK(15),
    @SerializedName("16")
    DMS_CANCELED_FAILED(16),
    @SerializedName("17")
    REVERSED(17),
    @SerializedName("18")
    INPUT_VALIDATION_FAILED(18),
    @SerializedName("19")
    BR_VALIDATION_FAILED(19),
    @SerializedName("20")
    TG_SELECT_FAILED(20),
    @SerializedName("21")
    T_SELECT_FAILED(21),
    @SerializedName("22")
    INIT_PARAMS_INVALID(22),
    @SerializedName("23")
    DECLINED_BY_BR_ACTION(23),
    @SerializedName("24")
    CALLBACK_URL_GENERATED(24),
    @SerializedName("25")
    WAITING_CARD_FORM_FILL(25),
    @SerializedName("26")
    MPI_URL_GENERATED(26),
    @SerializedName("27")
    WAITING_MPI(27),
    @SerializedName("28")
    MPI_FAILED(28),
    @SerializedName("29")
    MPI_NOT_REACHABLE(29),
    @SerializedName("30")
    CARD_FORM_URL_SENT(30),
    @SerializedName("31")
    MPI_AUTH_ERROR(31),
    @SerializedName("32")
    ACQUIRER_NOT_REACHABLE(32),
    @SerializedName("33")
    REVERSAL_FAILED(33),
    @SerializedName("34")
    CREDIT_FAILED(34),
    @SerializedName("35")
    P2P_FAILED(35),
    @SerializedName("36")
    B2P_FAILED(36),
    @SerializedName("37")
    TOKEN_CREATED(37),
    @SerializedName("38")
    TOKEN_CREATE_FAILED(38);

    @Getter
    private final int value;

    Status(int value) {
        this.value = value;
    }
}
