/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.FornecedoresBeans;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelFornecedores extends AbstractTableModel {

    private List<FornecedoresBeans> linhas;

    public TableModelFornecedores() {
        linhas = new ArrayList<>();
    }

    public TableModelFornecedores(Set<FornecedoresBeans> lista) {
        linhas = new ArrayList<>(lista);
    }

    private String[] colunas = new String[]{"ID", "ID_Segmento", "Nome Fantasia", "Razão Social", "Telefone",
        "E-mail", "Tipo Pessoa", "CPF/CNPJ", "Banco", "Agência", "Conta"};

    public final int ID = 0;
    public final int ID_SEGMENTO = 1;
    public final int NOME_FANT = 2;
    public final int RAZAO_SOCIAL = 3;
    public final int TELEFONE = 4;
    public final int EMAIL = 5;
    public final int TIPO_PESSOA = 6;
    public final int CNPJ = 7;
    public final int BANCO = 8;
    public final int AGENCIA = 9;
    public final int CONTA = 10;

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

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case ID_SEGMENTO:
                return Integer.class;
            case NOME_FANT:
                return Integer.class;
            case RAZAO_SOCIAL:
                return Integer.class;
            case TELEFONE:
                return Integer.class;
            case EMAIL:
                return Integer.class;
            case TIPO_PESSOA:
                return String.class;
            case CNPJ:
                return String.class;
            case BANCO:
                return String.class;
            case AGENCIA:
                return String.class;
            case CONTA:
                return String.class;    
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        // if (columnIndex == DESCCONTA || columnIndex == ID || columnIndex == IDPAGTO ){
        //      return false;
        //}
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        FornecedoresBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case ID_SEGMENTO:
                return beans.getIdSegmento();
            case NOME_FANT:
                return beans.getNomeFantasia();
            case RAZAO_SOCIAL:
                return beans.getRazaoSocial();
            case TELEFONE:
                return beans.getTelefone();
            case EMAIL:
                return beans.getEmail();
            case TIPO_PESSOA:
                return beans.getTipoPessoa();
            case CNPJ:
                return beans.getCnpj();
            case BANCO:
                return beans.getBanco();
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
        FornecedoresBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
    }

    public FornecedoresBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(FornecedoresBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<FornecedoresBeans> beans) {
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void deletarUltimaLinha() {
        int ultimoIndice = getRowCount() - 1;
        linhas.remove(ultimoIndice);
        fireTableRowsDeleted(ultimoIndice, ultimoIndice);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public List<FornecedoresBeans> getLista() {
        return linhas;
    }

}
