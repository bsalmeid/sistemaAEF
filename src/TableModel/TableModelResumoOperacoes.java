/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelResumoOperacoes extends AbstractTableModel {

    private List<TbOperacoesBeans> linhas;

    public TableModelResumoOperacoes() {
        linhas = new ArrayList<TbOperacoesBeans>();
    }

    public TableModelResumoOperacoes(List<TbOperacoesBeans> listaDeFretes) {
        linhas = new ArrayList<TbOperacoesBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "ID_OS", "Data", "Safra", "Cultivo", "Cultura", "Operação", "Aplicação", "Trator", "Modelo Trator", "Implem.", "Modelo Implem.",
        "Operador", "Fazenda", "Talhão", "H.M.I", "H.M.F", "H.M Trabalho", "Area Trabalhada", "Rend. Teorico", "Rend OP", "Eficiencia OP"};

    private static final int ID = 0;
    private static final int ID_OS = 1;
    private static final int DATA = 2;
    private static final int SAFRA = 3;
    private static final int CULTIVO = 4;
    private static final int CULTURA = 5;
    private static final int OPERACAO = 6;
    private static final int APLICACAO = 7;
    private static final int TRATOR = 8;
    private static final int MODELOTRATOR = 9;
    private static final int IMPLEMENTO = 10;
    private static final int MODELOIMPLEMENTO = 11;
    private static final int OPERADOR = 12;
    private static final int FAZENDA = 13;
    private static final int TALHAO = 14;
    private static final int HMINICIAL = 15;
    private static final int HMFINAL = 16;
    private static final int HMTRABALHADO = 17;
    private static final int AREA = 18;
    private static final int RENDOPTEORICO = 19;
    private static final int RENDOP = 20;
    private static final int EFICIENCIAOP = 21;

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
            case ID_OS:
                return Integer.class;
            case DATA:
                return String.class;
            case SAFRA:
                return String.class;
            case CULTIVO:
                return String.class;
            case CULTURA:
                return String.class;
            case OPERACAO:
                return String.class;
            case APLICACAO:
                return String.class;
            case TRATOR:
                return String.class;
            case MODELOTRATOR:
                return String.class;
            case IMPLEMENTO:
                return String.class;
            case MODELOIMPLEMENTO:
                return String.class;
            case OPERADOR:
                return String.class;
            case FAZENDA:
                return String.class;
            case TALHAO:
                return String.class;
            case HMINICIAL:
                return Double.class;
            case HMFINAL:
                return Double.class;
            case HMTRABALHADO:
                return Double.class;
            case AREA:
                return Double.class;
            case RENDOPTEORICO:
                return Double.class;
            case RENDOP:
                return Double.class;
            case EFICIENCIAOP:
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
        TbOperacoesBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case ID_OS:
                return beans.getID_OS();
            case DATA:
                return beans.getData();
            case SAFRA:
                return beans.getSafra();
            case CULTIVO:
                return beans.getCultivo();
            case CULTURA:
                return beans.getCultura();
            case OPERACAO:
                return beans.getOperacao();
            case APLICACAO:
                return beans.getAplicacao();
            case TRATOR:
                return beans.getTrator();
            case MODELOTRATOR:
                return beans.getModeloTrator();
            case IMPLEMENTO:
                return beans.getImplemento();
            case MODELOIMPLEMENTO:
                return beans.getModeloImplemento();
            case OPERADOR:
                return beans.getOperador();
            case FAZENDA:
                return beans.getFazenda();
            case TALHAO:
                return beans.getTalhao();
            case HMINICIAL:
                return beans.getHmInicial();
            case HMFINAL:
                return beans.getHmFinal();
            case HMTRABALHADO:
                return beans.getHmTrablhada();
            case AREA:
                return beans.getAreaTrabalhada();
            case RENDOPTEORICO:
                return beans.getRendOpTeorico();
            case RENDOP:
                return beans.getRendOp();
            case EFICIENCIAOP:
                return beans.getEficienciaOp();    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbOperacoesBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            /*  case STATUS:
                beans.setStatus((Boolean) aValue);
                break;
            case ITEMPEDIDO:
                beans.setItemListaPedidos((Integer) aValue);
                break;
            case IDPEDIDO:
                beans.setIdPedido((Integer) aValue);
                break;
            case NPEDIDO:
                beans.setnPedido((String) aValue);
                break;
            case IDFAZENDA:
                beans.setIdFazenda((Integer) aValue);
                break;
            case FAZENDA:
                beans.setFazenda((String) aValue);
                break;
            case IDCATEGORIA:
                beans.setIdCategoria((Integer) aValue);
                break;    
            case CATEGORIA:
                beans.setCategoria((String) aValue);
                break;
            case IDINSUMO:
                beans.setIdInsumo((Integer) aValue);
                break;
            case INSUMO:
                beans.setInsumo((String) aValue);
                break;    
            case UNIDADE:
                beans.setUnidade((String) aValue);
                break;
            case QUANTIDADE:
                beans.setQuantidade((Double) aValue);
                break;            
            case SIFRA:
                beans.setSifra((String) aValue);
                break;                
            case VALORUNIT:
                beans.setValorUnit((BigDecimal) aValue);
                break;                
            case VALORTOTAL:
                beans.setValorTotal((BigDecimal) aValue);
                break;                
            case QTENTREGA:
                beans.setQtEntregue((Double) aValue);
                break;                
            case SALDO:
                beans.setSaldoEntregar((Double) aValue);
                break; */
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

// retorna o socio referente a linha especidicada      
    public TbOperacoesBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbOperacoesBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbOperacoesBeans> beans) {
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

}
