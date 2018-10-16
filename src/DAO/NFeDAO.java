/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Icones.FormatarICO;
import NFe.NFeBeans;
import NFe.NFeProdutosBeans;
import NFe.TableModelNFe;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author agroa
 */
public class NFeDAO {

    Integer LastID;

    public void cadastrarNFe(NFeBeans nfe) {
        Conexao.ReiniciarCon();
        String insertNFe = "insert into nfe_xml (id_fornecedor, chNFe, cUF, cNF, natOp, nNF, dhEmi, cMunFG, CNPJ, xNome, xFant, xLgr, nro, xBairro, cMun, xMun, UF, IEDest, \n"
                + "vNF, nDup, dVenc, vDup, arq_xml) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        String insertProdutos = "insert into nfe_produtos (id_nfe, id_cadastro, cProd, xProd, NCM, CEST, uCom, qCom, vUnCom, vProd, vDesc, valorUnitFinal) values (?,?,?,?,?,?,?,?,?,?,?,?);";
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement stNFe = Conexao.getConnection().prepareStatement(insertNFe, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stProdutos = Conexao.getConnection().prepareStatement(insertProdutos);
            stInsertNFe(stNFe, nfe).execute();
            ResultSet rs = stNFe.getGeneratedKeys();
            while (rs.next()) {
                LastID = rs.getInt(1);
            }
            for (int i = 0; i < nfe.getListProdutos().size(); i++) {
                stInsertProdutos(stProdutos, nfe.getListProdutos().get(i));
            }
            stProdutos.executeBatch();
            stNFe.close();
            stProdutos.close();
            rs.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "NFe Cadastrada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(NFeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + " Erro ao Cadastrar NFe!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    private PreparedStatement stInsertProdutos(PreparedStatement stProdutos, NFeProdutosBeans produtos) throws SQLException {
        stProdutos.setInt(1, LastID);
        stProdutos.setInt(2, produtos.getID_Cadastro());
        stProdutos.setString(3, produtos.getcProd());
        stProdutos.setString(4, produtos.getxProd());
        stProdutos.setString(5, produtos.getNCM());
        stProdutos.setString(6, produtos.getCEST());
        stProdutos.setString(7, produtos.getuCom());
        stProdutos.setDouble(8, produtos.getqCom());
        stProdutos.setDouble(9, produtos.getvUnCom());
        stProdutos.setDouble(10, produtos.getvProd());
        stProdutos.setDouble(11, produtos.getvDesc());
        stProdutos.setDouble(12, produtos.getValorUnitFinal());
        stProdutos.addBatch();
        return stProdutos;
    }

    private PreparedStatement stInsertNFe(PreparedStatement stNFe, NFeBeans nfe) throws SQLException {
        stNFe.setInt(1, nfe.getID_Fornecedor());
        stNFe.setString(2, nfe.getChNFe());
        stNFe.setInt(3, nfe.getcUF());
        stNFe.setInt(4, nfe.getcNF());
        stNFe.setString(5, nfe.getNatOp());
        stNFe.setInt(6, nfe.getnNF());
        stNFe.setString(7, nfe.getDhEmi());
        stNFe.setInt(8, nfe.getcMunFG());
        stNFe.setString(9, nfe.getCNPJ());
        stNFe.setString(10, nfe.getxNome());
        stNFe.setString(11, nfe.getxFant());
        stNFe.setString(12, nfe.getxLgr());
        stNFe.setString(13, nfe.getNro());
        stNFe.setString(14, nfe.getxBairro());
        stNFe.setInt(15, nfe.getcMun());
        stNFe.setString(16, nfe.getxMun());
        stNFe.setString(17, nfe.getUF());
        stNFe.setString(18, nfe.getIEDest());
        stNFe.setDouble(19, nfe.getvNF());
        stNFe.setString(20, nfe.getnDup());
        stNFe.setString(21, nfe.getdVenc());
        stNFe.setDouble(22, nfe.getvDup());
        stNFe.setString(23, nfe.getCaminho());
        return stNFe;
    }

    public void ConsultarNFe(TableModelNFe TbNfe, String WHERE) {
        Conexao.ReiniciarCon();
        String select = "SELECT nf.id as ID, nf.id_fornecedor as ID_Forn, nf.nNF as nNF, nf.dhEmi as dhEmi,\n"
                + "nf.CNPJ as CNPJ, nf.xFant as xFant, nf.xNome, nf.IEDest as IEDest, nf.vNF as vNF\n"
                + "FROM nfe_xml nf " + WHERE;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            ResultSet rs = st.executeQuery();
            NFeBeans nfe;
            TbNfe.limpar();
            while (rs.next()) {
                nfe = new NFeBeans();
                nfe.setID(rs.getInt("ID"));
                nfe.setID_Fornecedor(rs.getInt("ID_Forn"));
                nfe.setnNF(rs.getInt("nNF"));
                nfe.setDhEmi(Corretores.ConverterParaJava(rs.getString("dhEmi")));
                nfe.setCNPJ(Corretores.ConverterParaCNPJ(rs.getString("CNPJ")));
                nfe.setxFant(rs.getString("xFant"));
                nfe.setxNome(rs.getString("xNome"));
                nfe.setIEDest(rs.getString("IEDest"));
                nfe.setvNF(rs.getDouble("vNF"));
                TbNfe.addBeans(nfe);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao Consultar NFe!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public List<NFeProdutosBeans> ConsultarProdutosNFe(Integer IdNFe) {
        Conexao.ReiniciarCon();
        List<NFeProdutosBeans> listaProdutos = new ArrayList<>();
        String select = "SELECT nfp.id_cadastro as IDCadastro, nfp.cProd as cProd, nfp.xProd as xProd, nfp.uCom as Unid, \n"
                + "nfp.qCom as Quant, nfp.vUnCom as vUnCom, nfp.vProd as vProd, nfp.vDesc as vDesc\n"
                + "FROM nfe_produtos nfp WHERE nfp.id_nfe = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, IdNFe);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NFeProdutosBeans produto = new NFeProdutosBeans();
                produto.setID_Cadastro(rs.getInt("IDCadastro"));
                produto.setcProd(rs.getString("cProd"));
                produto.setxProd(rs.getString("xProd"));
                produto.setuCom(rs.getString("Unid"));
                produto.setqCom(rs.getDouble("Quant"));
                produto.setvUnCom(rs.getDouble("vUnCom"));
                produto.setvProd(rs.getDouble("vProd"));
                produto.setvDesc(rs.getDouble("vDesc"));
                produto.setValorUnitFinal();
                listaProdutos.add(produto);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao Consultar NFe!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return listaProdutos;
    }

}
