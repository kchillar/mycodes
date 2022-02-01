package com.learn.rmt.domain.customer;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CardAccountInput 
{
	 String ownerServiceRefId;
	 String accountNumber;
	 String accountSubType;
	 String expiryDate;
	 AddressInput cardAddress;	 
}
