/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TreeModel;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.table.TableCellRenderer;

/**
 * 28 * A TableCellRenderer for JCheckBox that disables the checkbox when the
 * cell 29 * isn't editable to make it clear that you can't click on it 30 * 31
 *
 * * @author Mark A. Greenwood 32
 */
@SuppressWarnings("serial")
public class CheckBoxTableCellRenderer extends JCheckBox implements
        TableCellRenderer {

    /**
     * 38 * An empty border we use when the cell doesn't have focus 39
     */
    private static final Border NO_FOCUS = BorderFactory.createEmptyBorder(1, 1,
            1, 1);

    public CheckBoxTableCellRenderer() {
        super();

        // centre the checkbox within the cell
        setHorizontalAlignment(JCheckBox.CENTER);

        // make sure we always paint the cell border
        setBorderPainted(true);

        // make sure we always paint the background
        setOpaque(true);
    }

    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {

        // this is needed for Nimbus which has alternative rows in different colours
        // hopefully other L&Fs that also do this use the same UIManager key
        Color alternate = UIManager.getColor("Table.alternateRowColor");

        // strangely the background color from Nimbus doesn't render properly unless
        // we convert it in this way. I'm guessing the problem is to do with the
        // DerivedColor class that nimbus uses
        Color normal = new Color(table.getBackground().getRGB());

        if (isSelected) {
            // if the cell is selected then set it's colors from the table
            setForeground(table.getSelectionForeground());
            setBackground(table.getSelectionBackground());
        } else {
            // if the cell isn't selected set it's colors taking into account the
            // possibility of alternating colors that Nimbus throws into the mix
            setForeground(table.getForeground());
            setBackground(alternate != null && row % 2 == 0 ? alternate : normal);
        }

        // if the cell isn't editable then disable the checkbox to give some visual
        // feedback to the user
        setEnabled(table.isCellEditable(row, column));

        // make the checkbox reflect the underlying boolean data
        if (value instanceof Boolean) {
            setSelected(value != null && (Boolean) value);
        }
        if (hasFocus) {
            // if the cell has focus then draw the border set by the L&F
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        } else {
            // if the cell doesn't have the focus then draw the empty border
            setBorder(NO_FOCUS);
        }

        // now return the checkbox for rendering into the table
        return this;
    }
}
