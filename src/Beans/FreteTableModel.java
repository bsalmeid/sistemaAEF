/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Beans;

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
public class FreteTableModel extends AbstractTableModel {

    private List<TbFreteBeans>  linhas;
    
    public FreteTableModel(){
        linhas = new ArrayList<TbFreteBeans>();
    }
    
    public FreteTableModel(List<TbFreteBeans> listaDeFretes){
        linhas = new ArrayList<TbFreteBeans>(listaDeFretes);
    }

    private String [] colunas = new String[]{"ID", "Romaneio", "Compra", "Comprador", "Entrada", "Origem", "Destino", 
            "Transportadora", "Placa", "Nº Min", "Qt Min", "Qt Origem", "KM", "Vlr KM", 
            "Vlr Total", "Status", "StatusDB", "Situação" ,"Pagto"};
    
    private static final int ID = 0;
    private static final int ROMANEIO = 1;
    private static final int COMPRA = 2;
    private static final int COMPRADOR = 3;
    private static final int DT_ENTRADA = 4;
    private static final int ORIGEM = 5;
    private static final int DESTINO = 6;
    private static final int TRANSPORTADORA = 7;
    private static final int PLACA = 8;
    private static final int N_MIN = 9;
    private static final int Q_MIN = 10;
    private static final int Q_ORIGEM = 11;
    private static final int KM = 12;
    private static final int VLR_KM = 13;
    private static final int VLR_FRETE = 14;
    private static final int STATUS_ATUAL = 15;
    private static final int STATUS_DB = 16;
    private static final int SITUACAO_PAGTO = 17;
    private static final int DT_PAGTO = 18;
    
    
    
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
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case ID: 
                return Integer.class;
            case ROMANEIO: 
                return Integer.class;
            case COMPRA:
                return Integer.class;
            case COMPRADOR:
                return String.class;
            case DT_ENTRADA:
                return String.class;
            case ORIGEM:
                return String.class;
            case DESTINO:
                return String.class;
            case TRANSPORTADORA:
                return String.class;
            case PLACA:
                return String.class;
            case N_MIN:
                return String.class;
            case Q_MIN:
                return Integer.class;
            case Q_ORIGEM:
                return Integer.class;
            case KM:
                return Integer.class;
            case VLR_KM:
                return String.class;
            case VLR_FRETE:
                return String.class;
            case STATUS_ATUAL:
                return Boolean.class;
            case STATUS_DB:
                return Boolean.class;
            case SITUACAO_PAGTO:
               return String.class;
            case DT_PAGTO:
               return String.class;
                
            default: 
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }
    
    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        return columnIndex == TRANSPORTADORA || columnIndex == N_MIN || columnIndex == KM || columnIndex == VLR_KM || columnIndex == STATUS_ATUAL
                || columnIndex == SITUACAO_PAGTO || columnIndex == VLR_FRETE;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbFreteBeans frete = linhas.get(rowIndex);
        switch (columnIndex){
            case ID: 
                return frete.getID();
            case ROMANEIO:
                return frete.getnRomaneio();
            case COMPRA:
                return frete.getnCompra();
            case COMPRADOR:
                return frete.getComprador();
            case DT_ENTRADA:
                return frete.getDtEntrada();
            case ORIGEM:
                return frete.getOrigemGTA();
            case DESTINO:
                return frete.getDestino();
            case TRANSPORTADORA:
                return frete.getTransportadora();
            case PLACA:
                return frete.getPlaca();
            case N_MIN:
                return frete.getnMIN();
            case Q_MIN:
                return frete.getqMin();
            case Q_ORIGEM:
                return frete.getqOrigem();
            case KM:
                return frete.getKM();
            case VLR_KM:
                return frete.getVlrKM();
            case VLR_FRETE:
                return frete.getVlrFrete();
            case STATUS_ATUAL:
                return frete.isStatusAtual();
            case STATUS_DB:
                return frete.isStatusDB();
            case SITUACAO_PAGTO:
               return frete.getSituacaoPagto();            
            case DT_PAGTO:
               return frete.getDtPagto();
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex){
        //Pega o sócio referente  a linha especificada
        TbFreteBeans frete = linhas.get(rowIndex);
        
        switch (columnIndex){
            case KM: 
                frete.setKM((Integer) aValue);
                break;
            case TRANSPORTADORA: 
                frete.setTransportadora((String) aValue);
                break;
            case N_MIN: 
                frete.setnMIN((String) aValue);
                break;
            case VLR_KM:
                frete.setVlrKM(new BigDecimal(aValue.toString().replace(",", ".")));
                break;
            case VLR_FRETE:
                frete.setVlrFrete(new BigDecimal(aValue.toString().replace(",", ".")));
                break;
            case STATUS_ATUAL:
                frete.setStatusAtual((Boolean) aValue);
                break;    
            case SITUACAO_PAGTO:
                frete.setSituacaoPagto((String) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbFreteBeans getFrete(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addFrete(TbFreteBeans frete){
    //Adiciona registro
      linhas.add(frete);
    // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addListaDeFretes(List<TbFreteBeans> frete){
      //pega o tamanho antigo da tabela, que servirá como indice para o primeiro dos novos registros
      int indice = getRowCount();
      //adiciona os registros
      linhas.addAll(frete);
      //notifica a mudunça
      fireTableRowsInserted(indice, indice + frete.size());
  }  
  
  public void limpar(){
      //remove todos os elementos da lista sócios;
      linhas.clear();
      //notifica a mudança;
      fireTableDataChanged();
  }
          
}
  
    
    
    
