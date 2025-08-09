package org.utej.soundnest.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "useraccounts")
public class UserAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String upiId;
    private String phoneNumber;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;
}
