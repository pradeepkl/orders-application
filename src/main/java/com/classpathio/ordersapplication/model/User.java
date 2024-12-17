package com.classpathio.ordersapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@ToString(exclude = "password")
@EqualsAndHashCode(of = {"username", "dob"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private long id;

    @Setter @Getter
    private String username;

    @Setter @Getter @JsonIgnore
    private String password;

    @Setter @Getter
    private LocalDate dob;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

}
