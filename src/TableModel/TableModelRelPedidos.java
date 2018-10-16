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
public class TableModelRelPedidos extends AbstractTableModel {

    private List<TbRelPedidosInsumosBeans>  linhas;
    
    public TableModelRelPedidos(){
        linhas = new ArrayList<TbRelPedidosInsumosBeans>();
    }
    
    public TableModelRelPedidos(List<TbRelPedidosInsumosBeans> listaDeFretes){
        linhas = new ArrayList<TbRelPedidosInsumosBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"Status", "ID Ped", "Emissão" ,"Vencimento", "Nº Pedido",
        "Safra","Id Fornecedor","Fornecedor" ,"Id Faz" ,"Fazenda", "Moeda", "Sifra", "Valor do Pedido",
        "Valor Insumos","Valor Agendado Moeda" ,"Valor Pago Moeda", "Valor Pago - R$", "Valor NF's", "Status Pagto" };
    
    public final int STATUS = 0;
    public final int ID = 1;
    public final int DATAEMISSAO = 2;
    public final int VENCIMENTO = 3;
    public final int NPEDIDO = 4;
    public final int SAFRA = 5;
    public final int IDFORNECEDOR = 6; 
    public final int FORNECEDOR = 7;
    public final int IDFAZENDA = 8;
    public final int FAZENDA = 9;  
    public final int MOEDA = 10;
    public final int SIFRA = 11;
    public final int VALORPEDIDO = 12;
    public final int VALORINSUMOS = 13;
    public final int VALORAGENDADOMOEDA = 14;
    public final int VALORPAGOMOEDA = 15;
    public final int VALORPAGOREAIS = 16;
    public final int VALORNF = 17;
    public final int STATUSPAGTO = 18;
    
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
            case STATUS: 
                return Boolean.class;
            case ID: 
                return Integer.class;
            case DATAEMISSAO: 
                return String.class;            
            case VENCIMENTO: 
                return String.class;
            case NPEDIDO:
                return String.class;
            case SAFRA:
                return String.class;
            case IDFORNECEDOR:
                return Integer.class;
            case FORNECEDOR:
                return String.class;
            case IDFAZENDA:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case MOEDA:
                return String.class;
            case SIFRA:
                return String.class;    
            case VALORPEDIDO:
                return BigDecimal.class;
            case VALORINSUMOS:
                return BigDecimal.class;    
            case VALORAGENDADOMOEDA:
                return BigDecimal.class;    
            case VALORPAGOMOEDA:
                return BigDecimal.class;                  
            case VALORPAGOREAIS:
                return BigDecimal.class;    
            case VALORNF:
                return BigDecimal.class;
            case STATUSPAGTO:
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
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbRelPedidosInsumosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case STATUS: 
                return beans.isStatus();
            case ID:
                return beans.getId();
            case DATAEMISSAO:
                return beans.getDataEmissao();
            case VENCIMENTO:
                return beans.getVencimento();
            case NPEDIDO:
                return beans.getnPedido();
            case SAFRA:
                return beans.getSafra();
            case IDFORNECEDOR:
                return beans.getIdFornecedor();
            case FORNECEDOR:
                return beans.getFornecedor(); 
            case IDFAZENDA:
                return beans.getIdFazenda();
            case FAZENDA:
                return beans.getFazenda();
            case MOEDA:
                return beans.getMoeda();    
            case SIFRA:
                return beans.getSifra();    
            case VALORPEDIDO:
                return beans.getValorPedido(); 
            case VALORINSUMOS:
                return beans.getValorInsumos(); 
            case VALORAGENDADOMOEDA:
                return beans.getValorAgendadoMoeda(); 
            case VALORPAGOMOEDA:
                return beans.getValorPagoMoeda(); 
            case VALORPAGOREAIS:
                return beans.getValorPagoReais();
            case VALORNF:
                return beans.getValorNF(); 
            case STATUSPAGTO:
                return beans.getStatusPagto();     
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbRelPedidosInsumosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setId((Integer) aValue);
                break;            
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbRelPedidosInsumosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbRelPedidosInsumosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbRelPedidosInsumosBeans> beans){
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
  
    
    
    
