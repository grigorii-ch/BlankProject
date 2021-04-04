package chuprynin.com.epam.rd.user;

import java.util.HashMap;
import java.util.Map;

/**
 * Класс описывает сущность Пользователь
 */
public class User {
    private String fio;
    private Roles role;

    public User(String fio, Roles role) {
        this.fio = fio;
        this.role = role;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public Roles getRole() {
        return role;
    }

    public void setRole(Roles role) {
        this.role = role;
    }

    /**
     * Статический метод для получения описания роли
     *
      * @param role
     * @return описание роли
     */
    public static String getDescribeRole(Roles role) {
        Map map = new HashMap<Roles,String>();
        map.put(Roles.USER,"читать");
        map.put(Roles.MODERATOR,"читать писать");
        map.put(Roles.ADMIN,"читать писать удалять");
        return map.get(role).toString();
    }
}
