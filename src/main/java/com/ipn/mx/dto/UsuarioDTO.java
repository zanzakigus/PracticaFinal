package com.ipn.mx.dto;

import com.ipn.mx.entidades.Usuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class UsuarioDTO implements Serializable {
    private Usuario entidad;

    public UsuarioDTO() {
        entidad = new Usuario();
    }
}
