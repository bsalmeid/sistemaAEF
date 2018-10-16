/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.TbPagtoNFBeans;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class PagtoNFTableModel extends AbstractTableModel {

    private List<TbPagtoNFBeans> linhas;

    public PagtoNFTableModel() {
        linhas = new ArrayList<TbPagtoNFBeans>();
    }

    public PagtoNFTableModel(List<TbPagtoNFBeans> listaDeFretes) {
        linhas = new ArrayList<TbPagtoNFBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "IDPagto", "Data Emissão", "TipoCad", "CPF/CNPJ", "Tipo Documento", "Nº Documento",
        "Fazenda", "ID Emissor", "Emissor", "Valor", "Id Tipo Doc"};

    public final int ID = 0;
    public final int IDPAGTO = 1;
    public final int DTEMISSAO = 2;
    public final int TIPOCAD = 3;
    public final int NCAD = 4;
    public final int TIPODOC = 5;
    public final int NDOC = 6;
    public final int FAZENDA = 7;
    public final int IDEMISSOR = 8;
    public final int EMISSOR = 9;
    public final int VALOR = 10;
    public final int IDTIPODOC = 11;

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

    public void addRow(TbPagtoNFBeans Beans) {
        int indice = getRowCount() - 1;
        linhas.add(Beans);
        fireTableRowsInserted(indice, indice);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case IDPAGTO:
                return Integer.class;
            case DTEMISSAO:
                return String.class;
            case TIPOCAD:
                return String.class;
            case NCAD:
                return String.class;
            case TIPODOC:
                return String.class;
            case NDOC:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case IDEMISSOR:
                return Integer.class;
            case EMISSOR:
                return String.class;
            case VALOR:
                return BigDecimal.class;
            case IDTIPODOC:
                return Integer.class;
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

        TbPagtoNFBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case IDPAGTO:
                return beans.getIDPagto();
            case DTEMISSAO:
                return beans.getDtEmissao();
            case TIPOCAD:
                return beans.getTipoCad();
            case NCAD:
                return beans.getnCad();
            case TIPODOC:
                return beans.getTipoDoc();
            case NDOC:
                return beans.getNDOC();
            case FAZENDA:
                return beans.getFazenda();
            case IDEMISSOR:
                return beans.getIdFornecedor();
            case EMISSOR:
                return beans.getFornecedor();
            case VALOR:
                return beans.getValor();
            case IDTIPODOC:
                return beans.getIdTipoDoc();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        TbPagtoNFBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case IDPAGTO:
                beans.setIDPagto((Integer) aValue);
                break;
            case DTEMISSAO:
                beans.setDtEmissao((String) aValue);
                break;
            case TIPOCAD:
                beans.setTipoCad((String) aValue);
                break;
            case NCAD:
                beans.setnCad((String) aValue);
                break;
            case TIPODOC:
                beans.setTipoDoc((String) aValue);
                break;
            case NDOC:
                beans.setNDOC((Integer) aValue);
                break;
            case FAZENDA:
                beans.setFazenda((String) aValue);
                break;
            case IDEMISSOR:
                beans.setIdFornecedor((Integer) aValue);
                break;
            case EMISSOR:
                beans.setFornecedor((String) aValue);
                break;
            case VALOR:
                beans.setValor((BigDecimal) aValue);
                break;
            case IDTIPODOC:
                beans.setIdTipoDoc((Integer) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }
  
    public TbPagtoNFBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbPagtoNFBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbPagtoNFBeans> beans) {
       
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    
}
