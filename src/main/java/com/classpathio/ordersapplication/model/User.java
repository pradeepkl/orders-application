package com.classpathio.ordersapplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;
import java.time.LocalDate;

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

}
