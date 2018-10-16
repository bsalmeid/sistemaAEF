/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.FornecedoresBeans;
import Beans.PagamentosBeans;
import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TableModelImportarFolhaPagto extends AbstractTableModel {

    private String[] colunas = new String[]{"ID_PAGTO", "Data", "Conta de Origem", "Atividade", "Fazenda", "Centro Result.",
        "Plano de Conta", "Forma Pagto", "Nº Doc", "Banco Destino", "Descricão", "ID_CAD", "CPF", "Nome", "Agência", "Conta", "Valor"};

    private List<PagamentosBeans> linhas;
    public final int ID_PAGTO = 0;
    public final int DATA = 1;
    public final int CONTAORIGEM = 2;
    public final int ATIVIDADE = 3;
    public final int FAZENDA = 4;
    public final int CENTRO_RESULTADO = 5;
    public final int PLANOCONTA = 6;
    public final int FORMAPAGTO = 7;
    public final int NCHEQUE = 8;
    public final int BANCODESTINO = 9;
    public final int DESCRICAO = 10;
    public final int ID_CAD = 11;
    public final int CPF = 12;
    public final int NOME = 13;
    public final int AGENCIA = 14;
    public final int CONTA = 15;
    public final int VALOR = 16;

    public TableModelImportarFolhaPagto() {
        linhas = new ArrayList<PagamentosBeans>();
    }

    public TableModelImportarFolhaPagto(List<PagamentosBeans> listaPagtos) {
        linhas = new ArrayList<PagamentosBeans>(listaPagtos);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID_PAGTO:
                return Long.class;
            case DATA:
                return Date.class;
            case CONTAORIGEM:
                return String.class;
            case CENTRO_RESULTADO:
                return String.class;
            case ATIVIDADE:
                return String.class;
            case FAZENDA:
                return String.class;
            case PLANOCONTA:
                return Integer.class;
            case FORMAPAGTO:
                return String.class;
            case NCHEQUE:
                return Integer.class;
            case BANCODESTINO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case ID_CAD:
                return Integer.class;
            case CPF:
                return String.class;
            case NOME:
                return String.class;
            case VALOR:
                return Double.class;
            case AGENCIA:
                return String.class;
            case CONTA:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

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
    public boolean isCellEditable(int row, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        PagamentosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_PAGTO:
                return beans.getID();
            case DATA:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDtRealizado());
            case CONTAORIGEM:
                return beans.getIDContaOrigem().getDescricao();
            case CENTRO_RESULTADO:
                return beans.getListaClassificacao().get(0).getCentroResultado().getDescricao();
            case ATIVIDADE:
                return beans.getListaClassificacao().get(0).getAtividadeBeans().getDescricao();
            case FAZENDA:
                return beans.getListaClassificacao().get(0).getFazendaB().getNome();
            case PLANOCONTA:
                return beans.getListaClassificacao().get(0).getPlanoConta().getConta();
            case FORMAPAGTO:
                return beans.getFormaPagto();
            case NCHEQUE:
                return beans.getnDocumento();
            case BANCODESTINO:
                return beans.getBanco();
            case DESCRICAO:
                return beans.getListaClassificacao().get(0).getDescricao();
            case ID_CAD:
                return (beans.getFornecedorB() != null ? beans.getFornecedorB().getID() : 0);
            case CPF:
                return beans.getCpf();
            case NOME:
                return beans.getTitular();
            case VALOR:
                return beans.getValorPagamento();
            case AGENCIA:
                return beans.getAgencia();
            case CONTA:
                return beans.getConta();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        PagamentosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case DATA:
                beans.setID((Long) aValue);
                break;
            case ID_CAD:
                beans.setFornecedorB((FornecedoresBeans) aValue);
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

    public List<PagamentosBeans> getLista(){
       return linhas; 
    }
    
    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

}
