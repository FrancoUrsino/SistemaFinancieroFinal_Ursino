package com.SistemaFinanciero.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "productos")
@Getter
@Setter
public class Producto {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;  // Clave primaria

  @Column(nullable = false)
  private String nombre;

  @Column(nullable = false)
  private Double precio;

  private String descripcion;

  @Column(nullable = false)
  private int stock;

  public Producto(String nombre, String descripcion, Double precio, int stock) {
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.precio = precio;
    this.stock = stock;
  }

  public Producto() {}
}
