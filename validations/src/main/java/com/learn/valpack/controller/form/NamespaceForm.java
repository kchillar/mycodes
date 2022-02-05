package com.learn.valpack.controller.form;

import lombok.Builder;
import lombok.Getter;


@Getter
public class NamespaceForm extends BaseForm
{	
	private String namespace;
	private String packageName;
	

	@Builder
	public NamespaceForm(int errorCode, String errorMsg,String namespace, String packageName) {
		super(errorCode, errorMsg);		
		this.namespace = namespace;
		this.packageName = packageName;
	}
	
	
	public String toString()
	{
		StringBuilder buff = new StringBuilder();
		buff.append("{namespace:"+namespace+", package: "+packageName+"}");
		return buff.toString();
	}

}
