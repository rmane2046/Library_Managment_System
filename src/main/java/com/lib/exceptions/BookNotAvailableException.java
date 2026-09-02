package com.lib.exceptions;

public class BookNotAvailableException extends RuntimeException
{
	public BookNotAvailableException(String msg)
	{
		super(msg);
	}
}
