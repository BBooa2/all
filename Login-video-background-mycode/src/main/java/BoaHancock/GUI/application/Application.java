package BoaHancock.GUI.application;

import BoaHancock.GUI.components.ApplicationForm;
import BoaHancock.GUI.components.Background;
import BoaHancock.GUI.forms.DashboardForm;
import BoaHancock.GUI.menu.FormManager;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import raven.popup.GlassPanePopup;

import javax.swing.*;
import java.awt.*;

public class Application extends JFrame {
    public final static Application application = new Application();

    private final ApplicationForm applicationForm = new ApplicationForm();
    public Application() {
        init();
    }
    private void init() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT,true);
//        setUndecorated(true); // 隐藏边框
        setSize(UIScale.scale(new Dimension(1293,757)));
        setResizable(false);
        setLocationRelativeTo(null); // 居中显示
        setContentPane(new Background());
//        setVisible(true);
        GlassPanePopup.install(this);
        FormManager.install(this);
        FormManager.showForm(new DashboardForm());
    }
}
