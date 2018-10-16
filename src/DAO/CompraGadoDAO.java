package DAO;

import Beans.CompraGadoBeans;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import Utilitarios.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CompraGadoDAO {

    public Boolean inserirCompra(CompraGadoBeans compra) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.save(compra);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Ordem de Compra de Gado Registrada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean editarCompra(CompraGadoBeans compra) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.update(compra);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Ordem de Compra de Gado Editada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public void pesquisarCompras(DefaultTableModel TbCompra, String Where) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select *, " // 24 linhas
                + "ifnull((select Sum(ent_gado_nf.ent_gado_QNF) From ent_gado_nf Where ent_gado_nf.ent_gado_ncompra = compra_gado.compra_gado_id),0) as QNF," // 25
                + "ifnull((select Sum(ent_gado_fisica.ent_gado_fis_quantGTA) From ent_gado_fisica WHERE ent_gado_fis_nCompra = compra_gado.compra_gado_id ),0) as QGTA," // 26
                + "ifnull((select Sum(ent_gado_fisica.ent_gado_fis_quantCab) From ent_gado_fisica WHERE ent_gado_fis_nCompra = compra_gado.compra_gado_id),0) as QMin, " // 27
                + "ifnull((select Sum(pagamentos.pagto_valorPagto) From pagamentos WHERE pagamentos.pagto_nCompra = compra_gado.compra_gado_id),0) as ValorPago, " // 28
                + "ifnull((SELECT Sum(ent_gado_fisica.ent_gado_fis_pesoL) as Peso From ent_gado_fisica Where ent_gado_fis_nCompra = compra_gado.compra_gado_id),0) as PesoGado, " // 29
                + "@dtPagto:= ifnull((select Min(pagamentos.pagto_DataPagto) From pagamentos WHERE pagamentos.pagto_nCompra = compra_gado.compra_gado_id),0) as DataPagto, " // 30
                + "ifnull((select Sum(ent_gado_fis_vlrFrete) from ent_gado_fisica Where ent_gado_fis_nCompra = compra_gado_id) ,0 ) as VlrFrete, " // 31
                + "ifnull((select avg(ent_gado_fis_km) From ent_gado_fisica WHERE ent_gado_fis_nCompra = compra_gado.compra_gado_id),0) as KmMedio " // 32
                + "FROM compra_gado " + Where ;

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbCompra.setNumRows(0);
            double ReaisAr;
            double PesoMedio;
            String dataPagto;
            double freteReaisAr = 0;
            double Quebra = 0;
            while (rs.next()) {
                if (rs.getDouble(29) == 0) {
                    ReaisAr = 0;
                } else {
                    ReaisAr = (rs.getDouble(28) / rs.getDouble(29)) * 30;
                }

                if (rs.getInt(27) == 0) {
                    PesoMedio = 0;
                    Quebra = 0;
                } else {
                    PesoMedio = (rs.getDouble(29) / rs.getInt(27));
                    if (PesoMedio > 0 & rs.getDouble(18) > 0) {
                        Quebra = (PesoMedio / rs.getDouble(18) - 1);
                    } else {
                        Quebra = 0;
                    }
                }

                if (rs.getInt("QMin") != 0) {
                    if (PesoMedio == 0) {
                        if (rs.getDouble(18) != 0) {
                            freteReaisAr = ((rs.getDouble("vlrFrete") / rs.getInt("QMIN")) / rs.getDouble(18)) * 30;
                        }
                    } else {
                        freteReaisAr = ((rs.getDouble("vlrFrete") / rs.getInt("QMIN")) / PesoMedio) * 30;
                    }
                }
                if (rs.getDate("DataPagto") == null) {
                    dataPagto = "";
                } else {
                    dataPagto = Corretores.ConverterDateStringDDMMAAA(rs.getDate("DataPagto"));
                }

                TbCompra.addRow(new Object[]{rs.getInt(1), Corretores.ConverterParaJava(rs.getString(4)), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getInt(9), new DecimalFormat("R$ #,##0.00").format(rs.getDouble(10)), new DecimalFormat("#,##0.00 kg").format(rs.getDouble(11)), new DecimalFormat("R$ #,##0.00").format(rs.getDouble(12)), rs.getString(13), rs.getString(14), new DecimalFormat("#,##0.00 kg").format(rs.getDouble(18)), new DecimalFormat("R$ #,##0.00").format(rs.getDouble(19)), new DecimalFormat("R$ #,##0.00").format(rs.getDouble(28)), rs.getString(20), rs.getInt(25), rs.getInt(26), rs.getInt(27), rs.getString(15), rs.getString(16), rs.getString(17), new DecimalFormat("R$ #,##0.00").format(ReaisAr), new DecimalFormat("R$ #,##0.00").format(freteReaisAr), new DecimalFormat("#,##0.00 kg").format(PesoMedio), new DecimalFormat("0.0 %").format(Quebra), 0, rs.getInt("KmMedio"), dataPagto});

            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            
        }
            
    }

    public CompraGadoBeans buscarCompra(Integer ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        CompraGadoBeans compra = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query consulta = sessao.createQuery("From CompraGadoBeans c JOIN FETCH c.CategoriaAnimal Where c.ID = :id");
            consulta.setParameter("id", ID);
            compra = (CompraGadoBeans) consulta.uniqueResult();
        } catch (RuntimeException e) {
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Ordem!", "Erro", 0, FormatarICO.ICObtnSair());
            return compra;
        } finally {
            sessao.close();
        }
        return compra;
    }

    private void rollBack(Transaction transacao) {
        if (transacao != null) {
            transacao.rollback();
        }
    }

    public Boolean excluirOrdem(CompraGadoBeans compra) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(compra);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Ordem de Compra Excluída com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            rollBack(transacao);
            JOptionPane.showMessageDialog(null, e + " Erro Resgitrar Orderm!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

}
