package TableModel;

import Beans.AtividadeBeans;
import Beans.CadColaboradorBeans;
import Beans.CadColaboradorClassBeans;
import Beans.CentroDeResultado;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import DAO.DiversasHibernate;
import static GUI.Principal.ListaPropriedades;
import static GUI.Principal.listPlanoContas;
import static GUI.Principal.listaCentroResultado;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Bruno
 */
public class TableModelColaboradorClass extends AbstractTableModel {

    private final List<CadColaboradorClassBeans> linhas;

    public TableModelColaboradorClass() {
        linhas = new ArrayList<>();
    }

    private final String[] colunas = new String[]{"ID", "ID Colaborador", "C. Resultado",
        "Conta", "Fazenda", "Valor"};

    public final int ID = 0;
    public final int IDCOLABORADOR = 1;
    public final int CENTRO_RESULTADO = 2;
    public final int PLANOCONTA = 3;
    public final int FAZENDA = 4;
    public final int VALOR = 5;

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
        this.fireTableRowsDeleted(rowIndex, rowIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case ID:
                return Long.class;
            case IDCOLABORADOR:
                return Integer.class;
//            case ATIVIDADE:
//                return AtividadeBeans.class;
            case CENTRO_RESULTADO:
                return String.class;
            case PLANOCONTA:
                return String.class;
            case FAZENDA:
                return String.class;
            case VALOR:
                return Double.class;
            default:
                throw new IndexOutOfBoundsException("column out of bounds");
        }
    }

    @Override
    public boolean isCellEditable(int row, int columnIndex) {
        return false;
   //     return columnIndex == VALOR;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        CadColaboradorClassBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                return beans.getId();
            case IDCOLABORADOR:
                return  (beans.getIdColaborador() == null ? 0 : beans.getIdColaborador().getId());
            case CENTRO_RESULTADO:
                return  beans.getIdCentro();
            case PLANOCONTA:
                return  beans.getIdPlanoConta();
            case FAZENDA:
                return  beans.getIdFazenda();
            case VALOR:
                return beans.getValor();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }

    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        CadColaboradorClassBeans beans = linhas.get(rowIndex);
        switch (columnIndex) {
            case ID:
                beans.setId((Long) aValue);
                break;
            case IDCOLABORADOR:
                beans.setIdColaborador((CadColaboradorBeans) aValue);
                break;
//            case ATIVIDADE:
//                beans.setIdAtividade((AtividadeBeans) aValue);
//               break;
            case CENTRO_RESULTADO:
                beans.setIdCentro((CentroDeResultado) aValue);
                break;
            case PLANOCONTA:
                beans.setIdPlanoConta((PlanoContaBeans) aValue);
                break;
            case FAZENDA:
                beans.setIdFazenda((PropriedadesBeans) aValue);
                break;
            case VALOR:
                beans.setValor((Double) aValue);
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
        fireTableCellUpdated(rowIndex, columnIndex);//notifica a atualização da celula

    }

    public CadColaboradorClassBeans getBeans(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    public void setBeans(CadColaboradorClassBeans beans, int rowIndex) {
        linhas.set(rowIndex, beans);
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    public List<CadColaboradorClassBeans> getLista() {
        return linhas;
    }

    public void addBeans(CadColaboradorClassBeans beans) {
        linhas.add(beans);
        int ultimoIndice = getRowCount() - 1;
        fireTableRowsInserted(ultimoIndice, ultimoIndice);
    }

    public void addLista(List<CadColaboradorClassBeans> beans) {
        int indice = getRowCount();
        linhas.addAll(beans);
        fireTableRowsInserted(indice, indice + beans.size());
    }

    public void deletarUltimaLinha() {
        int ultimoIndice = getRowCount() - 1;
        linhas.remove(ultimoIndice);
        fireTableRowsDeleted(ultimoIndice, ultimoIndice);
    }

    public void limpar() {
        linhas.clear();
        fireTableDataChanged();
    }

    public Double somarValorTabela(int columnIndex) {
        Double s = 0.0;
        for (int i = 0; i < linhas.size(); i++) {
            s += (Double) getValueAt(i, columnIndex);
        }
        return s;
    }
    
    
    private PropriedadesBeans getFazendas(Integer ID) {
        if (ListaPropriedades == null) {
            ListaPropriedades = DiversasHibernate.getListaFazendas();
        }
        for (PropriedadesBeans f : ListaPropriedades) {
            if (f.getCodigo() == ID) {
                return f;
            }
        }
        return new PropriedadesBeans(0, "-");
    }

    private CentroDeResultado getCentroResultado(Long ID) {
        if (listaCentroResultado == null) {
            listaCentroResultado = DiversasHibernate.getListaCentroResultado();
        }
        for (CentroDeResultado c : listaCentroResultado) {
            if (Objects.equals(c.getID(), ID)) {
                return c;
            }
        }
        return new CentroDeResultado(0L, "-");
    }

    private PlanoContaBeans getPlanoContas(Integer ID) {
        if (listPlanoContas == null) {
            listPlanoContas = DiversasHibernate.getPlanoConta();
        }
        for (PlanoContaBeans p : listPlanoContas) {
            if (Objects.equals(p.getID(), ID)) {
                return p;
            }
        }
        return new PlanoContaBeans(0, "-");
    }

}
