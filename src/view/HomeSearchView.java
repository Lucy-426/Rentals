package view;

import interface_adapter.homeSearch.HomeSearchController;
import interface_adapter.homeSearch.HomeSearchState;
import interface_adapter.homeSearch.HomeSearchViewModel;

import interface_adapter.search.CenterMapController;
import org.jdesktop.swingx.JXMapKit;
import org.jdesktop.swingx.mapviewer.DefaultWaypoint;
import org.jdesktop.swingx.mapviewer.GeoPosition;
import org.jdesktop.swingx.mapviewer.Waypoint;
import org.jdesktop.swingx.mapviewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class HomeSearchView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "home search";

    private final HomeSearchViewModel homesearchViewModel;

    public final JXMapKit jxMapKit = new JXMapKit();

    private final JTextField homeSearchBar = new JTextField(30);
    private JButton searchButton;

//    filters
    private JComboBox<String> numRooms;
    private JComboBox<String> priceRange;
    private JComboBox<String> numBaths;
    private JComboBox<String> walkScore;
    private JComboBox<String> furnished;
    private JComboBox<String> listingType;




    private JTextArea listingsArea;
    private JScrollPane listingsScroll;


    private final HomeSearchController homesearchController;

    private final CenterMapController centerMapController;

    public HomeSearchView(HomeSearchController controller, HomeSearchViewModel viewModel, CenterMapController centerMapController) {
        this.homesearchController = controller;
        this.homesearchViewModel = viewModel;
        this.centerMapController = centerMapController;
        homesearchViewModel.addPropertyChangeListener(this);

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

        walkScore = new JComboBox<>(homesearchViewModel.walkScoreStrings);
        walkScore.setSelectedIndex(0);
        walkScore.addActionListener(this);

        furnished = new JComboBox<>(homesearchViewModel.furnishedStrings);
        furnished.setSelectedIndex(0);
        furnished.addActionListener(this);

        listingType = new JComboBox<>(homesearchViewModel.listingTypeStrings);
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

        // Displaying interactive map
        jxMapKit.setDefaultProvider(JXMapKit.DefaultProviders.OpenStreetMaps);
        jxMapKit.setDataProviderCreditShown(true);
        jxMapKit.setZoom(5);
        jxMapKit.setAddressLocationShown(true);


    // Displaying waypoints
    // Note: this should be done in the add listings use case or
    // another file, but I'll leave it here for reference
//        Set<Waypoint> waypoints = new HashSet<>();
//        waypoints.add(new DefaultWaypoint(43.6669356, -79.3945384));
//        waypoints.add(new DefaultWaypoint(43.6634425, -79.3964002));
//        WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<>();
//        waypointPainter.setWaypoints(waypoints);


    // Add the waypoint painter to the map viewer
//        jxMapKit.getMainMap().setOverlayPainter(waypointPainter);


    // Set starting point at UofT
//        GeoPosition waypoint1 = new GeoPosition(43.6634425, -79.3964002);
        jxMapKit.setAddressLocation(homesearchViewModel.startPosition);

        GridBagConstraints rc = new GridBagConstraints();
        rc.gridx = 1;
        rc.gridy = 0;
        rc.gridheight = 7;
        rc.anchor = GridBagConstraints.EAST;
        add(jxMapKit, rc);


        homeSearchBar.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped (KeyEvent e) {
                        HomeSearchState currentState = homesearchViewModel.getState();
                        currentState.setAddress(homeSearchBar.getText() + e.getKeyChar());
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
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == searchButton) {

                            homesearchController.execute(homesearchViewModel.getState().getId(),
                                    homesearchViewModel.getState().getCity(), homesearchViewModel.getState().getAddress(),
                                    homesearchViewModel.getState().getNumRooms(), homesearchViewModel.getState().getPriceRange(),
                                    homesearchViewModel.getState().getNumBaths(), homesearchViewModel.getState().getWalkScore(),
                                    homesearchViewModel.getState().getFurnished(), homesearchViewModel.getState().getListingType());

                            centerMapController.execute(homesearchViewModel.getState().getAddress());
                            jxMapKit.setAddressLocation(homesearchViewModel.getState().getStartPosition());
                        }
                    }
                }
        );

        numRooms.addActionListener(
                new ActionListener() {
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


    // TODO: action performed and property change
    public void actionPerformed(ActionEvent e) {
        // System.out.println("action Performed");
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("property change");
    }
}

