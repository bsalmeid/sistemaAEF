package DAO;

import Beans.CadUsuario;
import Beans.CadUsuarioFazendas;
import Beans.CadUsuarioPermissoes;
import Beans.UsuarioBeans;
import Icones.FormatarICO;
import Utilitarios.Conexao;
import Utilitarios.Corretores;
import Utilitarios.HibernateUtil;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class UsuarioDAO {

    public void CadastrarUsuario(UsuarioBeans Usuario, DefaultTableModel TbFazendas) {
        Conexao.ReiniciarCon();
        int r = 0;
        try {
            Conexao.getConnection();
            String SQLInsertion = "insert into cad_usuario (cad_usuario_nome, cad_usuario_login, cad_usuario_senha, cad_usuario_email, cad_usuario_telefone, cad_usuario_pm_propr, cad_usuario_pm_insumos, cad_usuario_pm_pecas, cad_usuario_pm_invent, cad_usuario_pm_nusuario, cad_usuario_pm_entrada_gado ,cad_usuario_pm_saida_gado ,cad_usuario_pm_compra_gado ,cad_usuario_pm_venda_gado ,cad_usuario_pm_pedido_insumos, cad_usuario_pm_pedido_supri, cad_usuario_pm_entrada_insumos ,cad_usuario_pm_saida_insumos, cad_usuario_pm_centralCompra, cad_usuario_pm_entGraos, cad_usuario_pm_SaidaGraos, cad_usuario_pm_VendaGraos, cad_usuario_pm_plantio, cad_usuario_pm_aplicacoes, cad_usuario_pm_operacoes, cad_usuario_pm_monit, cad_usuario_pm_fornecedores) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
            String sql = "insert into pm_user_fazenda(id_fazenda, nome_fazenda, login_usuario, valor ) values (?,?,?,?)";
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLInsertion);
            PreparedStatement st2 = Conexao.getConnection().prepareStatement(sql);

            st.setString(1, Usuario.getNome());
            st.setString(2, Usuario.getLogin());
            st.setString(3, Usuario.getSenha());
            st.setString(4, Usuario.getEmail());
            st.setString(5, Usuario.getTelefone());

            st.setBoolean(6, Usuario.isCad_Propriedades());
            st.setBoolean(7, Usuario.isCad_Insumos());
            st.setBoolean(8, Usuario.isCad_Pecas());
            st.setBoolean(9, Usuario.isCad_Invent());
            st.setBoolean(10, Usuario.isCad_Usuarios());

            st.setBoolean(11, Usuario.isEntrada_Gado());
            st.setBoolean(12, Usuario.isSaida_Gado());
            st.setBoolean(13, Usuario.isCompra_Gado());
            st.setBoolean(14, Usuario.isVenda_Gado());

            st.setBoolean(15, Usuario.isPedidos_Insumos());
            st.setBoolean(16, Usuario.isGerar_Pedidos());
            st.setBoolean(17, Usuario.isEntrada_Insumos());
            st.setBoolean(18, Usuario.isSaida_Insumos());
            st.setBoolean(19, Usuario.isCentral_Compra());

            st.setBoolean(20, Usuario.isEntrada_Graos());
            st.setBoolean(21, Usuario.isSaida_Graos());
            st.setBoolean(22, Usuario.isVenda_Graos());

            st.setBoolean(23, Usuario.isPlantio());
            st.setBoolean(24, Usuario.isAplicaoes());
            st.setBoolean(25, Usuario.isOperacoes());
            st.setBoolean(26, Usuario.isMonitoramento());

            st.setBoolean(27, Usuario.isCad_Fornecedores());
            st.execute();

            for (r = 0; r < TbFazendas.getRowCount(); r++) {

                st2.setString(1, TbFazendas.getValueAt(r, 0).toString());
                st2.setString(2, TbFazendas.getValueAt(r, 1).toString());
                st2.setString(3, Usuario.getLogin());
                st2.setBoolean(4, Boolean.parseBoolean(TbFazendas.getValueAt(r, 2).toString()));
                st2.execute();
            }

            System.out.println(SQLInsertion);
            JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar registro!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public String proximoUsuario() {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from cad_usuario order by cad_usuario_id desc limit 1";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return (Integer.parseInt(rs.getString("cad_usuario_id")) + 1) + "";
            } else {
                return "1";
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar registro!", "Erro", 0, FormatarICO.ICObtnSair());
            return "0";
        }
    }

    public void buscarFazendas(DefaultTableModel TbFazendas) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select cad_fazendas_id, cad_fazendas_nome from cad_fazendas order by cad_fazendas_id";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                TbFazendas.addRow(new Object[]{rs.getString("cad_fazendas_id"), rs.getString("cad_fazendas_nome"), false});
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar fazendas!", "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void buscarUsuario(UsuarioBeans Usuario, DefaultTableModel TbFazendas) {
        Conexao.ReiniciarCon();
        String SQLSelection = "select * from cad_usuario where cad_usuario_login = ?";
        String sql = "select * from pm_user_fazenda where login_usuario = ?";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLSelection);
            PreparedStatement st2 = Conexao.getConnection().prepareStatement(sql);
            st.setString(1, Usuario.getLogin());
            st2.setString(1, Usuario.getLogin());
            ResultSet rs = st.executeQuery();
            ResultSet rs2 = st2.executeQuery();

            int i = 0;
            while (rs2.next()) {

                for (i = 0; i < TbFazendas.getRowCount(); i++) {

                    if (TbFazendas.getValueAt(i, 0).equals(rs2.getString(1))) {
                        TbFazendas.setValueAt(rs2.getBoolean(4), i, 2);
                    }
                }
            }

            if (rs.next()) {
                Usuario.setCodigo(rs.getInt(1));
                Usuario.setNome(rs.getString(2));
                Usuario.setLogin(rs.getString(3));
                Usuario.setSenha(rs.getString(4));
                Usuario.setEmail(rs.getString(5));
                Usuario.setTelefone(rs.getString(6));

                Usuario.setCad_Propriedades(rs.getBoolean(7));
                Usuario.setCad_Insumos(rs.getBoolean(8));
                Usuario.setCad_Pecas(rs.getBoolean(9));
                Usuario.setCad_Invent(rs.getBoolean(10));
                Usuario.setCad_Usuarios(rs.getBoolean(11));

                Usuario.setEntrada_Gado(rs.getBoolean(12));
                Usuario.setSaida_Gado(rs.getBoolean(13));
                Usuario.setCompra_Gado(rs.getBoolean(14));
                Usuario.setVenda_Gado(rs.getBoolean(15));

                Usuario.setPedidos_Insumos(rs.getBoolean(16));
                Usuario.setGerar_Pedidos(rs.getBoolean(17));
                Usuario.setEntrada_Insumos(rs.getBoolean(18));
                Usuario.setSaida_Insumos(rs.getBoolean(19));
                Usuario.setCentral_Compra(rs.getBoolean(20));

                Usuario.setEntrada_Graos(rs.getBoolean(21));
                Usuario.setSaida_Graos(rs.getBoolean(22));
                Usuario.setVenda_Graos(rs.getBoolean(23));

                Usuario.setPlantio(rs.getBoolean(24));
                Usuario.setAplicaoes(rs.getBoolean(25));
                Usuario.setOperacoes(rs.getBoolean(26));
                Usuario.setMonitoramento(rs.getBoolean(27));

                Usuario.setCad_Fornecedores(rs.getBoolean(28));
            } else {
                JOptionPane.showMessageDialog(null, "Usuario não localizado!", "Erro", 0, FormatarICO.ICObtnSair());
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Erro", 0, FormatarICO.ICObtnSair());
        }

    }

    public void editarUsuario(UsuarioBeans Usuario, DefaultTableModel TbFazendas) {
        Conexao.ReiniciarCon();
        String SQLUpdate = "update cad_usuario SET cad_usuario_nome = ?, cad_usuario_senha = ?, cad_usuario_email = ?, cad_usuario_telefone = ?, cad_usuario_pm_propr = ?, cad_usuario_pm_insumos = ?, cad_usuario_pm_pecas = ?, cad_usuario_pm_invent = ?, cad_usuario_pm_nusuario = ?, cad_usuario_pm_entrada_gado = ?, cad_usuario_pm_saida_gado = ?, cad_usuario_pm_compra_gado = ?, cad_usuario_pm_venda_gado = ?, cad_usuario_pm_pedido_insumos = ?, cad_usuario_pm_pedido_supri = ?, cad_usuario_pm_entrada_insumos = ? ,cad_usuario_pm_saida_insumos = ?, cad_usuario_pm_centralCompra = ?, cad_usuario_pm_entGraos = ?, cad_usuario_pm_SaidaGraos = ?, cad_usuario_pm_VendaGraos = ?, cad_usuario_pm_plantio = ?, cad_usuario_pm_aplicacoes = ?, cad_usuario_pm_operacoes = ?, cad_usuario_pm_monit = ?, cad_usuario_pm_fornecedores = ? WHERE cad_usuario_login = ?";
        String SQLDelete = "delete from pm_user_fazenda WHERE login_usuario = ?";
        String SQLInsertion = "insert into pm_user_fazenda(id_fazenda, nome_fazenda, login_usuario, valor ) values (?,?,?,?)";
        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLUpdate);
            PreparedStatement st2 = Conexao.getConnection().prepareStatement(SQLDelete);
            PreparedStatement st3 = Conexao.getConnection().prepareStatement(SQLInsertion);

            st.setString(1, Usuario.getNome());
            st.setString(2, Usuario.getSenha());
            st.setString(3, Usuario.getEmail());
            st.setString(4, Usuario.getTelefone());

            st.setBoolean(5, Usuario.isCad_Propriedades());
            st.setBoolean(6, Usuario.isCad_Insumos());
            st.setBoolean(7, Usuario.isCad_Pecas());
            st.setBoolean(8, Usuario.isCad_Invent());
            st.setBoolean(9, Usuario.isCad_Usuarios());

            st.setBoolean(10, Usuario.isEntrada_Gado());
            st.setBoolean(11, Usuario.isSaida_Gado());
            st.setBoolean(12, Usuario.isCompra_Gado());
            st.setBoolean(13, Usuario.isVenda_Gado());

            st.setBoolean(14, Usuario.isPedidos_Insumos());
            st.setBoolean(15, Usuario.isGerar_Pedidos());
            st.setBoolean(16, Usuario.isEntrada_Insumos());
            st.setBoolean(17, Usuario.isSaida_Insumos());
            st.setBoolean(18, Usuario.isCentral_Compra());

            st.setBoolean(19, Usuario.isEntrada_Graos());
            st.setBoolean(20, Usuario.isSaida_Graos());
            st.setBoolean(21, Usuario.isVenda_Graos());

            st.setBoolean(22, Usuario.isPlantio());
            st.setBoolean(23, Usuario.isAplicaoes());
            st.setBoolean(24, Usuario.isOperacoes());
            st.setBoolean(25, Usuario.isMonitoramento());

            st.setBoolean(26, Usuario.isCad_Fornecedores());
            st.setString(27, Usuario.getLogin());
            st.executeUpdate();

            st2.setString(1, Usuario.getLogin());
            st2.executeUpdate();

            for (int r = 0; r < TbFazendas.getRowCount(); r++) {

                st3.setString(1, TbFazendas.getValueAt(r, 0).toString());
                st3.setString(2, TbFazendas.getValueAt(r, 1).toString());
                st3.setString(3, Usuario.getLogin());
                st3.setBoolean(4, Boolean.parseBoolean(TbFazendas.getValueAt(r, 2).toString()));
                st3.execute();
            }

            JOptionPane.showMessageDialog(null, "Usuário Editado com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public void excluirUsuario(UsuarioBeans Usuario) {
        Conexao.ReiniciarCon();
        String SQLDeletePM = "delete from pm_user_fazenda WHERE login_usuario = ?";
        String SQLDeleteUser = "delete from cad_usuario WHERE cad_usuario_login = ?";

        try {
            PreparedStatement st = Conexao.getConnection().prepareStatement(SQLDeletePM);
            PreparedStatement st2 = Conexao.getConnection().prepareStatement(SQLDeleteUser);

            st.setString(1, Usuario.getLogin());
            st2.setString(1, Usuario.getLogin());

            st.executeUpdate();
            st2.executeUpdate();

            JOptionPane.showMessageDialog(null, "Usuário excluído com sucesso!", "Executado", 1, FormatarICO.ICObtnOk());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Erro", 0, FormatarICO.ICObtnSair());
        }
    }

    public Boolean SalvarUsuarioNovo(CadUsuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Integer LastID = 0;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            LastID = (Integer) sessao.save(usuario);
            for (int i = 0; i < usuario.getListPermissao().size(); i++) {
                CadUsuarioPermissoes permissao = usuario.getListPermissao().get(i);
                permissao.setIdUsuario(usuario);
                sessao.save(permissao);
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            for (int i = 0; i < usuario.getListFazendas().size(); i++) {
                CadUsuarioFazendas fazendaP = usuario.getListFazendas().get(i);
                fazendaP.setIdUsuario(usuario);
                sessao.save(fazendaP);
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            usuario.setId(LastID);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Usuário Cadastrado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public CadUsuario BuscarUsuario(String login) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        CadUsuario usuario = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CadUsuario c Where c.login = :login ");
            query.setParameter("login", login);
            usuario = (CadUsuario) query.uniqueResult();
            if (usuario != null) {
                Query permissoes = sessao.createQuery("From CadUsuarioPermissoes p Where p.idUsuario = :usuario");
                Query fazendaP = sessao.createQuery("From CadUsuarioFazendas f Where f.idUsuario = :usuario");
                fazendaP.setParameter("usuario", usuario);
                permissoes.setParameter("usuario", usuario);
                usuario.setListPermissao(permissoes.list());
                usuario.setListFazendas(fazendaP.list());
            }
            transacao.commit();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return null;
        } finally {
            sessao.close();
        }
        return usuario;
    }

    public Boolean EditarUsuarioNovo(CadUsuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.update(usuario);
            for (int i = 0; i < usuario.getListPermissao().size(); i++) {
                CadUsuarioPermissoes permissao = usuario.getListPermissao().get(i);
                permissao.setIdUsuario(usuario);
                sessao.saveOrUpdate(permissao);
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            for (int i = 0; i < usuario.getListFazendas().size(); i++) {
                CadUsuarioFazendas fazendaP = usuario.getListFazendas().get(i);
                fazendaP.setIdUsuario(usuario);
                sessao.saveOrUpdate(fazendaP);
                if (i % 20 == 0) { //20, same as the JDBC batch size
                    sessao.flush();
                    sessao.clear();
                }
            }
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Usuário Editado com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Editar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public Boolean ExcluiroNovo(CadUsuario usuario) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            sessao.delete(usuario);
            transacao.commit();
            JOptionPane.showMessageDialog(null, "Usuário Excluído com Sucesso!", "Salvo", 0, FormatarICO.ICObtnOk());
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro ao Excluir Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            return false;
        } finally {
            sessao.close();
        }
        return true;
    }

    public CadUsuario BuscarUsuario(String login, String Senha) {
        Session sessao = HibernateUtil.getSessionFactory().openSession();
        Transaction transacao = null;
        Integer LastID = 0;
        CadUsuario usuario = null;
        try {
            transacao = sessao.beginTransaction();  // abrindo a transação
            Query query = sessao.createQuery("From CadUsuario c Where c.login = :login and c.senha = :senha");
            query.setParameter("login", login);
            query.setParameter("senha", Senha);
            usuario = (CadUsuario) query.uniqueResult();
            if (usuario != null) {
                Query permissoes = sessao.createQuery("From CadUsuarioPermissoes p Where p.idUsuario = :usuario");
                permissoes.setParameter("usuario", usuario);
                usuario.setListPermissao(permissoes.list());
            }
            transacao.commit();
            sessao.close();
        } catch (RuntimeException e) {
            if (transacao != null) {
                transacao.rollback(); // desfaz a transacao
            }
            JOptionPane.showMessageDialog(null, e + " Erro Cadastrar Usuário!", "Erro", 0, FormatarICO.ICObtnSair());
            sessao.close();
            return null;
        }
        return usuario;
    }

}
