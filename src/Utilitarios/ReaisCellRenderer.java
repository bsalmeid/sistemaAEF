/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.awt.Component;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Bruno
 */
public class ReaisCellRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        Object val = table.getValueAt(row, column);
        if (val instanceof BigDecimal) {
            BigDecimal valor = (BigDecimal) val;
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            setText(valor != null ? nf.format(valor) : "");
            setHorizontalAlignment(SwingConstants.CENTER);
        } else if (val instanceof Double) {
            Double valor = (Double) val;
            NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
            setText(valor != null ? nf.format(valor) : "");
            setHorizontalAlignment(SwingConstants.CENTER);
        }
        return this;
    }

}
