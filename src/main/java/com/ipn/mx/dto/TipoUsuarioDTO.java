package com.ipn.mx.dto;

import com.ipn.mx.entidades.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class TipoUsuarioDTO implements Serializable {
    private TipoUsuario entidad;

    public TipoUsuarioDTO(){
        entidad=new TipoUsuario();
    }
}
