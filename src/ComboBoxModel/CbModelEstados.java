
package ComboBoxModel;

import Beans.EstadosBeans;
import java.util.ArrayList;
import java.util.List; 
import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;
  
public class CbModelEstados extends AbstractListModel<Object> implements ComboBoxModel<Object> {
 
    private List<EstadosBeans> listEstados;
    private EstadosBeans selectedEstado;
    private final static int FIRSTINDEX = 0;
 
    public CbModelEstados() {
        this.listEstados = new ArrayList<>();
    }
     
    public CbModelEstados(List<EstadosBeans> listEstados) {
        this();
        this.listEstados.addAll(listEstados);
        if (getSize() > 0) { 
            setSelectedItem(this.listEstados.get(FIRSTINDEX));
        }
    }
     
    @Override
    public EstadosBeans getElementAt(int index) {
        return listEstados.get(index);
    }
 
    @Override
    public final int getSize() {
        return listEstados.size();
    }
 
    @Override
    public EstadosBeans getSelectedItem() {
        return selectedEstado;
    }
 
    @Override
    public final void setSelectedItem(Object anItem) {
        selectedEstado = (EstadosBeans) anItem;
    }
     
    public void addEstado(EstadosBeans estado) {
        listEstados.add(estado);
        fireIntervalAdded(this, getSize() - 1, getSize() - 1);
        setSelectedItem(listEstados.get(getSize() - 1));
    }
     
    public void addListEstado(List<EstadosBeans> estados) {
        int primeiraLinha = getSize();
        listEstados.addAll(estados);
        fireIntervalAdded(this, primeiraLinha, primeiraLinha  + estados.size());
        setSelectedItem(listEstados.get(getSize() - 1));
    }
     
    public void removeEstado() {
        listEstados.remove(getSelectedItem());
        fireIntervalRemoved(this, FIRSTINDEX, getSize() - 1);
        setSelectedItem(listEstados.get(FIRSTINDEX));
    }
     
    public void clear() {
        listEstados.clear();
        fireContentsChanged(this, FIRSTINDEX, getSize() - 1);
    }

    
    


}
