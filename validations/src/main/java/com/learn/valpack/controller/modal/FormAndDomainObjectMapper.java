package com.learn.valpack.controller.modal;

import java.util.ArrayList;
import java.util.List;

import com.learn.valpack.bl.modal.NamespaceVO;
import com.learn.valpack.bl.modal.ObjectFieldVO;
import com.learn.valpack.bl.modal.ObjectTypeVO;

public class FormAndDomainObjectMapper 
{

	public static final NamespaceVO toNamespaceVO(NamespaceForm form)
	{
		NamespaceVO vo = NamespaceVO.builder().namespace(form.getNamespace()).packageName(form.getPackageName()).build();
		return vo;
	}
		
	public static final ObjectTypeVO toObjectTypeVO(ObjectTypeForm form)
	{
		List<ObjectFieldVO> list = null;
		
		if(form.getFieldInfoList() != null && form.getFieldInfoList().size() > 0)
		{
			list =  new ArrayList<>();
			for(ObjectFieldForm fieldForm: form.getFieldInfoList())
				 list.add( toObjectFieldVO(fieldForm));
		}
		ObjectTypeVO vo = ObjectTypeVO.builder().typeName(form.getTypeName()).namespace(form.getNamespace()).extendsType(form.getExtendsType()).isList(form.isList()).isSimpleType(form.isSimpleType()).fieldInfoList(list).build();
		return vo;
	}
	
	public static final ObjectFieldVO toObjectFieldVO(ObjectFieldForm form)
	{
		ObjectFieldVO vo =  ObjectFieldVO.builder().fieldName(form.getFieldName()).fieldType(form.getFieldType()).build();
		return vo;
	}
	
	
	public static final NamespaceForm toNamespaceForm(NamespaceVO vo)	
	{		
		NamespaceForm form = NamespaceForm.builder().namespace(vo.getNamespace()).packageName(vo.getPackageName()).build();
		return form;
	}

}
