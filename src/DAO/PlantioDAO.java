/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Beans.PlantioBeans;
import Icones.FormatarICO;
import TableModel.TableModelPlantio;
import TableModel.TbPlantioBeans;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class PlantioDAO {

    public Boolean inserirPlantio(PlantioBeans b) {
        Conexao.ReiniciarCon();
        String insert = "insert into plantio (id_ordem, data_plantio, id_safra, id_cultivo, id_operacao, id_cultura, id_cultivar, stand_inicial ,id_fazenda, id_talhao, area_talhao, \n"
                + "porcentagem, area_plantada, data_colheita_prevista, id_equipe_plantio, equipe_plantio, observacao) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        if (VerificarAreaPlantadaTalhao(b) == false) {
            JOptionPane.showMessageDialog(null, "A área plantada está maior que a área do Talhão!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(insert);
            st.setInt(1, b.getIdOrdemServico());
            st.setString(2, Corretores.ConverterParaSQL(b.getDataPlantio()));
            st.setInt(3, b.getIdSafra());
            st.setInt(4, b.getIdCultivo());
            st.setInt(5, b.getIdOperacao());
            st.setInt(6, b.getIdCultura());
            st.setInt(7, b.getIdCultivar());
            st.setDouble(8, b.getStandIncial());
            st.setInt(9, b.getIdFazenda());
            st.setInt(10, b.getIdTalhao());
            st.setDouble(11, b.getAreaTalhao());
            st.setDouble(12, b.getPorcentagemPlantada());
            st.setDouble(13, b.getAreaPlantada());
            st.setString(14, Corretores.ConverterParaSQL(b.getDataColheitaPrevista()));
            st.setInt(15, b.getIdEquipePlantio());
            st.setString(16, b.getEquipePlantio());
            st.setString(17, b.getObservacao());
            st.execute();
            
            st.close();
            JOptionPane.showMessageDialog(null, "Plantio registrado com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao inserir plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        return true;
    }

    public void atulizarTabelaPlantio(Integer IdSafra, Integer IdCultivo, Integer IdFazenda, TableModelPlantio TbPlantio, String SQlIdFazendas) {
        Conexao.ReiniciarCon();
        String Select = "SELECT \n"
                + "id as ID, id_ordem as IdOrdem,\n"
                + "data_plantio as DataPlantio, "
                + "(select cs.cad_safra_ano from cad_safra cs where cs.cad_safra_id = id_safra) as Safra,\n"
                + "(select ce.cultivo from cad_epocacultivo ce where ce.id = id_cultivo ) as Cultivo,\n"
                + "(select co.nome from cad_operacoes co where co.id = id_operacao ) as Operacao,\n"
                + "(select cc.cultura from cad_culturas cc where cc.id = id_cultura) as Cultura, \n"
                + "(select cc.cultivar from cad_cultivares cc where cc.id =  id_cultivar) as Cultivar,\n"
                + "(select cf.cad_fazendas_nome from cad_fazendas cf where cf.cad_fazendas_id = id_fazenda )as Fazenda, \n"
                + "(select ct.talhao from cad_talhao ct where ct.id = id_talhao) as Talhao, "
                + "stand_inicial as StandInicial,"
                + "area_talhao as AreaTalhao, porcentagem as Porcentagem,\n"
                + "area_plantada as AreaPlantada, data_colheita_prevista as DataColheita,\n"
                + "equipe_plantio as equipePlantio\n"
                + "FROM plantio pl WHERE pl.id_safra = ? and pl.id_cultivo = ? and pl.id_fazenda IN " + SQlIdFazendas;

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Select);
            st.setInt(1, IdSafra);
            st.setInt(2, IdCultivo);
            ResultSet rs = st.executeQuery();
            TbPlantio.limpar();

            while (rs.next()) {
                TbPlantioBeans p = new TbPlantioBeans();
                p.setID(rs.getInt("ID"));
                p.setIdOrdemServico(rs.getInt("IdOrdem"));
                p.setDataPlantio(Corretores.ConverterParaJava(rs.getString("DataPlantio")));
                p.setSafra(rs.getString("Safra"));
                p.setCultivo(rs.getString("Cultivo"));
                p.setCultura(rs.getString("Cultura"));
                p.setOperacao(rs.getString("Operacao"));
                p.setCultivar(rs.getString("Cultivar"));
                p.setStandInicial(rs.getDouble("StandInicial"));
                p.setFazenda(rs.getString("Fazenda"));
                p.setTalhao(rs.getString("Talhao"));
                p.setAreaTalhao(rs.getDouble("AreaTalhao"));
                p.setPorcentagemPlantada(rs.getDouble("Porcentagem"));
                p.setAreaPlantada(rs.getDouble("AreaPlantada"));
                p.setDataColheitaPrevista(Corretores.ConverterParaJava(rs.getString("DataColheita")));
                p.setEquipePlantio(rs.getString("EquipePlantio"));
                TbPlantio.addBeans(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }

    }

    public Double buscarAreaPlantada(Integer IdSafra, Integer IdCultivo, Integer IdFazenda, Integer IdTalhao) {
        Double areaPlantada = 0.00;
        Conexao.ReiniciarCon();
        String select = "Select Sum(pl.area_plantada) as AreaPlantada from plantio pl where pl.id_safra = ? and pl.id_cultivo = ? and pl.id_fazenda = ? and pl.id_talhao = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, IdSafra);
            st.setInt(2, IdCultivo);
            st.setInt(3, IdFazenda);
            st.setInt(4, IdTalhao);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                areaPlantada = rs.getDouble("AreaPlantada");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return 0.00;
        }
        return areaPlantada;
    }

    private Boolean VerificarAreaPlantadaTalhao(PlantioBeans b) {
        double areaPlantada = buscarAreaPlantada(b.getIdSafra(), b.getIdCultivo(), b.getIdFazenda(), b.getIdTalhao());
        double diferenca = b.getAreaTalhao() - (areaPlantada + b.getAreaPlantada());       
        if (diferenca < -0.5){
            return false;
        }  
        return true;
    }

    public void buscarLancamentoPlantio(PlantioBeans b, Integer IdPlantio) {
        Conexao.ReiniciarCon();
        String select = "select id as ID,\n"
                + "id_ordem as idOrdem,\n"
                + "data_plantio as dataPlantio,\n"
                + "id_safra as idSafra,\n"
                + "id_cultivo as idCultivo,\n"
                + "id_operacao as idOperacao,\n"
                + "id_cultura as idCultura,\n"
                + "id_cultivar as idCultivar,\n"
                + "stand_inicial as StandInicial,\n"
                + "id_fazenda as idFazenda,\n"
                + "id_talhao as idTalhao,\n"
                + "(select ct.talhao from cad_talhao ct where ct.id = pl.id_talhao) as Talhao,\n"
                + "area_talhao as areaTalhao,\n"
                + "porcentagem as porcentagem,\n"
                + "area_plantada as areaPlantada,\n"
                + "data_colheita_prevista as dataColheita,\n"
                + "id_equipe_plantio as idEquipePlantio,\n"
                + "observacao as Observacao\n"
                + " from plantio pl where pl.id = ? ";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, IdPlantio);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                b.setID(rs.getInt("ID"));
                b.setDataPlantio(Corretores.ConverterParaJava(rs.getString("dataPlantio")));
                b.setIdOrdemServico(rs.getInt("idOrdem"));
                b.setIdSafra(rs.getInt("idSafra"));
                b.setIdCultivo(rs.getInt("idCultivo"));
                b.setIdOperacao(rs.getInt("idOperacao"));
                b.setIdCultura(rs.getInt("idCultura"));
                b.setIdCultivar(rs.getInt("idCultivar"));
                b.setStandIncial(rs.getDouble("StandInicial"));
                b.setIdFazenda(rs.getInt("idFazenda"));
                b.setIdTalhao(rs.getInt("idTalhao"));
                b.setNomeTalhao(rs.getString("Talhao"));
                b.setAreaTalhao(rs.getDouble("areaTalhao"));
                b.setPorcentagemPlantada(rs.getDouble("porcentagem"));
                b.setAreaPlantada(rs.getDouble("areaPlantada"));
                b.setDataColheitaPrevista(Corretores.ConverterParaJava(rs.getString("dataPlantio")));
                b.setIdEquipePlantio(rs.getInt("idEquipePlantio"));
                b.setObservacao(rs.getString("Observacao"));
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao buscar plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }

    }

    public Boolean editarPlantio(PlantioBeans b) {
        Conexao.getConnection();
        String update = "update plantio SET id_ordem = ?, data_plantio = ?,\n"
                + "id_safra = ?,\n"
                + "id_cultivo = ?,\n"
                + "id_operacao = ?,\n"
                + "id_cultura = ?,\n"
                + "id_cultivar = ?,\n"
                + "stand_inicial = ?,\n"
                + "id_fazenda = ?,\n"
                + "id_talhao = ?,\n"
                + "area_talhao = ?,\n"
                + "porcentagem = ?,\n"
                + "area_plantada = ?,\n"
                + "data_colheita_prevista = ?,\n"
                + "id_equipe_plantio = ?,\n"
                + "equipe_plantio = ?,\n"
                + "observacao = ?\n"
                + "Where id = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(update);
            st.setInt(1, b.getIdOrdemServico());
            st.setString(2, Corretores.ConverterParaSQL(b.getDataPlantio()));
            st.setInt(3, b.getIdSafra());
            st.setInt(4, b.getIdCultivo());
            st.setInt(5, b.getIdOperacao());
            st.setInt(6, b.getIdCultura());
            st.setInt(7, b.getIdCultivar());
            st.setDouble(8, b.getStandIncial());
            st.setInt(9, b.getIdFazenda());
            st.setInt(10, b.getIdTalhao());
            st.setDouble(11, b.getAreaTalhao());
            st.setDouble(12, b.getPorcentagemPlantada());
            st.setDouble(13, b.getAreaPlantada());
            st.setString(14, Corretores.ConverterParaSQL(b.getDataColheitaPrevista()));
            st.setInt(15, b.getIdEquipePlantio());
            st.setString(16, b.getEquipePlantio());
            st.setString(17, b.getObservacao());
            st.setInt(18, b.getID());
            st.execute();
            st.close();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Plantio editado com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao editar plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }

        return true;
    }

    public Boolean deletarRegistro(Integer IdPlantio){
        Conexao.ReiniciarCon();
        String delete = "delete from plantio where id = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(delete);
            st.setInt(1, IdPlantio);
            st.executeUpdate();
            st.close();
            Conexao.getConnection().commit();
            JOptionPane.showMessageDialog(null, "Plantio excluído com sucesso!", "Registro Salvo com Sucesso", 0, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + " Erro ao excluir plantio!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
            return false;
        }
        return true;
    }
    
}
