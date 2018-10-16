/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NFe;

import TableModel.*;
import NFe.NFeBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelNFe extends AbstractTableModel {

    private List<NFeBeans> linhas;

    public TableModelNFe() {
        linhas = new ArrayList<NFeBeans>();
    }

    public TableModelNFe(List<NFeBeans> listaDeFretes) {
        linhas = new ArrayList<NFeBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "ID_Forn", "Chave NF", "cUF", "cNF", "Operação", "Nº NF", "" , "Emissão",
        "cMunFG", "CNPJ", "Razão Social", "Nome Fantasia", "Logradouro", "Número", "Bairro", "cMun", "Municipio", "UF",
        "I.E Destino", "Valor NF"};

    public final int ID = 0;
    public final int ID_FORNECEDOR = 1;
    public final int CHNFE = 2;
    public final int CUF = 3;
    public final int CNF = 4;
    public final int NATOP = 5;
    public final int NNF = 6;
    public final int SELECIONADO = 7;
    public final int DHEMI = 8;
    public final int CMUNFG = 9;
    public final int CNPJ = 10;
    public final int XNOME = 11;
    public final int XFANT = 12;
    public final int XLGR = 13;
    public final int NRO = 14;
    public final int XBAIRRO = 15;
    public final int CMUN = 16;
    public final int XMUN = 17;
    public final int UF = 18;
    public final int IEDEST = 19;
    public final int VNF = 20;

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
            case ID_FORNECEDOR:
                return Integer.class;
            case CHNFE:
                return String.class;
            case CUF:
                return Integer.class;
            case CNF:
                return Integer.class;
            case NATOP:
                return String.class;
            case NNF:
                return Integer.class;
            case SELECIONADO:
                return Boolean.class;
            case DHEMI:
                return String.class;
            case CMUNFG:
                return Integer.class;
            case CNPJ:
                return String.class;
            case XNOME:
                return String.class;
            case XFANT:
                return String.class;
            case XLGR:
                return String.class;
            case NRO:
                return String.class;
            case XBAIRRO:
                return String.class;
            case CMUN:
                return Integer.class;
            case XMUN:
                return String.class;
            case UF:
                return String.class;
            case IEDEST:
                return String.class;
            case VNF:
                return Double.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        if (columnIndex == SELECIONADO) {
            return true;
        }
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o Socio referente a linha especificada;
        NFeBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case ID_FORNECEDOR:
                return beans.getID_Fornecedor();
            case CHNFE:
                return beans.getChNFe();
            case CUF:
                return beans.getcUF();
            case CNF:
                return beans.getcNF();
            case NATOP:
                return beans.getNatOp();
            case NNF:
                return beans.getnNF();
            case SELECIONADO:
                return beans.getSelecionado();    
            case DHEMI:
                return beans.getDhEmi();
            case CMUNFG:
                return beans.getcMunFG();
            case CNPJ:
                return beans.getCNPJ();
            case XNOME:
                return beans.getxNome();
            case XFANT:
                return beans.getxFant();
            case XLGR:
                return beans.getxLgr();
            case NRO:
                return beans.getNro();
            case XBAIRRO:
                return beans.getxBairro();
            case CMUN:
                return beans.getcMun();
            case XMUN:
                return beans.getxMun();
            case UF:
                return beans.getUF();
            case IEDEST:
                return beans.getIEDest();
            case VNF:
                return beans.getvNF();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        NFeBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
            case SELECIONADO:
                beans.setSelecionado((Boolean) aValue);
                break;
          /*  case ID_CATEGORIA_ITEM:
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

    public NFeBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(NFeBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<NFeBeans> beans) {
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

    public List<NFeBeans> getLista() {
        return linhas;
    }

}
