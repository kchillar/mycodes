package com.learn.valpack.bl.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.valpack.bl.bo.NamespaceBO;
import com.learn.valpack.bl.modal.Namespace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class NameSpaceService 
{
	@Autowired
	NamespaceBO namespaceBO;
		
	@Transactional
	public Namespace create(ServiceContext context, Namespace input)
	{
		log.info("create({})", input);
		return namespaceBO.create(context, input);
	}
	
	@Transactional
	public List<Namespace> findByName(ServiceContext context, Namespace input)
	{
		log.info("findByName({clientId:{}})", input.getClientId());
		return namespaceBO.findNamespaces(context, input);
	}

}
