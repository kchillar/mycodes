package com.learn.rmt.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import graphql.GraphQLError;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.execution.error.GraphQLErrorHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * This is another way to handler exceptions<br>.
 * @author kalyanc
 *
 */
@Slf4j
@Component
public class CustomGraphQLErrorHandler implements GraphQLErrorHandler
{
	private static final boolean isDevMode = true;
	
	/**
	 * {
  "errors": [
    {
      "message": "Exception while fetching data (/bankAccount/bene) : Unable to retrieve benefeciary at this time as remote system is down!!",
      "locations": [
        {
          "line": 8,
          "column": 5
        }
      ],
      "path": [
        "bankAccount",
        "bene"
      ],
      "extensions": {
        "classification": "DataFetchingException"
      }
    }
  ],
  "extensions": {},
  "data": {
    "bankAccount": null
  }
}
	 */
	@Override
	public List<GraphQLError> processErrors(List<GraphQLError> errors) 
	{
		log.info("Error handler is invoked to handle the errors");
		
		if(!isDevMode)
			return filteredErrors(errors);			
		else		
			return errors;
	}
		
	
	/**
	 * {
  "errors": [
    {
      "message": "Some error"
    }
  ],
  "extensions": {},
  "data": {
    "bankAccount": null
  }
}
	 * @param errors
	 * @return
	 */
	private List<GraphQLError> filteredErrors(List<GraphQLError> errors)
	{
		log.info("will filetering errors and return only some to the client");
		GraphQLError err = new GenericGraphQLError("Some error");
		List<GraphQLError> list = new ArrayList<>();
		list.add(err);
		return list;
	}
		

}
