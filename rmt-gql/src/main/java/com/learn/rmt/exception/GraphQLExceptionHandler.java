package com.learn.rmt.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;

/**
 * This is one way to handle exception.<br>
 * The other way is by use of CustomerGraphQLErrorHandlers.<br>
 *  
 *  If we want to use this need to configure the following in application.yml :
 *  servlet:
 *    exception-handlers-enabled: true
 *
 */
@Component
public class GraphQLExceptionHandler 
{
	@ExceptionHandler(GraphQLException.class)
	public ThrowableGraphQLError handle(GraphQLException exp)
	{
		return new ThrowableGraphQLError(exp, exp.getMessage());
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ThrowableGraphQLError handle(RuntimeException exp)
	{
		return new ThrowableGraphQLError(exp, "Internal Runtime Exception");
	}
}
