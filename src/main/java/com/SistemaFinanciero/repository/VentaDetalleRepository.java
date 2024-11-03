package com.SistemaFinanciero.repository;

import com.SistemaFinanciero.entities.VentaDetalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaDetalleRepository extends JpaRepository<VentaDetalle, Long> {
  List<VentaDetalle> findByVentaId(Long ventaId);

  List<VentaDetalle> findByProductoId(Long productoId);
}
