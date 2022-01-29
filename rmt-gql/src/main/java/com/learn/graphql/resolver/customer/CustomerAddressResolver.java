package com.learn.graphql.resolver.customer;

import org.springframework.stereotype.Component;

import com.learn.graphql.domain.customer.Address;
import com.learn.graphql.domain.customer.Customer;

import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerAddressResolver implements GraphQLResolver<Customer>
{
	public Address address(Customer customer)
	{
		log.info("customer address(custRefId:{})", customer.getRefId());
		Address address = Address.builder().line1("145 West Mc. Millan").line2("Unit A").city("Cincinnati").state("OH").zip1("45219").build();	
		return address;
	}

}
