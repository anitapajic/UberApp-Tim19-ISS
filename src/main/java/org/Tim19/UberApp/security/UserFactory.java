package org.Tim19.UberApp.security;


import org.Tim19.UberApp.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class UserFactory {

    public static SecurityUser create(User user) {
        Collection<? extends GrantedAuthority> authorities;
        try {
            authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getAuthorities());
        } catch (Exception e) {
            authorities = null;
        }

        return new SecurityUser(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                authorities
        );
    }
}
