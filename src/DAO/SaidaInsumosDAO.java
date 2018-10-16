/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.SaidaInsumosBeans;
import Icones.FormatarICO;
import TableModel.TableModelSaidaInsumos;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author agroa
 */
public class SaidaInsumosDAO {

    public Boolean inserirMovimento(SaidaInsumosBeans beans, TableModelSaidaInsumos TbInsumos) {
        int lastId = 0;
        Conexao.ReiniciarCon();
        String SQLInsertDoc = "insert into saida_insumos_doc (id_ordemAplicacao," + "data_saida, operacao," + "idLocalEstoque,idSafra, "
                + "idCultivo,idCultura," + "idAplicacao,nDoc," + "responsavel,observacao) values (?,?,?,?,?,?,?,?,?,?,?)";

        String SQLInsertInsumo = "insert into saida_insumos (id_documento, id_ordem ,id_insumo, nome_insumo, categoria, quantidade) values (?,?,?,?,?,?)";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertDoc, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(SQLInsertInsumo);
            Conexao.getConnection().setAutoCommit(false);
            st.setInt(1, beans.getIDOrdem());
            st.setString(2, Corretores.ConverterParaSQL(beans.getDtSaida()));
            st.setString(3, beans.getOperacao());
            st.setInt(4, beans.getIdLocalSaida());
            st.setInt(5, beans.getIdSafra());
            st.setInt(6, beans.getIdCultivo());
            st.setInt(7, beans.getIdCultura());
            st.setInt(8, beans.getIdAplicao());
            st.setInt(9, beans.getnDoc());
            st.setString(10, beans.getResonsavel());
            st.setString(11, beans.getObservacao());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                lastId = rs.getInt(1);
            }

            for (int i = 0; i < TbInsumos.getRowCount(); i++) {
                stInsumo.setInt(1, lastId);
                stInsumo.setInt(2, (Integer) TbInsumos.getValueAt(i, 1));
                stInsumo.setInt(3, (Integer) TbInsumos.getValueAt(i, 17));
                stInsumo.setString(4, TbInsumos.getValueAt(i, 18).toString());
                stInsumo.setString(5, TbInsumos.getValueAt(i, 20).toString());
                stInsumo.setDouble(6, (Double) TbInsumos.getValueAt(i, 21));
                stInsumo.addBatch();
            }
            stInsumo.executeBatch();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Saída registrada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SaidaInsumosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao salvar registro! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    public Boolean editarMovimento(SaidaInsumosBeans beans, TableModelSaidaInsumos TbInsumos) {
        Conexao.ReiniciarCon();
        String SQLUpdate = "Update saida_insumos_doc SET id_ordemAplicacao = ?, "
                + "data_saida = ?, " + "operacao = ?, " + "idLocalEstoque = ?, " + "idSafra = ?, " + "idCultivo = ?, "
                + "idCultura = ?, " + "idAplicacao = ?, " + "nDoc = ?, " + "responsavel = ?, " + "observacao = ? " + "WHERE id = ?";
        String SQLUpdateInsumos = "Update saida_insumos SET id_documento = ?,\n"
                + "id_ordem = ?, " + "id_insumo = ?,\n" + "nome_insumo = ?,\n" + "categoria = ?,\n" + "quantidade = ?\n" + "WHERE id = ?;";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(SQLUpdateInsumos);
            Conexao.getConnection().setAutoCommit(false);
            st.setInt(1, beans.getIDOrdem());
            st.setString(2, Corretores.ConverterParaSQL(beans.getDtSaida()));
            st.setString(3, beans.getOperacao());
            st.setInt(4, beans.getIdLocalSaida());
            st.setInt(5, beans.getIdSafra());
            st.setInt(6, beans.getIdCultivo());
            st.setInt(7, beans.getIdCultura());
            st.setInt(8, beans.getIdAplicao());
            st.setInt(9, beans.getnDoc());
            st.setString(10, beans.getResonsavel());
            st.setString(11, beans.getObservacao());
            st.setInt(12, beans.getIdDoc());
            st.executeUpdate();

            for (int i = 0; i < TbInsumos.getRowCount(); i++) {
                if ((Integer) TbInsumos.getValueAt(i, 0) > 0) {
                    stInsumos.setInt(1, beans.getIdDoc());
                    stInsumos.setInt(2, (Integer) TbInsumos.getValueAt(i, 1));
                    stInsumos.setInt(3, (Integer) TbInsumos.getValueAt(i, 17));
                    stInsumos.setString(4, TbInsumos.getValueAt(i, 18).toString());
                    stInsumos.setString(5, TbInsumos.getValueAt(i, 20).toString());
                    stInsumos.setDouble(6, (Double) TbInsumos.getValueAt(i, 21));
                    stInsumos.setInt(7, (Integer) TbInsumos.getValueAt(i, 0));
                    stInsumos.addBatch();
                } else {
                    incluirInsumo(i, beans, TbInsumos);
                }
            }

            stInsumos.executeBatch();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            st.close();
            JOptionPane.showMessageDialog(null, "Saída editada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao editar registro! ", "Erro", 0, FormatarICO.ICObtnSair());
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(SaidaInsumosDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            return false;
        }
        return true;
    }

    public void pesquisarSaidas(TableModelSaidaInsumos TbResumo, Integer idLocalEstoque, String dtInicial, String dtFinal, String SQLWhere) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select \n"
                + "i.id as IdSaidaInsumo,\n"
                + "doc.id_ordemAplicacao as idOrdemAplic,\n"
                + "doc.data_saida as DataSaida,\n"
                + "doc.operacao as Operacao,\n"
                + "doc.idLocalEstoque as idLocalEstoque,\n"
                + " (select cf.cad_fazendas_nome from cad_fazendas cf where cf.cad_fazendas_id = doc.idLocalEstoque) as LocalEstoque,\n"
                + "doc.idSafra as idSafra,\n"
                + " (select cs.cad_safra_ano from cad_safra cs where cs.cad_safra_id = doc.idSafra) as Safra,\n"
                + "doc.idCultivo as idCultivo, \n"
                + "(select ec.cultivo from cad_epocacultivo ec where ec.id = doc.idCultivo) as Cultivo,\n"
                + "doc.idCultura as idcultura,\n"
                + "(select cc.cultura from cad_culturas cc where cc.id = doc.idCultura) as Cultura,\n"
                + "doc.idAplicacao as idAplicacao,\n"
                + "(select ca.nome from cad_aplicacoes ca where ca.id = doc.idAplicacao ) as Aplicacao,\n"
                + "doc.nDoc as nDoc, doc.responsavel as Responsavel, doc.observacao as Observacao, i.id_insumo as idInsumo, i.nome_insumo as NomeInsumo,\n"
                + "(select ci.cad_ins_unid from cad_insumos ci where ci.cad_ins_id = i.id_insumo) as Unidade,\n"
                + "i.categoria as Categoria, i.quantidade as Quantidade \n"
                + "FROM saida_insumos_doc doc, saida_insumos i WHERE doc.id = i.id_documento\n"
                + " and doc.data_saida BETWEEN ? and ? \n"
                + " and doc.idLocalEstoque = ? " + SQLWhere;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Corretores.ConverterParaSQL(dtInicial));
            st.setString(2, Corretores.ConverterParaSQL(dtFinal));
            st.setInt(3, idLocalEstoque);

            ResultSet rs = st.executeQuery();
            List<SaidaInsumosBeans> list = new ArrayList<>();
            TbResumo.limpar();
            while (rs.next()) {
                SaidaInsumosBeans SaidaB = new SaidaInsumosBeans();
                SaidaB.setID(rs.getInt("IdSaidaInsumo"));
                SaidaB.setIDOrdem(rs.getInt("idOrdemAplic"));
                SaidaB.setDtSaida(Corretores.ConverterParaJava(rs.getString("DataSaida")));
                SaidaB.setOperacao(rs.getString("Operacao"));
                SaidaB.setIdLocalSaida(rs.getInt("idLocalEstoque"));
                SaidaB.setLocalSaida(rs.getString("LocalEstoque"));
                SaidaB.setIdSafra(rs.getInt("idSafra"));
                SaidaB.setSafra(rs.getString("Safra"));
                SaidaB.setIdCultivo(rs.getInt("idCultivo"));
                SaidaB.setCultivo(rs.getString("Cultivo"));
                SaidaB.setIdCultura(rs.getInt("idCultura"));
                SaidaB.setCultura(rs.getString("Cultura"));
                SaidaB.setIdAplicao(rs.getInt("idAplicacao"));
                SaidaB.setAplicacao(rs.getString("Aplicacao"));
                SaidaB.setnDoc(rs.getInt("nDoc"));
                SaidaB.setResonsavel(rs.getString("Responsavel"));
                SaidaB.setObservacao(rs.getString("Observacao"));
                SaidaB.setIdInsumo(rs.getInt("idInsumo"));
                SaidaB.setNomeInsumo(rs.getString("NomeInsumo"));
                SaidaB.setUnidade(rs.getString("Unidade"));
                SaidaB.setCategoria(rs.getString("Categoria"));
                SaidaB.setQuant(rs.getDouble("Quantidade"));
                list.add(SaidaB);
            }
            TbResumo.addLista(list);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao realizar pesquisa! ", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public Boolean BuscarSaida(TableModelSaidaInsumos TbInsumos, Integer idSaida, SaidaInsumosBeans SaidaB) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select \n"
                + "i.id as IdSaidaInsumo,\n"
                + "doc.id_ordemAplicacao as idOrdemAplic,\n"
                + "doc.data_saida as DataSaida,\n"
                + "doc.operacao as Operacao,\n"
                + "doc.idLocalEstoque as idLocalEstoque,\n"
                + " (select cf.cad_fazendas_nome from cad_fazendas cf where cf.cad_fazendas_id = doc.idLocalEstoque) as LocalEstoque,\n"
                + "doc.idSafra as idSafra,\n"
                + " (select cs.cad_safra_ano from cad_safra cs where cs.cad_safra_id = doc.idSafra) as Safra,\n"
                + "doc.idCultivo as idCultivo, \n"
                + "(select ec.cultivo from cad_epocacultivo ec where ec.id = doc.idCultivo) as Cultivo,\n"
                + "doc.idCultura as idcultura,\n"
                + "(select cc.cultura from cad_culturas cc where cc.id = doc.idCultura) as Cultura,\n"
                + "doc.idAplicacao as idAplicacao,\n"
                + "(select ca.nome from cad_aplicacoes ca where ca.id = doc.idAplicacao ) as Aplicacao,\n"
                + "doc.nDoc as nDoc, doc.responsavel as Responsavel, doc.observacao as Observacao, i.id_insumo as idInsumo, i.nome_insumo as NomeInsumo,\n"
                + "(select ci.cad_ins_unid from cad_insumos ci where ci.cad_ins_id = i.id_insumo) as Unidade,\n"
                + "i.categoria as Categoria, i.quantidade as Quantidade, \n"
                + "(select i.id_documento from saida_insumos i where i.id = ?) as IdDoc "
                + " FROM saida_insumos_doc doc, saida_insumos i WHERE doc.id = i.id_documento\n"
                + " and doc.id = (select i.id_documento from saida_insumos i where i.id = ?)";

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setInt(1, idSaida);
            st.setInt(2, idSaida);
            ResultSet rs = st.executeQuery();
            List<SaidaInsumosBeans> list = new ArrayList<>();
            TbInsumos.limpar();
            SaidaInsumosBeans s = null;
            while (rs.next()) {
                s = new SaidaInsumosBeans();
                s.setID(rs.getInt("IdSaidaInsumo"));
                s.setIdDoc(rs.getInt("IdDoc"));
                s.setIDOrdem(rs.getInt("idOrdemAplic"));
                s.setDtSaida(Corretores.ConverterParaJava(rs.getString("DataSaida")));
                s.setOperacao(rs.getString("Operacao"));
                s.setIdLocalSaida(rs.getInt("idLocalEstoque"));
                s.setLocalSaida(rs.getString("LocalEstoque"));
                s.setIdSafra(rs.getInt("idSafra"));
                s.setSafra(rs.getString("Safra"));
                s.setIdCultivo(rs.getInt("idCultivo"));
                s.setCultivo(rs.getString("Cultivo"));
                s.setIdCultura(rs.getInt("idCultura"));
                s.setCultura(rs.getString("Cultura"));
                s.setIdAplicao(rs.getInt("idAplicacao"));
                s.setAplicacao(rs.getString("Aplicacao"));
                s.setnDoc(rs.getInt("nDoc"));
                s.setResonsavel(rs.getString("Responsavel"));
                s.setObservacao(rs.getString("Observacao"));
                s.setIdInsumo(rs.getInt("idInsumo"));
                s.setNomeInsumo(rs.getString("NomeInsumo"));
                s.setUnidade(rs.getString("Unidade"));
                s.setCategoria(rs.getString("Categoria"));
                s.setQuant(rs.getDouble("Quantidade"));
                list.add(s);
            }

            SaidaB.setID(s.getID());
            SaidaB.setIdDoc(s.getIdDoc());
            SaidaB.setIDOrdem(s.getIDOrdem());
            SaidaB.setDtSaida(s.getDtSaida());
            SaidaB.setOperacao(s.getOperacao());
            SaidaB.setIdLocalSaida(s.getIdLocalSaida());
            SaidaB.setIdSafra(s.getIdSafra());
            SaidaB.setIdCultivo(s.getIdCultivo());
            SaidaB.setIdCultura(s.getIdCultura());
            SaidaB.setIdAplicao(s.getIdAplicao());
            SaidaB.setnDoc(s.getnDoc());
            SaidaB.setResonsavel(s.getResonsavel());
            SaidaB.setObservacao(s.getObservacao());

            TbInsumos.addLista(list);
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao realizar pesquisa! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    public void incluirInsumo(Integer i, SaidaInsumosBeans beans, TableModelSaidaInsumos TbInsumos) throws SQLException {
        // metodo utlizadp para incluir novos insumos no documento de saída durante a edição;
        if ((Integer) TbInsumos.getValueAt(i, 0) == 0) {
            String SQLInsertInsumo = "insert into saida_insumos (id_documento, id_ordem ,id_insumo, nome_insumo, categoria, quantidade) values (?,?,?,?,?,?)";
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(SQLInsertInsumo);

            stInsumos.setInt(1, beans.getIdDoc());
            stInsumos.setInt(2, (Integer) TbInsumos.getValueAt(i, 1));
            stInsumos.setInt(3, (Integer) TbInsumos.getValueAt(i, 17));
            stInsumos.setString(4, TbInsumos.getValueAt(i, 18).toString());
            stInsumos.setString(5, TbInsumos.getValueAt(i, 20).toString());
            stInsumos.setDouble(6, (Double) TbInsumos.getValueAt(i, 21));

            stInsumos.execute();

        }
    }

    public Boolean excluirSaida(Integer IdDoc) {
        String SQLDeleteDoc = "delete from saida_insumos_doc where id = " + IdDoc;
        String SQLDeleteInsumo = "delete from saida_insumos where id_documento = " + IdDoc;

        try {
            PreparedStatement stDoc = Conexao.getConnection().prepareStatement(SQLDeleteDoc);
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(SQLDeleteInsumo);
            Conexao.getConnection().setAutoCommit(false);
            stDoc.execute();
            stInsumo.execute();

            Conexao.getConnection().commit();
            stInsumo.close();
            stDoc.close();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, "Saída excluída com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao excluir registro! ", "Erro", 0, FormatarICO.ICObtnSair());
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {

            }
            return false;
        }
        return true;
    }

}
