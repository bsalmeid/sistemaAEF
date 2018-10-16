package DAO;

import Beans.FornecedoresBeans;
import Icones.FormatarICO;
import Beans.PagamentosBeans;
import Beans.PagtoClassBeans;
import Beans.PagtoNFBeans;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PagamentosDAO {

    String resumoPagamentos = "Select new PagamentosBeans (\n"
            + "ID,\n"
            + "(Select C From ContaBancariaBeans C WHERE C.IdConta = pg.IDContaOrigem), \n"
            + "DtPrevista,\n" + "DtRealizado,\n" + "Moeda,\n" + "ValorEmMoeda,\n" + "TaxaConverte,\n"
            + "ValorPagamento,\n" + "ValorCompra,\n" + "FormaPagto,\n" + "nDocumento,\n" + "titular,\n"
            + "cpf,\n" + "status,\n"
            + "(Select SUM(clas.ValorClas) From PagtoClassBeans clas WHERE clas.Pagto = pg.ID ) as ValorClas,  \n"
            + "(Select SUM(nf.Valor) From PagtoNFBeans nf WHERE nf.Pagto = pg.ID ) as ValorNF)  \n"
            + "FROM PagamentosBeans pg ";

    public FornecedoresBeans buscarFornecedor(String cnpj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        FornecedoresBeans fornecedor = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From FornecedoresBeans f Where f.cnpj = :cnpj ");
            query.setParameter("cnpj", cnpj);
            fornecedor = (FornecedoresBeans) query.uniqueResult();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback();
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return fornecedor;
    }

    public Boolean salvarPagto(PagamentosBeans pg) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.save(pg);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Ordem de Pagamento Registrada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarPagtoEmLote(PagamentosBeans pg) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.save(pg);
            transacao.commit();
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editarPagto(PagamentosBeans pg) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.saveOrUpdate(pg);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Ordem de Pagamento Registrada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public PagamentosBeans getPagamento(Long id) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao;
        PagamentosBeans pg = null;
        List<PagtoNFBeans> listaNF;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From PagamentosBeans pg LEFT JOIN FETCH pg.listaClassificacao Where pg.ID = :id ");
            query.setParameter("id", id);
            pg = (PagamentosBeans) query.uniqueResult();
            pg.setnCompra(pg.getnCompra());
            Query queryNF = sessao.createQuery("From PagtoNFBeans nf Where nf.Pagto.ID = :id");
            queryNF.setParameter("id", id);
            listaNF = queryNF.list();
            pg.setListaNotaFiscal(listaNF);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Pagamento!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return pg;
    }

    public Boolean DeletarPagto(PagamentosBeans pg) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(pg);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Ordem de Pagamento Excluída com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Ordem!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean DeletarNF(PagtoNFBeans nf) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(nf);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Documento Fiscal Excluído com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Documento Fiscal!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean DeletarClassicacao(PagtoClassBeans clas) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(clas);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Classificação Excluída com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
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

    public List<PagamentosBeans> resumoPagamentos(String Where) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao;
        List<PagamentosBeans> lista = new ArrayList<>();
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            lista = sessao.createQuery(resumoPagamentos + Where).list();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Pagamento!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

}
