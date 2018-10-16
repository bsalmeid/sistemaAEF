package Teste;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputAdapter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class JCollapsiblePane extends JPanel {

    private static final Icon collapsedIcon = createHeaderIcon(true);
    private static final Icon expandedIcon = createHeaderIcon(false);
    private static final int iconSize = 11;
    private Component contentPane;
    private boolean collapsed;
    private boolean fixedSize;
    private JLabel titleLabel;
    private JLabel iconLabel;
    private JPanel headerRow;
    private JPanel contentRow;
    
    public static JPanel createCollapsiblePaneContainer() {
        return new CollapsiblePaneContainer();
    }

    /**
     *
     * Constructs a new <code>CollapsiblePane</code>.
     *
     */
    public JCollapsiblePane(String title, Component contentPane, boolean collapsed, boolean fixedSize) {

        super();
        this.titleLabel = new JLabel(title);
        this.collapsed = collapsed;
        this.contentPane = contentPane;
        this.fixedSize = fixedSize;
        iconLabel = new JLabel();
        iconLabel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setCollapsed(!isCollapsed());
                updateState();
            }

        });
        titleLabel = new JLabel(title);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.ITALIC));
        JPanel iconDummy = new JPanel();
        iconDummy.setPreferredSize(new Dimension(2 * iconSize, iconSize));
        headerRow = new JPanel(new BorderLayout(2, 0));
        headerRow.add(iconLabel, BorderLayout.WEST);
        headerRow.add(titleLabel, BorderLayout.CENTER);
        contentRow = new JPanel(new BorderLayout(2, 0));
        contentRow.add(iconDummy, BorderLayout.WEST);
        if (contentPane != null) {
            contentRow.add(contentPane, BorderLayout.CENTER);
        }
        setLayout(new BorderLayout(2, 2));
        add(headerRow, BorderLayout.NORTH);
        add(contentRow, BorderLayout.CENTER);
        updateState();
    }

    public Component getContentPane() {
        return contentPane;
    }

    public void setContentPane(Component contentPane) {
        Component oldValue = this.contentPane;
        this.contentPane = contentPane;
        if (oldValue != getContentPane()) {
            if (oldValue != null) {
                contentRow.remove(oldValue);
            }
            if (getContentPane() != null) {
                contentRow.add(getContentPane(), BorderLayout.CENTER);
            }
            updateState();
            firePropertyChange("contentPane", oldValue, getContentPane());
        }

    }

    public boolean isFixedSize() {
        return fixedSize;
    }

    public void setFixedSize(boolean fixedSize) {
        boolean oldValue = isFixedSize();
        this.fixedSize = fixedSize;
        if (oldValue != isFixedSize()) {
            revalidateParent();
            firePropertyChange("fixedSize", oldValue, isFixedSize());
        }

    }

    public boolean isCollapsed() {
        return collapsed;
    }

    public void setCollapsed(boolean collapsed) {
        boolean oldValue = isCollapsed();
        this.collapsed = collapsed;
        if (oldValue != isCollapsed()) {
            updateState();
            firePropertyChange("collapsed", oldValue, isCollapsed());
        }
    }

    private void updateState() {
        updateHeaderRow();
        updateContentRow();
    }

    private void updateHeaderRow() {
        iconLabel.setIcon(collapsed ? collapsedIcon : expandedIcon);
    }

    private void updateContentRow() {
        if (isCollapsed()) {
            remove(contentRow);
        } else {
            add(contentRow, BorderLayout.CENTER);
        }
        revalidateParent();

    }

    private void revalidateParent() {
        Container parent = getParent();
        if (parent != null) {
            parent.invalidate();
            parent.validate();
        }
    }

    public String getTitle() {

        return titleLabel.getText();

    }

    public void setTitle(String title) {

        String oldValue = getTitle();

        this.titleLabel.setText(title);

        firePropertyChange("title", oldValue, getTitle());

        repaint();

    }

    private static Icon createHeaderIcon(boolean collapsed) {

        BufferedImage bi = new BufferedImage(iconSize, iconSize, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = bi.createGraphics();

        int n = iconSize - 1;

        int n2 = n / 2;

        g.setColor(Color.WHITE);

        g.fillRect(0, 0, n, n);

        g.setColor(Color.GRAY);

        g.drawRect(0, 0, n, n);

        if (collapsed) {

            g.drawLine(n2, 2, n2, n - 2);

        }

        g.drawLine(2, n2, n - 2, n2);

        g.dispose();

        return new ImageIcon(bi);

    }

    private int getPreferredHeight() {

        return getPreferredSize().height;

    }

    private int getPreferredHeaderHeight() {

        return headerRow.getPreferredSize().height;

    }

    private static class CollapsiblePaneContainer extends JPanel {

        public CollapsiblePaneContainer() {

            super(null);

        }

        @Override

        protected void addImpl(Component component, Object constraints, int index) {

            if (!(component instanceof JCollapsiblePane)) {

                throw new IllegalArgumentException("component not a " + JCollapsiblePane.class.getName());

            }

            super.addImpl(component, constraints, index);

        }

        @Override

        public void doLayout() {

            Component[] components = getComponents();

            ArrayList<Float> weights = new ArrayList<Float>();

            int collapsedHeightSum = 0;

            int expandedHeightSum = 0;

            for (Component component : components) {

                JCollapsiblePane collapsiblePane = (JCollapsiblePane) component;

                int height = component.getPreferredSize().height;

                if (collapsiblePane.isCollapsed() || collapsiblePane.isFixedSize()) {

                    collapsedHeightSum += height;

                } else {

                    expandedHeightSum += height;

                    weights.add((float) height);

                }

            }

            for (int i = 0; i < weights.size(); i++) {

                weights.set(i, weights.get(i) / (float) expandedHeightSum);

            }

            Insets insets = getInsets();

            int availableWidth = getWidth() - (insets.left + insets.right);

            int availableHeight = getHeight() - (insets.top + insets.bottom);

            int distributableSpace = availableHeight - collapsedHeightSum;

            int x = insets.left;

            int y = insets.top;

            int currentHeight;

            int i = 0;

            for (Component component : components) {

                JCollapsiblePane collapsiblePane = (JCollapsiblePane) component;

                if (collapsiblePane.isCollapsed() || collapsiblePane.isFixedSize()) {

                    currentHeight = collapsiblePane.getPreferredHeight();

                } else {

                    currentHeight = Math.round(weights.get(i++) * distributableSpace);

                    int headerHeight = collapsiblePane.getPreferredHeaderHeight();

                    if (currentHeight < headerHeight) {

                        currentHeight = headerHeight;

                    }

                }

                component.setBounds(x, y, availableWidth, currentHeight);

                y += currentHeight;

            }

        }

    }

}
