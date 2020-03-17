package allst.boot.strap.entity;

import java.util.Date;

/**
 * @author YiYa
 * @since 2020/3/11
 */
public class User {

    private String uid;
    private String name;
    private Integer age;
    private Date birthday;

    public User() {
    }

    public User(String uid, String name, Integer age, Date birthday) {
        this.uid = uid;
        this.name = name;
        this.age = age;
        this.birthday = birthday;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", birthday=" + birthday +
                '}';
    }
}
