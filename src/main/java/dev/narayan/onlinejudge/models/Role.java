package dev.narayan.onlinejudge.models;

import javax.persistence.*;
import java.util.List;

@Entity(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(name ="role_perrmission",joinColumns = @JoinColumn(name ="role_id",referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name="permission_id",referencedColumnName = "id"))
    private List<Permission> permissions;

    @ManyToMany(fetch= FetchType.LAZY,mappedBy = "roles")
    private List<User> users;
}
