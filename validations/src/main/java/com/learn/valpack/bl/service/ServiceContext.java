package com.learn.valpack.bl.service;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Builder
@Getter
@Setter
public class ServiceContext 
{
	private boolean isSuccess;
	private int errorCode;
	private String errorMessage;
	
	public void reset()
	{
		setSuccess(false);
	}
	
	public void setSuccess()
	{
		setSuccess(true);
	}
}

