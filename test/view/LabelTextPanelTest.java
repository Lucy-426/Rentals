package view;

import org.junit.jupiter.api.Test;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;

class LabelTextPanelTest {

    @Test
    void init(){
        JLabel jLabel = new JLabel("test");
        JTextField jTextField = new JTextField();
        LabelTextPanel labelTextPanel = new LabelTextPanel(jLabel, jTextField);
    }

}