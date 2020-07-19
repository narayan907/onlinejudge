package dev.narayan.onlinejudge.models;

import javax.persistence.*;
import java.util.List;

@Entity(name="permissions")
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch= FetchType.LAZY,mappedBy = "permissions")
    private List<Role> roles;

}
