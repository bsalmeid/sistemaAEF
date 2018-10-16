package DAO;

import Almoxarifado.CategoriaAlmoxarif;
import Beans.AnoSafra;
import Beans.AtividadeBeans;
import Beans.BancosBeans;
import Beans.CEIBeans;
import Beans.CadPlacaBeans;
import Beans.CadUnidadesBeans;
import Beans.CargosBeans;
import Beans.CategoriaAnimalBeans;
import Beans.CategoriaEquipamentosBeans;
import Beans.CentroDeResultado;
import Beans.CidadesBeans;
import Beans.CompradorGadoBeans;
import Beans.ContaBancariaBeans;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.EstadosBeans;
import Beans.FornecedoresBeans;
import Beans.GrupoContasBeans;
import Beans.InventarioBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.MarcaEquipamentosBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MoedaBeans;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import Beans.TipoDocPagto;
import Beans.TransportadorasBeans;
import static GUI.Principal.listaFazPermitida;
import GUI.frmLogin;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DiversasHibernate {

    public DiversasHibernate() {

    }

    public static FornecedoresBeans getFornecedor(Integer ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        FornecedoresBeans f = new FornecedoresBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT F FROM FornecedoresBeans F WHERE F.ID =:id");
            consulta.setParameter("id", ID);
            f = (FornecedoresBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fornecedores!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return f;
    }

    public static List<PropriedadesBeans> getListaFazendas() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<PropriedadesBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From PropriedadesBeans f Where f.Status = :status ");
            query.setParameter("status", "Ativo");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CEIBeans> getListaCEI() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CEIBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("SELECT C FROM CEIBeans C Where C.Status = 1 ");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CargosBeans> getListaCargos() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CargosBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("SELECT C FROM CargosBeans C WHERE C.Status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<ListSetorTrabalhoBeans> getListaSetoresTrabalho() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<ListSetorTrabalhoBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("SELECT S FROM ListSetorTrabalhoBeans S WHERE S.Status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<PropriedadesBeans> getListaFazendasPermitidas() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<PropriedadesBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("SELECT F "
                    + " FROM PropriedadesBeans F, CadUsuarioFazendas C "
                    + " WHERE F.Codigo = C.idFazenda and F.Status = 'Ativo' and C.acesso = true and C.idUsuario.id = :idUser ");
            query.setParameter("idUser", frmLogin.UsuarioLogadoBeans.getId());
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Fazendas Permitidas!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CadUnidadesBeans> getUnidades() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CadUnidadesBeans> lista = new ArrayList<>();
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CadUnidadesBeans u Where u.Status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<EstadosBeans> getListaEstados() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<EstadosBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From EstadosBeans e");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CidadesBeans> getListaCidades() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CidadesBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CidadesBeans e");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Listar Cidades!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<TipoDocPagto> getListaDocumentosPagto() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<TipoDocPagto> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From TipoDocPagto e Where e.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<PlanoContaBeans> getPlanoConta() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<PlanoContaBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From PlanoContaBeans p Where p.status = 1 and p.Tipo = 'A'");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<BancosBeans> getListaBancos() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<BancosBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From BancosBeans b Where b.Status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Bancos!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<ContaBancariaBeans> getListaContasBancarias() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<ContaBancariaBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From ContaBancariaBeans c Where c.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Contas Bancárias!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<AtividadeBeans> getListaAtividade() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<AtividadeBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From AtividadeBeans c Where c.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<MoedaBeans> getListaMoeda() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<MoedaBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From MoedaBeans m Where m.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<GrupoContasBeans> getListaGrupoConta() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<GrupoContasBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From GrupoContasBeans m Where m.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CentroDeResultado> getListaCentroResultado() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CentroDeResultado> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CentroDeResultado c Where c.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<TransportadorasBeans> getListaTransportadoras() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<TransportadorasBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("SELECT T FROM TransportadorasBeans T Where T.status = 1 and T.Segmento= 'Gado'");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CentroDeResultado> getListaCentroResultadoTodos() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CentroDeResultado> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CentroDeResultado c");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CulturaBeans> getListaCulturas() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CulturaBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CulturaBeans c Where c.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CultivoBeans> getListaCultivos() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CultivoBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CultivoBeans c Where c.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<AnoSafra> getListaAnoSafra() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<AnoSafra> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From AnoSafra a Where a.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<InventarioBeans> getListaInventario() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<InventarioBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From InventarioBeans i WHERE i.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CategoriaAnimalBeans> getListaCategoriaAnimal() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CategoriaAnimalBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CategoriaAnimalBeans i WHERE i.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Buscar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CategoriaAlmoxarif> getListaCategoriaAlmoxarifado() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CategoriaAlmoxarif> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("SELECT c From CategoriaAlmoxarif c Where c.status = :status ");
            query.setParameter("status", true);
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Consulta Lista de Categorias!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CompradorGadoBeans> getListaCompradorgado() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        List<CompradorGadoBeans> lista = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CompradorGadoBeans c WHERE c.status = 1");
            lista = query.list();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro ao Buscar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static FornecedoresBeans buscarFornecedor(String cnpj) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        FornecedoresBeans fornecedor = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From FornecedoresBeans f Where f.cnpj = :cnpj ");
            query.setParameter("cnpj", cnpj);
            fornecedor = (FornecedoresBeans) query.uniqueResult();
            transacao.commit();
        } catch (RuntimeException e) {
            rollback(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Pesquisar Fornecedor!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return fornecedor;
    }

    private static void rollback(Transaction transacao) {
        if (transacao != null) {
            transacao.rollback(); // desfaz a transacao
        }
    }

    public static List<ModeloEquipamentosBeans> getListaModeloInventario() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<ModeloEquipamentosBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT M FROM ModeloEquipamentosBeans M JOIN FETCH M.id_categoria JOIN FETCH M.id_marca WHERE M.Status = 1 ORDER BY M.ID");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<CategoriaEquipamentosBeans> getListaCategoria() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Object transaction = null;
        List<CategoriaEquipamentosBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT c FROM CategoriaEquipamentosBeans c ORDER BY c.ID");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static List<MarcaEquipamentosBeans> getListaMarca() {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Object transaction = null;
        List<MarcaEquipamentosBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT c FROM MarcaEquipamentosBeans c ORDER BY c.ID");
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public static String getIdFazendaPermitida() {
        String s = "";
        if (listaFazPermitida == null) {
            listaFazPermitida = getListaFazendasPermitidas();
        }
        for (int i = 0; i < listaFazPermitida.size(); i++) {
            s += ", " + listaFazPermitida.get(i).getCodigo();
        }
        s = "(" + s.replaceFirst(",", "") + ")";
        return s;
    }

    public static void main(String[] args) {

    }

}
