package com.learn.graphql.domain.bank;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class BankAccount 
{
	UUID id;
	Client client;
	Client bene;
	Currency currency;	
}
