package com.api.teamplayer.service.user.impl;

import com.api.teamplayer.base.exception.ProcessResultException;
import com.api.teamplayer.base.type.GenericResult;
import com.api.teamplayer.base.type.ProcessResult;
import com.api.teamplayer.dto.mapper.Mapper;
import com.api.teamplayer.dto.user.CreateUserRequest;
import com.api.teamplayer.dto.user.User;
import com.api.teamplayer.entity.user.UserEntity;
import com.api.teamplayer.repository.user.UserEntityRepository;
import com.api.teamplayer.service.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserEntityRepository userEntityRepository;
    private final Mapper mapper;

    public UserServiceImpl(BCryptPasswordEncoder bCryptPasswordEncoder,
                           UserEntityRepository userEntityRepository, Mapper mapper) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userEntityRepository = userEntityRepository;
        this.mapper = mapper;
    }

    @Override
    public GenericResult<User> registerUser(CreateUserRequest request) {
        this.verifyExistingUser(request);
        GenericResult<User> response = new GenericResult<>();

        try {
            UserEntity userEntity = new UserEntity();
            userEntity.setFirstName(request.getFirstName());
            userEntity.setLastName(request.getLastName());
            userEntity.setEmail(request.getEmail());
            userEntity.setPassword(bCryptPasswordEncoder.encode(request.getPassword()));

            this.userEntityRepository.save(userEntity);
            User user = this.mapper.convertUserEntityToUser(userEntity);

            response.setResult(user);
            response.setProcessResult(ProcessResult.success("User registration is complete. "));

            return response;
        } catch (ProcessResultException exc) {
            throw new ProcessResultException(ProcessResult.badRequest(exc.getMessage()), true);
        }
    }

    private void verifyExistingUser(CreateUserRequest request) {
        final UserEntity user = this.userEntityRepository.findByEmail(request.getEmail());
        if (user != null)
            throw new ProcessResultException(ProcessResult.badRequest("THIS_EMAIL_ALREADY_EXISTS"), true);
    }
}
