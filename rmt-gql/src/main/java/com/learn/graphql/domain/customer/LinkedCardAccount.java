package com.learn.graphql.domain.customer;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;


@Value
@EqualsAndHashCode(callSuper=false)
public class LinkedCardAccount extends LinkedAccount 
{
	private final String validityDate;
	
	@Builder(builderMethodName="cardBuilder")
	public LinkedCardAccount(final String serviceRefId, final String nickName, final String accountHolderRefId, 
			final String type, final String subType, final String validityDate)
	{	
		super(serviceRefId, nickName, accountHolderRefId, type, subType);
		this.validityDate = validityDate;
	}
}
