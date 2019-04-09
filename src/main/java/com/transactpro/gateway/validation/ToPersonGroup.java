package com.transactpro.gateway.validation;

import com.transactpro.gateway.validation.base.MoneyGroup;
import com.transactpro.gateway.validation.base.OrderRecipientNameGroup;
import com.transactpro.gateway.validation.base.PaymentMethodPanExpGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, MoneyGroup.class, PaymentMethodPanExpGroup.class, OrderRecipientNameGroup.class})
public interface ToPersonGroup {
}
