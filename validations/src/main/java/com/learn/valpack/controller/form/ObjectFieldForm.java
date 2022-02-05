package com.learn.valpack.controller.form;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ObjectFieldForm extends BaseForm
{
	String fieldName;
	String fieldType;
	
	@Builder
	public ObjectFieldForm(int errorCode, String errorMsg, String fieldName, String fieldType) {
		super(errorCode, errorMsg);
		this.fieldName = fieldName;
		this.fieldType = fieldType;
	}
	
}
