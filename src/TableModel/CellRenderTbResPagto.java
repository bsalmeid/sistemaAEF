/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import Utilitarios.*;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bruno
 */
public class CellRenderTbResPagto extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

        Component renderer = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        TableModelResPagto TbModel;
        if (table.getModel() instanceof TableModelResPagto){
            TbModel = (TableModelResPagto) table.getModel();
        }
        
        Object val = table.getValueAt(row, column);
        if (val instanceof Double) {
            Double valor = (Double) val;
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            setText(valor != null ? nf.format(valor) : "");

            ((JLabel) renderer).setOpaque(true);
            Color background;
            Color foreground;

            if (isSelected) {
                return renderer;
            }
            if (!table.getValueAt(row, 11).equals(table.getValueAt(row, column))) {
                Color vermelho = new Color(244, 176, 132);
                setFont(new Font("Tahoma", Font.BOLD, 11));
                foreground = Color.black;
                background = vermelho;
            } else {
                if (row % 2 == 0) {
                    foreground = table.getForeground();
                    background = table.getBackground();
                } else {
                    foreground = table.getForeground();
                    background = table.getBackground();
                }
            }
            renderer.setBackground(background);
            renderer.setForeground(foreground);
        }
        return renderer;
    }

}
