/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeModel;

import Beans.AnoSafra;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListFazendasBeans;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTree;
import javax.swing.tree.TreePath;

/**
 *
 * @author JUNIOR
 */
public class NodeSelectionListener extends MouseAdapter {

    JTree tree;

    public NodeSelectionListener(JTree tree) {
        this.tree = tree;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();
        int row = tree.getRowForLocation(x, y);
        TreePath path = tree.getPathForRow(row);
        if (path != null) {
            if (path.getLastPathComponent() instanceof AnoSafra) {
                //Neste caso, indica que o usuário clicou sobre a classe pai Fabrica,
                //portanto todos os seus filhos serão marcado com o estado no qual o pai
                //está sendo, ou seja, receberá o mesmo status que o pai
                AnoSafra node = (AnoSafra) path.getLastPathComponent();
                boolean isSelected = !(node.isIsSelected());
                node.setSelected(isSelected);
                for (CultivoBeans cultivos : node.getListCultivos()) {
                    cultivos.setSelected(isSelected);
                    for (CulturaBeans cultura : cultivos.getListCultura()) {
                        cultura.setSelected(isSelected);
                    }
                }
                
                if (isSelected) {
                    tree.expandPath(path);
                } else {
                    tree.collapsePath(path);
                }
            } else if (path.getLastPathComponent() instanceof AnoSafra) {
                //Neste caso, indica que o usuário clicou sobre a classe pai Carro,
                //portanto todos os seus filhos serão marcado com o estado no qual o pai
                //está sendo, ou seja, receberá o mesmo status que o pai
                AnoSafra node = (AnoSafra) path.getLastPathComponent();
                boolean isSelected = !(node.isIsSelected());
                boolean anoSafraSelect = false;
                ListFazendasBeans fazenda = ((ListFazendasBeans) path.getParentPath().getLastPathComponent());
                node.setSelected(isSelected);
                for (CultivoBeans cultivos : node.getListCultivos()) {
                    cultivos.setSelected(isSelected);
                }
                //Será necessário verificar se há outros filhos do respectivo
                //pai que estejam marcados, caso haja o pai não poderá ser desmarcado
              /*  for (ListAnoSafra anoSafra : safra) {
                    if (anoSafra.isIsSelected()) {
                        anoSafraSelect = true;
                        break;
                    }
                } */
                if (anoSafraSelect) {
                    fazenda.setSelected(true);
                } else {
                    fazenda.setSelected(false);
                }
                if (isSelected) {
                    tree.expandPath(path);
                } else {
                    tree.collapsePath(path);
                }
            } else if (path.getLastPathComponent() instanceof CultivoBeans) {
                CultivoBeans node = (CultivoBeans) path.getLastPathComponent();
                boolean isSelected = !(node.isIsSelected());
                boolean anoSafraSelect = false;
                boolean cultivoSelect = false;
                node.setSelected(isSelected);
                AnoSafra anoSafra = ((AnoSafra) path.getParentPath().getLastPathComponent());
                ListFazendasBeans fazenda = ((ListFazendasBeans) path.getParentPath().getParentPath().getLastPathComponent());
                //Será necessário verificar se há outros filhos (Pessoas) do respectivo
                //pai que estejam marcados, caso haja o pai não poderá ser desmarcado
                for (CultivoBeans cultivo : anoSafra.getListCultivos()) {
                    if (cultivo.isIsSelected()) {
                        cultivoSelect = true;
                        break;
                    }
                }
                if (cultivoSelect) {
                    anoSafra.setSelected(true);
                } else {
                    anoSafra.setSelected(false);
                }
                //Será necessário verificar se há outros filhos (Pessoas) do respectivo
                //pai que estejam marcados, caso haja o pai não poderá ser desmarcado
            /*    for (ListAnoSafra anoSafraPai : fazenda.getListAnoSafra()) {
                    if (anoSafraPai.isIsSelected()) {
                        anoSafraSelect = true;
                        break;
                    }
                }*/
                if (anoSafraSelect) {
                    fazenda.setSelected(true);
                } else {
                    fazenda.setSelected(false);
                }
            }
            tree.setModel((FazendasTreeModel) tree.getModel());
            tree.revalidate();
            tree.repaint();
        }
    }
    
    
}
