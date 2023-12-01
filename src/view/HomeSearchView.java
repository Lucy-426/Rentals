package view;

import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class HomeSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Search";

    private final HomeSearchViewModel homesearchViewModel;

    private final JTextField homeSearchBar = new JTextField(30);
    private JButton searchButton;

    private JButton update;

//    filters
    private JComboBox<String> numRooms;
    private JComboBox<String> priceRange;
    private JComboBox<String> numBaths;
    private JComboBox<String> walkScore;
    private JComboBox<String> furnished;
    private JComboBox<String> listingType;

    // Scroll pane
    private JScrollPane listingsScroll;
    private ArrayList<JButton> listingButtons = new ArrayList<>();

    private JPanel buttonsPanel;


    // Controllers
    private final HomeSearchController homesearchController;

    private final ListingController listingController;


    public HomeSearchView(HomeSearchController homeController, ListingController listingController, HomeSearchViewModel viewModel) {
        this.homesearchController = homeController;
        this.listingController = listingController;
        this.homesearchViewModel = viewModel;
        homesearchViewModel.addPropertyChangeListener(this);

        // for formatting
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(homesearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel searchBar = new LabelTextPanel(
                new JLabel(homesearchViewModel.SEARCH_BAR_LABEL), homeSearchBar);
        searchButton = new JButton("Search");
        // Add action listener to the search button
        searchButton.addActionListener(this);

        // filter for number of rooms
        numRooms = new JComboBox<>(homesearchViewModel.numRoomStrings);
        numRooms.setSelectedIndex(0);
        numRooms.addActionListener(this);

        priceRange = new JComboBox<>(homesearchViewModel.priceRangeStrings);
        priceRange.setSelectedIndex(0);
        priceRange.addActionListener(this);

        numBaths = new JComboBox<>(homesearchViewModel.numBathsStrings);
        numBaths.setSelectedIndex(0);
        numBaths.addActionListener(this);

        walkScore = new JComboBox<>(homesearchViewModel.walkScoreStrings);
        walkScore.setSelectedIndex(0);
        walkScore.addActionListener(this);

        furnished = new JComboBox<>(homesearchViewModel.furnishedStrings);
        furnished.setSelectedIndex(0);
        furnished.addActionListener(this);

        listingType = new JComboBox<>(homesearchViewModel.listingTypeStrings);
        listingType.setSelectedIndex(0);
        listingType.addActionListener(this);


        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(searchBar);
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

        this.add(new JLabel("Select preferred walkscore:"));
        c.gridx = 20;
        c.gridy = 2;
        add(walkScore);

        this.add(new JLabel("furnished/not furnished"));
        c.gridx = 25;
        c.gridy = 2;
        add(furnished);

        this.add(new JLabel("Select preferred listing type:"));
        c.gridx = 30;
        c.gridy = 2;
        add(listingType);

        this.add(new JLabel("Listings:"));

        // Create a JPanel for the buttons to put in the JScrollPane
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        listingsScroll = new JScrollPane(new JLabel("Click Search to find listings"));
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
        this.add(listingsScroll, c);


        homeSearchBar.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped (KeyEvent e) {
                        HomeSearchState currentState = homesearchViewModel.getState();
                        currentState.setSearchBarInput(homeSearchBar.getText() + e.getKeyChar());
                        homesearchViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {

                    }
                }
        );
        searchButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == searchButton) {
                            // Determine whether search bar input is a city, address, or listing ID
                            String input = homesearchViewModel.getState().getSearchBarInput();
                            if (input == null) {} // if nothing was typed in the search bar
                            else if (input.matches("[0-9]+")) {
                                HomeSearchState currentState = homesearchViewModel.getState();
                                currentState.setId(homesearchViewModel.getState().getSearchBarInput());
                                homesearchViewModel.setState(currentState);
                            } else if (input.matches("[a-zA-Z\\s]+")) {
                                HomeSearchState currentState = homesearchViewModel.getState();
                                currentState.setCity(homesearchViewModel.getState().getSearchBarInput());
                                homesearchViewModel.setState(currentState);
                            } else if (input.matches(".+")) {
                                HomeSearchState currentState = homesearchViewModel.getState();
                                currentState.setAddress(homesearchViewModel.getState().getSearchBarInput());
                                homesearchViewModel.setState(currentState);
                            }

                            homesearchController.execute(homesearchViewModel.getState().getId(),
                                    homesearchViewModel.getState().getCity(), homesearchViewModel.getState().getAddress(),
                                    homesearchViewModel.getState().getNumRooms(), homesearchViewModel.getState().getPriceRange(),
                                    homesearchViewModel.getState().getNumBaths(), homesearchViewModel.getState().getWalkScore(),
                                    homesearchViewModel.getState().getFurnished(), homesearchViewModel.getState().getListingType());
                        }
                    }
                }
        );

        numRooms.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == numRooms) {
                            HomeSearchState currentState = homesearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String numRooms = (String) cb.getSelectedItem();
                            currentState.setNumRooms(numRooms);
                            homesearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        priceRange.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == priceRange) {
                            HomeSearchState currentState = homesearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String priceRange = (String) cb.getSelectedItem();
                            currentState.setPriceRange(priceRange);
                            homesearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        numBaths.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == numBaths) {
                            HomeSearchState currentState = homesearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String numBaths = (String) cb.getSelectedItem();
                            currentState.setNumBaths(numBaths);
                            homesearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        walkScore.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == walkScore) {
                            HomeSearchState currentState = homesearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String walkScore = (String) cb.getSelectedItem();
                            currentState.setWalkScore(walkScore);
                            homesearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        furnished.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == furnished) {
                            HomeSearchState currentState = homesearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String furnished = (String) cb.getSelectedItem();
                            currentState.setFurnished(furnished);
                            homesearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        listingType.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == listingType) {
                            HomeSearchState currentState = homesearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String listingType = (String) cb.getSelectedItem();
                            currentState.setListingType(listingType);
                            homesearchViewModel.setState(currentState);
                        }
                    }
                }
        );
    }

    public void actionPerformed(ActionEvent evt) {
        for (JButton button : listingButtons) {
            if (evt.getSource() == button) {
                listingController.execute(button.getName());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        // Updates the home page to add buttons according to the filtered listings
        if (!(homesearchViewModel.getState().getDisplayedListings() == null)) {
            listingButtons = new ArrayList<>();
            for (String id : homesearchViewModel.getState().getDisplayedListings().keySet()) {
                JButton listingButton = new JButton(homesearchViewModel.getState().getDisplayedListings().get(id));
                listingButton.setName(id);
                listingButton.addActionListener(this);
                listingButtons.add(listingButton);
            }
        }
        buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        for (JButton button : listingButtons) {
            buttonsPanel.add(button);
        }
        this.remove(listingsScroll);
        listingsScroll = new JScrollPane(buttonsPanel);
        listingsScroll.setPreferredSize(new Dimension(200, 200));
        this.add(listingsScroll);
        this.revalidate();
        this.repaint();
    }
}

