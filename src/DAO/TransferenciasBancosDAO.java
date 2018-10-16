/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.TransferenciasBancosBeans;
import Icones.FormatarICO;
import TableModel.TableModelTransfBancos;
import Utilitarios.Conexao;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author agroa
 */
public class TransferenciasBancosDAO {

    public boolean inserirMovimento(TransferenciasBancosBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.save(beans);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Transferência Realizada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public  List<TransferenciasBancosBeans> resumoTransferencias(String SQLWhere) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<TransferenciasBancosBeans> listaT = new ArrayList<>();
        try {
            transacao = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT T From TransferenciasBancosBeans T JOIN FETCH T.idContaOrigem JOIN FETCH T.idContaDestino " + SQLWhere);
            listaT = consulta.list();
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return listaT;
        } finally {
            sessao.close();
        }
        return listaT;

    }

    public TransferenciasBancosBeans buscarTransferencias(Integer ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        TransferenciasBancosBeans t = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query consulta = sessao.createQuery("From TransferenciasBancosBeans T JOIN FETCH T.idContaOrigem JOIN FETCH T.idContaDestino Where T.ID = :id");
            consulta.setParameter("id", ID);
            t = (TransferenciasBancosBeans) consulta.uniqueResult();
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return t;
        } finally {
            sessao.close();
        }
        return t;
    }

    public boolean editarTransferencia(TransferenciasBancosBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.update(beans);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Transferência Editada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public boolean excluirTransferencia(TransferenciasBancosBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(beans);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Transferência Excluída com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    private void rollBack(Transaction transacao) {
        if (transacao != null) {
            transacao.rollback();
        }
    }

}
