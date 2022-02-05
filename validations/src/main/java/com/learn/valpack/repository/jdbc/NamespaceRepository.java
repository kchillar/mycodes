package com.learn.valpack.repository.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.learn.valpack.domain.NamespaceVO;
import com.learn.valpack.service.NameSpaceService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class NamespaceRepository
{	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	
	public int create(NamespaceVO nameSpaceInfo) 
	{
		log.info("create() ",nameSpaceInfo);
		final String insQuery = "INSERT INTO obj_namespace_tbl (obj_namespace, code_prefix) VALUES (?,?)";
		int rows = jdbcTemplate.update(insQuery, new Object[] {nameSpaceInfo.getNamespace(), nameSpaceInfo.getPackageName()});
		return rows;
	}

	
	public  NamespaceVO findByName(NamespaceVO input) 
	{
		log.info("findByname({}) ",input.getNamespace());
		final String selQuery = "select obj_namespace, code_prefix, namespace_id from obj_namespace_tbl where obj_namespace = ?";

		Object[] sqlInput = {input.getNamespace()};
		
		List<NamespaceVO> list = jdbcTemplate.query(selQuery, sqlInput, new NamespaceVORowMapper());
		
		log.info("size: "+list.size());
		
		if(list.size() > 0)
			return list.get(0);
		else
			return null;
	}

	public List<NamespaceVO> findAll() 
	{
		log.info("findAll()");
		final String selQuery = "select obj_namespace, code_prefix, namespace_id from obj_namespace_tbl";

		Object[] input = {};
		
		List<NamespaceVO> list = jdbcTemplate.query(selQuery, input, new NamespaceVORowMapper());
		
		log.info("size: "+list.size());
		
		return list;

	}

	
	public NamespaceVO deleteByName(String nameSpaceName) {
		// TODO Auto-generated method stub
		return null;
	}

	
	class NamespaceVORowMapper implements RowMapper<NamespaceVO> 
	 {
		   public NamespaceVO mapRow(ResultSet rs, int rowNum) throws SQLException {
			   NamespaceVO nsi = NamespaceVO.builder().id(rs.getInt("namespace_id")).namespace(rs.getString("obj_namespace")).packageName(rs.getString("code_prefix")).build();
		      return nsi;
		   }
	}

}

 