package com.learn.valpack.bl.service;

import com.learn.valpack.bl.modal.BaseVO;

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
