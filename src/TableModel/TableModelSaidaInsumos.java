/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.SaidaInsumosBeans;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultRowSorter;
import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelSaidaInsumos extends AbstractTableModel {

    private List<SaidaInsumosBeans> linhas;

    public TableModelSaidaInsumos() {
        linhas = new ArrayList<SaidaInsumosBeans>();
    }

    public TableModelSaidaInsumos(List<SaidaInsumosBeans> listaPagtos) {
        linhas = new ArrayList<SaidaInsumosBeans>(listaPagtos);
    }

    private String[] colunas = new String[]{"ID", "ID Ordem", "Data", "Operação" ,"idLocalSaida","Local Estoque" ,"idSafra",
        "Safra", "idCultivo", "Cultivo", "idCultura", "Cultura", "idAplicação", "Aplicação", "Nº Doc",
        "Responsavel", "Observacao", "idInsumo",
        "Insumo", "Unid", "Categoria", "Quantidade"
    };

    private static final int ID = 0;
    private static final int IDORDEM = 1;
    private static final int DATA = 2;
    private static final int OPERACAO = 3;
    private static final int IDLOCALSAIDA = 4;
    private static final int LOCALSAIDA = 5;
    private static final int IDSAFRA = 6;
    private static final int SAFRA = 7;
    private static final int IDCULTIVO = 8;
    private static final int CULTIVO = 9;
    private static final int IDCULTURA = 10;
    private static final int CULTURA = 11;
    private static final int IDAPLICACAO = 12;
    private static final int APLICACAO = 13;
    private static final int NDOC = 14;
    private static final int RESPONSAVEL = 15;
    private static final int OBSERVACAO = 16;
    private static final int IDINSUMO = 17;
    private static final int INSUMO = 18;
    private static final int UNIDADE = 19;
    private static final int CATEGORIA = 20;
    private static final int QUANTIDADE = 21;

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case IDORDEM:
                return Integer.class;
            case DATA:
                return String.class;
            case OPERACAO:
                return String.class;
            case IDLOCALSAIDA:
                return Integer.class;
            case LOCALSAIDA:
                return String.class;
            case IDSAFRA:
                return Integer.class;
            case SAFRA:
                return String.class;
            case IDCULTIVO:
                return Integer.class;
            case CULTIVO:
                return String.class;
            case IDCULTURA:
                return Integer.class;
            case CULTURA:
                return String.class;
            case IDAPLICACAO:
                return Integer.class;
            case APLICACAO:
                return String.class;
            case NDOC:
                return Integer.class;
            case RESPONSAVEL:
                return String.class;
            case OBSERVACAO:
                return String.class;
            case IDINSUMO:
                return Integer.class;
            case INSUMO:
                return String.class;
            case UNIDADE:
                return String.class;
            case CATEGORIA:
                return String.class;
            case QUANTIDADE:
                return Double.class;
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

    public void addRow(SaidaInsumosBeans Beans) {
        int indice = getRowCount() - 1;
        linhas.add(Beans);
        fireTableRowsInserted(indice, indice);
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        SaidaInsumosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case IDORDEM:
                return beans.getIDOrdem();
            case DATA:
                return beans.getDtSaida();
            case OPERACAO:
                return beans.getOperacao();
            case IDLOCALSAIDA:
                return beans.getIdLocalSaida();
            case LOCALSAIDA:
                return beans.getLocalSaida();
            case IDSAFRA:
                return beans.getIdSafra();
            case SAFRA:
                return beans.getSafra();
            case IDCULTIVO:
                return beans.getIdCultivo();
            case CULTIVO:
                return beans.getCultivo();
            case IDCULTURA:
                return beans.getIdCultura();
            case CULTURA:
                return beans.getCultura();
            case IDAPLICACAO:
                return beans.getIdAplicao();
            case APLICACAO:
                return beans.getAplicacao();
            case NDOC:
                return beans.getnDoc();
            case RESPONSAVEL:
                return beans.getResonsavel();
            case OBSERVACAO:
                return beans.getObservacao();
            case IDINSUMO:
                return beans.getIdInsumo();
            case INSUMO:
                return beans.getNomeInsumo();
            case UNIDADE:
                return beans.getUnidade();
            case CATEGORIA:
                return beans.getCategoria();
            case QUANTIDADE:
                return beans.getQuant();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        SaidaInsumosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case IDORDEM:
                beans.setIDOrdem((Integer) aValue);
                break;
            case DATA:
                beans.setDtSaida((String) aValue);
                break;
            case OPERACAO:
                beans.setOperacao((String) aValue);
                break;
            case IDLOCALSAIDA:
                beans.setIdLocalSaida((Integer) aValue);
                break;
            case LOCALSAIDA:
                beans.setLocalSaida((String) aValue);
                break;
            case IDSAFRA:
                beans.setIdSafra((Integer) aValue);
                break;
            case SAFRA:
                beans.setSafra((String) aValue);
                break;
            case IDCULTIVO:
                beans.setIdCultivo((Integer) aValue);
                break;    
            case CULTIVO:
                beans.setCultivo((String) aValue);
                break;
            case IDCULTURA:
                beans.setIdCultura((Integer) aValue);
                break;
            case CULTURA:
                beans.setCultura((String) aValue);
                break;   
             case IDAPLICACAO:
                beans.setIdAplicao((Integer) aValue);
                break;
            case APLICACAO:
                beans.setAplicacao((String) aValue);
                break;
            case NDOC:
                beans.setnDoc((Integer) aValue);
                break;
            case RESPONSAVEL:
                beans.setResonsavel((String) aValue);
                break;
            case OBSERVACAO:
                beans.setObservacao((String) aValue);
                break;
            case IDINSUMO:
                beans.setIdInsumo((Integer) aValue);
                break;
            case INSUMO:
                beans.setNomeInsumo((String) aValue);
                break;
            case UNIDADE:
                beans.setUnidade((String) aValue);
                break;    
            case CATEGORIA:
                beans.setCategoria((String) aValue);
                break;
            case QUANTIDADE:
                beans.setQuant((Double) aValue);
                break;            
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    // retorna o socio referente a linha especidicada      
    public SaidaInsumosBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(SaidaInsumosBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<SaidaInsumosBeans> beans) {

        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size() + 1);

    }

    public void limpar() {
        //remove todos os elementos da lista sócios;
        linhas.clear();
        //notifica a mudança;
        fireTableDataChanged();
    }
    
    
    
}
