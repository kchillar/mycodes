package com.learn.valpack.bl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.learn.valpack.bl.modal.Namespace;
import com.learn.valpack.bl.modal.ObjectType;
import com.learn.valpack.bl.service.ServiceContext;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class ObjectTypeDAO 
{
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int createObjectType(ServiceContext context, ObjectType objectType) 
	{
		log.info("createObjectType(objectType:{})", objectType);
		final StringBuilder buff = new StringBuilder();		
		buff.append("INSERT INTO obj_type_tbl");
		buff.append(" (type_name, extends_type_id, namespace_id, is_simple_type)");
		buff.append(" VALUES");
		buff.append(" (?, ?, ?, ?)");		
		final String insQuery = buff.toString();	
		
		final Object input[] = new Object[] { 
					objectType.getTypeName(),
					objectType.getExtendsTypeId(),
					objectType.getNamespaceId(),
					objectType.isSimpleType()
		};
		
		int rows = jdbcTemplate.update(insQuery, input);
		log.info("createObjectType(rows:{})", rows);
		return rows;
	}

	
	public List<ObjectType> findObjectTypesByTypeNameAndNamespace(ServiceContext context, List<ObjectType> list)
	{
		log.info("findObjectTypesByTypeNameAndNamespace((list:{})", list.size());	
		
		final StringBuilder buff = new StringBuilder();				
		buff.append("SELECT ");
		buff.append("ott.type_name, ott.is_simple_type, ott.type_id, ott.extends_type_id, "); 
		buff.append("ott.namespace_id, ont.client_id ");
		buff.append("FROM ");
		buff.append("obj_type_tbl ott, obj_namespace_tbl ont ");
		buff.append("where ott.type_name= ? and (ott.namespace_id =ont.namespace_id) ");
		buff.append("and ((ont.is_default = true) OR (ott.namespace_id = ?))");
		 
		final String selQuery = buff.toString();				
		List<ObjectType> otList = new ArrayList<>();
		
		for(ObjectType obj: list)
		{
			log.info("obj:{}}", obj);
			final Object[] input = {obj.getTypeName(), obj.getNamespaceId()};		
			List<ObjectType> aList = jdbcTemplate.query(selQuery, input, new ObjectTypeRowMapper());		
			
			if(aList != null && aList.size() == 1)
				otList.add(aList.get(0));				
		}
		return otList;
	}

	class ObjectTypeRowMapper implements RowMapper<ObjectType> 
	 {
		   public ObjectType mapRow(ResultSet rs, int rowNum) throws SQLException 
		   {
			   ObjectType ot = ObjectType.builder().build();
			   int i = 1;
			   ot.setTypeName(rs.getString(i++));
			   ot.setSimpleType(rs.getBoolean(i++));
			   ot.setTypeId(rs.getString(i++));
			   ot.setExtendsTypeId(rs.getString(i++));			   
			   ot.setNamespaceId(rs.getInt(i++));		
			   ot.setClientId(rs.getInt(i++));
			   return ot;
		   }
	}
	
}
