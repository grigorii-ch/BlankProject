package chuprynin.com.epam.rd.human;

import java.util.Objects;

/**
 * Класс описывает сущность Человек
 */
public class Human {
    private String fio;
    private int age;
    private Address address;

    public Human(String fio, int age, Address address) {
        this.fio = fio;
        this.age = age;
        this.address = address;
    }

    public String getFio() {
        return fio;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public String getAdressString() {
        return this.address.toString();
    }

    @Override
    public String toString() {
        return "Human{" +
                "fio='" + fio + '\'' +
                ", age=" + age +
                ", address=" + address +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return age == human.age && Objects.equals(fio, human.fio) && Objects.equals(address, human.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, age, address);
    }
}
