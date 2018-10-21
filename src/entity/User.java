package entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Objects;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String username;
    private String password;


}
