package br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.details;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import br.edu.ifsuldeminas.mch.webii.crudmanager.spring.model.entities.Police;

public class PoliceDetails implements UserDetails {

    private final Police police;

    public PoliceDetails(Police police) {
        this.police = police;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }

    @Override
    public String getPassword() {
        return police.getPassword();
    }

    @Override
    public String getUsername() {
        return police.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public Police getPolice() {
        return police;
    }

}