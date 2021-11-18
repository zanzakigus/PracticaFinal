package com.ipn.mx.dao;

import com.ipn.mx.dto.UsuarioDTO;
import com.ipn.mx.entidades.Producto;
import com.ipn.mx.entidades.Usuario;
import com.ipn.mx.utilerias.HIbernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {


    public UsuarioDTO login(UsuarioDTO dto){
        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List list = new ArrayList();

        try {
            t.begin();
            Query q = s.createQuery("from Usuario c where c.nombreUsuario=:nombreUsuario  and c.claveUsuario=:claveUsuario  order by c.idUsuario");
            q.setParameter("nombreUsuario", dto.getEntidad().getNombreUsuario());
            q.setParameter("claveUsuario", dto.getEntidad().getClaveUsuario());
            for (Usuario c:(List<Usuario>) q.list()) {
                UsuarioDTO dtoR = new UsuarioDTO();
                dtoR.setEntidad(c);
                list.add(dtoR);
            }
            t.commit();

        }catch (HibernateException he){
            if(t != null && t.isActive()){
                t.rollback();
            }
        }
        return (UsuarioDTO) list.get(0);
    }

    public void create(UsuarioDTO dto){
        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();

        try {
            t.begin();
            s.save(dto.getEntidad());
            t.commit();

        }catch (HibernateException he){
            if(t != null && t.isActive()){
                t.rollback();
            }
        }
    }

    public void update(UsuarioDTO dto){
        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();

        try {
            t.begin();
            s.update(dto.getEntidad());
            t.commit();

        }catch (HibernateException he){
            if(t != null && t.isActive()){
                t.rollback();
            }
        }
    }

    public void delete(UsuarioDTO dto){
        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(),dto.getEntidad().getIdUsuario()));
            s.delete(dto.getEntidad());
            t.commit();

        }catch (HibernateException he){
            if(t != null && t.isActive()){
                t.rollback();
            }
        }
    }

    public UsuarioDTO read(UsuarioDTO dto){
        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        try {
            t.begin();
            dto.setEntidad(s.get(dto.getEntidad().getClass(),dto.getEntidad().getIdUsuario()));
            t.commit();

        }catch (HibernateException he){
            if(t != null && t.isActive()){
                t.rollback();
            }
        }
        return dto;
    }

    public List readAll(){
        Session s = HIbernateUtil.getSessionFactory().getCurrentSession();
        Transaction t = s.getTransaction();
        List list = new ArrayList();

        try {
            t.begin();
            Query q = s.createQuery("from Usuario c order by c.idUsuario");
            for (Usuario c:(List<Usuario>) q.list()) {
                UsuarioDTO dto = new UsuarioDTO();
                dto.setEntidad(c);
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
}