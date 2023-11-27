package view;

import interface_adapter.ViewManagerModel;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class ViewManager implements PropertyChangeListener {
    private final BorderLayout borderLayout;
    private final JPanel views;
    private ViewManagerModel viewManagerModel;

    public ViewManager(JPanel views, BorderLayout borderLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.borderLayout = borderLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("view")) {
            String viewModelName = (String) evt.getNewValue();
//            cardLayout.show(views, viewModelName);
        }
    }
}
