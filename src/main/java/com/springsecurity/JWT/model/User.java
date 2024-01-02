package com.springsecurity.JWT.model;


import com.springsecurity.JWT.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "users")
public class User implements UserDetails {



    @Id
    @GeneratedValue //(strategy = GenerationType.IDENTITY)
    private Long id;


    private String nameSurname;
    private String username;
    private String password;


    @Enumerated(EnumType.STRING)
    Role role;



    //kulancıları dönderiyor hangi rollere sahip oldugunu gösteriyor, hem user
    //hem user hemde admin rolünde olup iki kısmıda göre bilir ondan list dönüyoruz
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }


    // kullancı hesap süresi dolup dolmadı belirtir
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //hesap kitlimi degilmi
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //kullanıcı aktifmi pasif mi
    @Override
    public boolean isEnabled() {
        return true;
    }
}
