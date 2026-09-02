package com.lib.exceptions;

public class BookNotFoundException extends RuntimeException
{
	public BookNotFoundException(String msg)
	{
		super(msg);
	}
}
