
package DAO;

import Beans.VendasGadoBeans;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class VendasGadoDAO {
    
    public void inserirVenda (VendasGadoBeans venda){
        Conexao.ReiniciarCon();
        String SQLInsertion = "insert into vendas_gado (vendas_gado_user, vendas_gado_dtLan, vendas_gado_dtVenda, vendas_gado_idCliente, vendas_gado_cliente, vendas_gado_categoria, vendas_gado_mercado, vendas_gado_qCab, vendas_gado_qArr, vendas_gado_reaisAr, vendas_gado_cepea, vendas_gado_observ, vendas_gado_stat) values (?,?,?,?,?,?,?,?,?,?,?,?, ?)";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertion);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(dataAtual));
            st.setString(3, Corretores.ConverterParaSQL(venda.getDataVenda()));
            st.setInt(4, venda.getIdCli());
            st.setString(5, venda.getCliente());
            st.setString(6, venda.getCategoria());
            st.setString(7, venda.getMercado());
            st.setInt(8, venda.getqCab());
            st.setDouble(9, venda.getqArrobas());
            st.setDouble(10, venda.getReaisArr());
            st.setDouble(11, venda.getCepea());
            st.setString(12, venda.getObservacao());
            st.setString(13, venda.getSituacao());
            
            st.execute();
            JOptionPane.showMessageDialog(null, "Contrato de Venda salvo com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao salvar contratos de venda", "Erro", 0, FormatarICO.ICObtnSair());
        }    
    }
    
    public void buscarVendas (DefaultTableModel TbVendas){
        Conexao.ReiniciarCon();
        String SQLSelection = "select *, "
                + " ifnull((select sum(escalaAbate_qtEscala) from escala_abate es where es.escalaAbate_idVenda = v.vendas_gado_id),0) as QEscalado,"
                + " ifnull((select sum(sg.saida_gado_qMin) from saida_gado sg, escala_abate es Where sg.saida_gado_nEscala = es.escalaAbate_id and es.escalaAbate_idVenda = v.vendas_gado_id ),0) as QEmbarcado "
                + "From vendas_gado v";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            
            TbVendas.setNumRows(0);
            while (rs.next()){
                String valorAr = new DecimalFormat("R$ ##0.00").format(rs.getDouble(11));
                String cepea = new DecimalFormat("R$ ##0.00").format(rs.getDouble(12));
                TbVendas.addRow(new Object[]{rs.getInt(1), Corretores.ConverterParaJava(rs.getString(4)), rs.getInt(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), rs.getDouble(10), valorAr, cepea, rs.getString(13), rs.getString(14), rs.getInt("QEscalado"), rs.getInt("QEmbarcado")});
            }           
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar vendas", "Erro", 0, FormatarICO.ICObtnSair());
        }
   
    }
    
    public void editarVenda(VendasGadoBeans venda){
        Conexao.ReiniciarCon();
        String SQLUpdate = "Update vendas_gado SET vendas_gado_user = ? , vendas_gado_dtLan = ?, vendas_gado_dtVenda = ?, vendas_gado_idCliente = ?, "
                + "vendas_gado_cliente = ?, vendas_gado_categoria = ?, vendas_gado_mercado = ?, vendas_gado_qCab = ?, vendas_gado_qArr = ?, "
                + " vendas_gado_reaisAr = ?, vendas_gado_cepea = ?, vendas_gado_observ = ?, vendas_gado_stat =? WHERE vendas_gado_id = ?";
        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(dataAtual));
            st.setString(3, Corretores.ConverterParaSQL(venda.getDataVenda()));
            st.setInt(4, 0);
            st.setString(5, venda.getCliente());
            st.setString(6, venda.getCategoria());
            st.setString(7, venda.getMercado());
            st.setInt(8, venda.getqCab());
            st.setDouble(9, venda.getqArrobas());
            st.setDouble(10, venda.getReaisArr());
            st.setDouble(11, venda.getCepea());
            st.setString(12, venda.getObservacao());
            st.setString(13, venda.getSituacao());
            st.setInt(14,venda.getIdVenda());
            st.executeUpdate();
            
            JOptionPane.showMessageDialog(null, "Contrato de Venda editado com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null," Erro ao buscar vendas", "Erro", 0, FormatarICO.ICObtnSair());
        } 
    }
    
    public void deletarVenda(VendasGadoBeans venda){
        Conexao.ReiniciarCon();
        String SQLDelete = "delete from vendas_gado Where vendas_gado_id = ?";
        
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLDelete);
            st.setInt(1, venda.getIdVenda());
            st.execute();
            JOptionPane.showMessageDialog(null, "Contrato de Venda excluido com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex+ " Erro ao excluir venda!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        
        
        
        
    }
    
    
    
}
