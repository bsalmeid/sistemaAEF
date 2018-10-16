/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.EntradaInsumosBeans;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelEntInsumos;
import TableModel.TableModelRelInsumos;
import TableModel.TbEntradaInsumosBeans;
import TableModel.TbRelatorioInsumosBeans;
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
public class EntradaInsumosDAO {
    
    public Boolean InserirEntrada (EntradaInsumosBeans beans, TableModelEntInsumos TbInsumos){
        Conexao.ReiniciarCon();
        String SQLInsertNF = "insert into ent_insumo_nf (ent_nf_dtLan, ent_nf_user, ent_nf_idEmissor, ent_nf_Emissor ,ent_nf_idFazenda, ent_nf_motivo," +
"ent_nf_idPedido, ent_nf_tipoDoc, ent_nf_dtDoc, ent_nf_nDoc, ent_nf_vlrDoc, ent_nf_tipoFrete, ent_nf_nCTE, ent_nf_placa, ent_nf_pesoDoc" +
" ,ent_nf_pesoB, ent_nf_tara, ent_nf_pesoL) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        String SQLInsertInsumo = "insert into ent_insumo (ent_ins_idNF, idItemPedido, ent_ins_dtEntr, ent_ins_idFazenda, ent_ins_idPedido, ent_ins_idCategoria, " +
"ent_ins_categoria, ent_ins_idInsumo, ent_ins_insumo, ent_ins_quant, ent_ins_vlrUnit, ent_ins_vlrTotal) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertNF , Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(SQLInsertInsumo);
            st.setString(1, Corretores.ConverterParaSQL(dataAtual));
            st.setString(2, UsuarioLogado);
            st.setInt(3, beans.getIdFornecedor());
            st.setString(4, beans.getFornecedor());
            st.setInt(5, beans.getIdFazenda());
            st.setString(6, beans.getOperacao());
            st.setInt(7, beans.getIdPedido());
            st.setString(8, beans.getTipoDoc());
            st.setString(9, Corretores.ConverterParaSQL(beans.getDtEmissao()));
            st.setInt(10, beans.getnDoc());
            st.setBigDecimal(11, beans.getVlrTotal());
            st.setString(12, beans.getTipoFrete());
            st.setInt(13, beans.getnCTE());
            st.setString(14, beans.getPlaca());
            st.setDouble(15, beans.getPesoNF());
            st.setDouble(16, beans.getPesoBruto());
            st.setDouble(17, beans.getTara());
            st.setDouble(18, beans.getPesoLiq());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int lastID = 0;
            while (rs.next()){
                lastID = rs.getInt(1);
            }
 
            for (int i = 0; i < TbInsumos.getRowCount(); i++ ){
                stInsumo.setInt(1, lastID);
                stInsumo.setInt(2, Integer.parseInt(TbInsumos.getValueAt(i, 2).toString())); // ID do item na tabela de pedidos_insumos
                stInsumo.setString(3,Corretores.ConverterParaSQL(beans.getDtEntrada())); // data de entrada
                stInsumo.setInt(4, Integer.parseInt(TbInsumos.getValueAt(i, 5).toString())); // ida fazenda
                stInsumo.setInt(5, beans.getIdPedido()); // id pedido
                stInsumo.setInt(6, Integer.parseInt(TbInsumos.getValueAt(i, 7).toString())); // id categoria
                stInsumo.setString(7, TbInsumos.getValueAt(i, 8).toString()); // categoria
                stInsumo.setInt(8, Integer.parseInt(TbInsumos.getValueAt(i, 9).toString())); // id insumo;
                stInsumo.setString(9, TbInsumos.getValueAt(i, 10).toString()); // nome insumo;
                stInsumo.setDouble(10, Double.parseDouble(TbInsumos.getValueAt(i, 12).toString())); // quantidade;
                stInsumo.setBigDecimal(11, new BigDecimal(TbInsumos.getValueAt(i, 14).toString())); // valor unit
                stInsumo.setBigDecimal(12, new BigDecimal(TbInsumos.getValueAt(i, 15).toString())); // valor total
                stInsumo.addBatch();
            }
            stInsumo.executeBatch();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " Erro ao inserir registro!", "Erro", 0, FormatarICO.ICObtnSair());
           
           try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
                return false; 
            } catch (SQLException exl) {
                return false;
            }
        }
        return true;
    }
    
    public void consultarEntradas(TableModelRelInsumos TbRelEntradas, EntradaInsumosBeans beans){
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
        " WHERE ent_ins_dtEntr between '" + beans.getDtInicial() + "' and '" + beans.getDtFinal() + "' and ent_ins_idFazenda = " + beans.getIdFazendaPesq() + beans.getSQLPesqFornecedor() + beans.getSQLPesqProduto();
        
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
    
    public void buscarEntrada(TableModelEntInsumos TbInsumos, EntradaInsumosBeans beans){
        Conexao.ReiniciarCon();
        String SQLNF = "select "
                + " (select DISTINCT ent_ins_dtEntr from ent_insumo where ent_ins_idNF = nf.ent_nf_id) as dtEntrada," +
                " ent_nf_idEmissor as idEmissor," +
                " ifnull(nf.ent_nf_Emissor, (select cad_fornecedor_razao from cad_fornecedor where cad_fornecedor_id = idEmissor)) as Fornecedor," +
                " ent_nf_idFazenda as idFazenda," +
                " ent_nf_motivo as Motivo, " +
                " ent_nf_idPedido as idPedido, " +
                " (select pedidos_nPedido from pedidos where pedidos_id = idPedido) as nPedido, " +
                " ent_nf_tipoDoc as tipoDoc, " +
                " ent_nf_dtDoc as dtDocumento, " +
                " ent_nf_nDoc as nDoc, " +
                " ent_nf_vlrDoc as VlrDoc, " +
                " ent_nf_tipoFrete as TipoFrete, " +
                " ent_nf_nCTE as nCTE," +        
                " ent_nf_placa as Placa, " +
                " ent_nf_pesoDoc as pesoDoc, " +
                " ent_nf_pesoB as pesoB, " +
                " ent_nf_tara as tara, " +
                " ent_nf_pesoL as pesoL " +
                " from ent_insumo_nf nf where ent_nf_id = " + beans.getID(); 

        String SQLInsumos = "select " +
                "ent_ins_id as idEntrada," +
                "0 as Stat, " +
                "idItemPedido as ItemPedido, " +
                "ent_ins_idPedido as idPedido," +
                "(select pedidos_nPedido from pedidos where pedidos_id = idPedido) as nPedido, " +
                "ent_ins_idFazenda as idFazenda," +
                "(select cad_fazendas_nome from cad_fazendas where cad_fazendas_id = idFazenda) as Fazenda, " +
                "ent_ins_idCategoria as idCategoria," +
                "ent_ins_categoria as Categoria, " +
                "ent_ins_idInsumo as idInsumo, " +
                "ent_ins_insumo as Insumo, " +
                "(select cad_ins_unid from cad_insumos where cad_ins_id = idInsumo) as Unidade, " +
                "ent_ins_quant as Quantidade, " +
                "ent_ins_vlrUnit as VlrUnit, " +
                "ent_ins_vlrTotal as VlrTotal, " +
                "@qtEntrega:= ifnull((select sum(ent_ins_quant) from ent_insumo where idItemPedido = ItemPedido),0) as qtEntrega, " +
                "ifnull((select insumos_quant from pedidos_insumos where insumos_id = ItemPedido),0) - @qtEntrega as Saldo " +
                "from ent_insumo ei " +
                "WHERE ent_ins_idNF = " + beans.getID();  

        try {
            List<TbEntradaInsumosBeans> listaInsumos = new ArrayList<>();
            PreparedStatement stNF = Conexao.getConnection().prepareStatement(SQLNF);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(SQLInsumos);
            ResultSet rsNF = stNF.executeQuery();
            
            while (rsNF.next()){
                beans.setDtEntrada(Corretores.ConverterParaJava(rsNF.getString("dtEntrada")));
                beans.setIdFornecedor(rsNF.getInt("idEmissor"));
                beans.setFornecedor(rsNF.getString("Fornecedor"));
                beans.setIdFazenda(rsNF.getInt("idFazenda"));
                beans.setOperacao(rsNF.getString("Motivo"));
                beans.setIdPedido(rsNF.getInt("idPedido"));
                beans.setnPedido(rsNF.getString("nPedido"));
                beans.setTipoDoc(rsNF.getString("tipoDoc"));
                beans.setDtEmissao(Corretores.ConverterParaJava(rsNF.getString("dtDocumento")));
                beans.setnDoc(rsNF.getInt("nDoc"));
                beans.setVlrTotal(rsNF.getBigDecimal("VlrDoc"));
                beans.setTipoFrete(rsNF.getString("TipoFrete"));
                beans.setnCTE(rsNF.getInt("nCTE"));
                beans.setPlaca(rsNF.getString("Placa"));
                beans.setPesoNF(rsNF.getDouble("pesoDoc"));
                beans.setPesoBruto(rsNF.getDouble("pesoB"));
                beans.setTara(rsNF.getDouble("tara"));
                beans.setPesoLiq(rsNF.getDouble("pesoL"));
            }
     
        ResultSet rsInsumos = stInsumos.executeQuery();
            TbInsumos.limpar();
            while(rsInsumos.next()){
                TbEntradaInsumosBeans l = new TbEntradaInsumosBeans();
                l.setId(rsInsumos.getInt("idEntrada"));
                l.setStatus(rsInsumos.getBoolean("Stat"));
                l.setItemListaPedidos(rsInsumos.getInt("ItemPedido"));
                l.setIdPedido(rsInsumos.getInt("idPedido"));
                l.setnPedido(rsInsumos.getString("nPedido"));
                l.setIdFazenda(rsInsumos.getInt("idFazenda"));
                l.setFazenda(rsInsumos.getString("Fazenda"));
                l.setIdCategoria(rsInsumos.getInt("idCategoria"));
                l.setCategoria(rsInsumos.getString("Categoria"));
                l.setIdInsumo(rsInsumos.getInt("idInsumo"));
                l.setInsumo(rsInsumos.getString("Insumo"));
                l.setUnidade(rsInsumos.getString("Unidade"));
                l.setQuantidade(rsInsumos.getDouble("Quantidade"));
                l.setSifra("R$");
                l.setValorUnit(rsInsumos.getBigDecimal("VlrUnit"));
                l.setValorTotal(rsInsumos.getBigDecimal("VlrTotal"));
                if (rsInsumos.getInt("ItemPedido") == 0){ l.setQtEntregue(0.0) ; l.setSaldoEntregar(0.0);
                } else { 
                    l.setQtEntregue(rsInsumos.getDouble("qtEntrega"));
                    l.setSaldoEntregar(rsInsumos.getDouble("Saldo"));
                }
                listaInsumos.add(l);
            }
        TbInsumos.addLista(listaInsumos);
        
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex + " Erro ao buscar entrada", "Erro", 0, FormatarICO.ICObtnSair());
        }
       
    }
    
    public boolean excluirInsumo (Integer ID){
        Conexao.ReiniciarCon();
        String SQLDelete = "delete from ent_insumo where ent_ins_id = " + ID;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLDelete);
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Registro excluido com sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao excluir insumo!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return true;
    }
 
    public void editarEntrada(EntradaInsumosBeans beans, TableModelEntInsumos TbInsumos){
        Conexao.ReiniciarCon();
        String SQLNF = "update ent_insumo_nf SET ent_nf_dtLan = ?, ent_nf_user = ?," +
            "ent_nf_idEmissor = ?, ent_nf_idFazenda = ?, " +
            "ent_nf_motivo = ?, ent_nf_idPedido = ?, " +
            "ent_nf_tipoDoc = ?, ent_nf_dtDoc = ?, " +
            "ent_nf_nDoc = ?, ent_nf_vlrDoc = ?, " +
            "ent_nf_tipoFrete = ?, ent_nf_nCTE = ?, " +
            "ent_nf_placa = ?, ent_nf_pesoDoc = ?, " +
            "ent_nf_pesoB = ?, ent_nf_tara = ?, " +
            "ent_nf_pesoL = ? WHERE ent_nf_id = ? "; 
        
        String SQLInsumos = "update ent_insumo SET ent_ins_idNF = ?, idItemPedido = ?," +
            "ent_ins_dtEntr = ?, ent_ins_idFazenda = ?," +
            "ent_ins_idPedido = ?, ent_ins_idCategoria = ?," +
            "ent_ins_categoria = ?, ent_ins_idInsumo = ?, " +
            "ent_ins_insumo = ?, ent_ins_quant = ?, " +
            "ent_ins_vlrUnit = ?, ent_ins_vlrTotal = ? " +
            " Where ent_ins_id = ?";
        
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement stNF = Conexao.getConnection().prepareStatement(SQLNF);
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(SQLInsumos);
                stNF.setString(1, Corretores.ConverterParaSQL(dataAtual));
                stNF.setString(2, UsuarioLogado);
                stNF.setInt(3, beans.getIdFornecedor());
                stNF.setInt(4, beans.getIdFazenda());
                stNF.setString(5, beans.getOperacao());
                stNF.setInt(6, beans.getIdPedido());
                stNF.setString(7, beans.getTipoDoc());
                stNF.setString(8, Corretores.ConverterParaSQL(beans.getDtEmissao()));
                stNF.setInt(9, beans.getnDoc());
                stNF.setBigDecimal(10, beans.getVlrTotal());
                stNF.setString(11, beans.getTipoFrete());
                stNF.setInt(12, beans.getnCTE());
                stNF.setString(13, beans.getPlaca());
                stNF.setDouble(14, beans.getPesoNF());
                stNF.setDouble(15, beans.getPesoBruto());
                stNF.setDouble(16, beans.getTara());
                stNF.setDouble(17, beans.getPesoLiq());
                stNF.setInt(18, beans.getID());
                stNF.executeUpdate();
            
            for (int i = 0; i < TbInsumos.getRowCount(); i++){
                if ((Integer)TbInsumos.getValueAt(i, 0) > 0){
                    stInsumo.setInt(1, beans.getID());
                    stInsumo.setInt(2, Integer.parseInt(TbInsumos.getValueAt(i, 2).toString())); // ID do item na tabela de pedidos_insumos
                    stInsumo.setString(3,Corretores.ConverterParaSQL(beans.getDtEntrada())); // data de entrada
                    stInsumo.setInt(4, Integer.parseInt(TbInsumos.getValueAt(i, 5).toString())); // ida fazenda
                    stInsumo.setInt(5, beans.getIdPedido()); // id pedido
                    stInsumo.setInt(6, Integer.parseInt(TbInsumos.getValueAt(i, 7).toString())); // id categoria
                    stInsumo.setString(7, TbInsumos.getValueAt(i, 8).toString()); // categoria
                    stInsumo.setInt(8, Integer.parseInt(TbInsumos.getValueAt(i, 9).toString())); // id insumo;
                    stInsumo.setString(9, TbInsumos.getValueAt(i, 10).toString()); // nome insumo;
                    stInsumo.setDouble(10, Double.parseDouble(TbInsumos.getValueAt(i, 12).toString())); // quantidade;
                    stInsumo.setBigDecimal(11, new BigDecimal(TbInsumos.getValueAt(i, 14).toString())); // valor unit
                    stInsumo.setBigDecimal(12, new BigDecimal(TbInsumos.getValueAt(i, 15).toString())); // valor total
                    stInsumo.setInt(13, Integer.parseInt(TbInsumos.getValueAt(i, 0).toString()));
                    stInsumo.addBatch();                   
                } else {
                   incluirInsumo(beans, TbInsumos, i);
                }                
            }
            stInsumo.executeBatch();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Entrada editada com sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {    
            try {
                Conexao.getConnection().rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(EntradaInsumosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + " Erro ao editar insumo!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }
    
    private void incluirInsumo(EntradaInsumosBeans beans, TableModelEntInsumos TbInsumos, Integer i){
        String SQLInsertInsumo = "insert into ent_insumo (ent_ins_idNF, idItemPedido, ent_ins_dtEntr, ent_ins_idFazenda, ent_ins_idPedido, ent_ins_idCategoria, " +
        "ent_ins_categoria, ent_ins_idInsumo, ent_ins_insumo, ent_ins_quant, ent_ins_vlrUnit, ent_ins_vlrTotal) values (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(SQLInsertInsumo);
                stInsumo.setInt(1, beans.getID());
                stInsumo.setInt(2, Integer.parseInt(TbInsumos.getValueAt(i, 2).toString())); // ID do item na tabela de pedidos_insumos
                stInsumo.setString(3,Corretores.ConverterParaSQL(beans.getDtEntrada())); // data de entrada
                stInsumo.setInt(4, Integer.parseInt(TbInsumos.getValueAt(i, 5).toString())); // ida fazenda
                stInsumo.setInt(5, beans.getIdPedido()); // id pedido
                stInsumo.setInt(6, Integer.parseInt(TbInsumos.getValueAt(i, 7).toString())); // id categoria
                stInsumo.setString(7, TbInsumos.getValueAt(i, 8).toString()); // categoria
                stInsumo.setInt(8, Integer.parseInt(TbInsumos.getValueAt(i, 9).toString())); // id insumo;
                stInsumo.setString(9, TbInsumos.getValueAt(i, 10).toString()); // nome insumo;
                stInsumo.setDouble(10, Double.parseDouble(TbInsumos.getValueAt(i, 12).toString())); // quantidade;
                stInsumo.setBigDecimal(11, new BigDecimal(TbInsumos.getValueAt(i, 14).toString())); // valor unit
                stInsumo.setBigDecimal(12, new BigDecimal(TbInsumos.getValueAt(i, 15).toString())); // valor total
                stInsumo.execute();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " Erro ao incluir insumo!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    
    }
    
    
    
    
}
