package com.learn.valpack.repository.jdbc;

import org.springframework.stereotype.Repository;

import com.learn.valpack.controller.form.ObjectTypeForm;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ObjectTypeRepository 
{
	
	public ObjectTypeForm create(ObjectTypeForm objectTypeInfo) 
	{
		log.info("create()");
		// TODO Auto-generated method stub
		return null;
	}

	
	public ObjectTypeForm findByName(String objectTypeName) {
		log.info("findByName()");
		// TODO Auto-generated method stub
		return null;
	}
	
	public ObjectTypeForm deleteByName(String objectTypeName) 
	{
		log.info("deleteByName()");
		// TODO Auto-generated method stub
		return null;
	}
}
