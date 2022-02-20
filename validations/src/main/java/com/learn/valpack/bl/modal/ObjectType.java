package com.learn.valpack.bl.modal;

import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter
@Getter
public class ObjectType
{
	String namespace;	
	int namespaceId;
	
	String typeName;
	String typeId;
	
	String extendsTypeName;
	String extendsTypeId;
	
	boolean isSimpleType;
	boolean isList;	
	List<ObjectField> fieldInfoList;	
	
	int clientId;
	
	public String toString()
	{
		StringBuilder buff = new StringBuilder();		
		buff.append("{isSimple:"+isSimpleType+", ");
		buff.append("typeName:'"+typeName+"',  extendsName:'"+extendsTypeName+"',  namespace:'"+namespace+"', ");
		buff.append("typeId:"+typeId+", extendsTypeId:"+extendsTypeId+", namespaceId:"+namespaceId+"}");	 	
		return buff.toString();
	}
}
