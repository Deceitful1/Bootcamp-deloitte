package Semana1.Exceptions;

public class InvalidCPFFormatException extends RuntimeException
{
    public InvalidCPFFormatException(String msg)
    {
        super(msg);
    }

}
