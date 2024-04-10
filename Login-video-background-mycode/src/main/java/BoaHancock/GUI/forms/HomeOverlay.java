package BoaHancock.GUI.forms;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.util.Animator;
import com.formdev.flatlaf.util.CubicBezierEasing;
import net.miginfocom.swing.MigLayout;
import BoaHancock.GUI.components.EventHomeOverlay;
import BoaHancock.GUI.components.HeaderButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.util.List;

public class HomeOverlay extends JWindow {

    static Point origin = new Point();
    public PanelOverlay getOverlay() {
        return overlay;
    }
    private PanelOverlay overlay;
    private List<ModelLocation> locations;

    public HomeOverlay(JFrame frame, List<ModelLocation> locations) {
        super(frame);
        this.locations = locations;
        init();
    }
    private void init() {
        setBackground(new Color(35,96,135,80));
        setLayout(new BorderLayout());
        overlay = new PanelOverlay();
        add(overlay);
    }

    public class PanelOverlay extends JPanel {

        public void setEventHomeOverlay(EventHomeOverlay eventHomeOverlay) {
            this.eventHomeOverlay = eventHomeOverlay;
        }
        private MigLayout migLayout;
        private EventHomeOverlay eventHomeOverlay;
        private AnimationType animationType = AnimationType.NONE;
        private Animator animator;
        private Animator loginAnimator;
        private Animator registerAnimator;
        private float animate;
        private int index;
        private boolean showLogin;
        private boolean showRegister;
        public void setIndex(int index) {
            this.index = index;
            ModelLocation location = locations.get(index);
            textTitle.setText(location.getTitle());
            textDescription.setText(location.getDescription());
        }
        public PanelOverlay() {
            init();
        }

        private void init() {

            setOpaque(false);
            migLayout = new MigLayout("fill,insets 10 180 10 180", "fill", "[grow 0][]");
            setLayout(migLayout);
            createHeader();
//            createPageButton();
            createLogin();
            createRegister();
            JPanel panel = new JPanel(new MigLayout("wrap", "", "[]30[]"));
            panel.setOpaque(false);
            textTitle = new JTextPane();
            textDescription = new JTextPane();
//            cmdReadMore = new JButton("Read More");
            textTitle.setOpaque(false);
            textTitle.setEditable(false);
            textTitle.putClientProperty(FlatClientProperties.STYLE, "" +
                    "font:bold +40;" +
                    "border:0,0,0,0");

            textDescription.setOpaque(false);
            textDescription.setEditable(false);
            textDescription.putClientProperty(FlatClientProperties.STYLE, "" +
                    "font:bold +2;" +
                    "border:0,0,0,0");
//            cmdReadMore.putClientProperty(FlatClientProperties.STYLE,"" +
//                    "background:$Component.accentColor;" +
//                    "borderWidth:0;" +
//                    "margin:5,15,5,15;" +
//                    "focusWidth:0;" +
//                    "innerFocusWidth:0;" +
//                    "arc:999");
            panel.add(textTitle);
            panel.add(textDescription);
//            panel.add(cmdReadMore);
            add(panel, "width 80%!");
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseReleased(MouseEvent e) {
                    runLoginAnimation(false);
                    runRegisterAnimation(false);
//                    System.out.println("Login : " + showLogin + "  Register : " + showRegister);
                }
            });
            animator = new Animator(500, new Animator.TimingTarget() {
                @Override
                public void timingEvent(float v) {
                    animate = v;
                    repaint();
                }

                @Override
                public void end() {
                    if(animationType == AnimationType.CLOSE_VIDEO) {
                        eventHomeOverlay.onChanged(index);
                        SwingUtilities.invokeLater(() -> {
                            sleep(500);
                            runAnimation(index, AnimationType.SHOW_VIDEO);
                        });
                    } else {
                        animationType = AnimationType.NONE;
                    }
                }
            });
            loginAnimator = new Animator(500 , new Animator.TimingTarget() {
                @Override
                public void timingEvent(float v) {
                    float f = showLogin ? v : 1f - v;
                    int x = (int) ((350 + 180) * f);
                    migLayout.setComponentConstraints(panelLogin, "pos 100%-" + x + " 0.5al, w 350");
                    revalidate();
                }
            });

            registerAnimator = new Animator(500, new Animator.TimingTarget() {
                @Override
                public void timingEvent(float v) {
                    float f = showRegister ? v : 1f - v;
                    int x = (int) ((350 + 180) * f);
                    migLayout.setComponentConstraints(panelRegister, "pos 100%-" + x + " 0.5al, w 350");
                    revalidate();
                }
            });

            animator.setInterpolator(CubicBezierEasing.EASE_IN);
            loginAnimator.setInterpolator(CubicBezierEasing.EASE);
            registerAnimator.setInterpolator(CubicBezierEasing.EASE);
        }

        private void sleep(long l) {
            try {
                Thread.sleep(l);
            }catch (Exception e) {
                System.err.println(e);
            }
        }
        private void createHeader() {
            header = new JPanel(new MigLayout("fill", "[]push[][]"));
            header.setOpaque(false);
            JLabel title = new JLabel("在线时长统计");
            title.putClientProperty(FlatClientProperties.STYLE, "" + "font:bold +10");
            HeaderButton home = new HeaderButton("Home");
            HeaderButton about = new HeaderButton("About");
            HeaderButton login = new HeaderButton("Login");
            HeaderButton register = new HeaderButton("Register");
//            HeaderButton explore = new HeaderButton("Explore");

            login.addActionListener(e -> {
                runLoginAnimation(true);
//                System.out.println("Login : " + showLogin);
            });

            register.addActionListener(e -> {
                runRegisterAnimation(true);
//                System.out.println("Register : " + showRegister);

            });
            header.add(title);
            header.add(home);
            header.add(about);
//            header.add(explore);
            header.add(login);
            header.add(register);
            add(header, "wrap");
        }

        private void createLogin() {
            panelLogin = new Login();
            add(panelLogin, "pos 100% 0.5al,w 350");
        }

        private void createRegister() {
            panelRegister = new Register();
            add(panelRegister, "pos 100% 0.5al,w 350");
        }


        /* 多个视频的情况下切换视频按钮
        private void createPageButton() {
            JPanel panel = new JPanel(new MigLayout("gapx 20"));
            panel.setOpaque(false);
            for(int i = 0; i < locations.size(); ++ i) {
                JButton cmd = new JButton("");
                cmd.putClientProperty(FlatClientProperties.STYLE, "" +
                        "margin:5,5,5,5;" +
                        "arc:999;" +
                        "borderWidth:0;" +
                        "focusWidth:0;" +
                        "innerFocusWidth:0;" +
                        "selectedBackground:$Component.accentColor");
                cmd.setCursor(new Cursor(Cursor.HAND_CURSOR));
                final int index = i;
                cmd.addActionListener(e -> {
                    if (this.index != index) {
                        boolean act = runAnimation(index, AnimationType.CLOSE_VIDEO);
                        if (act) {
                            setSelectedButton(panel, index);
                        }
                    }
                });
                panel.add(cmd);
            }
            add(panel, "pos 0.5al 80%");
            setSelectedButton(panel, index);
        }

        private void setSelectedButton(JPanel panel, int index) {
            int count = panel.getComponentCount();
            for(int i = 0; i < count; ++ i) {
                JButton cmd = (JButton) panel.getComponent(i);
                if (i == index) {
                    cmd.setSelected(true);
                } else {
                    cmd.setSelected(false);
                }
            }
        }
        */



        private boolean runAnimation(int index, AnimationType animationType) {
            if (!animator.isRunning()) {
                this.animate = 0;
                this.animationType = animationType;
                this.index = index;
                animator.start();
                return true;
            } else {
                return false;
            }
        }
        private void runLoginAnimation(boolean show) {
            if(show && showRegister == show) {
                runRegisterAnimation(false);
                showRegister = false;
            }
            if (showLogin != show) {
                if(registerAnimator.isRunning()) {
                    runRegisterAnimation(false);
                    showRegister = false;
                }
                if (!loginAnimator.isRunning()) {
                    showLogin = show;
                    loginAnimator.start();
                }
            }
        }

        private void runRegisterAnimation(boolean show) {
            if(show && showLogin == show) {
                runLoginAnimation(false);
                showLogin = false;
            }
            if (showRegister != show) {
                if(loginAnimator.isRunning()) {
                    runLoginAnimation(false);
                    showLogin = false;
                }
                if (!registerAnimator.isRunning()) {
                    showRegister = show;
                    registerAnimator.start();
                }
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            if (animationType != AnimationType.NONE) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                int width = getWidth();
                int height = getHeight();
                g2.setColor(UIManager.getColor("Component.accentColor"));
                Rectangle rec = new Rectangle(0, 0, width, height);
                if (animationType == AnimationType.CLOSE_VIDEO) {
                    g2.setComposite(AlphaComposite.SrcOver.derive(animate));
                    g2.fill(rec);
                } else {
                    Area area = new Area(rec);
                    area.subtract(new Area(createRec(rec)));
                    g2.fill(area);
                }
                g2.dispose();
            }
            super.paintComponent(g);
        }

        private Shape createRec(Rectangle rec) {
            int maxSize = Math.max(rec.width, rec.height);
            float size = maxSize * animate;
            float x = (rec.width - size) / 2;
            float y = (rec.height - size) / 2;
            Ellipse2D ell = new Ellipse2D.Double(x, y, size, size);
            return ell;
        }
        private JPanel header;
        private JTextPane textTitle;
        private JTextPane textDescription;
//        private JButton cmdReadMore;
        private Login panelLogin;
        private Register panelRegister;
    }

    public enum AnimationType {
        CLOSE_VIDEO, SHOW_VIDEO, NONE
    }
}
