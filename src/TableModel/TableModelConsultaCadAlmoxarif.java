/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelConsultaCadAlmoxarif extends AbstractTableModel {

    private List<TbConsultaCadAlmoxarif> linhas;

    public TableModelConsultaCadAlmoxarif() {
        linhas = new ArrayList<TbConsultaCadAlmoxarif>();
    }

    public TableModelConsultaCadAlmoxarif(List<TbConsultaCadAlmoxarif> listaDeFretes) {
        linhas = new ArrayList<TbConsultaCadAlmoxarif>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Código", "Original", "Descrição", "Unidade", "Modelo"};

    public final int ID = 0;
    public final int CODIGO = 1;
    public final int ORIGINAL = 2;
    public final int DESCRICAO = 3;
    public final int UNIDADE = 4;
    public final int MODELO = 5;
    
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
            case CODIGO:
                return String.class;
            case ORIGINAL:
                return Boolean.class;
            case DESCRICAO:
                return String.class;
            case UNIDADE:
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
        //pega o Socio referente a linha especificada;
        TbConsultaCadAlmoxarif beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case CODIGO:
                return beans.getCodigo();
            case ORIGINAL:
                return beans.getCodigoCatalogo();
            case DESCRICAO:
                return beans.getDescricao();
            case UNIDADE:
                return beans.getUnidade();    
            case MODELO:
                return beans.getModelo();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbConsultaCadAlmoxarif beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
               break;
        /*    case ID_ITEM:
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
                break;*/
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }
    
    public TbConsultaCadAlmoxarif getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbConsultaCadAlmoxarif beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbConsultaCadAlmoxarif> beans) {
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

    public List<TbConsultaCadAlmoxarif> getLista() {
        return linhas;
    }

}
