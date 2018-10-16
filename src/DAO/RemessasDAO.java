package DAO;

import Beans.RemessaMercadoriaBeans;
import Beans.RemessaMercadoriaItens;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class RemessasDAO {

    public Boolean salvarRemessa(RemessaMercadoriaBeans remessa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.save(remessa);
            if (remessa.getStatus() == true && remessa.getListaObjectItens().isEmpty() == false) {
                Query atulizaStatusItem = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens I SET I.idStatusItem = :idStatus WHERE I.id IN :listaID");
                atulizaStatusItem.setParameterList("listaID", remessa.getListaObjectItens());
                atulizaStatusItem.setParameter("idStatus", 5);
                atulizaStatusItem.executeUpdate();
            }
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Remessa Nº " + remessa.getId() + " Inserido com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Salvar remessa!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editatRemessa(RemessaMercadoriaBeans remessa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long LastID = 0L;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.saveOrUpdate(remessa);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Remessa Nº " + remessa.getId() + " Editada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Salvar remessa!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean deletarRemessa(RemessaMercadoriaBeans remessa) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            if (remessa.getStatus() == true && remessa.getListaObjectItens().isEmpty() == false) {
                Query atulizaStatusItem = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens I SET I.idStatusItem = :idStatus WHERE I.id IN :listaID");
                atulizaStatusItem.setParameterList("listaID", remessa.getListaObjectItens());
                atulizaStatusItem.setParameter("idStatus", 3);
                atulizaStatusItem.executeUpdate();
            }
            sessao.delete(remessa);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Remessa Nº " + remessa.getId() + " Excluída com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Salvar remessa!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean deletarItem(RemessaMercadoriaItens item) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            if (item.getItemPedido() != null) {
                Query atulizaStatusItem = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens I SET I.idStatusItem = :idStatus WHERE I.id = :id");
                atulizaStatusItem.setParameter("id", item.getID());
                atulizaStatusItem.setParameter("idStatus", 3);
                atulizaStatusItem.executeUpdate();
            }
            sessao.delete(item);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "O Item " + item.getDescricao() + " Excluído com Sucesso!", "Excluído", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }
    
    public RemessaMercadoriaBeans getRemessa(Long ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        RemessaMercadoriaBeans bean = new RemessaMercadoriaBeans();
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query consulta = sessao.createQuery("SELECT R FROM RemessaMercadoriaBeans R LEFT JOIN FETCH R.listaItens WHERE R.id =:id");
            //consulta.setResultTransformer(new AliasToBeanResultTransformer(RemessaMercadoriaBeans.class));
            consulta.setParameter("id", ID);
            bean = (RemessaMercadoriaBeans) consulta.uniqueResult();
            for (RemessaMercadoriaItens item : bean.getListaItens()) {
                if (item.getItemPedido() != null) {
                    item.getItemPedido().getId();
                }
            }
            transacao.commit();
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Remessa!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return bean;
    }

    public List<RemessaMercadoriaItens> consultarRemessa(String SQLWhere) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<RemessaMercadoriaItens> lista = new ArrayList();
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query consulta = sessao.createQuery("SELECT I FROM RemessaMercadoriaItens I LEFT JOIN I.remessa AS R " + SQLWhere);
            lista = consulta.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Remessa!", "Erro", 0, FormatarICO.ICObtnSair());
            return lista;
        } finally {
            sessao.close();
        }
        return lista;
    }

    private void rollBack(Transaction transacao) {
        if (transacao != null) {
            transacao.rollback(); // desfaz a transacao
        }
    }

}
