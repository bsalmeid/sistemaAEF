/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.OrdemServicoBeans;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelInsumosAplic;
import TableModel.TableModelOrdemServico;
import TableModel.TableModelTalhoesAplic;
import TableModel.TbInsumosAplicBeans;
import TableModel.TbOrdemServicoBeans;
import TableModel.TbTalhaoAplicBeans;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author agroa
 */
public class OrdemServicoDAO {

    public OrdemServicoDAO() {
        Conexao.ReiniciarCon();
    }

    public Boolean InserirOrdem(OrdemServicoBeans b, TableModelInsumosAplic TbInsumos, TableModelTalhoesAplic TbTalhao) {
        Conexao.ReiniciarCon();
        try {
            Conexao.getConnection().setAutoCommit(false);
            int lastID = inserirOrdem(b);
            inserirInsumo(lastID, TbInsumos, true).executeBatch();
            inserirTalhoes(lastID, TbTalhao, true).executeBatch();
            inserirInsumo(lastID, TbInsumos, false).close();
            inserirTalhoes(lastID, TbTalhao, false).close();

            if (b.getGerarBaixaEstoque()) {
                inserirMovimentoSaida(lastID, b, TbInsumos);
            }
            b.setIdOrdem(lastID);
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Ordem de Serviço registrada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
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

    public Integer inserirOrdem(OrdemServicoBeans b) throws SQLException {
        int lastID = 0;
        String SQLInsert = "insert into ordem_servico (usuario, data_lan, data_ordem, id_safra, id_cultivo, id_cultura, id_operacao, id_aplicacao, estadio_fen,\n"
                + "calda, id_ponta, ponta , responsavel, status, observacao, baixa_estoque, id_localEstoque) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsert, Statement.RETURN_GENERATED_KEYS);
        st.setString(1, UsuarioLogado);
        st.setString(2, Corretores.ConverterParaSQL(dataAtual));
        st.setString(3, Corretores.ConverterParaSQL(b.getDtOrdem()));
        st.setInt(4, b.getIdSafra());
        st.setInt(5, b.getIdCultivo());
        st.setInt(6, b.getIdCultura());
        st.setInt(7, b.getIdOperacao());
        st.setInt(8, b.getIdAplicacao());
        st.setString(9, b.getEstadioFen());
        st.setDouble(10, b.getCalda());
        st.setInt(11, b.getIdPonta());
        st.setString(12, b.getPontaPulverizacao());
        st.setString(13, b.getResponsavel());
        st.setBoolean(14, b.getStatus());
        st.setString(15, b.getObservacao());
        st.setBoolean(16, b.getGerarBaixaEstoque());
        st.setInt(17, b.getIdLocalEstoque());
        st.executeUpdate();
        ResultSet rs = st.getGeneratedKeys();
        while (rs.next()) {
            lastID = rs.getInt(1);
        }
        return lastID;
    }

    public PreparedStatement inserirInsumo(Integer lastID, TableModelInsumosAplic TbInsumos, Boolean Aberto) throws SQLException {
        String SQLInsertInsumo = "insert into os_insumos (id_os, id_insumos, insumo, id_categoria, categoria, dose, q_consumida, q_retirada) values (?,?,?,?,?,?,?,?)";
        PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertInsumo);
        if (Aberto) {
            for (int i = 0; i < TbInsumos.getRowCount(); i++) {
                int IdDB = (Integer) TbInsumos.getValueAt(i, 10);
                if (IdDB == 0) {
                    st.setInt(1, lastID);
                    st.setInt(2, (Integer) TbInsumos.getValueAt(i, 0)); // ID Insumos
                    st.setString(3, TbInsumos.getValueAt(i, 1).toString()); // Nome Insumo
                    st.setInt(4, (Integer) TbInsumos.getValueAt(i, 2)); // ID Categoria
                    st.setString(5, TbInsumos.getValueAt(i, 3).toString()); // Nome Categoria
                    st.setDouble(6, (Double) TbInsumos.getValueAt(i, 7)); // Dose Aplicada
                    st.setDouble(7, (Double) TbInsumos.getValueAt(i, 8)); // Q Consumida
                    st.setDouble(8, (Double) TbInsumos.getValueAt(i, 9)); // Q Retirada
                }
                st.addBatch();
            }
            return st;
        } else {
            return st;
        }
    }

    public PreparedStatement inserirTalhoes(Integer lastID, TableModelTalhoesAplic TbTalhao, Boolean Aberto) throws SQLException {
        String SQLInsertTalhao = "insert into os_talhoes (id_os, id_talhao, nome_talhao, id_fazenda, data_inicio, data_termino, porcentagem_aplic, area_talhao, area_planejada) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stT = Conexao.getConnection().prepareStatement(SQLInsertTalhao);
        if (Aberto) {
            for (int i = 0; i < TbTalhao.getRowCount(); i++) {
                int IdDbTalhao = (Integer) TbTalhao.getValueAt(i, 8);
                if (IdDbTalhao == 0) {
                    stT.setInt(1, lastID);
                    stT.setInt(2, (Integer) TbTalhao.getValueAt(i, 0)); // ID Talhão
                    stT.setString(3, TbTalhao.getValueAt(i, 1).toString()); // Nome Talhão
                    stT.setInt(4, (Integer) TbTalhao.getValueAt(i, 2)); // ID fazenda
                    stT.setString(5, getDate(TbTalhao.getValueAt(i, 3))); // Data de Inicio
                    stT.setString(6, getDate(TbTalhao.getValueAt(i, 4))); // Data de Termino
                    stT.setDouble(7, (Double) TbTalhao.getValueAt(i, 5)); // Porcentagem
                    stT.setDouble(8, (Double) TbTalhao.getValueAt(i, 6)); // Area Talhão
                    stT.setDouble(9, (Double) TbTalhao.getValueAt(i, 7)); // Area Aplicado
                }
                stT.addBatch();
            }
            return stT;
        } else {
            return stT;
        }
    }

    public void relatorioOrdens(TableModelOrdemServico TbOS, String SQLWhere) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select os.id as ID,\n"
                + "os.status as Stat,\n"
                + "os.data_ordem as DataOrdem,\n"
                + "(select cs.cad_safra_ano from cad_safra cs where cs.cad_safra_id = os.id_safra) as Safra,\n"
                + "(select cc.cultivo from cad_epocacultivo cc where cc.id = os.id_cultivo) as Cultivo,\n"
                + "(select cc.cultura from cad_culturas cc where cc.id = os.id_cultura) as Cultura,\n"
                + "(select co.nome from cad_operacoes co where co.id = os.id_operacao) as Operacao,\n"
                + "(select ca.nome from cad_aplicacoes ca where ca.id = os.id_aplicacao) as Aplicacao,\n"
                + "(select cf.cad_fazendas_nome from cad_fazendas cf where cf.cad_fazendas_id = ost.id_fazenda) as Fazenda,\n"
                + "(select ct.talhao from cad_talhao ct where ct.id = ost.id_talhao) as Talhao,\n"
                + "ost.area_talhao as AreaTalhao,\n"
                + "ost.area_planejada as AreaPlanejada,\n"
                + "(select SUM(opmt.area_trabalhada) from opm_talhoes opmt, operacoes_mecanizadas op where op.id = opmt.id_om and op.id_os = ost.id_os and opmt.id_talhao = ost.id_talhao)  as AreaAplicada "
                + " from ordem_servico os, os_talhoes ost where os.id = ost.id_os " + SQLWhere + " order by os.data_ordem";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            TbOS.limpar();
            while (rs.next()) {
                TbOrdemServicoBeans b = new TbOrdemServicoBeans();
                b.setID(rs.getInt("ID"));
                b.setDataOrdem(Corretores.ConverterParaJava(rs.getString("DataOrdem")));
                b.setStatus(rs.getBoolean("Stat"));
                b.setSafra(rs.getString("Safra"));
                b.setCultivo(rs.getString("Cultivo"));
                b.setCultura(rs.getString("Cultura"));
                b.setOperacao(rs.getString("Operacao"));
                b.setAplicacao(rs.getString("Aplicacao"));
                b.setFazenda(rs.getString("Fazenda"));
                b.setTalhao(rs.getString("Talhao"));
                b.setAreaTalhao(rs.getDouble("AreaTalhao"));
                b.setAreaPlanejada(rs.getDouble("AreaPlanejada"));
                b.setAreaAplic(rs.getDouble("AreaAplicada"));
                TbOS.addBeans(b);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar ordens! ", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public void buscarOS(OrdemServicoBeans b, Integer IDOrdem, TableModelInsumosAplic TbInsumos, TableModelTalhoesAplic TbTalhao) {
        Conexao.ReiniciarCon();
        try {
            String SQLSelection = "select os.id as IdOrdem,\n"
                    + "data_ordem as DataOrdem,\n" + "id_safra as IdSafra,\n" + "id_cultivo as IdCultivo, \n" + "id_cultura as IdCultura,\n"
                    + "id_operacao as IdOperacao,\n" + "id_aplicacao as IdAplicacao,\n" + "estadio_fen as Estadio,\n" + "calda as Calda,\n"
                    + "id_ponta as IdPonta,\n" + "responsavel as Responsavel,\n" + "status as Stat,\n" + "observacao as Observacao,\n"
                    + "baixa_estoque as BaixaEstoque, \n"
                    + " id_localEstoque as LocalEstoque "
                    + " from ordem_servico os where os.id = " + IDOrdem;
            String SQLInsumosOS = "select oi.id as IdDB,\n" + "oi.id_insumos as IdInsumo, \n" + "oi.insumo as Insumo,\n"
                    + "oi.id_categoria as IdCategoria,\n" + "oi.categoria as Categoria,\n"
                    + "(select ci.cad_ins_doseControl from cad_insumos ci where ci.cad_ins_id = oi.id_insumos) as DoseControl,\n"
                    + "(select ci.cad_ins_doseControl from cad_insumos ci where ci.cad_ins_id = oi.id_insumos) as DoseMin,\n"
                    + "(select ci.cad_ins_doseControl from cad_insumos ci where ci.cad_ins_id = oi.id_insumos) as DoseMax,\n"
                    + "oi.dose as DoseAplic,\n" + "oi.q_consumida as QConsumo,\n" + "oi.q_retirada as QRetirado\n"
                    + "from os_insumos oi where oi.id_os = " + IDOrdem;
            String SQLTalhoesOS = "select ost.id as IdDB,\n" + "ost.id_talhao as IdTalhao,\n" + "ost.nome_talhao as Talhao,\n"
                    + "ost.id_fazenda as IdFazenda,\n" + " ost.data_inicio as DataInicio,\n" + "ost.data_termino as DataFinal,\n" + "ost.porcentagem_aplic as PorcentagemAplic,\n"
                    + "ost.area_talhao as AreaTalhao,\n" + "ost.area_planejada as AreaPlanejada, \n"
                    + "(select SUM(opmt.area_trabalhada) from opm_talhoes opmt, operacoes_mecanizadas op where op.id = opmt.id_om and op.id_os = ost.id_os and opmt.id_talhao = ost.id_talhao) as AreaAplicada "
                    + " from os_talhoes ost where ost.id_os = " + IDOrdem;
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(SQLInsumosOS);
            PreparedStatement stTalhoes = Conexao.getConnection().prepareStatement(SQLTalhoesOS);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                b.setIdOrdem(rs.getInt("IdOrdem"));
                b.setDtOrdem(rs.getString("DataOrdem"));
                b.setIdSafra(rs.getInt("IdSafra"));
                b.setIdCultivo(rs.getInt("IdCultivo"));
                b.setIdCultura(rs.getInt("IdCultura"));
                b.setIdOperacao(rs.getInt("IdOperacao"));
                b.setIdAplicacao(rs.getInt("IdAplicacao"));
                b.setEstadioFen(rs.getString("Estadio"));
                b.setCalda(rs.getDouble("Calda"));
                b.setIdPonta(rs.getInt("IdPonta"));
                b.setResponsavel(rs.getString("Responsavel"));
                b.setStatus(rs.getBoolean("Stat"));
                System.out.println(rs.getString("Observacao"));
                b.setObservacao(rs.getString("Observacao"));
                b.setGerarBaixaEstoque(rs.getBoolean("BaixaEstoque"));
                b.setIdLocalEstoque(rs.getInt("LocalEstoque"));
            }
            rs.close();
            ResultSet rsInsumos = stInsumos.executeQuery();
            TbInsumos.limpar();
            while (rsInsumos.next()) {
                TbInsumosAplicBeans insumo = new TbInsumosAplicBeans();
                insumo.setIdInsumo(rsInsumos.getInt("IdInsumo"));
                insumo.setInsumo(rsInsumos.getString("Insumo"));
                insumo.setIdCategoria(rsInsumos.getInt("IdCategoria"));
                insumo.setCategoria(rsInsumos.getString("Categoria"));
                insumo.setDoseControl(rsInsumos.getBoolean("DoseControl"));
                insumo.setDoseMin(rsInsumos.getDouble("DoseMin"));
                insumo.setDoseMax(rsInsumos.getDouble("DoseMax"));
                insumo.setDoseAplic(rsInsumos.getDouble("DoseAplic"));
                insumo.setQuantConsumida(rsInsumos.getDouble("QConsumo"));
                insumo.setQuantRetirada(rsInsumos.getDouble("QRetirado"));
                insumo.setIdDB(rsInsumos.getInt("IdDB"));
                TbInsumos.addBeans(insumo);
            }
            rsInsumos.close();
            ResultSet rsTalhoes = stTalhoes.executeQuery();
            TbTalhao.limpar();
            while (rsTalhoes.next()) {
                TbTalhaoAplicBeans talhao = new TbTalhaoAplicBeans();
                talhao.setIdTalha(rsTalhoes.getInt("IdTalhao"));
                talhao.setnTalhao(rsTalhoes.getString("Talhao"));
                talhao.setIdFazenda(rsTalhoes.getInt("IdFazenda"));
                talhao.setDataInicio(setDateJava((rsTalhoes.getString("DataInicio"))));
                talhao.setDataFinal(setDateJava((rsTalhoes.getString("DataFinal"))));
                talhao.setPorcentAplic(rsTalhoes.getDouble("PorcentagemAplic"));
                talhao.setAreaTalhao(rsTalhoes.getDouble("AreaTalhao"));
                talhao.setAreaPlanejada(rsTalhoes.getDouble("AreaPlanejada"));
                talhao.setIdDB(rsTalhoes.getInt("IdDB"));
                talhao.setAreaAplicTotal(rsTalhoes.getDouble("AreaAplicada"));
                talhao.setAreaAplic(0.00);
                TbTalhao.addBeans(talhao);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar ordem! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void atualizarTabelaTalhoes(Integer OS, TableModelTalhoesAplic TbTalhao){
        Conexao.ReiniciarCon();
        String SQLTalhoesOS = "select ost.id as IdDB,\n" + "ost.id_talhao as IdTalhao,\n" + "ost.nome_talhao as Talhao,\n"
                    + "ost.id_fazenda as IdFazenda,\n" + " ost.data_inicio as DataInicio,\n" + "ost.data_termino as DataFinal,\n" + "ost.porcentagem_aplic as PorcentagemAplic,\n"
                    + "ost.area_talhao as AreaTalhao,\n" + "ost.area_planejada as AreaPlanejada, \n"
                    + "(select SUM(opmt.area_trabalhada) from opm_talhoes opmt, operacoes_mecanizadas op where op.id = opmt.id_om and op.id_os = ost.id_os and opmt.id_talhao = ost.id_talhao) as AreaAplicada "
                    + " from os_talhoes ost where ost.id_os = " + OS;

        try {
            PreparedStatement stTalhoes = Conexao.getConnection().prepareStatement(SQLTalhoesOS);
            ResultSet rsTalhoes = stTalhoes.executeQuery();
            TbTalhao.limpar();
            while (rsTalhoes.next()) {
                TbTalhaoAplicBeans talhao = new TbTalhaoAplicBeans();
                talhao.setIdTalha(rsTalhoes.getInt("IdTalhao"));
                talhao.setnTalhao(rsTalhoes.getString("Talhao"));
                talhao.setIdFazenda(rsTalhoes.getInt("IdFazenda"));
                talhao.setDataInicio(setDateJava((rsTalhoes.getString("DataInicio"))));
                talhao.setDataFinal(setDateJava((rsTalhoes.getString("DataFinal"))));
                talhao.setPorcentAplic(rsTalhoes.getDouble("PorcentagemAplic"));
                talhao.setAreaTalhao(rsTalhoes.getDouble("AreaTalhao"));
                talhao.setAreaPlanejada(rsTalhoes.getDouble("AreaPlanejada"));
                talhao.setIdDB(rsTalhoes.getInt("IdDB"));
                talhao.setAreaAplicTotal(rsTalhoes.getDouble("AreaAplicada"));
                talhao.setAreaAplic(0.00);
                TbTalhao.addBeans(talhao);
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, ex + "Erro ao atualizar talhões! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }
    
    public boolean EditarOS(OrdemServicoBeans b, TableModelInsumosAplic TbInsumos, TableModelTalhoesAplic TbTalhao) {
        Conexao.ReiniciarCon();
        String Update = "update ordem_servico SET usuario = ?, \n"
                + "data_lan = ?,\n" + "data_ordem = ?,\n"+ "id_safra = ?,\n"+ "id_cultivo = ?,\n" + "id_cultura = ?,\n"
                + "id_operacao = ?,\n" + "id_aplicacao = ?,\n" + "estadio_fen = ?,\n" + "calda = ?,\n"
                + "id_ponta = ?,\n"+ "ponta = ?,\n" + "responsavel = ?,\n" + "status = ?,\n"
                + "observacao = ?,\n" + "baixa_estoque = ?,\n" + "id_localEstoque = ? "
                + " WHERE id = ?";
        String UpdateInsumos = "update os_insumos SET id_insumos = ?,"
                + "insumo = ?," + "id_categoria = ?," + "categoria = ?," + "dose = ?," + "q_consumida = ?,"
                + "q_retirada = ?" + "WHERE id = ?";
        String UpdateTalhoes = "update os_talhoes SET id_talhao = ?,\n" + "nome_talhao = ?,\n" + "id_fazenda = ?,\n"
                + "data_inicio = ?,\n" + "data_termino = ?,\n" + "porcentagem_aplic = ?,\n" + "area_talhao = ?,\n"
                + "area_planejada = ?\n" + "WHERE id = ?";

        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(Update);
            PreparedStatement stInsumos = Conexao.getConnection().prepareStatement(UpdateInsumos);
            PreparedStatement stTalhoes = Conexao.getConnection().prepareStatement(UpdateTalhoes);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(dataAtual));
            st.setString(3, Corretores.ConverterParaSQL(b.getDtOrdem()));
            st.setInt(4, b.getIdSafra());
            st.setInt(5, b.getIdCultivo());
            st.setInt(6, b.getIdCultura());
            st.setInt(7, b.getIdOperacao());
            st.setInt(8, b.getIdAplicacao());
            st.setString(9, b.getEstadioFen());
            st.setDouble(10, b.getCalda());
            st.setInt(11, b.getIdPonta());
            st.setString(12, b.getPontaPulverizacao());
            st.setString(13, b.getResponsavel());
            st.setBoolean(14, b.getStatus());
            st.setString(15, b.getObservacao());
            st.setBoolean(16, b.getGerarBaixaEstoque());
            st.setInt(17, b.getIdLocalEstoque());
            st.setInt(18, b.getIdOrdem());
            st.executeUpdate();

            if (b.getGerarBaixaEstoque() == false) {
                // EDITAR INSUMOS E INCLUIR NOVOS CASO SE NESCESSÁRIO;
                for (int i = 0; i < TbInsumos.getRowCount(); i++) {
                    int idDB = (Integer) TbInsumos.getValueAt(i, 10);
                    if (idDB > 0) {
                        stInsumos.setInt(1, (Integer) TbInsumos.getValueAt(i, 0)); // id Insumos
                        stInsumos.setString(2, TbInsumos.getValueAt(i, 1).toString()); // insumo
                        stInsumos.setInt(3, (Integer) TbInsumos.getValueAt(i, 2)); // id categoria
                        stInsumos.setString(4, TbInsumos.getValueAt(i, 3).toString()); // categoria
                        stInsumos.setDouble(5, (Double) TbInsumos.getValueAt(i, 7)); // dose
                        stInsumos.setDouble(6, (Double) TbInsumos.getValueAt(i, 8)); // q consumida
                        stInsumos.setDouble(7, (Double) TbInsumos.getValueAt(i, 9)); // q retirada
                        stInsumos.setInt(8, (Integer) TbInsumos.getValueAt(i, 10)); // ID DB
                        stInsumos.addBatch();
                    } else if (idDB == 0) {
                        incluirInsumoAoEditar(b.getIdOrdem(), TbInsumos, i).execute();
                    }
                }
                stInsumos.executeBatch();
                // EDITAR TALHÕES E INCLUIR NOVOS CASO SE NESCESSÁRIO;
                for (int i = 0; i < TbTalhao.getRowCount(); i++) {
                    int idDB = (Integer) TbTalhao.getValueAt(i, 8);
                    if (idDB > 0) {
                        stTalhoes.setInt(1, (Integer) TbTalhao.getValueAt(i, 0)); // Id Talhao;
                        stTalhoes.setString(2, TbTalhao.getValueAt(i, 1).toString()); // Nome talhao;
                        stTalhoes.setInt(3, (Integer) TbTalhao.getValueAt(i, 2)); // id fazenda;
                        stTalhoes.setString(4, getDate(TbTalhao.getValueAt(i, 3))); // data inicio
                        stTalhoes.setString(5, getDate(TbTalhao.getValueAt(i, 4))); // data termino
                        stTalhoes.setDouble(6, (Double) TbTalhao.getValueAt(i, 5)); // porcentagem aplicada
                        stTalhoes.setDouble(7, (Double) TbTalhao.getValueAt(i, 6)); // area talhao;
                        stTalhoes.setDouble(8, (Double) TbTalhao.getValueAt(i, 7)); // area aplicada;
                        stTalhoes.setInt(9, (Integer) TbTalhao.getValueAt(i, 8)); // Id Talhao;
                        stTalhoes.addBatch();
                    } else if (idDB == 0) {
                        incluirTalhaoAoEditar(b.getIdOrdem(), TbTalhao, i).execute();
                    }
                }
                stTalhoes.executeBatch();
            }

            Conexao.getConnection().commit();
            st.close();
            stInsumos.close();
            stTalhoes.close();
            JOptionPane.showMessageDialog(null, "Ordem de Serviço Editada com Sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao editar ordem! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private PreparedStatement incluirInsumoAoEditar(Integer IdOrdem, TableModelInsumosAplic TbInsumos, Integer nLinha) throws SQLException {
        String Insert = "insert into os_insumos (id_os, id_insumos, insumo, id_categoria, categoria, dose, q_consumida, q_retirada) values (?,?,?,?,?,?,?,?)";
        PreparedStatement st = Conexao.getConnection().prepareStatement(Insert);
        int IdDB = (Integer) TbInsumos.getValueAt(nLinha, 10);
        if (IdDB == 0) {
            st.setInt(1, IdOrdem);
            st.setInt(2, (Integer) TbInsumos.getValueAt(nLinha, 0)); // ID Insumos
            st.setString(3, TbInsumos.getValueAt(nLinha, 1).toString()); // Nome Insumo
            st.setInt(4, (Integer) TbInsumos.getValueAt(nLinha, 2)); // ID Categoria
            st.setString(5, TbInsumos.getValueAt(nLinha, 3).toString()); // Nome Categoria
            st.setDouble(6, (Double) TbInsumos.getValueAt(nLinha, 7)); // Dose Aplicada
            st.setDouble(7, (Double) TbInsumos.getValueAt(nLinha, 8)); // Q Consumida
            st.setDouble(8, (Double) TbInsumos.getValueAt(nLinha, 9)); // Q Retirada
        }
        return st;
    }

    private PreparedStatement incluirTalhaoAoEditar(Integer IdOrdem, TableModelTalhoesAplic TbTalhao, Integer nLinha) throws SQLException {
        String Insert = "insert into os_talhoes (id_os, id_talhao, nome_talhao, id_fazenda, data_inicio, data_termino, porcentagem_aplic, area_talhao, area_planejada) values (?,?,?,?,?,?,?,?,?)";
        PreparedStatement stT = Conexao.getConnection().prepareStatement(Insert);
        int IdDB = (Integer) TbTalhao.getValueAt(nLinha, 8);
        if (IdDB == 0) {
            stT.setInt(1, IdOrdem);
            stT.setInt(2, (Integer) TbTalhao.getValueAt(nLinha, 0)); // ID Talhão
            stT.setString(3, TbTalhao.getValueAt(nLinha, 1).toString()); // Nome Talhão
            stT.setInt(4, (Integer) TbTalhao.getValueAt(nLinha, 2)); // ID fazenda
            stT.setString(5, getDate(TbTalhao.getValueAt(nLinha, 3))); // Data de Inicio
            stT.setString(6, getDate(TbTalhao.getValueAt(nLinha, 4))); // Data de Termino
            stT.setDouble(7, (Double) TbTalhao.getValueAt(nLinha, 5)); // Porcentagem
            stT.setDouble(8, (Double) TbTalhao.getValueAt(nLinha, 6)); // Area Talhão
            stT.setDouble(9, (Double) TbTalhao.getValueAt(nLinha, 7)); // Area Aplicado
        }
        return stT;
    }

    public Boolean inserirMovimentoSaida(Integer IdOrdem, OrdemServicoBeans beans, TableModelInsumosAplic TbInsumos) throws SQLException {
        int lastId = 0;
        Conexao.ReiniciarCon();
        String SQLInsertDoc = "insert into saida_insumos_doc (id_ordemAplicacao," + "data_saida, operacao," + "idLocalEstoque,idSafra, "
                + "idCultivo,idCultura," + "idAplicacao,nDoc," + "responsavel,observacao) values (?,?,?,?,?,?,?,?,?,?,?)";

        String SQLInsertInsumo = "insert into saida_insumos (id_documento, id_ordem ,id_insumo, nome_insumo, categoria, quantidade) values (?,?,?,?,?,?)";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertDoc, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(SQLInsertInsumo);
            Conexao.getConnection().setAutoCommit(false);
            st.setInt(1, IdOrdem);
            st.setString(2, Corretores.ConverterParaSQL(beans.getDtOrdem()));
            st.setString(3, "Uso");
            st.setInt(4, beans.getIdLocalEstoque()); // ID Local Saída
            st.setInt(5, beans.getIdSafra());
            st.setInt(6, beans.getIdCultivo());
            st.setInt(7, beans.getIdCultura());
            st.setInt(8, beans.getIdAplicacao());
            st.setInt(9, IdOrdem);
            st.setString(10, beans.getResponsavel());
            st.setString(11, beans.getObservacao());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            while (rs.next()) {
                lastId = rs.getInt(1);
            }

            for (int i = 0; i < TbInsumos.getRowCount(); i++) {
                stInsumo.setInt(1, lastId);
                stInsumo.setInt(2, (Integer) TbInsumos.getValueAt(i, 0)); // Id INsumo
                stInsumo.setInt(3, (Integer) TbInsumos.getValueAt(i, 2)); // id categoria
                stInsumo.setString(4, TbInsumos.getValueAt(i, 1).toString()); // Nome Insumos
                stInsumo.setString(5, TbInsumos.getValueAt(i, 3).toString()); // nome categoria
                stInsumo.setDouble(6, (Double) TbInsumos.getValueAt(i, 9)); // quantidade
                stInsumo.addBatch();
            }
            stInsumo.executeBatch();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Saída registrada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao salvar registro! ", "Erro", 0, FormatarICO.ICObtnSair());
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

    public Boolean excluirOrdemServiço(Integer IdOrdem) {
        Conexao.ReiniciarCon();
        String DeleteOS = "delete from ordem_servico where ordem_servico.id = " + IdOrdem;
        String DeleteInsumo = "delete from os_insumos where os_insumos.id_os = " + IdOrdem;
        String DeleteTalhao = "delete from os_talhoes where os_talhoes.id_os = " + IdOrdem;
        String DeleteSaidaInsumo = "delete si.*,  sd.*  from saida_insumos si, saida_insumos_doc sd WHERE sd.id = si.id_documento and sd.id_ordemAplicacao = " + IdOrdem;

        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(DeleteOS);
            PreparedStatement stInsumo = Conexao.getConnection().prepareStatement(DeleteInsumo);
            PreparedStatement stTalhao = Conexao.getConnection().prepareStatement(DeleteTalhao);
            PreparedStatement stSaidaInsumo = Conexao.getConnection().prepareStatement(DeleteSaidaInsumo);

            st.execute();
            stInsumo.execute();
            stTalhao.execute();
            stSaidaInsumo.execute();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Ordem de Serviço Excluído com Sucesso!", "", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(OrdemServicoDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao excluir registro! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }

    private String getDate(Object Data) {
        String dtFormatoSQL = "";
        if (Data == null) {
            return dtFormatoSQL;
        } else {
            try {
                return Corretores.ConverterParaSQL(Data.toString());
            } catch (Exception e) {
                return "";
            }
        }
    }

    private String setDateJava(Object Data) {
        String dtFormatoSQL = "";
        if (Data == null) {
            return dtFormatoSQL;
        } else {
            try {
                return Corretores.ConverterParaJava(Data.toString());
            } catch (Exception e) {
                return "";
            }
        }
    }

}
