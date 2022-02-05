package com.learn.valpack.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.valpack.domain.NamespaceVO;
import com.learn.valpack.repository.jdbc.NamespaceRepository;
import com.learn.valpack.service.ServiceContext;
import com.learn.valpack.service.ServiceErrorCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class NamespaceBO 
{
	@Autowired
	NamespaceRepository namespaceRepo;
	
	public NamespaceVO create(ServiceContext context, NamespaceVO input)
	{
		log.info("create({})", input);
		int rows;		
		try
		{
			rows = namespaceRepo.create(input);
			log.info("create({}) returned {} row ",input, rows);
			
			if(rows != 1)
			{
				input.setErrorCode(ServiceErrorCodes.ServiceError);
				input.setErrorMsg("Unable to create namespace");
			}
			return input;
		}
		catch(Exception exp)
		{
			NamespaceVO vo = NamespaceVO.builder().errorCode(ServiceErrorCodes.ServiceError).build();
			input.setErrorMsg("Encountered error");
			log.error("Error", exp);
			return vo;
		}		
	}

	
	public NamespaceVO findByName(ServiceContext context, NamespaceVO input)
	{
		log.info("findByName({})", input.getNamespace());
		NamespaceVO vo;		
		try
		{
			vo = namespaceRepo.findByName(input);			

			if(vo != null)
			{
				log.info("found namespace entry by name: '"+input.getNamespace()+"'");
			}
			else
			{				
				vo = NamespaceVO.builder().errorCode(ServiceErrorCodes.UnableToFindDataInStore).build();
				String msg = "Unable to find namespace entry by name: '"+input.getNamespace()+"'";
				vo.setErrorMsg(msg);
				log.info(msg);
			}
		}
		catch(Exception exp)
		{
			vo = NamespaceVO.builder().errorCode(ServiceErrorCodes.ServiceError).build();
			log.error("Error", exp);
		}		
		return vo;
	}


}
