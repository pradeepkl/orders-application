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
@Getter
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private long id;

    @Setter
    private String username;

    @Setter @JsonIgnore
    private String password;

    @Setter
    private LocalDate dob;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name ="user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;




}
