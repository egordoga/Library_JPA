package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Groups {
    private String groupid;

    @Id
    @Column(name = "GROUPID")
    public String getGroupid() {
        return groupid;
    }

    public void setGroupid(String groupid) {
        this.groupid = groupid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Groups groups = (Groups) o;
        return Objects.equals(groupid, groups.groupid);
    }

    @Override
    public int hashCode() {

        return Objects.hash(groupid);
    }
}
