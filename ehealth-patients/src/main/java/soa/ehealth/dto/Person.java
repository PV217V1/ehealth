package soa.ehealth.dto;

import javax.persistence.*;


@Entity
public class Person {
    @Id
    @GeneratedValue
    private Long id;
    //@Column(length = 40, unique = true)
    public String name;
    //@Column(length = 40)
    public String age;
    /// @Column(length = 150)
    public String sex;
    public String address;
    public String telephone;

    public String getSex() {
        return sex;
    }

    public String getEmail() {
        return email;
    }

    public String email;
    public Person() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sex='" + sex + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
