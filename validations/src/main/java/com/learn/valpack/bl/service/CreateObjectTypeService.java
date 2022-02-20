package com.learn.valpack.bl.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.learn.valpack.bl.bo.NamespaceBO;
import com.learn.valpack.bl.bo.ObjectTypeBO;
import com.learn.valpack.bl.modal.Namespace;
import com.learn.valpack.bl.modal.ObjectField;
import com.learn.valpack.bl.modal.ObjectType;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CreateObjectTypeService {

	@Autowired
	NamespaceBO namespaceBO;

	@Autowired
	ObjectTypeBO objectTypeBO;

	
	@Transactional
	public ObjectType processRequest1(ServiceContext context, ObjectType input)
	{
		log.info("processRequest({})", input);
		
		if(input == null)
			throw new IllegalStateException("objectType input cannot be null in a call to create ObjectType!!");

		if(input.getTypeName() == null || input.getTypeName().trim().length() == 0)
			throw new IllegalStateException("objectTypeName cannot be null in a call to create ObjectType!!");

		if(input.getNamespace() == null || input.getNamespace().trim().length() == 0)
			throw new IllegalStateException("namespace cannot be null or empty string in a call to create a new ObjectType!!!");
		
		Namespace inputNs = Namespace.builder().namespace(input.getNamespace()).clientId(input.getClientId()).build();
		Namespace ns  = namespaceBO.findNamespaceByClientAndName(context, inputNs);

		log.info("context.isSuccess(): "+context.isSuccess());
		
		if(context.isSuccess())
			context.reset();
		else
			throw new IllegalStateException("Error in getting namespace information from repository");
		
		if(ns == null)
			throw new IllegalStateException("Did not find namespace by name: "+input.getNamespace()+" for clientId: "+input.getClientId());
				
		input.setNamespaceId(ns.getNamespaceId());
		
		List<ObjectType> typeList = new ArrayList<>();		
		typeList.add(input);
		
		if(input.getExtendsTypeName() != null && input.getExtendsTypeName().trim().length() >0)			
			typeList.add(ObjectType.builder().typeName(input.getExtendsTypeName()).namespaceId(input.getNamespaceId()).build());
		
		if(input.getFieldInfoList() != null && input.getFieldInfoList().size() > 0)
			for(ObjectField of: input.getFieldInfoList())			
				typeList.add(ObjectType.builder().typeName(of.getFieldType()).namespaceId(input.getNamespaceId()).build());			
		//log.info("typeList: "+typeList);
		
		List<ObjectType> alist = objectTypeBO.getObjectTypesByTypeNameInNamespace(context, typeList);
		
		log.info("alist: "+alist);
		
		
		ObjectType extendsObjectType = null;
		
		if(context.isSuccess())
		{
			context.reset();
			
			Map<String, ObjectType> map = new HashMap<>();
			
			for(ObjectType ot: alist)
				map.put(ot.getTypeName(), ot);
			
			if(input.getExtendsTypeName() != null && input.getExtendsTypeName().trim().length()>0)
			{
				extendsObjectType = map.get(input.getExtendsTypeName());

				//The input asks to extends but, we do not have the extending type in the repoS
				if(extendsObjectType == null)
					throw new IllegalStateException("Unable to find extends type: "+input.getExtendsTypeName()+" in namespace: "+input.getNamespace());
				
				if(extendsObjectType.isSimpleType() && input.getFieldInfoList() != null && input.getFieldInfoList().size()>0)				
					throw new IllegalStateException("Cannot extend a simple type and have child fields !!!");				
			}
			
			ObjectType ot = map.get(input.getTypeName());
				
			if(ot == null)
			{
				ot = ObjectType.builder().build();
				ot.setNamespace(ns.getNamespace());
				ot.setNamespaceId(ns.getNamespaceId());
				ot.setTypeName(input.getTypeName());
				
				if(extendsObjectType != null)
				{
					ot.setSimpleType(extendsObjectType.isSimpleType());
					ot.setExtendsTypeName(extendsObjectType.getTypeName());
					ot.setExtendsTypeId(extendsObjectType.getTypeId());
				}
				
				objectTypeBO.createObjectType(context, ot);
				
				List<ObjectType> childObjectTypesList = new ArrayList<>();
				
				if(input.getFieldInfoList()!= null && input.getFieldInfoList().size()>0)
				{
					for(ObjectField of: input.getFieldInfoList())
					{
						if(map.get(of.getFieldType())==null)
						{
							ObjectType cot = ObjectType.builder().build();
							cot.setNamespace(ns.getNamespace());
							cot.setNamespaceId(ns.getNamespaceId());
							cot.setTypeName(of.getFieldType());
							childObjectTypesList.add(cot);
						}
					}
				}
				
				log.info("Successfully created objectType:{}",ot);
				return ot;

			}
			else
			{
				log.info("The type by name: "+input.getTypeName()+" and typeId: "+ot.getTypeId()+" already exists in namespace: "+ input.getNamespace()+"!!. Will not create again!!!");
				return ot;				
			}
		}
		else 
		{
			log.error("Error in checking for types names in repository!!!");
			return null;
		}		
	}
	

	@Transactional
	public ObjectType processRequest(ServiceContext context, ObjectType input)
	{
		log.info("processRequest({})", input);
		
		if(input == null)
			throw new IllegalStateException("objectType input cannot be null in a call to create ObjectType!!");

		if(input.getTypeName() == null || input.getTypeName().trim().length() == 0)
			throw new IllegalStateException("objectTypeName cannot be null in a call to create ObjectType!!");

		if(input.getNamespace() == null || input.getNamespace().trim().length() == 0)
			throw new IllegalStateException("namespace cannot be null or empty string in a call to create a new ObjectType!!!");
		
		Namespace inputNs = Namespace.builder().namespace(input.getNamespace()).clientId(input.getClientId()).build();
		Namespace ns  = namespaceBO.findNamespaceByClientAndName(context, inputNs);
		
		if(context.isSuccess())
			context.reset();
		else
			throw new IllegalStateException("Error in getting namespace information from repository");		
		if(ns == null)
			throw new IllegalStateException("Did not find namespace by name: "+input.getNamespace()+" for clientId: "+input.getClientId());
				
		input.setNamespaceId(ns.getNamespaceId());
		
		Map<String, ObjectType> existingTypesMap = objectTypeBO.getExistingObjectTypesMap(context, input);				
		ObjectType extendsObjectType = null;
		
		if(context.isSuccess())
		{
			context.reset();						
			if(input.getExtendsTypeName() != null && input.getExtendsTypeName().trim().length()>0)
			{
				extendsObjectType = existingTypesMap.get(input.getExtendsTypeName());
				//The input type uses extends but the extends type in not found in our repo
				if(extendsObjectType == null)
					throw new IllegalStateException("Unable to find extends type: "+input.getExtendsTypeName()+" in namespace: "+input.getNamespace());				
				if(extendsObjectType.isSimpleType() && input.getFieldInfoList() != null && input.getFieldInfoList().size()>0)				
					throw new IllegalStateException("The type '"+input.getTypeName()+"' is trying to extend a simple type:'"+ extendsObjectType.getTypeName() +"' and cannot have child fields for simple type!!!");				
			}
			
			ObjectType ot = existingTypesMap.get(input.getTypeName());
				
			if(ot == null)
			{
				ot = ObjectType.builder().build();
				ot.setNamespace(ns.getNamespace());
				ot.setNamespaceId(ns.getNamespaceId());
				ot.setTypeName(input.getTypeName());				
				if(extendsObjectType != null)
				{
					ot.setSimpleType(extendsObjectType.isSimpleType());
					ot.setExtendsTypeName(extendsObjectType.getTypeName());
					ot.setExtendsTypeId(extendsObjectType.getTypeId());
				}				
				objectTypeBO.createObjectType(context, ot);
				objectTypeBO.createFieldObjectTypes(context, ot, input.getFieldInfoList(), existingTypesMap);								
			}
			else
			{
				log.info("The type by name: "+input.getTypeName()+" and typeId: "+ot.getTypeId()+" already exists in namespace: "+ input.getNamespace()+"!!. Will not create again!!!");				
			}
			
			return ot;
		}
		else 
		{
			log.error("Error in checking for types names in repository!!!");
			return null;
		}		
	}
	

}
