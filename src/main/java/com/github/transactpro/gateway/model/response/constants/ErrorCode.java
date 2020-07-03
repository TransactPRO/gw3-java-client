package com.github.transactpro.gateway.model.response.constants;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;

public enum ErrorCode {
    @SerializedName("1000")
    EEC_GENERAL_ERROR(1000),
    @SerializedName("1001")
    EEC_DISABLED_ACCOUNT(1001),
    @SerializedName("1002")
    EEC_DISABLED_TERMINAL(1002),
    @SerializedName("1003")
    EEC_DISABLED_LEGAL_PERSON(1003),
    @SerializedName("1004")
    EEC_TIMEOUT_TRN(1004),
    @SerializedName("1005")
    EEC_TIMEOUT_REDIRECT(1005),
    @SerializedName("1006")
    EEC_TIMEOUT_3D(1006),
    @SerializedName("1007")
    EEC_TIMEOUT_ACQ(1007),
    @SerializedName("1008")
    EEC_TIMEOUT_INTERNAL(1008),
    @SerializedName("1009")
    EEC_HSM_ENCODE(1009),
    @SerializedName("1010")
    EEC_HSM_DECODE(1010),
    @SerializedName("1011")
    EEC_MERCHANT_COUNTERS_EXCEEDED(1011),
    @SerializedName("1012")
    EEC_ACCOUNT_COUNTERS_EXCEEDED(1012),
    @SerializedName("1013")
    EEC_TERMINAL_GROUP_COUNTERS_EXCEEDED(1013),
    @SerializedName("1014")
    EEC_TERMINAL_COUNTERS_EXCEEDED(1014),
    @SerializedName("1015")
    EEC_HSM_TOKEN(1015),
    @SerializedName("1100")
    EEC_INPUT_VALIDATION_FAILED(1100),
    @SerializedName("1101")
    EEC_FAILED_BUSINESS_RULES(1101),
    @SerializedName("1102")
    EEC_CC_BAD_NUMBER(1102),
    @SerializedName("1103")
    EEC_CC_BAD_EXPIRE(1103),
    @SerializedName("1104")
    EEC_CC_NO_CVV(1104),
    @SerializedName("1105")
    EEC_CC_BAD_CVV(1105),
    @SerializedName("1106")
    EEC_CC_EXPIRED(1106),
    @SerializedName("1107")
    EEC_CC_UNKNOWN_CARD_TYPE(1107),
    @SerializedName("1108")
    EEC_3D_ERROR_MDSTATUS(1108),
    @SerializedName("1109")
    EEC_3D_ERROR_AUTH(1109),
    @SerializedName("1110")
    EEC_CC_LIABILITY_SHIFT(1110),
    @SerializedName("1111")
    EEC_CC_NOT_VALIDATED(1111),
    @SerializedName("1112")
    EEC_3D_DATA_CORRUPTED(1112),
    @SerializedName("1151")
    EEC_WRONG_GW_UNIQ_ID(1151),
    @SerializedName("1152")
    EEC_UNACCEPTABLE_GW_UNIQ_ID(1152),
    @SerializedName("1153")
    EEC_GW_UNIQ_ID_CONFLICT(1153),
    @SerializedName("1154")
    EEC_TRANSACTION_TYPE_INVALID(1154),
    @SerializedName("1155")
    EEC_TRANSACTION_STATE_INVALID(1155),
    @SerializedName("1156")
    EEC_TRANSACTION_ALREADY_FINISHED(1156),
    @SerializedName("1157")
    EEC_NO_PARENT_TRANSACTION_PROVIDED(1157),
    @SerializedName("1158")
    EEC_DYNAMIC_DESCRIPTOR_ERROR(1158),
    @SerializedName("1159")
    EEC_UCOF_ERROR(1159),
    @SerializedName("1200")
    EEC_TERMINAL_NOT_FOUND(1200),
    @SerializedName("1201")
    EEC_ALL_TERMINAL_COUNTERS_EXCEEDED(1201),
    @SerializedName("1202")
    EEC_TERMINAL_GROUP_NOT_FOUND(1202),
    @SerializedName("1203")
    EEC_ALL_TERMINAL_GROUP_COUNTERS_EXCEEDED(1203),
    @SerializedName("1204")
    EEC_TERMINAL_NOT_SUPPORTING_MOTO(1204),
    @SerializedName("1205")
    EEC_TERMINAL_NOT_SUPPORTING_RECURRENTS(1205),
    @SerializedName("1301")
    EEC_DECLINED_BY_ACQUIRER(1301),
    @SerializedName("1302")
    EEC_ACQUIRER_ERROR(1302),
    @SerializedName("1400")
    EEC_INVALID_FORM_ID(1400),
    @SerializedName("1401")
    EEC_FORM_UNAVAILABLE(1401),
    @SerializedName("1500")
    EEC_CARD_VERIFICATION_NO_CARD_DATA(1500),
    @SerializedName("1501")
    EEC_CARD_VERIFICATION_ALREADY_VERIFIED(1501),
    @SerializedName("2000")
    EEC_RBS_INVALID_ORDER_NUMBER(2000),
    @SerializedName("2001")
    EEC_RBS_INVALID_DESCRIPTION(2001);

    @Getter
    private final int value;

    ErrorCode(int value) {
        this.value = value;
    }
}