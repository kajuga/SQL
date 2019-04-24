package itvdn_jdbc.lesson_002.homework;

public class Abonent {
    int id;
    int number;
    String name;

    public Abonent(int id, int number, String name) {
        this.id = id;
        this.number = number;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Abonent{" +
                "id=" + id +
                ", number=" + number +
                ", name='" + name + '\'' +
                '}';
    }
}
