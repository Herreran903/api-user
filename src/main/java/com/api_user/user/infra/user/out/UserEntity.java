package com.api_user.user.infra.user.out;

import com.api_user.user.domain.user.util.UserConstants;
import com.api_user.user.infra.role.out.RoleEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = UserConstants.USER_TABLE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastname;

    @Column(unique = true, nullable = false)
    private String dni;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private LocalDate birthdate;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = UserConstants.USER_ROLE_COLUMN, nullable = false)
    private RoleEntity role;
}