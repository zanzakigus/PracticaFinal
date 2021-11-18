package com.ipn.mx.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GraficaDTO implements Serializable {
    private String nombreCategoria;
    private int cantidad;

}
