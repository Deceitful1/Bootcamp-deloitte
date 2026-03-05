package Semana1.exceptions;

public class InvalidCPFFormatException extends RuntimeException
{
    public InvalidCPFFormatException(String msg)
    {
        super(msg);
    }

}