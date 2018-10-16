/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.AnoSafra;
import Beans.AtividadeBeans;
import Beans.CentroDeResultado;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CentroResultadoDAO {

    private Session sessao;
    private Transaction transaction;

    public CentroResultadoDAO() {
    }

    public Boolean salvarCentroResultado(CentroDeResultado resultado) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(resultado);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Centro de Resultado Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarCentroResultado(CentroDeResultado resultado) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(resultado);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Centro de Resultado Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean removerCentroResultado(CentroDeResultado resultado) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(resultado);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Centro de Resultado Deletado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Deletar Centro de Resultado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarAnoSafa(AnoSafra safra) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(safra);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Ano da Safra Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Ano da Safra!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarAnoSafra(AnoSafra safra) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(safra);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Ano da Safra Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Ano da Safra!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarAtividade(AtividadeBeans atividade) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(atividade);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Atividade Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Atividade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarAtividade(AtividadeBeans atividade) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(atividade);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Atividade Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Atividade!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarCultivo(CultivoBeans cultivo) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(cultivo);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Cultivo Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Cultivo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarCultivo(CultivoBeans cultivo) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(cultivo);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Cultivo Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Cultivo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarCultura(CulturaBeans cultura) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(cultura);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Cultura Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Cultura!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarCultura(CulturaBeans cultura) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(cultura);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Cultura Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Cultura!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

      public List<AnoSafra> listarAnoSafra() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<AnoSafra> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT A FROM AnoSafra A ORDER BY A.AnoSafra DESC");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao listar Ano da Safra!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }//FrmCadCentroResultado

    public List<CultivoBeans> listarCultivo() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<CultivoBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT C FROM CultivoBeans C ORDER BY C.Cultivo");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao listar Cultivo!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }//FrmCadCentroResultado

    public List<CulturaBeans> listarCultura() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<CulturaBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT C FROM CulturaBeans C ORDER BY C.NomeCultura");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao listar Cultura!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }//FrmCadCentroResultado
    
}
