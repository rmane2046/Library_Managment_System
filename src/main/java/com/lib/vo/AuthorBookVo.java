package com.lib.vo;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@Getter
@Setter
public class AuthorBookVo 
{
	private AuthorVo authorvo;
	private BookVo bookvo;

}
