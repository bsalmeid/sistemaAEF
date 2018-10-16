/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PagtoNFBeans;
import Beans.PagtoClassBeans;
import Beans.PagamentosBeans;
import Utilitarios.Corretores;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelResPagtoNovo extends AbstractTableModel {

    private List<PagamentosBeans> linhas;

    public TableModelResPagtoNovo() {
        linhas = new ArrayList<>();
    }

    public TableModelResPagtoNovo(List<PagamentosBeans> listaPagtos) {
        linhas = new ArrayList<>(listaPagtos);
    }

    private String[] colunas = new String[]{"ID", "Conta de Origem", "Data Prevista",
        "Data Pagto", "Favorecido", "Forma de Pgto", "Nº Doc", "Moeda", "Valor em Moeda", "Taxa", "Valor Previsto",
        "Valor Pago", "Valor NF's", "Classificado", "Status"};

    public final int IDPAGTO = 0;
    public final int CONTAORIGEM = 1;
    public final int DTPREVISTA = 2;
    public final int DTPAGTO = 3;
    public final int TITULAR = 4;
    public final int FORMAPAGTO = 5;
    public final int NDOC = 6;
    public final int MOEDA = 7;
    public final int VALOREMMOEDA = 8;
    public final int TAXA = 9;
    public final int VALORPREVISTO = 10;
    public final int VALORPAGO = 11;
    public final int VALORNF = 12;
    public final int VALORCLASSIFICADO = 13;
    public final int STATUS = 14;

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return colunas[columnIndex];
    }

    public void excluirLinha(int rowIndex) {
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public void addRow(PagamentosBeans Beans) {
        int indice = getRowCount() - 1;
        linhas.add(Beans);
        fireTableRowsInserted(indice, indice);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case IDPAGTO:
                return Integer.class;
            case CONTAORIGEM:
                return String.class;
            case DTPREVISTA:
                return String.class;
            case DTPAGTO:
                return String.class;
            case TITULAR:
                return String.class;
            case FORMAPAGTO:
                return String.class;
            case NDOC:
                return Integer.class;
            case MOEDA:
                return String.class;
            case VALOREMMOEDA:
                return Double.class;
            case TAXA:
                return Double.class;
            case VALORPREVISTO:
                return Double.class;
            case VALORPAGO:
                return Double.class;
            case VALORNF:
                return Double.class;
            case VALORCLASSIFICADO:
                return Double.class;
            case STATUS:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {

        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PagamentosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case IDPAGTO:
                return beans.getID();
            case CONTAORIGEM:
                return beans.getIDContaOrigem().getDescricao();
            case DTPREVISTA:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDtPrevista());
            case DTPAGTO:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDtRealizado());
            case TITULAR:
                return beans.getTitular();
            case FORMAPAGTO:
                return beans.getFormaPagto();
            case NDOC:
                return beans.getnDocumento();
            case MOEDA:
                return beans.getMoeda();
            case VALOREMMOEDA:
                return beans.getValorEmMoeda();
            case TAXA:
                return beans.getTaxaConverte();
            case VALORPREVISTO:
                return beans.getValorCompra();
            case VALORPAGO:
                return beans.getValorPagamento();
            case VALORNF:
                return beans.getValorNF();
            case VALORCLASSIFICADO:
                return beans.getValorClas();
            case STATUS:
                return beans.getStatus();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PagamentosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case IDPAGTO:
                beans.setID((Long) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public PagamentosBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(PagamentosBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<PagamentosBeans> beans) {
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size() + 1);

    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public Double somarValorClassificado(PagamentosBeans pg) {
        List<PagtoClassBeans> lista = pg.getListaClassificacao();
        Double s = 0.00;
        for (int i = 0; i < lista.size(); i++) {
            s += lista.get(i).getValorClas();
        }
        return s;
    }

    public Double somarValorNF(PagamentosBeans pg) {
        List<PagtoNFBeans> lista = pg.getListaNotaFiscal();
        Double s = 0.00;
        for (int i = 0; i < lista.size(); i++) {
            s += lista.get(i).getValor();
        }
        return s;
    }

}
