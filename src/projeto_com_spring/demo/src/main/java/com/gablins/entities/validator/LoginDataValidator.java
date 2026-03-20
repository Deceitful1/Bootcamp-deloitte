package com.gablins.entities.validator;

public class LoginDataValidator
{


    public static boolean isEmail(String email) {
        String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
        return email != null && email.matches(regex);
    }

    public static boolean isCPF(String cpf) {
        return cpf != null && cpf.matches("\\d{11}");
    }
}
