/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Almoxarifado.CadItensAlmoxAplicacao;
import Almoxarifado.CadItensAlmoxCodigos;
import Almoxarifado.CadItensAlmoxarifadoBeans;
import Icones.FormatarICO;
import NFe.NFeProdutosBeans;
import TableModel.TableModelConsultaCadAlmoxarif;
import TableModel.TbConsultaCadAlmoxarif;
import Utilitarios.Conexao;
import Utilitarios.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class CadAlmoxarifadaoDAO {

    String insertItem = "insert into cad_itens_almoxarif (id_categoria, descricao, observacao, status, id_unidade) values (?,?,?,?,?)";
    String insertCod = "insert into cad_itens_almox_codigos (id_item, id_categoria, codigo_catalogo, codigo, id_fornecedor, cnpj_fornecedor) values (?,?,?,?,?,?)";
    String insertAplic = "insert into cad_itens_almox_aplic (id_item, id_categoria_item, id_categoria_aplic, id_marca_aplic, id_modelo_aplic, marca, modelo) values (?,?,?,?,?,?,?)";

    String updateItem = "update cad_itens_almoxarif Set id_categoria = ?, descricao = ?, observacao = ?, status = ?, id_unidade = ? Where id = ?";
    String updateCod = "update cad_itens_almox_codigos Set id_categoria = ?, codigo_catalogo = ?, codigo = ?, id_fornecedor = ?, cnpj_fornecedor = ? Where id = ?";
    String updateAplic = "update cad_itens_almox_aplic Set id_categoria_item = ?, id_categoria_aplic = ?, id_marca_aplic = ?, id_modelo_aplic = ?, marca = ?, modelo = ? Where id = ?";

    PreparedStatement stInsertCodigo;
    PreparedStatement stInsertAplic;

    public Boolean CadastrarItem(CadItensAlmoxarifadoBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(beans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Item Cadastrado Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean CadastrarConversaoEmLote(CadItensAlmoxCodigos codigo) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.save(codigo);
            transaction.commit();
            //JOptionPane.showMessageDialog(null, "Conversão Cadastrada com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Conversão!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public List<CadItensAlmoxCodigos> buscarListaDeCodigos(String listaCodigos, Integer idFornecedor) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        List<CadItensAlmoxCodigos> lista = new ArrayList<>();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("Select C From CadItensAlmoxCodigos C JOIN FETCH C.id_item WHERE C.id_fornecedor.ID = :idFornecedor and C.Codigo IN " + listaCodigos);
            consulta.setParameter("idFornecedor", idFornecedor);
            lista = consulta.list();
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Lista Itens!", "Erro", 0, FormatarICO.ICObtnSair());
        } finally {
            sessao.close();
        }
        return lista;
    }

    public CadItensAlmoxarifadoBeans getItemBeans(Integer ID) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        CadItensAlmoxarifadoBeans item = new CadItensAlmoxarifadoBeans();
        try {
            transaction = sessao.beginTransaction();
            Query consulta = sessao.createQuery("SELECT Item FROM CadItensAlmoxarifadoBeans Item LEFT JOIN FETCH Item.listaCodigo WHERE Item.ID =:id");
            Query consultaAplic = sessao.createQuery("SELECT A FROM CadItensAlmoxAplicacao A JOIN FETCH A.id_categoria_aplic JOIN FETCH A.id_marca_aplic JOIN FETCH A.id_modelo_aplic WHERE A.id_item.ID =:idItem");
            consulta.setParameter("id", ID);
            consultaAplic.setParameter("idItem", ID);
            item = (CadItensAlmoxarifadoBeans) consulta.uniqueResult();
            List<CadItensAlmoxAplicacao> lista = consultaAplic.list();
            item.setListaAplicacao(lista);
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Consultar Lista Itens!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return item;
    }

    public void CadastrarConversao(NFeProdutosBeans prod, Integer ID_Fornecedor, String CNPJ) {
        Conexao.ReiniciarCon();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(insertCod);
            st.setInt(1, prod.getID_Cadastro());
            st.setInt(2, 0);
            st.setBoolean(3, false);
            st.setString(4, prod.getcProd());
            st.setInt(5, ID_Fornecedor);
            st.setString(6, CNPJ);
            st.execute();
            JOptionPane.showMessageDialog(null, "Registro inserido com sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao cadastrar conversão!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void CadastrarConversaoEmLOTE(List<NFeProdutosBeans> listProd, Integer ID_Fornecedor, String CNPJ) {
        Conexao.ReiniciarCon();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(insertCod);
            int contador = 0;
            for (int i = 0; i < listProd.size(); i++) {
                NFeProdutosBeans prod = listProd.get(i);
                if (prod.getID_Cadastro() > 0 && prod.getID_DB() == null) {
                    st.setInt(1, prod.getID_Cadastro());
                    st.setInt(2, 0);
                    st.setBoolean(3, false);
                    st.setString(4, prod.getcProd());
                    st.setInt(5, ID_Fornecedor);
                    st.setString(6, CNPJ);
                    st.addBatch();
                    contador += 1;
                }
            }
            st.executeBatch();
            JOptionPane.showMessageDialog(null, contador + " Registros inseridos com sucesso!", "Executado", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao cadastrar conversão!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public Boolean EditarItem(CadItensAlmoxarifadoBeans beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.saveOrUpdate(beans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Item Editado Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    private PreparedStatement insertCodigo(PreparedStatement stCodigo, Integer ID_Item, Integer IdCategoria, CadItensAlmoxCodigos bean) throws SQLException {
        stCodigo.setInt(1, ID_Item);
        stCodigo.setInt(2, IdCategoria);
        stCodigo.setBoolean(3, bean.getCodigoCatalogo());
        stCodigo.setString(4, bean.getCodigo());
        stCodigo.setInt(5, bean.getID_Fornecedor());
        stCodigo.setString(6, bean.getCNPJ_Fornecedor());
        stCodigo.addBatch();
        return stCodigo;
    }

    private PreparedStatement insertAplicacao(PreparedStatement stAplic, Integer LastID, Integer IdCategoria, CadItensAlmoxAplicacao bean) throws SQLException {
        stAplic.setInt(1, LastID);
        stAplic.setInt(2, IdCategoria);
        stAplic.setInt(3, bean.getIdCategoriaAplic());
        stAplic.setInt(4, bean.getIdMarcaAplic());
        stAplic.setInt(5, bean.getIdModeloAplica());
        stAplic.setString(6, bean.getMarca());
        stAplic.setString(7, bean.getModelo());
        stAplic.addBatch();
        return stAplic;
    }

    private PreparedStatement updateCodigo(PreparedStatement stCodigo, Integer IdCategoria, CadItensAlmoxCodigos bean) throws SQLException {
        stCodigo.setInt(1, IdCategoria);
        stCodigo.setBoolean(2, bean.getCodigoCatalogo());
        stCodigo.setString(3, bean.getCodigo());
        stCodigo.setInt(4, bean.getID_Fornecedor());
        stCodigo.setString(5, bean.getCNPJ_Fornecedor());
        stCodigo.setInt(6, bean.getID());
        stCodigo.addBatch();
        return stCodigo;
    }

    private PreparedStatement updateAplicacao(PreparedStatement stAplic, Integer IdCategoria, CadItensAlmoxAplicacao bean) throws SQLException {
        stAplic.setInt(1, IdCategoria);
        stAplic.setInt(2, bean.getIdCategoriaAplic());
        stAplic.setInt(3, bean.getIdMarcaAplic());
        stAplic.setInt(4, bean.getIdModeloAplica());
        stAplic.setString(5, bean.getMarca());
        stAplic.setString(6, bean.getModelo());
        stAplic.setInt(7, bean.getID());
        stAplic.addBatch();
        return stAplic;
    }

    private PreparedStatement insertUpdateCodigo(Integer ID, Integer IdCategoria, CadItensAlmoxCodigos bean) throws SQLException {
        stInsertCodigo = Conexao.getConnection().prepareStatement(insertCod);
        stInsertCodigo.setInt(1, ID);
        stInsertCodigo.setInt(2, IdCategoria);
        stInsertCodigo.setBoolean(3, bean.getCodigoCatalogo());
        stInsertCodigo.setString(4, bean.getCodigo());
        stInsertCodigo.setInt(5, bean.getID_Fornecedor());
        stInsertCodigo.setString(6, bean.getCNPJ_Fornecedor());
        stInsertCodigo.addBatch();
        return stInsertCodigo;
    }

    private PreparedStatement insertUpdateAplicacao(Integer ID, Integer IdCategoria, CadItensAlmoxAplicacao bean) throws SQLException {
        stInsertAplic.setInt(1, ID);
        stInsertAplic.setInt(2, IdCategoria);
        stInsertAplic.setInt(3, bean.getIdCategoriaAplic());
        stInsertAplic.setInt(4, bean.getIdMarcaAplic());
        stInsertAplic.setInt(5, bean.getIdModeloAplica());
        stInsertAplic.setString(6, bean.getMarca());
        stInsertAplic.setString(7, bean.getModelo());
        stInsertAplic.addBatch();
        return stInsertAplic;
    }

    public void consultarCadastro(TableModelConsultaCadAlmoxarif TbPecas, String SQLWhere) {
        Conexao.ReiniciarCon();
        String consulta = "SELECT \n"
                + "    ci.id AS ID, IFNULL(cc.codigo, '-') AS Codigo, cc.codigo_catalogo AS Original, \n"
                + "    IFNULL((Select cu.Descricao from cad_unidades cu WHERE cu.id = ci.id_unidade), '-') as Unidade, \n"
                + "    ci.descricao AS Descricao, ifnull(c_equip.descricao, '-') AS Modelo\n"
                + "FROM\n"
                + "    cad_itens_almoxarif ci\n"
                + "    LEFT JOIN cad_itens_almox_codigos cc  ON ci.id = cc.id_item\n"
                + "    LEFT JOIN cad_itens_almox_aplic ca ON ci.id = ca.id_item\n"
                + "    LEFT JOIN cad_modelo_equip c_equip ON c_equip.id = ca.id_modelo_aplic\n" + SQLWhere;
        //System.out.println(consulta);
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(consulta);
            ResultSet rs = st.executeQuery();
            TbPecas.limpar();
            while (rs.next()) {
                TbConsultaCadAlmoxarif p = new TbConsultaCadAlmoxarif();
                p.setID(rs.getInt("ID"));
                p.setCodigo(rs.getString("Codigo"));
                p.setCodigoCatalogo(rs.getBoolean("Original"));
                p.setUnidade(rs.getString("Unidade"));
                p.setDescricao(rs.getString("Descricao"));
                p.setModelo(rs.getString("Modelo"));
                TbPecas.addBeans(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao consultar peças!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void consultarCodigoPorFornecedor(TableModelConsultaCadAlmoxarif TbPecas, String Codigo, Integer Id_Fornecedor) {
        Conexao.ReiniciarCon();
        String consulta = "SELECT \n"
                + "    ci.id AS ID, cc.codigo AS Codigo, cc.codigo_catalogo AS Original, ci.descricao AS Descricao, c_equip.descricao AS Modelo\n"
                + "FROM \n"
                + "    cad_itens_almox_codigos cc, cad_itens_almoxarif ci, cad_itens_almox_aplic ca, cad_modelo_equip c_equip\n"
                + "WHERE \n"
                + "    ci.id = ca.id_item AND ci.id = cc.id_item AND c_equip.id = ca.id_modelo_aplic AND cc.codigo = ? AND cc.id_fornecedor = ?";
        //System.out.println(consulta);
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(consulta);
            st.setString(1, Codigo);
            st.setInt(2, Id_Fornecedor);
            ResultSet rs = st.executeQuery();
            TbPecas.limpar();
            while (rs.next()) {
                TbConsultaCadAlmoxarif p = new TbConsultaCadAlmoxarif();
                p.setID(rs.getInt("ID"));
                p.setCodigo(rs.getString("Codigo"));
                p.setCodigoCatalogo(rs.getBoolean("Original"));
                p.setDescricao(rs.getString("Descricao"));
                p.setModelo(rs.getString("Modelo"));
                TbPecas.addBeans(p);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao consultar peças!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public List<CadItensAlmoxCodigos> consultarListaDeCodigoCodigoFornecedor(Integer ID_Fornecedor, String Codigos) {
        Conexao.ReiniciarCon();
        String select = "SELECT\n"
                + "    cc.id as ID_DB, ci.id AS ID_ITEM, cc.codigo AS Codigo\n"
                + "FROM\n"
                + "	cad_itens_almoxarif ci\n"
                + "	JOIN cad_itens_almox_codigos cc  ON ci.id = cc.id_item\n"
                + "WHERE\n"
                + "	(cc.id_fornecedor = ? and cc.codigo IN (" + Codigos + ")) ";
        List<CadItensAlmoxCodigos> listaID = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, ID_Fornecedor);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CadItensAlmoxCodigos codigo = new CadItensAlmoxCodigos();
                codigo.setID(rs.getInt("ID_DB"));
                codigo.setID_ITEM(rs.getInt("ID_ITEM"));
                codigo.setCodigo(rs.getString("Codigo"));
                listaID.add(codigo);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao consultar peças!", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return listaID;
    }

    public CadItensAlmoxarifadoBeans getItem(Integer ID) {
        Conexao.ReiniciarCon();
        String selectItem = "select ci.id as ID, ci.id_categoria as IDCat, ci.descricao as Descricao, ci.observacao as Obs, ci.status as Status, ci.id_unidade as Unidade FROM cad_itens_almoxarif ci where ci.id = " + ID;

        String selectCod = "select cc.id as ID, cc.id_categoria as IDCat, cc.codigo_catalogo as Original, cc.codigo as Codigo, cc.id_fornecedor as IdForn,\n"
                + "cc.cnpj_fornecedor as CNPJ_Forn from cad_itens_almox_codigos cc Where cc.id_item = " + ID;

        String selectAplic = "Select ca.id as ID, ca.id_categoria_item as IDCat, ca.id_categoria_aplic as CatAplic, ca.id_marca_aplic as ID_Marca, ca.id_modelo_aplic as ID_Modelo,\n"
                + "(Select cm.marca from cad_marca_equipamentos cm Where cm.id = ca.id_marca_aplic) as Marca, \n"
                + "(select ce.descricao from cad_modelo_equip ce Where ce.id = ca.id_modelo_aplic) as Modelo  \n"
                + "from cad_itens_almox_aplic ca Where ca.id_item = " + ID;
        CadItensAlmoxarifadoBeans beans = new CadItensAlmoxarifadoBeans();
        List<CadItensAlmoxCodigos> listCod = new ArrayList<>();
        List<CadItensAlmoxAplicacao> listAplic = new ArrayList<>();
        try {
            PreparedStatement stItem = Conexao.getConnection().prepareStatement(selectItem);
            PreparedStatement stCod = Conexao.getConnection().prepareStatement(selectCod);
            PreparedStatement stAplic = Conexao.getConnection().prepareStatement(selectAplic);
            ResultSet rsItem = stItem.executeQuery();
            while (rsItem.next()) {
                beans.setID(rsItem.getInt("ID"));
                beans.setIDCategoria(rsItem.getInt("IDCat"));
                beans.setDescricao(rsItem.getString("Descricao"));
                beans.setObservacao(rsItem.getString("Obs"));
                beans.setStatus(rsItem.getBoolean("Status"));
                beans.setIDUnidade(rsItem.getInt("Unidade"));
            }

            ResultSet rsCod = stCod.executeQuery();
            while (rsCod.next()) {
                CadItensAlmoxCodigos c = new CadItensAlmoxCodigos();
                c.setID(rsCod.getInt("ID"));
                c.setID_ITEM(ID);
                c.setCodigoCatalogo(rsCod.getBoolean("Original"));
                c.setCodigo(rsCod.getString("Codigo"));
                c.setID_Fornecedor(rsCod.getInt("IdForn"));
                c.setCNPJ_Fornecedor(rsCod.getString("CNPJ_Forn"));
                listCod.add(c);
            }

            ResultSet rsAplic = stAplic.executeQuery();
            while (rsAplic.next()) {
                CadItensAlmoxAplicacao aplic = new CadItensAlmoxAplicacao();
                aplic.setID(rsAplic.getInt("ID"));
                aplic.setIdItem(ID);
                aplic.setIdCategoriaItem(rsAplic.getInt("IDCat"));
                aplic.setIdCategoriaAplic(rsAplic.getInt("CatAplic"));
                aplic.setIdMarcaAplic(rsAplic.getInt("ID_Marca"));
                aplic.setIdModeloAplica(rsAplic.getInt("ID_Modelo"));
                aplic.setMarca(rsAplic.getString("Marca"));
                aplic.setModelo(rsAplic.getString("Modelo"));
                listAplic.add(aplic);
            }
            beans.setListaCodigo(listCod);
            beans.setListaAplicacao(listAplic);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Item!", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return beans;
    }

    public Boolean DeletarCodigo(CadItensAlmoxCodigos beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(beans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Codigo Excluido Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean DeletarAplicacao(CadItensAlmoxAplicacao beans) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try {
            transaction = sessao.beginTransaction();
            sessao.delete(beans);
            transaction.commit();
            JOptionPane.showMessageDialog(null, "Aplicação Excluída Com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (HibernateException e) {
            transaction.rollback();
            JOptionPane.showMessageDialog(null, e + " Erro ao Cadastrar Item!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

}
