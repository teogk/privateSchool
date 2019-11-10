package choice;

import static choice.ChoiceStep2_Capabilities.*;
import model.User;

public class ChoiceStep1_UserAction {

    public static void chooseUserAction() {

        User user = User.getInstance();

        switch (user.getRoleID()) {
            case 1:
                System.out.println("Hello Head Master " + user.getFirstname());
                headMasterCapabilities();
                break;
            case 2:
                System.out.println("Hello Trainer " + user.getFirstname());
                trainerCapabilities();
                break;
            case 3:
                System.out.println("Hello Student " + user.getFirstname());
                studentCapabilities();
                break;
            default:
                System.out.println("Try again");
                break;
        }
    }

}
