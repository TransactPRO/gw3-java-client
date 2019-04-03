package com.gateway.validation;

import com.gateway.validation.base.MoneyGroup;
import com.gateway.validation.base.OrderRecipientNameGroup;
import com.gateway.validation.base.PaymentMethodPanExpGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, MoneyGroup.class, PaymentMethodPanExpGroup.class, OrderRecipientNameGroup.class})
public interface ToPersonGroup {
}
