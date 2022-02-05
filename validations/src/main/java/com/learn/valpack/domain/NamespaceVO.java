package com.learn.valpack.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NamespaceVO extends BaseVO
{
	private int id;
	private String namespace;
	private String packageName;
	
	@Builder
	public NamespaceVO(int errorCode, String errorMsg, int id, String namespace, String packageName) {
		super(errorCode, errorMsg);
		this.id = id;
		this.namespace = namespace;
		this.packageName = packageName;
	}

	public String toString()
	{
		StringBuilder buff = new StringBuilder();
		buff.append("{namespace:"+namespace+", package: "+packageName+" id:"+id+"}");
		return buff.toString();
	}

	
}
