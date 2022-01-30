package com.learn.rmt.domain.customer;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class CustomerBankAccount 
{
  String nameOnAccount;
  String routingNumber;
  String accountNumber;
  String accountSubType;
  String status;
}
