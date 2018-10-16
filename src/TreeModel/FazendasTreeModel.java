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
import Beans.TalhaoBeans;
import java.util.ArrayList;
import java.util.List;
import javax.swing.event.TreeModelListener;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

public class FazendasTreeModel implements TreeModel {

    //Criamos um objeto para nossa raiz. O List não pode ser usado diretamente pois  
    //seu hash irá mudar sempre que um novo livro for adicionado.  
    //Fora que é mais bonitinho escrever "Livros" no topo.  
    private String raiz = "ListAnoSafra";  // Escrevam depois AbstractTreeModel para lidar com os listeners.  
    private List<TreeModelListener> listeners = new ArrayList<TreeModelListener>();// Raiz da nossa árvore, vamos exibir uma lista de livros.  
    private List<AnoSafra> listAnoSafra;

    public FazendasTreeModel(List<AnoSafra> listAnoSafra) {
        this.listAnoSafra = listAnoSafra;
    }

    /**
     * Com esse método, o Java quem é o objeto que está num determinado índice
     * do pai. Cada nó de uma árvore pode ser encarado como uma lista. Sendo o
     * pai a lista e o índice um dos filhos.
     *
     * @param parent É o pai, que tem os filhos. No caso do Livro, o próprio
     * livro.
     * @param index Índice do filho. No caso do livro, o índice corresponde aos
     * autores.
     */
    public Object getChild(Object parent, int index) {
        if (parent == raiz) {      // É o nó principal?  
            return listAnoSafra.get(index); // Pegamos da lista de livro  
        }
        
        if (parent instanceof AnoSafra) { // O pai é um livro?   
            return ((AnoSafra) parent).getListCultivos().get(index);
        } else if (parent instanceof CultivoBeans) {
            return ((CultivoBeans) parent).getListCultura().get(index);
        } else if (parent instanceof CulturaBeans){
            return ((CulturaBeans) parent).getListFazendas().get(index);
        } else if (parent instanceof ListFazendasBeans){
            return ((ListFazendasBeans) parent).getListTalhoes().get(index);
        }    
        // Se o pai não é nenhum desses. Melhor dar erro.  
        throw new IllegalArgumentException("Invalid parent class" + parent.getClass().getSimpleName());
    }

    /**
     * Retornamos quantos filhos um pai tem. No caso de um livro, é a contagem
     * de autores. No caso da lista de livros, é a quantidade de livros.
     */
    public int getChildCount(Object parent) {
        // Mesma lógica.  
        if (parent == raiz) {
            return listAnoSafra.size();
        }
        
        if (parent instanceof AnoSafra) {// O pai é um livro?  
            return ((AnoSafra) parent).getListCultivos().size();
            
        } else if (parent instanceof CultivoBeans) {
            return ((CultivoBeans) parent).getListCultura().size();
       
         } else if (parent instanceof CulturaBeans) {
            return ((CulturaBeans) parent).getListFazendas().size();    
        
         } else if (parent instanceof ListFazendasBeans) {
            return ((ListFazendasBeans) parent).getListTalhoes().size();    
                
        }
        // Se o pai não é nenhum desses. Melhor dar erro.  
        throw new IllegalArgumentException("Invalid parent class"
                + parent.getClass().getSimpleName());
    }

    /**
     * Dado um pai, indicamos qual é o índice do filho correspondente.
     */
    public int getIndexOfChild(Object parent, Object child) {
        if (parent == raiz) {
            return listAnoSafra.indexOf(child);
        }
        if (parent instanceof AnoSafra) {
            return ((AnoSafra) parent).getListCultivos().indexOf(child);
        
        } else if (parent instanceof CultivoBeans) {
            return ((CultivoBeans) parent).getListCultura().indexOf(child);
        
        } else if (parent instanceof CulturaBeans) {
            return ((CulturaBeans) parent).getListFazendas().indexOf(child);
        
        } else if (parent instanceof ListFazendasBeans) {
            return ((ListFazendasBeans) parent).getListTalhoes().indexOf(child);
            
        }
        return 0;
    }

    /**
     * Devemos retornar quem é o nó raiz da árvore. Afinal, a árvore tem que
     * começar em algum lugar.
     */
    @Override
    public Object getRoot() {
        return raiz;
    }

    /**
     * Indicamos se um nó é ou não uma folha. Isso é, se ele não tem filhos. No
     * nosso caso, os autores são as folhas da árvore.
     */
    @Override
    public boolean isLeaf(Object node) {
        return node instanceof TalhaoBeans;
    }

    @Override
    public void valueForPathChanged(TreePath path, Object newValue) {
        // Com esse método, a tree avisa que um objeto mudou.  
        // Editem se quiserem que um nó seja editável  
    }

    // Esses dois métodos abaixo poderiam ir para classe abstrata  
    @Override
    public void removeTreeModelListener(TreeModelListener l) {
        listeners.remove(l);
    }

    @Override
    public void addTreeModelListener(TreeModelListener l) {
        listeners.add(l);
    }

}
