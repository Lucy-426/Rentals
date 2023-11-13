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

    private JTextArea listingsArea;
    private JScrollPane listingsScroll;


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
        numRooms = new JComboBox<>(homesearchViewModel.numRoomStrings);
        numRooms.setSelectedIndex(0);
        numRooms.addActionListener(this);

        priceRange = new JComboBox<>(homesearchViewModel.priceRangeStrings);
        priceRange.setSelectedIndex(0);
        priceRange.addActionListener(this);

        numBaths = new JComboBox<>(homesearchViewModel.numBathsStrings);
        numBaths.setSelectedIndex(0);
        numBaths.addActionListener(this);



        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(searchBar);
        // TODO: fix formatting of everything
        c.gridx = 10;
        c.gridy = 1;
        c.gridwidth = 1;
        this.add(searchButton, c);

        this.add(new JLabel("Select number of bedrooms:"));
        c.gridx = 0;
        c.gridy = 2;
        add(numRooms, c);

        this.add(new JLabel("Select price range:"));
        c.gridx = 10;
        c.gridy = 2;
        add(priceRange, c);

        this.add(new JLabel("Select number of bathrooms:"));
        c.gridx = 15;
        c.gridy = 2;
        add(numBaths);

        this.add(new JLabel("Listings:"));
//    scroll pane for listings
//    Create a JTextArea
        listingsArea = new JTextArea(20, 20);
        listingsArea.setEditable(false); // if you want to make it read-only
        listingsArea.setText("Listings would go here...\n");
//    Create a JScrollPane and add the JTextArea to it
        listingsScroll = new JScrollPane(listingsArea);
        listingsScroll.setPreferredSize(new Dimension(200, 200));  // Set a preferred size for the JScrollPane


//     Set constraints for JScrollPane
        c.gridx = 20;  // Change to desired column
        c.gridy = 1;  // Change to desired row
        c.gridwidth = 1;  // Spans across 1 column
        c.gridheight = 3;  // Spans across 3 rows
        c.fill = GridBagConstraints.BOTH;  // The component should be resized both horizontally and vertically
        c.weightx = 1.0;  // The extra space should be distributed to this column
        c.weighty = 1.0;  // The extra space should be distributed to this row

        // Add the JScrollPane to the panel
        add(listingsScroll, c);
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

