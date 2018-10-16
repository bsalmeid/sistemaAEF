/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import NFe.NFeProdutosBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelProdutosNFe extends AbstractTableModel {

    private List<NFeProdutosBeans> linhas;

    public TableModelProdutosNFe() {
        linhas = new ArrayList<NFeProdutosBeans>();
    }

    public TableModelProdutosNFe(List<NFeProdutosBeans> listaDeFretes) {
        linhas = new ArrayList<NFeProdutosBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID_DB", "ID_CAD", "Código", "Descrição", "NCM", "CEST", "Unid", "Quant.", "Valor Unit",
        "Valor Total", "Valor Desconto", "Valor Unit", "Localizador", "Id_Localizador"};

    public final int ID_DB = 0;
    public final int ID_CADASTRO = 1;
    public final int CPROD = 2;
    public final int XPROD = 3;
    public final int NCM = 4;
    public final int CEST = 5;
    public final int UCOM = 6;
    public final int QCOM = 7;
    public final int VUNCOM = 8;
    public final int VPROD = 9;
    public final int VDESC = 10;
    public final int VALORUNITFINAL = 11;
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
            case ID_DB:
                return Integer.class;
            case ID_CADASTRO:
                return Integer.class;
            case CPROD:
                return String.class;
            case XPROD:
                return String.class;
            case NCM:
                return String.class;
            case CEST:
                return String.class;
            case UCOM:
                return String.class;
            case QCOM:
                return Double.class;
            case VUNCOM:
                return Double.class;
            case VPROD:
                return Double.class;
            case VDESC:
                return Double.class;
            case VALORUNITFINAL:
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
        if (columnIndex == LOCALIZADOR) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        NFeProdutosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_DB:
                return beans.getID_DB();
            case ID_CADASTRO:
                return beans.getID_Cadastro();
            case CPROD:
                return beans.getcProd();
            case XPROD:
                return beans.getxProd();
            case NCM:
                return beans.getNCM();
            case CEST:
                return beans.getCEST();
            case UCOM:
                return beans.getuCom();
            case QCOM:
                return beans.getqCom();
            case VUNCOM:
                return beans.getvUnCom();
            case VPROD:
                return beans.getvProd();
            case VDESC:
                return beans.getvDesc();
            case VALORUNITFINAL:
                return beans.getValorUnitFinal();
            case LOCALIZADOR:
                return beans.getLocalizador();
            case ID_LOCALIZADOR:
                return beans.getId_localizador();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        NFeProdutosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID_DB:
                beans.setID_DB((Integer) aValue);
                break;
            case ID_CADASTRO:
                beans.setID_Cadastro((Integer) aValue);
                break;
            case LOCALIZADOR:
                beans.setLocalizador((String) aValue);
                break;
            case ID_LOCALIZADOR:
                beans.setId_localizador((Integer) aValue);
                break;
            /*case ID_MARCA_APLIC:
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

    public NFeProdutosBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(NFeProdutosBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<NFeProdutosBeans> beans) {
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

    public void SetBeans(Integer rowIndex, NFeProdutosBeans bean) {
        linhas.set(rowIndex, bean);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public List<NFeProdutosBeans> getLista() {
        return linhas;
    }

}
