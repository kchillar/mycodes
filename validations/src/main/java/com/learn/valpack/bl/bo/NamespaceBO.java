package com.learn.valpack.bl.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.valpack.bl.dao.NamespaceDAO;
import com.learn.valpack.bl.modal.Namespace;
import com.learn.valpack.bl.service.ServiceContext;
import com.learn.valpack.bl.service.ServiceErrorCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NamespaceBO 
{
	@Autowired
	NamespaceDAO namespaceRepo;
	
	public Namespace create(ServiceContext context, Namespace input)
	{
		log.info("create({})", input);
		int rows;		
		try
		{
			rows = namespaceRepo.create(input);
			log.info("create({}) returned {} row ",input, rows);
			
			if(rows == 1)
			{
				context.setSuccess();
			}
			else
			{
				context.setErrorCode(ServiceErrorCodes.ServiceError);
				context.setErrorMessage("Unable to create namespace");
			}
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to create namespace");
			log.error("Error", exp);
		}	
		return input;
	}

	
	public List<Namespace> findNamespaces(ServiceContext context, Namespace input)
	{
		log.info("findNamespaces(clientId:{})", input.getClientId());		
		List<Namespace> list = null;		
		
		try
		{
			list = namespaceRepo.findAllNamespacesByClientId(input);			
			context.setSuccess();
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to create namespace");
			log.error("Error", exp);
		}		
		return list;
	}

	public Namespace findNamespaceByClientAndName(ServiceContext context, Namespace input)
	{
		log.info("findNamespaceByClientAndName(clientId:{}, namespace:{})", input.getClientId(),input.getNamespace());		
		Namespace ns = null;		
		try
		{
			List<Namespace> list = namespaceRepo.findAllNamespacesByClientId(input);
			context.setSuccess();
			
			if(list == null || list.size() == 0)
				return null;
			
			for(Namespace nsl: list)
			{
				log.info("namespaceId:"+nsl.getNamespaceId()+", namespace:'"+nsl.getNamespace()+"', clientId:"+nsl.getClientId());
				if(nsl.getNamespace().equals(input.getNamespace()))			
					ns = nsl;
			}
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to find namespace "+input.getNamespace());
			log.error("Error", exp);
		}		
		log.info("Giving ns: "+ns);
		return ns;
	}


}
