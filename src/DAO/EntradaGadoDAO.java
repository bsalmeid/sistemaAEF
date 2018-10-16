
package DAO;


import Beans.EntradaGadoBeans;
import Utilitarios.Corretores;
import GUI.frmEntradaGado;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class EntradaGadoDAO {
     
    public void pmfazenda(){
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from pm_user_fazenda where  login_usuario = ?";
        String sql = "select * from cad_fazendas WHERE cad_fazendas_stat = 'Ativo'";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            PreparedStatement st2 = Conexao.getConnection().prepareStatement(sql);
            st.setString(1, UsuarioLogado);
            ResultSet rs = st.executeQuery();
            ResultSet rs2 = st2.executeQuery();
            
            frmEntradaGado.cb_fazenda.addItem("-");
            while (rs.next()) {
                if (rs.getInt(4) == 1){
                    frmEntradaGado.cb_fazenda.addItem(rs.getString(2));       
                }
            }
            
            frmEntradaGado.cb_destinoFisico.addItem("-");
            frmEntradaGado.cb_destinoGTA.addItem("-");
            while (rs2.next()){
                frmEntradaGado.cb_destinoFisico.addItem(rs2.getString(3));
                frmEntradaGado.cb_destinoGTA.addItem(rs2.getString(3));
                frmEntradaGado.cb_pesqFazenda.addItem(rs2.getString(3));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar permissões", "Erro", 0, FormatarICO.ICObtnSair());
        }
    } 
    
    public Integer buscarUltimo(){
        Conexao.ReiniciarCon();
        String SQLSelection = "select max(ent_gado_romaneio) From ent_gado_nf";
        int Ultimo = 0;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            
            while(rs.next()){
                Ultimo = rs.getInt(1) + 1;     
            }       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Buscar Último Romaneio", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return Ultimo;
        
    }
    
    public void inserirNF(EntradaGadoBeans entGado, DefaultTableModel TbNF){
        Conexao.ReiniciarCon(); 
        String SQLInsertion = "insert into ent_gado_nf (ent_gado_romaneio, ent_gado_usuario, ent_gado_fazenda, ent_gad_data_lan, ent_gado_data_nf, ent_gado_comprador, ent_gado_produtor, ent_gado_cpf, ent_gado_fazOrigem, ent_gado_ins_est, ent_gado_ncompra, ent_gado_motivo, ent_gado_NumNF, ent_gado_QNF, ent_gado_valor_nf, ent_gado_valor_cab) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertion);
            Conexao.getConnection().setAutoCommit(false);
            if (entGado.getMetodoInsercao().equals("NovoRom")) {
                st.setInt(1, buscarUltimo());
            } else if (entGado.getMetodoInsercao().equals("RomExist")){
                st.setInt(1, entGado.getCodigoROM());
            }
            st.setString(2, UsuarioLogado);
            st.setString(3, entGado.getFazendaEnt());
            st.setString(4, Corretores.ConverterParaSQL(dataAtual));
            st.setString(5, Corretores.ConverterParaSQL(entGado.getDataNF()));
            st.setString(6, entGado.getComprador());
            st.setString(7, entGado.getProdutor());
            st.setString(8, entGado.getCPFProdutor());
            st.setString(9, entGado.getFazendaOrigem());
            st.setString(10, entGado.getInscricao());
            st.setInt(11, Integer.parseInt(entGado.getNumCompra()));
            st.setString(12, entGado.getMotivo());
            st.setString(13, entGado.getNumNf());
            st.setInt(14, Integer.parseInt(entGado.getQuantNF()));
            st.setFloat(15, Float.parseFloat(entGado.getValorNF()));
            st.setFloat(16, Float.parseFloat(entGado.getValorNF()) / Integer.parseInt(entGado.getQuantNF()));
            
            boolean retorno =  st.execute();
            if (retorno == true){
                Conexao.getConnection().commit();
                JOptionPane.showMessageDialog(null, "Nota Fiscal salva com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                JOptionPane.showMessageDialog(null, "Erro na Instrução RollBack, Solicitar Suporte Imediatamente ", "Erro", 0, FormatarICO.ICObtnSair());
            }
        }     
    }
    
    public void buscarRomaneio(EntradaGadoBeans entGado, DefaultTableModel TbNF){
         Conexao.ReiniciarCon();
        String SQLSelection = "select * from ent_gado_nf WHERE ent_gado_romaneio = ?";
        
        try {
             PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
             
             st.setInt(1, entGado.getCodigoROM());
             ResultSet rs = st.executeQuery();

            TbNF.setNumRows(0);
            if (rs.next()){
                if (rs.getString(4).equals(entGado.getFazendaEnt())){
                    entGado.setMotivo(rs.getString(13));
                    if (rs.getString(13).equals("Compra")){
                        entGado.setComprador(rs.getString(7));
                        entGado.setNumCompra(rs.getString(12));
                    }
                }
            }

            do {
                if (rs.getString(4).equals(entGado.getFazendaEnt())) {
                    TbNF.addRow(new Object[]{rs.getInt(1),rs.getInt(2), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), Corretores.ConverterParaJava(rs.getString(6)), rs.getString(14), rs.getInt(15), rs.getDouble(16), rs.getDouble(17)});
                  
                } else {
                    JOptionPane.showMessageDialog(null, "Você não possúi acesso a esta propriedade!", "Erro", 0, FormatarICO.ICObtnSair());
                    break;
                }
            } while (rs.next());
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "Erro", 0, FormatarICO.ICObtnSair());
        }
            
    }
  
    public void editarNF(EntradaGadoBeans entGado){
        Conexao.ReiniciarCon();
        String SQLUpdate = "update ent_gado_nf SET ent_gado_romaneio = ?,  ent_gado_usuario = ?, ent_gado_fazenda = ?, ent_gad_data_lan = ?, ent_gado_data_nf = ?, ent_gado_comprador = ?, ent_gado_produtor = ?, ent_gado_cpf = ?, ent_gado_fazOrigem = ?, ent_gado_ins_est = ?, ent_gado_ncompra = ?, ent_gado_motivo = ?, ent_gado_NumNF = ?, ent_gado_QNF = ?, ent_gado_valor_nf = ?, ent_gado_valor_cab = ? Where ent_gado_id = ? ";
            
        PreparedStatement st;
        try {
            st = Conexao.getConnection().prepareStatement(SQLUpdate);
            st.setInt(1, entGado.getCodigoROM());
            st.setString(2, UsuarioLogado);
            st.setString(3, entGado.getFazendaEnt());
            st.setString(4, Corretores.ConverterParaSQL(dataAtual));
            st.setString(5, Corretores.ConverterParaSQL(entGado.getDataNF()));
            st.setString(6, entGado.getComprador());
            st.setString(7, entGado.getProdutor());
            st.setString(8, entGado.getCPFProdutor());
            st.setString(9, entGado.getFazendaOrigem());
            st.setString(10, entGado.getInscricao());
            st.setInt(11, Integer.parseInt(entGado.getNumCompra()));
            st.setString(12, entGado.getMotivo());
            st.setString(13, entGado.getNumNf());
            st.setInt(14, Integer.parseInt(entGado.getQuantNF()));
            st.setFloat(15, Float.parseFloat(entGado.getValorNF()));
            st.setFloat(16, Float.parseFloat(entGado.getValorNF()) / Integer.parseInt(entGado.getQuantNF()));
            st.setInt(17, entGado.getID());
            
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nota Fiscal editada com sucesso com sucesso!", "Executado", 1, FormatarICO.ICObtnSair());
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        } 
    }
    
    public void inserirGTA(EntradaGadoBeans entGado){
         Conexao.ReiniciarCon();
        String SQLInsertion = "insert into ent_gado_fisica (ent_gado_fis_user, ent_gado_fis_dtLan, ent_gado_fis_nromaneio, ent_gado_fis_data_gta, ent_gado_fis_ngta, ent_gado_fis_quantGTA, ent_gado_fis_fazGTA, ent_gado_fis_MuniGTA, ent_gado_fis_tipoTrans, ent_gado_fis_destGTA, ent_gado_fis_m12, ent_gado_fis_m24, ent_gado_fis_m36, ent_gado_fis_mm36, ent_gado_fis_m_total, ent_gado_fis_f12, ent_gado_fis_f24, ent_gado_fis_f36, ent_gado_fis_fm36, ent_gado_fis_f_total, ent_gado_fis_DataFis, ent_gado_fis_placa, ent_gado_fisi_tara, ent_gado_fis_pesoB, ent_gado_fis_pesoL, ent_gado_fis_quantCab, ent_gado_fis_QtOrigem, ent_gado_fis_pesoM, ent_gado_fis_km, ent_gado_fis_nRefugo, ent_gado_fis_destFisico, ent_gado_fis_cat, ent_gado_fis_trans, ent_gado_fis_nMin, ent_gado_fis_nCompra ) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st;
        
        try {
            st = Conexao.getConnection().prepareStatement(SQLInsertion);
            
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(dataAtual));
            st.setInt(3, entGado.getCodigoROM());
            st.setString(4, Corretores.ConverterParaSQL(entGado.getDataGTA()));
            System.out.println(entGado.getNumGTA());
            st.setString(5, entGado.getNumGTA());
            st.setInt(6, Integer.parseInt(entGado.getQuantGTA()));
            st.setString(7, entGado.getFazendaOrigemGTA());
            st.setString(8, entGado.getMunicipioGTA());
            st.setString(9, entGado.getTipoTransp());
            st.setString(10, entGado.getDestinoGTA());
            st.setInt(11, Integer.parseInt(entGado.getMate12()));
            st.setInt(12, Integer.parseInt(entGado.getMate24()));
            st.setInt(13, Integer.parseInt(entGado.getMate36()));
            st.setInt(14, Integer.parseInt(entGado.getMateM36()));
            st.setInt(15, Integer.parseInt(entGado.getMTotal()));
            st.setInt(16, Integer.parseInt(entGado.getFate12()));
            st.setInt(17, Integer.parseInt(entGado.getFate24()));
            st.setInt(18, Integer.parseInt(entGado.getFate36()));
            st.setInt(19, Integer.parseInt(entGado.getFateM36()));
            st.setInt(20, Integer.parseInt(entGado.getFTotal()));
            st.setString(21, Corretores.ConverterParaSQL(entGado.getDataEntradaFisica()));
            st.setString(22, entGado.getPlaca());
            st.setDouble(23, Float.parseFloat(entGado.getTara()));
            st.setDouble(24, Float.parseFloat(entGado.getPesoBruto()));
            st.setDouble(25, Float.parseFloat(entGado.getPesoBruto()) - Float.parseFloat(entGado.getTara()));
            st.setInt(26, Integer.parseInt(entGado.getQuantCab()));
            st.setInt(27, entGado.getQuantCabOrigem());
           
            if (Integer.parseInt(entGado.getQuantCab())== 0) {
                st.setDouble(28, 0);
            } else{
                st.setDouble(28, (Float.parseFloat(entGado.getPesoBruto()) - Float.parseFloat(entGado.getTara()))/ Integer.parseInt(entGado.getQuantCab()));
            }
            
            st.setInt(29, Integer.parseInt(entGado.getQuilometro()));
            st.setString(30, entGado.getNumRefugo());
            st.setString(31, entGado.getDestinoFisico());
            st.setString(32, entGado.getCategoria());
            st.setString(33, entGado.getTransportadora());
            st.setString(34, entGado.getNumMinuta());
            st.setInt(35, Integer.parseInt(entGado.getNumCompra()));
            
            st.execute();
            JOptionPane.showMessageDialog(null, "GTA e Minutas salva com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        } 
    }
    
    public void editarGTA(EntradaGadoBeans entGado){
         Conexao.ReiniciarCon();
        String SQLUpdate = "update ent_gado_fisica SET ent_gado_fis_user = ?, ent_gado_fis_dtLan = ?, ent_gado_fis_nromaneio = ?, ent_gado_fis_data_gta = ?, ent_gado_fis_ngta = ?, ent_gado_fis_quantGTA = ?, ent_gado_fis_fazGTA = ?, ent_gado_fis_MuniGTA = ?, ent_gado_fis_tipoTrans = ?, ent_gado_fis_destGTA = ?, ent_gado_fis_m12 = ?, ent_gado_fis_m24 = ?, ent_gado_fis_m36 = ?, ent_gado_fis_mm36 = ?, ent_gado_fis_m_total = ?, ent_gado_fis_f12 = ?, ent_gado_fis_f24 = ?, ent_gado_fis_f36 = ?, ent_gado_fis_fm36 = ?, ent_gado_fis_f_total = ?, ent_gado_fis_DataFis = ?, ent_gado_fis_placa = ?, ent_gado_fisi_tara = ?, ent_gado_fis_pesoB = ?, ent_gado_fis_pesoL = ?, ent_gado_fis_quantCab = ?, ent_gado_fis_QtOrigem = ?, ent_gado_fis_pesoM = ?, ent_gado_fis_km = ?, ent_gado_fis_nRefugo = ?, ent_gado_fis_destFisico = ?, ent_gado_fis_cat = ?, ent_gado_fis_trans = ?, ent_gado_fis_nMin = ?, ent_gado_fis_nCompra = ? Where ent_gado_fis_id = ? ";
        PreparedStatement st;
        try {
            st = Conexao.getConnection().prepareStatement(SQLUpdate);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(dataAtual));
            st.setInt(3, entGado.getCodigoROM());
            st.setString(4, Corretores.ConverterParaSQL(entGado.getDataGTA()));
            st.setString(5, entGado.getNumGTA());
            st.setInt(6, Integer.parseInt(entGado.getQuantGTA()));
            st.setString(7, entGado.getFazendaOrigemGTA());
            st.setString(8, entGado.getMunicipioGTA());
            st.setString(9, entGado.getTipoTransp());
            st.setString(10, entGado.getDestinoGTA());
            st.setInt(11, Integer.parseInt(entGado.getMate12()));
            st.setInt(12, Integer.parseInt(entGado.getMate24()));
            st.setInt(13, Integer.parseInt(entGado.getMate36()));
            st.setInt(14, Integer.parseInt(entGado.getMateM36()));
            st.setInt(15, Integer.parseInt(entGado.getMTotal()));
            st.setInt(16, Integer.parseInt(entGado.getFate12()));
            st.setInt(17, Integer.parseInt(entGado.getFate24()));
            st.setInt(18, Integer.parseInt(entGado.getFate36()));
            st.setInt(19, Integer.parseInt(entGado.getFateM36()));
            st.setInt(20, Integer.parseInt(entGado.getFTotal()));
            st.setString(21, Corretores.ConverterParaSQL(entGado.getDataEntradaFisica()));
            st.setString(22, entGado.getPlaca());
            st.setDouble(23, Float.parseFloat(entGado.getTara()));
            st.setDouble(24, Float.parseFloat(entGado.getPesoBruto()));
            st.setDouble(25, Float.parseFloat(entGado.getPesoBruto()) - Float.parseFloat(entGado.getTara()));
            st.setInt(26, Integer.parseInt(entGado.getQuantCab()));
            st.setInt(27, entGado.getQuantCabOrigem());
            if (Integer.parseInt(entGado.getQuantCab()) > 0){
                st.setDouble(28, (Float.parseFloat(entGado.getPesoBruto()) - Float.parseFloat(entGado.getTara()))/ Integer.parseInt(entGado.getQuantCab()));
            } else {
                st.setDouble(28, 0.00);
            }
            st.setInt(29, Integer.parseInt(entGado.getQuilometro()));
            st.setString(30, entGado.getNumRefugo());
            st.setString(31, entGado.getDestinoFisico());
            st.setString(32, entGado.getCategoria());
            st.setString(33, entGado.getTransportadora());
            st.setString(34, entGado.getNumMinuta());
            st.setInt(35, Integer.parseInt(entGado.getNumCompra()));
            st.setInt(36, entGado.getID());
                        
            int retorno =  st.executeUpdate();
            if (retorno != 0) {
               JOptionPane.showMessageDialog(null, "GTA e Minuta editada com sucesso com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
            }
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        }
        
    }
    
    public void preencherTbGTA(EntradaGadoBeans entGado, DefaultTableModel TbGTA) {
         Conexao.ReiniciarCon();
        String SQLSelection = "select * from ent_gado_fisica WHERE ent_gado_fis_nromaneio = ?";
        PreparedStatement st;
        try {
            st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setInt(1, entGado.getCodigoROM());
            ResultSet rs = st.executeQuery();

            String nGTA;
            TbGTA.setNumRows(0);
            while (rs.next()) {
                if (rs.getString(7) == null) {
                    nGTA = "";
                } else {
                    nGTA = rs.getString(7);
                }
                TbGTA.addRow(new Object[]{rs.getInt(1), nGTA, Corretores.ConverterParaJava(rs.getString(6)), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getInt(8), rs.getInt(13), rs.getInt(14), rs.getInt(15), rs.getInt(16), rs.getInt(17), rs.getInt(18), rs.getInt(19), rs.getInt(20), rs.getInt(21), rs.getInt(22), rs.getString(24), rs.getDouble(25), rs.getDouble(26), rs.getDouble(27), rs.getInt(28), rs.getInt(29), rs.getDouble(30), rs.getString(32), rs.getString(33), Corretores.ConverterParaJava(rs.getString(23)), rs.getString(34), rs.getString(35), rs.getString(36), rs.getInt(31), rs.getInt(4)});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        }
    }
    
    public void deletarNF(EntradaGadoBeans entGado){
         Conexao.ReiniciarCon();
        String SQLDelete = "delete from ent_gado_nf Where ent_gado_id = ?";
        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLDelete);
            st.setInt(1, entGado.getID());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "Nota Fiscal excluída com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        }    
    }
      
    public void deletarGTA(EntradaGadoBeans entGado){
         Conexao.ReiniciarCon();
        String SQLDelete = "delete from ent_gado_fisica Where ent_gado_fis_id = ?";
        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLDelete);
            st.setInt(1, entGado.getID());
            st.executeUpdate();
            JOptionPane.showMessageDialog(null, "GTA excluído com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        } 
    }
    
    public void PesquisarRomaneio(EntradaGadoBeans entGado, DefaultTableModel TbPesquisa){
       Conexao.ReiniciarCon();
       String SQLSelection = "Select nf.ent_gado_romaneio as Romaneio, nf.ent_gado_fazenda as Destino, " +
                            "nf.ent_gado_fazOrigem as FazOrigem, " +
                            "ifnull((Select MAX(fis.ent_gado_fis_DataFis) From ent_gado_fisica fis Where fis.ent_gado_fis_nromaneio = ent_gado_romaneio), (select MAX(ent_gado_data_nf) from ent_gado_nf Where ent_gado_romaneio = nf.ent_gado_romaneio)) as DataEntrada, " +
                            "nf.ent_gado_motivo as Motivo, nf.ent_gado_ncompra as NCompra, nf.ent_gado_comprador as Comprador, " +
                            "nf.ent_gado_produtor as Produtor, " +
                            "@VNF:=(Select sum(ent_gado_valor_nf) From ent_gado_nf Where ent_gado_romaneio = nf.ent_gado_romaneio) as ValorNF, " +
                            "@QNF:= (Select sum(ent_gado_QNF) From ent_gado_nf Where ent_gado_romaneio = nf.ent_gado_romaneio) as QNF, " +
                            "ifnull(Format(@VNF/@QNF,2), 0) as PrecoMedio, " +
                            "ifnull((Select SUM(fis.ent_gado_fis_quantGTA) from ent_gado_fisica fis Where fis.ent_gado_fis_nromaneio = ent_gado_romaneio),0) as GTATotal, " +
                            "ifnull((Select SUM(fis.ent_gado_fis_quantCab) from ent_gado_fisica fis Where fis.ent_gado_fis_nromaneio = ent_gado_romaneio),0) as MinTotal  " +
                            "From ent_gado_nf nf, ent_gado_fisica fis " +
                            "Where fis.ent_gado_fis_nromaneio = ent_gado_romaneio AND " +
                            "ifnull((Select MAX(fis.ent_gado_fis_DataFis) From ent_gado_fisica fis Where fis.ent_gado_fis_nromaneio = ent_gado_romaneio), (select MAX(ent_gado_data_nf) from ent_gado_nf Where ent_gado_romaneio = nf.ent_gado_romaneio)) " +
                            "between ? AND ? " +
                            "And nf.ent_gado_fazenda Like '%" + entGado.getPesqFazenda() +"%' " +
                            "And nf.ent_gado_motivo Like '%" + entGado.getPesqMotivo()  + "%' " +
                            "And nf.ent_gado_comprador Like '%" + entGado.getPesqComprador() + "%' " +
                            "And nf.ent_gado_ncompra Like '%" + entGado.getPesqNcompra() + "%' " +
                            "And nf.ent_gado_produtor Like '%" + entGado.getPesqProdutor()  + "%' " +
                            "And nf.ent_gado_fazOrigem Like '%" + entGado.getPesqFazOrigem()  + "%' " +
                            "GROUP BY nf.ent_gado_romaneio "; 
       
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Corretores.ConverterParaSQL(entGado.getPesqDataInicial()));
            st.setString(2, Corretores.ConverterParaSQL(entGado.getPesqDataFinal()));
            ResultSet rs = st.executeQuery();
            TbPesquisa.setNumRows(0);
            while (rs.next()){
                TbPesquisa.addRow(new Object[]{rs.getInt(1), rs.getString(2),rs.getString(3),Corretores.ConverterParaJava(rs.getString(4)), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getString(8), new DecimalFormat("R$ #,##0.00").format(rs.getDouble(9)), rs.getString(11), rs.getInt(10), rs.getInt(12), rs.getInt(13)}); 
            }         
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Não foi possível realizar a pesquisa!", "Erro", 0, FormatarICO.ICObtnSair());
        }
 
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
    
    
    
    
    
    
}
