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
public class TableModelEstoqueInsumo extends AbstractTableModel {

    private List<TbEstoqueInsumosBeans>  linhas;
    
    public TableModelEstoqueInsumo(){
        linhas = new ArrayList<TbEstoqueInsumosBeans>();
    }
    
    public TableModelEstoqueInsumo(List<TbEstoqueInsumosBeans> listaDeFretes){
        linhas = new ArrayList<TbEstoqueInsumosBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "Insumo" ,"Categoria", "Fazenda", 
        "Entrada","Saida","Estoque" ," Dose Media", "Área Aplicável"};
    
    public final int ID = 0;
    public final int INSUMO = 1;
    public final int CATEGORIA = 2;
    public final int FAZENDA = 3;
    public final int ENTRADAPERIODO = 4;
    public final int SAIDAPERIODO = 5;
    public final int ESTOQUE = 6;
    public final int DOSEMEDIA = 7;
    public final int AREA = 8;

    
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
            case INSUMO: 
                return String.class;
            case CATEGORIA:
                return String.class;
            case FAZENDA:
                return String.class;
            case ENTRADAPERIODO:
                return Double.class;
            case SAIDAPERIODO:
                return Double.class;
            case ESTOQUE:
                return Double.class;
            case DOSEMEDIA:
                return Double.class;
            case AREA:
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
        TbEstoqueInsumosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return beans.getID();
            case INSUMO:
                return beans.getInsumo();
            case CATEGORIA:
                return beans.getCategoria();
            case FAZENDA:
                return beans.getFazenda();
            case ENTRADAPERIODO:
                return beans.getEntradaPeriodo();
            case SAIDAPERIODO:
                return beans.getSaidaPeriodo();
            case ESTOQUE:
                return beans.getEstoque(); 
            case DOSEMEDIA:
                return beans.getDoseMedia();
            case AREA:
                return beans.getAreaAplicavel();
            
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbEstoqueInsumosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
                beans.setID((Integer) aValue);
                break;
        /*    case STATUS:
                beans.setStatus((Boolean) aValue);
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
                break;                                          */
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbEstoqueInsumosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbEstoqueInsumosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbEstoqueInsumosBeans> beans){
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
  
    
    
    
