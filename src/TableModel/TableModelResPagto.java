/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultRowSorter;
import javax.swing.RowSorter;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelResPagto extends AbstractTableModel {

    private List<TbResPagamentosBeans>  linhas;
    
        
    public TableModelResPagto(){
        linhas = new ArrayList<TbResPagamentosBeans>();
    }
    
    public TableModelResPagto(List<TbResPagamentosBeans> listaPagtos){
        linhas = new ArrayList<TbResPagamentosBeans>(listaPagtos);
    }

    private String [] colunas = new String[]{"ID","Conta de Origem", "Data Prevista",
        "Data Pagto","Favorecido" ,"Forma de Pgto", "Nº Doc", 
        "Banco de Destino","Agencia","Conta","Moeda", "Valor em Moeda", "Taxa", "Valor Previsto"
         , "Valor Pago", "Valor NF's", "Classificado", "Status"  };
    
    private static final int IDPAGTO = 0;
    private static final int CONTAORIGEM = 1;
    private static final int DTPREVISTA = 2;
    private static final int DTPAGTO = 3;
    private static final int TITULAR = 4;
    private static final int FORMAPAGTO = 5;
    private static final int NDOC = 6;
    private static final int BANCODESTINO = 7;
    private static final int AGENCIA = 8;
    private static final int CONTA = 9;
    private static final int MOEDA = 10;
    private static final int VALOREMMOEDA = 11;
    private static final int TAXA = 12;
    private static final int VALORPREVISTO = 13;
    private static final int VALORPAGO = 14;
    private static final int VALORNF = 15;
    private static final int VALORCLASSIFICADO = 16;
    private static final int STATUS = 17;
    
    
    
    
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
    
    public void addRow(TbResPagamentosBeans Beans){
      int indice = getRowCount()- 1;
        linhas.add(Beans);
        fireTableRowsInserted(indice , indice );
    }
    
    @Override
    public Class<?> getColumnClass(int columnIndex){
        switch (columnIndex){
            case IDPAGTO: 
                return Integer.class;
            case CONTAORIGEM: 
                return String.class; 
            case DTPREVISTA:
                return String.class;
            case DTPAGTO:
                return String.class;
            case TITULAR:
                return String.class;
            case FORMAPAGTO: 
                return String.class;
            case NDOC: 
                return Integer.class;  
            case BANCODESTINO: 
                return String.class;    
            case AGENCIA:
                return String.class;
            case CONTA:
                return String.class;
            case MOEDA:
                return String.class;   
            case VALOREMMOEDA:
                return BigDecimal.class;    
            case TAXA:
                return BigDecimal.class;
            case VALORPREVISTO:
                return BigDecimal.class;
            case VALORPAGO:
                return BigDecimal.class;
            case VALORNF:
                return BigDecimal.class;
            case VALORCLASSIFICADO:
                return BigDecimal.class;
            case STATUS:
                return Boolean.class;
            default: 
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }
    
    
    
    
    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        //Apenas o campo ATIVO será editavel
        return false;
    }
    
    @Override
    public Object getValueAt(int rowIndex, int columnIndex){
        //pega o Socio referente a linha especificada;
        TbResPagamentosBeans beans = linhas.get(rowIndex);
        switch (columnIndex){
            case IDPAGTO: 
                return beans.getIDPagto();
            case CONTAORIGEM:
                return beans.getContaOrigem();
            case DTPREVISTA:
                return beans.getDtPrevista();
            case DTPAGTO:
                return beans.getDtPagamento();
            case TITULAR:
                return beans.getTitular();
            case FORMAPAGTO:
                return beans.getFormaPagto();
            case NDOC:
                return beans.getnDoc();
            case BANCODESTINO:
                return beans.getBancoDestino();    
            case AGENCIA:
                return beans.getAgencia();
            case CONTA:
                return beans.getContaCorrente();
            case MOEDA:
                return beans.getMoeda();  
            case VALOREMMOEDA:
                return beans.getValorEmMoeda();  
            case TAXA:
                return beans.getTaxa();
            case VALORPREVISTO:
                return beans.getValorPrevisto();
            case VALORPAGO:
                return beans.getValorPagto();    
            case VALORNF:
                return beans.getValorFiscal();    
            case VALORCLASSIFICADO:
                return beans.getValorClassificado();
            case STATUS:
                return beans.isStatus();
            default: 
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        
    }
  
  @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        TbResPagamentosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case IDPAGTO:
                beans.setID((Integer) aValue);
                break;
        /*    case IDPAGTO:
                beans.setIDPagto((Integer) aValue);
                break;
            case DTEMISSAO:
                beans.setDtEmissao((String) aValue);
                break;
            case TIPOCAD:
                beans.setTipoCad((String) aValue);
                break;
            case NCAD:
                beans.setnCad((String) aValue);
                break;
            case TIPODOC:
                beans.setTipoDoc((String) aValue);
                break;
            case NDOC:
                beans.setNDOC((Integer) aValue);
                break;
            case FAZENDA:
                beans.setFazenda((String) aValue);
                break;    
            case IDEMISSOR:
                beans.setIdFornecedor((Integer) aValue);
                break;
            case EMISSOR:
                beans.setFornecedor((String) aValue);
                break;
            case VALOR:
                beans.setValor((BigDecimal) aValue);
                break;   */
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }    
            fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula
                       
    }
  
// retorna o socio referente a linha especidicada      
  public TbResPagamentosBeans getBeans(int indiceLinha){
      return linhas.get(indiceLinha);
  }  
  
  public void addBeans(TbResPagamentosBeans beans){
      //Adiciona registro
      linhas.add(beans);
      // Pega a quantidade de registros e subtrai 1 para
    // achar o último índice. A subtração é necessária
    // porque os índices começam em zero.
    int ultimoIndice = getRowCount() -1;
    
//notifica a mudança
      fireTableRowsInserted(ultimoIndice, ultimoIndice);
  }
  
  public void addLista(List<TbResPagamentosBeans> beans){
      
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size()+1);

  }  
  
  public void limpar(){
      //remove todos os elementos da lista sócios;
      linhas.clear();
      //notifica a mudança;
      fireTableDataChanged();
  }
       
}
  
    
    
    
