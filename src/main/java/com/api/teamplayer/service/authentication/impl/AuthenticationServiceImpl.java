package com.api.teamplayer.service.authentication.impl;

import com.api.teamplayer.authentication.util.JwtTokenUtil;
import com.api.teamplayer.base.exception.ProcessResultException;
import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.base.type.ProcessResult;
import com.api.teamplayer.base.util.BaseEnumeration.*;
import com.api.teamplayer.dto.auth.AuthenticationRequest;
import com.api.teamplayer.dto.mapper.Mapper;
import com.api.teamplayer.dto.user.TokenizedUser;
import com.api.teamplayer.dto.user.User;
import com.api.teamplayer.entity.user.UserEntity;
import com.api.teamplayer.repository.user.UserEntityRepository;
import com.api.teamplayer.service.authentication.AuthenticationService;
import com.api.teamplayer.service.user.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final HttpServletResponse httpServletResponse;
    private final UserEntityRepository userEntityRepository;
    private final UserDetailsServiceImpl userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final Mapper mapper;

    @Value("${jwt.signToken}")
    private String signToken = "Authorization";

    @Autowired
    public AuthenticationServiceImpl(AuthenticationManager authenticationManager, HttpServletResponse httpServletResponse, UserEntityRepository userEntityRepository, UserDetailsServiceImpl userService, JwtTokenUtil jwtTokenUtil, Mapper mapper) {
        this.authenticationManager = authenticationManager;
        this.httpServletResponse = httpServletResponse;
        this.userEntityRepository = userEntityRepository;
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.mapper = mapper;
    }

    @Override
    public GenericResult<User> authenticate(AuthenticationRequest authRequest) {
        GenericResult<User> response = new GenericResult<>();
        try {
            final UserEntity userEntity = this.userEntityRepository.findByEmail(authRequest.getUsername());
            if (userEntity == null)
                throw new ProcessResultException(ProcessResult.internalServerError("USER_NOT_FOUND_" + AuthenticationOperation.BAD_CREDENTIALS), true);

            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            final TokenizedUser userDetails = (TokenizedUser) this.userService.loadUserByUsername(authRequest.getUsername());

            final String token = jwtTokenUtil.generateToken(userDetails);
            this.httpServletResponse.addHeader(signToken, token);

            User user = this.mapper.convertUserEntityToUser(userEntity);
            response.setResult(user);

            response.setProcessResult(ProcessResult.success(user.getEmail() + " authenticated"));
        } catch (Exception exc) {
            response.setProcessResult(ProcessResult.unauthorized(exc.getMessage()));

            return response;
        }

        return response;
    }
}
