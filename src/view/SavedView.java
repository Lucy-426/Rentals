package view;

import use_case.signup.UserSignupDataAccessInterface;
import entity.Property;
import interface_adapter.saved.SavedController;
import interface_adapter.saved.SavedViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;

public class SavedView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Saved";

    private final SavedController savedController;
    private final SavedViewModel savedViewModel;

    private JPanel buttons;

    private ArrayList<JButton> listingButtons = new ArrayList<>();
    private JScrollPane listingsScroll;
    private JPanel buttonsPanel;

    private UserSignupDataAccessInterface userSignupDataAccessInterface;

    /**
     * A window with a title and a JButton.
     */
    public SavedView(SavedController savedController, SavedViewModel savedViewModel,
                     UserSignupDataAccessInterface userSignupDataAccessInterface) {
        this.savedController = savedController;
        this.savedViewModel = savedViewModel;
        this.savedViewModel.addPropertyChangeListener(this);
        this.userSignupDataAccessInterface = userSignupDataAccessInterface;

        this.removeAll();

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel("Profile View");

        buttons = new JPanel();
        JButton back = new JButton("Back");
        buttons.add(back);
        JButton logOut = new JButton("Log Out");
        buttons.add(logOut);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            savedController.displayHome();
                        }
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            savedController.logOut();
                        }
                    }
                }
        );

        c.fill = GridBagConstraints.HORIZONTAL;
        title.setFont(new Font("Arial", 1, 30));
        title.setSize(200, 60);
        c.gridx = 0;
        c.gridy = 0;
        this.add(title, c);

        // Updates the profile page to add buttons according to the saved listings
        Map<String, Property> savedListings = userSignupDataAccessInterface.getUserProperties(savedViewModel.getState().getUsername());
        if (!(savedListings == null)) {
            listingButtons = new ArrayList<>();
            for (String id : savedListings.keySet()) {
                JButton listingButton = new JButton(savedListings.get(id).getAddress());
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
        listingsScroll = new JScrollPane(buttonsPanel);
        listingsScroll.setPreferredSize(new Dimension(400, 200));
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 0;
        this.add(listingsScroll, c);
        this.add(buttons);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        for (JButton button : listingButtons) {
            if (evt.getSource() == button) {
                savedController.execute(button.getName());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        this.removeAll();
        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        buttons = new JPanel();
        JButton back = new JButton("Back");
        buttons.add(back);
        JButton logOut = new JButton("Log Out");
        buttons.add(logOut);

        back.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            savedController.displayHome();
                        }
                    }
                }
        );

        logOut.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(logOut)) {
                            savedController.logOut();
                        }
                    }
                }
        );


        JLabel userGreeting = new JLabel("Hello " + savedViewModel.getState().getUsername() + "!");

        c.fill = GridBagConstraints.HORIZONTAL;
        userGreeting.setFont(new Font("Arial", 1, 30));
        userGreeting.setSize(200, 60);
        c.gridx = 0;
        c.gridy = 1;
        this.add(userGreeting, c);

        JLabel listings = new JLabel("Below are your saved listings:");
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 0;
        this.add(listings, c);
        c.insets = new Insets(0, 20, 0, 0);

        // Updates the profile page to add buttons according to the filtered listings
        Map<String, Property> savedListings = userSignupDataAccessInterface.getUserProperties(savedViewModel.getState().getUsername());
        if (!(savedListings == null)) {
            listingButtons = new ArrayList<>();
            for (String id : savedListings.keySet()) {
                JButton listingButton = new JButton(savedListings.get(id).getAddress());
                listingButton.setName(id);
                listingButton.addActionListener(this);
                listingButtons.add(listingButton);
            }

            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
            for (JButton button : listingButtons) {
                buttonsPanel.add(button);
            }
        } else {
            buttonsPanel = new JPanel();
            buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));
        }

        this.remove(listingsScroll);
        listingsScroll = new JScrollPane(buttonsPanel);
        listingsScroll.setPreferredSize(new Dimension(400, 200));
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 0;
        this.add(listingsScroll, c);

        this.add(buttons);
        this.revalidate();
        this.repaint();
    }

}