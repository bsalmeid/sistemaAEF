/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.RelatoriosFinanceirosBeans;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class RelatoriosFinanceirosDAO {

    public void relatorioPlanoContas(DefaultTableModel TbPlanoContas, RelatoriosFinanceirosBeans beans, String SQLFazendas) {
        Conexao.ReiniciarCon();
        double VlrTotal = 0;
        String SQLVlrTotal = "select Sum(clas_valor) as VlrTotal from escrituracao_classificacao ec, pagamentos pg where clas_idpagto = pg.pagto_id and pg.pagto_DataPagto between ? and ?";
        String SQLSelect = "select  "
                + "ec.clas_planoconta as Conta, "
                + "ec.clas_descconta as Descricao, \n"
                + beans.getSQL_Plano_Conta_Completo()
                + " SUM(ec.clas_valor) as Valor  "
                + "from escrituracao_classificacao ec, pagamentos pg  "
                + "where ec.clas_idpagto = pg.pagto_id and pg.pagto_DataPagto between ? and ? " + SQLFazendas
                + " group by ec.clas_planoconta, ec.clas_descconta " + beans.getGROUPBY_SQL_PlanoConta()
                + " order by ec.clas_planoconta;";

        try {
            PreparedStatement stVlrTotal = Conexao.getConnection().prepareStatement(SQLVlrTotal);
            stVlrTotal.setString(1, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stVlrTotal.setString(2, Corretores.ConverterParaSQL(beans.getDtFinal()));
            ResultSet rsVlrTotal = stVlrTotal.executeQuery();
            while (rsVlrTotal.next()) {
                VlrTotal = rsVlrTotal.getDouble("VlrTotal");
            }

            PreparedStatement stSelect = Conexao.getConnection().prepareStatement(SQLSelect);
            stSelect.setString(1, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stSelect.setString(2, Corretores.ConverterParaSQL(beans.getDtFinal()));
            ResultSet rs = stSelect.executeQuery();
            TbPlanoContas.setNumRows(0);

            String descricaoDespesa = "";
            int Idpagto = 0;
            String Fazenda = "";
            while (rs.next()) {
                if (!beans.getSQL_Plano_Conta_Completo().equals("")) {
                    descricaoDespesa = rs.getString("DescDespesa");
                    Idpagto = rs.getInt("IDPagto");
                    Fazenda = rs.getString("Fazenda");
                }
                TbPlanoContas.addRow(new Object[]{rs.getInt("Conta"), rs.getString("Descricao"), Idpagto, descricaoDespesa, Fazenda,
                    new DecimalFormat("R$ ###,###,##0.00").format(rs.getDouble("Valor")),
                    new DecimalFormat("0.00 %").format(rs.getDouble("Valor") / VlrTotal)});
            }

            double TotalContas = 0;
            double TotalPorcentagem = 0;
            for (int i = 0; i < TbPlanoContas.getRowCount(); i++) {
                TotalContas += Corretores.ConverterReaisDouble(TbPlanoContas.getValueAt(i, 5).toString());
                TotalPorcentagem += Double.parseDouble(TbPlanoContas.getValueAt(i, 6).toString().replace("%", "").replace(",", "."));
            }
            TbPlanoContas.addRow(new Object[]{"", "Total", "", "", "", new DecimalFormat("R$ ###,###,##0.00").format(TotalContas),
                new DecimalFormat("0.00 %").format(TotalPorcentagem / 100)});

            rs.close();
            rsVlrTotal.close();
            stVlrTotal.close();
            stSelect.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao gerar relatório", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {

        }

    }

    public void relatorioContasDeOrigem(DefaultTableModel TbContaOrigem, RelatoriosFinanceirosBeans beans) {
        Conexao.ReiniciarCon();
        String SQLValorTotal = "select SUM(pagto_valorPagto) as Total from pagamentos where pagto_DataPagto between ? and ? ";

        String SQLSelect = "select "
                + "pagto_idContaOrigem as IDConta, "
                + "(Select cad_conta_descricao from cad_contabancaria where cad_conta_id = pagto_idContaOrigem) as Origem,"
                + "Sum(pagto_valorPagto) as Valor, "
                + "(Sum(pagto_valorPagto)/ ?) as Porcentagem "
                + "from pagamentos where pagto_DataPagto between ? and ? "
                + "group by pagto_idContaOrigem;";

        try {
            PreparedStatement stValorTotal = Conexao.getConnection().prepareStatement(SQLValorTotal);
            stValorTotal.setString(1, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stValorTotal.setString(2, Corretores.ConverterParaSQL(beans.getDtFinal()));
            ResultSet rsTotal = stValorTotal.executeQuery();
            double valorTotal = 0;
            while (rsTotal.next()) {
                valorTotal = rsTotal.getDouble("Total");
            }

            PreparedStatement stConta = Conexao.getConnection().prepareStatement(SQLSelect);
            stConta.setDouble(1, valorTotal);
            stConta.setString(2, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stConta.setString(3, Corretores.ConverterParaSQL(beans.getDtFinal()));
            TbContaOrigem.setNumRows(0);
            ResultSet rs = stConta.executeQuery();
            while (rs.next()) {
                TbContaOrigem.addRow(new Object[]{rs.getInt("IDConta"), rs.getString("Origem"), new DecimalFormat("R$ ###,###,##0.00").format(rs.getDouble("Valor")), new DecimalFormat("#0.00 %").format(rs.getDouble("Porcentagem"))});
            }
            double TotalContas = 0;
            double TotalPorcentagem = 0;
            for (int i = 0; i < TbContaOrigem.getRowCount(); i++) {
                TotalContas += Corretores.ConverterReaisDouble(TbContaOrigem.getValueAt(i, 2).toString());
                TotalPorcentagem += Double.parseDouble(TbContaOrigem.getValueAt(i, 3).toString().replace("%", "").replace(",", "."));
            }

            TbContaOrigem.addRow(new Object[]{"", "Total", new DecimalFormat("R$ ###,###,##0.00").format(TotalContas), new DecimalFormat("#0.00 %").format(TotalPorcentagem / 100)});

            stConta.close();
            rs.close();
            stValorTotal.close();
            rsTotal.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao gerar relatório", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void relatorioDespesasFazendas(DefaultTableModel TbFazenda, RelatoriosFinanceirosBeans beans) {
        String SQLTotal = "select SUM(pagto_valorPagto) as Total from pagamentos where pagto_DataPagto between ? and ? ";
        String SQLFazendas = "select "
                + "(Select cad_fazendas_id from cad_fazendas where cad_fazendas_nome = ec.clas_fazenda) as IDFazenda, "
                + "ec.clas_fazenda as Fazenda, "
                + "SUM(ec.clas_valor) as Valor, "
                + "(SUM(ec.clas_valor) / ?) as Porcentagem "
                + "from escrituracao_classificacao ec, pagamentos pg "
                + "where ec.clas_idpagto = pg.pagto_id and pg.pagto_DataPagto between ? and ? "
                + "group by Fazenda "
                + "order by IDFazenda";

        try {
            PreparedStatement stTotal = Conexao.getConnection().prepareStatement(SQLTotal);
            stTotal.setString(1, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stTotal.setString(2, Corretores.ConverterParaSQL(beans.getDtFinal()));
            ResultSet rsTotal = stTotal.executeQuery();
            double valorTotal = 0;
            while (rsTotal.next()) {
                valorTotal = rsTotal.getDouble("Total");
            }

            PreparedStatement stFazenda = Conexao.getConnection().prepareStatement(SQLFazendas);
            stFazenda.setDouble(1, valorTotal);
            stFazenda.setString(2, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stFazenda.setString(3, Corretores.ConverterParaSQL(beans.getDtFinal()));
            ResultSet rsFazenda = stFazenda.executeQuery();
            TbFazenda.setNumRows(0);
            while (rsFazenda.next()) {
                TbFazenda.addRow(new Object[]{rsFazenda.getInt("IDFazenda"), rsFazenda.getString("Fazenda"), new DecimalFormat("R$ ###,###,##0.00").format(rsFazenda.getDouble("Valor")), new DecimalFormat("#0.00 %").format(rsFazenda.getDouble("Porcentagem"))});
            }

            double TotalFazendas = 0;
            double TotalPorcentagem = 0;
            for (int i = 0; i < TbFazenda.getRowCount(); i++) {
                TotalFazendas += Corretores.ConverterReaisDouble(TbFazenda.getValueAt(i, 2).toString());
                TotalPorcentagem += Double.parseDouble(TbFazenda.getValueAt(i, 3).toString().replace("%", "").replace(",", "."));
            }
            TbFazenda.addRow(new Object[]{"", "Total", new DecimalFormat("R$ ###,###,##0.00").format(TotalFazendas), new DecimalFormat("#0.00 %").format(TotalPorcentagem / 100)});

            rsFazenda.close();
            stFazenda.close();
            rsTotal.close();
            stTotal.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao gerar relatório", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void relatorioPagamentos(DefaultTableModel TbPagto, RelatoriosFinanceirosBeans beans) {
        Conexao.ReiniciarCon();
        String SQL = "select  "
                + "ef.fiscal_idpagto as IdPagto, "
                + "(Select cc.cad_conta_descricao from cad_contabancaria cc where cc.cad_conta_id = pg.pagto_idContaOrigem) as ContaOrigem,  "
                + "fiscal_emissor as Emissor, "
                + "ifnull((select cad_fornecedor_nome from cad_fornecedor where cad_fornecedor_id = ef.fiscal_idEmissor),'-') as NomeFantasia, "
                + "ef.fiscal_dtEmissao as DataEmissao, "
                + "pg.pagto_DataPagto as DataPagto, "
                + "ef.fiscal_cnpj as CNPJ, "
                + "ef.fiscal_tipodoc as TipoDocumento, "
                + "ef.fiscal_ndoc as NDOC, "
                + "ef.fiscal_valordoc as Valor, "
                + "(select ec.clas_tipodespesa from escrituracao_classificacao ec where ec.clas_idpagto = ef.fiscal_idpagto Order by ec.clas_valor DESC Limit 1) as TipoDespesa, "
                + "(select ec.clas_planoconta from escrituracao_classificacao ec where ec.clas_idpagto = ef.fiscal_idpagto Order by ec.clas_valor DESC Limit 1) as PlanoConta "
                + " from escrituracao_fiscal ef, pagamentos pg "
                + " WHERE ef.fiscal_idPagto = pg.pagto_id "
                + " And pg.pagto_DataPagto between ? and ? " + beans.getSQLPagto()
                + " order  by pg.pagto_DataPagto asc, IdPagto ASC ";

        try {
            PreparedStatement stSQL = Conexao.getConnection().prepareStatement(SQL);
            stSQL.setString(1, Corretores.ConverterParaSQL(beans.getDtInicial()));
            stSQL.setString(2, Corretores.ConverterParaSQL(beans.getDtFinal()));
            System.out.println(stSQL);
            ResultSet rs = stSQL.executeQuery();
            TbPagto.setNumRows(0);
            while (rs.next()) {
                TbPagto.addRow(new Object[]{rs.getInt("IdPagto"),
                    rs.getString("ContaOrigem"),
                    Corretores.ConverterParaJava(rs.getString("DataEmissao")),
                    Corretores.ConverterParaJava(rs.getString("DataPagto")),
                    rs.getString("Emissor"),
                    rs.getString("NomeFantasia"),
                    rs.getString("CNPJ"),
                    rs.getString("TipoDespesa"),
                    rs.getInt("PlanoConta"),
                    rs.getString("TipoDocumento"),
                    rs.getInt("NDOC"),
                    new DecimalFormat("R$ ###,###,##0.00").format(rs.getDouble("Valor"))});
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao gerar relatório", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public ResultSet relatorioDepesasDiarias(String dtInicial, String dtFinal) {
        Conexao.ReiniciarCon();
        String select = "SELECT p.pagto_id as ID,  \n"
                + "p.pagto_DataPagto as Data, \n"
                + "Primeira_LETRA(p.pagto_titular) as Favorecido, \n"
                + "p.pagto_valorPagto as Valor, \n"
                + "Primeira_LETRA((Select ec.clas_descricao from escrituracao_classificacao ec Where ec.clas_idpagto = p.pagto_id ORDER BY ec.clas_valor DESC LIMIT 1)) as Descricao,\n"
                + "(Select group_concat(distinct concat(' ',ec.clas_fazenda)) as GroupFazenda from escrituracao_classificacao ec Where ec.clas_idpagto = p.pagto_id) as Fazenda,\n"
                + "(Select cb.cad_conta_descricao from cad_contabancaria cb Where cb.cad_conta_id = p.pagto_idContaOrigem) as ContaOrigem, \n"
                + "p.pagto_forma as Forma\n"
                + "FROM pagamentos p WHERE p.pagto_DataPagto BETWEEN '" + dtInicial + "' and '" + dtFinal + "' Order by p.pagto_valorPagto desc;";
        // Funcao Primeira_Letra Favorecido e Descrição
        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = Conexao.getConnection().prepareStatement(select);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return rs;
    }

    public ResultSet relatorioPrevisaoPagtos(String dtInicial, String dtFinal) {
        Conexao.ReiniciarCon();
        String select = "SELECT p.pagto_id as ID,  \n"
                + "p.pagto_DtPrevista as Data, \n"
                + "Primeira_LETRA(p.pagto_titular) as Favorecido, \n"
                + "p.pagto_valorCompra as Valor, \n"
                + "Primeira_LETRA((Select ec.clas_descricao from escrituracao_classificacao ec Where ec.clas_idpagto = p.pagto_id ORDER BY ec.clas_valor DESC LIMIT 1)) as Descricao,\n"
                + "(Select group_concat(distinct concat(' ',ec.clas_fazenda)) as GroupFazenda from escrituracao_classificacao ec Where ec.clas_idpagto = p.pagto_id) as Fazenda,\n"
                + "(Select cb.cad_conta_descricao from cad_contabancaria cb Where cb.cad_conta_id = p.pagto_idContaOrigem) as ContaOrigem, \n"
                + "p.pagto_forma as Forma\n"
                + "FROM pagamentos p WHERE p.pagto_DtPrevista BETWEEN '" + dtInicial + "' and '" + dtFinal + "' ORDER BY ContaOrigem asc, Valor desc, Data ;";

        ResultSet rs = null;
        PreparedStatement st = null;
        try {
            st = Conexao.getConnection().prepareStatement(select);
            rs = st.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return rs;
    }

    public ResultSet exportarPlanoContas(String dtInicial, String dtFinal) {
        Conexao.ReiniciarCon();
        String select = "select \n"
                + " pg.pagto_DataPagto as DataPagto,\n"
                + " ec.clas_idpagto as idPagto,\n"
                + " ec.clas_tipodespesa as Categoria,\n"
                + " ec.clas_atividade as Atividade,\n"
                + " ec.clas_descconta as DescricaoConta,\n"
                + " ec.clas_planoconta as PlanoConta,\n"
                + " ec.clas_fazenda as Fazenda,\n"
                + " ec.clas_descricao as Descricao,\n"
                + " ec.clas_valor as Valor,\n"
                + " pg.pagto_titular as Favorecido FROM escrituracao_classificacao ec \n"
                + "	LEFT JOIN pagamentos pg ON pg.pagto_id = ec.clas_idpagto "
                + " "
                + " WHERE pg.pagto_DataPagto between  '" + dtInicial + "' and '" + dtFinal + "' ;";
        
        ResultSet rs = null;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            return rs = st.executeQuery();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return rs;
    }

}
