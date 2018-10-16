/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Icones.FormatarICO;
import TableModel.TableModelRastreabilidade;
import TableModel.TbRastreabilidadeBeans;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author agroa
 */
public class RastreabilidadeDAO {

    String insert = "insert ignore into cad_animais_rastreados (data_cadastro, id_status, status, origem_entrada, n_compra, id_fazenda, id_brinco, IDF, IDE, peso, sexo, id_categoria,\n"
            + "biotipo, id_raca, IDF_matriz, IDE_matriz, id_protocolo, id_comprador, comprador, forma, valor_unit, valor_ar) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

    String update = "update cad_animais_rastreados SET data_cadastro = ?, id_status = ?, status = ? , origem_entrada =?,\n" + "n_compra = ?,\n" + "id_fazenda = ?\n," + "id_brinco =?,\n" + "IDF = ?,\n"
            + "IDE = ?,\n" + "peso = ?,\n" + "sexo = ?,\n" + "id_categoria = ?,\n" + "biotipo = ?,\n" + "id_raca = ?,\n" + "IDF_matriz = ?,\n" + "IDE_matriz = ?,\n" + "id_protocolo = ?,\n"
            + "id_comprador = ?,\n" + "comprador = ?,\n" + "forma = ?,\n" + "valor_unit = ?,\n" + "valor_ar = ?\n" + "WHERE id = ?";

    protected String idBrinco;
    protected Integer nBrinco;

    public String getIdBrinco() {
        return idBrinco;
    }

    public void setIdBrinco(String idBrinco) {
        this.idBrinco = idBrinco;
    }

    public Integer getnBrinco() {
        return nBrinco;
    }

    public void setnBrinco(Integer nBrinco) {
        this.nBrinco = nBrinco;
    }

    public List<TbRastreabilidadeBeans> cadastrarAnimal(TableModelRastreabilidade TbGado) {
        Conexao.ReiniciarCon();
        List<Integer> listaID = new ArrayList<>();
        try {
            Conexao.getConnection().setAutoCommit(false);
            PreparedStatement st = Conexao.getConnection().prepareStatement(insert);
            PreparedStatement stUpdate = Conexao.getConnection().prepareStatement(update);
            for (int i = 0; i < TbGado.getRowCount(); i++) {
                if ((Integer) TbGado.getValueAt(i, TbGado.ID) == 0) {
                    stInsert(st, i, TbGado);
                } else if (((Integer) TbGado.getValueAt(i, TbGado.ID) > 0) && (Boolean) TbGado.getValueAt(i, TbGado.EDITADO) == true) {
                    stUpdate(stUpdate, i, TbGado);
                }
            }
            int[] qInsert = st.executeBatch();
            int[] qUpdate = stUpdate.executeBatch();
            st.close();
            stUpdate.close();
            Conexao.getConnection().commit();
            Conexao.getConnection().setAutoCommit(true);
            JOptionPane.showMessageDialog(null, qInsert.length + " Animais Cadastrados com Sucesso! \n " + qUpdate.length + " Registros Editados!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException ex) {
            try {
                Conexao.getConnection().rollback();
                Conexao.getConnection().setAutoCommit(true);
            } catch (SQLException ex1) {
                Logger.getLogger(RastreabilidadeDAO.class.getName()).log(Level.SEVERE, null, ex1);
            }
            JOptionPane.showMessageDialog(null, ex + "Erro ao cadastrar animais!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        }
        return verificarIDF(TbGado);
    }

    public List<TbRastreabilidadeBeans> verificarIDF(TableModelRastreabilidade TbGado) {
        Conexao.ReiniciarCon();
        String IDF = "";
        List<TbRastreabilidadeBeans> lista = new ArrayList<>();
        for (int i = 0; i < TbGado.getRowCount(); i++) {
            IDF += ",'" + TbGado.getValueAt(i, TbGado.IDF).toString() + "'";
        }
        IDF = IDF.replaceFirst(",", "");
        String select = "select IFNULL(car.id ,0) as ID, concat(car.IDF,car.id_brinco) as IDF from cad_animais_rastreados car Where concat(car.IDF,car.id_brinco) IN (" + IDF + ")";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TbRastreabilidadeBeans b = new TbRastreabilidadeBeans();
                b.setID(rs.getInt("ID"));
                b.setIDF(rs.getString("IDF"));
                lista.add(b);
            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao Buscar animais!", "Erro", 0, FormatarICO.ICObtnSair());
        }

        return lista;
    }

    public PreparedStatement stInsert(PreparedStatement st, Integer i, TableModelRastreabilidade TbGado) throws SQLException {
        st.setString(1, Corretores.ConverterParaSQL(TbGado.getValueAt(i, TbGado.DATA).toString()));   // data Cadastro
        st.setInt(2, (Integer) TbGado.getValueAt(i, TbGado.IDSTATUS));        // ID Status
        st.setString(3, TbGado.getValueAt(i, TbGado.STATUS).toString());    // Status
        st.setString(4, TbGado.getValueAt(i, TbGado.MOTIVO).toString());    // Oirgem Entrada
        st.setInt(5, (Integer) TbGado.getValueAt(i, TbGado.COMPRA));       // n compra
        st.setInt(6, (Integer) TbGado.getValueAt(i, TbGado.IDFAZENDA));       // id fazenda
        DesmembrarNBrinco(TbGado.getValueAt(i, TbGado.IDF).toString());
        st.setString(7, this.getIdBrinco());                    // ID no brinco
        st.setInt(8, this.getnBrinco());                        //    IDF
        st.setString(9, TbGado.getValueAt(i, TbGado.IDE).toString());    //    IDE
        st.setDouble(10, (Double) TbGado.getValueAt(i, TbGado.PESO));        // peso
        st.setString(11, TbGado.getValueAt(i, TbGado.SEXO).toString());    // sexo
        st.setInt(12, (Integer) TbGado.getValueAt(i, TbGado.IDCATEGORIA));       //id categoria
        st.setString(13, TbGado.getValueAt(i, TbGado.BIOTIPO).toString());    //biotipo
        st.setInt(14, (Integer) TbGado.getValueAt(i, TbGado.IDRACA));      //id_raca
        st.setString(15, TbGado.getValueAt(i, TbGado.IDFMATRIZ).toString());   // IDF matriz
        st.setString(16, TbGado.getValueAt(i, TbGado.IDEMATRIZ).toString());   //IDE matriz
        st.setInt(17, (Integer) TbGado.getValueAt(i, TbGado.PROTOCOLO));      // id protocolo
        st.setInt(18, (Integer) TbGado.getValueAt(i, TbGado.IDCOMPRADOR));      // id comprador
        st.setString(19, TbGado.getValueAt(i, TbGado.COMPRADOR).toString());   //comprador
        st.setString(20, TbGado.getValueAt(i, TbGado.FORMACOMPRA).toString());   // forma
        st.setDouble(21, (Double) TbGado.getValueAt(i, TbGado.PRECOUNIT));        // valor unitario
        st.setDouble(22, (Double) TbGado.getValueAt(i, TbGado.VALORAR));        // valor arroba
        st.addBatch();
        return st;
    }

    public PreparedStatement stUpdate(PreparedStatement stUpdate, Integer i, TableModelRastreabilidade TbGado) throws SQLException {
        stUpdate.setString(1, Corretores.ConverterParaSQL(TbGado.getValueAt(i, 1).toString()));   // data Cadastro
        stUpdate.setInt(2, (Integer) TbGado.getValueAt(i, TbGado.IDSTATUS));        // ID Status
        stUpdate.setString(3, TbGado.getValueAt(i, TbGado.STATUS).toString());    // Status
        stUpdate.setString(4, TbGado.getValueAt(i, 4).toString());    // Oirgem Entrada
        stUpdate.setInt(5, (Integer) TbGado.getValueAt(i, 5));       // n compra
        stUpdate.setInt(6, (Integer) TbGado.getValueAt(i, 6));       // id fazenda

        DesmembrarNBrinco(TbGado.getValueAt(i, 8).toString());
        stUpdate.setString(7, this.getIdBrinco());                    // ID Brinco
        stUpdate.setInt(8, this.getnBrinco());                        //    IDF
        stUpdate.setString(9, TbGado.getValueAt(i, 9).toString());    //    IDE
        stUpdate.setDouble(10, (Double) TbGado.getValueAt(i, 10));        // peso
        stUpdate.setString(11, TbGado.getValueAt(i, TbGado.SEXO).toString());    // sexo
        stUpdate.setInt(12, (Integer) TbGado.getValueAt(i, TbGado.IDCATEGORIA));       //id categoria
        stUpdate.setString(13, TbGado.getValueAt(i, TbGado.BIOTIPO).toString());    //biotipo
        stUpdate.setInt(14, (Integer) TbGado.getValueAt(i, TbGado.IDRACA));      //id_raca
        stUpdate.setString(15, TbGado.getValueAt(i, TbGado.IDFMATRIZ).toString());   // IDF matriz
        stUpdate.setString(16, TbGado.getValueAt(i, TbGado.IDEMATRIZ).toString());   //IDE matriz
        stUpdate.setInt(17, (Integer) TbGado.getValueAt(i, TbGado.PROTOCOLO));      // id protocolo
        stUpdate.setInt(18, (Integer) TbGado.getValueAt(i, TbGado.IDCOMPRADOR));      // id comprador
        stUpdate.setString(19, TbGado.getValueAt(i, TbGado.COMPRADOR).toString());   //comprador
        stUpdate.setString(20, TbGado.getValueAt(i, TbGado.FORMACOMPRA).toString());   // forma
        stUpdate.setDouble(21, (Double) TbGado.getValueAt(i, TbGado.PRECOUNIT));        // valor unitario
        stUpdate.setDouble(22, (Double) TbGado.getValueAt(i, TbGado.VALORAR));        // valor arroba
        stUpdate.setInt(23, (Integer) TbGado.getValueAt(i, 0));
        stUpdate.addBatch();
        return stUpdate;
    }

    public void consultarBrinco(String SQLWhere, TableModelRastreabilidade TbGado) {
        Conexao.ReiniciarCon();
        String select = "select \n"
                + "car.id as ID,\n"
                + "car.data_cadastro as Data,\n"
                + "car.id_status as idStatus,\n"
                + "car.status as Status,\n"
                + "car.origem_entrada as Motivo,\n"
                + "car.n_compra as nCompra,\n"
                + "car.id_fazenda as idFazenda,\n"
                + "(select cf.cad_fazendas_nome from cad_fazendas cf where cf.cad_fazendas_id = car.id_fazenda ) as Fazenda,\n"
                + "concat(car.IDF, car.id_brinco) as IDF,\n"
                + "car.IDE as IDE,\n"
                + "car.peso as Peso,\n"
                + "car.sexo as Sexo,\n"
                + "car.id_categoria as idCategoria,\n"
                + "(select ca.descricao from cad_categoria_animal ca where ca.id= car.id_categoria ) as Categoria,\n"
                + "car.biotipo as Biotipo,\n"
                + "car.id_raca as idRaca,\n"
                + "(select cr.descricao from cad_raca_animal cr where cr.id = car.id_raca ) as Raca,\n"
                + "car.IDF_matriz as idfMatriz,\n"
                + "car.IDE_matriz as ideMatriz,\n"
                + "car.id_protocolo as Protocolo,\n"
                + "car.id_comprador as idComprador,\n"
                + "car.comprador as Comprador,\n"
                + "car.forma as Forma,\n"
                + "car.valor_unit as ValorUnit,\n"
                + "car.valor_ar as ValorAr\n"
                + "from cad_animais_rastreados car " + SQLWhere;
        List<TbRastreabilidadeBeans> lista = new ArrayList<>();
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(select);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                TbRastreabilidadeBeans b = new TbRastreabilidadeBeans();
                b.setID(rs.getInt(1));
                b.setData(Corretores.ConverterParaJava(rs.getString(2)));
                b.setIdStatus(rs.getInt(3));
                b.setStatusAnimal(rs.getString(4));
                b.setMotivo(rs.getString(5));
                b.setnCompra(rs.getInt(6));
                b.setIdFazenda(rs.getInt(7));
                b.setFazenda(rs.getString(8));
                b.setIDF(rs.getString(9));
                b.setIDE(rs.getString(10));
                b.setPeso(rs.getDouble(11));
                b.setSexo(rs.getString(12));
                b.setIdCategoria(rs.getInt(13));
                b.setCategoria(rs.getString(14));
                b.setBiotipo(rs.getString(15));
                b.setIdRaca(rs.getInt(16));
                b.setRaca(rs.getString(17));
                b.setIDFMatriz(rs.getString(18));
                b.setIDEMatriz(rs.getString(19));
                b.setProtocolo(rs.getInt(20));
                b.setIdComprador(rs.getInt(21));
                b.setComprador(rs.getString(22));
                b.setFormaNeg(rs.getString(23));
                b.setValorUnit(rs.getDouble(24));
                b.setValorAr(rs.getDouble(25));
                b.setCampoEditado(false);
                lista.add(b);
            }
            TbGado.limpar();
            TbGado.addLista(lista);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex + "Erro ao buscar animais!", "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public void DesmembrarNBrinco(String nBrinco) {
        int nChar = nBrinco.length();
        //System.out.println(nBrinco);
        this.setIdBrinco(nBrinco.substring(nChar - 1, nChar).toLowerCase());
        try {
            this.setnBrinco(new Integer(nBrinco.substring(0, nChar - 1)));
        } catch (Exception e) {
            this.setnBrinco(0);
        }
    }

}
