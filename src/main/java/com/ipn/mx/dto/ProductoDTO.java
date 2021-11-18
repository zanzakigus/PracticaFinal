package com.ipn.mx.dto;

import com.ipn.mx.entidades.Producto;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ProductoDTO implements Serializable {
    private Producto entidad;

    public ProductoDTO(){
        entidad = new Producto();
    }
}
