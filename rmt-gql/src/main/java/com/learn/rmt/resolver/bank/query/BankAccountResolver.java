package com.learn.rmt.resolver.bank.query;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.learn.rmt.domain.bank.BankAccount;
import com.learn.rmt.domain.bank.Currency;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver 
{
	public BankAccount bankAccount(UUID id)
	{
		log.info("Retrieving bank account by id: {} ", id);				
		return BankAccount.builder().id(id).currency(Currency.INR).build();
	}
	
	
}
