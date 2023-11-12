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

//    filters
    private JComboBox<String> numRooms;
    private JComboBox<String> priceRange;
    private JComboBox<String> numBaths;


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

        String[] priceRangeStrings = {"<1000", "1000-1500", "1500-2000", "2000+"};
        priceRange = new JComboBox<>(priceRangeStrings);
        priceRange.setSelectedIndex(0);
        priceRange.addActionListener(this);

        String[] numBathsStrings = {"1", "2", "3", "4+"};
        numBaths = new JComboBox<>(numBathsStrings);
        numBaths.setSelectedIndex(0);
        numBaths.addActionListener(this);


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

        c.gridx = 10;
        c.gridy = 2;
        add(priceRange, c);

        c.gridx = 15;
        c.gridy = 2;
        add(numBaths);


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
        } else if (e.getSource() == priceRange) {
            JComboBox cb = (JComboBox)e.getSource();
            String priceRange = (String)cb.getSelectedItem();
            System.out.println("Selected priceRange: " + priceRange);
        } else if (e.getSource() == numBaths) {
            JComboBox cb = (JComboBox)e.getSource();
            String numOfBaths = (String)cb.getSelectedItem();
            System.out.println("Selected number of bathrooms: " + numOfBaths);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("property change");
    }
}

