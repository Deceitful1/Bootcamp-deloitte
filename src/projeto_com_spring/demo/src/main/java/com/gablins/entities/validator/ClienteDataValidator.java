package com.gablins.entities.validator;




import com.gablins.exceptions.InvalidCPFFormatException;

public class ClienteDataValidator {

    public static boolean validateCPF(String cpf)
    {

        if (!cpf.matches("\\d{11}")) {
            throw new InvalidCPFFormatException("CPF invalido!");

        }

        return true;
    }

    public static boolean validateEmail(String email)
    {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        if (!email.matches(regex)) {
            throw new InvalidCPFFormatException("Email invalido!");
        }
        return true;
    }


}
