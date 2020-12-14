package com.Igor.SpringMPS.entities;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.Arrays;
import java.util.Collection;
import java.util.Set;

@Entity
@Table (name = "user_table")
@Data
//@NoArgsConstructor(access = AccessLevel.PRIVATE, force=true)
@NoArgsConstructor(access = AccessLevel.PUBLIC, force=true)
//@RequiredArgsConstructor
//@AllArgsConstructor
public class User{ //implements UserDetails {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    //private final String username;
    //private final String password;
    private String email;//username;
    private String password;
    private String name;
    private String googleName;
    private String googleUsername;
    private String imageUrl;

    //@Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return getRoles();
    }
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"))
    @Enumerated(EnumType.STRING)
    private Set<Role> roles;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider provider;

    private String providerId;


    //@Override
    //public boolean isAccountNonExpired() {        return true;    }

    //@Override
    //public boolean isAccountNonLocked() {       return true;    }

    //@Override
    //public boolean isCredentialsNonExpired() {        return true;    }

    //@Override
    //public boolean isEnabled() {        return true;    }
}
