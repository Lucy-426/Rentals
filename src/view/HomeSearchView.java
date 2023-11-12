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

public class HomeSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "home search";

    private final HomeSearchViewModel homesearchViewModel;

    private final JTextField homeSearchBar = new JTextField(30);
    private JButton searchButton;

    private JComboBox<String> numRooms;

    private final HomeSearchController homesearchController;

    public HomeSearchView(HomeSearchController controller, HomeSearchViewModel viewModel) {
        this.homesearchController = controller;
        this.homesearchViewModel = viewModel;
        homesearchViewModel.addPropertyChangeListener(this);

//        for formatting
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(homesearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchBar = new LabelTextPanel(
                new JLabel(homesearchViewModel.SEARCH_BAR_LABEL), homeSearchBar);
        searchButton = new JButton("Search");
        // Add action listener to the search button
        searchButton.addActionListener(this);

//        filter for number of rooms
        String[] numRoomStrings = {"1", "2", "3", "4", "5+"};
        numRooms = new JComboBox<>(numRoomStrings);
        numRooms.setSelectedIndex(0);
        numRooms.addActionListener(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(searchBar);
        // TODO: fix formatting of everything
        c.gridx = 10;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(searchButton, c);

        c.gridx = 0;
        c.gridy = 2;
        add(numRooms, c);

    }


    // TODO: action performed and property change
    @Override
    public void actionPerformed(ActionEvent e) {
//        System.out.println("action Performed");
        if (e.getSource() == searchButton) {
            String searchText = homeSearchBar.getText();
//         Here, implement whatever search logic using searchText
            System.out.println("Searching for: " + searchText);
        } else if (e.getSource() == numRooms) {
            JComboBox cb = (JComboBox)e.getSource();
            String numOfRoom = (String)cb.getSelectedItem();
            System.out.println("Selected number of rooms: " + numOfRoom);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("property change");
    }
}

