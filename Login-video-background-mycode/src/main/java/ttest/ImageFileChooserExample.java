package ttest;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ImageFileChooserExample extends JFrame {

    private JLabel imageLabel;

    public ImageFileChooserExample() {
        setTitle("Image Chooser Example");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JButton selectImageButton = new JButton("Select Image");
        selectImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Select Image");
                int result = fileChooser.showOpenDialog(ImageFileChooserExample.this);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
                    imageLabel.setIcon(imageIcon);
                }
            }
        });

        imageLabel = new JLabel();
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(selectImageButton);

        getContentPane().add(imageLabel, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
//        FlatRobotoFont.install();
//        FlatLaf.registerCustomDefaultsSource("reven.themes");
//        FlatMacDarkLaf.setup();
//        UIManager.put("defaultFont", new Font( "", Font.PLAIN, 13));
//        SwingUtilities.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                ImageFileChooserExample example = new ImageFileChooserExample();
//                example.setVisible(true);
//            }
//        });

        testclass tt = new testclass();
        tt.setName("11");
        System.out.println(tt.getName());
    }
}