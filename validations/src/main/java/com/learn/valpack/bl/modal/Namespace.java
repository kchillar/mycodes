package com.learn.valpack.bl.modal;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class Namespace 
{
	private int clientId;
	private String clientName;
	private int namespaceId;
	private String namespace;
	private String packageName;
	private List<ObjectType> objectTypes;


	public String toString()
	{
		StringBuilder buff = new StringBuilder();
		buff.append("{namespace:"+namespace+", package: "+packageName+" id:"+namespaceId+"}");
		return buff.toString();
	}

	
}
