/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Almoxarifado.CadItensAlmoxAplicacao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelCadAlmoxarifAplic extends AbstractTableModel {

    private List<CadItensAlmoxAplicacao> linhas;

    public TableModelCadAlmoxarifAplic() {
        linhas = new ArrayList<CadItensAlmoxAplicacao>();
    }

    public TableModelCadAlmoxarifAplic(List<CadItensAlmoxAplicacao> listaDeFretes) {
        linhas = new ArrayList<CadItensAlmoxAplicacao>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "ID_ITEM", "ID_CATEGORIA", "ID_CAT_APLIC", "ID_MARCA_APLIC",
        "ID_MODELO", "Categoria", "Marca", "Modelo"};

    public final int ID = 0;
    public final int ID_ITEM = 1;
    public final int ID_CATEGORIA_ITEM = 2;
    public final int ID_CATEGORIA_APLIC = 3;
    public final int ID_MARCA_APLIC = 4;
    public final int ID_MODELO_APLIC = 5;
    public final int CATEGORIA = 6;
    public final int MARCA = 7;
    public final int MODELO = 8;

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
            case ID_ITEM:
                return Integer.class;
            case ID_CATEGORIA_ITEM:
                return Integer.class;
            case ID_CATEGORIA_APLIC:
                return Integer.class;
            case ID_MARCA_APLIC:
                return Integer.class;
            case ID_MODELO_APLIC:
                return Integer.class;
            case CATEGORIA:
                return String.class;    
            case MARCA:
                return String.class;
            case MODELO:
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

        CadItensAlmoxAplicacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case ID_ITEM:
                return beans.getIdItem();
            case ID_CATEGORIA_ITEM:
                return 0;
            case ID_CATEGORIA_APLIC:
                return beans.getId_categoria_aplic().getID();
            case ID_MARCA_APLIC:
                return beans.getId_marca_aplic().getID();
            case ID_MODELO_APLIC:
                return beans.getId_modelo_aplic().getID();
            case CATEGORIA:
                return beans.getId_categoria_aplic().getCategoria();
            case MARCA:
                return beans.getId_marca_aplic().getMarca();
            case MODELO:
                return beans.getId_modelo_aplic().getDescricao();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        CadItensAlmoxAplicacao beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case ID_ITEM:
                beans.setIdItem((Integer) aValue);
                break;
            case ID_CATEGORIA_ITEM:
                beans.setIdCategoriaItem((Integer) aValue);
                break;
            case ID_CATEGORIA_APLIC:
                beans.setIdCategoriaAplic((Integer) aValue);
                break;
            case ID_MARCA_APLIC:
                beans.setIdMarcaAplic((Integer) aValue);
                break;
            case ID_MODELO_APLIC:
                beans.setIdModeloAplica((Integer) aValue);
                break;
            case MARCA:
                beans.setMarca((String) aValue);
                break;
            case MODELO:
                beans.setModelo((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public CadItensAlmoxAplicacao getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(CadItensAlmoxAplicacao beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<CadItensAlmoxAplicacao> beans) {
        //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
        int indice = getRowCount();
        //adiciona os registros
        linhas.addAll(beans);
        //notifica a mudunça
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void deletarUltimaLinha() {
        int ultimoIndice = getRowCount() - 1;
        linhas.remove(ultimoIndice);
        fireTableRowsDeleted(ultimoIndice, ultimoIndice);
    }

    public void limpar() {
        //remove todos os elementos da lista sócios;
        linhas.clear();
        //notifica a mudança;
        fireTableDataChanged();
    }

    public List<CadItensAlmoxAplicacao> getLista() {
        return linhas;
    }

    public void setBeans(CadItensAlmoxAplicacao beans, int rowIndex){
        linhas.set(rowIndex, beans);
        this.fireTableRowsUpdated(rowIndex, rowIndex);
    }
    
}
