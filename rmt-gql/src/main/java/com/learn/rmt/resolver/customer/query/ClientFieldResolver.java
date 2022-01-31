package com.learn.rmt.resolver.customer.query;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.learn.rmt.domain.customer.Address;
import com.learn.rmt.domain.customer.Customer;
import com.learn.rmt.domain.customer.InternalBankAccount;
import com.learn.rmt.domain.customer.LinkedAccount;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ClientFieldResolver implements GraphQLResolver<Customer>
{
	public Address  address(Customer customer)
	{
		log.info("customer address(custRefId:{})", customer.getRefId());
		Address address = Address.builder().line1("145 West Mc. Millan").line2("Unit A").city("Cincinnati").state("OH").zip1("45219").build();	
		return address;
	}

	
	public List<InternalBankAccount> bankAccounts(Customer customer)
	{
		log.info("customer bankAccounts(custRefId:{})", customer.getRefId());		
		List<InternalBankAccount> list = new ArrayList<>();		
		InternalBankAccount acct;		
		acct = InternalBankAccount.builder().accountNumber("99999999999").nameOnAccount("John D").accountSubType("Checking").build();
		list.add(acct);		
		acct = InternalBankAccount.builder().accountNumber("6666666666").nameOnAccount("J Doe").accountSubType("Savings").build();		
		list.add(acct);	
		return list;
	}
	
	public List<LinkedAccount> linkedAccounts(Customer customer)
	{
		log.info("customer linkedAccounts(custRefId:{})", customer.getRefId());		
		List<LinkedAccount> list = new ArrayList<>();		
		LinkedAccount acct = LinkedAccount.builder().nickName("Chase Account").serviceRefId("abcRefId").build();
		list.add(acct);
		return list;
	}	
}
