
package GUI;

import Beans.UsuarioBeans;
import Controller.UsuarioController;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;


public class telausuario extends javax.swing.JInternalFrame {

    UsuarioBeans UsuarioB;
    UsuarioController UsuarioC;
    DefaultTableModel TbFazendas;
    
    public telausuario() {
        initComponents();
         
        UsuarioB = new UsuarioBeans();
        UsuarioC = new UsuarioController();
        
        txt_codigo.setText(UsuarioC.controledeCodigo());
        TbFazendas = (DefaultTableModel)tb_fazendas.getModel();
        
        UsuarioC.controleBuscarFazendas(TbFazendas);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        txt_codigo = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txt_nome = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txt_login = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_email = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_telefone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_senha1 = new javax.swing.JPasswordField();
        txt_senha2 = new javax.swing.JPasswordField();
        jSeparator1 = new javax.swing.JSeparator();
        btn_novo = new javax.swing.JButton();
        btn_cadastrar = new javax.swing.JButton();
        btn_editar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        ch_Novos_Insumos = new javax.swing.JCheckBox();
        ch_pecas = new javax.swing.JCheckBox();
        ch_invent = new javax.swing.JCheckBox();
        ch_propriedades = new javax.swing.JCheckBox();
        ch_fornecedores = new javax.swing.JCheckBox();
        ch_usuarios = new javax.swing.JCheckBox();
        jCheckBox11 = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        ch_pedidosInsumos = new javax.swing.JCheckBox();
        ch_entradaInsumos = new javax.swing.JCheckBox();
        ch_pedidosSupri = new javax.swing.JCheckBox();
        ch_saidaInsumos = new javax.swing.JCheckBox();
        ch_centralPedidos = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        ch_compra_gado = new javax.swing.JCheckBox();
        ch_venda_gado = new javax.swing.JCheckBox();
        ch_entrada_gado = new javax.swing.JCheckBox();
        ch_saida_gado = new javax.swing.JCheckBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        tb_fazendas = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        ch_entradaGraos = new javax.swing.JCheckBox();
        ch_saidaGraos = new javax.swing.JCheckBox();
        ch_vendaGraos = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        ch_opPlantio = new javax.swing.JCheckBox();
        ch_opAplicacoes = new javax.swing.JCheckBox();
        ch_opOperacoes = new javax.swing.JCheckBox();
        ch_opMonitoramento = new javax.swing.JCheckBox();
        btn_excluir = new javax.swing.JButton();
        txt_pesquisar = new javax.swing.JButton();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Cadastro de Usuários");
        setToolTipText("");
        setNormalBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setPreferredSize(new java.awt.Dimension(700, 620));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Código");

        txt_codigo.setEditable(false);
        txt_codigo.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Nome");

        txt_nome.setEnabled(false);
        txt_nome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_nomeActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Login");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("E-mail");

        txt_email.setEnabled(false);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Telefone");

        txt_telefone.setEnabled(false);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel6.setText("Senha");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Confirmar Senha");

        txt_senha1.setEnabled(false);
        txt_senha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_senha1ActionPerformed(evt);
            }
        });

        txt_senha2.setEnabled(false);

        btn_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/inserir.png"))); // NOI18N
        btn_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_novoActionPerformed(evt);
            }
        });

        btn_cadastrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/salvar.png"))); // NOI18N
        btn_cadastrar.setEnabled(false);
        btn_cadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cadastrarActionPerformed(evt);
            }
        });

        btn_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/editar.png"))); // NOI18N
        btn_editar.setEnabled(false);
        btn_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_editarActionPerformed(evt);
            }
        });

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Permissões"));

        ch_Novos_Insumos.setText("Cadastro de Novos Insumos");

        ch_pecas.setText("Cadastro Peças");

        ch_invent.setText("Cadastro de Itens Inventário");

        ch_propriedades.setText("Cadastro de Propriedades");

        ch_fornecedores.setText("Cadastro de Fornecedores");

        ch_usuarios.setText("Cadastros de Novos Usuários");

        jCheckBox11.setText("jCheckBox5");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Módulo de Pedidos"));
        jPanel2.setMinimumSize(new java.awt.Dimension(0, 0));
        jPanel2.setPreferredSize(new java.awt.Dimension(340, 100));

        ch_pedidosInsumos.setText("Pedidos de Insumos");

        ch_entradaInsumos.setText("Entrada de Insumos");

        ch_pedidosSupri.setText("Gerar Pedidos de Supr.");

        ch_saidaInsumos.setText("Saida de Insumos");

        ch_centralPedidos.setText("Central de Compras");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ch_pedidosInsumos)
                            .addComponent(ch_pedidosSupri))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ch_saidaInsumos)
                            .addComponent(ch_entradaInsumos)))
                    .addComponent(ch_centralPedidos))
                .addGap(46, 46, 46))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ch_pedidosInsumos)
                    .addComponent(ch_entradaInsumos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ch_pedidosSupri)
                    .addComponent(ch_saidaInsumos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_centralPedidos)
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Permisões Módulo de Gado"));
        jPanel1.setPreferredSize(new java.awt.Dimension(340, 100));

        ch_compra_gado.setText("Compra de gado");
        ch_compra_gado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_compra_gadoActionPerformed(evt);
            }
        });

        ch_venda_gado.setText("Venda de Gado");

        ch_entrada_gado.setText("Entrada de Gado");

        ch_saida_gado.setText("Saída de gado");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_compra_gado)
                    .addComponent(ch_venda_gado))
                .addGap(51, 51, 51)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_saida_gado)
                    .addComponent(ch_entrada_gado))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ch_compra_gado)
                    .addComponent(ch_entrada_gado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_venda_gado)
                    .addComponent(ch_saida_gado))
                .addContainerGap())
        );

        tb_fazendas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fazenda", "Permissão"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Boolean.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_fazendas.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(tb_fazendas);
        if (tb_fazendas.getColumnModel().getColumnCount() > 0) {
            tb_fazendas.getColumnModel().getColumn(0).setResizable(false);
            tb_fazendas.getColumnModel().getColumn(0).setPreferredWidth(30);
            tb_fazendas.getColumnModel().getColumn(2).setResizable(false);
            tb_fazendas.getColumnModel().getColumn(2).setPreferredWidth(60);
        }

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Permisões Módulo de Armazéns"));
        jPanel4.setPreferredSize(new java.awt.Dimension(340, 100));

        ch_entradaGraos.setText("Entrada de Grãos");
        ch_entradaGraos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_entradaGraosActionPerformed(evt);
            }
        });

        ch_saidaGraos.setText("Saída de Grãos");

        ch_vendaGraos.setText("Venda de Grãos");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_entradaGraos)
                    .addComponent(ch_saidaGraos))
                .addGap(51, 51, 51)
                .addComponent(ch_vendaGraos)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ch_entradaGraos)
                    .addComponent(ch_vendaGraos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(ch_saidaGraos)
                .addContainerGap())
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Permisões Módulo de Operações"));
        jPanel5.setPreferredSize(new java.awt.Dimension(340, 100));

        ch_opPlantio.setText("Plantio");
        ch_opPlantio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ch_opPlantioActionPerformed(evt);
            }
        });

        ch_opAplicacoes.setText("Aplicações");

        ch_opOperacoes.setText("Operações Mecanizadas");

        ch_opMonitoramento.setText("Monitoramento");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_opPlantio)
                    .addComponent(ch_opAplicacoes))
                .addGap(51, 51, 51)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ch_opOperacoes)
                    .addComponent(ch_opMonitoramento))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ch_opPlantio)
                    .addComponent(ch_opOperacoes))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ch_opAplicacoes)
                    .addComponent(ch_opMonitoramento))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(ch_Novos_Insumos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ch_pecas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ch_invent, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ch_usuarios, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)
                    .addComponent(ch_propriedades, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(ch_fornecedores, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(jCheckBox11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(ch_Novos_Insumos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_pecas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_invent)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_usuarios)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_propriedades)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ch_fornecedores)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jCheckBox11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 14, Short.MAX_VALUE))))
        );

        jScrollPane1.setViewportView(jPanel3);

        btn_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/deletar.png"))); // NOI18N
        btn_excluir.setEnabled(false);
        btn_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_excluirActionPerformed(evt);
            }
        });

        txt_pesquisar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/buscar.png"))); // NOI18N
        txt_pesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_pesquisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(9, 9, 9)
                                        .addComponent(jLabel1))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(15, 15, 15)
                                        .addComponent(jLabel2))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(14, 14, 14)
                                            .addComponent(jLabel4))
                                        .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(10, 10, 10)
                                                .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addGap(74, 74, 74)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(50, 50, 50)
                                                .addComponent(jLabel6))
                                            .addComponent(jLabel7)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(10, 10, 10)
                                        .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, 276, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(32, 32, 32)
                                        .addComponent(jLabel3)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txt_login)
                                    .addComponent(txt_senha1)
                                    .addComponent(txt_senha2, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_nome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txt_login, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txt_senha1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txt_telefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txt_senha2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 6, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(btn_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txt_pesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_cadastrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        setBounds(0, 0, 656, 620);
    }// </editor-fold>//GEN-END:initComponents

    private void ch_compra_gadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_compra_gadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_compra_gadoActionPerformed

    private void btn_cadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_cadastrarActionPerformed
        int cadastrar = JOptionPane.showConfirmDialog(null, "Deseja Cadastrar este Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (cadastrar == JOptionPane.YES_OPTION){
            popularUsuarioBeans();
            UsuarioC.verificarDados(UsuarioB, TbFazendas);
            limparCampos();
            InabilitarCampos();
            limparBeans();
        }
    }//GEN-LAST:event_btn_cadastrarActionPerformed

    private void txt_nomeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_nomeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_nomeActionPerformed

    private void txt_senha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_senha1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_senha1ActionPerformed

    private void btn_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_novoActionPerformed
        habilitarCampos();
        btn_editar.setEnabled(false);
        btn_excluir.setEnabled(false);
        limparCampos();
        txt_codigo.setText(UsuarioC.controledeCodigo());
        
    }//GEN-LAST:event_btn_novoActionPerformed

    private void ch_entradaGraosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_entradaGraosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_entradaGraosActionPerformed

    private void ch_opPlantioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ch_opPlantioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ch_opPlantioActionPerformed

    private void btn_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_excluirActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja Excluir este Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION){
            popularUsuarioBeans();
            UsuarioC.controleExcluirUsuario(UsuarioB);
            limparCampos();
            InabilitarCampos();
            btn_editar.setEnabled(false);
            limparBeans();
        }   
    }//GEN-LAST:event_btn_excluirActionPerformed

    private void txt_pesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_pesquisarActionPerformed
        UsuarioB.setLogin(txt_login.getText());
        limparCampos();
        UsuarioC.controleBuscarUsuario(UsuarioB, TbFazendas);
        preencherCampos();
        habilitarCampos();
        btn_editar.setEnabled(true);
        btn_excluir.setEnabled(true);
        btn_cadastrar.setEnabled(false);
        limparBeans();
    }//GEN-LAST:event_txt_pesquisarActionPerformed

    private void btn_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_editarActionPerformed
        int editar = JOptionPane.showConfirmDialog(null, "Deseja editar este Usuário?", "Atenção", JOptionPane.YES_NO_OPTION);
        if (editar == JOptionPane.YES_OPTION){
            popularUsuarioBeans();
            UsuarioC.controleEditarUsuario(UsuarioB, TbFazendas);
            limparCampos();
            InabilitarCampos();
            btn_editar.setEnabled(false);
            limparBeans();
        }
    }//GEN-LAST:event_btn_editarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    javax.swing.JButton btn_cadastrar;
    javax.swing.JButton btn_editar;
    javax.swing.JButton btn_excluir;
    javax.swing.JButton btn_novo;
    javax.swing.JCheckBox ch_Novos_Insumos;
    javax.swing.JCheckBox ch_centralPedidos;
    javax.swing.JCheckBox ch_compra_gado;
    javax.swing.JCheckBox ch_entradaGraos;
    javax.swing.JCheckBox ch_entradaInsumos;
    javax.swing.JCheckBox ch_entrada_gado;
    javax.swing.JCheckBox ch_fornecedores;
    javax.swing.JCheckBox ch_invent;
    javax.swing.JCheckBox ch_opAplicacoes;
    javax.swing.JCheckBox ch_opMonitoramento;
    javax.swing.JCheckBox ch_opOperacoes;
    javax.swing.JCheckBox ch_opPlantio;
    javax.swing.JCheckBox ch_pecas;
    javax.swing.JCheckBox ch_pedidosInsumos;
    javax.swing.JCheckBox ch_pedidosSupri;
    javax.swing.JCheckBox ch_propriedades;
    javax.swing.JCheckBox ch_saidaGraos;
    javax.swing.JCheckBox ch_saidaInsumos;
    javax.swing.JCheckBox ch_saida_gado;
    javax.swing.JCheckBox ch_usuarios;
    javax.swing.JCheckBox ch_vendaGraos;
    javax.swing.JCheckBox ch_venda_gado;
    javax.swing.JCheckBox jCheckBox11;
    javax.swing.JDialog jDialog1;
    javax.swing.JLabel jLabel1;
    javax.swing.JLabel jLabel2;
    javax.swing.JLabel jLabel3;
    javax.swing.JLabel jLabel4;
    javax.swing.JLabel jLabel5;
    javax.swing.JLabel jLabel6;
    javax.swing.JLabel jLabel7;
    javax.swing.JPanel jPanel1;
    javax.swing.JPanel jPanel2;
    javax.swing.JPanel jPanel3;
    javax.swing.JPanel jPanel4;
    javax.swing.JPanel jPanel5;
    javax.swing.JScrollPane jScrollPane1;
    javax.swing.JScrollPane jScrollPane2;
    javax.swing.JSeparator jSeparator1;
    javax.swing.JSeparator jSeparator2;
    javax.swing.JTable tb_fazendas;
    javax.swing.JTextField txt_codigo;
    javax.swing.JTextField txt_email;
    javax.swing.JTextField txt_login;
    javax.swing.JTextField txt_nome;
    javax.swing.JButton txt_pesquisar;
    javax.swing.JPasswordField txt_senha1;
    public static javax.swing.JPasswordField txt_senha2;
    javax.swing.JTextField txt_telefone;
    // End of variables declaration//GEN-END:variables

    final void popularUsuarioBeans(){
        UsuarioB.setCodigo(Integer.parseInt(txt_codigo.getText()));
        UsuarioB.setNome(txt_nome.getText());
        UsuarioB.setLogin(txt_login.getText());
        UsuarioB.setSenha(txt_senha1.getText());
        UsuarioB.setEmail(txt_email.getText());
        UsuarioB.setTelefone(txt_telefone.getText());
        
        UsuarioB.setCad_Insumos(ch_Novos_Insumos.isSelected());
        UsuarioB.setCad_Invent(ch_Novos_Insumos.isSelected());
        UsuarioB.setCad_Pecas(ch_pecas.isSelected());
        UsuarioB.setCad_Propriedades(ch_propriedades.isSelected());
        UsuarioB.setCad_Usuarios(ch_usuarios.isSelected());
  
        UsuarioB.setPedidos_Insumos(ch_pedidosInsumos.isSelected());
        UsuarioB.setGerar_Pedidos(ch_pedidosSupri.isSelected());
        UsuarioB.setEntrada_Insumos(ch_entradaInsumos.isSelected());
        UsuarioB.setSaida_Insumos(ch_saidaInsumos.isSelected());
        
        UsuarioB.setCompra_Gado(ch_compra_gado.isSelected());
        UsuarioB.setVenda_Gado(ch_venda_gado.isSelected());
        UsuarioB.setEntrada_Gado(ch_entrada_gado.isSelected());
        UsuarioB.setSaida_Gado(ch_saida_gado.isSelected());
        
        UsuarioB.setCentral_Compra(ch_centralPedidos.isSelected());
        UsuarioB.setEntrada_Graos(ch_entradaGraos.isSelected());
        UsuarioB.setSaida_Graos(ch_saidaGraos.isSelected());
        UsuarioB.setVenda_Graos(ch_vendaGraos.isSelected());
        
        UsuarioB.setPlantio(ch_opPlantio.isSelected());
        UsuarioB.setAplicaoes(ch_opAplicacoes.isSelected());
        UsuarioB.setOperacoes(ch_opOperacoes.isSelected());
        UsuarioB.setMonitoramento(ch_opMonitoramento.isSelected());
        UsuarioB.setCad_Fornecedores(ch_fornecedores.isSelected());    
    }
    
    final void habilitarCampos(){
        txt_nome.setEnabled(true);
        txt_login.setEnabled(true);
        txt_email.setEnabled(true);
        txt_senha1.setEnabled(true);
        txt_senha2.setEnabled(true);
        txt_telefone.setEnabled(true);
        btn_cadastrar.setEnabled(true);
    }
    
    final void InabilitarCampos(){
        txt_nome.setEnabled(false);
        txt_email.setEnabled(false);
        txt_senha1.setEnabled(false);
        txt_senha2.setEnabled(false);
        txt_telefone.setEnabled(false);
    }
    
    final void limparCampos(){
        txt_nome.setText("");
        txt_login.setText("");
        txt_email.setText("");
        txt_senha1.setText("");
        txt_senha2.setText("");
        txt_telefone.setText("");
        
        ch_Novos_Insumos.setSelected(false);
        ch_centralPedidos.setSelected(false);
        ch_compra_gado.setSelected(false);
        ch_entradaGraos.setSelected(false);
        ch_entradaInsumos.setSelected(false);
        ch_entrada_gado.setSelected(false);
        ch_fornecedores.setSelected(false);
        ch_invent.setSelected(false);
        ch_opAplicacoes.setSelected(false);
        ch_opMonitoramento.setSelected(false);
        ch_opOperacoes.setSelected(false);
        ch_opPlantio.setSelected(false);
        ch_pecas.setSelected(false);
        ch_pedidosInsumos.setSelected(false);
        ch_pedidosSupri.setSelected(false);
        ch_propriedades.setSelected(false);
        ch_saidaGraos.setSelected(false);
        ch_saidaInsumos.setSelected(false);
        ch_saida_gado.setSelected(false);
        ch_usuarios.setSelected(false);
        ch_vendaGraos.setSelected(false);
        ch_venda_gado.setSelected(false);
        
        for (int i = 0; i < TbFazendas.getRowCount(); i ++){
            TbFazendas.setValueAt(false, i, 2);
        }
    }
    
    final void preencherCampos(){
        txt_codigo.setText(Integer.toString(UsuarioB.getCodigo()));
        txt_login.setText(UsuarioB.getLogin());
        txt_nome.setText(UsuarioB.getNome());
        txt_senha1.setText(UsuarioB.getSenha());
        txt_senha2.setText(UsuarioB.getSenha());
        txt_email.setText(UsuarioB.getEmail());
        txt_telefone.setText(UsuarioB.getTelefone());
        
        ch_Novos_Insumos.setSelected(UsuarioB.isCad_Insumos());
        ch_centralPedidos.setSelected(UsuarioB.isCentral_Compra());
        ch_compra_gado.setSelected(UsuarioB.isCompra_Gado());
        ch_entradaGraos.setSelected(UsuarioB.isEntrada_Graos());
        ch_entradaInsumos.setSelected(UsuarioB.isEntrada_Insumos());
        ch_entrada_gado.setSelected(UsuarioB.isEntrada_Gado());
        ch_fornecedores.setSelected(UsuarioB.isCad_Fornecedores());
        ch_invent.setSelected(UsuarioB.isCad_Invent());
        ch_opAplicacoes.setSelected(UsuarioB.isAplicaoes());
        ch_opMonitoramento.setSelected(UsuarioB.isMonitoramento());
        ch_opOperacoes.setSelected(UsuarioB.isOperacoes());
        ch_opPlantio.setSelected(UsuarioB.isPlantio());
        ch_pecas.setSelected(UsuarioB.isCad_Pecas());
        ch_pedidosInsumos.setSelected(UsuarioB.isPedidos_Insumos());
        ch_pedidosSupri.setSelected(UsuarioB.isGerar_Pedidos());
        ch_propriedades.setSelected(UsuarioB.isCad_Propriedades());
        ch_saidaGraos.setSelected(UsuarioB.isSaida_Graos());
        ch_saidaInsumos.setSelected(UsuarioB.isSaida_Insumos());
        ch_saida_gado.setSelected(UsuarioB.isSaida_Gado());
        ch_usuarios.setSelected(UsuarioB.isCad_Usuarios());
        ch_vendaGraos.setSelected(UsuarioB.isVenda_Graos());
        ch_venda_gado.setSelected(UsuarioB.isVenda_Gado());
    }
    
    public void limparBeans(){
        UsuarioB.setNome("");
        UsuarioB.setCodigo(0);
        UsuarioB.setSenha("");
        UsuarioB.setTelefone("");
        UsuarioB.setEmail("");
    }
    
}
