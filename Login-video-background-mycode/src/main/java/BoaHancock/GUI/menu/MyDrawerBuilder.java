package BoaHancock.GUI.menu;

import BoaHancock.GUI.forms.DashboardForm;
import BoaHancock.GUI.forms.InboxForm;
import BoaHancock.GUI.forms.ReadForm;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import raven.drawer.component.DrawerPanel;
import raven.drawer.component.SimpleDrawerBuilder;
import raven.drawer.component.footer.SimpleFooterData;
import raven.drawer.component.header.SimpleHeaderData;
import raven.drawer.component.header.SimpleHeaderStyle;
import raven.drawer.component.menu.MenuAction;
import raven.drawer.component.menu.MenuEvent;
import raven.drawer.component.menu.SimpleMenuOption;
import raven.drawer.component.menu.SimpleMenuStyle;
import raven.drawer.component.menu.data.Item;
import raven.drawer.component.menu.data.MenuItem;
import raven.swing.AvatarIcon;

import javax.swing.*;
import java.awt.*;

public class MyDrawerBuilder extends SimpleDrawerBuilder {
    private final ThemesChange themesChange;

    public MyDrawerBuilder() {
        themesChange = new ThemesChange();
    }

    @Override
    public Component getFooter() {
        return themesChange;
    }

    @Override
    public SimpleHeaderData getSimpleHeaderData() {
        AvatarIcon icon = new AvatarIcon(getClass().getResource("/main/resources/images/profile.jpg"), 60, 60, 999);

        icon.setBorder(2);
        return new SimpleHeaderData()
                .setIcon(icon)
                .setTitle("BoaHancock")
                .setDescription("raven@gmail.com")
                .setHeaderStyle(new SimpleHeaderStyle() {

                    @Override
                    public void styleTitle(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#FAFAFA");
                    }

                    @Override
                    public void styleDescription(JLabel label) {
                        label.putClientProperty(FlatClientProperties.STYLE, ""
                                + "[light]foreground:#E1E1E1");
                    }
                });
    }

    @Override
    public SimpleFooterData getSimpleFooterData() {
        return new SimpleFooterData();
    }

    @Override
    public SimpleMenuOption getSimpleMenuOption() {

        raven.drawer.component.menu.data.MenuItem items[] = new MenuItem[]{
                new Item.Label("MAIN"),
                new Item("Dashboard", "dashboard.svg"),
                new Item.Label("WEB APP"),
                new Item("Email", "email.svg")
                        .subMenu("Inbox")
                        .subMenu("Read")
                        .subMenu(
                                new Item("Group Read")
                                        .subMenu("Read 1")
                                        .subMenu("Read 2")
                                        .subMenu(
                                                new Item("Group Item")
                                                        .subMenu("Item 1")
                                                        .subMenu("Item 2")
                                                        .subMenu("Item 3")
                                                        .subMenu("Item 4")
                                                        .subMenu("Item 5")
                                                        .subMenu("Item 6")
                                        )
                                        .subMenu("Read 3")
                                        .subMenu("Read 4")
                                        .subMenu("Read 5")
                        )
                        .subMenu("Compost"),
                new Item("Chat", "chat.svg"),
                new Item("Calendar", "calendar.svg"),
                new Item.Label("COMPONENT"),
                new Item("Advanced UI", "ui.svg")
                        .subMenu("Cropper")
                        .subMenu("Owl Carousel")
                        .subMenu("Sweet Alert"),
                new Item("Forms", "forms.svg")
                        .subMenu("Basic Elements")
                        .subMenu("Advanced Elements")
                        .subMenu("SEditors")
                        .subMenu("Wizard"),
                new Item.Label("OTHER"),
                new Item("Charts", "chart.svg")
                        .subMenu("Apex")
                        .subMenu("Flot")
                        .subMenu("Sparkline"),
                new Item("Icons", "icon.svg")
                        .subMenu("Feather Icons")
                        .subMenu("Flag Icons")
                        .subMenu("Mdi Icons"),
                new Item("Special Pages", "page.svg")
                        .subMenu("Blank page")
                        .subMenu("Faq")
                        .subMenu("Invoice")
                        .subMenu("Profile")
                        .subMenu("Pricing")
                        .subMenu("Timeline")
        };

        SimpleMenuOption simpleMenuOption = new SimpleMenuOption() {
            @Override
            public Icon buildMenuIcon(String path, float scale) {
                FlatSVGIcon icon = new FlatSVGIcon(path, scale);
                FlatSVGIcon.ColorFilter colorFilter = new FlatSVGIcon.ColorFilter();
                colorFilter.add(Color.decode("#969696"), Color.decode("#FAFAFA"), Color.decode("#969696"));
                icon.setColorFilter(colorFilter);
                return icon;
            }
        };

        simpleMenuOption.setMenuStyle(new SimpleMenuStyle() {
            @Override
            public void styleMenuItem(JButton menu, int[] index) {
                menu.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:#FAFAFA;"
                        + "arc:10");
            }

            @Override
            public void styleMenu(JComponent component) {
                component.putClientProperty(FlatClientProperties.STYLE, ""
                        + "background:$Drawer.background");
            }

            @Override
            public void styleLabel(JLabel label) {
                label.putClientProperty(FlatClientProperties.STYLE, ""
                        + "[light]foreground:darken(#FAFAFA,15%);"
                        + "[dark]foreground:darken($Label.foreground,30%)");
            }
        });
        simpleMenuOption.addMenuEvent(new MenuEvent() {
            @Override
            public void selected(MenuAction action, int[] index) {
                if (index.length == 1) {
                    if (index[0] == 0) {
                        FormManager.showForm(new DashboardForm());
                    }
                } else if (index.length == 2) {
                    if (index[0] == 1) {
                        if (index[1] == 0) {
                            FormManager.showForm(new InboxForm());
                        } else if (index[1] == 1) {
                            FormManager.showForm(new ReadForm());
                        }
                    }
                }
            }
        });

        simpleMenuOption.setMenus(items)
                .setBaseIconPath("main/resources/menu")
                .setIconScale(0.45f);
        return simpleMenuOption;
    }

    @Override
    public void build(DrawerPanel drawerPanel) {
        drawerPanel.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Drawer.background");
    }

    @Override
    public int getDrawerWidth() {
        return 270;
    }
}
