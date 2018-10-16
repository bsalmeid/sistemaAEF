/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Almoxarifado.CadAlmoxarifadoBeans;
import Beans.CadUnidadesBeans;
import Beans.CadUsuario;
import Beans.AnoSafra;
import Beans.ListAplicacaoBeans;
import Beans.BancosBeans;
import Beans.CEIBeans;
import Beans.CargosBeans;
import Almoxarifado.CategoriaAlmoxarif;
import Beans.CategoriaAnimalBeans;
import Beans.CategoriaEquipamentosBeans;
import Beans.ListCategoriaInsumos;
import Beans.CompradorGadoBeans;
import Beans.ContaBancariaBeans;
import Beans.ListCultivarBeans;
import Beans.CultivoBeans;
import Beans.CulturaBeans;
import Beans.ListDeficienciaBeans;
import Beans.ListEstadoCivil;
import Beans.EstadosBeans;
import Beans.ListFazendasBeans;
import Beans.ListFrigorificosBeans;
import Beans.ListColaborador;
import Beans.InventarioBeans;
import Almoxarifado.LocalizadorAlmoxarifadoBeans;
import Beans.ModeloEquipamentosBeans;
import Beans.MoedaBeans;
import Beans.ListOperacoesBeans;
import Beans.ListPluviometroBeans;
import Beans.ListPontaPulverizacaoBeans;
import Beans.ListRacaAnimalBeans;
import Beans.ListSetorTrabalhoBeans;
import Beans.ListStatusAnimalBeans;
import Beans.TalhaoBeans;
import Beans.TipoDocPagto;
import Beans.TransportadorasBeans;
import Beans.MarcaEquipamentosBeans;
import Beans.PlanoContaBeans;
import Beans.PropriedadesBeans;
import Beans.StatusItemPedido;
import static GUI.Principal.ListFazPermitidas;
import static GUI.frmLogin.UsuarioLogado;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.HibernateUtil;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author Bruno
 */
public class Diversas {

    public List<Integer> listIDFrigorifico;

    public Diversas() {

    }

    public ArrayList pmfazenda() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from pm_user_fazenda where login_usuario = ?";
        ArrayList FazendaPermitida = new ArrayList();

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, UsuarioLogado);
            ResultSet rs = st.executeQuery();
            FazendaPermitida.add("-");
            while (rs.next()) {
                if (rs.getInt(4) == 1) {
                    FazendaPermitida.add(rs.getString(2));
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar permissões", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return FazendaPermitida;
    }

    public ArrayList buscarFrigorifico() {
        Conexao.ReiniciarCon();

        String SQLSelection = "select cad_cliente_id as id_cli, cad_cliente_nome as Cliente From cad_clientes Where cad_clientes_categoria = 'Frigorifico'";
        ArrayList Frigorificos = new ArrayList();
        listIDFrigorifico = new ArrayList();
        Frigorificos.add("-");
        listIDFrigorifico.add(0);
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Frigorificos.add(rs.getString(2));
                listIDFrigorifico.add(rs.getInt("id_cli"));
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar frigoríficos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }

        return Frigorificos;

    }

    public ArrayList buscarTransportadora() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_transp_id, cad_transp_nome from cad_transportadora Where cad_transp_segmento = 'Gado' And cad_transp_status = 'Ativo'";
        ArrayList transportadora = new ArrayList();
        transportadora.add("-");
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                transportadora.add(rs.getString(2));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar transportadoras", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return transportadora;
    }

    public List<TransportadorasBeans> ListTransportadorasGado() {
        List<TransportadorasBeans> listTransportadoras = new ArrayList<>();
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_transp_id as ID, cad_transp_nome as Nome from cad_transportadora Where cad_transp_segmento = 'Gado' And cad_transp_status = 1";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TransportadorasBeans t = new TransportadorasBeans();
                t.setID(rs.getInt("ID"));
                t.setNome(rs.getString("Nome"));
                listTransportadoras.add(t);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar transportadoras", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return listTransportadoras;
    }

    public List<CompradorGadoBeans> listCompradorGado() {
        Conexao.ReiniciarCon();
        List<CompradorGadoBeans> listCompradorGado = new ArrayList<>();
        String SQLSelection = "select c.comprador_id as ID, c.comprador_nome as Nome  from cad_comprador_gado c where comprador_status = 'Ativo'";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            listCompradorGado.clear();
            while (rs.next()) {
                CompradorGadoBeans l = new CompradorGadoBeans();
                l.setIdComprador(rs.getInt("ID"));
                l.setComprador(rs.getString("Nome"));
                listCompradorGado.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar compradores", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return listCompradorGado;
    }

    public List<ListFazendasBeans> listaDeFazendas() {
        Conexao.ReiniciarCon();
        List<ListFazendasBeans> listFazenda = new ArrayList<>();
        String SQLSelection = "select cad_fazendas_id as ID, cad_fazendas_nome as Nome from cad_fazendas WHERE cad_fazendas_stat = 'Ativo' order by ID";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            listFazenda.clear();
            while (rs.next()) {
                ListFazendasBeans l = new ListFazendasBeans(rs.getInt("ID"), rs.getString("Nome"));
                listFazenda.add(l);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar fazendas", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return listFazenda;
    }

    public List<PlanoContaBeans> ListPlanoContas() {
        List<PlanoContaBeans> listPlanoContas = new ArrayList<>();
        Conexao.ReiniciarCon();
        String SQLSelection = "select pc_id as ID,pc_conta as Conta ,pc_descricao as Descricao, pc_tipo as Tipo from cad_plano_conta";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                PlanoContaBeans pc = new PlanoContaBeans();
                pc.setID(rs.getInt("ID"));
                pc.setConta(rs.getInt("Conta"));
                pc.setDescricao(rs.getString("Descricao"));
                pc.setTipo(rs.getString("Tipo"));
                listPlanoContas.add(pc);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar contas", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return listPlanoContas;
    }

    public List<BancosBeans> ListBancos() {
        List<BancosBeans> listBancos = new ArrayList<>();
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_id as ID, cad_bancoNome as Nome, cad_codCompe as CodCompe FROM cad_bancos ";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                BancosBeans bancos = new BancosBeans();
                bancos.setIdBanco(rs.getInt("ID"));
                bancos.setNomeBanco(rs.getString("Nome"));
                bancos.setCodCompe(rs.getInt("CodCompe"));
                listBancos.add(bancos);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar bancos", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return listBancos;
    }

    public List<ContaBancariaBeans> listContaOrigem() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select c.cad_conta_id as ID, c.cad_conta_descricao as Descricao, c.cad_conta_banco as Banco from cad_contabancaria c Where c.cad_conta_status = 1";
        List<ContaBancariaBeans> listContaOrigem = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                ContaBancariaBeans list = new ContaBancariaBeans();
                list.setIdConta(rs.getInt("ID"));
                list.setDescricao(rs.getString("Descricao"));
                list.setBanco(rs.getString("Banco"));
                listContaOrigem.add(list);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar contas", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return listContaOrigem;
    }

    public List<AnoSafra> listAnoSafra() {
        Conexao.ReiniciarCon();
        String SQlSelection = "select cad_safra_id as ID, cad_safra_ano as Safra, cad_safra_status as Status from cad_safra";
        List<AnoSafra> listAnoSafra = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQlSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                AnoSafra l = new AnoSafra();
                l.setIdSafra(rs.getInt("ID"));
                l.setAnoSafra(rs.getString("Safra"));
                l.setStatus(rs.getBoolean("Status"));
                listAnoSafra.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar Ano Safra", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return listAnoSafra;
    }

    public List<MoedaBeans> listMoeda() {
        Conexao.ReiniciarCon();
        String SQlSelection = "select cad_moeda_id as ID, cad_moeda_nome as Moeda, cad_moeda_sifra as Sifra, cad_moeda_vlrConverteRS as Converssao from cad_moeda";
        List<MoedaBeans> listMoeda = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQlSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                MoedaBeans l = new MoedaBeans();
                l.setIdMoeda(rs.getInt("ID"));
                l.setMoeda(rs.getString("Moeda"));
                l.setSifra(rs.getString("Sifra"));
                l.setConversao(rs.getDouble("Converssao"));
                listMoeda.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Moeda", "Erro", 0, FormatarICO.ICObtnSair());
        }
        return listMoeda;
    }

    public List<ListCategoriaInsumos> listCategoriaInsumos() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_categoria_id as ID, cad_categoria_nome as Categoria from cad_categoriainsumos where cad_categoria_status = 1";
        List<ListCategoriaInsumos> listCategoriaInsumos = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                ListCategoriaInsumos list = new ListCategoriaInsumos();
                list.setID(rs.getInt("ID"));
                list.setNome(rs.getString("Categoria"));
                listCategoriaInsumos.add(list);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar categorias", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return listCategoriaInsumos;

    }

    public List<ListFazendasBeans> ListpmFazenda() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select id_fazenda as ID, nome_fazenda as Nome FROM pm_user_fazenda where login_usuario = ? and valor = 1 order by ID";
        List<ListFazendasBeans> ListFazPermitida = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, UsuarioLogado);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListFazendasBeans l = new ListFazendasBeans(rs.getInt("ID"), rs.getString("Nome"));
                ListFazPermitida.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar permissões", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return ListFazPermitida;
    }

    public List<ListAplicacaoBeans> ListAplicacao() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select id as ID, id_cultura as IdCultura, nome as Nome from cad_aplicacoes where status = 1";
        List<ListAplicacaoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListAplicacaoBeans l = new ListAplicacaoBeans();
                l.setID(rs.getInt("ID"));
                l.setIDCultura(rs.getInt("IdCultura"));
                l.setAplicacao(rs.getString("Nome"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Aplicações", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CulturaBeans> ListCultura() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select id as ID, cultura as Cultura from cad_culturas where status = 1";
        List<CulturaBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CulturaBeans l = new CulturaBeans();
                l.setIDCultura(rs.getInt("ID"));
                l.setNomeCultura(rs.getString("Cultura"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Cultura", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CultivoBeans> ListCultivo() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select id as ID, cultivo as Cultivo from cad_epocacultivo where status = 1";
        List<CultivoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CultivoBeans l = new CultivoBeans();
                l.setIDCultivo(rs.getInt("ID"));
                l.setCultivo(rs.getString("Cultivo"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Cultivos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<InventarioBeans> ListInventario() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select ci.id as ID,\n"
                + " ci.n_frota as nFrota,\n"
                + " (select ce.descricao from cad_categoria_equipamentos ce where ce.id = ci.id_categoria) as Categoria, \n"
                + " (select cm.marca from cad_marca_equipamentos cm where cm.id = ci.id_marca) as Marca,\n"
                + " (select cm.descricao from cad_modelo_equip cm where cm.id = ci.id_modelo) as Modelo,\n"
                + " largura_trabalho as Largura"
                + " from cad_inventario ci where ci.status = 1;";
        List<InventarioBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                InventarioBeans l = new InventarioBeans();
                l.setID(rs.getInt("ID"));
                l.setnFrota(rs.getString("nFrota"));
                l.setCategoria(rs.getString("Categoria"));
                l.setMarca(rs.getString("Marca"));
                l.setModelo(rs.getString("Modelo"));
                l.setLarguraTrabalho(rs.getDouble("Largura"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Inventário", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;

    }

    public List<ListFrigorificosBeans> ListFrigorificos() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_cliente_id as ID, cad_cliente_nome as Nome From cad_clientes Where cad_clientes_categoria = 'Frigorifico'";

        List<ListFrigorificosBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListFrigorificosBeans l = new ListFrigorificosBeans();
                l.setID(rs.getInt("ID"));
                l.setNomeFrigorifico(rs.getString("Nome"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Frigorificos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;

    }

    public List<TalhaoBeans> ListTalhao() {
        Conexao.ReiniciarCon();
        String SQLSelection = "Select ct.id as ID, ct.id_fazenda as idFazenda, ct.talhao as Talhao ,ct.area as Area, ct.id_pluviometro as idPluviometro from cad_talhao ct where ct.status = 1";
        List<TalhaoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TalhaoBeans l = new TalhaoBeans();
                l.setID(rs.getInt("ID"));
                l.setIdFazenda(rs.getInt("idFazenda"));
                l.setTalhao(rs.getString("Talhao"));
                l.setArea(rs.getDouble("Area"));
                l.setIdPluviometro(rs.getInt("idPluviometro"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Talhões", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<ListOperacoesBeans> ListOperacoes() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select id as ID, id_Cultura as idCultura, nome as Nome, uso_aplicacao as UsoAplicacao , uso_insumos as UsoInsumos, uso_f_climaticos as UsoClimatico from cad_operacoes co where co.status = 1;";
        List<ListOperacoesBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListOperacoesBeans l = new ListOperacoesBeans();
                l.setID(rs.getInt("ID"));
                l.setIdCultura(rs.getInt("idCultura"));
                l.setNome(rs.getString("Nome"));
                l.setUsoAplicacoes(rs.getBoolean("UsoAplicacao"));
                l.setUsoFClimaticos(rs.getBoolean("UsoClimatico"));
                l.setUsoInsumos(rs.getBoolean("UsoInsumos"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Operações", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CategoriaEquipamentosBeans> ListCategoriaEquipmentos() {
        Conexao.ReiniciarCon();
        String SQLSelection = "SELECT ce.id as ID, ce.descricao as Categoria, ce.status as Status FROM cad_categoria_equipamentos ce where ce.status = 1";
        List<CategoriaEquipamentosBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoriaEquipamentosBeans l = new CategoriaEquipamentosBeans();
                l.setID(rs.getInt("ID"));
                l.setCategoria(rs.getString("Categoria"));
                l.setStatus(rs.getBoolean("Status"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Categorias de Equipamentos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<ModeloEquipamentosBeans> ListModeloEquipmentos() {
        Conexao.ReiniciarCon();
        String SQLSelection = "SELECT cm.id as IdModelo, cm.id_categoria as IdCategoria,\n"
                + "(select ce.descricao from cad_categoria_equipamentos ce where ce.id = cm.id_categoria) as Categoria,\n"
                + " cm.id_marca as IdMarca,\n"
                + " (select c.marca from cad_marca_equipamentos c where c.id = cm.id_marca) as Marca,\n"
                + " cm.descricao as Modelo, cm.status as Status FROM cad_modelo_equip cm where cm.status = 1";
        List<ModeloEquipamentosBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ModeloEquipamentosBeans l = new ModeloEquipamentosBeans();
                l.setID(rs.getInt("IdModelo"));
                l.setIDCategoria(rs.getInt("IdCategoria"));
                l.setIDMarca(rs.getInt("IdMarca"));
                l.setCategoria(rs.getString("Categoria"));
                l.setMarca(rs.getString("Marca"));
                l.setModelo(rs.getString("Modelo"));
                l.setStatus(rs.getBoolean("Status"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Modelos de Equipamentos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<ListPontaPulverizacaoBeans> ListPontaPulverizacao() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cp.id as ID, cp.ponta as Ponta, cp.fabricante as Fabricante from cad_ponta_pulverizacao cp; ";
        List<ListPontaPulverizacaoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListPontaPulverizacaoBeans l = new ListPontaPulverizacaoBeans();
                l.setID(rs.getInt("ID"));
                l.setPonta(rs.getString("Ponta"));
                l.setFabricante(rs.getString("Fabricante"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Modelos de Equipamentos", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CEIBeans> ListCEI() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cc.id as IdCEI, cc.cei as CEI, cc.descricao as Descricao from cad_cei cc where cc.status = 1";
        List<CEIBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CEIBeans l = new CEIBeans();
                l.setIdCEI(rs.getInt("IdCEI"));
                l.setCEI(rs.getBigDecimal("CEI").toBigInteger());
                l.setDescricao(rs.getString("Descricao"));
                l.setStatus(true);
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de CEI!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public String ListFuncionariosExemploExcel(BigInteger CEIFuncionario, Integer CodigoFunc) {
        String nome = "";
        InputStream ExcelFileToRead = null;
        InputStream canonicalFile = null;
        XSSFWorkbook wb;

        try {
            if (Conexao.Usuario.equals("root")) {
                ExcelFileToRead = new FileInputStream("C:\\Users\\agroa\\OneDrive\\Escritório\\Cadastro de Funcionários.xlsm");
            } else {
                String caminhoCanonical;
                caminhoCanonical = (getClass().getResource("/Arquivos/cadastro_de_funcionarios.xlsm").getFile());
                System.out.println(caminhoCanonical);
                ExcelFileToRead = new FileInputStream(caminhoCanonical);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Diversas.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            wb = new XSSFWorkbook(ExcelFileToRead);
            XSSFSheet planilha = wb.getSheetAt(0);
            XSSFCell cellCEI;
            XSSFCell cellCodigo;

            for (int i = 1; i < planilha.getLastRowNum(); i++) {
                XSSFRow row = planilha.getRow(i);
                try {
                    cellCEI = row.getCell(2);
                    cellCodigo = row.getCell(3);
                    BigInteger CEI = new BigInteger(cellCEI.getRawValue());
                    Integer Codigo = Integer.parseInt(cellCodigo.getRawValue());
                    if (Objects.equals(CEI, CEIFuncionario) && Objects.equals(Codigo, CodigoFunc)) {
                        return row.getCell(5).getStringCellValue();
                    }
                } catch (Exception e) {
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(Diversas.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nome;
    }

    public List<ListColaborador> ListFuncionarios(Integer idFazenda) {
        Conexao.ReiniciarCon();
        String select = "select id as ID,\n"
                + "id_cei as idCEI,\n"
                + "codigo as Codigo,\n"
                + "id_fazenda as idFazenda,\n"
                + "id_setor as idSetor,\n"
                + "id_funcao as idFuncao,\n"
                + "nome as Nome\n"
                + " from cad_funcionarios where id_fazenda = ? and Status = 'Ativo'";
        List<ListColaborador> lista = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            st.setInt(1, idFazenda);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListColaborador f = new ListColaborador();
                f.setID(rs.getInt("ID"));
                f.setIdCEI(rs.getInt("idCEI"));
                f.setIdFazenda(rs.getInt("idFazenda"));
                f.setIdFuncao(rs.getInt("idFuncao"));
                f.setIdSetor(rs.getInt("idSetor"));
                f.setCodigo(rs.getInt("Codigo"));
                f.setNome(rs.getString("Nome"));
                lista.add(f);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Colaboradores", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return lista;
    }

    public List<ListCultivarBeans> LisCultivares() {
        Conexao.ReiniciarCon();
        String Selection = "select cc.id as idCultivar, " + "cc.id_cultura as idCultura, " + "cultivar as Cultivar, "
                + "evento as Evento, " + "transgenico as Transgenico, " + "ciclo_dias as Ciclo, " + "grupo_maturacao as Maturacao "
                + " from cad_cultivares cc where cc.status = 1";
        List<ListCultivarBeans> list = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Selection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListCultivarBeans b = new ListCultivarBeans();
                b.setIdCultivar(rs.getInt("idCultivar"));
                b.setIdCultura(rs.getInt("idCultura"));
                b.setCultivar(rs.getString("Cultivar"));
                b.setEvento(rs.getString("Evento"));
                b.setTransgenico(rs.getBoolean("Transgenico"));
                b.setCiclo(rs.getInt("Ciclo"));
                b.setGrupoMaturacao(rs.getDouble("Maturacao"));
                list.add(b);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Cultivares", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return list;
    }

    public List<TipoDocPagto> ListTipoDocPagto() {
        Conexao.ReiniciarCon();
        String Selection = "select doc.id as ID, doc.Tipo as Nome, doc.uso_fiscal as UsoFiscal from cad_doc_financeiro doc where doc.status = 1 ";
        List<TipoDocPagto> list = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Selection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TipoDocPagto b = new TipoDocPagto();
                b.setIdDoc(rs.getInt("ID"));
                b.setNome(rs.getString("Nome"));
                b.setUsoFiscalLivroCaixa(rs.getBoolean("UsoFiscal"));
                list.add(b);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Cultivares", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return list;
    }

    public List<ListPluviometroBeans> ListPluviometro() {
        Conexao.ReiniciarCon();
        String Selection = "select cp.id as ID, cp.id_fazenda as IdFazenda, cp.descricao as Descricao from cad_pluviometro cp where cp.id_fazenda IN " + getIdFazendaPermitida();
        List<ListPluviometroBeans> list = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Selection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListPluviometroBeans b = new ListPluviometroBeans();
                b.setID(rs.getInt("ID"));
                b.setIdFazenda(rs.getInt("IdFazenda"));
                b.setDescricao(rs.getString("Descricao"));
                list.add(b);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Pluviometros", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return list;
    }

    public List<EstadosBeans> ListEstados() {
        Conexao.ReiniciarCon();
        String Selection = "select id as IdEstado, estado as Estado, abreviacao as Abreviacao from cad_estados";
        List<EstadosBeans> list = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(Selection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                EstadosBeans b = new EstadosBeans();
                b.setIdEtado(rs.getInt("IdEstado"));
                b.setEstado(rs.getString("Estado"));
                b.setAbreviacao(rs.getString("Abreviacao"));
                list.add(b);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Lista de Estados", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return list;
    }

    public List<CargosBeans> ListCargosFuncoes() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select id as Id, descricao as Cargo, salario as Salario from cad_cargos_salario where status = 1;";
        List<CargosBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CargosBeans l = new CargosBeans();
                l.setId(rs.getInt("Id"));
                l.setDescricao(rs.getString("Cargo"));
                l.setSalario(rs.getDouble("Salario"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Funções!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<ListEstadoCivil> ListEstadoCivil() {
        List<ListEstadoCivil> list = new ArrayList<>();
        list.add(new ListEstadoCivil(0, "-", false));
        list.add(new ListEstadoCivil(1, "Solteiro(a)", false));
        list.add(new ListEstadoCivil(2, "Divorciado(a)", false));
        list.add(new ListEstadoCivil(3, "Casado(a)", true));
        list.add(new ListEstadoCivil(4, "Amasiado(a)", true));
        list.add(new ListEstadoCivil(5, "Viúvo(a)", false));
        return list;
    }

    public List<ListDeficienciaBeans> ListDeficiencias() {
        List<ListDeficienciaBeans> list = new ArrayList<>();
        list.add(new ListDeficienciaBeans(0, "-"));
        list.add(new ListDeficienciaBeans(1, "Auditiva"));
        list.add(new ListDeficienciaBeans(2, "Visual"));
        list.add(new ListDeficienciaBeans(3, "Física"));
        list.add(new ListDeficienciaBeans(4, "Mental"));
        list.add(new ListDeficienciaBeans(5, "Múltipla"));
        list.add(new ListDeficienciaBeans(5, "Reabilitado"));
        return list;
    }

    public List<ListSetorTrabalhoBeans> ListSetoresTrabalho() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cs.id as ID, cs.descricao as Descricao from cad_setor_servico cs where cs.status = 1";
        List<ListSetorTrabalhoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListSetorTrabalhoBeans l = new ListSetorTrabalhoBeans();
                l.setID(rs.getInt("ID"));
                l.setDescricao(rs.getString("Descricao"));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Setores!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CategoriaAnimalBeans> ListCategoriaAnimal() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from cad_categoria_animal Where status = 1";
        List<CategoriaAnimalBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                CategoriaAnimalBeans l = new CategoriaAnimalBeans();
                l.setID(rs.getInt(1));
                l.setDescricao(rs.getString(2));
                l.setSexo(rs.getString(3));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Categoria de Animais!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<ListRacaAnimalBeans> ListRacaAnimal() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from cad_raca_animal Where status = 1";
        List<ListRacaAnimalBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                ListRacaAnimalBeans l = new ListRacaAnimalBeans();
                l.setID(rs.getInt(1));
                l.setDescricao(rs.getString(2));
                l.setAbreviacao(rs.getString(3));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Raça de Animais!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<ListStatusAnimalBeans> ListStatusAnimal() {

        List<ListStatusAnimalBeans> List = new ArrayList<>();
        List.add(new ListStatusAnimalBeans(0, "-"));
        List.add(new ListStatusAnimalBeans(1, "Ativo"));
        List.add(new ListStatusAnimalBeans(2, "Abatido"));
        List.add(new ListStatusAnimalBeans(3, "Vendido"));
        List.add(new ListStatusAnimalBeans(4, "Morto"));
        return List;
    }

    public List<MarcaEquipamentosBeans> ListMarcaEquipamentos() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cm.id as ID, cm.marca as Marca, cm.status as Status from cad_marca_equipamentos cm where cm.status = 1";
        List<MarcaEquipamentosBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            MarcaEquipamentosBeans l = new MarcaEquipamentosBeans();
            l.setID(0);
            l.setMarca("-");
            List.add(l);
            while (rs.next()) {
                l = new MarcaEquipamentosBeans();
                l.setID(rs.getInt(1));
                l.setMarca(rs.getString(2));
                l.setStatus(rs.getBoolean(3));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar Raça de Animais!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CadAlmoxarifadoBeans> ListAlmoxarifado(String Usuario) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from cad_almoxarifado ca where ca.id_fazenda IN (select pm.id_fazenda from pm_user_fazenda pm Where pm.login_usuario = ? and pm.valor = 1)";
        List<CadAlmoxarifadoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            st.setString(1, Usuario);
            ResultSet rs = st.executeQuery();
            CadAlmoxarifadoBeans l = new CadAlmoxarifadoBeans();
            l.setID(0);
            l.setDescricao("-");
            List.add(l);
            while (rs.next()) {
                l = new CadAlmoxarifadoBeans();
                l.setID(rs.getInt(1));
                l.setID_Fazenda(rs.getInt(2));
                l.setDescricao(rs.getString(3));
                l.setStatus(rs.getBoolean(4));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Buscar Almoxarifados!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<CadUnidadesBeans> ListUnidades() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from cad_unidades where status = 1";
        List<CadUnidadesBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            CadUnidadesBeans l = new CadUnidadesBeans();
            l.setID(0);
            l.setDescricao("-");
            List.add(l);
            while (rs.next()) {
                l = new CadUnidadesBeans();
                l.setID(rs.getInt(1));
                l.setID_Categoria_Unid(rs.getInt(2));
                l.setDescricao(rs.getString(3));
                l.setConversaoKg_L(rs.getDouble(4));
                l.setStatus(rs.getBoolean(5));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao carregar Unidades!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<LocalizadorAlmoxarifadoBeans> ListLocalizadorAlmox() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cl.id as ID, cl.id_almoxarifado as Id_Almoxarifado, cl.descricao as Descricao from cad_almoxarifado_localizador cl where cl.status = 1";
        List<LocalizadorAlmoxarifadoBeans> List = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            LocalizadorAlmoxarifadoBeans l = new LocalizadorAlmoxarifadoBeans();
            l.setID(0);
            l.setDescricao("-");
            List.add(l);
            while (rs.next()) {
                l = new LocalizadorAlmoxarifadoBeans();
                l.setID(rs.getInt(1));
                l.setId_Almoxarifado(rs.getInt(2));
                l.setDescricao(rs.getString(3));
                List.add(l);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao carregar Localizadores!", "Erro", 0, new ImageIcon("imagens/btn_sair.png"));
        }
        return List;
    }

    public List<StatusItemPedido> ListarStatusItemPedido() {
        List<StatusItemPedido> list = new ArrayList<>();
        list.add(new StatusItemPedido(0, "-", true));
        list.add(new StatusItemPedido(1, "Em Solicitacao", true));
        list.add(new StatusItemPedido(2, "Em Análise", true));
        list.add(new StatusItemPedido(3, "Comprado", true));
        list.add(new StatusItemPedido(4, "Recebido", true));
        list.add(new StatusItemPedido(5, "Enviado", true));
        list.add(new StatusItemPedido(6, "Cancelado", true));

        return list;
    }

    public String getIdFazendaPermitida() {
        String s = "";
        if (ListFazPermitidas == null) {
            ListFazPermitidas = ListpmFazenda();
        }
        for (int i = 0; i < ListFazPermitidas.size(); i++) {
            s += ", " + ListFazPermitidas.get(i).getID();
        }
        s = "(" + s.replaceFirst(",", "") + ")";
        return s;
    }

}
