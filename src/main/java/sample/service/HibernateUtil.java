package sample.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import sample.model.User;

import java.util.Properties;
import java.util.logging.Logger;

public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class.getName());
    private static SessionFactory sessionFactory;

    /**
     * @return new session
     */
    public static Session getSession() {
        logger.info("Session created");
        return getSessionFactory().openSession();
    }

    /**
     * @return sessionFactory
     */
    private static SessionFactory getSessionFactory() {
        Configuration configuration = getConfiguration();

        //annotated classes
        configuration.addAnnotatedClass(User.class);

        //singleton
        if (sessionFactory == null) {
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        logger.info("Session factory was created");
        return sessionFactory;
    }

    //hibernate properties and configuration
    private static Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        Properties settings = new Properties();
        settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
        settings.put(Environment.URL, "jdbc:mysql://localhost:3306/productsfrommarketplace_schema?useSSL=false");
        settings.put(Environment.USER, "root");
        settings.put(Environment.PASS, "root");
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");
        settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
        settings.put(Environment.HBM2DDL_AUTO, "update");
        configuration.setProperties(settings);
        logger.info("Configuration was returned");
        return configuration;
    }
}
