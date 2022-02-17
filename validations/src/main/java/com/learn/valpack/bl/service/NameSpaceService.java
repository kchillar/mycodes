package com.learn.valpack.bl.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.valpack.bl.bo.NamespaceBO;
import com.learn.valpack.bl.modal.NamespaceVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NameSpaceService 
{
	@Autowired
	NamespaceBO namespaceBO;
		
	@Transactional
	public NamespaceVO create(ServiceContext context, NamespaceVO input)
	{
		log.info("create({})", input);
		return namespaceBO.create(context, input);
	}
	
	@Transactional
	public NamespaceVO findByName(ServiceContext context, NamespaceVO input)
	{
		log.info("findByName({})", input.getNamespace());
		return namespaceBO.findByName(context, input);
	}

}
