//package BoaHancock.GUI.components;
//
//import com.formdev.flatlaf.FlatClientProperties;
//import com.formdev.flatlaf.FlatLightLaf;
//import net.miginfocom.swing.MigLayout;
//
//import javax.swing.*;
//import java.awt.*;
//
//public class FormManager {
//    private static FormManager instance;
//    private JDesktopPane desktopPane;
//    public static FormManager getInstance() {
//        if(instance == null) {
//            instance = new FormManager();
//        }
//        return instance;
//    }
//    private FormManager() {
//    }
//    public void setDesktopPane(JDesktopPane desktopPane) {
//
//        this.desktopPane = desktopPane;
//    }
//    public void DashboardshowForm(String title, Component component) {
//        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
//        frame.setSize(new Dimension(600, 450));
//        frame.add(component);
//        frame.setVisible(true);
//        desktopPane.add(frame,0);
//    }
//    public void showForm(String title, Component component) {
//        JInternalFrame frame = new JInternalFrame(title, true, true, true, true);
//        frame.getContentPane().setBackground(new Color(0, 0, 0, 0)); // 使内容面板透明
//        frame.setSize(new Dimension(600, 450));
//        frame.add(component);
//        frame.setVisible(true);
//        desktopPane.add(frame,0);
//    }
//
//
//    public void showSettingForm(String title, Component component) {
//        JInternalFrame frame = new JInternalFrame(title, false, true, false, true);
////        try {
////            UIManager.setLookAndFeel(new FlatLightLaf());
////        } catch (UnsupportedLookAndFeelException e) {
////            e.printStackTrace();
////            // 处理外观设置失败的情况
////        }
//        frame.getContentPane().setBackground(new Color(0, 0, 0, 0)); // 使内容面板透明
//        frame.setSize(new Dimension(300, 600));
//        frame.setLayout(new MigLayout("wrap, fillx, insets 45 45 50 45", "[fill]"));
//        JLabel title1 = new JLabel("编辑个人信息", SwingConstants.CENTER);
//        JTextField2 txtNickname = new JTextField2();
//        JTextField2 txtName = new JTextField2();
//        JTextField2 txtPhoneNumber = new JTextField2();
//
//        title1.putClientProperty(FlatClientProperties.STYLE, "" +
//                "font:bold +10");
//        txtNickname.putClientProperty(FlatClientProperties.STYLE, "" +
//                "margin:5,10,5,10;" +
//                "focusWidth:1;" +
//                "innerFocusWidth:0");
//        txtName.putClientProperty(FlatClientProperties.STYLE, "" +
//                "margin:5,10,5,10;" +
//                "focusWidth:1;" +
//                "innerFocusWidth:0");
//        txtPhoneNumber.putClientProperty(FlatClientProperties.STYLE, "" +
//                "margin:5,10,5,10;" +
//                "focusWidth:1;" +
//                "innerFocusWidth:0");
//        txtNickname.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your nickname");
//        txtName.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your name");
//        txtPhoneNumber.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Enter your phone number");
//        frame.add(title1);
//        frame.add(new JLabel("Nickname"), "gapy 10");
//        frame.add(txtNickname);
//        frame.add(new JLabel("Name"), "gapy 10");
//        frame.add(txtName);
//        frame.add(new JLabel("PhoneNumber"), "gapy 10");
//        frame.add(txtPhoneNumber);
//        frame.setVisible(true);
//        desktopPane.add(frame,0);
//    }
//}
