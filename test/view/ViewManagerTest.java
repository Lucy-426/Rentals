package view;

import interface_adapter.ViewManagerModel;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;

import static org.junit.jupiter.api.Assertions.*;

class ViewManagerTest {

    @Test
    void propertyChange() {
        CardLayout cardLayout = new CardLayout();
        JPanel views = new JPanel(cardLayout);
        ViewManagerModel viewManagerModel = new ViewManagerModel();
        ViewManager viewManager = new ViewManager(views, cardLayout, viewManagerModel);
        PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, "view", null, null);
        viewManager.propertyChange(propertyChangeEvent);
    }
}