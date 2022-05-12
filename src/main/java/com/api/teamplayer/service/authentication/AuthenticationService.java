package com.api.teamplayer.service.authentication;

import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.dto.auth.AuthenticationRequest;
import com.api.teamplayer.dto.user.User;

public interface AuthenticationService {
    GenericResult<User> authenticate(AuthenticationRequest authRequest);
}
