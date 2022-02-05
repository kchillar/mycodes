package com.learn.valpack.domain;

import java.util.List;

import com.learn.valpack.controller.form.NamespaceForm;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ObjectTypeVO extends BaseVO
{
	NamespaceForm namespace;
	String typeName;	
	String extendsType;
	boolean isSimpleType = false;
	boolean isList = false;
	List<ObjectFieldVO> fieldInfoList;
	
	@Builder
	public ObjectTypeVO(int errorCode, String errorMsg, NamespaceForm namespace, String typeName, String extendsType,
			boolean isSimpleType, boolean isList, List<ObjectFieldVO> fieldInfoList) {
		super(errorCode, errorMsg);
		this.namespace = namespace;
		this.typeName = typeName;
		this.extendsType = extendsType;
		this.isSimpleType = isSimpleType;
		this.isList = isList;
		this.fieldInfoList = fieldInfoList;
	}

	
}
