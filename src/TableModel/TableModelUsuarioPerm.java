/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.CadUsuario;
import Beans.CadUsuarioPermissoes;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelUsuarioPerm extends AbstractTableModel {

    private List<CadUsuarioPermissoes> linhas;

    public TableModelUsuarioPerm() {
        linhas = new ArrayList<CadUsuarioPermissoes>();
    }

    public TableModelUsuarioPerm(List<CadUsuarioPermissoes> listaDeFretes) {
        linhas = new ArrayList<CadUsuarioPermissoes>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "ID_USER", "Nome Classe", "Formulário", "Visualização",
        "Inserir", "Editar","Consultar","Excluir"};

    public final int ID = 0;
    public final int ID_USER = 1;
    public final int NOME_CLASSE = 2;
    public final int NOME_AMIGAVEL = 3;
    public final int VIEW = 4;
    public final int INSERT = 5;
    public final int UPDATE = 6;
    public final int SELECT = 7;
    public final int DELETE = 8;

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
            case ID_USER:
                return Integer.class;
            case NOME_CLASSE:
                return String.class;
            case NOME_AMIGAVEL:
                return String.class;
            case VIEW:
                return Boolean.class;
            case INSERT:
                return Boolean.class;
            case UPDATE:
                return Boolean.class;
            case DELETE:
                return Boolean.class;
            case SELECT:
                return Boolean.class;    
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        if (columnIndex == VIEW || columnIndex == INSERT || columnIndex == UPDATE || columnIndex == DELETE || columnIndex == SELECT) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CadUsuarioPermissoes beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getId();
            case ID_USER:
                return beans.getIdUsuario().getId();
            case NOME_CLASSE:
                return beans.getNomeClasse();
            case NOME_AMIGAVEL:
                return beans.getFormulario();
            case VIEW:
                return beans.getView();
            case INSERT:
                return beans.getInsert();
            case UPDATE:
                return beans.getUpdate();
            case DELETE:
                return beans.getDelete();
            case SELECT:
                return beans.getSelect();    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        CadUsuarioPermissoes beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setId((Integer) aValue);
                break;
            case ID_USER:
                beans.setIdUsuario(((CadUsuario) aValue));
                break;
            case NOME_CLASSE:
                beans.setNomeClasse((String) aValue);
                break;
            case NOME_AMIGAVEL:
                beans.setFormulario((String) aValue);
                break;
            case VIEW:
                beans.setView((Boolean) aValue);
                break;
            case INSERT:
                beans.setInsert((Boolean) aValue);
                break;
            case UPDATE:
                beans.setUpdate((Boolean) aValue);
                break;
            case DELETE:
                beans.setDelete((Boolean) aValue);
                break;
            case SELECT:
                beans.setSelect((Boolean) aValue);
                break;    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }

    public CadUsuarioPermissoes getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(CadUsuarioPermissoes beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<CadUsuarioPermissoes> beans) {
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

    public List<CadUsuarioPermissoes> getLista() {
        return linhas;
    }

}
