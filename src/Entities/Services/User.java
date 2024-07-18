package Entities.Services;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private String email;
    private Integer age;
    private Double height;
    private List<String> answers = new ArrayList<>();


    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    @Override
    public String toString() {
        StringBuilder userString = new StringBuilder();
        userString.append(name).append("\n")
                .append(email).append("\n")
                .append(age).append(" anos").append("\n")
                .append(height).append(" m").append("\n");

        for (String answers : answers) {
            userString.append(answers).append("\n");
        }
        return userString.toString();
    }
}
