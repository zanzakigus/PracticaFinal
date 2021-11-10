package com.ipn.mx.utilerias;

import org.hibernate.boot.Metadata;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HIbernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory(){
        if(sessionFactory==null){
            try {
                registry= new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();
                MetadataSources mds = new MetadataSources();
                Metadata metadata = mds.getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
            }catch (Exception e){
                if(registry!=null){
                    StandardServiceRegistryBuilder.destroy(registry);
                }
            }
        }

        return sessionFactory;
    }

    public static void shotdown(){
        if(registry!=null){
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
