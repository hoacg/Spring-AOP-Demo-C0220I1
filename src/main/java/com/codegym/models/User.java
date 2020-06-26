package com.codegym.models;

import org.springframework.stereotype.Component;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Component
public class User {


    @NotEmpty(message = "Không được để trống trường này")
    @Size(min = 2, max = 30, message = "Phải từ 2 đến 30 kí tự")
    private String name;

    @Min(value = 18, message = "Ít nhất 18 tuổi")
    private int age;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}
