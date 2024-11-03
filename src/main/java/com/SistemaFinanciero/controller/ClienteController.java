package com.SistemaFinanciero.controller;

import com.SistemaFinanciero.entities.Cliente;
import com.SistemaFinanciero.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

  @Autowired
  private ClienteService clienteService;

  @GetMapping
  public List<Cliente> getAllClientes() {
    return clienteService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
    Optional<Cliente> cliente = clienteService.findById(id);
    return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
    Cliente nuevoCliente = clienteService.save(cliente);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoCliente);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Cliente> updateCliente(@PathVariable Long id, @RequestBody Cliente clienteDetails) {
    Optional<Cliente> cliente = clienteService.findById(id);
    if (cliente.isPresent()) {
      Cliente clienteActualizado = clienteService.save(clienteDetails);
      return ResponseEntity.ok(clienteActualizado);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
    clienteService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}