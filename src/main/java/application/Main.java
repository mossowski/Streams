package application;

import java.util.ArrayList;
import entities.User;
import service.UserService;

public class Main {

    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<User>();
        UserService userService = new UserService(users);

        userService.findUsersWithAddressesCountMoreThan(5);
        userService.findOldestPerson();
        userService.findUserWithLongestUsername();
        userService.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18();
        userService.getSortedPermissionsOfUsersWithNameStartingWith("costam");
        userService.getUsersAverageAge();
        userService.printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith("costam");
        userService.groupUsersByRole();
        userService.partitionUserByUnderAndOver18();
    }

}
