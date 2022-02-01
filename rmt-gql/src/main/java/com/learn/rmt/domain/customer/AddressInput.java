package com.learn.rmt.domain.customer;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class AddressInput {
	String line1;	
	String line2;	
	String city	;
	String state;	
	String country;	
	String zip1;	
	String zip2;
}
