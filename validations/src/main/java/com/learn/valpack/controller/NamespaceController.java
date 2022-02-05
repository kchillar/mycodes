package com.learn.valpack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.valpack.controller.form.NamespaceForm;
import com.learn.valpack.domain.NamespaceVO;
import com.learn.valpack.service.NameSpaceService;
import com.learn.valpack.service.ServiceContext;
import com.learn.valpack.service.ServiceErrorCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/namespace")
public class NamespaceController 
{
	@Autowired
	NameSpaceService namespaceService;
	
	@PostMapping("")	
	public ResponseEntity<NamespaceForm> createNamespace(@RequestBody NamespaceForm nameSpace) 
	{
		log.info("createNamespace({})",nameSpace);		
		NamespaceVO input = FormAndDomainObjectMapper.toNamespaceVO(nameSpace);				
		NamespaceVO vo = namespaceService.create(ServiceContext.builder().build(), input);
		NamespaceForm output = FormAndDomainObjectMapper.toNamespaceForm(vo);		
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
	
	@GetMapping("/{namespace}")
	public ResponseEntity<NamespaceForm> getNamespace(@PathVariable("namespace") String nameSpace) 
	{
		log.info("getNamespace({})",nameSpace);
		ResponseEntity<NamespaceForm> entity;
						
		NamespaceVO input = NamespaceVO.builder().namespace(nameSpace).build();
		NamespaceVO vo = namespaceService.findByName(ServiceContext.builder().build(), input);		
		NamespaceForm output = FormAndDomainObjectMapper.toNamespaceForm(vo);
		
				
		if(output.getErrorCode() == ServiceErrorCodes.Success)
			entity = new ResponseEntity<>(output, HttpStatus.OK);
			
		else
			entity = new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		return entity;
	}

	/*
	@GetMapping("/namespace")
	public ResponseEntity<List<NamespaceForm>> getNamespaces() 
	{
		log.info("getNamespaces()");
		ResponseEntity<List<NamespaceForm>> entity;
		List<NamespaceForm> list = namespaceRepo.findAll();
		
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
