package BoaHancock.GUI.components;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;

import javax.swing.*;
import java.awt.*;

public class Background extends JPanel {
    public Background() {
        init();
    }
    private void init() {
        setOpaque(false);
        setLayout(new BorderLayout());
        putClientProperty(FlatClientProperties.STYLE, ""
                + "border:5,5,5,5;"
                + "background:$Drawer.background");
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        FlatUIUtils.setRenderingHints(g2);
        int arc = UIScale.scale(10);
        g2.setColor(getBackground());
        FlatUIUtils.paintComponentBackground(g2, 0, 0, getWidth(), getHeight(), 0, arc);
        g2.dispose();
        super.paintComponent(g);
    }
}
