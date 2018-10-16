/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.SaidaGadoBeans;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Bruno
 */
public class SaidaGadoDAO {
     
    
   public ArrayList preencherFazendaDestino(){
            Conexao.ReiniciarCon();
            String SQLSelection = "select cad_fazendas_id as ID, cad_fazendas_nome as Fazenda from cad_fazendas WHERE cad_fazendas_stat = 'Ativo'";
            ArrayList fazenda = new ArrayList();
            try {
                PreparedStatement st2 = Conexao.getConnection().prepareStatement(SQLSelection);
                ResultSet rs2 = st2.executeQuery();
                fazenda.add("-");
                while (rs2.next()){
                   fazenda.add(rs2.getString(2));
                }           
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar fazendas", "Erro", 0, FormatarICO.ICObtnSair());
            }
            return fazenda;
    }
   
    public ArrayList buscarClientes(){
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_cliente_id as id_cli, cad_cliente_nome as Cliente From cad_clientes Where cad_clientes_categoria = 'Frigorifico'";
        ArrayList cliente = new ArrayList();
        cliente.add("-");
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()){
                cliente.add(rs.getString(2));
            }
        } catch (Exception e) {
            
        }
        return cliente;
    }
  
   public ArrayList fazendaOrigem(){
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from pm_user_fazenda where login_usuario = ?";
            ArrayList Origem = new ArrayList();
            Origem.add("-");
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, UsuarioLogado);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                if (rs.getInt(4) == 1){
                  Origem.add(rs.getString(2));
                }
            }  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar permissões", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return Origem;
    } 
  
   public ArrayList buscarTransportadora(){
       Conexao.ReiniciarCon();
       String SQLSelection = "select cad_transp_id, cad_transp_nome from cad_transportadora Where cad_transp_segmento = 'Gado' And cad_transp_status = 'Ativo'";
       ArrayList transportadora = new ArrayList();
       transportadora.add("-");
       try {
           PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
           ResultSet rs = st.executeQuery();
           while(rs.next()){
               transportadora.add(rs.getString(2));
           }        
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro ao buscar transportadoras", "Erro", 0, FormatarICO.ICObtnSair()); 
       }    
       return transportadora;
   }
   
   public Boolean inserirGta(DefaultTableModel TbGTASaida, SaidaGadoBeans Saida) {
      Conexao.ReiniciarCon();
       String SQLNF = "insert into saida_gado(saida_gado_dtLan, saida_gado_user, saida_gado_dtSaida, saida_gado_motivo "
               + ", saida_gado_nEscala, saida_gado_nNF, saida_gado_valorNF, saida_gado_qNF, saida_gado_Destino, saida_gado_transp "
               + ", saida_gado_nMin, saida_gado_origGado, saida_gado_categoria, saida_gado_placa, saida_gado_qMin, saida_gado_tara"
               + ", saida_gado_pesoB, saida_gado_pesoL, saida_gado_pesoM, saida_gado_obs) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

       String SQLGTA = "insert into saida_gta (saida_gta_idSaida, saida_gta_ngta, saida_gta_orig, saida_gta_destino"
               + ", saida_gta_data, saida_gta_m012, saida_gta_m1324, saida_gta_m2536, saida_gta_m36, saida_gta_mT, saida_gta_f012, saida_gta_f1324, saida_gta_f2536, saida_gta_f36, saida_gta_fT) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      // String SQLLastID = "select max(saida_gado_id) from saida_gado";
       try {
           PreparedStatement stGTA = Conexao.getConnection().prepareStatement(SQLGTA);
           PreparedStatement stNF = Conexao.getConnection().prepareStatement(SQLNF, Statement.RETURN_GENERATED_KEYS);
           Conexao.getConnection().setAutoCommit(false);           
          
           stNF.setString(1, Corretores.ConverterParaSQL(dataAtual));
           stNF.setString(2, UsuarioLogado);
           stNF.setString(3, Corretores.ConverterParaSQL(Saida.getDataSaida()));
           stNF.setString(4, Saida.getMotivo());
           stNF.setInt(5, Saida.getnEscala());
           stNF.setString(6, Saida.getnNF());
           stNF.setDouble(7, Saida.getValorNF());
           stNF.setInt(8, Saida.getQtNF());
           stNF.setString(9, Saida.getDestino());
           stNF.setString(10, Saida.getTransportadora());
           stNF.setString(11, Saida.getnMin());
           stNF.setString(12, Saida.getOrigem());
           stNF.setString(13, Saida.getCategoria());
           stNF.setString(14, Saida.getPlaca());
           stNF.setInt(15, Saida.getQtMin());
           stNF.setDouble(16, Saida.getTara());
           stNF.setDouble(17, Saida.getPesoB());
           stNF.setDouble(18, Saida.getPesoL());
           stNF.setDouble(19, Saida.getPesoM());
           stNF.setString(20, Saida.getObservacao());
           stNF.executeUpdate();
           
           ResultSet rs = stNF.getGeneratedKeys();
           int lastID = 0;
           while(rs.next()){
                lastID = rs.getInt(1); 
           }
           for (int linha = 0; linha < TbGTASaida.getRowCount(); linha++){       
               stGTA.setInt(1, lastID);
               stGTA.setString(2, TbGTASaida.getValueAt(linha, 0).toString());
               stGTA.setString(3, TbGTASaida.getValueAt(linha, 1).toString());
               stGTA.setString(4, TbGTASaida.getValueAt(linha, 2).toString());
               stGTA.setString(5, Corretores.ConverterParaSQL(dataAtual));
               stGTA.setInt(6, Integer.parseInt(TbGTASaida.getValueAt(linha, 4).toString()));
               stGTA.setInt(7, Integer.parseInt(TbGTASaida.getValueAt(linha, 5).toString()));
               stGTA.setInt(8, Integer.parseInt(TbGTASaida.getValueAt(linha, 6).toString()));
               stGTA.setInt(9, Integer.parseInt(TbGTASaida.getValueAt(linha, 7).toString()));
               stGTA.setInt(10, Integer.parseInt(TbGTASaida.getValueAt(linha, 8).toString()));
               
               stGTA.setInt(11, Integer.parseInt(TbGTASaida.getValueAt(linha, 9).toString()));
               stGTA.setInt(12, Integer.parseInt(TbGTASaida.getValueAt(linha, 10).toString()));
               stGTA.setInt(13, Integer.parseInt(TbGTASaida.getValueAt(linha, 11).toString()));
               stGTA.setInt(14, Integer.parseInt(TbGTASaida.getValueAt(linha, 12).toString()));
               stGTA.setInt(15, Integer.parseInt(TbGTASaida.getValueAt(linha, 13).toString()));
              
               stGTA.addBatch();
           }
           
           stGTA.executeBatch();
           stGTA.close();
           stNF.close();
           Conexao.getConnection().commit();
           JOptionPane.showMessageDialog(null, "Saída registrada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
       
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " ", "Erro", 0, FormatarICO.ICObtnSair()); 
          try {
              Conexao.getConnection().rollback();
              Conexao.getConnection().setAutoCommit(true);
          } catch (SQLException ex1) {
            JOptionPane.showMessageDialog(null, "Erro na Instrução RollBack, Solicitar Suporte Imediatamente ", "Erro", 0, FormatarICO.ICObtnSair()); 
          }
          return false;
       } 
       return true;
   }
   
   public void preencherTabela(SaidaGadoBeans Saida, DefaultTableModel TbSaida){
       Conexao.ReiniciarCon();
       String SQLSelection = "Select saida_gado_id as ID," 
               + "saida_gado_dtSaida as DtSaida,  " 
               + "saida_gado_motivo as Motivo, " 
               + "saida_gado_nEscala as NEsc,  " 
               + "saida_gado_nNF as nNF,  "
               + "saida_gado_valorNF as ValorNF,  "
               + "saida_gado_origGado as Origem,  "
               + "saida_gado_Destino as Destino,  "
               + "saida_gado_transp as Trans,  "
               + "saida_gado_nMin as nMin,  "
               + "saida_gado_placa as Placa,  "
               + "saida_gado_tara as Tara,  "
               + "saida_gado_pesoB as PesoB,  "
               + "saida_gado_pesoL as PesoL,  "
               + "saida_gado_pesoM as PesoM,  "
               + "saida_gado_categoria as Categoria,  "
               + "saida_gado_obs as Obs,  "
               + "saida_gado_qNF as qNF, "
               + "(select ifnull(sum(saida_gta_mT),0) + ifnull(sum(saida_gta_fT),0) from saida_gta where saida_gta_idSaida = saida_gado_id) as qGTA, "
               + "saida_gado_qMin as qMin "
               + "From saida_gado "
               + "Where saida_gado_OrigGado like '%" + Saida.getOrigem() + "%' And saida_gado_dtSaida like '%" + Corretores.ConverterParaSQL(dataAtual) + "%';";
       try {
           PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
           ResultSet rs = st.executeQuery();
           TbSaida.setNumRows(0);
           while (rs.next()){
                TbSaida.addRow(new Object[]{rs.getInt("ID"), Corretores.ConverterParaJava(rs.getString("DtSaida")), rs.getString("Motivo"), rs.getInt("NEsc"), rs.getString("nNF"), new DecimalFormat("R$ #,###,##0.00").format(rs.getDouble("ValorNF")), rs.getString("Origem"), rs.getString("Destino"), rs.getString("Trans"), rs.getString("nMin"), rs.getString("Placa"), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("Tara")), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("PesoB")), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("PesoL")), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("PesoM")), rs.getString("Categoria"), rs.getInt("qNF"), rs.getInt("qGTA"), rs.getInt("qMin"), rs.getString("Obs") });
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " Erro ao buscar saídas!", "Erro", 0, FormatarICO.ICObtnSair());  
       }    
   }

   public void buscarRegistroGTA(DefaultTableModel TbGTASaida, Integer ID ){
       Conexao.ReiniciarCon();
       String SQLSelection =  "Select saida_gta_ngta as NGTA, "
               + "saida_gta_orig as Origem, "
               + "saida_gta_destino as Destino, "
               + "saida_gta_m012 as M012, "
               + "saida_gta_m1324 as M1324, "
               + "saida_gta_m2536 as M2536, "
               + "saida_gta_m36 as M36, "
               + "saida_gta_mT as MT, "
               + "saida_gta_f012 as F012, "
               + "saida_gta_f1324 as F1324, "
               + "saida_gta_f2536 as F2536, "
               + "saida_gta_f36 as F36, "
               + "saida_gta_fT as FT, "
               + "saida_gta_id as idGTA "
               + "from saida_gta Where saida_gta_idSaida = ?"  ;
          
       try {
           PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
           st.setInt(1, ID);
           ResultSet rs = st.executeQuery();
           TbGTASaida.setNumRows(0);
           while (rs.next()){
               TbGTASaida.addRow(new Object[] {rs.getString("NGTA"), rs.getString("Origem"), rs.getString("Destino"), rs.getInt("MT") + rs.getInt("FT") ,rs.getInt("M012"), rs.getInt("M1324"), rs.getInt("M2536"), rs.getInt("M36"), rs.getInt("MT"), rs.getInt("F012"), rs.getInt("F1324"), rs.getInt("F2536"), rs.getInt("F36"), rs.getInt("FT"), rs.getInt("idGTA")  });
           }
           
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " Erro ao buscar GTA's!", "Erro", 0, FormatarICO.ICObtnSair());
       }    
   }
   
   public void editarRegistro(DefaultTableModel TbGTASaida, Integer ID, SaidaGadoBeans Saida){
       Conexao.ReiniciarCon(); 
       String UpdateTbSaida = "Update saida_gado Set saida_gado_dtLan = ?, saida_gado_user = ?, saida_gado_dtSaida = ?, saida_gado_motivo = ?,"
               + " saida_gado_nEscala = ?, saida_gado_nNF = ?, saida_gado_valorNF = ?, saida_gado_qNF = ?, saida_gado_Destino = ?, saida_gado_transp = ?, saida_gado_nMin = ?,"
               + " saida_gado_origGado = ?, saida_gado_categoria = ?, saida_gado_placa = ?, saida_gado_qMin = ?, saida_gado_tara = ?, saida_gado_pesoB = ?, "
               + " saida_gado_pesoL = ?, saida_gado_pesoM = ?, saida_gado_obs = ? "              
               + " Where saida_gado_id = ?";
       
        String UpdateTbGTA = "update saida_gta Set saida_gta_ngta = ?, saida_gta_orig = ?, saida_gta_destino = ?, saida_gta_data = ? ,"
               + " saida_gta_m012 = ?, saida_gta_m1324 = ?, saida_gta_m2536 = ?, saida_gta_m36 = ?, saida_gta_mT = ?,"
               + " saida_gta_f012 = ?, saida_gta_f1324 = ?, saida_gta_f2536 = ?, saida_gta_f36 = ?, saida_gta_fT = ? "
               + " Where saida_gta_id = ?";
       
       try {
           Conexao.getConnection().setAutoCommit(false);
           PreparedStatement stNF = Conexao.getConnection().prepareStatement(UpdateTbSaida);
           PreparedStatement stGTA = Conexao.getConnection().prepareStatement(UpdateTbGTA);
           
           stNF.setString(1, Corretores.ConverterParaSQL(dataAtual));
           stNF.setString(2, UsuarioLogado);
           stNF.setString(3, Corretores.ConverterParaSQL(Saida.getDataSaida()));
           stNF.setString(4, Saida.getMotivo());
           stNF.setInt(5, Saida.getnEscala());
           stNF.setString(6, Saida.getnNF());
           stNF.setDouble(7, Saida.getValorNF());
           stNF.setInt(8, Saida.getQtNF());
           stNF.setString(9, Saida.getDestino());
           stNF.setString(10, Saida.getTransportadora());
           stNF.setString(11, Saida.getnMin());
           stNF.setString(12, Saida.getOrigem());
           stNF.setString(13, Saida.getCategoria());
           stNF.setString(14, Saida.getPlaca());
           stNF.setInt(15, Saida.getQtMin());
           stNF.setDouble(16, Saida.getTara());
           stNF.setDouble(17, Saida.getPesoB());
           stNF.setDouble(18, Saida.getPesoL());
           stNF.setDouble(19, Saida.getPesoM());
           stNF.setString(20, Saida.getObservacao());
           stNF.setInt(21, ID);
           stNF.executeUpdate();
           
           for (int linha = 0; linha < TbGTASaida.getRowCount(); linha++){ 
               stGTA.setString(1, TbGTASaida.getValueAt(linha, 0).toString());
               stGTA.setString(2, TbGTASaida.getValueAt(linha, 1).toString());
               stGTA.setString(3, TbGTASaida.getValueAt(linha, 2).toString());
               stGTA.setString(4, Corretores.ConverterParaSQL(dataAtual));
               stGTA.setInt(5, Integer.parseInt(TbGTASaida.getValueAt(linha, 4).toString()));
               stGTA.setInt(6, Integer.parseInt(TbGTASaida.getValueAt(linha, 5).toString()));
               stGTA.setInt(7, Integer.parseInt(TbGTASaida.getValueAt(linha, 6).toString()));
               stGTA.setInt(8, Integer.parseInt(TbGTASaida.getValueAt(linha, 7).toString()));
               stGTA.setInt(9, Integer.parseInt(TbGTASaida.getValueAt(linha, 8).toString()));
               
               stGTA.setInt(10, Integer.parseInt(TbGTASaida.getValueAt(linha, 9).toString()));
               stGTA.setInt(11, Integer.parseInt(TbGTASaida.getValueAt(linha, 10).toString()));
               stGTA.setInt(12, Integer.parseInt(TbGTASaida.getValueAt(linha, 11).toString()));
               stGTA.setInt(13, Integer.parseInt(TbGTASaida.getValueAt(linha, 12).toString()));
               stGTA.setInt(14, Integer.parseInt(TbGTASaida.getValueAt(linha, 13).toString())); 
               stGTA.setInt(15, Integer.parseInt(TbGTASaida.getValueAt(linha, 14).toString()));
               stGTA.addBatch();
           }
           
          stGTA.executeBatch();
          Conexao.getConnection().commit();
           JOptionPane.showMessageDialog(null, "Registro editado com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
                
       } catch (SQLException e) {
           JOptionPane.showMessageDialog(null, e + " Erro ao editar registro!", "Erro", 0, FormatarICO.ICObtnSair());  
          try {
              Conexao.getConnection().rollback();
              Conexao.getConnection().setAutoCommit(true);
          } catch (SQLException ex1) {
            JOptionPane.showMessageDialog(null, "Erro na Instrução RollBack, Solicitar Suporte Imediatamente ", "Erro", 0, FormatarICO.ICObtnSair()); 
          }
       }  
   } 
   
   public void excluirRegistro(Integer ID){
       Conexao.ReiniciarCon();
       String deleteNF = "delete from saida_gado where saida_gado_id = ?";
       String deleteGTA = "delete from saida_gta where saida_gta_idSaida = ?";
       
       try {
           PreparedStatement stNF = Conexao.getConnection().prepareStatement(deleteNF);
           stNF.setInt(1, ID);
           stNF.execute();
           
           PreparedStatement stGTA = Conexao.getConnection().prepareStatement(deleteGTA);
           stGTA.setInt(1, ID);
           stGTA.execute();
           Conexao.getConnection().commit();
           JOptionPane.showMessageDialog(null, "Registro excluído com sucesso!", "Registro Excluído com Sucesso", 0, FormatarICO.ICObtnOk());        
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + "Erro ao excluir registro! ", "Erro", 0, FormatarICO.ICObtnSair()); 
       }
       
   }
   
   public void preencherTabelaResumo(SaidaGadoBeans Saida, DefaultTableModel TbSaidaResumo){
       Conexao.ReiniciarCon();
       String SQLSelection = "Select saida_gado_id as ID," 
               + "saida_gado_dtSaida as DtSaida,  " 
               + "saida_gado_motivo as Motivo, " 
               + "saida_gado_nEscala as NEsc,  " 
               + "saida_gado_nNF as nNF,  "
               + "saida_gado_valorNF as ValorNF,  "
               + "saida_gado_origGado as Origem,  "
               + "saida_gado_Destino as Destino,  "
               + "saida_gado_transp as Trans,  "
               + "saida_gado_nMin as nMin,  "
               + "saida_gado_placa as Placa,  "
               + "saida_gado_tara as Tara,  "
               + "saida_gado_pesoB as PesoB,  "
               + "saida_gado_pesoL as PesoL,  "
               + "saida_gado_pesoM as PesoM,  "
               + "saida_gado_categoria as Categoria,  "
               + "saida_gado_obs as Obs,  "
               + "saida_gado_qNF as qNF, "
               + "(select ifnull(sum(saida_gta_mT),0) + ifnull(sum(saida_gta_fT),0) from saida_gta where saida_gta_idSaida = saida_gado_id) as qGTA, "
               + "saida_gado_qMin as qMin "
               + "From saida_gado "
               + "Where saida_gado_OrigGado like '%" + Saida.getpOrigem() + "%' And "
               + " saida_gado_dtSaida Between ? and ? and saida_gado_motivo like '%" + Saida.getpMotivo() + "%' and saida_gado_Destino like '%" + Saida.getpDestino() +"%' and saida_gado_transp like '%" + Saida.getpTrans() + "%' ";
       try {
           PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
           st.setString(1, Corretores.ConverterParaSQL(Saida.getDataInicial()));
           st.setString(2, Corretores.ConverterParaSQL(Saida.getDataFinal()));
           
           ResultSet rs = st.executeQuery();
           TbSaidaResumo.setNumRows(0);
           while (rs.next()){
                TbSaidaResumo.addRow(new Object[]{rs.getInt("ID"), Corretores.ConverterParaJava(rs.getString("DtSaida")), rs.getString("Motivo"), rs.getInt("NEsc"), rs.getString("nNF"), new DecimalFormat("R$ #,###,##0.00").format(rs.getDouble("ValorNF")), rs.getString("Origem"), rs.getString("Destino"), rs.getString("Trans"), rs.getString("nMin"), rs.getString("Placa"), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("Tara")), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("PesoB")), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("PesoL")), new DecimalFormat("#,##0.0 kg").format(rs.getDouble("PesoM")), rs.getString("Categoria"), rs.getInt("qNF"), rs.getInt("qGTA"), rs.getInt("qMin"), rs.getString("Obs") });
           }
       } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex + " Erro ao buscar saídas!", "Erro", 0, FormatarICO.ICObtnSair());  
       }    
   }   
   
   
}
