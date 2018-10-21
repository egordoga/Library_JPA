package entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users_groups", schema = "library", catalog = "")
@IdClass(UsersGroupsPK.class)
public class UsersGroups {
    private String groupid;
    private String userid;

    @Id
    @Column(name = "GROUPID")
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Id
    @Column(name = "USERID")
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsersGroups that = (UsersGroups) o;
        return Objects.equals(groupid, that.groupid) &&
                Objects.equals(userid, that.userid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupid, userid);
    }
}
