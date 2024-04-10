package BoaHancock.GUI.main;

import BoaHancock.service.netty.Client;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.util.UIScale;
import BoaHancock.GUI.forms.Home;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


public class Main extends JFrame {
    public static Client client = new Client();
    public final static Main main = new Main();
//    public static Point origin = new Point();
    private Home home;
    public Main() {
        init();
    }
    private void init() {
        getRootPane().putClientProperty(FlatClientProperties.FULL_WINDOW_CONTENT, true);
//        setUndecorated(true); // 隐藏边框
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(UIScale.scale(new Dimension(1293,757)));
        setLocationRelativeTo(null);
        setResizable(false);
        home = new Home();
        setContentPane(home);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                home.initOverlay(Main.this);
                home.play(0);
            }

            @Override
            public void windowClosing(WindowEvent e) {
                home.stop();
            }
        });
    }
    public static void main(String[] args) {
        System.setProperty("sun.java2d.noddraw", "true");
        FlatRobotoFont.install();
        FlatLaf.registerCustomDefaultsSource("main.resources.themes");
        FlatMacDarkLaf.setup();
        UIManager.put("defaultFont", new Font( "", Font.PLAIN, 13));
        new Thread(() -> {
            try {
                client.connect("localhost", 8091);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        EventQueue.invokeLater(() -> main.setVisible(true));
    }
    public void closeLoginWindow() {
        main.dispose();
    }

}
