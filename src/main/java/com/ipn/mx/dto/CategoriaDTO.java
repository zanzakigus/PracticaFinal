package com.ipn.mx.dto;

import com.ipn.mx.entidades.Categoria;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CategoriaDTO implements Serializable {
    private Categoria entidad;

    public CategoriaDTO(){
        entidad = new Categoria();
    }
}
