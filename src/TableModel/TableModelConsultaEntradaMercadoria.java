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
public class TableModelConsultaEntradaMercadoria extends AbstractTableModel {

    private List<TbResumoEntradaMercadoriaBeans> linhas;

    public TableModelConsultaEntradaMercadoria() {
        linhas = new ArrayList<TbResumoEntradaMercadoriaBeans>();
    }

    public TableModelConsultaEntradaMercadoria(List<TbResumoEntradaMercadoriaBeans> listaDeFretes) {
        linhas = new ArrayList<TbResumoEntradaMercadoriaBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID_Entrada", "Data", "Operação", "Nº Doc", "ID_Fornecedor","Fornecedor", "CNPJ",
    "ID_Item", "Código", "Descrição", "Quantidade", "Valor Unitário", "Localizador", "ID_Local" };

    public final int ID_ENTRADA = 0;
    public final int DATA = 1;
    public final int OPERACAO = 2;
    public final int DOCUMENTO_ENTRADA = 3;
    public final int ID_FORNECEDOR = 4;
    public final int FORNECEDOR = 5;
    public final int CNPJ = 6;
    public final int ID_ITEM = 7;
    public final int CODIGO = 8;
    public final int DESCRICAO = 9;
    public final int QUANTIDADE = 10;
    public final int VALORUNIT = 11;
    public final int LOCALIZADOR = 12;
    public final int ID_LOCALIZADOR = 13;
    
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
            case ID_ENTRADA:
                return Integer.class;
            case DATA:
                return String.class;
            case OPERACAO:
                return String.class;
            case DOCUMENTO_ENTRADA:
                return Integer.class;
            case ID_FORNECEDOR:
                return Integer.class;    
            case FORNECEDOR:
                return String.class;
            case CNPJ:
                return String.class;
            case ID_ITEM:
                return Integer.class;
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case QUANTIDADE:
                return Double.class;    
            case VALORUNIT:
                return Double.class;
            case LOCALIZADOR:
                return String.class;
            case ID_LOCALIZADOR:
                return Integer.class;    
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
        TbResumoEntradaMercadoriaBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_ENTRADA:
                return beans.getID_Entrada();
            case DATA:
                return beans.getData();
            case OPERACAO:
                return beans.getOperacao();
            case DOCUMENTO_ENTRADA:
                return beans.getDocumentoEntrada();
            case FORNECEDOR:
                return beans.getFornecedor();    
            case ID_FORNECEDOR:
                return beans.getID_Fornecedor();
            case CNPJ:
                return beans.getCNPJ();
            case ID_ITEM:
                return beans.getID_Item();
            case CODIGO:
                return beans.getCodigo();
            case DESCRICAO:
                return beans.getDescricao();
            case QUANTIDADE:
                return beans.getQuantidade();
            case VALORUNIT:
                return beans.getValorUnit();
            case LOCALIZADOR:
                return beans.getLocalizador();
            case ID_LOCALIZADOR:
                return beans.getID_Localizador();    
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbResumoEntradaMercadoriaBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID_ENTRADA:
                beans.setID_Entrada((Integer) aValue);
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
    
    public TbResumoEntradaMercadoriaBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbResumoEntradaMercadoriaBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbResumoEntradaMercadoriaBeans> beans) {
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

    public List<TbResumoEntradaMercadoriaBeans> getLista() {
        return linhas;
    }

}
