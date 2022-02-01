package com.learn.rmt.resolver.customer.mutation;

import org.springframework.stereotype.Component;

import com.learn.rmt.domain.customer.CardAccount;
import com.learn.rmt.domain.customer.CardAccountInput;

import graphql.kickstart.tools.GraphQLMutationResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CardAccountInputMutationResolver implements GraphQLMutationResolver 
{
	public CardAccount addCardAccount(CardAccountInput cardAccountInput)
	{
		log.info("addCardAccount(CardAccountInput:{})",cardAccountInput.getOwnerServiceRefId());		
		CardAccount cardAccount = CardAccount.builder().ownerServiceRefId(cardAccountInput.getOwnerServiceRefId()).accountNumber(cardAccountInput.getAccountNumber()).accountSubType(cardAccountInput.getAccountSubType()).expiryDate(cardAccountInput.getExpiryDate()).status("Active").serviceRefId("ref123").build();				
		return cardAccount;
	}
}
