/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;


import Beans.FreteGadoBeans;
import Beans.FreteTableModel;
import Beans.TbFreteBeans;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RelatoriosGadoDAO {
    
    public void consultarEntradas(FreteGadoBeans beans, FreteTableModel TbEntrada, String SQLWhere){
        Conexao.ReiniciarCon();
        String SQLSelection =  "select ent_gado_fis_id as ID,"
                + "ent_gado_fis_nromaneio as nRomaneio,"
                + "ent_gado_fis_nCompra as nCompra,"
                + "ifnull((select compra_gado_comprador from compra_gado where compra_gado_id = ent_gado_fis_nCompra),0) as Comprador, "
                + "ent_gado_fis_DataFis as dtEntrada,"
                + "nf.ent_gado_fazOrigem as OrigemGTA,"
                + "ent_gado_fis_destFisico as Destino,"
                + "ent_gado_fis_trans as Trans,"
                + "ent_gado_fis_placa as Placa,"
                + "ent_gado_fis_nMin as nMin,"
                + "ent_gado_fis_quantCab as Qmin,"
                + "ent_gado_fis_QtOrigem as QOrigem,"
                + "ent_gado_fis_km as Km,"
                + "ent_gado_fis_vlrKM as VlrKM,"
                + "ent_gado_fis_vlrFrete as VlrFrete,"
                + "ent_gado_fis_statConferencia as StatusConf,"
                + "ent_gado_fis_StatusPagto as StatusPagto,"
                + "ent_gado_fis_idPagto as IdPagto"
                + " FROM ent_gado_fisica, (select Distinct ent_gado_romaneio , ent_gado_motivo, IF(ent_gado_motivo like '%Compra%', (select compra_gado_fornecedor from compra_gado where compra_gado_id = ent_gado_ncompra) , ent_gado_fazOrigem ) as ent_gado_fazOrigem from  ent_gado_nf) nf"
                + " WHERE ent_gado_fis_DataFis BETWEEN ? and ? and nf.ent_gado_romaneio = ent_gado_fis_nromaneio "
                + SQLWhere 
                +  " ORDER BY nRomaneio asc ";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Corretores.ConverterParaSQL(beans.getDtInicial()));
            st.setString(2, Corretores.ConverterParaSQL(beans.getDtFinal()));
            ResultSet rs = st.executeQuery();
            TbEntrada.limpar();
            List<TbFreteBeans> lista = new ArrayList<>();
            while(rs.next()){
              TbFreteBeans f = new TbFreteBeans();
              f.setID(rs.getInt("ID"));
              f.setnRomaneio(rs.getInt("nRomaneio"));
              f.setnCompra(rs.getInt("nCompra"));
              f.setComprador(rs.getString("Comprador"));
              f.setDtEntrada(Corretores.ConverterParaJava(rs.getString("dtEntrada")));
              f.setOrigemGTA(rs.getString("OrigemGTA"));
              f.setDestino(rs.getString("Destino"));
              f.setTransportadora(rs.getString("Trans"));
              f.setPlaca(rs.getString("Placa"));
              f.setnMIN(rs.getString("nMin"));
              f.setqMin(rs.getInt("Qmin"));
              f.setqOrigem(rs.getInt("QOrigem"));        
              f.setKM(rs.getInt("Km"));
              f.setVlrKM(rs.getBigDecimal("VlrKM"));
              f.setVlrFrete(rs.getBigDecimal("VlrFrete"));
              f.setStatusAtual(rs.getBoolean("StatusConf"));
              f.setStatusDB(rs.getBoolean("StatusConf"));
              f.setSituacaoPagto("");
              f.setDtPagto("");
              lista.add(f);
            }
            TbEntrada.addListaDeFretes(lista);
            
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar minutas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        
        
        
    }
    
}
