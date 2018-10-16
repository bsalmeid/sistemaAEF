/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilitarios;

import java.awt.Color;
import java.awt.Component;
import java.text.NumberFormat;
import java.util.Locale;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author Bruno
 */
public class MoedaCellRenderer extends JLabel implements TableCellRenderer {
    private Color whiteColor = new Color(254, 254, 254);  
    private Color alternateColor = new Color(237, 243, 253);  
    private Color selectedColor = new Color(61, 128, 223);  
    /** 
     * classe para mostrar a celula com formato de moeda 
     */  
    private static final long serialVersionUID = 1L;  
    private JLabel cell = null;  
    private NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("pt", "BR")); // Locale.getDefault()  
    
    private JLabel getCell() {  
        if (cell == null)  
            cell = new JLabel();  
        return cell;  
    }  
    public Component getTableCellRendererComponent(JTable table, Object value,  
            boolean selected, boolean hasFocus, int row, int column) {  
        Color bg;  
        if (value != null) {  
            bg = selectedColor;  
            getCell().setText(formatter.format(value));  
            getCell().setHorizontalAlignment(SwingConstants.RIGHT);  
            getCell().setOpaque(true);  
            getCell().setBackground(bg);  
            getCell().setForeground(selected ? Color.white : Color.black);  
            getCell().setVisible(true);  
            if (hasFocus) {  
                bg = selectedColor;  
                setBackground(bg);  
                setForeground(selected ? Color.white : Color.black);  
            } else if (!selected)  
                bg = (row % 2 == 0 ? alternateColor : whiteColor);  
            else  
                bg = selectedColor;  
            getCell().setBackground(bg);  
            getCell().setForeground(selected ? Color.white : Color.black);  
        } else  
            getCell().setText("");  
        return getCell();  
    }  


    
}
