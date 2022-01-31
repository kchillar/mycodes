package com.learn.rmt.domain.customer;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CardAccount 
{
	 String serviceRefId;
	 String ownerServiceRefId;
	 String accountNumber;
	 String accountSubType;
	 String expiryDate;
	 String status;
}
