/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JProgressBar;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.basic.BasicProgressBarUI;

public class FrmProgressBar extends javax.swing.JDialog {

//    javax.swing.plaf.metal.MetalLookAndFeel - Metal
//    javax.swing.plaf.nimbus.NimbusLookAndFeel - Nimbus
//    com.sun.java.swing.plaf.motif.MotifLookAndFeel - CDE/Motif
//    com.sun.java.swing.plaf.windows.WindowsLookAndFeel - Windows
//     com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel - Windows Classic

    ThreadLoadProgressBar thread;

    public FrmProgressBar(java.awt.Frame parent, boolean modal, ThreadLoadProgressBar thread) {
        super(parent, modal);
        mudaAparencia("Windows Classic");

        initComponents();
        this.setAlwaysOnTop(true);
        this.setLocationRelativeTo(null);
        this.thread = thread;
        pg_bar.setIndeterminate(true);
        pg_bar.setForeground(Color.GREEN);

        pg_bar.setBorder(BorderFactory.createEtchedBorder());
        pg_bar.setStringPainted(true);
        pg_bar.setString("Buscando");
        pg_bar.repaint();
        setVisible(true);
        mudaAparencia("Nimbus");
        thread.iniciaLoad();
    }

    public void setMinimo(int minimo) {
        pg_bar.setMinimum(minimo);
    }

    public void setMaximo(int maximo) {
        pg_bar.setMaximum(maximo);
    }

    public void setValue(int value) {
        pg_bar.setValue(value);
    }

    public void setString(String texto) {
        pg_bar.setString(texto);
    }

    public void setIndeterminate(Boolean bol) {
        pg_bar.setIndeterminate(bol);
    }

    public void setProgressBar(Boolean bol, int minimo, int maximo, String texto) {
        pg_bar.setIndeterminate(bol);
        pg_bar.setString(texto);
        pg_bar.setMaximum(maximo);
        pg_bar.setMinimum(minimo);
        pg_bar.setValue(minimo);
        for (int i = 0; i < maximo; i++) {
            pg_bar.setValue(i);
        }
    }

    public void setStringPaint(Boolean bol){
        pg_bar.setStringPainted(bol);
    }
    
    public ThreadLoadProgressBar getThread() {
        return thread;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        pg_bar = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        pg_bar.setIndeterminate(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pg_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pg_bar, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {

            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmProgressBar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                FrmProgressBar dialog = new FrmProgressBar(new javax.swing.JFrame(), true, null);

                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel jPanel1;
    public static javax.swing.JProgressBar pg_bar;
    // End of variables declaration//GEN-END:variables

    public void mudaAparencia(String laf) {
        for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
            //System.out.println(info.getClassName() + " - " + info.getName());
            if (laf.equals(info.getName())) {
                try {
                    UIManager.setLookAndFeel(info.getClassName());
                    UIManager.put("JProgressBar.UIClassID", info.getClassName());
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(FrmProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InstantiationException ex) {
                    Logger.getLogger(FrmProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(FrmProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                } catch (UnsupportedLookAndFeelException ex) {
                    Logger.getLogger(FrmProgressBar.class.getName()).log(Level.SEVERE, null, ex);
                }
                //break;
            }
        }
    }

}

class XDMProgressBarUI extends BasicProgressBarUI {

    GradientPaint high, low, back;
    static JProgressBar progressBar;

    public static ComponentUI createUI(JComponent c) {
        return new XDMProgressBarUI(progressBar);
    }

    public XDMProgressBarUI(JProgressBar progressBar) {
        this.progressBar = progressBar;
    }

    @Override
    public void paint(Graphics g, JComponent c) {

        if (!(g instanceof Graphics2D)) {
            return;
        }

        if (high == null) {
            high = new GradientPaint(0, 0, new Color(117, 225, 248), 0, c
                    .getHeight() / 2, new Color(88, 207, 229), false);
        }
        if (low == null) {
            low = new GradientPaint(0, 0, new Color(3, 157, 177), 0, c
                    .getHeight() / 2, new Color(10, 160, 182), false);
        }
        if (back == null) {
            back = new GradientPaint(0, 0, Color.WHITE, 0, c.getHeight() / 2,
                    Color.LIGHT_GRAY, false);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.setPaint(back);
        g2.fillRect(0, 0, c.getWidth(), c.getHeight());
        g2.setColor(Color.GRAY);
        g2.drawRect(0, 0, c.getWidth() - 1, c.getHeight() - 1);

        if (progressBar.isIndeterminate()) {
            paintIndeterminate(g, c);
        } else {
            paintDeterminate(g, c);
        }

    }

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {

        Insets b = progressBar.getInsets(); // area for border

        int barRectWidth = progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }

        Graphics2D g2 = (Graphics2D) g;

        // Paint the bouncing box.
        boxRect = getBox(boxRect);
        if (boxRect != null) {
            g2.setPaint(high);
            g2.fillRect(boxRect.x, boxRect.y, boxRect.width, boxRect.height / 2);

            g2.setPaint(low);
            g2.fillRect(boxRect.x, boxRect.height / 2, boxRect.width, boxRect.height);
        }
    }

    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        Insets b = progressBar.getInsets(); // area for border
        int barRectWidth = progressBar.getWidth() - (b.right + b.left);
        int barRectHeight = progressBar.getHeight() - (b.top + b.bottom);

        if (barRectWidth <= 0 || barRectHeight <= 0) {
            return;
        }

        // amount of progress to draw
        int amountFull = getAmountFull(b, barRectWidth, barRectHeight);

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(progressBar.getForeground());

        if (progressBar.getOrientation() == JProgressBar.HORIZONTAL) {
            g2.setPaint(high);
            g2.fillRect(0, 0, amountFull, c.getHeight() / 2);
            g2.setPaint(low);
            g2.fillRect(0, c.getHeight() / 2, amountFull, c.getHeight());
        } else { // VERTICAL
        }

        // Deal with possible text painting
        if (progressBar.isStringPainted()) {
            paintString(g, b.left, b.top, barRectWidth, barRectHeight,
                    amountFull, b);
        }
    }

}
