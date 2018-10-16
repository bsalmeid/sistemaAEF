/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeModel;

import Beans.FornecedoresBeans;
import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifadoCotacao;
import Beans.PedidosAlmoxarifadoFechamentoItens;
import GUI.FrmPedidosAnalise;
import Icones.FormatarICO;
import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.tree.TreePath;
import org.jdesktop.swingx.JXTreeTable;

/**
 *
 * @author JUNIOR
 */
public class AnaliseMercadoriaListener extends MouseAdapter implements KeyListener {

    JXTreeTable tree;
    JXTreeTable tb_fechamento;
    TreeTableFechamento TbFechamento;

    public AnaliseMercadoriaListener(JXTreeTable tree, JXTreeTable tb_fechamento) {
        this.tree = tree;
        this.tb_fechamento = tb_fechamento;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (tree.getSelectedColumn() == 11) {
            selecionarItem();
        }

    }

    @Override
    public void keyPressed(KeyEvent ke) {
        int row = tree.getSelectedRow();
        TreePath path = tree.getPathForRow(row);
        if (path != null) {
            if (path.getLastPathComponent() instanceof PedidoAlmoxarifadoItens) {
                if (ke.getKeyCode() == KeyEvent.VK_RIGHT && ke.getModifiers() == KeyEvent.CTRL_MASK) {
                    tree.expandPath(path);
                } else if (ke.getKeyCode() == KeyEvent.VK_LEFT && ke.getModifiers() == KeyEvent.CTRL_MASK) {
                    tree.collapsePath(path);
                }
            } else if (path.getLastPathComponent() instanceof PedidosAlmoxarifadoCotacao) {
                if (ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    selecionarItem();
                } else if (ke.getKeyCode() == KeyEvent.VK_LEFT && ke.getModifiers() == KeyEvent.CTRL_MASK) {
                    tree.collapsePath(path.getParentPath());
                }
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent ke) {

    }

    @Override
    public void keyReleased(KeyEvent ke) {

    }

    public void selecionarItem() {
        int row = tree.getSelectedRow();
        TreePath path = tree.getPathForRow(row);
        if (path != null) {
            if (path.getLastPathComponent() instanceof PedidoAlmoxarifadoItens) {
                PedidoAlmoxarifadoItens node = (PedidoAlmoxarifadoItens) path.getLastPathComponent();
                boolean isSelected = !(node.isSelected());
                node.setSelected(isSelected);
                if (isSelected) {
                    tree.expandPath(path);
                } else {
                    tree.collapsePath(path);
                }
            } else if (path.getLastPathComponent() instanceof PedidosAlmoxarifadoCotacao) {
                PedidosAlmoxarifadoCotacao cotacao = (PedidosAlmoxarifadoCotacao) path.getLastPathComponent();
                
                if (cotacao.isItem_comprado()) {
                    JOptionPane.showMessageDialog(null, "Este Item já encontra finalizado, você não pode editá-lo!", "Erro", 0, FormatarICO.ICObtnSair());
                } else {
                    boolean isSelected = !(cotacao.isItem_selecionado());
                    cotacao.setItem_selecionado(isSelected);

                    // verificar se alguma cotacao está marcada e selecionar o item como true
                    if (path.getParentPath().getLastPathComponent() instanceof PedidoAlmoxarifadoItens) {
                        PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) path.getParentPath().getLastPathComponent();
                        item.setSelected(false);
                        for (PedidosAlmoxarifadoCotacao c : item.getListaCotacoes()) {
                            if (c.isItem_selecionado() == true) {
                                item.setSelected(true);
                                break;
                            }
                        }

                    }
                    TbFechamento = (TreeTableFechamento) tb_fechamento.getTreeTableModel();
                    List<FornecedoresBeans> listFornecedores = (List<FornecedoresBeans>) TbFechamento.getListaFornecedor();
                    if (isSelected) {
                        adicionarItemFechamento(row, listFornecedores, cotacao);
                    } else {
                        retirarItemFechamento(row, listFornecedores, cotacao);
                    }

                    // atulizar JTreeTable Fechamento
                    Container container = tb_fechamento.getParent().getParent().getParent().getParent().getParent().getParent();
                    if (container instanceof FrmPedidosAnalise) {
                        FrmPedidosAnalise analise = (FrmPedidosAnalise) container;
                        tb_fechamento.setTreeTableModel(new TreeTableFechamento(TbFechamento.getListaFornecedor(), tb_fechamento));
                        tb_fechamento.revalidate();
                        tb_fechamento.repaint();
                        tb_fechamento.expandAll();
                        analise.ajustarColuna();
                    }
                }
            }
        }
        tree.setTreeTableModel((TreeTableAnaliseMercadoria) tree.getTreeTableModel());
        tree.revalidate();
        tree.repaint();

    }

    public void adicionarItemFechamento(int row, List<FornecedoresBeans> listFornecedores, PedidosAlmoxarifadoCotacao cotacao) {
        TreePath pathItem = tree.getPathForRow(row);
        if (pathItem != null) {
            if (pathItem.getParentPath().getLastPathComponent() instanceof PedidoAlmoxarifadoItens) {
                PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) pathItem.getParentPath().getLastPathComponent();
                for (int i = 0; i < listFornecedores.size(); i++) {
                    FornecedoresBeans fornecedor = listFornecedores.get(i);
                    if (fornecedor.getID() == cotacao.getIdFornecedor()) {
                        PedidosAlmoxarifadoFechamentoItens fechamento = new PedidosAlmoxarifadoFechamentoItens();
                      //  System.out.println(node.getId());
                        fechamento.setIdCotacao(cotacao);
                        fechamento.setCodigo(item.getCodigo());
                        fechamento.setDescricao(item.getDescricao());
                        fechamento.setMarca(cotacao.getMarcaPeca());
                        fechamento.setFornecedor(cotacao.getFornecedor());
                        fechamento.setID_Fornecedor(cotacao.getIdFornecedor());
                        fechamento.setIdCadastro(item.getIdItem());
                        fechamento.setId_item(cotacao.getIdItemPedido());
                        fechamento.setId_pedido(item.getIdPedido().getId());
                        fechamento.setN_item_pedido(item.getnItem());
                        fechamento.setQuantidade(item.getQuantidade());
                        fechamento.setStatus_fechamento(false);
                        fechamento.setValor_unit(cotacao.getValorUnit());
                        fechamento.setValor_total(item.getQuantidade() * cotacao.getValorUnit());
                        fornecedor.setValorTotalFechamento(fornecedor.getValorTotalFechamento() + fechamento.getValor_total());
                        
                        fornecedor.getListaFechamento().add(fechamento);
                        listFornecedores.set(i, fornecedor);
                        TbFechamento.setListaFornecedor(listFornecedores);
                        break;
                    }
                }
            }
        }
    }

    public void retirarItemFechamento(int row, List<FornecedoresBeans> listFornecedores, PedidosAlmoxarifadoCotacao node) {
        TreePath pathItem = tree.getPathForRow(row);
        if (pathItem != null) {
            if (pathItem.getParentPath().getLastPathComponent() instanceof PedidoAlmoxarifadoItens) {
                PedidoAlmoxarifadoItens item = (PedidoAlmoxarifadoItens) pathItem.getParentPath().getLastPathComponent();
                for (int i = 0; i < listFornecedores.size(); i++) {
                    FornecedoresBeans fornecedor = listFornecedores.get(i);
                    if (fornecedor.getID() == node.getIdFornecedor()) {
                        for (int y = 0; y < fornecedor.getListaFechamento().size(); y++) {
                            if (Objects.equals(fornecedor.getListaFechamento().get(y).getId_item().getId(), item.getId())) {
                                fornecedor.setValorTotalFechamento(fornecedor.getValorTotalFechamento() - fornecedor.getListaFechamento().get(y).getValor_total());
                                fornecedor.getListaFechamento().remove(y);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

}
