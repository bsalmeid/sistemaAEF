/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;


import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelDadosClima extends AbstractTableModel {

    private List<TbDadosClimaticosBeans>  linhas;
    
    public TableModelDadosClima(){
        linhas = new ArrayList<TbDadosClimaticosBeans>();
    }
    
    public TableModelDadosClima(List<TbDadosClimaticosBeans> listaDeFretes){
        linhas = new ArrayList<TbDadosClimaticosBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "ID_OS", "ID_OP" ,"Data", "Hora", "Vento","Umidade","Temperatura" ,"Chuva"};
    
    private static final int ID = 0;
    private static final int ID_OS = 1;
    private static final int ID_OP = 2;
    private static final int DATA = 3;
    private static final int HORA = 4;
    private static final int VENTO = 5;
    private static final int UMIDADE = 6;
    private static final int TEMPERATURA = 7;
    private static final int CHUVA = 8;
    
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
            case ID_OS: 
                return Integer.class;
            case ID_OP: 
                return Integer.class;
            case DATA:
                return String.class;
            case HORA:
                return Time.class;
            case VENTO:
                return Double.class;
            case UMIDADE:
                return Double.class;
            case TEMPERATURA:
                return Double.class;
            case CHUVA:
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
        return true;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbDadosClimaticosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return beans.getID();
            case ID_OS:
                return beans.getID_OS();
            case ID_OP:
                return beans.getID_OP();
            case DATA:
                return beans.getData();
            case HORA:
                return beans.getHora();
            case VENTO:
                return beans.getVento();
            case UMIDADE:
                return beans.getUmidade();
            case TEMPERATURA:
                return beans.getTemperatura(); 
            case CHUVA:
                return beans.getChuva();
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbDadosClimaticosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case DATA:
                beans.setData((String) aValue);
                break;
            case HORA:
                beans.setHora((Time) aValue);
                break;
            case VENTO:
                beans.setVento((Double) aValue);
                break;
            case UMIDADE:
                beans.setUmidade((Double) aValue);
                break;
            case TEMPERATURA:
                beans.setTemperatura((Double) aValue);
                break;
            case CHUVA:
                beans.setChuva((Double) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbDadosClimaticosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbDadosClimaticosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbDadosClimaticosBeans> beans){
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
  
    
    
    
