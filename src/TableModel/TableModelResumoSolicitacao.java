/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Utilitarios.Corretores;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelResumoSolicitacao extends AbstractTableModel {

    private List<TbResumoSolicitacao> linhas;

    public TableModelResumoSolicitacao() {
        linhas = new ArrayList<TbResumoSolicitacao>();
    }

    public TableModelResumoSolicitacao(List<TbResumoSolicitacao> listaDeFretes) {
        linhas = new ArrayList<TbResumoSolicitacao>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Data", "Usuário", "Descrição", "Q-S","Q-Cot" ,"Q-A",
        "Q-C", "Q-R", "Q-E","Q-Can" ,"S"};

    public final int ID = 0;
    public final int DATA = 1;
    public final int USUARIO = 2;
    public final int DESCRICAO = 3;
    public final int Q_SOLICITACAO = 4;
    public final int Q_COTACOES = 5;
    public final int Q_ANALISE = 6;
    public final int Q_COMPRADO = 7;
    public final int Q_RECEBIDO = 8;
    public final int Q_ENVIADO = 9;
    public final int Q_CANCELADO = 10;
    public final int STATUS = 11;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Long.class;
            case DATA:
                return Date.class;
            case USUARIO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case Q_SOLICITACAO:
                return Long.class;
            case Q_COTACOES:
                return Long.class;    
            case Q_ANALISE:
                return Long.class;
            case Q_COMPRADO:
                return Long.class;
            case Q_RECEBIDO:
                return Long.class;
            case Q_ENVIADO:
                return Long.class;
            case Q_CANCELADO:
                return Long.class;    
            case STATUS:
                return Boolean.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds " + columnIndex);
        }
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        TbResumoSolicitacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getSolic().getId();
            case DATA:
                return Corretores.ConverterDateStringDDMMAAA(beans.getSolic().getData());
            case USUARIO:
                return beans.getSolic().getIdUsuario();
            case DESCRICAO:
                return beans.getSolic().getDescricao();
            case Q_SOLICITACAO:
                return beans.getqSolicitacao();
            case Q_COTACOES:
                return beans.getqCotacao();    
            case Q_ANALISE:
                return beans.getqAnalise();
            case Q_COMPRADO:
                return beans.getqComprada();
            case Q_RECEBIDO:
                return beans.getqRecebido();
            case Q_ENVIADO:
                return beans.getqEnviada();
            case Q_CANCELADO:
                return beans.getqCancelado();
            case STATUS:
                return beans.getSolic().getStatus();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {

        return false;
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

     @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        TbResumoSolicitacao beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case STATUS:
                beans.getSolic().setStatus((Boolean) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);

    }
    
    public void excluirLinha(int rowIndex) {
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }

    public TbResumoSolicitacao getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbResumoSolicitacao beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbResumoSolicitacao> beans) {
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

    public List<TbResumoSolicitacao> getLista() {
        return linhas;
    }

}
