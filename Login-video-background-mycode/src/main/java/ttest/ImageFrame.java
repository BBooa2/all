package ttest;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;

import javax.swing.*;

public class ImageFrame extends JFrame {

    public ImageFrame() {
        setTitle("显示图片");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);

        // 创建一个 JLabel 来显示图片
        JButton cmdRefresh = new JButton();

        cmdRefresh = createButton(new FlatSVGIcon("main/resources/icon/dark.svg"));

        // 添加 JLabel 到 JFrame 中
        add(cmdRefresh);
    }

    private JButton createButton(Icon icon) {
        JButton button = new JButton(icon);
        button.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Button.toolbar.background;"
                + "arc:10;"
                + "borderWidth:0;"
                + "focusWidth:0;"
                + "innerFocusWidth:0");
        return button;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ImageFrame frame = new ImageFrame();
            frame.setVisible(true);
        });
    }
}
