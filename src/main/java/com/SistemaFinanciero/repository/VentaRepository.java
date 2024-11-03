package com.SistemaFinanciero.repository;

import com.SistemaFinanciero.entities.Venta;
import com.SistemaFinanciero.entities.Cliente;
import com.SistemaFinanciero.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Date;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Long> {

  List<Venta> findByCliente(Cliente cliente);

  List<Venta> findByProducto(Producto producto);

  List<Venta> findByFechaBetween(Date startDate, Date endDate);

  List<Venta> findByClienteAndProducto(Cliente cliente, Producto producto);
}