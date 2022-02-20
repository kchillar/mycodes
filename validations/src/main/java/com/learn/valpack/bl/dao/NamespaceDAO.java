package com.learn.valpack.bl.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.learn.valpack.bl.modal.Namespace;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class NamespaceDAO
{	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public int create(Namespace nameSpaceInfo) 
	{
		log.info("create() ",nameSpaceInfo);
		final StringBuilder buff = new StringBuilder();		
		buff.append("INSERT INTO obj_namespace_tbl");
		buff.append(" (obj_namespace, code_prefix, client_id)");
		buff.append(" VALUES");
		buff.append(" (?, ?, ?)");		
		final String insQuery = buff.toString();	
		
		final Object input[] = new Object[] { 
					nameSpaceInfo.getNamespace(),
					nameSpaceInfo.getPackageName(),
					nameSpaceInfo.getClientId()
			};
		int rows = jdbcTemplate.update(insQuery, input);
		return rows;
	}
	
	public List<Namespace> findAllNamespacesByClientId(Namespace namespace) 
	{
		log.info("findAllNamespacesByClientId(clientId:{})", namespace.getClientId());
		
		final StringBuilder buff = new StringBuilder();		
		buff.append("SELECT");
		buff.append(" obj_namespace, code_prefix, namespace_id, client_id");
		buff.append(" FROM obj_namespace_tbl where ");
		buff.append(" client_id = ? ");		
		final String selQuery = buff.toString();			
		final Object[] input = {namespace.getClientId()};		
		List<Namespace> list = jdbcTemplate.query(selQuery, input, new NamespaceRowMapper());		
		log.info("size: "+list.size());		
		return list;
	}

	public List<Namespace> findNamespaceByClientIdAndName(Namespace namespace) 
	{
		log.info("findNamespaceByClientIdAndName(clientId:{})", namespace.getClientId());
		
		final StringBuilder buff = new StringBuilder();		
		buff.append("SELECT");
		buff.append(" obj_namespace, code_prefix, namespace_id, client_id");
		buff.append(" FROM obj_namespace_tbl where ");
		buff.append(" client_id = ? and obj_namespace = ?");		
		final String selQuery = buff.toString();			
		final Object[] input = {namespace.getClientId(), namespace.getNamespace()};		
		List<Namespace> list = jdbcTemplate.query(selQuery, input, new NamespaceRowMapper());		
		log.info("size: "+list.size());		
		return list;
	}

	
	public int deleteById(Namespace namespace) 
	{
		log.info("deleteById(namespaceId:{}) ",namespace.getNamespaceId());
		final StringBuilder buff = new StringBuilder();		
		buff.append("DELETE FROM obj_namespace_tbl");
		buff.append(" where");
		buff.append(" namespace_id = ?");
	
		final String insQuery = buff.toString();	
		
		final Object input[] = new Object[] { 
					namespace.getNamespaceId()
			};
		int rows = jdbcTemplate.update(insQuery, input);
		log.info(" rows:{} deleteById(namespaceId:{}) ",rows, namespace.getNamespaceId());
		return rows;
	}

	
	class NamespaceRowMapper implements RowMapper<Namespace> 
	 {
		   public Namespace mapRow(ResultSet rs, int rowNum) throws SQLException 
		   {
			   Namespace nm = Namespace.builder().build();
			   int i = 1;
			   nm.setNamespace(rs.getString(i++));
			   nm.setPackageName(rs.getString(i++));
			   nm.setNamespaceId(rs.getInt(i++));
			   nm.setClientId(rs.getInt(i++));			   
		      return nm;
		   }
	}

}

 