package main;

import choice.ChoiceStep1_UserAction;
import userInterface.LoginUI;
import java.awt.event.ActionEvent;
import static queries.UserQueries.login;

public class School {

    public static void main(String[] args) {

        LoginUI ui = new LoginUI();
        ui.setVisible(true);
        ui.loginButton.addActionListener((ActionEvent e) -> {
            ui.setVisible(false);
            login(ui.usernameTextField.getText(), ui.passwordTextField.getText());

            ChoiceStep1_UserAction.chooseUserAction();
            System.exit(0);
        });
    }

}
