package Controller;

import Beans.CompraGadoBeans;
import DAO.CompraGadoDAO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class CompraGadoController {

    CompraGadoDAO CompraGadoD;
    CompraGadoBeans CompraGadoB;

    public CompraGadoController() {
        CompraGadoD = new CompraGadoDAO();
        CompraGadoB = new CompraGadoBeans();
    }

    public Boolean controleDeletarCompra(CompraGadoBeans Compra) {
        return CompraGadoD.excluirOrdem(Compra);
    }

    public CompraGadoBeans buscarCompra(Integer ID){
        return CompraGadoD.buscarCompra(ID);
    }
    
    public void controlePreencherTabela(DefaultTableModel TbCompra, String Where) {
        CompraGadoD.pesquisarCompras(TbCompra, Where);
    }

    public Boolean inserirCompra(CompraGadoBeans compra) {
        if (verificarBeans(compra)) {
            if (CompraGadoD.inserirCompra(compra)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    public Boolean editarCompra(CompraGadoBeans compra) {
        if (verificarBeans(compra)) {
            if (CompraGadoD.editarCompra(compra)) {
                return true;
            } else {
                return false;
            }
        }
        return false;
    }

    private boolean verificarBeans(CompraGadoBeans compra) {

        if (compra.getComprador().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Comprador!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getData() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo data!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getDescricao().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o Campo Descrição!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getDestino().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Destino!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        if (compra.getNegociacao().equals("-")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Forma Negociação!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        } else if (compra.getNegociacao().equals("Peso")) {
            if (compra.getPesoOrigem() == 0.00 || compra.getReaisArr() == 0.00) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Peso de Origem e R$/@!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                return false;
            }
        } else if (compra.getNegociacao().equals("Cabeça")) {
            if (compra.getValorUnit() == 0.00) {
                JOptionPane.showMessageDialog(null, "Verifique o campo Preço Unit!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
                return false;
            }
        }
        if (compra.getFornecedor().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Fornecedor!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        if (compra.getQuant() == 0) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Quantidade!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getStatusB() == null) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Status!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getValorT() == 0.00) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Valor Total!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getCpf().equals("   .   .   -  ") || compra.getCpf().equals("  .   .   ./    -  ")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo CPF!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getBanco().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Banco!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getAgencia().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Agência!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        if (compra.getConta().equals("")) {
            JOptionPane.showMessageDialog(null, "Verifique o campo Conta!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        return true;
    }

}
