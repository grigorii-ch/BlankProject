package chuprynin.com.epam.rd.user;

import java.util.ArrayList;

/**
 * Класс для работы сущностью User
 */
public class UserService {
    /**
     * Возвращает ArrayList с 3 элементами класс User
     *
     * @return ArrayList<User>
     */
    public ArrayList<User> getFillUserList() {
        ArrayList<User> userList = new ArrayList<>();

        userList.add(new User("Иванов И. И.", Roles.USER));
        userList.add(new User("Петров П. П.", Roles.MODERATOR));
        userList.add(new User("Сидоров С. С.", Roles.ADMIN));

        return userList;
    }

    /**
     * Метод вывода значений
     */
    public void printHelloUsers(ArrayList<User> users) {
        for (User user : users) {
            System.out.println("Приветствуем: " + user.getFio() + " - с ролью "
                    + user.getRole() + ", (" + user.getDescribeRole(user.getRole()) + ")");
        }
    }
}
