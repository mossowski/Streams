package application;

import java.util.ArrayList;
import entities.User;
import service.UserService;

public class Main {

    public static void main(String[] args) {
        ArrayList<User> users = Data.getUsers();
        UserService userService = new UserService(users);

        userService.findUsersWithAddressesCountMoreThan(1);
        userService.findOldestPerson();
        userService.findUserWithLongestUsername();
        userService.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18();
        userService.getSortedPermissionsOfUsersWithNameStartingWith("Wies");
        userService.getUsersAverageAge();
        userService.printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith("L");
        userService.groupUsersByRole();
        userService.partitionUserByUnderAndOver18();
    }

}
