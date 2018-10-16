/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.EntradaAlmoxarifadoBeans;
import static GUI.frmLogin.UsuarioLogado;
import Icones.FormatarICO;
import NFe.NFeProdutosBeans;
import TableModel.TableModelConsultaEntradaMercadoria;
import TableModel.TbResumoEntradaMercadoriaBeans;
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
public class EntradaAlmoxarifadoDAO {

    public Boolean salvarEntrada(EntradaAlmoxarifadoBeans beans) {
        Conexao.ReiniciarCon();
        String insert = "insert into entrada_almoxarifado (usuario, data, id_almoxarifado, id_operacao, id_fazenda, id_tipoPessoa , \n"
                + " n_doc, cnpj, id_fornecedor, nome_fantasia, razao_social, observacao) values (?,?,?,?,?,?,?,?,?,?,?,?)";

        String insertItens = "insert into entrada_almoxarifado_itens (id_entrada, n_doc_entrada, id_fornecedor, id_cadastro, codigoProduto, descricao, unidade, quantidade, valorUnit, id_localizador) values (?,?,?,?,?,?,?,?,?,?)";
        Integer LastID = 0;
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stItens = Conexao.getConnection().prepareStatement(insertItens);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(beans.getData()));
            st.setInt(3, beans.getID_Almoxarifado());
            st.setInt(4, beans.getID_Operacao());
            st.setInt(5, beans.getID_Fazenda());
            st.setInt(6, beans.getID_TipoPessoa());
            st.setInt(7, beans.getnDoc());
            st.setString(8, beans.getCNPJ());
            st.setInt(9, beans.getID_Fornecedor());
            st.setString(10, beans.getNomeFantasia());
            st.setString(11, beans.getRazaoSocial());
            st.setString(12, beans.getObservacao());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                LastID = rs.getInt(1);
            }

            List<NFeProdutosBeans> lista = beans.getProdutos();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getID_DB() == null || lista.get(i).getID_DB() == 0) {
                    inserirItem(stItens, LastID, lista.get(i), beans.getnDoc(), beans.getID_Fornecedor());
                }
            }
            stItens.executeBatch();
            rs.close();
            st.close();
            stItens.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Registro Inserido com Sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Inserir Registro!", "Erro", 0, FormatarICO.ICObtnSair());
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(CadAlmoxarifadaoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    public void consultarEntradas(String Where, TableModelConsultaEntradaMercadoria TbEntrada) {
        Conexao.ReiniciarCon();
        String select
                = "SELECT ea.id as ID, ea.data as Data, ea.id_operacao as Id_Operacao, ea.n_doc as nDoc, \n" 
                + "(CASE WHEN ea.id_operacao = 1 THEN cf.cad_fornecedor_id WHEN ea.id_operacao = 2 THEN cff.cad_fazendas_id WHEN ea.id_operacao > 2 THEN 0 END) as Id_Fornecedor,\n" 
                + "(CASE WHEN ea.id_operacao = 1 THEN cf.cad_fornecedor_nome WHEN ea.id_operacao = 2 THEN cff.cad_fazendas_nome WHEN ea.id_operacao > 2 THEN '-' END) as Fornecedor,\n" 
                + "(CASE WHEN ea.id_operacao = 1 THEN ea.cnpj WHEN ea.id_operacao = 2 THEN '019.899.109-63' WHEN ea.id_operacao > 2 THEN '-' END) as CNPJ, \n"
                + "    eai.id_cadastro as Id_Item,\n"
                + "    eai.codigoProduto as Codigo,\n"
                + "    (Select ca.descricao from cad_itens_almoxarif ca where ca.id = eai.id_cadastro) as Descricao,\n"
                + "    eai.quantidade as Quantidade,\n"
                + "    eai.valorUnit as ValorUnit,\n"
                + "    (Select cl.descricao from cad_almoxarifado_localizador cl Where cl.id = eai.id_localizador) as Localizador,\n"
                + "    eai.id_localizador as Id_Localizador\n"
                + "FROM entrada_almoxarifado ea \n"
                + " 	LEFT JOIN entrada_almoxarifado_itens eai ON eai.id_entrada = ea.id\n"
                + "	LEFT JOIN cad_fornecedor cf ON cf.cad_fornecedor_id = ea.id_fornecedor \n" 
                + "     LEFT JOIN cad_fazendas cff ON cff.cad_fazendas_id = ea.id_fazenda \n"
                + Where;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            ResultSet rs = st.executeQuery();
            List<TbResumoEntradaMercadoriaBeans> lista = new ArrayList<>();
            while (rs.next()) {
                TbResumoEntradaMercadoriaBeans beans = new TbResumoEntradaMercadoriaBeans();
                beans.setID_Entrada(rs.getInt(1));
                beans.setData(Corretores.ConverterParaJava(rs.getString(2)));
                beans.setOperacao(rs.getString(3));
                beans.setDocumentoEntrada(rs.getInt(4));
                beans.setID_Fornecedor(rs.getInt(5));
                beans.setFornecedor(rs.getString(6));
                beans.setCNPJ(rs.getString(7));
                beans.setID_Item(rs.getInt(8));
                beans.setCodigo(rs.getString(9));
                beans.setDescricao(rs.getString(10));
                beans.setQuantidade(rs.getDouble(11));
                beans.setValorUnit(rs.getDouble(12));
                beans.setLocalizador(rs.getString(13));
                beans.setID_Localizador(rs.getInt(14));
                lista.add(beans);
            }
            TbEntrada.limpar();
            TbEntrada.addLista(lista);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public EntradaAlmoxarifadoBeans getEntrada(Integer ID) {
        Conexao.ReiniciarCon();
        String selectEntrada = "SELECT  \n"
                + "	ea.id as ID,\n"
                + "    ea.data as Data,\n"
                + "    ea.id_almoxarifado as IdAlmoxarifado,\n"
                + "    ea.id_operacao as IdOperacao,\n"
                + "    ea.id_fazenda as IdFazenda,\n"
                + "    ea.id_tipoPessoa as IdTipoPessoa,\n"
                + "    ea.n_doc as nDoc,\n"
                + "    ea.cnpj as CNPJ,\n"
                + "    ea.id_fornecedor as IdFornecedor,\n"
                + "    ea.nome_fantasia as NomeFantasia,\n"
                + "    ea.razao_social as Razao,\n"
                + "    ea.observacao as Observacao\n"
                + "FROM entrada_almoxarifado ea \n"
                + "Where ea.id = ?;";
        String selectItens
                = "SELECT \n"
                + " eai.id as ID,\n"
                + " eai.id_cadastro as IdCadastro,"
                + " eai.codigoProduto as CodigoProduto,\n"
                + " eai.descricao as Descricao,\n"
                + " eai.unidade as Unidade,"
                + " eai.quantidade as Quantidade,\n"
                + " eai.valorUnit as ValorUnit,\n"
                + " (eai.quantidade * eai.valorUnit) as ValorTotal,"
                + " IFNULL((Select cl.descricao FROM cad_almoxarifado_localizador cl WHERE cl.id = eai.id_localizador), '-') as Localizador,\n"
                + " eai.id_localizador as IdLocalizador\n"
                + "FROM entrada_almoxarifado_itens eai \n"
                + "WHERE eai.id_entrada = ?";
        EntradaAlmoxarifadoBeans bean = new EntradaAlmoxarifadoBeans();
        try {
            PreparedStatement stEntrada = Conexao.getConnection().prepareStatement(selectEntrada);
            stEntrada.setInt(1, ID);
            PreparedStatement stItens = Conexao.getConnection().prepareStatement(selectItens);
            stItens.setInt(1, ID);
            ResultSet rsEntrada = stEntrada.executeQuery();
            while (rsEntrada.next()) {
                bean.setID(rsEntrada.getInt("ID"));
                bean.setData(Corretores.ConverterParaJava(rsEntrada.getString("Data")));
                bean.setID_Almoxarifado(rsEntrada.getInt("IdAlmoxarifado"));
                bean.setID_Operacao(rsEntrada.getInt("IdOperacao"));
                bean.setID_Fazenda(rsEntrada.getInt("IdFazenda"));
                bean.setID_TipoPessoa(rsEntrada.getInt("IdTipoPessoa"));
                bean.setnDoc(rsEntrada.getInt("nDoc"));
                bean.setCNPJ(rsEntrada.getString("CNPJ"));
                bean.setID_Fornecedor(rsEntrada.getInt("IdFornecedor"));
                bean.setNomeFantasia(rsEntrada.getString("NomeFantasia"));
                bean.setRazaoSocial(rsEntrada.getString("Razao"));
                bean.setObservacao(rsEntrada.getString("Observacao"));
            }
            ResultSet rsItens = stItens.executeQuery();
            List<NFeProdutosBeans> lista = new ArrayList<>();
            while (rsItens.next()) {
                NFeProdutosBeans produto = new NFeProdutosBeans();
                produto.setID_DB(rsItens.getInt("ID"));
                produto.setID_Cadastro(rsItens.getInt("IdCadastro"));
                produto.setcProd(rsItens.getString("CodigoProduto"));
                produto.setxProd(rsItens.getString("Descricao"));
                produto.setuCom(rsItens.getString("Unidade"));
                produto.setqCom(rsItens.getDouble("Quantidade"));
                produto.setvUnCom(rsItens.getDouble("ValorUnit"));
                produto.setvProd(rsItens.getDouble("ValorTotal"));
                produto.setLocalizador(rsItens.getString("Localizador"));
                produto.setId_localizador(rsItens.getInt("IdLocalizador"));
                lista.add(produto);
            }
            bean.setProdutos(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Consultar Entrada!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return bean;
    }

    private PreparedStatement inserirItem(PreparedStatement stItens, Integer LastID, NFeProdutosBeans produto, Integer nDoc, Integer IDFornecedor) throws SQLException {
        stItens.setInt(1, LastID);
        stItens.setInt(2, nDoc);
        stItens.setInt(3, IDFornecedor);
        stItens.setInt(4, produto.getID_Cadastro());
        stItens.setString(5, produto.getcProd());
        stItens.setString(6, produto.getxProd());
        stItens.setString(7, produto.getuCom());
        stItens.setDouble(8, produto.getqCom());
        stItens.setDouble(9, produto.getvUnCom());
        stItens.setInt(10, (produto.getId_localizador() == null ? 0 : produto.getId_localizador()));
        stItens.addBatch();
        return stItens;
    }

    public List<NFeProdutosBeans> consultaEntradaLocalizador(Integer Id_Almoxarifado, Integer Id_Cadastro) {
        Conexao.ReiniciarCon();
        String select = "SELECT eai.id_cadastro as IdCadastro,\n"
                + "eai.codigoProduto as CodigoProduto,\n"
                + "eai.descricao as Descricao,\n"
                + "eai.quantidade as Quantidade,\n"
                + "IFNULL((Select cl.descricao FROM cad_almoxarifado_localizador cl WHERE cl.id = eai.id_localizador), '-') as Localizador,\n"
                + "eai.id_localizador as IdLocalizador\n"
                + "FROM entrada_almoxarifado_itens eai \n"
                + "	JOIN entrada_almoxarifado ea ON ea.id = eai.id_entrada\n"
                + "WHERE ea.id_almoxarifado = ? and eai.id_cadastro = ?";
        List<NFeProdutosBeans> lista = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, Id_Almoxarifado);
            st.setInt(2, Id_Cadastro);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NFeProdutosBeans p = new NFeProdutosBeans();
                p.setID_Cadastro(rs.getInt("IdCadastro"));
                p.setcProd(rs.getString("CodigoProduto"));
                p.setxProd(rs.getString("Descricao"));
                p.setqCom(rs.getDouble("Quantidade"));
                p.setLocalizador(rs.getString("Localizador"));
                p.setId_localizador(rs.getInt("IdLocalizador"));
                lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Consultar Entradas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return lista;
    }

    public Boolean ExcluirEntrada(Integer IdEntrada) {
        Conexao.ReiniciarCon();
        String delEntrada = "DELETE From entrada_almoxarifado Where entrada_almoxarifado.id = ?";
        String delItens = "DELETE From entrada_almoxarifado_itens Where entrada_almoxarifado_itens.id_entrada = ?;";
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement stEntrada = Conexao.getConnection().prepareStatement(delEntrada);
            PreparedStatement stItens = Conexao.getConnection().prepareStatement(delItens);
            stEntrada.setInt(1, IdEntrada);
            stItens.setInt(1, IdEntrada);
            stEntrada.execute();
            stItens.execute();

            stItens.close();
            stEntrada.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Registro Excluído com Sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Excluir Registro!", "Erro", 0, FormatarICO.ICObtnSair());
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(CadAlmoxarifadaoDAO.class.getName()).log(Level.SEVERE, null, ex1);
                return false;
            }
            return false;
        }
        return true;
    }

    public List<NFeProdutosBeans> consultarLocalizadorEmLote(Integer Id_Almoxarifado, String Lote_ID) {
        Conexao.ReiniciarCon();
        String select
                = "SELECT Max(tb1.ID), tb1.IdCadastro, tb1.Localizador, tb1.IdLocalizador FROM (\n"
                + "	SELECT  eai.id as ID,\n"
                + "	eai.id_cadastro as IdCadastro,\n"
                + "	IFNULL((Select cl.descricao FROM cad_almoxarifado_localizador cl WHERE cl.id = eai.id_localizador), '-') as Localizador,\n"
                + "	eai.id_localizador as IdLocalizador\n"
                + "	FROM entrada_almoxarifado_itens eai \n"
                + "		JOIN entrada_almoxarifado ea ON ea.id = eai.id_entrada\n"
                + "	WHERE ea.id_almoxarifado = ? and eai.id_cadastro IN (" + Lote_ID + ")) tb1 \n"
                + "GROUP BY IdCadastro, Localizador, IdLocalizador;";
        List<NFeProdutosBeans> lista = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, Id_Almoxarifado);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                NFeProdutosBeans p = new NFeProdutosBeans();
                p.setID_Cadastro(rs.getInt("IdCadastro"));
                p.setLocalizador(rs.getString("Localizador"));
                p.setId_localizador(rs.getInt("IdLocalizador"));
                lista.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Consultar Localizadores!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return lista;
    }

    private PreparedStatement editarItem(PreparedStatement stItens, Integer LastID, NFeProdutosBeans produto, Integer nDoc, Integer IDFornecedor) throws SQLException {
        stItens.setInt(1, LastID);
        stItens.setInt(2, nDoc);
        stItens.setInt(3, IDFornecedor);
        stItens.setInt(4, produto.getID_Cadastro());
        stItens.setString(5, produto.getcProd());
        stItens.setString(6, produto.getxProd());
        stItens.setString(7, produto.getuCom());
        stItens.setDouble(8, produto.getqCom());
        stItens.setDouble(9, produto.getvUnCom());
        stItens.setInt(10, (produto.getId_localizador() == null ? 0 : produto.getId_localizador()));
        stItens.setInt(11, produto.getID_DB());
        stItens.addBatch();
        return stItens;
    }

    public Boolean EditarEntrada(EntradaAlmoxarifadoBeans beans) {
        Conexao.ReiniciarCon();
        String update = "UPDATE entrada_almoxarifado ea SET usuario = ?, data  = ?, id_almoxarifado = ?, id_operacao = ?, id_fazenda = ?, id_tipoPessoa = ?, n_doc = ?, \n"
                + " cnpj = ?, id_fornecedor = ?, nome_fantasia = ?, razao_social = ?, observacao  = ? WHERE ea.id = ?";

        String updateItens = "UPDATE entrada_almoxarifado_itens eai SET id_entrada = ?, n_doc_entrada = ?, id_fornecedor = ?, id_cadastro = ?, codigoProduto = ?,\n"
                + " descricao = ?, unidade = ?, quantidade = ?, valorUnit = ?, id_localizador = ? WHERE eai.id = ?";

        String insertItens = "insert into entrada_almoxarifado_itens (id_entrada, n_doc_entrada, id_fornecedor, id_cadastro, codigoProduto, descricao, unidade, quantidade, valorUnit, id_localizador) values (?,?,?,?,?,?,?,?,?,?)";
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(update);
            PreparedStatement stUpdateItens = Conexao.getConnection().prepareStatement(updateItens);
            PreparedStatement stInsertItens = Conexao.getConnection().prepareStatement(insertItens);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(beans.getData()));
            st.setInt(3, beans.getID_Almoxarifado());
            st.setInt(4, beans.getID_Operacao());
            st.setInt(5, beans.getID_Fazenda());
            st.setInt(6, beans.getID_TipoPessoa());
            st.setInt(7, beans.getnDoc());
            st.setString(8, beans.getCNPJ());
            st.setInt(9, beans.getID_Fornecedor());
            st.setString(10, beans.getNomeFantasia());
            st.setString(11, beans.getRazaoSocial());
            st.setString(12, beans.getObservacao());
            st.setInt(13, beans.getID());
            st.execute();
            List<NFeProdutosBeans> lista = beans.getProdutos();
            for (int i = 0; i < lista.size(); i++) {
                if (lista.get(i).getID_DB() == null || lista.get(i).getID_DB() == 0) {
                    inserirItem(stInsertItens, beans.getID(), lista.get(i), beans.getnDoc(), beans.getID_Fornecedor());
                } else {
                    editarItem(stUpdateItens, beans.getID(), lista.get(i), beans.getnDoc(), beans.getID_Fornecedor());
                }
            }
            stUpdateItens.executeBatch();
            stInsertItens.executeBatch();
            st.close();
            stUpdateItens.close();
            stInsertItens.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Registro Editado com Sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Editar Registro!", "Erro", 0, FormatarICO.ICObtnSair());
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(CadAlmoxarifadaoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

}
