package com.learn.valpack.bl.modal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Setter
@Getter
public class ObjectField
{
	String fieldName;
	String fieldType;
	String typeId;		
}
