
package DAO;

import Beans.PropriedadesBeans;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PropriedadesDAO {

    public Boolean cadastrarFazendas(PropriedadesBeans fazendas) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.save(fazendas);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Fazenda Cadastrada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback();
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public List<PropriedadesBeans> consultarFazendas() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<PropriedadesBeans> listaFazendas = new ArrayList<>();
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            listaFazendas = sessao.createQuery("From PropriedadesBeans").list();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback();
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fazendas!", "Erro", 0, FormatarICO.ICObtnSair());
            return listaFazendas;
        } finally {
            sessao.close();
        }
        return listaFazendas;

    }

    public PropriedadesBeans buscarFazendas(int Codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        PropriedadesBeans fazenda = new PropriedadesBeans();
        try {
            transacao = sessao.beginTransaction();
            Query query = sessao.createQuery("From PropriedadesBeans f LEFT JOIN FETCH f.listaTalhao WHERE f.Codigo = :codigo");
            query.setParameter("codigo", Codigo);
            fazenda = (PropriedadesBeans) query.uniqueResult();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback();
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return fazenda;
        } finally {
            sessao.close();
        }
        return fazenda;
    }

    public Boolean editarFazendas(PropriedadesBeans fazendas) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.saveOrUpdate(fazendas);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Fazenda Cadastrada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback();
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Fazenda!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Integer buscarUltimo(Integer Ultimo) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select max(cad_fazendas_id) From cad_fazendas";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Ultimo = rs.getInt(1) + 1;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        }
        return Ultimo;
    }

}
