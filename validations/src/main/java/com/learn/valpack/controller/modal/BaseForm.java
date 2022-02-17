package com.learn.valpack.controller.modal;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BaseForm 
{
	int errorCode;
	String errorMsg = "";
}
