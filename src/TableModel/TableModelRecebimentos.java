/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Beans.RecebimentosBeans;
import Utilitarios.Corretores;
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
public class TableModelRecebimentos extends AbstractTableModel {

    private List<RecebimentosBeans> linhas;

    public TableModelRecebimentos() {
        linhas = new ArrayList<RecebimentosBeans>();
    }

    public TableModelRecebimentos(List<RecebimentosBeans> listaPagtos) {
        linhas = new ArrayList<RecebimentosBeans>(listaPagtos);
    }

    private String[] colunas = new String[]{"Status", "ID", "Data", "idConta", "Conta Bancária", "Atividade", "C. Resultado",
        "Nº Escala", "Nº Cto Grãos", "idMoeda", "Moeda", "Sifra", "Descricão", "idFazenda", "Fazenda",
        "Conta", "Descrição Conta",
        "CPF/CNPJ", "idCliente", "Cliente", "Valor em Moeda", "Taxa", "Valor em Reais"
    };

    public final int STATUS = 0;
    public final int ID = 1;
    public final int DTRECEBIMENTO = 2;
    public final int IDCONTA = 3;
    public final int CONTADESTINO = 4;
    public final int ATIVIDADE = 5;
    public final int C_RESULTADO = 6;
    public final int NESCALA = 7;
    public final int NCTOGRAO = 8;
    public final int IDMOEDA = 9;
    public final int MOEDA = 10;
    public final int SIFRA = 11;
    public final int DESCRICAO = 12;
    public final int IDFAZENDA = 13;
    public final int FAZENDA = 14;
    public final int PLANOCONTAS = 15;
    public final int DESCPLANOCONTAS = 16;
    public final int CNPJ = 17;
    public final int IDCLIENTE = 18;
    public final int CLIENTE = 19;
    public final int VALORMOEDA = 20;
    public final int TAXA = 21;
    public final int VALORTOTAL = 22;

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

    public void addRow(RecebimentosBeans Beans) {
        int indice = getRowCount() - 1;
        linhas.add(Beans);
        fireTableRowsInserted(indice, indice);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Integer.class;
            case DTRECEBIMENTO:
                return String.class;
            case IDCONTA:
                return Integer.class;
            case CONTADESTINO:
                return String.class;
            case NESCALA:
                return Integer.class;
            case NCTOGRAO:
                return Integer.class;
            case IDMOEDA:
                return Integer.class;
            case MOEDA:
                return String.class;
            case SIFRA:
                return String.class;
            case VALORMOEDA:
                return BigDecimal.class;
            case TAXA:
                return Double.class;
            case VALORTOTAL:
                return BigDecimal.class;
            case DESCRICAO:
                return String.class;
            case CNPJ:
                return String.class;
            case IDCLIENTE:
                return Integer.class;
            case CLIENTE:
                return String.class;
            case IDFAZENDA:
                return Integer.class;
            case FAZENDA:
                return String.class;
            case ATIVIDADE:
                return String.class;
            case C_RESULTADO:
                return String.class;
            case PLANOCONTAS:
                return Integer.class;
            case DESCPLANOCONTAS:
                return String.class;
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
    public Object getValueAt(int rowIndex, int columnIndex) {
        RecebimentosBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getID();
            case DTRECEBIMENTO:
                return Corretores.ConverterDateStringDDMMAAA(beans.getDtRecebimentos());
            case IDCONTA:
                return beans.getIdConta().getIdConta();
            case CONTADESTINO:
                return beans.getIdConta().getDescricao();
            case NESCALA:
                return beans.getnEscala();
            case NCTOGRAO:
                return beans.getnCtoGrao();
            case IDMOEDA:
                return beans.getIdMoeda().getIdMoeda();
            case MOEDA:
                return beans.getIdMoeda().getMoeda();
            case SIFRA:
                return beans.getIdMoeda().getSifra();
            case VALORMOEDA:
                return beans.getValorEmMoeda();
            case TAXA:
                return beans.getTaxa();
            case VALORTOTAL:
                return beans.getValorTotal();
            case DESCRICAO:
                return beans.getDescricao();
            case CNPJ:
                return beans.getCNPJ();
            case IDCLIENTE:
                return beans.getIdCliente();
            case CLIENTE:
                return beans.getNomeCliente();
            case IDFAZENDA:
                return beans.getIdFazenda().getCodigo();
            case FAZENDA:
                return beans.getIdFazenda().getNome();
            case ATIVIDADE:
                return beans.getAtividade().getDescricao();
            case C_RESULTADO:
                try {
                    return beans.getCentroResultado().getDescricao();
                } catch (Exception e) {
                    return "-";
                }
            case PLANOCONTAS:
                return beans.getPlanoConta().getConta();
            case DESCPLANOCONTAS:
                return beans.getPlanoConta().getDescricao();
            case STATUS:
                return beans.isStatus();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        //Pega o sócio referente  a linha especificada
        RecebimentosBeans beans = linhas.get(rowIndex);

        switch (columnIndex) {
            case ID:
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

    public RecebimentosBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void addBeans(RecebimentosBeans beans) {
        //Adiciona registro
        linhas.add(beans);
        // Pega a quantidade de registros e subtrai 1 para
        // achar o último índice. A subtração é necessária
        // porque os índices começam em zero.
        int ultimoIndice = getRowCount() - 1;

//notifica a mudança
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<RecebimentosBeans> beans) {

        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size() + 1);

    }

    public void limpar() {
        //remove todos os elementos da lista sócios;
        linhas.clear();
        //notifica a mudança;
        fireTableDataChanged();
    }

}
