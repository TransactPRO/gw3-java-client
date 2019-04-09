package com.transactpro.gateway.validation;

import com.transactpro.gateway.validation.base.MoneyGroup;
import com.transactpro.gateway.validation.base.PaymentMethodPanExpGroup;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

@GroupSequence({Default.class, MoneyGroup.class, PaymentMethodPanExpGroup.class})
public interface CreditGroup {
}
