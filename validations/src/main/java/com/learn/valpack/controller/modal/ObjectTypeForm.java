package com.learn.valpack.controller.modal;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Getter 
public class ObjectTypeForm extends BaseForm 
{	
	NamespaceForm namespace;
	String typeName;	
	String extendsType;
	boolean isSimpleType = false;
	boolean isList = false;
	List<ObjectFieldForm> fieldInfoList;
	
	@Builder
	public ObjectTypeForm(int errorCode, String errorMsg, NamespaceForm namespace, String typeName, String extendsType,
			boolean isSimpleType, boolean isList, List<ObjectFieldForm> fieldInfoList) {
		super(errorCode, errorMsg);
		this.namespace = namespace;
		this.typeName = typeName;
		this.extendsType = extendsType;
		this.isSimpleType = isSimpleType;
		this.isList = isList;
		this.fieldInfoList = fieldInfoList;
	}	
	
	
}
