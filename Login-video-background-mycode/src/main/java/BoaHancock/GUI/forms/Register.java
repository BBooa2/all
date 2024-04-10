package BoaHancock.GUI.forms;

import BoaHancock.GUI.components.JTextField2;
import BoaHancock.pojo.info.RegisterRequestInfo;
import BoaHancock.GUI.main.Main;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.UIScale;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.geom.RoundRectangle2D;

public class Register extends JPanel {
    public RegisterRequestInfo info;
    public Register() {
        init();
    }

    private void init() {
        setOpaque(false); //设置为透明
        addMouseListener(new MouseAdapter() {
        });
        setLayout(new MigLayout("wrap, fillx, insets 20 45 20 45", "[fill]"));
        JLabel title = new JLabel("Register a new account", SwingConstants.CENTER);
        JTextField2 txtUsername = new JTextField2();
        JTextField2 txtNickname = new JTextField2();
        JPasswordField txtPassword = new JPasswordField();
        JPasswordField txtConfirmPassword = new JPasswordField();
        JTextField2 txtEmail = new JTextField2();
        JButton cmdRegister = new JButton("Register");

        cmdRegister.addActionListener(e -> {
            info = new RegisterRequestInfo(txtUsername.getText(), new String(txtPassword.getPassword()), new String(txtConfirmPassword.getPassword()), txtNickname.getText(), txtEmail.getText());
            Main.client.sendRegisterMessage(info);
        });

        title.putClientProperty(FlatClientProperties.STYLE, "" +
                "font:bold +10");
        txtUsername.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:5,10,5,10;" +
                "focusWidth:1;" +
                "innerFocusWidth:0");
        txtNickname.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:5,10,5,10;" +
                "focusWidth:1;" +
                "innerFocusWidth:0");
        txtPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:5,10,5,10;" +
                "focusWidth:1;" +
                "innerFocusWidth:0;" +
                "showRevealButton:true");
        txtConfirmPassword.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:5,10,5,10;" +
                "focusWidth:1;" +
                "innerFocusWidth:0;" +
                "showRevealButton:true");
        txtEmail.putClientProperty(FlatClientProperties.STYLE, "" +
                "margin:5,10,5,10;" +
                "focusWidth:1;" +
                "innerFocusWidth:0");
        cmdRegister.putClientProperty(FlatClientProperties.STYLE,"" +
                "background:$Component.accentColor;" +
                "borderWidth:0;" +
                "focusWidth:0;" +
                "innerFocusWidth:0");
        txtUsername.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your username");
        txtNickname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your nickname");
        txtPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        txtConfirmPassword.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your password");
        txtEmail.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your email");

        add(title);
        add(new JLabel("Username"), "gapy 10");
        add(txtUsername);
        add(new JLabel("Nickname"), "gapy 10");
        add(txtNickname);
        add(new JLabel("Password"), "gapy 10");
        add(txtPassword);
        add(new JLabel("ConfirmPassword"), "gapy 10");
        add(txtConfirmPassword);
        add(new JLabel("Email"), "gapy 10");
        add(txtEmail);
        add(cmdRegister, "gapy 30");
    }
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D)g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        int arc = UIScale.scale(20);
        g2.setColor(getBackground());
        g2.setComposite(AlphaComposite.SrcOver.derive(0.6f));
        g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), arc, arc));
        g2.dispose();
        super.paintComponent(g);
    }
}
