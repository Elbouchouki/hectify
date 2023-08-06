package org.elbouchouki.hectify.core.users.service;


import org.elbouchouki.hectify.core.users.dto.UserRequest;
import org.elbouchouki.hectify.core.users.dto.UserResponse;
import org.elbouchouki.hectify.core.users.entitie.User;
import org.elbouchouki.hectify.core.dto.PagingResponse;
import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.exception.NotFoundException;

public interface UserService {

    UserResponse getUserById(String id) throws NotFoundException;

    UserResponse createUser(UserRequest userRequest) throws AlreadyExistsException;

    UserResponse updateUser(String id, UserRequest userRequest) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;


    PagingResponse<UserResponse> getAllUsers(int page, int size);


    User getRawUserById(String id) throws NotFoundException;

    User getRawUserByUsername(String username) throws NotFoundException;

    User getRawUserByEmail(String email) throws NotFoundException;

    User getRawUserByUsernameOrEmail(String usernameOrEmail) throws NotFoundException;

    boolean existsById(String id);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

}
