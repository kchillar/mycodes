package com.learn.valpack.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ObjectFieldVO extends BaseVO
{
	String fieldName;
	String fieldType;
	
	@Builder
	public ObjectFieldVO(int errorCode, String errorMsg, String fieldName, String fieldType) {
		super(errorCode, errorMsg);
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}

	
}
