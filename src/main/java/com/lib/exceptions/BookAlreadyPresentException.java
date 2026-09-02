package com.lib.exceptions;

public class BookAlreadyPresentException extends RuntimeException
{
	public BookAlreadyPresentException(String msg)
	{
		super(msg);
	}
}
