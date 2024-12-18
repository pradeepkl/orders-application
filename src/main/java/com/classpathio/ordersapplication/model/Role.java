package com.classpathio.ordersapplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@EqualsAndHashCode
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    private int id;

    @Getter @Setter
    private String role;

    @ManyToMany(mappedBy = "roles")
    @Getter @Setter
    private Set<User> users;


}
