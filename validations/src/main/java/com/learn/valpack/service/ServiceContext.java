package com.learn.valpack.service;

import com.learn.valpack.domain.BaseVO;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class ServiceContext 
{
	private BaseVO requestVO;
	private BaseVO responseVO;
	private boolean isError = true;
	
	
}
