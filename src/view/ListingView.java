package view;

import entity.Listing;
import interface_adapter.listing.ListingController;
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

    private final ListingViewModel listingViewModel;
    private final ListingController listingController;

    public ListingView(ListingController controller, ListingViewModel viewModel) {
        this.listingController = controller;
        this.listingViewModel = viewModel;
        listingViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        JLabel title = new JLabel(ListingViewModel.TITLE_LABEL);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        back = new JButton(ListingViewModel.BACK_BUTTON_LABEL);
        back.addActionListener(this);
        buttons.add(back);

        // TODO: change this
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        JLabel name = new JLabel("Listing View");
        name.setFont(new Font("Arial", 1, 30));
        c.insets = new Insets(0, 20, 0, 20);
        c.gridx = 0;
        c.gridy = 0;
        this.add(name, c);
        Font subText = new Font("Arial", 1, 25);
        JLabel detailTitle = new JLabel("Details:");
        detailTitle.setFont(subText);
        c.gridx = 0;
        c.gridy = 1;
        this.add(detailTitle, c);
        Font normalText = new Font("Arial", 0, 15);
        JLabel rentalType = new JLabel("Address: " + listingViewModel.getState().getAddress());
        rentalType.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 2;
        this.add(rentalType, c);
        JLabel price = new JLabel("Price (USD): $" + listingViewModel.getState().getPrice());
        price.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 3;
        this.add(price, c);
        JLabel walkScore = new JLabel("Walk Score: " + listingViewModel.getState().getWalkScore());
        walkScore.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 4;
        this.add(walkScore, c);
        JLabel numBed = new JLabel("Number of Bedrooms: " + listingViewModel.getState().getNumRooms());
        numBed.setFont(normalText);
        c.ipadx = 20;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 2;
        this.add(numBed, c);
        JLabel numBath = new JLabel("Number of Bathrooms: " + listingViewModel.getState().getNumBaths());
        numBath.setFont(normalText);
        c.ipadx = 20;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 3;
        this.add(numBath, c);
        JLabel furnished = new JLabel("Furnished: " + listingViewModel.getState().getFurnished());
        furnished.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 4;
        this.add(furnished, c);
        JLabel recommendations = new JLabel("You may also like: ");
        recommendations.setFont(new Font("Arial", 0, 20));
        c.ipadx = 20;
        c.gridx = 2;
        c.gridy = 1;
        this.add(recommendations, c);

        JPanel buttonsPanel = new JPanel();
        GridLayout gridlayout = new GridLayout(3, 1);
        buttonsPanel.setLayout(gridlayout);

        // TODO: fix to actual listings
        JButton listing1 = new JButton("Listing 1");
        listing1.addActionListener(this);
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 4;
        c.ipady = 60;
        JButton listing2 = new JButton("Listing 2");
        listing2.addActionListener(this);
        JButton listing3 = new JButton("Listing 3");
        listing3.addActionListener(this);
        listingButtons.add(listing1);
        listingButtons.add(listing2);
        listingButtons.add(listing3);

        for (JButton button : listingButtons) {
            buttonsPanel.add(button);
        }

        JScrollPane listingScroll = new JScrollPane(buttonsPanel);
        listingScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(listingScroll, c);


        c.gridx = 2;
        c.gridy = 7;
        c.ipady = 30;
        this.add(back, c);

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
                System.out.println("listing button");
            }
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
