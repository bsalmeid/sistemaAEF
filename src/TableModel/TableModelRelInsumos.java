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
public class TableModelRelInsumos extends AbstractTableModel {

    private List<TbRelatorioInsumosBeans>  linhas;
    
    public TableModelRelInsumos(){
        linhas = new ArrayList<TbRelatorioInsumosBeans>();
    }
    
    public TableModelRelInsumos(List<TbRelatorioInsumosBeans> listaDeFretes){
        linhas = new ArrayList<TbRelatorioInsumosBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "Status", "ID NF" ,"Item Pedido", "Data Entrada",
        "Motivo","Fornecedor","Id Pedido" ,"Nº Pedido", "Nº Doc" ,"Placa", "Id Categoria", "Categoria", 
        "Id Insumo", "Insumo","Quantidade" ,"Valor Unit", "Valor Total"};
    
    private static final int IDENTRADA = 0;
    private static final int STATUS = 1;
    private static final int IDNF = 2;
    private static final int ITEMPEDIDO = 3;
    private static final int DATAENTRADA = 4;
    private static final int MOTIVO = 5;
    private static final int FORNECEDOR = 6; 
    private static final int IDPEDIDO = 7;
    private static final int PEDIDO = 8;
    private static final int NDOC = 9;  
    private static final int PLACA = 10;
    private static final int IDCATEGORIA = 11;
    private static final int CATEGORIA = 12;
    private static final int IDINSUMO = 13;
    private static final int INSUMO = 14;
    private static final int QUANTIDADE = 15;
    private static final int VLRUNIT = 16;
    private static final int VLRTOTAL = 17;
    
    
    @Override
    public int getRowCount(){
        return linhas.size();
    }
    @Override
    public int getColumnCount(){
        return colunas.length;
    }
    
    @Override
    public String getColumnName(int columnIndex){
        return colunas[columnIndex]; 
    }
    
    public void excluirLinha(int rowIndex){
        linhas.remove(rowIndex);
        fireTableRowsDeleted(rowIndex, rowIndex);
    }
    
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case IDENTRADA: 
                return Integer.class;
            case STATUS: 
                return Boolean.class;
            case IDNF: 
                return Integer.class;            
            case ITEMPEDIDO: 
                return Integer.class;
            case DATAENTRADA:
                return String.class;
            case IDCATEGORIA:
                return Integer.class;
            case CATEGORIA:
                return String.class;
            case IDINSUMO:
                return Integer.class;
            case INSUMO:
                return String.class;
            case QUANTIDADE:
                return Double.class;
            case MOTIVO:
                return String.class;
            case FORNECEDOR:
                return String.class;    
            case IDPEDIDO:
                return Integer.class;
            case PEDIDO:
                return String.class;    
            case NDOC:
                return Integer.class;    
            case PLACA:
                return Integer.class;                  
            case VLRUNIT:
                return BigDecimal.class;    
            case VLRTOTAL:
                return BigDecimal.class;
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
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbRelatorioInsumosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case IDENTRADA: 
                return beans.getIdEntrada();
            case STATUS:
                return beans.isStatus();
            case IDNF:
                return beans.getIdNF();
            case ITEMPEDIDO:
                return beans.getIdItemPedido();
            case DATAENTRADA:
                return beans.getDataEntrada();
            case IDCATEGORIA:
                return beans.getIdCategoria();
            case CATEGORIA:
                return beans.getCategoria();
            case IDINSUMO:
                return beans.getIdInsumo(); 
            case INSUMO:
                return beans.getInsumo();
            case QUANTIDADE:
                return beans.getQuantidade();
            case MOTIVO:
                return beans.getMotivo();    
            case FORNECEDOR:
                return beans.getFornecedor();    
            case IDPEDIDO:
                return beans.getIdPedido(); 
            case PEDIDO:
                return beans.getPedido(); 
            case NDOC:
                return beans.getnDoc(); 
            case PLACA:
                return beans.getPlaca(); 
            case VLRUNIT:
                return beans.getVlrUnit(); 
            case VLRTOTAL:
                return beans.getVlrTotal(); 
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbRelatorioInsumosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case IDENTRADA:
                beans.setIdEntrada((Integer) aValue);
                break;            
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbRelatorioInsumosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbRelatorioInsumosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbRelatorioInsumosBeans> beans){
      //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
      int indice = getRowCount();
      //adiciona os registros
      linhas.addAll(beans);
      //notifica a mudunça
      fireTableRowsInserted(indice, indice + beans.size());
  }  
  
  public void deletarUltimaLinha(){
      int ultimoIndice = getRowCount() -1;
      linhas.remove(ultimoIndice);
      fireTableRowsDeleted(ultimoIndice, ultimoIndice);
  }
  
  
  public void limpar(){
      //remove todos os elementos da lista sócios;
      linhas.clear();
      //notifica a mudança;
      fireTableDataChanged();
  }
       
}
  
    
    
    
