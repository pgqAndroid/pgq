package net.gangpeng.pgq.domain;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

/**
 * className: net.gangpeng.pgq.domain.gangpeng
 * function: 用户实体类
 * <p/>
 * created at 17/1/9 15:10
 *
 * @author gangpeng
 */
@Entity(nameInDb = "USER")
public class User {
    @Id
    private Long id;
    private int age = 0;
    @Property(nameInDb = "GANDER")
    private int gander = 0;
    private String name = "";
    private String comment = "";
    @Transient
    private String love;
    private String phone;
    @Generated(hash = 760968933)
    public User(Long id, int age, int gander, String name, String comment,
            String phone) {
        this.id = id;
        this.age = age;
        this.gander = gander;
        this.name = name;
        this.comment = comment;
        this.phone = phone;
    }
    @Generated(hash = 586692638)
    public User() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public int getGander() {
        return this.gander;
    }
    public void setGander(int gander) {
        this.gander = gander;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getComment() {
        return this.comment;
    }
    public void setComment(String comment) {
        this.comment = comment;
    }
    public String getPhone() {
        return this.phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
}
