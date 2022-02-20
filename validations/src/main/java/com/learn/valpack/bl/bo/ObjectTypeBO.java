package com.learn.valpack.bl.bo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.learn.valpack.bl.dao.ObjectTypeDAO;
import com.learn.valpack.bl.modal.ObjectField;
import com.learn.valpack.bl.modal.ObjectType;
import com.learn.valpack.bl.service.ServiceContext;
import com.learn.valpack.bl.service.ServiceErrorCodes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class ObjectTypeBO 
{	
	@Autowired
	ObjectTypeDAO objectTypeRepo; 
	
	public ObjectType createObjectType(ServiceContext context, ObjectType objectType)
	{
		log.info("createObjectType({})", objectType);		
				
		try
		{
			int rows = objectTypeRepo.createObjectType(context, objectType);
			if(rows == 1)
			{
				log.info("Successfully created objectType:{}",objectType);
				context.setSuccess();
			}
			else
			{
				context.reset();
				context.setErrorMessage("Got rows as:"+rows+" in creation of objectType:"+objectType);
			}
			
			return objectType;
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to populate object ids");
			log.error("Error", exp);
			return null;
		}			
	}

	
	public ObjectType createFieldObjectTypes(ServiceContext context, ObjectType ot, List<ObjectField> childFields, Map<String, ObjectType> existingObjectTypeMap)
	{
		log.info("createFieldOjectType({})", ot);		
				
		try
		{
			if(childFields == null || childFields.size() == 0)
				return ot;
						
			for(ObjectField of: childFields)
			{
				if(existingObjectTypeMap.get(of.getFieldType()) == null)
				{
					ObjectType cot = ObjectType.builder().build();
					cot.setNamespace(ot.getNamespace());
					cot.setNamespaceId(ot.getNamespaceId());
					cot.setTypeName(of.getFieldType());
					
					context.reset();
					this.createObjectType(context, cot);
					if(!context.isSuccess())
						throw new IllegalStateException("Unable to create field type: "+cot.getTypeName());
				}
			}
			
			context.setSuccess();
			log.info("createFieldOjectType({})", context.isSuccess());
			return ot;
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to populate object ids");
			log.error("Error", exp);
			return null;
		}			
	}

	
	public List<ObjectType> getObjectTypesByTypeNameInNamespace(ServiceContext context, List<ObjectType> list)
	{
		log.info("getObjectTypesByNamespaceIdAndTypeName(list:{})", list.size());		
				
		try
		{
			List<ObjectType> objList = objectTypeRepo.findObjectTypesByTypeNameAndNamespace(context, list);				
			if(objList!= null && objList.size()>0)
			{
				for(ObjectType ot: objList)
					log.info("obj-->{}",ot);
			}			
			context.setSuccess();
			return objList;
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to populate object ids");
			log.error("Error", exp);
			return null;
		}			
	}
	
	public Map<String, ObjectType> getExistingObjectTypesMap(ServiceContext context, ObjectType input)
	{
		log.info("getExistingObjectTypesMap(namespaceId:{}, objectTypeName:{}, extendsTypeName:{} )", input.getNamespaceId(), input.getTypeName(), input.getExtendsTypeName());
		Map<String, ObjectType> map = new HashMap<>();
		try
		{
			List<ObjectType> inputTypesList = new ArrayList<>();		
			inputTypesList.add(input);
			
			if(input.getExtendsTypeName() != null && input.getExtendsTypeName().trim().length() >0)			
				inputTypesList.add(ObjectType.builder().typeName(input.getExtendsTypeName()).namespaceId(input.getNamespaceId()).build());
			
			if(input.getFieldInfoList() != null && input.getFieldInfoList().size() > 0)
				for(ObjectField of: input.getFieldInfoList())			
					inputTypesList.add(ObjectType.builder().typeName(of.getFieldType()).namespaceId(input.getNamespaceId()).build());			
			
			List<ObjectType> existingTypesList = objectTypeRepo.findObjectTypesByTypeNameAndNamespace(context, inputTypesList);

			log.info("inputTypesList.size():{}, existingTypesList.size():{}", inputTypesList.size(), existingTypesList.size());
			
			for(ObjectType ot: existingTypesList)
				map.put(ot.getTypeName(), ot);
			
			log.info("getExistingObjectTypesMap(map.size():{}", map.size());
			context.setSuccess();
			return map;
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to populate object ids");
			log.error("Error", exp);
			return null;
		}			
	}

	public Map<String, ObjectType> getMissingObjectTypesMap(ServiceContext context, ObjectType input)
	{
		log.info("getMissingTypesMap(namespaceId:{}, objectTypeName:{}, extendsTypeName:{} )", input.getNamespaceId(), input.getTypeName(), input.getExtendsTypeName());
		Map<String, ObjectType> missingTypesMap = new HashMap<>();
		try
		{
			List<ObjectType> inputTypesList = new ArrayList<>();		
			inputTypesList.add(input);
			
			if(input.getExtendsTypeName() != null && input.getExtendsTypeName().trim().length() >0)			
				inputTypesList.add(ObjectType.builder().typeName(input.getExtendsTypeName()).namespaceId(input.getNamespaceId()).build());
			
			if(input.getFieldInfoList() != null && input.getFieldInfoList().size() > 0)
				for(ObjectField of: input.getFieldInfoList())			
					inputTypesList.add(ObjectType.builder().typeName(of.getFieldType()).namespaceId(input.getNamespaceId()).build());			
									
			List<ObjectType> existingTypesList = objectTypeRepo.findObjectTypesByTypeNameAndNamespace(context, inputTypesList);

			log.info("inputTypesList.size():{}, existingTypesList.size():{}", inputTypesList.size(), existingTypesList.size());
			
			for(ObjectType ot: inputTypesList)
				missingTypesMap.put(ot.getTypeName(),  ot);

			for(ObjectType ot: existingTypesList)			
				missingTypesMap.remove(ot.getTypeName());
			log.info("getMissingObjectTypesMap(map.size():{}", missingTypesMap.size());
			context.setSuccess();
			return missingTypesMap;
		}
		catch(Exception exp)
		{
			context.setErrorCode(ServiceErrorCodes.ServiceError);
			context.setErrorMessage("Unable to populate object ids");
			log.error("Error", exp);
			return null;
		}			
	}

}
