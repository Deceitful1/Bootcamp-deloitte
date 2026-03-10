package com.gablins.controllers;

import com.gablins.DTOs.ClienteVO;
import com.gablins.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController
{

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public ResponseEntity<List<ClienteVO>> findAll()
    {
        return ResponseEntity.ok(clienteService.findAll());
    }

    @PostMapping
    public ResponseEntity<ClienteVO> create(@RequestBody ClienteVO clienteVO)
    {
        return ResponseEntity.status(201).body(clienteService.create(clienteVO));
    }

    @PutMapping()
    public ResponseEntity<ClienteVO> update(@RequestBody ClienteVO clienteVO)
    {
        return ResponseEntity.status(204).body(clienteService.update(clienteVO));
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<ClienteVO> findById(@PathVariable Long id)
    {
        return ResponseEntity.ok(clienteService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id)
    {
        clienteService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
