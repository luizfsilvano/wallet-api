package com.luizfsilvano.wallet.domain.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import jakarta.persistence.Table;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name="users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(unique = true)
    private String username;

    // The password should be stored securely, ideally hashed
    private String password;

    private String email;

    // Roles
    @ElementCollection
    private List<String> roles;

    // Relation with Wallet
    @OneToOne(mappedBy="user", cascade = CascadeType.ALL)
    private Wallet wallet;


}
