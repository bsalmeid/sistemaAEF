/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.AnoSafra;
import Beans.FornecedoresBeans;
import Beans.ListSegFornecedores;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class FornecedoresDAO {

    public List<ListSegFornecedores> SegmentoFornecedor() {
        Conexao.ReiniciarCon();
        List<ListSegFornecedores> lista = new ArrayList<>();
        String SQLSelection = "SELECT segmento_id as ID, segmento_nome as Nome from cad_segmento_fornecedor";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ListSegFornecedores l = new ListSegFornecedores();
                l.setId_segmento(rs.getInt("ID"));
                l.setNome(rs.getString("Nome"));
                lista.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao bucar segmento!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return lista;
    }

    public void cadastrarFornecedor(FornecedoresBeans forn) {
        Conexao.ReiniciarCon();

        String SQLInsert = "insert into cad_fornecedor (cad_fornecedor_idseg, cad_fornecedor_nome, cad_fornecedor_razao,"
                + "cad_fornecedor_telefone, cad_fornecedor_email, cad_fornecedor_tipoPessoa, cad_fornecedor_cnpj,"
                + "cad_fornecedor_banco, cad_fornecedor_agencia, cad_fornecedor_conta) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsert);
            st.setInt(1, forn.getIdSegmento());
            st.setString(2, forn.getNomeFantasia());
            st.setString(3, forn.getRazaoSocial());
            st.setString(4, forn.getTelefone());
            st.setString(5, forn.getEmail());
            st.setString(6, forn.getTipoPessoa());
            st.setString(7, forn.getCnpj());
            st.setString(8, forn.getBanco());
            st.setString(9, forn.getAgencia());
            st.setString(10, forn.getConta());
            st.execute();
            st.close();
            JOptionPane.showMessageDialog(null, "Fornecedor Cadastrado com Sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao cadastrar fornecedor", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void editarFornecedor(FornecedoresBeans forn) {
        Conexao.ReiniciarCon();
        String SQLUpdate = "update cad_fornecedor SET cad_fornecedor_idseg = ?, cad_fornecedor_nome = ?, cad_fornecedor_razao = ?, cad_fornecedor_telefone = ?,"
                + "cad_fornecedor_email = ?, cad_fornecedor_tipoPessoa = ?, cad_fornecedor_cnpj = ?, cad_fornecedor_banco = ?,"
                + "cad_fornecedor_agencia = ?, cad_fornecedor_conta = ? Where cad_fornecedor_id = ?";

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            st.setInt(1, forn.getIdSegmento());
            st.setString(2, forn.getNomeFantasia());
            st.setString(3, forn.getRazaoSocial());
            st.setString(4, forn.getTelefone());
            st.setString(5, forn.getEmail());
            st.setString(6, forn.getTipoPessoa());
            st.setString(7, forn.getCnpj());
            st.setString(8, forn.getBanco());
            st.setString(9, forn.getAgencia());
            st.setString(10, forn.getConta());
            st.setInt(11, forn.getID());
            st.executeUpdate();
            st.close();
            JOptionPane.showMessageDialog(null, "Fornecedor Editado com Sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao editar fornecedor!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void buscarFornecedores(FornecedoresBeans forn, DefaultTableModel TbForn) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_fornecedor_id as ID, cad_fornecedor_idseg as idSeg, cad_fornecedor_nome as NomeFant, cad_fornecedor_razao as Razao,cad_fornecedor_telefone as Telefone,"
                + "cad_fornecedor_email as email, cad_fornecedor_tipoPessoa as tipoPessoa, cad_fornecedor_cnpj as cnpj, cad_fornecedor_banco as banco,"
                + "cad_fornecedor_agencia as Agencia, cad_fornecedor_conta as conta"
                + " from cad_fornecedor Where cad_fornecedor_nome like '" + forn.getNomeFantasia() + "%' and cad_fornecedor_razao like '" + forn.getRazaoSocial() + "%' and cad_fornecedor_cnpj like '" + forn.getCnpj() + "%'";

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbForn.setNumRows(0);
            while (rs.next()) {
                TbForn.addRow(new Object[]{rs.getInt("ID"), rs.getString("NomeFant"), rs.getString("Razao"), rs.getString("Telefone"),
                    rs.getString("email"), rs.getInt("idSeg"), rs.getString("tipoPessoa"), rs.getString("cnpj"), rs.getString("banco"),
                    rs.getString("Agencia"), rs.getString("conta")});
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao realizar consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public FornecedoresBeans getFornecedor(String CNPJ) {
        Conexao.ReiniciarCon();
        String select = "select cf.cad_fornecedor_id as ID, cf.cad_fornecedor_nome as NomeFant, cf.cad_fornecedor_razao as Razao From cad_fornecedor cf Where cf.cad_fornecedor_cnpj = ?;";
        FornecedoresBeans fornB = new FornecedoresBeans();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setString(1, CNPJ);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                fornB.setID(rs.getInt("ID"));
                fornB.setNomeFantasia(rs.getString("NomeFant"));
                fornB.setRazaoSocial(rs.getString("Razao"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao realizar consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return fornB;
    }

    public List<FornecedoresBeans> consultaFornecedoresLote(String CPFs) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<FornecedoresBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT F FROM FornecedoresBeans F WHERE F.cnpj IN " + CPFs);
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fornecedores!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public List<FornecedoresBeans> consultaFornecedores(String Where) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<FornecedoresBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT F FROM FornecedoresBeans F " + Where);
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fornecedores!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public FornecedoresBeans getFornecedor(Integer ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        FornecedoresBeans f = new FornecedoresBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT F FROM FornecedoresBeans F WHERE F.ID =:id");
            consulta.setParameter("id", ID);
            f = (FornecedoresBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fornecedores!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return f;
    }

    public Boolean cadastrarFornecedorHIbernate(FornecedoresBeans f) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<FornecedoresBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            sessao.save(f);
            transaction.commit();
        } catch (HibernateException e) {
            rollBack(transaction);
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fornecedores!", "Erro", 0, FormatarICO.ICObtnSair());
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
