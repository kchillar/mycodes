package com.learn.valpack.bl.modal;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Client 
{
	private int clientId;
	private String clientName;
	private List<Namespace> namespaces;

}
