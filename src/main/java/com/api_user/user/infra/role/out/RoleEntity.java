package com.api_user.user.infra.role.out;

import com.api_user.user.domain.role.util.RoleConstants;
import com.api_user.user.domain.role.util.RoleEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = RoleConstants.ROLE_TABLE)
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, updatable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private RoleEnum name;

    @Column(updatable = false)
    private String description;

    public String getNameString() {
        return name.toString();
    }
}
