package com.ipn.mx.entidades;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@NoArgsConstructor
@Entity
@Table(name = "Producto")
public class Producto implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idProducto;
    @Column(length = 50, nullable = false)
    private String nombreProducto;
    @Column(length = 100, nullable = false)
    private String descripcionProducto;
    @Column(nullable = false)
    private double precioProducto;
    @Column( nullable = false)
    private int existenciaProducto;



    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idCategoria")
    private Categoria idCategoria;


}
