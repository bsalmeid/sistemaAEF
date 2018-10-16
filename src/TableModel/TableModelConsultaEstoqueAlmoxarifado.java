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
public class TableModelConsultaEstoqueAlmoxarifado extends AbstractTableModel {

    private List<TbConsultaEstoqueAlmoxarifado> linhas;

    public TableModelConsultaEstoqueAlmoxarifado() {
        linhas = new ArrayList<TbConsultaEstoqueAlmoxarifado>();
    }

    public TableModelConsultaEstoqueAlmoxarifado(List<TbConsultaEstoqueAlmoxarifado> listaDeFretes) {
        linhas = new ArrayList<TbConsultaEstoqueAlmoxarifado>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Código", "Descrição", "Unidade", "(+)Intervalo", "(-)Intervalo", "Estoque"};

    public final int ID = 0;
    public final int CODIGO = 1;
    public final int DESCRICAO = 2;
    public final int UNIDADE = 3;
    public final int ENTRADA_INTERVALO = 4;
    public final int SAIDA_INTERVALO = 5;
    public final int ESTOQUE = 6;
    
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
            case DESCRICAO:
                return String.class;
            case UNIDADE:
                return String.class;    
            case ENTRADA_INTERVALO:
                return Double.class;
            case SAIDA_INTERVALO:
                return Double.class;
            case ESTOQUE:
                return Double.class;    
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
        TbConsultaEstoqueAlmoxarifado beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case CODIGO:
                return beans.getCodigo();
            case DESCRICAO:
                return beans.getDescricao();
            case UNIDADE:
                return beans.getUnidade();    
            case ENTRADA_INTERVALO:
                return beans.getEntradaIntervalo();
            case SAIDA_INTERVALO:
                return beans.getSaidaIntervalo(); 
            case ESTOQUE:
                return beans.getEstoque();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TbConsultaEstoqueAlmoxarifado beans = linhas.get(rowIndex);
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
        fireTableCellUpdated(rowIndex, columnIndex);

    }
    
    public TbConsultaEstoqueAlmoxarifado getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbConsultaEstoqueAlmoxarifado beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbConsultaEstoqueAlmoxarifado> beans) {
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

    public List<TbConsultaEstoqueAlmoxarifado> getLista() {
        return linhas;
    }

}
