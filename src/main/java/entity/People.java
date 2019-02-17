package entity;

import java.util.List;

public class People {
    private String name;
    private String sex;
    private Integer age;
    private String address;
    private List<String> hobby;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<String> getHobby() {
        return hobby;
    }

    public void setHobby(List<String> hobby) {
        this.hobby = hobby;
    }

    public People(String name, String sex, Integer age, String address, List<String> hobby) {
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.address = address;
        this.hobby = hobby;
    }
}
