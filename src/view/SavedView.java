package view;

import interface_adapter.login.LoginController;
import interface_adapter.login.LoginState;
import interface_adapter.login.LoginViewModel;
import interface_adapter.saved.SavedController;
import interface_adapter.saved.SavedState;
import interface_adapter.saved.SavedViewModel;

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

    /**
     * The username chosen by the user
     */
    final JTextField usernameInputField = new JTextField(15);
    private final JLabel usernameErrorField = new JLabel();
    /**
     * The password
     */
    final JPasswordField passwordInputField = new JPasswordField(15);
    private final JLabel passwordErrorField = new JLabel();

    /**
     * A window with a title and a JButton.
     */
    public SavedView(SavedController savedController, SavedViewModel savedViewModel) {
        this.savedController = savedController;
        this.savedViewModel = savedViewModel;
        this.savedViewModel.addPropertyChangeListener(this);

        JLabel title = new JLabel("Login Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel usernameInfo = new LabelTextPanel(
                new JLabel("Username"), usernameInputField);
        LabelTextPanel passwordInfo = new LabelTextPanel(
                new JLabel("Password"), passwordInputField);


        usernameInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SavedState currentState = savedViewModel.getState();
                        if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            currentState.setUsername(usernameInputField.getText());
                        } else {
                            currentState.setUsername(usernameInputField.getText() + e.getKeyChar());
                        }
                        savedViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        passwordInputField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        SavedState currentState = savedViewModel.getState();
                        char[] password = passwordInputField.getPassword();
                        String text_pass = "";
                        for(char character: password) {
                            text_pass += character;
                        }
                        if (e.getExtendedKeyCode() == KeyEvent.VK_BACK_SPACE) {
                            currentState.setPassword(text_pass);
                        } else {
                            currentState.setPassword(text_pass + e.getKeyChar());
                        }
                        savedViewModel.setState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(usernameInfo);
        this.add(usernameErrorField);
        this.add(passwordInfo);
        this.add(passwordErrorField);
    }

    /**
     * React to a button click that results in evt.
     */
    public void actionPerformed(ActionEvent evt) {
        System.out.println("Click " + evt.getActionCommand());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        SavedState state = (SavedState) evt.getNewValue();
        setFields(state);
        if (state.getUsernameError() != null) {
            JOptionPane.showMessageDialog(this, state.getUsernameError());
        }
    }

    private void setFields(SavedState state) {
        usernameInputField.setText(state.getUsername());
        passwordInputField.setText(state.getPassword());
    }

}