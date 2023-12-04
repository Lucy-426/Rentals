package view;

import interface_adapter.listing.ListingController;
import interface_adapter.listing.ListingState;
import interface_adapter.listing.ListingViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Listing";
    private JButton back;

    private ArrayList<JButton> listingButtons = new ArrayList<>();

    private JPanel buttonsPanel;

    private JPanel buttons;
    private JScrollPane listingScroll;

    private JButton recommendation1 = new JButton();

    private JButton recommendation2 = new JButton();

    private JButton recommendation3 = new JButton();

    private final ListingViewModel listingViewModel;
    private final ListingController listingController;

    // Labels for the property attributes
    private JLabel id = new JLabel();
    private JLabel city = new JLabel();
    private JLabel address = new JLabel();
    private JLabel numRooms = new JLabel();
    private JLabel price = new JLabel();
    private JLabel numBaths = new JLabel();
    private JLabel walkScore = new JLabel();
    private JLabel furnished = new JLabel();
    private JLabel listingType = new JLabel();


    public ListingView(ListingController controller, ListingViewModel viewModel) {
        this.listingController = controller;
        this.listingViewModel = viewModel;
        listingViewModel.addPropertyChangeListener(this);

        buttons = new JPanel();
        this.add(buttons);


        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        back = new JButton(ListingViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(this);


        c.fill = GridBagConstraints.HORIZONTAL;
        address.setFont(new Font("Arial", 1, 30));
        address.setSize(200, 60);
        c.gridx = 0;
        c.gridy = 0;
        this.add(address, c);

        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 0;
        this.add(new JLabel("ID: "), c);
        c.insets = new Insets(0, 20, 0, 0);
        this.add(id, c);
        c.insets = new Insets(0, 0, 0, 0);

        c.gridy = 2;
        this.add(new JLabel("\n"), c);

        c.gridy = 3;
        this.add(new JLabel("City: "), c);
        c.insets = new Insets(0, 35, 0, 0);
        this.add(city, c);
        c.insets = new Insets(0, 0, 0, 0);

        c.gridy = 4;
        this.add(new JLabel("\n"), c);

        c.gridy = 5;
        this.add(new JLabel("Price (USD): $"), c);
        c.insets = new Insets(0, 85, 0, 0);
        this.add(price, c);
        c.insets = new Insets(0, 0, 0, 0);

        c.gridy = 6;
        this.add(new JLabel("\n"), c);

        c.gridy = 7;
        this.add(new JLabel("Listing type: "), c);
        c.gridy = 8;
        this.add(listingType, c);

        c.gridy = 1;
        c.insets = new Insets(0, 300, 0, 0);
        this.add(new JLabel("Bedrooms: "), c);
        c.insets = new Insets(0, 370, 0, 0);
        this.add(numRooms, c);

        c.gridy = 2;
        this.add(new JLabel("\n"), c);

        c.gridy = 3;
        c.insets = new Insets(0, 300, 0, 0);
        this.add(new JLabel("Bathrooms: "), c);
        c.insets = new Insets(0, 375, 0, 0);
        this.add(numBaths, c);

        c.gridy = 4;
        this.add(new JLabel("\n"), c);

        c.gridy = 5;
        c.insets = new Insets(0, 300, 0, 0);
        this.add(new JLabel("Walk Score: "), c);
        c.insets = new Insets(0, 375, 0, 0);
        this.add(walkScore, c);

        c.gridy = 6;
        this.add(new JLabel("\n"), c);

        c.gridy = 7;
        c.insets = new Insets(0, 300, 0, 0);
        this.add(new JLabel("Furnished: "), c);
        c.insets = new Insets(0, 370, 0, 0);
        this.add(furnished, c);
        c.insets = new Insets(0, 0, 0, 0);

        buttonsPanel = new JPanel();
        GridLayout gridlayout = new GridLayout(3, 1);
        buttonsPanel.setLayout(gridlayout);

        recommendation1.addActionListener(this);
        recommendation2.addActionListener(this);
        recommendation3.addActionListener(this);
        listingButtons.add(recommendation1);
        listingButtons.add(recommendation2);
        listingButtons.add(recommendation3);

        for (JButton button : listingButtons) {
            buttonsPanel.add(button);
        }

        c.gridy = 9;
        this.add(new JLabel("\n"), c);

        JLabel recommendations = new JLabel("You may also like: ");
        recommendations.setFont(new Font("Arial", 1, 15));
        c.gridx = 0;
        c.gridy = 10;
        c.insets = new Insets(0, 125, 0, 0);
        this.add(recommendations, c);
        c.insets = new Insets(0, 0, 0, 0);

        listingScroll = new JScrollPane(buttonsPanel);
        listingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        c.gridy = 11;
        c.gridheight = 4;
        c.ipady = 60;
        this.add(listingScroll, c);

        this.add(back);


        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == back) {
                            listingController.switchView();
                        }
                    }
                }
        );

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (JButton button : listingButtons) {
            if (e.getSource() == button) {
                listingController.execute(button.getName());
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        ListingState state = listingViewModel.getState();
        id.setText(state.getId());
        city.setText(state.getCity());
        address.setText(state.getAddress());
        numRooms.setText(state.getNumRooms());
        price.setText(state.getPrice());
        numBaths.setText(state.getNumBaths());
        walkScore.setText(state.getWalkScore());
        furnished.setText(state.getFurnished());
        listingType.setText(state.getListingType());

        if (state.getRecommendations() != null) {
            ArrayList<String> buttonNames = new ArrayList<>(state.getRecommendations().values());
            ArrayList<String> idNames = new ArrayList<>(state.getRecommendations().keySet());

            recommendation1.setText(buttonNames.get(0));
            recommendation1.setName(idNames.get(0));
            recommendation2.setText(buttonNames.get(1));
            recommendation2.setName(idNames.get(1));
            recommendation3.setText(buttonNames.get(2));
            recommendation3.setName(idNames.get(2));
        }

        ListingState currentState = listingViewModel.getState();

        // Updates home page buttons depending on whether user is logged in
        if (currentState.isLoggedIn()) {
            this.remove(buttons);
            buttons = new JPanel();
            JButton save = new JButton("Save Listing");
            buttons.add(save);

            save.addActionListener(
                    new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            if (evt.getSource().equals(save)) {
                                listingController.saveListing(listingViewModel.getState().getUsername(),
                                        listingViewModel.getState().getId());
                                if (state.getSaveMsg() != null) {
                                    JOptionPane.showMessageDialog(buttons, state.getSaveMsg());
                                }
                            }
                        }
                    }
            );
        } else {
            this.remove(buttons);
            buttons = new JPanel();
        }
        this.add(buttons);
        this.revalidate();
        this.repaint();
    }
}
