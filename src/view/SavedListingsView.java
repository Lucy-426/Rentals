package view;

import interface_adapter.ViewManagerModel;
import interface_adapter.homeSearch.HomeSearchViewModel;
import interface_adapter.listing.ListingViewModel;
import interface_adapter.savedListings.SavedController;
import interface_adapter.savedListings.SavedViewModel;
import main.app.HomeSearchUseCaseFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import view.HomeSearchView;
import view.ListingView;


public class SavedListingsView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "saved listings";

    private final SavedViewModel savedViewModel;

    private final JTextField homeSearchBar = new JTextField(30);
    private JButton searchButton;

    //    filters

    private JComboBox<String> numRooms;
    private JComboBox<String> priceRange;
    private JComboBox<String> numBaths;
    private JComboBox<String> walkScore;
    private JComboBox<String> furnished;
    private JComboBox<String> listingType;
    private JTextArea savedListingsArea;
    private JScrollPane listingsScroll;
    private ArrayList<JButton> listingButtons = new ArrayList<>();

    private final SavedController savedController;

    public SavedListingsView(SavedController savedController, SavedViewModel viewModel, SavedViewModel savedViewModel, JButton searchButton, JComboBox<String> numRooms) {
        this.savedViewModel = savedViewModel;
        this.savedController = savedController;
        savedViewModel.addPropertyChangeListener(this);

        //        for formatting
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(savedViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        savedListingsArea = new JTextArea(20, 20);
        savedListingsArea.setEditable(false); // if you want to make it read-only

        // Create a JPanel for the buttons to put in the JScrollPane
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        // TODO: make it so listings in buttons aren't hard coded, figure out how to have default properties
        JButton listing1 = new JButton("listing1");
        listing1.addActionListener(this);
        JButton listing2 = new JButton("listing2");
        listing2.addActionListener(this);
        JButton listing3 = new JButton("listing3");
        listing3.addActionListener(this);
        JButton listing4 = new JButton("listing4");
        listing4.addActionListener(this);
        listingButtons.add(listing1);
        listingButtons.add(listing2);
        listingButtons.add(listing3);
        listingButtons.add(listing4);

        for (JButton button : listingButtons) {
            buttonsPanel.add(button);
        }
        // Create a JScrollPane and add the JPanel of buttons to it
        listingsScroll = new JScrollPane(buttonsPanel);
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
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public static void main(String[] args) {
//        JFrame application = new JFrame("Got Room?");
//        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//
//        CardLayout cardLayout = new CardLayout();
//
//        // The various View objects. Only one view is visible at a time.
//        JPanel views = new JPanel(cardLayout);
//        application.add(views);
//
//
//        ViewManagerModel viewManagerModel = new ViewManagerModel();
//        new ViewManager(views, cardLayout, viewManagerModel);
//
//
//        SavedViewModel savedViewModel = new SavedViewModel("saved listings view");
//        ListingViewModel listingViewModel = new ListingViewModel();
//
////        SavedListingsView savedListingsView = HomeSearchUseCaseFactory.create(viewManagerModel, homeSearchViewModel, listingViewModel);
////        views.add(savedListingsView, savedListingsView.viewName);
////
////        ListingView listingView = HomeSearchUseCaseFactory.createListingView(viewManagerModel, savedViewModel, listingViewModel);
////        views.add(listingView, listingView.viewName);
////
////        viewManagerModel.setActiveView(savedListingsView.viewName);
////        viewManagerModel.firePropertyChanged();
//
//
//        application.pack();
//        application.setVisible(true);


        // Create instances of required components
        SavedController savedController = new SavedController();
        SavedViewModel viewModel = new SavedViewModel("saved listings view");
        SavedViewModel savedViewModel = new SavedViewModel("saved?");
        JButton searchButton = new JButton("Search");
        JComboBox<String> numRooms = new JComboBox<>();

        // Create an instance of SavedListingsView
        SavedListingsView savedListingsView = new SavedListingsView(savedController, viewModel, savedViewModel, searchButton, numRooms);

        // Create a JFrame and add the SavedListingsView to it
        JFrame frame = new JFrame("Saved Listings");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(savedListingsView);
        frame.pack();
        frame.setVisible(true);
    }
}
