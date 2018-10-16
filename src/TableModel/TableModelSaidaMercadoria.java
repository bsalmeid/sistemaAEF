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
public class TableModelSaidaMercadoria extends AbstractTableModel {

    private List<TbSaidaAlmoxarifadoBeans> linhas;

    public TableModelSaidaMercadoria() {
        linhas = new ArrayList<TbSaidaAlmoxarifadoBeans>();
    }

    public TableModelSaidaMercadoria(List<TbSaidaAlmoxarifadoBeans> listaDeFretes) {
        linhas = new ArrayList<TbSaidaAlmoxarifadoBeans>(listaDeFretes);
    }

    private String[] colunas = new String[]{"ID", "Id Item", "Código","Descrição", "Unidade", "Quantidade", "Id Aplicação", "Aplicação",
        "Id_Localizador", "Localizador", "Id_Setor_Serviço", "Setor Serviço", "Recebedor", "Observação"};

    public final int ID = 0;
    public final int ID_ITEM = 1;
    public final int CODIGO = 2;
    public final int DESCRICAO = 3;
    public final int UNIDADE = 4;
    public final int QUANTIDADE = 5;
    public final int ID_APLICACAO = 6;
    public final int APLICACAO = 7;
    public final int ID_LOCALIZADOR = 8;
    public final int LOCALIZADOR = 9;
    public final int ID_SETOR_SERVICO = 10;
    public final int SETOR_SERVICO = 11;
    public final int RECEBEDOR = 12;
    public final int OBSERVACAO = 13;

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
            case ID_ITEM:
                return Integer.class;
            case CODIGO:
                return String.class;
            case DESCRICAO:
                return String.class;
            case UNIDADE:
                return String.class;
            case QUANTIDADE:
                return Double.class;
            case ID_APLICACAO:
                return Integer.class;
            case APLICACAO:
                return String.class;
            case ID_LOCALIZADOR:
                return Integer.class;
            case LOCALIZADOR:
                return String.class;
            case ID_SETOR_SERVICO:
                return Integer.class;
            case SETOR_SERVICO:
                return String.class;
            case RECEBEDOR:
                return String.class;
            case OBSERVACAO:
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
        //pega o Socio referente a linha especificada;
        TbSaidaAlmoxarifadoBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case ID_ITEM:
                return beans.getIdItem();
            case CODIGO:
                return beans.getCodigo();    
            case DESCRICAO:
                return beans.getDescricao();
            case UNIDADE:
                return beans.getUnidade();
            case QUANTIDADE:
                return beans.getQuantidade();
            case ID_APLICACAO:
                return beans.getIdAplicacao();
            case APLICACAO:
                return beans.getAplicacao();
            case ID_LOCALIZADOR:
                return beans.getIdLocalizador();
            case LOCALIZADOR:
                return beans.getLocalizador();
            case ID_SETOR_SERVICO:
                return beans.getIdSetorServico();
            case  SETOR_SERVICO:
                return beans.getSetorServico();
            case RECEBEDOR:
                return beans.getRecebedor();
            case OBSERVACAO:
                return beans.getObservacao();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbSaidaAlmoxarifadoBeans  beans = linhas.get(rowIndex);

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
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public TbSaidaAlmoxarifadoBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(TbSaidaAlmoxarifadoBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<TbSaidaAlmoxarifadoBeans> beans) {
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

    public List<TbSaidaAlmoxarifadoBeans> getLista() {
        return linhas;
    }
    
    public void SetBeans(Integer rowIndex, TbSaidaAlmoxarifadoBeans bean) {
        linhas.set(rowIndex, bean);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    
    
    
}
