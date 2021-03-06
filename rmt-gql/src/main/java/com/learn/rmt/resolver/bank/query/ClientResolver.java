package com.learn.rmt.resolver.bank.query;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.learn.rmt.domain.bank.BankAccount;
import com.learn.rmt.domain.bank.Client;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount>
{
	public Client client(BankAccount bankAccount)
	{
		log.info("Retrieving client using bank account object with id: {}", bankAccount.getId());
		Client client = Client.builder().id(UUID.randomUUID()).firstName("John").lastName("Doe").build();
		return client;
	}
	
	public Client bene(BankAccount bankAccount)
	{
		log.info("Retrieving bene using bank account object with id: {}", bankAccount.getId());
		Client client = Client.builder().id(UUID.randomUUID()).firstName("Mercy").lastName("Doe").build();
		return client;
		//throw new GraphQLException("Unable to retrieve benefeciary at this time as remote system is down!!");
		//throw new RuntimeException("Unable to retrieve benefeciary at this time as remote system is down!!");
	}
}
