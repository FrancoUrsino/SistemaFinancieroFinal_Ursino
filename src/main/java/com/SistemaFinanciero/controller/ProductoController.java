package com.SistemaFinanciero.controller;

import com.SistemaFinanciero.entities.Producto;
import com.SistemaFinanciero.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {

  @Autowired
  private ProductoService productoService;

  @GetMapping
  public List<Producto> getAllProductos() {
    return productoService.findAll();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
    Optional<Producto> producto = productoService.findById(id);
    return producto.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
    Producto nuevoProducto = productoService.save(producto);
    return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Producto> updateProducto(@PathVariable Long id, @RequestBody Producto productoDetails) {
    Optional<Producto> producto = productoService.findById(id);
    if (producto.isPresent()) {
      Producto productoActualizado = productoService.save(productoDetails);
      return ResponseEntity.ok(productoActualizado);
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteProducto(@PathVariable Long id) {
    productoService.deleteById(id);
    return ResponseEntity.noContent().build();
  }
}
