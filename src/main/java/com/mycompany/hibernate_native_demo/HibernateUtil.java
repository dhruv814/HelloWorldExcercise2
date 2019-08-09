package com.mycompany.hibernate_native_demo;

import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Environment;

@SuppressWarnings("deprecation")
public class HibernateUtil {

    private static StandardServiceRegistry registry;
    private static SessionFactory sessionFactory;
    final static String MY_DRIVER="org.postgresql.Driver";
    final static String MY_URL="jdbc:postgresql://localhost:5432/jdbcTemplatedb?useSSL=false";
    final static String MY_USER="postgres";
    final static String MY_PASSWORD="root";
    final static String MY_HBM2DDL="validate";
    
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();

                //Configuration properties
                Map<String, Object> settings = new HashMap<>();
                settings.put(Environment.DRIVER, MY_DRIVER);
                settings.put(Environment.URL, MY_URL);
                settings.put(Environment.USER, MY_USER);
                settings.put(Environment.PASS, MY_PASSWORD);
                settings.put(Environment.HBM2DDL_AUTO, MY_HBM2DDL);
                settings.put(Environment.SHOW_SQL, true);

                registryBuilder.applySettings(settings);
                registry = registryBuilder.build();

                MetadataSources sources = new MetadataSources(registry);
                sources.addAnnotatedClass(HelloWorld.class);
                
                Metadata metadata = sources.getMetadataBuilder().build();

                sessionFactory = metadata.getSessionFactoryBuilder().build();
            } catch (Exception e) {
                if (registry != null) {
                    StandardServiceRegistryBuilder.destroy(registry);
                }
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (registry != null) {
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }

}
