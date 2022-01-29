package com.learn.rmt.domain.bank;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Client {
	UUID id;
	String firstName;
	String lastName;

}
