package com.SistemaFinanciero.service;

import com.SistemaFinanciero.entities.Venta;
import com.SistemaFinanciero.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

  private final VentaRepository ventaRepository;

  @Autowired
  public VentaService(VentaRepository ventaRepository) {
    this.ventaRepository = ventaRepository;
  }

  public List<Venta> findAll() {
    return ventaRepository.findAll();
  }

  public Optional<Venta> findById(Long id) {
    return ventaRepository.findById(id);
  }

  public Venta save(Venta venta) {
    return ventaRepository.save(venta);
  }

  public void deleteById(Long id) {
    if (ventaRepository.existsById(id)) {
      ventaRepository.deleteById(id);
    } else {
      throw new RuntimeException("Venta no encontrada con ID: " + id);
    }
  }
}
