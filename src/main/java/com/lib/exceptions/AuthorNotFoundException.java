package com.lib.exceptions;

public class AuthorNotFoundException extends RuntimeException
{
	public AuthorNotFoundException(String msg)
	{
		super(msg);
	}
}
