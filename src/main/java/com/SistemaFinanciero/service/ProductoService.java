package com.SistemaFinanciero.service;

import com.SistemaFinanciero.entities.Producto;
import com.SistemaFinanciero.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

  private final ProductoRepository productoRepository;

  @Autowired
  public ProductoService(ProductoRepository productoRepository) {
    this.productoRepository = productoRepository;
  }

  public List<Producto> findAll() {
    return productoRepository.findAll();
  }

  public Optional<Producto> findById(Long id) {
    return productoRepository.findById(id);
  }

  public Producto save(Producto producto) {
    return productoRepository.save(producto);
  }

  public void deleteById(Long id) {
    if (productoRepository.existsById(id)) {
      productoRepository.deleteById(id);
    } else {
      throw new RuntimeException("Producto no encontrado con ID: " + id);
    }
  }

  public void updateProductoStock(Producto producto) {
    System.out.println("Actualizando stock para el producto ID: " + producto.getId());
    if (productoRepository.existsById(producto.getId())) {
      productoRepository.save(producto);
      System.out.println("Stock actualizado para el producto: " + producto.getId());
    } else {
      throw new RuntimeException("Producto no encontrado con ID: " + producto.getId());
    }
  }
}