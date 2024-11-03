package com.SistemaFinanciero.controller;

import com.SistemaFinanciero.entities.Venta;
import com.SistemaFinanciero.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/ventas")
public class VentaController {

  @Autowired
  private VentaService ventaService;

  @GetMapping
  public List<Venta> getAllVentas() {
    return ventaService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
    Optional<Venta> venta = ventaService.findById(id);
    return venta.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
    Venta nuevaVenta = ventaService.save(venta);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevaVenta);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Venta> updateVenta(@PathVariable Long id, @RequestBody Venta ventaDetails) {
    Optional<Venta> venta = ventaService.findById(id);
    if (venta.isPresent()) {
      Venta ventaActualizada = ventaService.save(ventaDetails);
      return ResponseEntity.ok(ventaActualizada);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteVenta(@PathVariable Long id) {
    ventaService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
