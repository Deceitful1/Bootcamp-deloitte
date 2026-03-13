package com.gablins.services.validator;


import com.gablins.entities.Cliente;
import com.gablins.exceptions.BadRequestException;
import com.gablins.exceptions.ClienteNotFoundException;
import com.gablins.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class ClienteServiceValidator
{
    @Autowired
    private ClienteRepository clienteRepository;

        public void validateCreate(Cliente novoCliente)
        {


            if (clienteRepository.existsByCpf(novoCliente.getCpf())) {
                throw new BadRequestException("cpf já cadastrado no sistema.");
            }
            if (clienteRepository.existsByEmail(novoCliente.getEmail())) {
                throw new BadRequestException("email já cadastrado no sistema.");
            }
        }



    public class existsByValidator
    {
        public static void validateExistsByEmail(ClienteRepository clienteRepository, String email)
        {
            if (!clienteRepository.existsByEmail(email)) {
                throw new ClienteNotFoundException("Cliente não encontrado.");
            }
        }

        public static void validateExistsByCpf(ClienteRepository clienteRepository, String cpf)
        {
            if (!clienteRepository.existsByCpf(cpf)) {
                throw new ClienteNotFoundException("Cpf não encontrado.");
            }
        }



    }



}


