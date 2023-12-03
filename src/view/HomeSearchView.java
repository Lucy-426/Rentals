package view;

import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.saved.SavedState;
import interface_adapter.signup.SignupViewModel;
import interface_adapter.listing.ListingController;

import interface_adapter.CenterMap.CenterMapController;
import org.jdesktop.swingx.JXMapKit;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class HomeSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Search";

    private final HomeSearchViewModel homeSearchViewModel;

    public final JXMapKit jxMapKit = new JXMapKit();

    private final JTextField homeSearchBar = new JTextField(30);

    private JPanel buttons;

    private JButton searchButton;

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
    private final HomeSearchController homeSearchController;

    private final ListingController listingController;

    private final CenterMapController centerMapController;

    public HomeSearchView(HomeSearchController homeController, ListingController listingController, HomeSearchViewModel viewModel,
                          CenterMapController centerMapController) {
        this.homeSearchController = homeController;
        this.listingController = listingController;
        this.homeSearchViewModel = viewModel;
        this.centerMapController = centerMapController;
        homeSearchViewModel.addPropertyChangeListener(this);

        // for formatting
        setLayout(new GridBagLayout());

        JLabel title = new JLabel(homeSearchViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        buttons = new JPanel();

        JButton signUp = new JButton("Sign Up");
        buttons.add(signUp);
        JButton logIn = new JButton("Log In");
        buttons.add(logIn);

        signUp.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(signUp)) {
                            homeSearchController.displaySignupView();
                        }
                    }
                }
        );

        logIn.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logIn)) {
                            homeSearchController.displayLoginView();
                        }
                    }
                }
        );

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        LabelTextPanel searchBar = new LabelTextPanel(
                new JLabel(homeSearchViewModel.SEARCH_BAR_LABEL), homeSearchBar);
        searchButton = new JButton("Search");
        // Add action listener to the search button
        searchButton.addActionListener(this);

        // filter for number of rooms
        numRooms = new JComboBox<>(homeSearchViewModel.numRoomStrings);
        numRooms.setSelectedIndex(0);
        numRooms.addActionListener(this);

        priceRange = new JComboBox<>(homeSearchViewModel.priceRangeStrings);
        priceRange.setSelectedIndex(0);
        priceRange.addActionListener(this);

        numBaths = new JComboBox<>(homeSearchViewModel.numBathsStrings);
        numBaths.setSelectedIndex(0);
        numBaths.addActionListener(this);

        walkScore = new JComboBox<>(homeSearchViewModel.walkScoreStrings);
        walkScore.setSelectedIndex(0);
        walkScore.addActionListener(this);

        furnished = new JComboBox<>(homeSearchViewModel.furnishedStrings);
        furnished.setSelectedIndex(0);
        furnished.addActionListener(this);

        listingType = new JComboBox<>(homeSearchViewModel.listingTypeStrings);
        listingType.setSelectedIndex(0);
        listingType.addActionListener(this);

//        for formatting
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;


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

        // Displaying interactive map
        jxMapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        jxMapKit.setDataProviderCreditShown(true);
        jxMapKit.setZoom(5);
        jxMapKit.setAddressLocationShown(true);
        jxMapKit.setAddressLocation(homeSearchViewModel.startPosition);

        this.add(jxMapKit);

        // Signup + LogIn / Profile + LogOut Button Panel
        this.add(buttons);

        homeSearchBar.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped (KeyEvent e) {
                        HomeSearchState currentState = homeSearchViewModel.getState();
                        if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            currentState.setSearchBarInput(homeSearchBar.getText());
                        } else {
                            currentState.setSearchBarInput(homeSearchBar.getText() + e.getKeyChar());
                        }
                        homeSearchViewModel.setState(currentState);
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
                            String input = homeSearchViewModel.getState().getSearchBarInput();
                            if (input == null) {} // if nothing was typed in the search bar
                            else if (input.matches("[0-9]+")) {
                                HomeSearchState currentState = homeSearchViewModel.getState();
                                currentState.setId(homeSearchViewModel.getState().getSearchBarInput());
                                homeSearchViewModel.setState(currentState);
                            } else if (input.matches("[a-zA-Z\\s]+")) {
                                HomeSearchState currentState = homeSearchViewModel.getState();
                                currentState.setCity(homeSearchViewModel.getState().getSearchBarInput());
                                homeSearchViewModel.setState(currentState);
                            } else if (input.matches(".+")) {
                                HomeSearchState currentState = homeSearchViewModel.getState();
                                currentState.setAddress(homeSearchViewModel.getState().getSearchBarInput());
                                homeSearchViewModel.setState(currentState);
                            }

                            homeSearchController.execute(homeSearchViewModel.getState().getId(),
                                    homeSearchViewModel.getState().getCity(), homeSearchViewModel.getState().getAddress(),
                                    homeSearchViewModel.getState().getNumRooms(), homeSearchViewModel.getState().getPriceRange(),
                                    homeSearchViewModel.getState().getNumBaths(), homeSearchViewModel.getState().getWalkScore(),
                                    homeSearchViewModel.getState().getFurnished(), homeSearchViewModel.getState().getListingType());

                            centerMapController.execute(homeSearchViewModel.getState().getSearchBarInput());
                            jxMapKit.setAddressLocation(homeSearchViewModel.getState().getStartPosition());
                        }
                    }
                }
        );

        numRooms.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == numRooms) {
                            HomeSearchState currentState = homeSearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String numRooms = (String) cb.getSelectedItem();
                            currentState.setNumRooms(numRooms);
                            homeSearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        priceRange.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == priceRange) {
                            HomeSearchState currentState = homeSearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String priceRange = (String) cb.getSelectedItem();
                            currentState.setPriceRange(priceRange);
                            homeSearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        numBaths.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == numBaths) {
                            HomeSearchState currentState = homeSearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String numBaths = (String) cb.getSelectedItem();
                            currentState.setNumBaths(numBaths);
                            homeSearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        walkScore.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == walkScore) {
                            HomeSearchState currentState = homeSearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String walkScore = (String) cb.getSelectedItem();
                            currentState.setWalkScore(walkScore);
                            homeSearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        furnished.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == furnished) {
                            HomeSearchState currentState = homeSearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String furnished = (String) cb.getSelectedItem();
                            currentState.setFurnished(furnished);
                            homeSearchViewModel.setState(currentState);
                        }
                    }
                }
        );

        listingType.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == listingType) {
                            HomeSearchState currentState = homeSearchViewModel.getState();
                            JComboBox cb = (JComboBox) e.getSource();
                            String listingType = (String) cb.getSelectedItem();
                            currentState.setListingType(listingType);
                            homeSearchViewModel.setState(currentState);
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
        HomeSearchState currentState = homeSearchViewModel.getState();

        // Updates home page buttons depending on whether user is logged in
        if (currentState.getLoggedIn()) {
            this.remove(buttons);
            buttons = new JPanel();
            JButton profile = new JButton("Profile");
            buttons.add(profile);
            JButton logOut = new JButton("Log Out");
            buttons.add(logOut);

            profile.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(profile)) {
                                homeSearchController.displayProfile(homeSearchViewModel.getState().getSavedState());
                            }
                        }
                    }
            );

            logOut.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(logOut)) {
                                homeSearchController.logOut();
                            }
                        }
                    }
            );
        } else {
            this.remove(buttons);

            buttons = new JPanel();
            JButton signUp = new JButton("Sign Up");
            buttons.add(signUp);
            JButton logIn = new JButton("Log In");
            buttons.add(logIn);

            signUp.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(signUp)) {
                                homeSearchController.displaySignupView();
                            }
                        }
                    }
            );

            logIn.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(logIn)) {
                                homeSearchController.displayLoginView();
                            }
                        }
                    }
            );
        }

        // Updates the home page to add buttons according to the filtered listings
        if (!(homeSearchViewModel.getState().getDisplayedListings() == null)) {
            listingButtons = new ArrayList<>();
            for (String id : homeSearchViewModel.getState().getDisplayedListings().keySet()) {
                JButton listingButton = new JButton(homeSearchViewModel.getState().getDisplayedListings().get(id));
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
        this.add(buttons);
        this.revalidate();
        this.repaint();
    }
}

