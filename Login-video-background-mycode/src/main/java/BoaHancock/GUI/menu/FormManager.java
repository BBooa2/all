package BoaHancock.GUI.menu;

import BoaHancock.GUI.components.ApplicationForm;
import BoaHancock.GUI.components.SimpleForm;
import BoaHancock.GUI.swing.slider.PanelSlider;
import BoaHancock.GUI.swing.slider.SimpleTransition;
import BoaHancock.utils.UndoRedo;

import javax.swing.*;
import java.awt.*;

public class FormManager {
    private static FormManager instance;
    private final JFrame frame;
    private final UndoRedo<SimpleForm> forms = new UndoRedo<>();

    private boolean menuShowing = true;

    private final PanelSlider panelSlider;

    private final ApplicationForm applicationForm;

    private final Menu menu;
    public static void install(JFrame frame) {
        instance = new FormManager(frame);
    }

    private FormManager(JFrame frame) {
        this.frame = frame;
        panelSlider = new PanelSlider();
        applicationForm = new ApplicationForm();
        menu = new Menu(new MyDrawerBuilder());
        this.frame.getContentPane().add(panelSlider);
    }

    public static void showMenu() {
        instance.menuShowing = true;
        instance.panelSlider.addSlide(instance.menu, SimpleTransition.getShowMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth()));
    }

    public static void showForm(SimpleForm component) {
        if (isNewFormAble()) {
            instance.forms.add(component);
            if (instance.menuShowing == true) {
                instance.menuShowing = false;
                Image oldImage = instance.panelSlider.createOldImage();
                instance.applicationForm.setForm(component);
                instance.panelSlider.addSlide(instance.applicationForm, SimpleTransition.getSwitchFormTransition(oldImage, instance.menu.getDrawerBuilder().getDrawerWidth()));
            } else {
                instance.applicationForm.showForm(component);
            }
            instance.forms.getCurrent().formInitAndOpen();
        }
    }

    public static void hideMenu() {
        instance.menuShowing = false;
        instance.panelSlider.addSlide(instance.applicationForm, SimpleTransition.getHideMenuTransition(instance.menu.getDrawerBuilder().getDrawerWidth()));
    }

    public static void undo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isUndoAble()) {
                instance.applicationForm.showForm(instance.forms.undo(), SimpleTransition.getDefaultTransition(true));
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void redo() {
        if (isNewFormAble()) {
            if (!instance.menuShowing && instance.forms.isRedoAble()) {
                instance.applicationForm.showForm(instance.forms.redo());
                instance.forms.getCurrent().formOpen();
            }
        }
    }

    public static void refresh() {
        if (!instance.menuShowing) {
            instance.forms.getCurrent().formRefresh();
        }
    }

    public static UndoRedo<SimpleForm> getForms() {
        return instance.forms;
    }

    public static boolean isNewFormAble() {
        return instance.forms.getCurrent() == null || instance.forms.getCurrent().formClose();
    }

    public static void updateTempFormUI() {
        for (SimpleForm f : instance.forms) {
            SwingUtilities.updateComponentTreeUI(f);
        }
    }
}
