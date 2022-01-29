package com.learn.graphql.domain.customer;

import lombok.Value;
import lombok.experimental.NonFinal;

@Value
@NonFinal
public class LinkedAccount 
{
	private final String serviceRefId;
	private final String nickName;
	private final String accountHolderRefId;
	private final String type;
	private final String subType;
	
}
