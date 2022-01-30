package com.learn.rmt.resolver.customer.query;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.learn.rmt.domain.customer.Customer;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerResolver implements GraphQLQueryResolver
{	                
	public Customer customerByRefId(UUID refId)
	{
		log.info("customerByRefId({}) ", refId);
		Customer customer = Customer.builder().name("John Doe").email("john.doe@gmail.com").phone("7327621213").refId(refId).build();
		return customer;
	}
}
