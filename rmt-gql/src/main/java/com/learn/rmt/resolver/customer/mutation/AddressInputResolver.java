package com.learn.rmt.resolver.customer.mutation;

import org.springframework.stereotype.Component;

import com.learn.rmt.domain.customer.Address;
import com.learn.rmt.domain.customer.AddressInput;
import com.learn.rmt.domain.customer.CardAccountInput;

import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;



@Slf4j
@Component
public class AddressInputResolver implements GraphQLMutationResolver
{
	public Address cardAddress(AddressInput addressInput)
	{
		log.info("cardAddress({}) ", addressInput.getLine1());
		Address address = Address.builder().line1("1010 Morgans").city("Cincinatti").state("OH").serviceRefId("cardRef1").build();
		return address;
	}
	
	public Address custdAddress(AddressInput addressInput)
	{
		log.info("custAddress({}) ", addressInput.getLine1());
		Address address = Address.builder().line1("645 Scioto").city("Cincinatti").state("OH").serviceRefId("cardRef2").build();
		return address;
	}
}
