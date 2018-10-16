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
public class TableModelConsultaSaidaMercadoria extends AbstractTableModel {

    private List<TbResumoSaidaMercadoriaBeans> linhas;

    public TableModelConsultaSaidaMercadoria() {
        linhas = new ArrayList<TbResumoSaidaMercadoriaBeans>();
    }

    public TableModelConsultaSaidaMercadoria(List<TbResumoSaidaMercadoriaBeans> listaDeFretes) {
        linhas = new ArrayList<TbResumoSaidaMercadoriaBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID_Saida", "Data", "Operação", "Nº Doc", "ID_Item", "Codigo", "Descrição",
        "Quantidade", "Id Setor","Setor" ,"Id Aplicação", "Aplicação",  "ID_Localizador","Localizador"};

    public final int ID_SAIDA = 0;
    public final int DATA = 1;
    public final int OPERACAO = 2;
    public final int NDOC = 3;
    public final int ID_ITEM = 4;
    public final int CODIGO = 5;
    public final int DESCRICAO = 6;
    public final int QUANTIDADE = 7;
    public final int ID_SETOR = 8;
    public final int SETOR = 9;
    public final int ID_APLICACAO = 10;
    public final int APLICACAO = 11;
    public final int ID_LOCALIZADOR = 13;
    public final int LOCALIZADOR = 12;

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
            case ID_SAIDA:
                return Integer.class;
            case DATA:
                return String.class;
            case OPERACAO:
                return String.class;
            case NDOC:
                return Integer.class;
            case ID_ITEM:
                return Integer.class;
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case QUANTIDADE:
                return Double.class;
            case ID_SETOR:
                return Integer.class;
            case SETOR:
                return String.class;
            case ID_APLICACAO:
                return Integer.class;
            case APLICACAO:
                return String.class;
            case ID_LOCALIZADOR:
                return Integer.class;
            case LOCALIZADOR:
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
        TbResumoSaidaMercadoriaBeans beans = linhas.get(rowIndex);
             switch (columnIndex) {
            case ID_SAIDA:
                return beans.getID_SAIDA();
            case DATA:
                return beans.getData();
            case OPERACAO:
                return beans.getOperacao();
            case NDOC:
                return beans.getNDoc();
            case ID_ITEM:
                return beans.getID_ITEM();
            case CODIGO:
                return beans.getCodigo();    
            case DESCRICAO:
                return beans.getDescricao();
            case QUANTIDADE:
                return beans.getQuantidade();
            case ID_SETOR:
                return beans.getIdSetor();
            case SETOR:
                return beans.getSetor();
            case ID_APLICACAO:
                return beans.getIdAplicacao();
            case APLICACAO:
                return beans.getAplicacao();
            case ID_LOCALIZADOR:
                return beans.getIdLocalizador();
            case LOCALIZADOR:
                return beans.getLocalizador();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbResumoSaidaMercadoriaBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID_SAIDA:
                beans.setID_SAIDA((Integer) aValue);
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

    public TbResumoSaidaMercadoriaBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbResumoSaidaMercadoriaBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbResumoSaidaMercadoriaBeans> beans) {
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

    public List<TbResumoSaidaMercadoriaBeans> getLista() {
        return linhas;
    }

}
