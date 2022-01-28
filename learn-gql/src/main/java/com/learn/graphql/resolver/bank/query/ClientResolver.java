package com.learn.graphql.resolver.bank.query;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.learn.graphql.domain.bank.BankAccount;
import com.learn.graphql.domain.bank.Client;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount>
{
	public Client client(BankAccount bankAccount)
	{
		log.info("Retrieving client using bank account object with id: {}", bankAccount.getId());
		Client client = Client.builder().id(UUID.randomUUID()).firstName("Sriram").lastName("Chillara").build();
		return client;
	}
	
	public Client bene(BankAccount bankAccount)
	{
		log.info("Retrieving bene using bank account object with id: {}", bankAccount.getId());
		Client client = Client.builder().id(UUID.randomUUID()).firstName("Srikari").lastName("Chillara").build();
		return client;
		//throw new GraphQLException("Unable to retrieve benefeciary at this time as remote system is down!!");
		//throw new RuntimeException("Unable to retrieve benefeciary at this time as remote system is down!!");
	}
}
