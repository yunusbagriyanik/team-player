package com.api.teamplayer.service.user;

import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.dto.user.CreateUserRequest;
import com.api.teamplayer.dto.user.User;

public interface UserService {
    GenericResult<User> registerUser(CreateUserRequest request);
}
