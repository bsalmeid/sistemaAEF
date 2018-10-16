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
public class TableModelEntInsumos extends AbstractTableModel {

    private List<TbEntradaInsumosBeans>  linhas;
    
    public TableModelEntInsumos(){
        linhas = new ArrayList<TbEntradaInsumosBeans>();
    }
    
    public TableModelEntInsumos(List<TbEntradaInsumosBeans> listaDeFretes){
        linhas = new ArrayList<TbEntradaInsumosBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "Status", "ItemPedido" ,"Id Pedido", "Nº Pedido", "Id Fazenda","Fazenda","Id Categoria" ,"Categoria", "Id Insumo", "Insumo", "Unid", 
          "Quant", "Sifra", "Valor Unit", "Valor Total", "Quant. Entregue", "Saldo a Entregar"};
    
    private static final int ID = 0;
    private static final int STATUS = 1;
    private static final int ITEMPEDIDO = 2;
    private static final int IDPEDIDO = 3;
    private static final int NPEDIDO = 4;
    private static final int IDFAZENDA = 5;
    private static final int FAZENDA = 6;
    private static final int IDCATEGORIA = 7;
    private static final int CATEGORIA = 8;
    private static final int IDINSUMO = 9;
    private static final int INSUMO = 10;
    private static final int UNIDADE = 11;
    private static final int QUANTIDADE = 12;
    private static final int SIFRA = 13;
    private static final int VALORUNIT = 14;
    private static final int VALORTOTAL = 15;
    private static final int QTENTREGA = 16;
    private static final int SALDO = 17;
    
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
            case ID: 
                return Integer.class;
            case STATUS: 
                return Boolean.class;
            case ITEMPEDIDO: 
                return Integer.class;
            case IDPEDIDO:
                return Integer.class;
            case NPEDIDO:
                return String.class;
            case IDFAZENDA:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case IDCATEGORIA:
                return Integer.class;
            case CATEGORIA:
                return String.class;
            case IDINSUMO:
                return Integer.class;
            case INSUMO:
                return String.class;    
            case UNIDADE:
                return String.class;
            case QUANTIDADE:
                return Double.class;    
            case SIFRA:
                return String.class;    
            case VALORUNIT:
                return BigDecimal.class;    
            case VALORTOTAL:
                return BigDecimal.class;    
            case QTENTREGA:
                return Double.class;
            case SALDO:
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
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbEntradaInsumosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return beans.getId();
            case STATUS:
                return beans.isStatus();
            case ITEMPEDIDO:
                return beans.getItemListaPedidos();
            case IDPEDIDO:
                return beans.getIdPedido();
            case NPEDIDO:
                return beans.getnPedido();
            case IDFAZENDA:
                return beans.getIdFazenda();
            case FAZENDA:
                return beans.getFazenda();
            case IDCATEGORIA:
                return beans.getIdCategoria(); 
            case CATEGORIA:
                return beans.getCategoria();
            case IDINSUMO:
                return beans.getIdInsumo();
            case INSUMO:
                return beans.getInsumo();    
            case UNIDADE:
                return beans.getUnidade();    
            case QUANTIDADE:
                return beans.getQuantidade(); 
            case SIFRA:
                return beans.getSifra(); 
            case VALORUNIT:
                return beans.getValorUnit(); 
            case VALORTOTAL:
                return beans.getValorTotal(); 
            case QTENTREGA:
                return beans.getQtEntregue(); 
            case SALDO:
                return beans.getSaldoEntregar(); 
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbEntradaInsumosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setId((Integer) aValue);
                break;
            case STATUS:
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
                break;                
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbEntradaInsumosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbEntradaInsumosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbEntradaInsumosBeans> beans){
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
  
    
    
    
