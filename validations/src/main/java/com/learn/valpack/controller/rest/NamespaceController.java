package com.learn.valpack.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.valpack.bl.modal.Namespace;
import com.learn.valpack.bl.service.NameSpaceService;
import com.learn.valpack.bl.service.ServiceContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/namespaces")
public class NamespaceController 
{
	@Autowired
	NameSpaceService namespaceService;
	
	@PostMapping("")	
	public ResponseEntity<Namespace> createNamespace(@RequestBody Namespace nameSpace) 
	{
		log.info("createNamespace({})",nameSpace);								
		Namespace vo = namespaceService.create(ServiceContext.builder().build(), nameSpace);
		if(vo != null)
			return new ResponseEntity<>(vo, HttpStatus.OK);
		else
			return ResponseEntity.notFound().build();			
	}
	
	@GetMapping("")
	public ResponseEntity<List<Namespace>> getNamespaces() 
	{
		log.info("getNamespaces({})");
		Namespace ns = Namespace.builder().build();
		ns.setClientId(2);
		
		List<Namespace> list = namespaceService.findByName(ServiceContext.builder().build(), ns);		
				
		if(list != null)
			return new ResponseEntity<>(list, HttpStatus.OK);			
		else
			return ResponseEntity.notFound().build();
	}

	/*
	@GetMapping("/namespace")
	public ResponseEntity<List<NamespaceForm>> getNamespaces() 
	{
		log.info("getNamespaces()");
		ResponseEntity<List<NamespaceForm>> entity;
		List<NamespaceForm> list = objectTypeRepo.findAll();
		
		if(list != null)
			entity = new ResponseEntity<>(list, HttpStatus.OK);
			
		else
			entity = new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
		
		return entity;
	}

	
	@PostMapping("/type")	
	public ResponseEntity<ObjectTypeForm> createObjectType(@RequestBody ObjectTypeForm objectInfo) 
	{
		log.info("createNamespace({})", objectInfo);
		return new ResponseEntity<>(objectInfo, HttpStatus.OK);
	}

	
	@GetMapping("/types/{namespace}")
	public ResponseEntity<List<String>> getTypesInNamespace(@PathVariable("namespace") String nameSpace) 
	{
		log.info("getTypesInNamespace({})",nameSpace);
		List<String> list = new ArrayList<String>();
		list.add("Boolean");
		list.add("String");
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	*/
	
}
