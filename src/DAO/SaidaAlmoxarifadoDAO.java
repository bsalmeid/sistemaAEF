/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.SaidaAlmoxarifadoBeans;
import static GUI.frmLogin.UsuarioLogado;
import Icones.FormatarICO;
import TableModel.TableModelConsultaSaidaMercadoria;
import TableModel.TbResumoSaidaMercadoriaBeans;
import TableModel.TbSaidaAlmoxarifadoBeans;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class SaidaAlmoxarifadoDAO {

    String insert = "insert into saida_almoxarifado (usuario, data, id_almoxarifado, id_operacao, id_fazenda, n_doc, responsavel) values (?,?,?,?,?,?,?);";

    String insertItens = "insert into saida_almoxarifado_itens (id_saida, id_item, codigo, descricao, unidade, quantidade, id_aplicacao, aplicacao, \n"
            + "id_localizador, localizador, id_setor_servico, setor_servico, recebedor, observacao) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    String selectResumoSaidas = "SELECT sai.id_saida as ID_Saida, \n"
            + "sa.data as Data, \n"
            + "sa.id_operacao as Operacao, \n"
            + "sa.n_doc as NDoc,  \n"
            + "sai.id_item as IdItem,\n"
            + "IFNULL(sai.codigo, '-') as Codigo,\n"
            + "IFNULL(sai.descricao, '-') as Descricao,\n"
            + "sai.quantidade as Quantidade,\n"
            + "sai.id_setor_servico as IdSetor,\n"
            + "sai.id_aplicacao  as IdAplicacao,\n"
            + "sai.id_localizador as IdLocalizador,\n"
            + "IFNULL((select cs.descricao from cad_setor_servico cs Where cs.id = sai.id_setor_servico), '-') as Setor,\n"
            + "IFNULL((Select ci.n_frota from cad_inventario ci WHERE ci.id = sai.id_aplicacao), '-') as Aplicacao,\n"
            + "IFNULL((Select cl.descricao from cad_almoxarifado_localizador cl Where cl.id = sai.id_localizador) , '-') as Localizador\n"
            + "FROM saida_almoxarifado sa\n"
            + "		 JOIN saida_almoxarifado_itens sai ON sa.id = sai.id_saida ";

    String updateSaida = "UPDATE saida_almoxarifado SET usuario = ?, data = ?, id_almoxarifado  = ?, id_operacao  = ?, id_fazenda  = ?, n_doc  = ?, responsavel  = ?\n" +
                        "WHERE id = ?;";
    String updateItens = "UPDATE saida_almoxarifado_itens SET id_item = ?, codigo  = ?, descricao  = ?, unidade  = ?, quantidade  = ?, id_aplicacao  = ?,aplicacao  = ?,id_localizador  = ?,\n" +
                        "localizador  = ?,id_setor_servico  = ?,setor_servico  = ?,recebedor  = ?,observacao  = ? WHERE id = ?";

    public Boolean inserirSaida(SaidaAlmoxarifadoBeans beans) {
        Conexao.ReiniciarCon();
        Integer LastID = 0;
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stItens = Conexao.getConnection().prepareStatement(insertItens);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(beans.getData()));
            st.setInt(3, beans.getIdAlmoxarifado());
            st.setInt(4, beans.getIdOperacao());
            st.setInt(5, beans.getIdFazenda());
            st.setInt(6, beans.getNDoc());
            st.setString(7, beans.getResponsavel());
            st.execute();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                LastID = rs.getInt(1);
            }
            List<TbSaidaAlmoxarifadoBeans> lista = beans.getProdutos();
            for (TbSaidaAlmoxarifadoBeans produto : lista) {
                inserirProduto(stItens, LastID, produto);
            }
            stItens.executeBatch();
            st.close();
            rs.close();
            stItens.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Saída Registrada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SaidaInsumosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao salvar registro! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private PreparedStatement inserirProduto(PreparedStatement stItens, Integer LastID, TbSaidaAlmoxarifadoBeans produto) throws SQLException {
        stItens.setInt(1, LastID);
        stItens.setInt(2, produto.getIdItem());
        stItens.setString(3, produto.getCodigo());
        stItens.setString(4, produto.getDescricao());
        stItens.setString(5, produto.getUnidade());
        stItens.setDouble(6, produto.getQuantidade());
        stItens.setInt(7, produto.getIdAplicacao());
        stItens.setString(8, produto.getAplicacao());
        stItens.setInt(9, produto.getIdLocalizador());
        stItens.setString(10, produto.getLocalizador());
        stItens.setInt(11, produto.getIdSetorServico());
        stItens.setString(12, produto.getSetorServico());
        stItens.setString(13, produto.getRecebedor());
        stItens.setString(14, produto.getObservacao());
        stItens.addBatch();
        return stItens;
    }

    public void consultaSaida(TableModelConsultaSaidaMercadoria TbConsulta, String Where) {
        Conexao.ReiniciarCon();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(selectResumoSaidas + Where);
             System.out.println(selectResumoSaidas + Where);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TbResumoSaidaMercadoriaBeans b = new TbResumoSaidaMercadoriaBeans();
                b.setID_SAIDA(rs.getInt("ID_Saida"));
                b.setData(Corretores.ConverterParaJava(rs.getString("Data")));
                b.setOperacao(rs.getString("Operacao"));
                b.setNDoc(rs.getInt("NDoc"));
                b.setID_ITEM(rs.getInt("IdItem"));
                b.setCodigo(rs.getString("Codigo"));
                b.setDescricao(rs.getString("Descricao"));
                b.setQuantidade(rs.getDouble("Quantidade"));
                b.setIdSetor(rs.getInt("IdSetor"));
                b.setIdAplicacao(rs.getInt("IdAplicacao"));
                b.setIdLocalizador(rs.getInt("IdLocalizador"));
                b.setSetor(rs.getString("Setor"));
                b.setAplicacao(rs.getString("Aplicacao"));
                b.setLocalizador(rs.getString("Localizador"));
                TbConsulta.addBeans(b);
            }
            rs.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public SaidaAlmoxarifadoBeans getItem(Integer ID) {
        Conexao.ReiniciarCon();
        SaidaAlmoxarifadoBeans saida = new SaidaAlmoxarifadoBeans();
        List<TbSaidaAlmoxarifadoBeans> listaItens = new ArrayList<>();
        String select = "SELECT \n" + "sa.data as Data,\n" + "sa.id_almoxarifado as IdAlmoxarifado,\n"
                + "sa.id_operacao as IdOperacao,\n" + "sa.id_fazenda as IdFazenda,\n" + "sa.n_doc as Doc,\n" + "sa.responsavel as Responsavel\n"
                + " FROM saida_almoxarifado sa WHERE sa.id = ?";
        String selectItens = "SELECT id as ID,\n" + "id_item as IdItem,\n" + "codigo as Codigo,\n" + "descricao as Descricao,\n" + "unidade as Unidade,\n"
                + "quantidade as Quantidade,\n" + "id_aplicacao as IdAplicacao,\n" + "aplicacao as Aplicacao,\n" + "id_localizador as IdLocalizador,\n"
                + "localizador as Localizador,\n" + "id_setor_servico as IdSetor,\n" + "setor_servico as Setor,\n" + "recebedor as Recebedor,\n"
                + "observacao as  Observacao\n" + "FROM saida_almoxarifado_itens sai WHERE sai.id_saida = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            PreparedStatement stItens = Conexao.getConnection().prepareStatement(selectItens);
            st.setInt(1, ID);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                saida.setID(ID);
                saida.setData(Corretores.ConverterParaJava(rs.getString("Data")));
                saida.setIdAlmoxarifado(rs.getInt("IdAlmoxarifado"));
                saida.setIdOperacao(rs.getInt("IdOperacao"));
                saida.setIdFazenda(rs.getInt("IdFazenda"));
                saida.setNDoc(rs.getInt("Doc"));
                saida.setResponsavel(rs.getString("Responsavel"));
            }

            stItens.setInt(1, ID);
            ResultSet rsItens = stItens.executeQuery();
            while (rsItens.next()) {
                TbSaidaAlmoxarifadoBeans i = new TbSaidaAlmoxarifadoBeans();
                i.setID(rsItens.getInt("ID"));
                i.setIdItem(rsItens.getInt("IdItem"));
                i.setCodigo(rsItens.getString("Codigo"));
                i.setDescricao(rsItens.getString("Descricao"));
                i.setUnidade(rsItens.getString("Unidade"));
                i.setQuantidade(rsItens.getDouble("Quantidade"));
                i.setIdAplicacao(rsItens.getInt("IdAplicacao"));
                i.setAplicacao(rsItens.getString("Aplicacao"));
                i.setIdLocalizador(rsItens.getInt("IdLocalizador"));
                i.setLocalizador(rsItens.getString("Localizador"));
                i.setIdSetorServico(rsItens.getInt("IdSetor"));
                i.setSetorServico(rsItens.getString("Setor"));
                i.setRecebedor(rsItens.getString("Recebedor"));
                i.setObservacao(rsItens.getString("Observacao"));
                listaItens.add(i);
            }
            saida.setProdutos(listaItens);
            rs.close();
            rsItens.close();
            st.close();
            stItens.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return saida;
    }

    public Boolean editarSaida(SaidaAlmoxarifadoBeans beans) {
        Conexao.ReiniciarCon();
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(updateSaida);
            PreparedStatement stUpdateItens = Conexao.getConnection().prepareStatement(updateItens);
            PreparedStatement stInsertItens = Conexao.getConnection().prepareStatement(insertItens);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(beans.getData()));
            st.setInt(3, beans.getIdAlmoxarifado());
            st.setInt(4, beans.getIdOperacao());
            st.setInt(5, beans.getIdFazenda());
            st.setInt(6, beans.getNDoc());
            st.setString(7, beans.getResponsavel());
            st.setInt(8, beans.getID());
            st.execute();
            List<TbSaidaAlmoxarifadoBeans> lista = beans.getProdutos();
            for (TbSaidaAlmoxarifadoBeans produto : lista) {
                if (produto.getID() > 0) {
                    updateProduto(stUpdateItens, produto);
                } else {
                    inserirProduto(stInsertItens, beans.getID(), produto);
                }
            }
            stUpdateItens.executeBatch();
            stInsertItens.executeBatch();
            st.close();
            stUpdateItens.close();
            stInsertItens.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Saída Atualizada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SaidaInsumosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao Editra Registro! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private PreparedStatement updateProduto(PreparedStatement stUpdateItens, TbSaidaAlmoxarifadoBeans produto) throws SQLException {
        stUpdateItens.setInt(1, produto.getIdItem());
        stUpdateItens.setString(2, produto.getCodigo());
        stUpdateItens.setString(3, produto.getDescricao());
        stUpdateItens.setString(4, produto.getUnidade());
        stUpdateItens.setDouble(5, produto.getQuantidade());
        stUpdateItens.setInt(6, produto.getIdAplicacao());
        stUpdateItens.setString(7, produto.getAplicacao());
        stUpdateItens.setInt(8, produto.getIdLocalizador());
        stUpdateItens.setString(9, produto.getLocalizador());
        stUpdateItens.setInt(10, produto.getIdSetorServico());
        stUpdateItens.setString(11, produto.getSetorServico());
        stUpdateItens.setString(12, produto.getRecebedor());
        stUpdateItens.setString(13, produto.getObservacao());
        stUpdateItens.setInt(14, produto.getID());
        stUpdateItens.addBatch();
        return stUpdateItens;
    }

    public Boolean excluirItem(Integer ID){
        Conexao.ReiniciarCon();
        String deleteItem = "DELETE FROM saida_almoxarifado_itens WHERE id = ?;";
        Boolean resposta = false;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(deleteItem);
            st.setInt(1, ID);
            resposta = st.execute();
            JOptionPane.showMessageDialog(null, "Item Excluído com Sucesso!", "", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + "Erro ao Excluir Registro!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return resposta;
    }
    
    public Boolean excluirSaida(Integer ID){
        Conexao.ReiniciarCon();
        String deleteSaida = "DELETE FROM saida_almoxarifado WHERE id = ?;";
        String deleteItens = "DELETE FROM saida_almoxarifado_itens WHERE id_saida = ?;";
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement stSaida = Conexao.getConnection().prepareStatement(deleteSaida);
            PreparedStatement stItens = Conexao.getConnection().prepareStatement(deleteItens);
            stSaida.setInt(1, ID);
            stItens.setInt(1, ID);
            stItens.execute();
            stSaida.execute();
            stSaida.close();
            stItens.close();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Item Excluído com Sucesso!", "", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SaidaAlmoxarifadoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao Excluir Registro!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }
    
    public Integer getUltimoLocalizadorItem(Integer IdItem, Integer IdAlmoxarifado){
        Conexao.ReiniciarCon();
        String select = "SELECT eai.id as ID, eai.id_localizador as IdLocalizador FROM entrada_almoxarifado_itens eai \n" +
    "	JOIN entrada_almoxarifado ea ON ea.id = eai.id_entrada \n" +
    "	WHERE eai.id_cadastro = ? and ea.id_almoxarifado = ? order by ID desc Limit 1;";
        Integer IdLocalizador = 0;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, IdItem);
            st.setInt(2, IdAlmoxarifado);
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                IdLocalizador = rs.getInt("IdLocalizador");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar localizador!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return IdLocalizador;
    }
    
    
}
