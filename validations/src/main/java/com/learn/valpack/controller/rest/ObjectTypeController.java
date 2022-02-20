package com.learn.valpack.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.valpack.bl.modal.ObjectType;
import com.learn.valpack.bl.service.CreateObjectTypeService;
import com.learn.valpack.bl.service.ServiceContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/objecttypes")
public class ObjectTypeController {

	@Autowired
	CreateObjectTypeService objectTypeService;
	
	@PostMapping("")	
	public ResponseEntity<ObjectType> createObjectType(@RequestBody ObjectType objectType) 
	{
		log.info("objectType({})",objectType);
		
		ObjectType vo = objectTypeService.processRequest(ServiceContext.builder().build(), objectType);
		
		if(vo != null)
			return new ResponseEntity<>(vo, HttpStatus.OK);
		else
			return ResponseEntity.notFound().build();			
	}
	
}
