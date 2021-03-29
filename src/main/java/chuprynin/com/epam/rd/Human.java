package chuprynin.com.epam.rd;

import java.util.Objects;

public class Human{
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
