package view.Panels;

import javax.swing.*;
import java.awt.*;

public class LoginFormPanel extends JPanel {
    private JLabel usernameLabel,passwordLabel;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public JTextField getUsernameField() {
        return usernameField;
    }

    public void setUsernameField(JTextField usernameField) {
        this.usernameField.setText(usernameField.getText());
    }

    public void setPasswordField(JTextField passwordField) {
        this.passwordField.setText(passwordField.getText());
    }

    public JPasswordField getPasswordField() {
        return passwordField;
    }


    public LoginFormPanel(){
        usernameLabel=new JLabel("Username :");
        add(usernameLabel);
        usernameField=new JTextField(20);
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        add(usernameField);
        passwordLabel=new JLabel("Password :");
        add(passwordLabel);
        passwordField=new JPasswordField(20);
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        add(passwordField);
        usernameLabel.setForeground(Color.white);
        passwordLabel.setForeground(Color.white);
        setBackground(new Color(102, 255, 0));
    }
}
