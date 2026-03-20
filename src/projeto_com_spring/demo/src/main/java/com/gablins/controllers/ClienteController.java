package com.gablins.controllers;

import com.gablins.DTOs.ClienteVO;
import com.gablins.entities.Login;
import com.gablins.entities.validator.ClienteDataValidator;
import com.gablins.entities.validator.LoginDataValidator;
import com.gablins.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"*", "http://localhost:63342"})
@RestController
@RequestMapping("/clientes")
public class ClienteController
{

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        ClienteVO cliente = null;

        if (LoginDataValidator.isEmail(login.getLogin())) {
            cliente = clienteService.findByEmail(login.getLogin());
        } else if (LoginDataValidator.isCPF(login.getLogin())) {
            cliente = clienteService.findByCpf(login.getLogin());
        }

        if (cliente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário ou formato de login inválido.");
        }

        if (cliente.getSenha().equals(login.getPassword())) {
            return ResponseEntity.ok(cliente);
        }

        return ResponseEntity.status(401).body("Senha incorreta.");
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<List<ClienteVO>> findAll()
    {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<ClienteVO> create(@RequestBody ClienteVO clienteVO)
    {
        return ResponseEntity.status(201).body(clienteService.create(clienteVO));
    }

    @PutMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE}, consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<ClienteVO> update(@RequestBody ClienteVO clienteVO)
    {
        return ResponseEntity.status(201).body(clienteService.update(clienteVO));
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<ClienteVO> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @GetMapping(params = "email", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<ClienteVO> findByEmail(@RequestParam String email)
    {
        return ResponseEntity.ok(clienteService.findByEmail(email));
    }

    @GetMapping(params = "cpf", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<ClienteVO> findByCpf(@RequestParam String cpf)
    {
        return ResponseEntity.ok(clienteService.findByCpf(cpf));
    }


    @DeleteMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_YAML_VALUE})
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}

