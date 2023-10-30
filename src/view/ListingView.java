package view;

import entity.Property;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

public class ListingView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewname = "Listing";
    private final JButton back;

    public ListingView(){
//        JLabel title = new JLabel(ListingView.TITLE_LABEL);
        JLabel title = new JLabel("Listing View");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        JPanel buttons = new JPanel();
//        back = new JButton(SignupViewModel.SIGNUP_BUTTON_LABEL);
        back = new JButton("Back");
        buttons.add(back);
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    public static void main(String[] args) {
        // Build the main program window, the main panel containing the
        // various cards, and the layout, and stitch them together.

        // The main application window.
        JFrame application = new JFrame("Listing View");
        application.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        CardLayout cardLayout = new CardLayout();

        // The various View objects. Only one view is visible at a time.
        JPanel views = new JPanel(cardLayout);
        application.add(views);

        application.pack();
        application.setVisible(true);

        Property propTest = new Property("Apartment",60, 350, 8, 3, 2,
                300, true, false, "John Smith 4373294732");

        JPanel panel = new JPanel();
        GridBagLayout gridbag = new GridBagLayout();
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.5;
        panel.setLayout(gridbag);
        JLabel title = new JLabel("Listing Views");
        title.setFont(new Font("Arial", 1, 30));
        c.insets = new Insets(0,20,0,20);
        c.gridx = 0;
        c.gridy = 0;
        panel.add(title, c);
        Font subText = new Font("Arial", 1, 25);
        JLabel detailTitle = new JLabel("Details:");
        detailTitle.setFont(subText);
        c.gridx = 0;
        c.gridy = 1;
        panel.add(detailTitle, c);
        Font normalText = new Font("Arial", 0, 15);
        JLabel rentalType = new JLabel("Rental Type: " + propTest.getRentalType());
        rentalType.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 2;
        panel.add(rentalType, c);
        JLabel daysAgo = new JLabel("Posted " + propTest.getDaysListedAgo() + " days ago");
        daysAgo.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 3;
        panel.add(daysAgo, c);
        JLabel price = new JLabel("Price (USD): $" + propTest.getPrice() + "/month");
        price.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 4;
        panel.add(price, c);
        JLabel walkScore = new JLabel("Walk Score: " + propTest.getWalkScore());
        walkScore.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 5;
        panel.add(walkScore, c);
        JLabel squareFeet = new JLabel("Square Feet: " + propTest.getSquareFeet());
        squareFeet.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 0;
        c.gridy = 6;
        panel.add(squareFeet, c);
        JLabel numBed = new JLabel("Number of Bedrooms: " + propTest.getNumBed());
        numBed.setFont(normalText);
        c.ipadx = 20;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 2;
        panel.add(numBed, c);
        JLabel numBath = new JLabel("Number of Bathrooms: " + propTest.getNumBath());
        numBath.setFont(normalText);
        c.ipadx = 20;
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 3;
        panel.add(numBath, c);
        JLabel furnished = new JLabel("Furnished: " + propTest.isFurnished());
        furnished.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 4;
        panel.add(furnished, c);
        JLabel parking = new JLabel("Parking: " + propTest.isParking());
        parking.setFont(normalText);
        c.weighty = 0.3;
        c.gridx = 1;
        c.gridy = 5;
        panel.add(parking, c);
        JLabel contact = new JLabel("Contact: " + propTest.getContact());
        contact.setFont(normalText);
        c.insets = new Insets(0,20,0,20);
        c.gridx = 2;
        c.gridy = 0;
        panel.add(contact, c);
        JLabel recommendations = new JLabel("You may also like: ");
        recommendations.setFont(new Font("Arial", 0, 20));
        c.ipadx = 20;
        c.gridx = 2;
        c.gridy = 1;
        panel.add(recommendations,c);
        JButton listing1 = new JButton("Listing 1");
        c.gridx = 2;
        c.gridy = 2;
        c.gridheight = 2;
        c.ipady = 60;
        panel.add(listing1, c);
        JButton listing2 = new JButton("Listing 2");
        c.gridx = 2;
        c.gridy = 4;
        c.gridheight = 2;
        panel.add(listing2, c);
        JButton button = new JButton("Back");
        c.gridx = 2;
        c.gridy = 7;
        c.ipady = 30;
        panel.add(button, c);

        application.add(panel);
    }
}
