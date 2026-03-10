package com.gablins.exceptions;

import org.springframework.http.HttpStatus;

public record CustomizedMessage(String timestamp,String message,HttpStatus status)
{
}
