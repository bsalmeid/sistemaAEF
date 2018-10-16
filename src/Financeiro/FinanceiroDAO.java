
package Financeiro;

import Beans.BancosBeans;
import Beans.CompradorGadoBeans;
import Beans.ContaBancariaBeans;
import Beans.GrupoContasBeans;
import Beans.MoedaBeans;
import Beans.PlanoContaBeans;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FinanceiroDAO {
    
    private Session sessao;
    private Transaction transaction;

    public FinanceiroDAO() {}
    
    public Boolean salvarBanco(BancosBeans banco) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(banco);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Banco Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Banco!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean atualizarBanco(BancosBeans banco) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(banco);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Banco atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Banco!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean salvarCompradorGado(CompradorGadoBeans comprador) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(comprador);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Comprador de Gado Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Comprador de Gado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean atualizarCompradorGado(CompradorGadoBeans comprador) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(comprador);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Comprador de Gado atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Comprador de Gado!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean salvarContaBancria(ContaBancariaBeans conta) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(conta);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Conta Bancária Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Conta Bancária!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean atualizarContaBancaria(ContaBancariaBeans conta) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(conta);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Conta Bancária atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Conta Bancária!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean salvarGrupoContas(GrupoContasBeans grupo) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(grupo);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Grupo de Contas Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Grupo de Contas!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean atualizarGrupoContas(GrupoContasBeans grupo) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(grupo);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Grupo de Contas atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Grupo de Contas!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean salvarMoeda(MoedaBeans moeda) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(moeda);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Moeda Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Moeda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean atualizarMoeda(MoedaBeans moeda) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(moeda);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Moeda atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Moeda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean salvarPlanoConta(PlanoContaBeans plano) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(plano);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Plano de Conta Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Plano de Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public Boolean atualizarPlanoConta(PlanoContaBeans plano) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(plano);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Plano de Conta atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Plano de Conta!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

   
}
