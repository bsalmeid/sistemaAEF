package DAO;

import Beans.CadPlacaBeans;
import Beans.TransportadorasBeans;
import Icones.FormatarICO;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Usuario
 */
public class TransportadorasDAO {

    private Session sessao;
    private Transaction transaction;

    public TransportadorasDAO() {
    }

    public Boolean salvarPlaca(CadPlacaBeans placa) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = sessao.beginTransaction();
            sessao.save(placa);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Placa salva com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            sessao.close();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao salvar Transportadora!", "Erro", 0, FormatarICO.ICObtnSair());
            System.out.println(e);
            return false;
        }
        return true;
    }

    public Boolean atualizarPlaca(CadPlacaBeans placa) {

        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(placa);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Placa atualizada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao atualizar Transportadora!", "Erro", 0, FormatarICO.ICObtnSair());
            transaction.rollback();
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean deletarPlaca(Long id) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = sessao.beginTransaction();
            Query query = sessao.createQuery("DELETE CadPlacaBeans where id = :id");
            query.setParameter("id", id);
            int list = query.executeUpdate();
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Placa apagada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            sessao.close();
        } catch (HibernateException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e + " Erro ao apagar Transportadora!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    public Boolean salvarTransportadora(TransportadorasBeans trans) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        try {
            transaction = sessao.beginTransaction();
            sessao.save(trans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Transportadora Salva com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            sessao.close();
        } catch (HibernateException e) {
            System.out.println(e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Transportadora!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    public Boolean atualizarTransportadoras(TransportadorasBeans trans) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(trans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Transportadora Atualizada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Transportadora!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean deletarTransportadoras(TransportadorasBeans trans) {

        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(trans);
            JOptionPane.showMessageDialog(null, "Transportadora Deletada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            transaction.commit();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Deletar Transportadora!", "Erro", 0, FormatarICO.ICObtnSair());
            transaction.rollback();
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public List<CadPlacaBeans> buscarPlaca(String Where) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        List<CadPlacaBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("Select new CadPlacaBeans(T, "
                    + " P.placa as placa, "
                    + " P.veiculo as veiculo, "
                    + " P.carroceria as carroceria," 
                    + " P.camProprio as camProprio, "
                    + " P.marca as marca, "
                    + " P.modelo as modelo, "
                    + " P.ano as ano, "
                    + " P.capacidadeCarga as capacidadeCarga, "
                    + " P.status as status) From  TransportadorasBeans T LEFT JOIN T.listPlacas P " + Where + " ORDER BY T.ID");
            lista = consulta.list();

        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            sessao.close();
        }
        return lista;
    }

    public TransportadorasBeans buscarTransportadora(Integer ID) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        TransportadorasBeans t = new TransportadorasBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("Select t FROM TransportadorasBeans t LEFT JOIN FETCH t.listPlacas WHERE t.ID = :ID");
            consulta.setParameter("ID", ID);
            t = (TransportadorasBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            sessao.close();
        }
        return t;
    }

}
