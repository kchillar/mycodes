package com.learn.valpack.controller.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseForm 
{
	int errorCode;
	String errorMsg = "";
}
