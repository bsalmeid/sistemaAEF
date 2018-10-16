package DAO;

import Beans.CadColaboradorBeans;
import Beans.CadColaboradorClassBeans;
import Icones.FormatarICO;
import Beans.FilhosFuncionariosBeans;
import TableModel.TbResumoColaborador;
import Utilitarios.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import org.hibernate.*;
import org.hibernate.transform.AliasToBeanResultTransformer;

public class CadColaboradorDAO {

    private Session sessao;
    private Transaction transaction;
    /*-------------------------------------------------------------------------------------------*/
    public Boolean salvarFuncionario(CadColaboradorBeans funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(funcionario);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Funcionário Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            //System.out.println("  Erro SALVAR  " + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editarClassificaoLote(List<Integer> listaID, List<CadColaboradorClassBeans> listaClassificacao) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            Query deletar = sessao.createQuery("DELETE CadColaboradorClassBeans C WHERE C.IdColaborador.Id IN :listaID ");
            deletar.setParameterList("listaID", listaID);
            deletar.executeUpdate();
            for (CadColaboradorClassBeans clas : listaClassificacao){
                sessao.save(clas);
            }
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Classificação Salva com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarFuncionario(CadColaboradorBeans funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(funcionario);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Funcionário Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            //System.out.println("  Erro  Atualizar " + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean removerFuncionario(CadColaboradorBeans funcionario) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(funcionario);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Funcionário Deletado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println(" Erro Remover " + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Deletar Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean salvarFilhos(FilhosFuncionariosBeans filhos) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(filhos);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Filhos do Funcionário Salvo com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Erro" + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Salvar Filhos do Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean atualizarFilhos(FilhosFuncionariosBeans filhos) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.update(filhos);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Filhos do Funcionário Atualizado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Erro" + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Atualizar Filhos do Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean removerFilhos(FilhosFuncionariosBeans filhos) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(filhos);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Filhos do Funcionário Deletado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            System.out.println("Erro" + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Deletar Filhos do Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean DeletarClassicacao(CadColaboradorClassBeans clas) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        try {
            transaction = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(clas);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Classificação Excluída com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            transaction.rollback();
            System.out.println(" Erro " + e);
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public FilhosFuncionariosBeans buscarFilhos(Integer ID) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        FilhosFuncionariosBeans F = new FilhosFuncionariosBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT T FROM FilhosFuncionariosBeans T WHERE T.ID = :id");
            consulta.setParameter("id", ID);
            F = (FilhosFuncionariosBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Buscar Filhos do Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return F;
    }

    public CadColaboradorBeans buscarColaborador(Integer Id) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        CadColaboradorBeans C = new CadColaboradorBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT C FROM CadColaboradorBeans C LEFT JOIN FETCH C.listFilhos WHERE C.Id = :Id");
            Query consultaClass = sessao.createQuery("Select Clas FROM CadColaboradorClassBeans Clas JOIN FETCH Clas.IdCentro JOIN FETCH Clas.IdFazenda JOIN FETCH Clas.IdPlanoConta WHERE Clas.IdColaborador.Id = :Id");
            consultaClass.setParameter("Id", Id);
            consulta.setParameter("Id", Id);
            C = (CadColaboradorBeans) consulta.uniqueResult();
            C.setListaClassificacao(consultaClass.list());
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Buscar Funcionário!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return C;
    }

    public CadColaboradorClassBeans buscarClassificacao(Long Id) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        CadColaboradorClassBeans C = new CadColaboradorClassBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT C FROM CadColaboradorClassBeans C  WHERE C.Id = :Id");
            consulta.setParameter("Id", Id);
            C = (CadColaboradorClassBeans) consulta.uniqueResult();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Buscar Classificação!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return C;
    }

    public List<TbResumoColaborador> listarColaborador(String Where) {
        sessao = HibernateUtil.getSessionFactory().openSession();
        transaction = null;
        List<TbResumoColaborador> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT C.Id as ID_COLABORADOR, C.Ativo as STATUS, C.NomeFunc as NOME, C.IdCei.CEI as CEI, "
                    + " C.idFazenda.Nome as FAZENDA, C.idSetor.Descricao as SETOR, C.idFuncao.Descricao as FUNCAO, C.Salario as SALARIO, "
                    + " C.dtAdmissao as DT_ADMISSAO FROM CadColaboradorBeans C " + Where);
            consulta.setResultTransformer(new AliasToBeanResultTransformer(TbResumoColaborador.class));
            lista = consulta.list();
        } catch (HibernateException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Realizar Consulta!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

}
