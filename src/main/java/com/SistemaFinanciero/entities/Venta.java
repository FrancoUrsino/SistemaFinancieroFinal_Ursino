package com.SistemaFinanciero.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "ventas")
@Getter
@Setter
public class Venta {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Double total = 0.0;

  private LocalDateTime fecha;

  @ManyToOne
  @JoinColumn(name = "cliente_id", nullable = false)
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name = "producto_id")
  private Producto producto;


  @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
  private List<VentaDetalle> detalles = new ArrayList<>();

  public Venta(Cliente cliente) {
    this.cliente = cliente;
    this.fecha = LocalDateTime.now();
    this.detalles = new ArrayList<>();
  }

  public void addDetalle(VentaDetalle detalle) {
    detalles.add(detalle);
    detalle.setVenta(this);
    total += detalle.getSubtotal();
  }

  public void removeDetalle(VentaDetalle detalle) {
    detalles.remove(detalle);
    detalle.setVenta(null);
    total -= detalle.getSubtotal();
  }

  public void calculateTotal() {
    total = detalles.stream()
            .mapToDouble(VentaDetalle::getSubtotal)
            .sum();
  }

  public Double getTotal() {
    return total;
  }

  public List<VentaDetalle> getDetalles() {
    return detalles;
  }

  public LocalDateTime getFecha() {
    return fecha;
  }
}
