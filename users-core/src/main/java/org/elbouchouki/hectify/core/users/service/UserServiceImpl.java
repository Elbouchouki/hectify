package org.elbouchouki.hectify.core.users.service;

import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.users.constant.UserConstants;
import org.elbouchouki.hectify.core.users.dto.UserRequest;
import org.elbouchouki.hectify.core.users.dto.UserResponse;
import org.elbouchouki.hectify.core.users.entitie.User;
import org.elbouchouki.hectify.core.users.mapper.UserMapper;
import org.elbouchouki.hectify.core.users.repository.UserRepository;
import org.elbouchouki.hectify.core.dto.PagingResponse;
import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.exception.NotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final String ELEMENT_TYPE = "User";
    private final String ID_FIELD_NAME = "userId";

    private final UserRepository repository;

    private final UserMapper mapper;

    @Override
    public UserResponse getUserById(String id) throws NotFoundException {
        User user = this.repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                UserConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, id},
                                null
                        )
                );

        return this.mapper.toResponse(
                user
        );
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) throws AlreadyExistsException {
        return null;
    }

    @Override
    public UserResponse updateUser(String id, UserRequest userRequest) throws NotFoundException {
        return null;
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {

    }

    @Override
    public PagingResponse<UserResponse> getAllUsers(int page, int size) {
        return null;
    }

    @Override
    public User getRawUserById(String id) throws NotFoundException {
        return null;
    }

    @Override
    public User getRawUserByUsername(String username) throws NotFoundException {
        return null;
    }

    @Override
    public User getRawUserByEmail(String email) throws NotFoundException {
        return null;
    }

    @Override
    public User getRawUserByUsernameOrEmail(String usernameOrEmail) throws NotFoundException {
        return null;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public boolean existsByUsername(String username) {
        return false;
    }

    @Override
    public boolean existsByEmail(String email) {
        return false;
    }
}
