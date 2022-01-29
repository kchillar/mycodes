package com.learn.graphql.resolver.customer.query;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.learn.graphql.domain.customer.Customer;

import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CustomerQueryResolver implements GraphQLQueryResolver
{
	public Customer getCustomerByRefId(UUID refId)
	{
		log.info("getCustomerByRefId({}) ", refId);
		Customer customer = Customer.builder().name("John Doe").refId(refId).build();
		return customer;
	}
}
