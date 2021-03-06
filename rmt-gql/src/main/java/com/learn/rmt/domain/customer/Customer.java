package com.learn.rmt.domain.customer;

import java.util.List;
import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Customer {

	UUID refId; //The reference id within our system
	String extRefId; //The bank reference id of the customer in the bank
	String serviceRefId; //The customer reference id in the money movement service (API)	
	String name;
	String email;
	String phone;	
	Address address;
	List<InternalBankAccount> bankAccounts;
	List<LinkedAccount> linkedAccounts;
	String status;
}
