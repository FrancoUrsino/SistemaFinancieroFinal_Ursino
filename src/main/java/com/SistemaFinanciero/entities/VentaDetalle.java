package com.SistemaFinanciero.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "venta_detalles")
@Getter
@Setter
public class VentaDetalle {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "venta_id", nullable = false)
  private Venta venta;

  @ManyToOne
  @JoinColumn(name = "producto_id", nullable = false)
  private Producto producto;

  private int cantidad;

  private Double precio;

  @Column(nullable = false)
  private Double subtotal;

  public VentaDetalle(Venta venta, Producto producto, int cantidad, Double precio) {
    this.venta = venta;
    this.producto = producto;
    this.cantidad = cantidad;
    this.precio = precio;
    this.subtotal = calcularSubtotal();
  }

  public VentaDetalle() {}

  private Double calcularSubtotal() {
    return this.cantidad * this.precio;
  }

  public Double getSubtotal() {
    return subtotal;
  }

  public void setVenta(Venta venta) {
    this.venta = venta;
  }

  public void setCantidad(int cantidad) {
    this.cantidad = cantidad;
    this.subtotal = calcularSubtotal();
  }

  public void setPrecio(Double precio) {
    this.precio = precio;
    this.subtotal = calcularSubtotal();
  }
}