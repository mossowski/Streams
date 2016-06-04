package service;

import entities.Person;
import entities.Role;
import entities.User;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class UserService implements UserServiceInterface {

    private List<User> users;

    public UserService(List<User> users) {
        this.users = users;
    }

    public List<User> findUsersWithAddressesCountMoreThan(int numberOfAddresses) {
        List<User> result = users
                .stream()
                .filter(user -> user.getPersonDetails().getAddresses().size() > numberOfAddresses)
                .collect(Collectors.toList());
        return result;
    }

    public Person findOldestPerson() {
        Person oldestPerson = users
                .stream()
                .map(user -> user.getPersonDetails())
                .max((x, y) -> Integer.compare(x.getAge(), y.getAge()))
                .get();
        return oldestPerson;
    }

    public User findUserWithLongestUsername() {
        User user = users
                .stream()
                .sorted((x, y) -> x.getName().length() > y.getName().length() ? -1 : 1)
                .findFirst()
                .get();
        return user;
    }

    public String getNamesAndSurnamesCommaSeparatedOfAllUsersAbove18() {
        String result = users
                .stream()
                .filter(user -> user.getPersonDetails().getAge() > 18)
                .map(user -> user.getPersonDetails().getName() + " " + user.getPersonDetails().getSurname())
                .reduce((x, y) -> x + "," + y)
                .get();
        return result;
    }

    public List<String> getSortedPermissionsOfUsersWithNameStartingWith(String prefix) {
        List<String> result = users
                .stream()
                .filter(user -> user.getName().startsWith(prefix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .flatMap(list -> list.stream())
                .map(permission -> permission.getName())
                .sorted()
                .collect(Collectors.toList());
        return result;
    }

    public double getUsersAverageAge() {
        double average = users
                .stream()
                .mapToDouble(user -> user.getPersonDetails().getAge())
                .average()
                .getAsDouble();
        return average;
    }

    public void printCapitalizedPermissionNamesOfUsersWithSurnameStartingWith(String suffix) {
        users
                .stream()
                .filter(user -> user.getPersonDetails().getSurname().startsWith(suffix))
                .map(user -> user.getPersonDetails().getRole().getPermissions())
                .flatMap(list -> list.stream())
                .map(permission -> permission.getName().toUpperCase())
                .forEach(e -> System.out.print(e + " "));
    }

    public Map<Role, List<User>> groupUsersByRole() {
        Map<Role, List<User>> result = users
                .stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getRole()));
        return result;
    }

    public Map<Boolean, List<User>> partitionUserByUnderAndOver18() {
        Map<Boolean, List<User>> result = users
                .stream()
                .collect(Collectors.groupingBy(user -> user.getPersonDetails().getAge() > 18 ? true : false));
        return result;
    }
}
