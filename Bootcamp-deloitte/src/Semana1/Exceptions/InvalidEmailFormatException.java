package Semana1.Exceptions;


public class InvalidEmailFormatException extends RuntimeException
{
    public InvalidEmailFormatException(String msg)
    {
        super(msg);
    }
}
