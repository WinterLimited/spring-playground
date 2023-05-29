package springprinciple.core;

import lombok.Getter;
import lombok.Setter;

// Lombok Getter Setter 자동 생성
@Getter
@Setter
public class HelloLombok {
    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("winter");

        String name = helloLombok.getName();
        System.out.println(name);
    }
}
