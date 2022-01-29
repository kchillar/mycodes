package com.learn.graphql.resolver.customer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.graphql.domain.customer.Customer;
import com.learn.graphql.domain.customer.CustomerBankAccount;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerBankAccountResolver implements GraphQLResolver<Customer>
{
	public List<CustomerBankAccount> bankAccounts(Customer customer)
	{
		log.info("customer bankAccounts(custRefId:{})", customer.getRefId());
		
		List<CustomerBankAccount> list = new ArrayList<>();
		
		CustomerBankAccount acct = CustomerBankAccount.builder().accountNumber("99999999999").nameOnAccount("John D").accountSubType("Checking").build();

		list.add(acct);
		
		acct = CustomerBankAccount.builder().accountNumber("6666666666").nameOnAccount("J Doe").accountSubType("Savings").build();
	
		return list;
	}

}
