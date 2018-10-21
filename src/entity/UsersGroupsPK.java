package entity;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class UsersGroupsPK implements Serializable {
    private String groupid;
    private String userid;

    @Column(name = "GROUPID")
    @Id
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Column(name = "USERID")
    @Id
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
        UsersGroupsPK that = (UsersGroupsPK) o;
        return Objects.equals(groupid, that.groupid) &&
                Objects.equals(userid, that.userid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupid, userid);
    }
}
