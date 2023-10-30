package view;

import interface_adapter.HomeSearchController;
import interface_adapter.HomeSearchViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class HomeSearchView extends JPanel implements ActionListener, PropertyChangeListener{

    public  final String viewName = "home search";

    private final HomeSearchViewModel homesearchViewModel;

    private final JTextField searchBar = new JTextField(30);

    private final HomeSearchController homesearchController;

    public HomeSearchView(HomeSearchController controller, HomeSearchViewModel viewModel) {
        this.homesearchController = controller;
        this.homesearchViewModel = viewModel;
        homesearchViewModel.addPropertyChangeListener(this);

    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
