/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.PagtoClassBeans;
import Beans.PagamentosBeans;
import Beans.PropriedadesBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelPagtoClass extends AbstractTableModel {

    private List<PagtoClassBeans> linhas;

    public TableModelPagtoClass() {
        linhas = new ArrayList<PagtoClassBeans>();
    }

    public TableModelPagtoClass(List<PagtoClassBeans> listaDeFretes) {
        linhas = new ArrayList<PagtoClassBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "ID Pagto", "Atividade", "C. Resultado" ,"Tipo Despesa", "IdPlano", "Conta",
        "Descrição da Conta", "Fazenda", "Nº Frota",
        "Descrição da Depesa", "Valor", "idFazenda", "idInventario"};

    public final int ID = 0;
    public final int IDPAGTO = 1;
    public final int ATIVIDADE = 2;
    public final int CENTRO_RESULTADO = 3;
    public final int TIPODESPESA = 4;
    public final int IDPLANO = 5;
    public final int PLANOCONTA = 6;
    public final int DESCCONTA = 7;
    public final int FAZ = 8;
    public final int APLICACAO = 9;
    public final int DESCRICAO = 10;
    public final int VALOR = 11;
    public final int IDFAZENDA = 12;
    public final int IDAPLICACAO = 13;

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
                return Long.class;
            case IDPAGTO:
                return PagamentosBeans.class;
            case ATIVIDADE:
                return String.class;
            case CENTRO_RESULTADO:  
                return String.class;
            case TIPODESPESA:
                return String.class;
            case IDPLANO:
                return Integer.class;
            case PLANOCONTA:
                return Integer.class;
            case DESCCONTA:
                return String.class;
            case FAZ:
                return String.class;
            case APLICACAO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case VALOR:
                return Double.class;
            case IDFAZENDA:
                return Integer.class;
            case IDAPLICACAO:
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
        PagtoClassBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case IDPAGTO:
                return beans.getPagto();
            case ATIVIDADE:
                return beans.getAtividadeBeans().getDescricao();
            case CENTRO_RESULTADO:
                if (beans.getCentroResultado() != null){
                    return beans.getCentroResultado().toString();
                } else {
                      return "-";
                }
            case TIPODESPESA:
                return beans.getTipoDespesa();
            case IDPLANO:
                return beans.getIdPlanoContas();
            case PLANOCONTA:
                return beans.getPlanoConta().getConta();
            case DESCCONTA:
                return beans.getPlanoConta().getDescricao();
            case FAZ:
                return beans.getFazendaB().getNome();
            case APLICACAO:
                return beans.getnFrota();
            case DESCRICAO:
                return beans.getDescricao();
            case VALOR:
                return beans.getValorClas();
            case IDFAZENDA:
                return beans.getFazendaB().getCodigo();
            case IDAPLICACAO:
                return beans.getIdAplicacao();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        PagtoClassBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Long) aValue);
                break;
            case IDPAGTO:
                beans.setPagto((PagamentosBeans) aValue);
                break;
            case ATIVIDADE:
                beans.setAtividade((String) aValue);
                break;
            case TIPODESPESA:
                beans.setTipoDespesa((String) aValue);
                break;
            case IDPLANO:
                beans.setIdPlanoContas((Integer) aValue);
                break;
            case PLANOCONTA:
                break;
            case DESCCONTA:
                beans.setDesConta((String) aValue);
                break;
            case FAZ:
                beans.setFazenda(aValue.toString());
                break;
            case APLICACAO:
                beans.setnFrota((String) aValue);
                break;
            case DESCRICAO:
                beans.setDescricao((String) aValue);
                break;
            case VALOR:
                break;
            case IDFAZENDA:
                beans.setFazendaB((PropriedadesBeans) aValue);
                break;
            case IDAPLICACAO:
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public PagtoClassBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void setBeans(PagtoClassBeans beans, int rowIndex) {
        linhas.set(rowIndex, beans);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public List<PagtoClassBeans> getLista() {
        return linhas;
    }

    public void addBeans(PagtoClassBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<PagtoClassBeans> beans) {
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

    public Double somarValorTabela(int columnIndex) {
        Double s = 0.00;
        for (int i = 0; i < linhas.size(); i++) {
            s += (Double) getValueAt(i, columnIndex);
        }
        return s;
    }

}
