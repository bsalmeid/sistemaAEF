package DAO;

import Beans.PedidoAlmoxarifadoItens;
import Beans.PedidosAlmoxarifado;
import Beans.PedidosAlmoxarifadoCotacao;
import Beans.PedidosAlmoxarifadoFechamento;
import Beans.PedidosAlmoxarifadoFechamentoItens;
import Beans.PedidosAlmoxarifadoSolicitacao;
import static GUI.Principal.listStatusItemPedido;
import Icones.FormatarICO;
import TableModel.TbResumoSolicitacao;
import Utilitarios.FrmProgressBar;
import Utilitarios.HibernateUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.*;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.hibernate.transform.AliasToBeanResultTransformer;

public class PedidosAlmoxarifadoDAO implements Serializable {

    String ConsultaItens = "Select \n"
            + "pi.id,\n" + "p.data as data_pedido,\n" + "pi.pedidos_almoxarifado_id,\n" + "pi.n_item,\n" + "pi.id_solicitante,\n" + "pi.solicitante,\n" + "pi.id_item,\n"
            + "pi.codigo,\n" + "pi.descricao,\n" + "pi.quantidade,\n" + "pi.unidade,\n" + "pi.id_setor,\n" + "(select cs.descricao from cad_setor_servico cs Where cs.id = pi.id_setor) as Setor,\n"
            + "pi.id_inventario,\n" + "(select ci.n_frota from cad_inventario ci Where ci.id = pi.id_inventario) as Inventario,\n"
            + "pi.id_urgencia,\n" + "pi.urgencia, \n" + "pi.id_status_item,\n" + "pi.id_solicitacao,\n"
            + "pi.id_compra, pi.status_pedido \n" + " FROM pedidos_almoxarifado_itens pi \n"
            + " JOIN pedidos_almoxarifado p ON p.id = pi.pedidos_almoxarifado_id ";

    String hql = "SELECT new PedidosAlmoxarifadoFechamentoItens "
            + " (0L,  faz.Nome, item, cot)"
            + " FROM PedidoAlmoxarifadoItens item, PedidosAlmoxarifadoCotacao cot, PedidosAlmoxarifado ped, PropriedadesBeans faz"
            + " WHERE ped.id = item.idPedido  and  item.id = cot.idItemPedido and faz.Codigo = ped.idFazenda and item.idSolicitacao.id = :idSolicitacao and cot.idFornecedor = :idFornecedor and cot.item_selecionado = true "
            + " Order By faz.Nome asc, item.id asc";


    public Boolean salvar(PedidosAlmoxarifado pedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long LastID = 0L;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            LastID = (Long) sessao.save(pedido);
            for (int i = 0; i < pedido.getListaItens().size(); i++) {
                PedidoAlmoxarifadoItens item = pedido.getListaItens().get(i);
                item.setIdPedido(pedido);
                sessao.save(item);
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            pedido.setId(LastID);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Pedido Nº " + LastID + " Inserido com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro inserir pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarSolicitacao(PedidosAlmoxarifadoSolicitacao solicitacao) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();
            Long lastID = (Long) sessao.save(solicitacao);
            solicitacao.setId(lastID);
            for (int i = 0; i < solicitacao.getListaSolicitacao().size(); i++) {
                PedidoAlmoxarifadoItens item = solicitacao.getListaSolicitacao().get(i);
                item.setIdSolicitacao(solicitacao);
                item.setIdStatusItem(listStatusItemPedido.get(1).getID());
                sessao.update(item);
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Solicitacão Nº " + solicitacao.getId() + " Inserido com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro inserir Solicitação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarCotacao(List<PedidosAlmoxarifadoCotacao> listCotacao, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Long LastID = 0L;

        try {
            frm.setString("Salvando Itens");
            frm.setIndeterminate(false);
            frm.setMaximo(listCotacao.size());
            transacao = sessao.beginTransaction();  // abrindo a transação
            for (int i = 0; i < listCotacao.size(); i++) {
                PedidosAlmoxarifadoCotacao cotacao = listCotacao.get(i);
                sessao.save(cotacao);
                frm.setString("Salvando Registro: " + (i + 1) + " de " + listCotacao.size());
                frm.setValue(i);
            }
            transacao.commit();

        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + "<html><br></html>" + " Erro inserir pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        frm.dispose();
        JOptionPane.showMessageDialog(null, "Cotações Inseridas com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        return true;
    }

    public Boolean salvarAnalise(List<PedidosAlmoxarifadoCotacao> listCotacao, PedidosAlmoxarifadoSolicitacao solicitacao, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            frm.setString("Salvando Itens");
            frm.setIndeterminate(false);
            frm.setMaximo(listCotacao.size());
            for (int i = 0; i < listCotacao.size(); i++) { // Atualizar status do item selecionado da cotacao referente;
                PedidosAlmoxarifadoCotacao cotacao = listCotacao.get(i);
                if (cotacao.isItem_selecionado()) {
                    Query qUpdateTrue = sessao.createQuery("UPDATE PedidosAlmoxarifadoCotacao c SET item_selecionado = true, c.quant_fechamento = " + cotacao.getQuant_fechamento() + " WHERE c.id = " + cotacao.getId());
                    qUpdateTrue.executeUpdate();
                } else {
                    Query qUpdateFalse = sessao.createQuery("UPDATE PedidosAlmoxarifadoCotacao c SET item_selecionado = false, c.quant_fechamento = 0 WHERE c.id =" + cotacao.getId());
                    qUpdateFalse.executeUpdate();
                }
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
                frm.setValue(i);
            }
            Query UpdateStatusItem = (Query) sessao.createQuery("UPDATE PedidoAlmoxarifadoItens i SET idStatusItem = 2 WHERE i.idStatusItem < 3 and i.idSolicitacao = " + solicitacao.getId());
            UpdateStatusItem.executeUpdate();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Análise!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            frm.dispose();
            sessao.close();
        }
        JOptionPane.showMessageDialog(null, "Análise Salva com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        return true;
    }

    public Boolean salvarFechamento(PedidosAlmoxarifadoFechamento fechamento, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            frm.setString("Salvando Itens");
            frm.setIndeterminate(false);
            frm.setMaximo(fechamento.getListItens().size());
            Long LastID = (Long) sessao.save(fechamento);
            for (int i = 0; i < fechamento.getListItens().size(); i++) {
                PedidosAlmoxarifadoFechamentoItens item = fechamento.getListItens().get(i);
                item.getIdCotacao().getId();
                if (item.isStatus_fechamento() == false) {
                    item.setFechamento(fechamento);
                    item.setStatus_fechamento(fechamento.getStatusEmissao());
                    sessao.save(item);
                    Query queryItens = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens i SET idStatusItem = 3, idCompra = " + fechamento.getId() + " WHERE i.id = " + item.getId_item().getId());
                    Query queryCot = sessao.createQuery("UPDATE PedidosAlmoxarifadoCotacao c SET c.item_comprado = true, c.quant_fechamento = " + item.getQuantidade() + " WHERE c.id = " + item.getIdCotacao().getId());
                    queryItens.executeUpdate();
                    queryCot.executeUpdate();
                    if (i % 20 == 0) { //20, same as the JDBC batch size
                        sessao.flush();
                        sessao.clear();
                    }
                }
                frm.setValue(i);
            }
            fechamento.setId(LastID);
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro inserir fechamento!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        frm.dispose();
        JOptionPane.showMessageDialog(null, "Fechamento Salvo Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        return true;
    }

    public Boolean EditarFechamento(PedidosAlmoxarifadoFechamento fechamento, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            frm.setString("Atualizando Itens");
            frm.setIndeterminate(false);
            frm.setStringPaint(true);
            frm.setMaximo(fechamento.getListItens().size());
            sessao.update(fechamento);
            for (int i = 0; i < fechamento.getListItens().size(); i++) {
                PedidosAlmoxarifadoFechamentoItens item = fechamento.getListItens().get(i);
                item.setFechamento(fechamento);
                item.setStatus_fechamento(fechamento.getStatusEmissao());
                sessao.saveOrUpdate(item);
                Query query = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens i SET idStatusItem = 3, idCompra = " + fechamento.getId() + " WHERE i.id = " + item.getId_item().getId());
                query.executeUpdate();
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
                frm.setValue(i);
            }
            transacao.commit();
            frm.setIndeterminate(true);
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro inserir fechamento!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            frm.dispose();
            sessao.close();
        }
        JOptionPane.showMessageDialog(null, "Fechamento Salvo Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        return true;
    }

    public Boolean atualizarStatusItemComprado(FrmProgressBar frm, PedidosAlmoxarifadoFechamento fechamento) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            for (int i = 0; i < fechamento.getListItens().size(); i++) {
                Query query = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens i SET idStatusItem = 3 WHERE i.idSolicitacao = " + fechamento.getListItens().get(i));
                query.executeUpdate();
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
                frm.setValue(i);
            }
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro inserir fechamento!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            frm.dispose();
            System.out.println("Teste Finally");
            sessao.close();
        }
        JOptionPane.showMessageDialog(null, "Fechamento Salvo Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        return true;
    }

    public List<PedidoAlmoxarifadoItens> verificarStatusItem(List<PedidoAlmoxarifadoItens> listVerificar) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidoAlmoxarifadoItens> listItens = new ArrayList<>();
        try {
            listItens = sessao.createQuery("Select new PedidoAlmoxarifadoItens(nItem, codigo, idStatusItem) From PedidoAlmoxarifadoItens Where id IN (" + listaIdConsulta(listVerificar) + ")").list();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return listItens;
    }

    public List<PedidoAlmoxarifadoItens> ConsultaPedidos(String Where) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidoAlmoxarifadoItens> listItens = new ArrayList<>();
        try {
            listItens = sessao.createSQLQuery(ConsultaItens + Where).addEntity("pi", PedidoAlmoxarifadoItens.class).list();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return listItens;
    }

    public List<PedidoAlmoxarifadoItens> ConsultaSolicitacoes(String Where, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidoAlmoxarifadoItens> listItens = new ArrayList<>();
        try {
            frm.setString("Realizando Consulta");
            listItens = sessao.createSQLQuery(ConsultaItens + Where).addEntity("pi", PedidoAlmoxarifadoItens.class).list();
            frm.setProgressBar(false, 0, listItens.size() * 1000, "Preenchendo Lista");
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
            frm.dispose();
        } finally {
            sessao.close();
        }
        return listItens;
    }

    public List<PedidosAlmoxarifadoFechamentoItens> ConsultaFechamento(Long id_solicitacao, Integer id_fornecedor, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidosAlmoxarifadoFechamentoItens> listItens = new ArrayList<>();
        try {
            frm.setString("Realizando Consulta");
            Query query = sessao.createQuery("SELECT 0L as ID, Item as id_item, Cot as idCotacao FROM PedidoAlmoxarifadoItens Item, PedidosAlmoxarifadoCotacao Cot, PedidosAlmoxarifado ped WHERE ped.id = Item.idPedido  and  Item.id = Cot.idItemPedido and  Item.idSolicitacao.id = :idSolicitacao and Cot.idFornecedor = :idFornecedor and Cot.item_selecionado = true ");
            query.setParameter("idSolicitacao", id_solicitacao);
            query.setParameter("idFornecedor", id_fornecedor);
            query.setResultTransformer(new AliasToBeanConstructorResultTransformer(PedidosAlmoxarifadoFechamentoItens.class.getConstructor(Long.class, PedidoAlmoxarifadoItens.class, PedidosAlmoxarifadoCotacao.class)));
            listItens = query.list();
            frm.setProgressBar(false, 0, listItens.size() * 1000, "Preenchendo Lista");
        } catch (RuntimeException e) {
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(PedidosAlmoxarifadoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            sessao.close();
        }
        frm.dispose();
        return listItens;
    }

    public List<PedidosAlmoxarifadoFechamentoItens> ConsultaFechamentoCriado(FrmProgressBar frm, String WHERE) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidosAlmoxarifadoFechamentoItens> listItens = new ArrayList<>();
        try {
            frm.setString("Realizando Consulta");
            Query query = sessao.createQuery("Select item From PedidosAlmoxarifadoFechamentoItens item,  PedidosAlmoxarifadoFechamento f Where f.id = item.fechamento" + WHERE);
            listItens = query.list();
            frm.setProgressBar(false, 0, listItens.size() * 1000, "Preenchendo Lista");
        } catch (RuntimeException e) {
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        frm.dispose();
        return listItens;
    }

    public List<PedidosAlmoxarifadoCotacao> ConsultaCotacoes(Long codigo, FrmProgressBar frm) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidosAlmoxarifadoCotacao> listCotacao = new ArrayList<>();
        try {
            frm.setString("Consultando Cotações");
            Query query = sessao.getNamedQuery("PedidosAlmoxarifadoCotacao.CotacoesItensNaoComprados");
            query.setParameter("codigo", codigo);
            listCotacao = query.list();
        } catch (RuntimeException e) {
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return listCotacao;
    }

    public List<PedidosAlmoxarifadoCotacao> BuscarCotacoes(String SQLWhere) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        List<PedidosAlmoxarifadoCotacao> listCotacao = new ArrayList<>();
        try {
            //frm.setString("Consultando Cotações");
            Query consulta = sessao.createQuery("Select cot From PedidosAlmoxarifadoCotacao as cot JOIN cot.idItemPedido as item JOIN cot.idSolicitacao as solic" + SQLWhere);
            listCotacao = consulta.list();

        } catch (RuntimeException e) {
            // frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return listCotacao;
    }

    public PedidosAlmoxarifado buscarPorCodigo(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        PedidosAlmoxarifado pedido = null;
        List<PedidoAlmoxarifadoItens> listItens = new ArrayList<>();
        try {
            Query consulta = sessao.createQuery("From PedidosAlmoxarifado p Where p.id = :codigo ");
            consulta.setLong("codigo", codigo);
            pedido = (PedidosAlmoxarifado) consulta.uniqueResult();

            SQLQuery consultaItens = sessao.createSQLQuery(ConsultaItens + " WHERE pi.pedidos_almoxarifado_id = :codigo").addEntity("pi", PedidoAlmoxarifadoItens.class);
            consultaItens.setLong("codigo", codigo);
            listItens = consultaItens.list();
            pedido.setListaItens(listItens);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return pedido;
    }

    public PedidosAlmoxarifadoFechamento buscarFechamento(Long idFechamento, FrmProgressBar frm) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        PedidosAlmoxarifadoFechamento fechamento = null;
        List<PedidosAlmoxarifadoFechamentoItens> listItens = new ArrayList<>();
        try {
            Query consulta = sessao.createQuery("From PedidosAlmoxarifadoFechamento f Where f.id = :id ");
            consulta.setLong("id", idFechamento);
            fechamento = (PedidosAlmoxarifadoFechamento) consulta.uniqueResult();
            Query consultaItens = sessao.createQuery("From PedidosAlmoxarifadoFechamentoItens item Where item.fechamento = :id Order By item.fazenda asc, item.id_pedido asc, item.n_item_pedido asc");
            consultaItens.setLong("id", idFechamento);
            listItens = consultaItens.list();
            fechamento.setListItens(listItens);
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            frm.dispose();
            sessao.close();
        }
        return fechamento;
    }

    public PedidosAlmoxarifadoSolicitacao buscarSolicitacaoPorCodigo(Long codigo, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        PedidosAlmoxarifadoSolicitacao solicitacao = null;
        List<PedidoAlmoxarifadoItens> listItens = new ArrayList<>();
        try {
            frm.setString("Realizando Consulta");
            Query consulta = sessao.createQuery("From PedidosAlmoxarifadoSolicitacao s Where s.id = :codigo ");
            SQLQuery consultaItens = sessao.createSQLQuery(ConsultaItens + " WHERE pi.id_solicitacao = :codigo ").addEntity("pi", PedidoAlmoxarifadoItens.class);
            // "and pi.id_status_item <= 2"
            consultaItens.setLong("codigo", codigo);
            consulta.setLong("codigo", codigo);
            solicitacao = (PedidosAlmoxarifadoSolicitacao) consulta.uniqueResult();
            listItens = consultaItens.list();
            solicitacao.setListaSolicitacao(listItens);
        } catch (RuntimeException e) {
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return solicitacao;
    }

    public void excluir(PedidosAlmoxarifado pedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(pedido);
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            throw e;
        } finally {
            sessao.close();
        }
    }

    public void excluir(Long codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            PedidosAlmoxarifado pedido = buscarPorCodigo(codigo);
            sessao.delete(pedido);
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            throw e;
        } finally {
            sessao.close();
        }
    }

    public Boolean excluirFechamentoItem(PedidosAlmoxarifadoFechamentoItens item, FrmProgressBar frm) {
        frm.setString("Conectando ao Banco de Dados");
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            frm.setString("Excluindo Item");
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(item);
            Query queryItens = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens i SET idStatusItem = 2, idCompra = 0 WHERE i.id = " + item.getId_item().getId());
            Query queryCot = sessao.createQuery("UPDATE PedidosAlmoxarifadoCotacao c SET c.item_comprado = false, c.quant_fechamento = 0 WHERE c.id = " + item.getIdCotacao().getId());
            queryItens.executeUpdate();
            queryCot.executeUpdate();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            frm.dispose();
            JOptionPane.showMessageDialog(null, e + " Erro inserir fechamento!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        frm.dispose();
        JOptionPane.showMessageDialog(null, "Item Excluído com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        return true;
    }

    public Boolean editar(PedidosAlmoxarifado pedido) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.update(pedido);

            for (int i = 0; i < pedido.getListaItens().size(); i++) {
                PedidoAlmoxarifadoItens item = pedido.getListaItens().get(i);
                if (item.getId() > 0) {
                    sessao.update(item);
                } else if (item.getId() == 0) {
                    sessao.save(item);
                }
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            transacao.commit();
            sessao.close();
            JOptionPane.showMessageDialog(null, "Pedido Nº " + pedido.getId() + " Editado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            sessao.close();
            return false;

        } finally {

        }
        return true;
    }

    public Boolean editarCotacao(PedidosAlmoxarifadoCotacao cotacao) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query update = sessao.createQuery("UPDATE PedidosAlmoxarifadoCotacao cot SET cot.item_original =:itemOriginal, cot.valorUnit = :valorUnit, cot.marcaPeca = :marca Where cot.id = :id");
            update.setParameter("itemOriginal", cotacao.getItem_original());
            update.setParameter("valorUnit", cotacao.getValorUnit());
            update.setParameter("marca", cotacao.getMarcaPeca());
            update.setParameter("id", cotacao.getId());
            transacao.commit();
            sessao.close();
            JOptionPane.showMessageDialog(null, "Cotação Editada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            sessao.close();
            return false;
        } finally {

        }
        return true;
    }

    public Boolean excluirCotacao(PedidosAlmoxarifadoCotacao cotacao) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(cotacao);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Cotação Editada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            sessao.close();
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editarStatusSolicitacao(Long idSolicitaca, Boolean status) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query update = sessao.createQuery("UPDATE PedidosAlmoxarifadoSolicitacao S SET S.status = :status WHERE S.id = :id");
            update.setParameter("id", idSolicitaca);
            update.setParameter("status", status);
            update.executeUpdate();
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Status Alterado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editarStatusItem(List<PedidoAlmoxarifadoItens> lista, Integer idStatus) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query update = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens I SET I.idStatusItem = :status WHERE I.id = :id");
            for (int i = 0; i < lista.size(); i++) {
                update.setParameter("id", lista.get(i).getId());
                update.setParameter("status", idStatus);
                update.executeUpdate();
                if (i % 20 == 0) {
                    sessao.flush();
                    sessao.clear();
                }
            }
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Status Alterado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean excluirItemSolicitacao(PedidoAlmoxarifadoItens Item) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query update = sessao.createQuery("UPDATE PedidoAlmoxarifadoItens I SET I.idStatusItem = 0, I.idSolicitacao = null WHERE I.id = :id");
            update.setParameter("id", Item.getId());
            Query delete = sessao.createQuery("Delete PedidosAlmoxarifadoCotacao C WHERE C.idItemPedido.id = :id AND C.idSolicitacao.id = :idSolicitacao");
            delete.setParameter("id", Item.getId());
            delete.setParameter("idSolicitacao", Item.getIdSolicitacao().getId());
            update.executeUpdate();
            delete.executeUpdate();
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Item Excluído com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public List<TbResumoSolicitacao> resumoSolicitacao(String Where) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<TbResumoSolicitacao> lista = new ArrayList<>();
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query consulta = sessao.createQuery("SELECT\n"
                    + "solic as solic,\n"
                    + "(SELECT COUNT(distinct C.idFornecedor) FROM PedidosAlmoxarifadoCotacao C WHERE C.idSolicitacao.id = solic.id) as qCotacao,\n "
                    + "COUNT(item.idSolicitacao) as qSolicitacao,\n"
                    + "SUM(CASE WHEN item.idStatusItem = 2 THEN 1 ELSE 0 END)  as qAnalise,\n"
                    + "SUM(CASE WHEN item.idStatusItem = 3 THEN 1 ELSE 0 END)  as qComprada,\n"
                    + "SUM(CASE WHEN item.idStatusItem = 4 THEN 1 ELSE 0 END)  as qRecebido,\n"
                    + "SUM(CASE WHEN item.idStatusItem = 5 THEN 1 ELSE 0 END)  as qEnviada,\n"
                    + "SUM(CASE WHEN item.idStatusItem = 6 THEN 1 ELSE 0 END)  as qCancelado\n"
                    + "FROM\n"
                    + "	PedidoAlmoxarifadoItens item,\n"
                    + "	PedidosAlmoxarifadoSolicitacao solic WHERE solic.id = item.idSolicitacao \n"
                    + Where
                    + "	GROUP BY item.idSolicitacao ");
            AliasToBeanResultTransformer alias = new AliasToBeanResultTransformer(TbResumoSolicitacao.class);
            consulta.setResultTransformer(alias);
            lista = consulta.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Pedido!", "Erro", 0, FormatarICO.ICObtnSair());
            return lista;
        } finally {
            sessao.close();
        }
        return lista;
    }

    private void rollback(Transaction transacao) {
        if (transacao != null) {
            transacao.rollback(); // desfaz a transacao
        }
    }

    private String listaIdConsulta(List<PedidoAlmoxarifadoItens> listVerificar) {
        // criar lista de ID, para realizar consulta da situacao dos items; VerificarStatusItem
        String s = "";
        for (PedidoAlmoxarifadoItens l : listVerificar) {
            s += "," + l.getId();
        }
        return s.replaceFirst(",", "");
    }

}
