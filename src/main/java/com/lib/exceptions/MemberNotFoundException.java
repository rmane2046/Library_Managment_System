package com.lib.exceptions;

public class MemberNotFoundException extends RuntimeException
{
	public MemberNotFoundException(String msg)
	{
		super(msg);
	}
}
