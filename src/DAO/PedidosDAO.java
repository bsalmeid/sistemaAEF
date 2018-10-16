/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.EntradaInsumosBeans;
import Beans.PagtoDiversosBeans;
import Beans.PedidosBeans;
import Icones.FormatarICO;
import TableModel.TableModelRelInsumos;
import TableModel.TableModelRelPedidos;
import TableModel.TableModelResPagto;
import TableModel.TableModelTbInsumos;
import TableModel.TbPedidosInsumosBeans;
import TableModel.TbRelPedidosInsumosBeans;
import TableModel.TbRelatorioInsumosBeans;
import TableModel.TbResPagamentosBeans;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.math.BigDecimal;
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
 * @author Bruno
 */
public class PedidosDAO {
    

    public Integer inserirPedido(PedidosBeans pedido, TableModelTbInsumos TbInsumos){
        Conexao.ReiniciarCon();
        String InsertPedido = "insert into pedidos(pedidos_nPedido, pedidos_safra,pedidos_emissao," +
        "pedidos_codFornecedor, pedidos_fornecedor, " +
        "pedidos_codFazenda, pedidos_fazenda, pedidos_moeda, pedidos_valorTotal, pedidos_vencto, pedidos_obs) values (?,?,?,?,?,?,?,?,?,?,?);";

        String InsertInsumos = "insert into pedidos_insumos (insumos_status, insumos_idPedido, insumos_npedido, insumos_idFazenda, " +
        "insumos_fazenda, insumos_idCategoria, insumos_nomeCat, insumos_idInsumo, insumos_Insumo, insumos_unid, " +
        "insumos_quant, insumos_sifra, insumos_valorUnit, insumos_valorTotal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
        
        int idPedido = 0;        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(InsertPedido, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(InsertInsumos);
            Conexao.getConnection().setAutoCommit(false);
            st.setString(1, pedido.getnPedido());
            st.setString(2, pedido.getSafra());
            st.setString(3, Corretores.ConverterParaSQL(pedido.getDtEmissao()));
            st.setInt(4, pedido.getIdFornecedor());
            st.setString(5, pedido.getFornecedor());
            st.setInt(6, pedido.getIdFazenda());
            st.setString(7, pedido.getFazenda());
            st.setString(8, pedido.getMoeda());
            st.setBigDecimal(9, pedido.getValorTotal());
            st.setString(10, Corretores.ConverterParaSQL(pedido.getDtVencimento()));
            st.setString(11, pedido.getObservacao());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            
            while (rs.next()){ idPedido = rs.getInt(1);}
            
            for (int i = 0; i < TbInsumos.getRowCount(); i++){
                stInsumos.setBoolean(1, Boolean.parseBoolean(TbInsumos.getValueAt(i, 1).toString())); //Status
                stInsumos.setInt(2, idPedido); //ID pedido
                stInsumos.setString(3, pedido.getnPedido()); // n pedido
                stInsumos.setInt(4, Integer.parseInt(TbInsumos.getValueAt(i, 4).toString())); // id fazenda
                stInsumos.setString(5, TbInsumos.getValueAt(i, 5).toString()); // nome fazenda
                stInsumos.setInt(6, Integer.parseInt(TbInsumos.getValueAt(i, 6).toString())); // id categoria
                stInsumos.setString(7, TbInsumos.getValueAt(i, 7).toString()); // nome categoria
                stInsumos.setInt(8, Integer.parseInt(TbInsumos.getValueAt(i, 8).toString())); // id insummo
                stInsumos.setString(9, TbInsumos.getValueAt(i, 9).toString()); // insumo
                stInsumos.setString(10, TbInsumos.getValueAt(i, 10).toString()); //unidade
                stInsumos.setDouble(11, Double.parseDouble(TbInsumos.getValueAt(i, 11).toString())); //quantidade
                stInsumos.setString(12, TbInsumos.getValueAt(i, 12).toString()); // sifra
                stInsumos.setBigDecimal(13, new BigDecimal(TbInsumos.getValueAt(i, 13).toString())); // valor unitario
                stInsumos.setBigDecimal(14, new BigDecimal(TbInsumos.getValueAt(i, 14).toString())); // valor total
                stInsumos.addBatch();
            }
            stInsumos.executeBatch();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null,"Pedido Inserido com Sucesso!", "Registro Salvo", 0, FormatarICO.ICObtnOk()); 
            stInsumos.close();
            st.close();
            rs.close();
            
        } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex+ " Erro ao Inserir Pedido", "Erro", 0, FormatarICO.ICObtnSair()); 
            try {
                Conexao.getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
        }
        return idPedido;
    }
    
    public void buscarInsumos(PedidosBeans pedido, TableModelTbInsumos TbInsumos){
        Conexao.ReiniciarCon();
        String SQLSelection = "select  " +
        "insumos_id as ID, insumos_status as Status, insumos_idPedido as IdPedido, " +
        "insumos_npedido as nPedido, insumos_idFazenda as idFazenda, insumos_fazenda as Fazenda, " +
        "insumos_idCategoria as idCategoria, insumos_nomeCat as Categoria, insumos_idInsumo as idInsumo, " +
        "insumos_Insumo as Insumo, insumos_unid as Unidade, insumos_quant as Quant, " +
        "insumos_sifra as Sifra, insumos_valorUnit as VlrUnit, insumos_valorTotal as VlrTotal, " +
        "@QtEntregue:= ifnull((SELECT sum(ent.ent_ins_quant) from ent_insumo ent where ent.idItemPedido = pi.insumos_id),0) as QtEntregue,  " +
        "insumos_quant - @QtEntregue as Saldo " +
        "from pedidos_insumos pi, pedidos p  " +
        "where pi.insumos_idPedido = p.pedidos_id " + pedido.getSQLnPedido() + pedido.getSQLFornecedor() +
                pedido.getSQLInsumo() + pedido.getSQLSifra() + pedido.getSQLIdCategoria() + pedido.getSQLIdFazenda();
        
        try {
            List<TbPedidosInsumosBeans> ListaInsumos = new ArrayList<>();
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbInsumos.limpar();
            while (rs.next()){
                    TbPedidosInsumosBeans l = new TbPedidosInsumosBeans();
                    l.setId(rs.getInt("ID"));
                    l.setStatus(rs.getBoolean("Status"));
                    l.setIdPedido(rs.getInt("IdPedido"));
                    l.setnPedido(rs.getString("nPedido"));
                    l.setIdFazenda(rs.getInt("idFazenda"));
                    l.setFazenda(rs.getString("Fazenda"));
                    l.setIdCategoria(rs.getInt("idCategoria"));
                    l.setCategoria(rs.getString("Categoria"));
                    l.setIdInsumo(rs.getInt("idInsumo"));
                    l.setInsumo(rs.getString("Insumo"));
                    l.setUnidade(rs.getString("Unidade"));
                    l.setQuantidade(rs.getDouble("Quant"));
                    l.setSifra(rs.getString("Sifra"));
                    l.setValorUnit(rs.getBigDecimal("VlrUnit"));
                    l.setValorTotal(rs.getBigDecimal("VlrTotal"));
                    l.setQtEntregue(rs.getDouble("QtEntregue"));
                    l.setSaldoEntregar(rs.getDouble("Saldo"));
                    ListaInsumos.add(l);
                }
          TbInsumos.addLista(ListaInsumos);
          
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " Erro ao buscar insumos!", "Erro", 0, FormatarICO.ICObtnSair()); 
        }
        
    }
 
    public void buscarPedido(PedidosBeans pedido , TableModelTbInsumos TbInsumos){
        Conexao.ReiniciarCon();
        String SQLSelectPedidos = "select pedidos_id as ID, " +
            "pedidos_nPedido as nPedido, " +
            "pedidos_safra as Safra, " +
            "pedidos_emissao as Emissao, " +
            "pedidos_codFornecedor as idFornecedor, " +
            "pedidos_fornecedor as Fornecedor, " +
            "pedidos_codFazenda as idFazenda, " +
            "pedidos_fazenda as Fazenda, " +
            "pedidos_moeda as Moeda, " +
            "pedidos_valorTotal as VlrTotal, " +
            "pedidos_vencto as dtVencto, " +
            "pedidos_obs as Observacao, " +    
            "0 as VlrPago, " +
            "0 as VlrNF " +
            "from pedidos where pedidos_id = " + pedido.getID();
        String SQLSelecInsumos = "select " +
            "insumos_id as ID, insumos_status as Status, insumos_idPedido as IdPedido, " +
            "insumos_npedido as nPedido, insumos_idFazenda as idFazenda, insumos_fazenda as Fazenda, " +
            "insumos_idCategoria as idCategoria, insumos_nomeCat as Categoria, insumos_idInsumo as idInsumo, " +
            "insumos_Insumo as Insumo, insumos_unid as Unidade, insumos_quant as Quant, " +
            "insumos_sifra as Sifra, insumos_valorUnit as VlrUnit, insumos_valorTotal as VlrTotal, " +
            "@QtEntregue:=0 as QtEntregue, " +
            "insumos_quant - @QtEntregue as Saldo " +
            "from pedidos_insumos pi " +
            "where pi.insumos_idPedido = " + pedido.getID();
        
        try {
            List<TbPedidosInsumosBeans> ListaInsumos = new ArrayList<>();
            PreparedStatement stPed = Conexao.getConnection().prepareStatement(SQLSelectPedidos);
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelecInsumos);
            ResultSet rsPed = stPed.executeQuery();
           
            while(rsPed.next()){
                pedido.setID(rsPed.getInt("ID"));
                pedido.setnPedido(rsPed.getString("nPedido"));
                pedido.setSafra(rsPed.getString("Safra"));
                pedido.setDtEmissao(Corretores.ConverterParaJava(rsPed.getString("Emissao")));
                pedido.setIdFornecedor(rsPed.getInt("idFornecedor"));
                pedido.setFornecedor(rsPed.getString("Fornecedor"));
                pedido.setIdFazenda(rsPed.getInt("idFazenda"));
                pedido.setMoeda(rsPed.getString("Moeda"));
                pedido.setValorTotal(rsPed.getBigDecimal("VlrTotal"));
                pedido.setDtVencimento(Corretores.ConverterParaJava(rsPed.getString("dtVencto")));
                pedido.setValorPago(rsPed.getBigDecimal("VlrPago"));
                pedido.setValorNF(rsPed.getBigDecimal("VlrNF"));
                pedido.setObservacao(rsPed.getString("Observacao"));
            }
            
            ResultSet rs = st.executeQuery();
            TbInsumos.limpar(); 
            while(rs.next()){
                  TbPedidosInsumosBeans l = new TbPedidosInsumosBeans();
                    l.setId(rs.getInt("ID"));
                    l.setStatus(rs.getBoolean("Status"));
                    l.setIdPedido(rs.getInt("IdPedido"));
                    l.setnPedido(rs.getString("nPedido"));
                    l.setIdFazenda(rs.getInt("idFazenda"));
                    l.setFazenda(rs.getString("Fazenda"));
                    l.setIdCategoria(rs.getInt("idCategoria"));
                    l.setCategoria(rs.getString("Categoria"));
                    l.setIdInsumo(rs.getInt("idInsumo"));
                    l.setInsumo(rs.getString("Insumo"));
                    l.setUnidade(rs.getString("Unidade"));
                    l.setQuantidade(rs.getDouble("Quant"));
                    l.setSifra(rs.getString("Sifra"));
                    l.setValorUnit(rs.getBigDecimal("VlrUnit"));
                    l.setValorTotal(rs.getBigDecimal("VlrTotal"));
                    l.setQtEntregue(rs.getDouble("QtEntregue"));
                    l.setSaldoEntregar(rs.getDouble("Saldo"));
                    ListaInsumos.add(l);
            }
            TbInsumos.addLista(ListaInsumos);
            
            stPed.close();
            rsPed.close();
            st.close();
            rs.close();
            
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex+ " Erro ao buscar Pedido", "Erro", 0, FormatarICO.ICObtnSair()); 
        }
        
    }
    
    public void editarPedido(PedidosBeans pedido, TableModelTbInsumos TbInsumos){
        Conexao.ReiniciarCon();
        String SQLUpdate = "update pedidos SET pedidos_nPedido = ?, " + 
            "pedidos_safra = ?, " + "pedidos_emissao = ?, " +
            "pedidos_codFornecedor = ?, " + "pedidos_fornecedor = ?, " +
            "pedidos_codFazenda = ?, " + "pedidos_fazenda = ?, " +
            "pedidos_moeda = ?, " + "pedidos_valorTotal = ?, " +
            "pedidos_vencto = ?, " + "pedidos_obs = ? " +
            "where pedidos_id = ?"; 
        String SQLInsumos = "update pedidos_insumos SET insumos_status = ?, " +
            "insumos_idPedido = ?," +
            "insumos_npedido = ?, " +
            "insumos_idFazenda = ?, " +
            "insumos_fazenda = ?, " +
            "insumos_idCategoria = ?, " +
            "insumos_nomeCat = ?, " +
            "insumos_idInsumo = ?, " +
            "insumos_Insumo = ?, " +
            "insumos_unid = ?, " +
            "insumos_quant = ?, " +
            "insumos_sifra = ?, " +
            "insumos_valorUnit = ?, " +
            "insumos_valorTotal = ? " +
            "WHERE insumos_id = ?";
        
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(SQLInsumos);
                st.setString(1, pedido.getnPedido());
                st.setString(2, pedido.getSafra());
                st.setString(3, Corretores.ConverterParaSQL(pedido.getDtEmissao()));
                st.setInt(4, pedido.getIdFornecedor());
                st.setString(5, pedido.getFornecedor());
                st.setInt(6, pedido.getIdFazenda());
                st.setString(7, pedido.getFazenda());
                st.setString(8, pedido.getMoeda());
                st.setBigDecimal(9, pedido.getValorTotal());
                st.setString(10, Corretores.ConverterParaSQL(pedido.getDtVencimento()));
                st.setString(11, pedido.getObservacao());
                st.setInt(12, pedido.getID());
                st.executeUpdate();
            
            for (int i = 0; i < TbInsumos.getRowCount(); i++){
               if (Integer.parseInt(TbInsumos.getValueAt(i, 0).toString()) != 0){
                    stInsumos.setBoolean(1, Boolean.parseBoolean(TbInsumos.getValueAt(i, 1).toString())); //Status
                    stInsumos.setInt(2, Integer.parseInt(TbInsumos.getValueAt(i, 2).toString())); //ID pedido
                    stInsumos.setString(3, pedido.getnPedido()); // n pedido
                    stInsumos.setInt(4, Integer.parseInt(TbInsumos.getValueAt(i, 4).toString())); // id fazenda
                    stInsumos.setString(5, TbInsumos.getValueAt(i, 5).toString()); // nome fazenda
                    stInsumos.setInt(6, Integer.parseInt(TbInsumos.getValueAt(i, 6).toString())); // id categoria
                    stInsumos.setString(7, TbInsumos.getValueAt(i, 7).toString()); // nome categoria
                    stInsumos.setInt(8, Integer.parseInt(TbInsumos.getValueAt(i, 8).toString())); // id insummo
                    stInsumos.setString(9, TbInsumos.getValueAt(i, 9).toString()); // insumo
                    stInsumos.setString(10, TbInsumos.getValueAt(i, 10).toString()); //unidade
                    stInsumos.setDouble(11, Double.parseDouble(TbInsumos.getValueAt(i, 11).toString())); //quantidade
                    stInsumos.setString(12, TbInsumos.getValueAt(i, 12).toString()); // sifra
                    stInsumos.setBigDecimal(13, new BigDecimal(TbInsumos.getValueAt(i, 13).toString())); // valor unitario
                    stInsumos.setBigDecimal(14, new BigDecimal(TbInsumos.getValueAt(i, 14).toString())); // valor total
                    stInsumos.setInt(15, Integer.parseInt(TbInsumos.getValueAt(i, 0).toString()));
                    stInsumos.addBatch();  
               } else {
                   incluirInsumo(i, TbInsumos, pedido.getID());
               }
            }
            
            stInsumos.executeBatch();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null,"Pedido editado com Sucesso!", "Registro Salvo", 0, FormatarICO.ICObtnOk()); 
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex+ " Erro ao editar Pedido", "Erro", 0, FormatarICO.ICObtnSair()); 
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(PedidosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            
        }
        
    }
    
    public void incluirInsumo(Integer i, TableModelTbInsumos TbInsumos, Integer idPedido){
        String SQLincluir = "insert into pedidos_insumos (insumos_status, insumos_idPedido, insumos_npedido, insumos_idFazenda, " +
        "insumos_fazenda, insumos_idCategoria, insumos_nomeCat, insumos_idInsumo, insumos_Insumo, insumos_unid, " +
        "insumos_quant, insumos_sifra, insumos_valorUnit, insumos_valorTotal) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)"; 
        try {
            PreparedStatement  stIncluir = Conexao.getConnection().prepareStatement(SQLincluir);
                stIncluir.setBoolean(1, Boolean.parseBoolean(TbInsumos.getValueAt(i, 1).toString())); //Status
                stIncluir.setInt(2, idPedido); //ID pedido
                stIncluir.setString(3, TbInsumos.getValueAt(i, 3).toString()); // n pedido
                stIncluir.setInt(4, Integer.parseInt(TbInsumos.getValueAt(i, 4).toString())); // id fazenda
                stIncluir.setString(5, TbInsumos.getValueAt(i, 5).toString()); // nome fazenda
                stIncluir.setInt(6, Integer.parseInt(TbInsumos.getValueAt(i, 6).toString())); // id categoria
                stIncluir.setString(7, TbInsumos.getValueAt(i, 7).toString()); // nome categoria
                stIncluir.setInt(8, Integer.parseInt(TbInsumos.getValueAt(i, 8).toString())); // id insummo
                stIncluir.setString(9, TbInsumos.getValueAt(i, 9).toString()); // insumo
                stIncluir.setString(10, TbInsumos.getValueAt(i, 10).toString()); //unidade
                stIncluir.setDouble(11, Double.parseDouble(TbInsumos.getValueAt(i, 11).toString())); //quantidade
                stIncluir.setString(12, TbInsumos.getValueAt(i, 12).toString()); // sifra
                stIncluir.setBigDecimal(13, new BigDecimal(TbInsumos.getValueAt(i, 13).toString())); // valor unitario
                stIncluir.setBigDecimal(14, new BigDecimal(TbInsumos.getValueAt(i, 14).toString())); // valor total
                stIncluir.execute();
                stIncluir.close();
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex+ " Erro ao Inserir Pedido", "Erro", 0, FormatarICO.ICObtnSair()); 
        }    
    }
    
    public void excluirPedido(PedidosBeans pedido){
        Conexao.ReiniciarCon();
        String DeletePedidos = "delete from pedidos where pedidos_id = ?";
        String DeleteInsumos = "delete from pedidos_insumos where insumos_idPedido = ?";
        
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(DeleteInsumos);
                stInsumos.setInt(1, pedido.getID());
            PreparedStatement stPedidos = Conexao.getConnection().prepareStatement(DeletePedidos);
                stPedidos.setInt(1, pedido.getID());
                stInsumos.execute();
                stPedidos.execute();
                stInsumos.close();
                stPedidos.close();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null,"Pedido excluido com Sucesso!", "Registro Salvo", 0, FormatarICO.ICObtnOk()); 
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
            }
            JOptionPane.showMessageDialog(null, ex+ " Erro ao Excluir Pedido", "Erro", 0, FormatarICO.ICObtnSair());  
        } finally {
            
        }     
    }
    
    public void excluirInsumo(Integer IdInsumo){
        Conexao.getConnection();
        String DeleteInsumo = "delete from pedidos_insumos where insumos_id = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(DeleteInsumo);
            st.setInt(1, IdInsumo);
            st.executeUpdate();
            st.close();
            JOptionPane.showMessageDialog(null,"Insumo excluido com Sucesso!", "Registro Salvo", 0, FormatarICO.ICObtnOk()); 
        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, ex+ " Erro ao Excluir Insumo", "Erro", 0, FormatarICO.ICObtnSair());  
        }
        
        
    }
    
    public void ResumoPedidos(TableModelRelPedidos TbPedidos, String SQLWhere){
        Conexao.ReiniciarCon();
        String SQLSelection = "select \n" +
        "0 as Status,\n" +
        "pedidos_id as ID,\n" +
        "pedidos_emissao as DtEmissao,\n" +
        "pedidos_vencto as DtVencimento,\n" +
        "pedidos_nPedido as nPedido,\n" +
        "pedidos_safra as Safra,\n" +
        "pedidos_codFornecedor as IdFornecedor,\n" +
        "pedidos_fornecedor as Fornecedor,\n" +
        "pedidos_codFazenda as IdFazenda,\n" +
        "pedidos_fazenda as Fazenda,\n" +
        "pedidos_moeda as Moeda,\n" +
        "(select cad_moeda_sifra from cad_moeda where cad_moeda_nome = Moeda) as Sifra,\n" +
        "pedidos_valorTotal as ValorPedido,\n" +
        "IFNULL((select sum(insumos_valorTotal) from pedidos_insumos where insumos_idPedido = ID),0) as ValorInsumos,\n" +
        "IFNULL((select sum((Select Case Moeda WHEN 'Real' THEN pagto_valorCompra ELSE pagto_valormoeda END)) from pagamentos where pagto_nPedido = ID),0) as ValorAgendadoMoeda,\n" +
        "IFNULL((select sum(pagto_valormoeda) from pagamentos where pagto_nPedido = ID and pagto_status = 1),0) as ValorPagoMoeda,\n" +
        "IFNULL((select sum(pagto_valorPagto) from pagamentos where pagto_nPedido = ID and pagto_status = 1),0) as ValorPagoReais,\n" +
        "IFNULL((select sum(ent_nf_vlrDoc) from ent_insumo_nf where ent_nf_idPedido = ID),0) as ValorNF\n" +
        " from pedidos where pedidos_fornecedor like '%" + SQLWhere;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            List<TbRelPedidosInsumosBeans> lista = new ArrayList<>();
            TbPedidos.limpar();
            while(rs.next()){
               TbRelPedidosInsumosBeans l = new TbRelPedidosInsumosBeans();
               l.setStatus(rs.getBoolean("Status"));
               l.setId(rs.getInt("ID"));
               l.setDataEmissao(Corretores.ConverterParaJava(rs.getString("DtEmissao")));
               l.setVencimento(Corretores.ConverterParaJava(rs.getString("DtVencimento")));
               l.setnPedido(rs.getString("nPedido"));
               l.setSafra(rs.getString("Safra"));
               l.setIdFornecedor(rs.getInt("IdFornecedor"));
               l.setFornecedor(rs.getString("Fornecedor"));
               l.setIdFazenda(rs.getInt("IdFazenda"));
               l.setFazenda(rs.getString("Fazenda"));
               l.setMoeda(rs.getString("Moeda"));
               l.setSifra(rs.getString("Sifra"));
               l.setValorPedido(rs.getBigDecimal("ValorPedido"));
               l.setValorInsumos(rs.getBigDecimal("ValorInsumos"));
               l.setValorAgendadoMoeda(rs.getBigDecimal("ValorAgendadoMoeda"));
               l.setValorPagoMoeda(rs.getBigDecimal("ValorPagoMoeda"));
               l.setValorPagoReais(rs.getBigDecimal("ValorPagoReais"));
               l.setValorNF(rs.getBigDecimal("ValorNF"));
               l.setStatusPagto("");
               lista.add(l);
            }
            TbPedidos.addLista(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex+ " Erro ao buscar pedidos!", "Erro", 0, FormatarICO.ICObtnSair());  
        }
    
    }
    
    public void resumoPagtosFornecedor(TableModelResPagto TbPagtos, String IdFornecedor){
        Conexao.ReiniciarCon();
        List<TbResPagamentosBeans> listPagtos = new ArrayList<>(); 
        String SQLSelection = "select "
                + "p.pagto_id as ID,"
                + "(Select cad_conta_descricao from cad_contabancaria where cad_conta_id = p.pagto_idContaOrigem) as ContaOrigem,"
                + "p.pagto_DtPrevista as DataPrevista,"
                + "p.pagto_DataPagto as DataPagto,"
                + "p.pagto_titular as Favorecido, "
                + "p.pagto_forma as Forma, "
                + "p.pagto_nDoc as NDoc, "
                + "p.pagto_banco as BancoDestino, "
                + "p.pagto_agencia as Agencia, "
                + "p.pagto_conta as Conta,"
                + "p.pagto_moeda as Moeda,"
                + "p.pagto_valormoeda as ValorEmMoeda, "
                + "p.pagto_taxa as Taxa, "
                + "p.pagto_valorCompra as ValorPrevisto, "
                + "p.pagto_valorPagto as ValorPago, "
                + "ifnull( (select sum(fis.fiscal_valordoc) from escrituracao_fiscal fis where fis.fiscal_idpagto = p.pagto_id  ),0) as ValorFiscal, "
                + "ifnull( (select sum(clas.clas_valor) from escrituracao_classificacao clas where clas.clas_idpagto = p.pagto_id  ),0) as ValorClass, "
                + "p.pagto_status as Status "
                + "from pagamentos p Where p.pagto_cpf = " + "(select c.cad_fornecedor_cnpj from cad_fornecedor c where c.cad_fornecedor_id = " + IdFornecedor + ")"
                + " order by ID";
        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbPagtos.limpar();
            while (rs.next()){ 
                TbResPagamentosBeans l = new TbResPagamentosBeans();
                l.setIDPagto(rs.getInt("ID"));
                l.setContaOrigem(rs.getString("ContaOrigem"));
                l.setDtPrevista(Corretores.ConverterParaJava(rs.getString("DataPrevista")));
                l.setDtPagamento(Corretores.ConverterParaJava(rs.getString("DataPagto")));
                l.setTitular(rs.getString("Favorecido"));
                l.setFormaPagto(rs.getString("Forma"));
                l.setnDoc(rs.getInt("NDoc"));
                l.setBancoDestino(rs.getString("BancoDestino"));
                l.setAgencia(rs.getString("Agencia"));
                l.setContaCorrente(rs.getString("Conta"));
                l.setMoeda(rs.getString("Moeda"));
                l.setValorEmMoeda(rs.getBigDecimal("ValorEmMoeda"));
                l.setTaxa(rs.getBigDecimal("Taxa"));
                l.setValorPrevisto(rs.getBigDecimal("ValorPrevisto"));
                l.setValorPagto(rs.getBigDecimal("ValorPago"));
                l.setValorFiscal(rs.getBigDecimal("ValorFiscal"));
                l.setValorClassificado(rs.getBigDecimal("ValorClass"));
                l.setStatus(rs.getBoolean("Status"));
                listPagtos.add(l);
            }    
            TbPagtos.addLista(listPagtos);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar pagamentos", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }
    
    public void resumoPagtosDoPedido(TableModelResPagto TbPagtos, String IdPedido){
        Conexao.ReiniciarCon();
        List<TbResPagamentosBeans> listPagtos = new ArrayList<>(); 
        String SQLSelection = "select "
                + "p.pagto_id as ID,"
                + "(Select cad_conta_descricao from cad_contabancaria where cad_conta_id = p.pagto_idContaOrigem) as ContaOrigem,"
                + "p.pagto_DtPrevista as DataPrevista,"
                + "p.pagto_DataPagto as DataPagto,"
                + "p.pagto_titular as Favorecido, "
                + "p.pagto_forma as Forma, "
                + "p.pagto_nDoc as NDoc, "
                + "p.pagto_banco as BancoDestino, "
                + "p.pagto_agencia as Agencia, "
                + "p.pagto_conta as Conta,"
                + "p.pagto_moeda as Moeda,"
                + "p.pagto_valormoeda as ValorEmMoeda, "
                + "p.pagto_taxa as Taxa, "
                + "p.pagto_valorCompra as ValorPrevisto, "
                + "p.pagto_valorPagto as ValorPago, "
                + "ifnull( (select sum(fis.fiscal_valordoc) from escrituracao_fiscal fis where fis.fiscal_idpagto = p.pagto_id  ),0) as ValorFiscal, "
                + "ifnull( (select sum(clas.clas_valor) from escrituracao_classificacao clas where clas.clas_idpagto = p.pagto_id  ),0) as ValorClass, "
                + "p.pagto_status as Status "
                + "from pagamentos p Where p.pagto_nPedido = " + IdPedido
                + " order by ID";
        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbPagtos.limpar();
            while (rs.next()){ 
                TbResPagamentosBeans l = new TbResPagamentosBeans();
                l.setIDPagto(rs.getInt("ID"));
                l.setContaOrigem(rs.getString("ContaOrigem"));
                l.setDtPrevista(Corretores.ConverterParaJava(rs.getString("DataPrevista")));
                l.setDtPagamento(Corretores.ConverterParaJava(rs.getString("DataPagto")));
                l.setTitular(rs.getString("Favorecido"));
                l.setFormaPagto(rs.getString("Forma"));
                l.setnDoc(rs.getInt("NDoc"));
                l.setBancoDestino(rs.getString("BancoDestino"));
                l.setAgencia(rs.getString("Agencia"));
                l.setContaCorrente(rs.getString("Conta"));
                l.setMoeda(rs.getString("Moeda"));
                l.setValorEmMoeda(rs.getBigDecimal("ValorEmMoeda"));
                l.setTaxa(rs.getBigDecimal("Taxa"));
                l.setValorPrevisto(rs.getBigDecimal("ValorPrevisto"));
                l.setValorPagto(rs.getBigDecimal("ValorPago"));
                l.setValorFiscal(rs.getBigDecimal("ValorFiscal"));
                l.setValorClassificado(rs.getBigDecimal("ValorClass"));
                l.setStatus(rs.getBoolean("Status"));
                listPagtos.add(l);
            }    
            TbPagtos.addLista(listPagtos);
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar pagamentos", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void consultarEntradas(TableModelRelInsumos TbRelEntradas, String idPedido){
            Conexao.ReiniciarCon();
            String SQLSelect = "select " +
            "ins.ent_ins_id as IdEntrada," +
            "ins.ent_ins_idNF as IdNF," +
            "ins.idItemPedido as IdItemPedido," +
            "ins.ent_ins_dtEntr as DataEntrada," +
            "ins.ent_ins_idCategoria as idCategoria," +
            "ins.ent_ins_categoria as Categoria," +
            "ins.ent_ins_idInsumo as IdInsumo," +
            "ins.ent_ins_insumo as Insumo, " +
            "ins.ent_ins_quant as Quantidade," +
            "ins.ent_ins_vlrUnit as VlrUnit," +
            "ins.ent_ins_vlrTotal as VlrTotal," +
            "nf.ent_nf_motivo as Motivo," +
            "ifnull(nf.ent_nf_Emissor ,(select cad_fornecedor_nome from cad_fornecedor f where f.cad_fornecedor_id = nf.ent_nf_idEmissor)) as Fornecedor, " +
            "nf.ent_nf_idPedido as idPedido," +
            "nf.ent_nf_placa as Placa," +
            "nf.ent_nf_nDoc as nDoc," +
            "(select p.pedidos_nPedido from pedidos p where p.pedidos_id = idPedido) as Pedido, " +
            "(Select insumos_status from pedidos_insumos where insumos_id = IdItemPedido) as Stat " +
            "FROM ent_insumo ins Join ent_insumo_nf nf ON ins.ent_ins_idNF = nf.ent_nf_id " +
            " WHERE nf.ent_nf_idPedido = " + idPedido;

            try {
                List<TbRelatorioInsumosBeans> listEntradas = new ArrayList<>();
                PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelect);
                ResultSet rs = st.executeQuery();
                TbRelEntradas.limpar();
                while (rs.next()){
                    TbRelatorioInsumosBeans l = new TbRelatorioInsumosBeans(); 
                    l.setIdEntrada(rs.getInt("IdEntrada"));
                    l.setStatus(rs.getBoolean("Stat"));
                    l.setIdNF(rs.getInt("IdNF"));
                    l.setIdItemPedido(rs.getInt("IdItemPedido"));
                    l.setDataEntrada(Corretores.ConverterParaJava(rs.getString("DataEntrada")));
                    l.setMotivo(rs.getString("Motivo"));
                    l.setFornecedor(rs.getString("Fornecedor"));
                    l.setIdPedido(rs.getInt("idPedido"));
                    l.setPedido(rs.getString("Pedido"));
                    l.setnDoc(rs.getInt("nDoc"));
                    l.setPlaca(rs.getString("Placa"));
                    l.setIdCategoria(rs.getInt("idCategoria"));
                    l.setCategoria(rs.getString("Categoria"));
                    l.setIdInsumo(rs.getInt("IdInsumo"));
                    l.setInsumo(rs.getString("Insumo"));
                    l.setQuantidade(rs.getDouble("Quantidade"));
                    l.setVlrUnit(rs.getBigDecimal("VlrUnit"));
                    l.setVlrTotal(rs.getBigDecimal("VlrTotal"));
                    listEntradas.add(l);
                }
                TbRelEntradas.addLista(listEntradas);

            } catch (SQLException ex) {
                  JOptionPane.showMessageDialog(null, ex + " Erro ao buscar entrada", "Erro", 0, FormatarICO.ICObtnSair());
            }

        }






}
