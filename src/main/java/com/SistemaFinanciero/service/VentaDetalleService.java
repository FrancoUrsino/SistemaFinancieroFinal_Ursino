package com.SistemaFinanciero.service;

import com.SistemaFinanciero.entities.Producto;
import com.SistemaFinanciero.entities.VentaDetalle;
import com.SistemaFinanciero.repository.ProductoRepository;
import com.SistemaFinanciero.repository.VentaDetalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaDetalleService {

  @Autowired
  private VentaDetalleRepository ventaDetalleRepository;

  @Autowired
  private ProductoRepository productoRepository;

  public VentaDetalle save(VentaDetalle detalle) {
    return ventaDetalleRepository.save(detalle);
  }

  public List<VentaDetalle> getAllDetalles() {
    return ventaDetalleRepository.findAll();
  }

  public Optional<Producto> getProductoById(Long id) {
    return productoRepository.findById(id);
  }

  public List<VentaDetalle> findByVentaId(Long ventaId) {
    return ventaDetalleRepository.findByVentaId(ventaId);
  }

  public void deleteById(Long id) {
    Optional<VentaDetalle> detalle = ventaDetalleRepository.findById(id);
    if (detalle.isPresent()) {
      ventaDetalleRepository.deleteById(id);
    } else {
      throw new RuntimeException("Detalle de venta no encontrado con ID: " + id);
    }
  }
}
