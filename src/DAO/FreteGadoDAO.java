/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.FreteGadoBeans;
import Beans.FreteTableModel;
import Beans.PagamentosBeans;
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



public class FreteGadoDAO {

    public static List<Integer> listaID = new ArrayList<>();

    public void buscarEntradas(FreteGadoBeans frete, FreteTableModel TbFrete) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select ent_gado_fis_id as ID,"
                + "ent_gado_fis_nromaneio as nRomaneio,"
                + "ent_gado_fis_nCompra as nCompra,"
                + "ifnull((Select compra_gado_comprador from compra_gado Where compra_gado_id = ent_gado_fis_nCompra),0) as Comprador, "
                + "ent_gado_fis_DataFis as dtEntrada,"
                + "ent_gado_fis_fazGTA as OrigemGTA,"
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
                + " from ent_gado_fisica "
                + " where ent_gado_fis_trans like '%" + frete.getTransportadora() + "%' and "
                + " ent_gado_fis_DataFis BETWEEN ? and ? and "
                + " ent_gado_fis_nMin like '%" + frete.getnMin() + "%' and"
                + " ent_gado_fis_placa like '%" + frete.getPlaca() + "%' And"
                + " ent_gado_fis_fazGTA like '%" + frete.getOrigem() + "%' and"
                + " ent_gado_fis_nCompra like '%" + frete.getnCompra() + "%' and "
                + " ent_gado_fis_destFisico like '%" + frete.getDestino() + "%' and "
                + " ent_gado_fis_statConferencia = " + frete.getStatusConferencia() + " and"
                + " ent_gado_fis_StatusPagto like '%" + frete.getStatusPagto() + "%'"
                + " ORDER BY ent_gado_fis_nMin asc ";

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Corretores.ConverterParaSQL(frete.getDtInicial()));
            st.setString(2, Corretores.ConverterParaSQL(frete.getDtFinal()));

            ResultSet rs = st.executeQuery();
            TbFrete.limpar();
            List<TbFreteBeans> lista = new ArrayList<>();
            while (rs.next()) {
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
                f.setSituacaoPagto(rs.getString("StatusPagto"));
                f.setDtPagto(rs.getString("IdPagto"));
                lista.add(f);

            }
            rs.close();
            st.close();
            TbFrete.addListaDeFretes(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar minutas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void atualizarFrete(FreteTableModel TbFrete) {
        Conexao.ReiniciarCon();
        String SQLUpdate = "update ent_gado_fisica SET ent_gado_fis_trans = ?, ent_gado_fis_nMin = ?, ent_gado_fis_km = ?, ent_gado_fis_vlrKM = ? ,ent_gado_fis_vlrFrete = ?, ent_gado_fis_statConferencia = ?, ent_gado_fis_StatusPagto = ? Where ent_gado_fis_id = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            for (int i = 0; i < TbFrete.getRowCount(); i++) {
                st.setString(1, (TbFrete.getValueAt(i, 7).toString()));
                st.setString(2, (TbFrete.getValueAt(i, 9).toString()));
                st.setInt(3, Integer.parseInt(TbFrete.getValueAt(i, 12).toString()));
                st.setBigDecimal(4, new BigDecimal(TbFrete.getValueAt(i, 13).toString()));
                st.setBigDecimal(5, new BigDecimal(TbFrete.getValueAt(i, 14).toString()));
                st.setBoolean(6, Boolean.parseBoolean(TbFrete.getValueAt(i, 15).toString()));
                st.setString(7, (TbFrete.getValueAt(i, 17).toString()));
                st.setInt(8, Integer.parseInt(TbFrete.getValueAt(i, 0).toString()));
                st.addBatch();
            }
            st.executeBatch();
            st.close();
            JOptionPane.showMessageDialog(null, "Minutas Editadas com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar editar minutas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void gerarPagto(FreteTableModel TbFreteTeste, PagamentosBeans pagto) {
        Conexao.ReiniciarCon();
        String SQLUpdate = "update ent_gado_fisica Set ent_gado_fis_StatusPagto = 'Pago', ent_gado_fis_idPagto = ? Where ent_gado_fis_id = ?";
        //JOptionPane.showMessageDialog(null, "Nº de Linhas" + listaID.size(), "Salvo", 0, FormatarICO.ICObtnOk());
        try {
            PreparedStatement stMin = Conexao.getConnection().prepareStatement(SQLUpdate);
            for (int i = 0; i < listaID.size(); i++) {
                stMin.setInt(1, Integer.parseInt(pagto.getID().toString()));
                stMin.setInt(2, listaID.get(i));
                stMin.addBatch();
            }
            stMin.executeBatch();
            stMin.close();
            JOptionPane.showMessageDialog(null, "Minutas Editadas com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao gerar contas a pagar do frete!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void popularListaID(FreteTableModel TbFreteTeste) {
        listaID.clear();
        for (int i = 0; i < TbFreteTeste.getRowCount(); i++) {
            if ((Boolean.parseBoolean(TbFreteTeste.getValueAt(i, 15).toString()) == true)) {
                listaID.add(Integer.parseInt(TbFreteTeste.getValueAt(i, 0).toString()));
            }
        }
    }

}
