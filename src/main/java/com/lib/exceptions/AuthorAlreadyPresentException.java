package com.lib.exceptions;

public class AuthorAlreadyPresentException extends RuntimeException
{
	public AuthorAlreadyPresentException(String msg)
	{
		super(msg);
	}
}
