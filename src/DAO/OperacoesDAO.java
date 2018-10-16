/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.OperacoesBeans;
import static GUI.frmLogin.UsuarioLogado;
import static GUI.frmLogin.dataAtual;
import Icones.FormatarICO;
import TableModel.TableModelDadosClima;
import TableModel.TableModelResumoOperacoes;
import TableModel.TableModelTalhoesAplic;
import TableModel.TbDadosClimaticosBeans;
import TableModel.TbOperacoesBeans;
import TableModel.TbTalhaoAplicBeans;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author agroa
 */
public class OperacoesDAO {
    
    public Boolean inserirOperacao(OperacoesBeans b, TableModelTalhoesAplic TbTalhao, TableModelDadosClima TbClima) {
        Conexao.ReiniciarCon();
        String Insert = "insert into operacoes_mecanizadas (usuario, data_lan, data_op, id_os, id_safra, id_cultivo, id_cultura, id_operacao,\n"
                + "id_aplicacao, id_trator, id_implemento, id_cei, codigo_operador, id_operador, largura_t, velocidade_t , hm_inicial, hm_final, hm_trabalhada) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        
        String InsertTalhao = "insert into opm_talhoes (id_om, id_os, id_fazenda, id_talhao, porcentagem, area_talhao, area_trabalhada, hm_trabalhada) "
                + "values(?,?,?,?,?,?,?,?)";
        String dadosClima = "insert INTO opm_dados_clima (id_os, id_op, data, hora, vento, temperatura, umidade, chuva) values (?,?,?,?,?,?,?, ?)";
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(Insert, Statement.RETURN_GENERATED_KEYS);
            PreparedStatement stTalhao = Conexao.getConnection().prepareStatement(InsertTalhao);
            PreparedStatement stClima = Conexao.getConnection().prepareStatement(dadosClima);
            st.setString(1, UsuarioLogado);
            st.setString(2, Corretores.ConverterParaSQL(dataAtual));
            st.setString(3, Corretores.ConverterParaSQL(b.getDataOperacao()));
            st.setInt(4, b.getIdOS());
            st.setInt(5, b.getIdSafra());
            st.setInt(6, b.getIdCultivo());
            st.setInt(7, b.getIdCultura());
            st.setInt(8, b.getIdOperacao());
            st.setInt(9, b.getIdAplicacao());
            st.setInt(10, b.getIdTrator());
            st.setInt(11, b.getIdImplemento());
            st.setInt(12, b.getIdCei());
            st.setInt(13, b.getCodigoOperador());
            st.setInt(14, b.getIdOperador());
            st.setDouble(15, b.getLarguraT());
            st.setDouble(16, b.getVelocidadeT());
            st.setDouble(17, b.getHmInicial());
            st.setDouble(18, b.getHmFinal());
            st.setDouble(19, b.getHmTrablhada());
            st.executeUpdate();
            ResultSet rs = st.getGeneratedKeys();
            int lastId = 0;
            while (rs.next()) {
                lastId = rs.getInt(1);
            }
            
            for (int i = 0; i < TbTalhao.getRowCount(); i++) {
                Double areaTrabalhada = (Double) TbTalhao.getValueAt(i, 11);
                Double hmTrabalhada = (Double) TbTalhao.getValueAt(i, 9);
                if (areaTrabalhada > 0 && hmTrabalhada > 0) {
                    stTalhao.setInt(1, lastId); // Id OM
                    stTalhao.setInt(2, b.getIdOS()); // Id OS
                    stTalhao.setInt(3, (Integer) TbTalhao.getValueAt(i, 2)); //  Id Fazenda
                    stTalhao.setInt(4, (Integer) TbTalhao.getValueAt(i, 0)); // IdTalhao
                    stTalhao.setDouble(5, (Double) TbTalhao.getValueAt(i, 5)); // Pocentagem
                    stTalhao.setDouble(6, (Double) TbTalhao.getValueAt(i, 6)); // Area Talhao
                    stTalhao.setDouble(7, areaTrabalhada); // area trabalhada
                    stTalhao.setDouble(8, hmTrabalhada); // hm trabalhada
                    stTalhao.addBatch();
                }
            }
            for (int y = 0; y < TbClima.getRowCount(); y++) {
                stClima.setInt(1, b.getIdOS()); // id OS
                stClima.setInt(2, lastId); // id OP
                stClima.setString(3, Corretores.ConverterParaSQL(TbClima.getValueAt(y, 3).toString())); // Data
                stClima.setTime(4, (Time) TbClima.getValueAt(y, 4));  // Hora
                stClima.setDouble(5, (Double) TbClima.getValueAt(y, 5)); // Vento
                stClima.setDouble(6, (Double) TbClima.getValueAt(y, 6)); // Temperatura
                stClima.setDouble(7, (Double) TbClima.getValueAt(y, 7)); // Umidade
                stClima.setDouble(8, (Double) TbClima.getValueAt(y, 8)); // Chuva
                stClima.addBatch();
            }
            
            stClima.executeBatch();
            stTalhao.executeBatch();
            st.close();
            stTalhao.close();
            stClima.close();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Ordem de Serviço registrada com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(OperacoesDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao inserir operação! ", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        }
        return true;
    }
    
    public Double buscarAreaAplicadaTalhao(Integer OS, Integer IdTalhao, String Modo) {
        Conexao.ReiniciarCon();
        String Selection = "";
        if (Modo.equals("AreaAplicada")) {
            Selection = "select SUM(opm.area_trabalhada) as AreaAplic from opm_talhoes opm where opm.id_os = ? and opm.id_talhao = ?";
        } else if (Modo.equals("PorcentagemAplicada")) {
            Selection = "select SUM(opm.porcentagem) as AreaAplic from opm_talhoes opm where opm.id_os = ? and opm.id_talhao = ?";
        }
        Double AreaAplic = 0.0;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Selection);
            st.setInt(1, OS);
            st.setInt(2, IdTalhao);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AreaAplic = rs.getDouble("AreaAplic");
            }
        } catch (SQLException ex) {
            Logger.getLogger(OperacoesDAO.class.getName()).log(Level.SEVERE, null, ex);
            return AreaAplic;
        }
        
        return AreaAplic;
    }
    
    public void atualizarTalhoes(Integer OS, TableModelTalhoesAplic TbTalhao) {
        Conexao.ReiniciarCon();
        String SQLTalhoesOS = "select ost.id as IdDB,\n" + "ost.id_talhao as IdTalhao,\n" + "ost.nome_talhao as Talhao,\n"
                + "ost.id_fazenda as IdFazenda,\n" + " ost.data_inicio as DataInicio,\n" + "ost.data_termino as DataFinal,\n" + "ost.porcentagem_aplic as PorcentagemAplic,\n"
                + "ost.area_talhao as AreaTalhao,\n" + "ost.area_planejada as AreaPlanejada, \n"
                + "(select SUM(opmt.area_trabalhada) from opm_talhoes opmt, operacoes_mecanizadas op where op.id = opmt.id_om and op.id_os = ost.id_os and opmt.id_talhao = ost.id_talhao) as AreaAplicadaTotal "
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
                double PorcentagemTrabalhada = (rsTalhoes.getDouble("AreaAplicadaTotal") / rsTalhoes.getDouble("AreaTalhao"));
                
                talhao.setPorcentAplic((Double.valueOf(new DecimalFormat("##0.00").format(PorcentagemTrabalhada).replace(",", "."))));
                talhao.setAreaTalhao(rsTalhoes.getDouble("AreaTalhao"));
                talhao.setAreaPlanejada(rsTalhoes.getDouble("AreaPlanejada"));
                talhao.setIdDB(rsTalhoes.getInt("IdDB"));
                talhao.setAreaAplicTotal(rsTalhoes.getDouble("AreaAplicadaTotal"));
                talhao.setAreaAplic(0.0);
                TbTalhao.addBeans(talhao);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar talhões!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }
    
    public Double buscarUltimaHoraTrator(Integer IdInventario) {
        Conexao.ReiniciarCon();
        String Select = "select opm.hm_final as HoraM from operacoes_mecanizadas opm where opm.id_trator = ? Order By opm.id desc Limit 1";
        Double HoraM = 0.0;
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Select);
            st.setInt(1, IdInventario);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                HoraM = rs.getDouble("HoraM");
            }
            rs.close();
            st.close();
            return HoraM;
        } catch (SQLException ex) {
            
        }
        
        return HoraM;
    }
    
    public void ResumoOperações(TableModelResumoOperacoes TbOp, String dtInicial, String dtFinal, String Condicao) {
        Conexao.ReiniciarCon();
        String select = "select \n"
                + "opm.id as ID,\n"
                + "opm.id_os as ID_OS,\n"
                + "opm.data_op as Data,\n"
                + "(select cs.cad_safra_ano from cad_safra cs where cs.cad_safra_id = opm.id_safra ) as Safra,\n"
                + "(select ce.cultivo from cad_epocacultivo ce Where ce.id = opm.id_cultivo) as Cultivo,\n"
                + "(select cc.cultura from cad_culturas cc Where cc.id = opm.id_cultura ) as Cultura,\n"
                + "(select co.nome from cad_operacoes co Where co.id = opm.id_operacao ) as Operacao,\n"
                + "(select ca.nome from cad_aplicacoes ca Where ca.id = opm.id_aplicacao) as Aplicacao,\n"
                + "(select cf.cad_fazendas_nome from cad_fazendas cf where cf.cad_fazendas_id = opt.id_fazenda) as Fazenda,\n"
                + "(select ct.talhao from cad_talhao ct Where ct.id = opt.id_talhao) as Talhao,\n"
                + "(select concat(ci.n_frota , ' - ', cm.descricao) from cad_inventario ci, cad_modelo_equip cm  where ci.id_modelo = cm.id and ci.id = opm.id_trator ) as Trator,\n"
                + "(select concat(ci.n_frota , ' - ', cm.descricao) from cad_inventario ci, cad_modelo_equip cm  where ci.id_modelo = cm.id and ci.id = opm.id_implemento ) as Implemento,\n"
                + "(select cff.nome from cad_funcionarios cff where cff.id = opm.id_operador ) as Operador,\n"
                + "opm.hm_inicial as HMI,\n"
                + "opm.hm_final as HMF,\n"
                + "@HMT:= opm.hm_trabalhada as HMT,\n"
                + "@Area:= (Select SUM(ot.area_trabalhada) from opm_talhoes ot where ot.id_om = opm.id) as Area,\n"
                + "@RendTeo:= IFNULL((largura_t * velocidade_t / 10),0) as RendTeorico,\n"
                + "@RendOP:= FORMAT(IFNULL((@Area/@HMT),0), 2) as RendOP,\n"
                + "Format(IFNULL((@RendOP/@RendTeo),0),2) as Eficiencia\n"
                + "FROM operacoes_mecanizadas opm, opm_talhoes opt Where opm.id = opt.id_om and opm.data_op BETWEEN ? and ? " + Condicao;
        try {
            TbOp.limpar();
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setString(1, Corretores.ConverterParaSQL(dtInicial));
            st.setString(2, Corretores.ConverterParaSQL(dtFinal));
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TbOperacoesBeans b = new TbOperacoesBeans();
                b.setID(rs.getInt("ID"));
                b.setID_OS(rs.getInt("ID_OS"));
                b.setData(Corretores.ConverterParaJava(rs.getString("Data")));
                b.setSafra(rs.getString("Safra"));
                b.setCultivo(rs.getString("Cultivo"));
                b.setCultura(rs.getString("Cultura"));
                b.setOperacao(rs.getString("Operacao"));
                b.setAplicacao(rs.getString("Aplicacao"));
                b.setTrator(rs.getString("Trator"));
                b.setImplemento(rs.getString("Implemento"));
                b.setOperador(rs.getString("Operador"));
                b.setFazenda(rs.getString("Fazenda"));
                b.setTalhao(rs.getString("Talhao"));
                b.setAreaTrabalhada(rs.getDouble("Area"));
                b.setHmInicial(rs.getDouble("HMI"));
                b.setHmFinal(rs.getDouble("HMF"));
                b.setHmTrablhada(rs.getDouble("HMT"));
                b.setRendOpTeorico(rs.getDouble("RendTeorico"));
                b.setRendOp(rs.getDouble("RendOP"));
                b.setEficienciaOp(rs.getDouble("Eficiencia"));
                TbOp.addBeans(b);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar operações! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
        
    }
    
    public OperacoesBeans buscarOperacoes(Integer Id) {
        Conexao.ReiniciarCon();
        String select = "select id as ID,\n"
                + "data_op as Data,\n"
                + "id_os as ID_OS,\n"
                + "id_safra as idSafra,\n"
                + "id_cultivo as idCultivo,\n"
                + "id_cultura as idCultura,\n"
                + "id_operacao as idOperacao,\n"
                + "id_aplicacao as idAplicacao,\n"
                + "id_trator as idTrator,\n"
                + "id_implemento as idImplemento,\n"
                + "id_cei as idCEI,\n"
                + "codigo_operador as CodigoOperador,\n"
                + "id_operador as IdOperador,\n"
                + "largura_t as Largura,\n"
                + "velocidade_t as Velocidade,\n"
                + "hm_inicial as HMI,\n"
                + "hm_final as HMF,\n"
                + "hm_trabalhada as HMT\n"
                + " from operacoes_mecanizadas opm Where opm.id = ?";
        String listTalhao = "select ot.id as ID,\n"
                + "id_om as ID_OM,\n"
                + "id_os as ID_OS,\n"
                + "id_fazenda as IdFazenda,\n"
                + "id_talhao as IdTalhao, "
                + "(select ct.talhao from cad_talhao ct Where ct.id = id_talhao) as Talhao,\n"
                + "porcentagem as Porcentagem,\n"
                + "area_talhao as AreaTalhao,\n"
                + "area_trabalhada as AreaTrabalhada,\n"
                + "hm_trabalhada as HMT\n"
                + " from opm_talhoes ot  Where ot.id_om = ?;";
        String SelectClima = "Select op.id as ID,\n"
                + "op.id_os as ID_OS,\n"
                + "op.data as Data,\n"
                + "op.hora as Hora,\n"
                + "op.vento as Vento,\n"
                + "op.temperatura as Temp,\n"
                + "op.umidade as Umidade,\n"
                + "op.chuva as chuva\n"
                + " from opm_dados_clima op where op.id_op = ?;";
        OperacoesBeans b = new OperacoesBeans();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            PreparedStatement stTalhao = Conexao.getConnection().prepareStatement(listTalhao);
            PreparedStatement stClima = Conexao.getConnection().prepareStatement(SelectClima);
            st.setInt(1, Id);
            stTalhao.setInt(1, Id);
            stClima.setInt(1, Id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                b.setID(rs.getInt("ID"));
                b.setIdOS(rs.getInt("ID_OS"));
                b.setDataOperacao(Corretores.ConverterParaJava(rs.getString("Data")));
                b.setIdSafra(rs.getInt("idSafra"));
                b.setIdCultivo(rs.getInt("idCultivo"));
                b.setIdCultura(rs.getInt("idCultura"));
                b.setIdOperacao(rs.getInt("idOperacao"));
                b.setIdAplicacao(rs.getInt("idAplicacao"));
                b.setIdTrator(rs.getInt("idTrator"));
                b.setIdImplemento(rs.getInt("idImplemento"));
                b.setIdCei(rs.getInt("idCEI"));
                b.setCodigoOperador(rs.getInt("CodigoOperador"));
                b.setIdOperador(rs.getInt("idOperador"));
                b.setLarguraT(rs.getDouble("Largura"));
                b.setVelocidadeT(rs.getDouble("Velocidade"));
                b.setHmInicial(rs.getDouble("HMI"));
                b.setHmFinal(rs.getDouble("HMF"));
                b.setHmTrablhada(rs.getDouble("HMT"));
            }
            
            ResultSet rsTalhao = stTalhao.executeQuery();
            List<TbTalhaoAplicBeans> listTalhoes = new ArrayList<>();
            while (rsTalhao.next()) {
                TbTalhaoAplicBeans t = new TbTalhaoAplicBeans();
                t.setIdDB(rsTalhao.getInt("ID"));
                t.setIdFazenda(rsTalhao.getInt("IdFazenda"));
                t.setIdTalha(rsTalhao.getInt("IdTalhao"));
                t.setnTalhao(rsTalhao.getString("Talhao"));
                t.setPorcentAplic(rsTalhao.getDouble("Porcentagem"));
                t.setAreaTalhao(rsTalhao.getDouble("AreaTalhao"));
                t.setAreaAplic(rsTalhao.getDouble("AreaTrabalhada"));
                t.setHMTrabalhada(rsTalhao.getDouble("HMT"));
                listTalhoes.add(t);
            }
            
            ResultSet rsClima = stClima.executeQuery();
            List<TbDadosClimaticosBeans> listClima = new ArrayList<>();
            while (rsClima.next()) {
                TbDadosClimaticosBeans c = new TbDadosClimaticosBeans();
                c.setID(rsClima.getInt("ID"));
                c.setID_OP(0);
                c.setID_OS(rsClima.getInt("ID_OS"));
                c.setData(Corretores.ConverterParaJava(rsClima.getString("Data")));
                c.setHora(rsClima.getTime("Hora"));
                c.setVento(rsClima.getDouble("Vento"));
                c.setTemperatura(rsClima.getDouble("Temp"));
                c.setUmidade(rsClima.getDouble("Umidade"));
                c.setChuva(rsClima.getDouble("Chuva"));
                listClima.add(c);
            }
            b.setListTalhoesAplic(listTalhoes);
            b.setListDadosClima(listClima);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar operação! ", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return b;
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
