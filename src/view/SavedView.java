package view;

import interface_adapter.saved.SavedController;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;
import use_case.saved.SavedOutputBoundary;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class SavedView extends JPanel implements ActionListener, PropertyChangeListener {

    public final String viewName = "Saved";

    private final SavedController savedController;
    private final SavedViewModel savedViewModel;

    private JPanel buttons;

    private JTextArea listingsArea;

    private JScrollPane listingsScroll;

    /**
     * A window with a title and a JButton.
     */
    public SavedView(SavedController savedController, SavedViewModel savedViewModel) {
        this.savedController = savedController;
        this.savedViewModel = savedViewModel;
        this.savedViewModel.addPropertyChangeListener(this);

        setLayout(new GridBagLayout());

        JLabel title = new JLabel("Profile View");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        this.add(title);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        GridBagConstraints c = new GridBagConstraints();

        System.out.println(savedViewModel.getState().getUsername());
        JLabel userGreeting = new JLabel("Hello " + savedViewModel.getState().getUsername() + "!");

        JLabel listings = new JLabel("Below are your saved listings:");
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

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(userGreeting);
        this.add(listings);
        add(listingsScroll, c);
        this.add(buttons);
    }

}