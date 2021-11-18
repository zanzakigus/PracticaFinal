package com.ipn.mx.dao;

import com.ipn.mx.dto.GraficaDTO;
import com.ipn.mx.dto.UsuarioDTO;
import com.ipn.mx.entidades.Usuario;
import com.ipn.mx.utilerias.HIbernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class GraficaDAO {

    public List obtenerDatosGrafica() {

        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List list = new ArrayList();

        try {
            t.begin();
            Query q = s.createQuery("select p.idCategoria.nombreCategoria as nombreCategoria , count(*) as cantidad from Producto p  group by  p.idCategoria.nombreCategoria");
            System.out.println(q.list());
            for (String c: (List<String>) q.list()) {
                GraficaDTO dto = new GraficaDTO();
                System.out.println("salida"+c);
                /*dto.setNombreCategoria(c.get("nombreCategoria"));
                dto.setCantidad(rs.getInt("cantidad"));*/
                list.add(dto);
            }
            t.commit();

        }catch (HibernateException he){
            if(t != null && t.isActive()){
                t.rollback();
            }
        }

        return list;
    }


    public static void main(String[] args) {
        GraficaDAO dao = new GraficaDAO();
        dao.obtenerDatosGrafica();
    }
}


