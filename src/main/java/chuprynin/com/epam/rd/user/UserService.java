package chuprynin.com.epam.rd.user;

import java.util.ArrayList;

/**
 * Класс для работы сущностью User
 */
public class UserService {
    private ArrayList<User> userList = new ArrayList<>();

    /**
     * Конструктор класса с заполнением дефолтных значений массива
     */
    public UserService() {
        userList.add(new User("Иванов И. И.", Roles.USER));
        userList.add(new User("Петров П. П.", Roles.MODERATOR));
        userList.add(new User("Сидоров С. С.", Roles.ADMIN));
    }

    /**
     * Метод для демонстрации работы c сущностью Human
     */
    public void service() {
        for (User user : userList) {
            printUser(user);
        }
    }

    /**
     * Метод вывода значений
     */
    public void printUser(User user) {
        System.out.println("Приветствуем: " + user.getFio() + " - с ролью "
                + user.getRole() + ", (" + user.getDescribeRole(user.getRole()) + ")");
    }
}
