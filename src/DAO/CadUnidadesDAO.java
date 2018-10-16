package DAO;

import Beans.CadUnidadesBeans;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CadUnidadesDAO {
//    private SessionFactory sessionFactory;
//    private StatelessSession stateless;
    private Session sessao;
    private Transaction transaction;


    public Boolean salvarUnidades(CadUnidadesBeans unidades) {
        sessao = HibernateUtil.getSessionFactory().openSession();

        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(unidades);
            transaction.commit();
            JOptionPane.showMessageDialog(null, " Salvo Com Sucesso!", "Sucesso!", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Unidade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.clear();
        }
        return true;
    }

    public Boolean editarUnidades(CadUnidadesBeans unidades) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(unidades);
            transaction.commit();
            JOptionPane.showMessageDialog(null, " Alterado Com Sucesso!", "Sucesso!", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Alterar Unidade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public List<CadUnidadesBeans> listarUnidades() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<CadUnidadesBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT U FROM CadUnidadesBeans U ORDER BY U.Descricao");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public CadUnidadesBeans buscarUnidade(Integer ID) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        CadUnidadesBeans Unidades = new CadUnidadesBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT U FROM CadUnidadesBeans U WHERE U.ID = :ID");
            consulta.setParameter("ID", ID);
            Unidades = (CadUnidadesBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return Unidades;
    }

}
