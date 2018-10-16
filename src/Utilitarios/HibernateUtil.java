package Utilitarios;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;


public class HibernateUtil {

    private static SessionFactory sessionFactory;
    private static ServiceRegistry serviceRegistry;

    private static SessionFactory buildSesionFactory() {
        try {
            Configuration config = new Configuration();
            config.configure();
            config.configure("hibernate.cfg.xml");
            config.setProperty("hibernate.connection.url", Conexao.URL);
            config.setProperty("hibernate.connection.username", Conexao.Usuario);
            config.setProperty("hibernate.connection.password", Conexao.SENHA);
            serviceRegistry = new ServiceRegistryBuilder()
                    .applySettings(config.getProperties()).buildServiceRegistry();
            sessionFactory = config.buildSessionFactory(serviceRegistry);
            return sessionFactory;
        } catch (HibernateException ex) {
            System.err.println("Initial SessionFactory creation failed:" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            System.out.println("TESTE SESSION 1");
            return buildSesionFactory();
        }
        try {
            System.out.println("TESTE SESSION 2");
            Session sessao = sessionFactory.openSession();
            sessao.createSQLQuery("Select 1").uniqueResult();
            sessao.close();
            return sessionFactory;
        } catch (HibernateException e) {
            System.out.println("TESTE SESSION 4");
            if (sessionFactory != null){
                sessionFactory.close();
            }
            return buildSesionFactory();
        }
    }

}

   /*     <!--property name="hibernate.hbm2ddl.auto">validate</property>
        <mapping class="Beans.PedidosAlmoxarifado"/>
        <mapping class="Beans.PedidoAlmoxarifadoItens"/>
        <mapping class="Beans.PedidosAlmoxarifadoSolicitacao"/>
        <mapping class="Beans.PedidosAlmoxarifadoCotacao"/>
        <mapping class="Beans.PedidosAlmoxarifadoFechamento"/>
        <mapping class="Beans.PedidosAlmoxarifadoFechamentoItens"/>
        <mapping class="Beans.PropriedadesBeans"/--> */

