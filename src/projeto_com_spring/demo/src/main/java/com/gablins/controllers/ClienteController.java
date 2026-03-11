package com.gablins.controllers;

import com.gablins.DTOs.ClienteVO;
import com.gablins.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController
{

    @Autowired
    private ClienteService clienteService;

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
        return ResponseEntity.status(204).body(clienteService.update(clienteVO));
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
