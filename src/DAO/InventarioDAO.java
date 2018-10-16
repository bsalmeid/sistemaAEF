package DAO;

import Icones.FormatarICO;
import Beans.CategoriaEquipamentosBeans;
import Beans.InventarioBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MarcaEquipamentosBeans;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.transform.AliasToBeanResultTransformer;

public class InventarioDAO {

    private Session sessao;
    private Transaction transaction;

    public InventarioDAO() {
        
    }

    public Boolean salvarInventario(InventarioBeans inv) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(inv);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Inventário Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Inventário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarInventario(InventarioBeans inv) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(inv);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Inventário Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Inventário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean deletarInventario(InventarioBeans inv) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(inv);
            JOptionPane.showMessageDialog(null, "Inventário Deletado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            transaction.commit();
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Deletar Inventário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarModelo(ModeloEquipamentosBeans mod) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(mod);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Modelo Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Modelo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarModelo(ModeloEquipamentosBeans mod) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(mod);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Modelo Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Modelo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean deletarModelo(ModeloEquipamentosBeans mod) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(mod);
            JOptionPane.showMessageDialog(null, "Modelo Deletado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
            transaction.commit();
        } catch (HibernateException e) {
             transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Deletar Modelo!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarMarca(MarcaEquipamentosBeans mac) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(mac);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Marca Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Marca!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarMarca(MarcaEquipamentosBeans mac) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(mac);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Marca Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Marca!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarCategoria(CategoriaEquipamentosBeans cat) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(cat);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Categoria Salva com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarCategoria(CategoriaEquipamentosBeans cat) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(cat);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Categoria Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public InventarioBeans buscarInventario(Integer ID) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        InventarioBeans i = new InventarioBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT T FROM InventarioBeans T WHERE T.ID = :id" );
            consulta.setParameter("id", ID);
            i = (InventarioBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
          JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return i;
    }

    public List<InventarioBeans> listarInventarioString(String Where) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<InventarioBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT T.ID as ID, T.id_fazenda.Nome as Fazenda, T.id_categoria.Categoria as Categoria,"
                    + " T.id_marca.Marca as Marca, T.id_modelo.descricao as Modelo, T.nFrota as nFrota,T.dataAquisicao as dataAquisicao,"
                    + " T.motorizado as motorizado, T.LarguraTrabalho as LarguraTrabalho,T.ano as ano, T.serie as serie, T.descricao as descricao,"
                    + " T.status as status FROM InventarioBeans T  " + Where+ " ORDER BY T.ID");
            consulta.setResultTransformer(new AliasToBeanResultTransformer(InventarioBeans.class));
            lista = consulta.list();
        } catch (HibernateException e) {
            System.out.println(e);
        } finally {
            sessao.close();
        }
        return lista;
    }//FrmCadInventario

    public List<ModeloEquipamentosBeans> listarModelo() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<ModeloEquipamentosBeans> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT M FROM ModeloEquipamentosBeans M JOIN FETCH M.id_categoria JOIN FETCH M.id_marca ORDER BY M.ID");
            lista = consulta.list();
        } catch (HibernateException e) {
           JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }//FrmCadModelo, FrmCadInventario

    public List<CategoriaEquipamentosBeans> listarCategoria() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
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
    }//FrmCadCategoria, FrmCadInventario

    public List<MarcaEquipamentosBeans> listarMarca() {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
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
    }//FrmCadMarca,FrmCadInventario

    public CategoriaEquipamentosBeans buscarCategoria(String nome) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        CategoriaEquipamentosBeans C = new CategoriaEquipamentosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT c FROM CategoriaEquipamentosBeans c WHERE c.Categoria = :nome");
            consulta.setParameter("nome", nome);
            C = (CategoriaEquipamentosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return C;
    }//FrmCadCategoria

    public MarcaEquipamentosBeans buscarMarca(String nome) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        MarcaEquipamentosBeans C = new MarcaEquipamentosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT c FROM MarcaEquipamentosBeans c WHERE c.Marca = :nome");
            consulta.setParameter("nome", nome);
            C = (MarcaEquipamentosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return C;
    }//FrmCadMarca

    public ModeloEquipamentosBeans buscarModelo(String nome) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        ModeloEquipamentosBeans M = new ModeloEquipamentosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT m FROM ListModeloEquipamentosBeans m WHERE m.descricao = :nome");
            consulta.setParameter("nome", nome);
            M = (ModeloEquipamentosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return M;
    }//FrmCadModelo

    public ModeloEquipamentosBeans buscarModeloCategoria(String nome) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        ModeloEquipamentosBeans M = new ModeloEquipamentosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT m FROM ListModeloEquipamentosBeans m WHERE m.id_categoria.Categoria = :nome");
            consulta.setParameter("nome", nome);
            M = (ModeloEquipamentosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
         JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return M;
    }//FrmCadInventario

    public ModeloEquipamentosBeans buscarModeloMarca(String nome) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        ModeloEquipamentosBeans M = new ModeloEquipamentosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT m FROM ListModeloEquipamentosBeans m WHERE m.id_marca.Marca = :nome");
            consulta.setParameter("nome", nome);
            M = (ModeloEquipamentosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Categoria!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return M;
    }//FrmCadInventario

}
