
package DAO;

import Beans.EscalasAbateBeans;
import GUI.frmEscalasAbate;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class EscalasAbateDAO {
    
    public void preencherFazendaDestino(){
            Conexao.ReiniciarCon();
            String SQLSelection = "select * from cad_fazendas WHERE cad_fazendas_stat = 'Ativo'";
            try {
                PreparedStatement st2 = Conexao.getConnection().prepareStatement(SQLSelection);
                ResultSet rs2 = st2.executeQuery();
                while (rs2.next()){
                    frmEscalasAbate.cb_FazOrigem.addItem(rs2.getString(3));
                    frmEscalasAbate.cb_pesqFazOrigem.addItem(rs2.getString(3));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar fazendas", "Erro", 0, FormatarICO.ICObtnSair());
            }
    }
    
    public ArrayList buscarClientes(){
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_cliente_id as id_cli, cad_cliente_nome as Cliente From cad_clientes Where cad_clientes_categoria = 'Frigorifico'";
        ArrayList cliente = new ArrayList();
       
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            cliente.add("-");
            while (rs.next()){
                cliente.add(rs.getString(2));
            }
            
            return cliente;
        } catch (Exception e) {
            return cliente;
        }
    }
    
    public void inserirEscala(EscalasAbateBeans Abate, DefaultTableModel TbEscalas){
        Conexao.ReiniciarCon();
        String SQLInsertion = "insert into escala_abate (escalaAbate_idVenda, escalaAbate_origem, escalaAbate_destino, escalaAbate_dtEmbarque, escalaAbate_dtAbate, "
                + " escalaAbate_dtPagto, escalaAbate_qtEscala, escalaAbate_categoria, escalaAbate_reaisAr, escalaAbate_valorTotal, "
                + " escalaAbate_pesoMT, escalaAbate_pesoVT, escalaAbate_rend, escalaAbate_pvm, escalaAbate_pesoAr, escala_abate_obs, escala_abate_status) "
                + " values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        PreparedStatement st;
        try {
            st = Conexao.getConnection().prepareStatement(SQLInsertion);
            st.setInt(1, Abate.getnVenda());
            st.setString(2, Abate.getOrigem());
            st.setString(3, Abate.getCliente());
            st.setString(4, Corretores.ConverterParaSQL(Abate.getDtEmbarque()));
            st.setString(5, Corretores.ConverterParaSQL(Abate.getDtAbate()));
            st.setString(6, Corretores.ConverterParaSQL(Abate.getDtPagamento()));
            st.setInt(7, Abate.getQtEscala());
            st.setString(8, Abate.getCategoria());
            st.setDouble(9, Abate.getReaisAr());
            st.setDouble(10, Abate.getValorReceber());
            st.setDouble(11, Abate.getPesoMorto());
            st.setDouble(12, Abate.getPesoVivoTotal());        
            double rendimento = 0;
            if (!Abate.getPesoVivoTotal().equals(0.0)){
                rendimento =  Abate.getPesoMorto()/Abate.getPesoVivoTotal();
            }
            double pesoVivoMedio = Abate.getPesoVivoTotal()/Abate.getQtEscala();
            double pesoArroba = (Abate.getPesoMorto()/ 15)/Abate.getQtEscala();
            st.setDouble(13, rendimento);
            st.setDouble(14, pesoVivoMedio);
            st.setDouble(15, pesoArroba);
            st.setString(16, Abate.getObservacao());
            st.setString(17, Abate.getStatus());
            st.execute();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Escala salva com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao salvar escala de abate!", "Erro", 0, FormatarICO.ICObtnSair());
        }   
    }
    
    public void preencherTabela (EscalasAbateBeans Escalas,DefaultTableModel TbEscalas, String SQLWhere){
        Conexao.ReiniciarCon();
        
        String SQLSelection = "Select *,"
                + " (select sum(sg.saida_gado_qMin) from saida_gado sg WHERE sg.saida_gado_nEscala = escalaAbate_id) as qEmbarcado,"
                + " (select SUM(rec_valorTotal) from recebimentos rec where rec_nEscala = escalaAbate_id) as ValorRecebido  "
                + " from escala_abate Where " + Escalas.getTipoDataPesquisa() + " BETWEEN ? And ?" + SQLWhere;
                          
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Corretores.ConverterParaSQL(Escalas.getPesqDataInicial()));
            st.setString(2, Corretores.ConverterParaSQL(Escalas.getPesqDataFinal()));
            ResultSet rs = st.executeQuery();
            TbEscalas.setNumRows(0);
            while (rs.next()){
                String dataEmbarque = Corretores.ConverterParaJava(rs.getString(5));
                String dataAbate = Corretores.ConverterParaJava(rs.getString(6));
                String dataPagto = Corretores.ConverterParaJava(rs.getString(7));
                String Observacao;
                if (rs.getString(17) == null) {
                    Observacao = "";
                } else {
                    Observacao = rs.getString(17);
                }   
                TbEscalas.addRow(new Object[]{rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getString(4), 
                    dataEmbarque, dataAbate, dataPagto, rs.getInt(8), rs.getInt("qEmbarcado"), 
                    rs.getString(9), new DecimalFormat("R$ ##0.00").format(rs.getDouble(10)), 
                    new DecimalFormat("###,##0.00 kg").format(rs.getDouble(12)), 
                    new DecimalFormat("###,##0.00 kg").format(rs.getDouble(13)), 
                    new DecimalFormat("#0.00 %").format(rs.getDouble(14)), 
                    new DecimalFormat("##0.00 kg").format(rs.getDouble(15)), 
                    new DecimalFormat("##0.00 @").format(rs.getDouble(16)), 
                    new DecimalFormat("R$ ###,###,##0.00").format(rs.getDouble(11)), Corretores.ConverterDoubleReais(rs.getDouble("ValorRecebido")) ,Observacao, rs.getString(18)});
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar escalas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }
    
    public void editarEscala(EscalasAbateBeans Abate){
        Conexao.ReiniciarCon();
        String SQLUpdate = "update escala_abate SET escalaAbate_idVenda = ?, escalaAbate_origem = ?, escalaAbate_destino = ?, escalaAbate_dtEmbarque = ?, escalaAbate_dtAbate = ?, escalaAbate_dtPagto = ?, " +
                            "escalaAbate_qtEscala = ?, escalaAbate_categoria = ?, escalaAbate_reaisAr = ?, escalaAbate_valorTotal = ?, escalaAbate_pesoMT = ?, escalaAbate_pesoVT = ?, " +
                            "escalaAbate_rend = ?, escalaAbate_pvm = ?, escalaAbate_pesoAr = ?, escala_abate_obs = ?, escala_abate_status = ? WHERE escalaAbate_id = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            st.setInt(1, Abate.getnVenda());
            st.setString(2, Abate.getOrigem());
            st.setString(3, Abate.getCliente());
            st.setString(4, Corretores.ConverterParaSQL(Abate.getDtEmbarque()));
            st.setString(5, Corretores.ConverterParaSQL(Abate.getDtAbate()));
            st.setString(6, Corretores.ConverterParaSQL(Abate.getDtPagamento()));
            st.setInt(7, Abate.getQtEscala());
            st.setString(8, Abate.getCategoria());
            st.setDouble(9, Abate.getReaisAr());
            st.setDouble(10, Abate.getValorReceber());
            st.setDouble(11, Abate.getPesoMorto());
            st.setDouble(12, Abate.getPesoVivoTotal());        
            double rendimento = 0;
            if (!Abate.getPesoVivoTotal().equals(0.0)){
                rendimento =  Abate.getPesoMorto()/Abate.getPesoVivoTotal();
            }
            double pesoVivoMedio = Abate.getPesoVivoTotal()/Abate.getQtEscala();
            double pesoArroba = (Abate.getPesoMorto()/ 15)/Abate.getQtEscala();
            st.setDouble(13, rendimento);
            st.setDouble(14, pesoVivoMedio);
            st.setDouble(15, pesoArroba);
            st.setString(16, Abate.getObservacao());
            st.setString(17, Abate.getStatus());
            st.setInt(18, Abate.getnEscala());
            st.executeUpdate();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Escala editada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao editar Escala!", "Erro", 0, FormatarICO.ICObtnSair());
        } 
    }
   
    public void excluirEscala(EscalasAbateBeans Abate){
        Conexao.ReiniciarCon();
        String SQLDelete = "delete from escala_abate WHERE escalaAbate_id = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLDelete);
            st.setInt(1, Abate.getnEscala());
            st.execute();
            Conexao.getConnection().commit();
            st.close();
            JOptionPane.showMessageDialog(null, "Escala exluida com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao excluir Escala!", "Erro", 0, FormatarICO.ICObtnSair());
        }      
    }

    
    
    
    
}
