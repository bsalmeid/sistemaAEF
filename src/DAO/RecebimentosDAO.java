/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.RecebimentosBeans;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RecebimentosDAO {

    public Boolean inserirRecebimento(RecebimentosBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(beans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Conta à Receber Registrado Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            rollBack(transaction);
            JOptionPane.showMessageDialog(null, e + " Erro ao Registrar Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editarRecebimento(RecebimentosBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(beans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Conta à Receber Editado Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            rollBack(transaction);
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public RecebimentosBeans getRecebimento(Integer ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        RecebimentosBeans rec = new RecebimentosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("FROM RecebimentosBeans R LEFT JOIN FETCH R.idCliente WHERE R.ID = :id");
            consulta.setParameter("id", ID);
            rec = (RecebimentosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            rollBack(transaction);
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Recebivel!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return rec;
    }

    public List<RecebimentosBeans> consultaRecebimentos(String Where) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<RecebimentosBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("FROM RecebimentosBeans R LEFT JOIN FETCH R.CentroResultado " + Where + " ORDER BY R.DtRecebimentos");
            lista = consulta.list();
        } catch (HibernateException e) {
            rollBack(transaction);
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Recebivel!", "Erro", 0, FormatarICO.ICObtnSair());
            return lista;
        } finally {
            sessao.close();
        }
        return lista;
    }

    private void rollBack(Transaction transaction) {
        if (transaction != null) {
            transaction.rollback();
        }
    }

}
