package com.api.teamplayer.service.user.impl;

import com.api.teamplayer.base.util.BaseEnumeration.*;
import com.api.teamplayer.dto.user.TokenizedUser;
import com.api.teamplayer.entity.user.UserEntity;
import com.api.teamplayer.repository.user.UserEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;

    @Autowired
    public UserDetailsServiceImpl(UserEntityRepository userEntityRepository) {
        this.userEntityRepository = userEntityRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity user = this.userEntityRepository.findByEmail(username);

        if (user == null)
            throw new UsernameNotFoundException(AuthenticationOperation.USER_NOT_FOUND.name() + username);

        return createTokenizedUser(user);
    }

    private TokenizedUser createTokenizedUser(UserEntity user) {
        return new TokenizedUser(
                user.getUserId(),
                user.getEmail(),
                user.getPassword(),
                true,
                this.mapToGrantedAuthorities());
    }

    public List<GrantedAuthority> mapToGrantedAuthorities() {
        ArrayList<GrantedAuthority> grantedAuthorityList = new ArrayList<>();
        grantedAuthorityList.add(new SimpleGrantedAuthority("USER"));
        return grantedAuthorityList;
    }
}
