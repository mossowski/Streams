package service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import entities.Address;
import entities.Permission;
import entities.Person;
import entities.Role;
import entities.User;

public class UserServiceTest {

    @Test
    public void test() {
        ArrayList<User> users = new ArrayList<User>();

        // Role User
        Role roleUser = new Role().setName("user");
        List<Permission> userPermissions = Arrays.asList(
                new Permission().setName("create"),
                new Permission().setName("update"), 
                new Permission().setName("delete"));
        roleUser.setPermissions(userPermissions);

        // Role Admin
        Role roleAdmin = new Role().setName("admin");
        List<Permission> adminPermissions = Arrays.asList(
                        new Permission().setName("ban"), 
                        new Permission().setName("banThemAll"));
        roleAdmin.setPermissions(adminPermissions);

        // Address 1
        Address address1 = new Address().setStreetName("ul. Dluga").setHouseNumber(4).setFlatNumber(5).setCity("Gdansk")
                .setPostCode("80-932").setCountry("Polska");
        // Address 2
        Address address2 = new Address().setStreetName("ul. Dluga").setHouseNumber(4).setFlatNumber(5).setCity("Gdansk")
                .setPostCode("80-932").setCountry("Polska");
        // Address 3
        Address address3 = new Address().setStreetName("ul. Dluga").setHouseNumber(4).setFlatNumber(5).setCity("Gdansk")
                .setPostCode("80-932").setCountry("Polska");

        // ------------------------------------------------------------------------------

        // Person 1
        Person person1 = new Person();
        person1.setName("Mietek");
        person1.setSurname("Kowalski");
        List<String> phoneNumbers1 = Arrays.asList("678435678", "623412278", "678545468");
        person1.setPhoneNumbers(phoneNumbers1);
        List<Address> addresses1 = Arrays.asList(address1);
        person1.setAddresses(addresses1);
        person1.setRole(roleUser);
        person1.setAge(21);

        // User 1
        User user1 = new User();
        user1.setName("mieciu123");
        user1.setPassword("haselko");
        user1.setPersonDetails(person1);

        users.add(user1);

        // ------------------------------------------------------------------------------

        // Person 2
        Person person2 = new Person();
        person2.setName("Wieslaw");
        person2.setSurname("Waplowski");
        List<String> phoneNumbers2 = Arrays.asList("898466678", "833412278", "888545468");
        person2.setPhoneNumbers(phoneNumbers2);
        List<Address> addresses2 = Arrays.asList(address1, address2);
        person2.setAddresses(addresses2);
        person2.setRole(roleUser);
        person2.setAge(15);

        // User 2
        User user2 = new User();
        user2.setName("luzny");
        user2.setPassword("wiesiek");
        user2.setPersonDetails(person2);

        users.add(user2);

        // ------------------------------------------------------------------------------

        // Person 3
        Person person3 = new Person();
        person3.setName("Stas");
        person3.setSurname("Lolek");
        List<String> phoneNumbers3 = Arrays.asList("444466678", "444413378");
        person3.setPhoneNumbers(phoneNumbers3);
        List<Address> addresses3 = Arrays.asList(address3);
        person3.setAddresses(addresses3);
        person3.setRole(roleAdmin);
        person3.setAge(17);

        // User 3
        User user3 = new User();
        user3.setName("stasiekthegod");
        user3.setPassword("haslo");
        user3.setPersonDetails(person3);

        users.add(user3);

        // ------------------------------------------------------------------------------

        // Person 4
        Person person4 = new Person();
        person4.setName("Andrzej");
        person4.setSurname("Polak");
        List<String> phoneNumbers4 = Arrays.asList("554466678", "554414478");
        person4.setPhoneNumbers(phoneNumbers4);
        List<Address> addresses4 = Arrays.asList(address1, address3);
        person4.setAddresses(addresses4);
        person4.setRole(roleUser);
        person4.setAge(44);

        // User 4
        User user4 = new User();
        user4.setName("andrzeju");
        user4.setPassword("niedenerwujsie");
        user4.setPersonDetails(person4);

        users.add(user4);

        // ------------------------------------------------------------------------------

        // Person 5
        Person person5 = new Person();
        person5.setName("Janusz");
        person5.setSurname("Admin");
        List<String> phoneNumbers5 = Arrays.asList("115562678", "775515578");
        person5.setPhoneNumbers(phoneNumbers5);
        List<Address> addresses5 = Arrays.asList(address1);
        person5.setAddresses(addresses5);
        person5.setRole(roleAdmin);
        person5.setAge(32);

        // User 5
        User user5 = new User();
        user5.setName("janusz");
        user5.setPassword("czlowieknoz");
        user5.setPersonDetails(person5);

        users.add(user5);

        // ------------------------------------------------------------------------------

        // Person 6
        Person person6 = new Person();
        person6.setName("Bogdan");
        person6.setSurname("Lysy");
        List<String> phoneNumbers6 = Arrays.asList("996662671", "996616671");
        person6.setPhoneNumbers(phoneNumbers6);
        List<Address> addresses6 = Arrays.asList(address1, address2, address3);
        person6.setAddresses(addresses6);
        person6.setRole(roleUser);
        person6.setAge(27);

        // User 6
        User user6 = new User();
        user6.setName("bogdan");
        user6.setPassword("kibol123");
        user6.setPersonDetails(person6);

        users.add(user6);

        // --------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------
        // --------------------------------------------------------------------------------------

        UserService userService = new UserService(users);

        // Test 1 findUsersWithAddressesCountMoreThan
        int addressParam = 1;
        System.out.println("Test 1 findUsersWithAddressesCountMoreThan " + addressParam);
        List<User> usersWithAddressesCountMoreThan = userService.findUsersWithAddressesCountMoreThan(addressParam);
        int counter = 0;
        for (User u : users) {
            int addressesCount = u.getPersonDetails().getAddresses().size();
            if (addressesCount > addressParam) {
                System.out.print(u.getName() + " ");
                counter++;
            }
        }
        System.out.println();
        assertEquals(usersWithAddressesCountMoreThan.size(), counter);

        // Test 2 findOldestPerson
        System.out.println("Test 2 findOldestPerson");
        Person oldestPerson = userService.findOldestPerson();
        System.out.println("Oldest person    : " + oldestPerson.getName() + " " + oldestPerson.getSurname());
        System.out.println("Age              : " + oldestPerson.getAge());
        Person oldest = null;
        int oldestAge = 0;
        for (User user : users) {
            int personAge = user.getPersonDetails().getAge();
            if (personAge > oldestAge) {
                oldestAge = personAge;
                oldest = user.getPersonDetails();
            }
        }
        assertEquals(oldestPerson, oldest);

        // Test 3 findUserWithLongestUsername
        System.out.println("Test 3 findUserWithLongestUsername");
        User userWithLongestUsername = userService.findUserWithLongestUsername();
        System.out.println("Longest username : " + userWithLongestUsername.getName());
        assertEquals(userWithLongestUsername, user3);

        // Test 4 getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18
        System.out.println("Test 4 getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18");
        String names = userService.getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18();
        System.out.println("Names            : " + names);
        assertEquals(names, "Mietek Kowalski,Andrzej Polak,Janusz Admin,Bogdan Lysy");

        // Test 5 getSortedPermissionsOfUsersWithNameStartingWith
        String prefix = "bog";
        System.out.println("Test 5 getSortedPermissionsOfUsersWithNameStartingWith " + prefix);
        List <String> sortedPermissions = userService.getSortedPermissionsOfUsersWithNameStartingWith(prefix);
        List <String> sorted = Arrays.asList("create", "delete", "update");
        System.out.println("Should print     : create delete update");
        System.out.print("Prints           : ");
        for (String s : sortedPermissions) {
            System.out.print(s + " ");
        }
        System.out.println();
        assertEquals(sortedPermissions, sorted);

        // Test 6 getUsersAverageAge
        System.out.println("Test 6 getUsersAverageAge");
        double averageAge = userService.getUsersAverageAge();
        System.out.println("Average age      : " + averageAge);
        double average = 0;
        for (User u : users) {
            average += u.getPersonDetails().getAge();
        }
        average /= users.size();
        assertEquals(averageAge, average, 0);

        // Test 7 printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith
        System.out.println("Test 7 printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith");
        System.out.println("Should print     : BAN BANTHEMALL CREATE UPDATE DELETE");
        System.out.print("Prints           : ");
        userService.printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith("L");
        System.out.println();

        // Test 8 groupUsersByRole
        System.out.println("Test 8 groupUsersByRole");
        Map<Role, List<User>> usersByRole = userService.groupUsersByRole();
        for (Map.Entry<Role, List<User>> entry : usersByRole.entrySet()) {
            System.out.print("Role : " + entry.getKey().getName() + " Users : ");
            for (User user : entry.getValue()) {
                System.out.print(user.getName() + " ");
            }
            System.out.println(" ");
        }

        // Test 9 partitionUserByUnderAndOver18
        System.out.println("Test 9 partitionUserByUnderAndOver18");
        Map<Boolean, List<User>> usersByAge = userService.partitionUserByUnderAndOver18();
        for (Map.Entry<Boolean, List<User>> entry : usersByAge.entrySet()) {
            System.out.print("+18 : " + entry.getKey() + " Users : ");
            for (User user : entry.getValue()) {
                System.out.print(user.getName() + " ");
            }
            System.out.println(" ");
        }
    }

}
