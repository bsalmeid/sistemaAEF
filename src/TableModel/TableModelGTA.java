/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.*;
import Icones.FormatarICO;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelGTA extends AbstractTableModel {

    private List<TbGTABeans>  linhas;
    
    public TableModelGTA(){
        linhas = new ArrayList<TbGTABeans>();
    }
    
    public TableModelGTA(List<TbGTABeans> listaDeFretes){
        linhas = new ArrayList<TbGTABeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "Romaneio" ,"Data", "Nº GTA", "Origem","Destino",
        "Status" ,"M 0-12", "M 13-24", "M 25-36", 
        "M > 36", " M Total" , "F 0-12", "F 13-24", "F 25-36", "F > 36", "F Total", "Q Total"};
    
    private static final int IDTABELA = 0;
    private static final int ROMANEIO = 1;
    private static final int DATA = 2;
    private static final int NGTA = 3;
    private static final int ORIGEM = 4;
    private static final int DESTINO = 5;
    private static final int STATUS = 6;
    private static final int M012 = 7;
    private static final int M1324 = 8;
    private static final int M2536 = 9;
    private static final int M36 = 10;
    private static final int MT = 11;
    private static final int F012 = 12;
    private static final int F1324 = 13;
    private static final int F2536 = 14;
    private static final int F36 = 15;
    private static final int FT = 16;
    private static final int QTOTAL = 17;
    
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
            case IDTABELA: 
                return Integer.class;
            case ROMANEIO: 
                return Integer.class;
            case DATA: 
                return String.class;    
            case NGTA:
                return Integer.class;
            case ORIGEM:
                return String.class;
            case DESTINO:
                return String.class;
            case STATUS:
                return Boolean.class;
            case M012:
                return Integer.class;
            case M1324:
                return Integer.class;
            case M2536:
                return Integer.class;
            case M36:
                return Integer.class;    
            case MT:
                return Integer.class;
            case F012:
                return Integer.class;    
            case F1324:
                return Integer.class;    
            case F2536:
                return Integer.class;    
            case F36:
                return Integer.class;    
            case FT:
                return Integer.class;
            case QTOTAL:
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
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbGTABeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case IDTABELA: 
                return beans.getIdTabela();
            case ROMANEIO:
                return beans.getRomaneio();
            case DATA:
                return beans.getData();    
            case NGTA:
                return beans.getnGTA();
            case ORIGEM:
                return beans.getOrigem();
            case DESTINO:
                return beans.getDestino();
            case STATUS:
                return beans.isStatusConfGTA();
            case M012:
                return beans.getM012(); 
            case M1324:
                return beans.getM1324();
            case M2536:
                return beans.getM2536();
            case M36:
                return beans.getM36();    
            case MT:
                return beans.getMachoTotal();    
            case F012:
                return beans.getF012(); 
            case F1324:
                return beans.getF1324(); 
            case F2536:
                return beans.getF2536(); 
            case F36:
                return beans.getF36();
            case FT:
                return beans.getFemeaTotal();
            case QTOTAL:
                return beans.getQTotal(); 
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbGTABeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ORIGEM:
                beans.setOrigem((String) aValue);
                break;
            case DESTINO:
                beans.setDestino((String) aValue);
                break;
            case STATUS:
                beans.setStatusConfGTA((Boolean) aValue);
                break;
            case M012:
                beans.setM012((Integer) aValue);
                break;
            case M1324:
                beans.setM1324((Integer) aValue);
                break;    
            case M2536:
                beans.setM2536((Integer) aValue);
                break;
            case M36:
                beans.setM36((Integer) aValue);
                break;
            case MT:
                beans.setMachoTotal((Integer) aValue);
                break;    
            case F012:
                beans.setF012((Integer) aValue);
                break;
            case F1324:
                beans.setF1324((Integer) aValue);
                break;            
            case F2536:
                beans.setF2536((Integer) aValue);
                break;                
            case F36:
                beans.setF36((Integer) aValue);
                break;                
            case FT:
                beans.setFemeaTotal((Integer) aValue);
                break;                                           
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbGTABeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbGTABeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbGTABeans> beans){
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
  
    
    
    
