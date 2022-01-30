package com.learn.rmt.domain.customer;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class LinkedAccount 
{
	String serviceRefId;
	String nickName;
	String accountHolderRefId;
	String type;
	String subType;	
}
