package org.elbouchouki.hectify.core.users.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.constant.CoreConstants;
import org.elbouchouki.hectify.core.exception.InvalidPasswordException;
import org.elbouchouki.hectify.core.exception.PasswordUsedException;
import org.elbouchouki.hectify.core.dto.user.*;
import org.elbouchouki.hectify.core.users.entitie.User;
import org.elbouchouki.hectify.core.users.mapper.UserMapper;
import org.elbouchouki.hectify.core.users.repository.UserRepository;
import org.elbouchouki.hectify.core.dto.shared.PagingResponse;
import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.exception.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final String ELEMENT_TYPE = "User";
    private final String ID_FIELD_NAME = "userId";

    private final UserRepository userRepository;

    private final CredentialService credentialService;

    private final UserMapper userMapper;

    private final PasswordEncoder passwordEncoder;

    private final RoleService roleService;

    @Override
    public UserResponse getUserById(String id) throws NotFoundException {
        return this.userMapper.toResponse(
                this.getRawUserById(
                        id
                )
        );
    }

    @Override
    public UserResponse createUser(UserCreateRequest userCreateRequest) throws AlreadyExistsException {
        if (this.existsByEmail(userCreateRequest.email())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "email", userCreateRequest.email()},
                    null
            );
        }
        if (this.existsByUsername(userCreateRequest.username())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "username", userCreateRequest.username()},
                    null
            );
        }

        if (userCreateRequest.roles() != null && !userCreateRequest.roles().isEmpty()) {
            this.checkRoles(userCreateRequest.roles());
        }

        User user = this.userMapper.toEntity(
                userCreateRequest
        );

        this.encodePassword(user);

        return this.userMapper.toResponse(
                this.userRepository.save(
                        user
                )
        );
    }

    @Override
    public UserResponse updateUser(String id, UserUpdateRequest request) throws NotFoundException {
        User user = this.getRawUserById(id);

        if (request.roles() != null && !request.roles().isEmpty()) {
            this.checkRoles(request.roles());
        }

        this.userMapper.update(request, user);

        return this.userMapper.toResponse(
                this.userRepository.save(user)
        );
    }

    @Override
    public UserResponse updatePassword(String id, UserUpdatePasswordRequest request) throws NotFoundException {
        User user = this.getRawUserById(id);

        this.verifyPassword(user, request.oldPassword());

        String oldPassword = user.getPassword();
        this.userMapper.update(request, user);

        if (!this.passwordEncoder.matches(request.newPassword(), oldPassword)) {
            user.getCredentials().stream().filter(
                            credential -> this.passwordEncoder.matches(request.newPassword(), credential.getLastPassword())
                    ).findFirst()
                    .ifPresent(
                            credential -> {
                                throw new PasswordUsedException();
                            }
                    );

            user.getCredentials().add(
                    credentialService.createCredential(
                            user, oldPassword
                    )
            );

            user.setPassword(request.newPassword());
            user.setPasswordUpdatedAt(new Timestamp(System.currentTimeMillis()));
            user.setPasswordVersion(user.getPasswordVersion() + 1);
            this.encodePassword(user);
        }

        return this.userMapper.toResponse(
                this.userRepository.save(user)
        );
    }

    @Override
    public UserResponse updateEmail(String id, UserUpdateEmailRequest request) throws NotFoundException {
        User user = this.getRawUserById(id);

        this.verifyPassword(user, request.currentPassword());

        if (request.email().toLowerCase().equals(user.getEmail())) {
            return this.userMapper.toResponse(user);
        }
        if (this.existsByEmail(request.email())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "email", request.email()},
                    null
            );
        }
        this.userMapper.update(request, user);
        return this.userMapper.toResponse(
                this.userRepository.save(user)
        );
    }

    @Transactional
    @Override
    public UserResponse updateUsername(String id, UserUpdateUsernameRequest request) throws NotFoundException {
        User user = this.getRawUserById(id);

        this.verifyPassword(user, request.currentPassword());

        if (request.username().toLowerCase().equals(user.getUsername())) {
            return this.userMapper.toResponse(user);
        }
        if (this.existsByUsername(request.username())) {
            throw new AlreadyExistsException(
                    CoreConstants.BusinessExceptionMessage.ALREADY_EXISTS,
                    new Object[]{ELEMENT_TYPE, "username", request.username()},
                    null
            );
        }
        this.userMapper.update(request, user);
        return this.userMapper.toResponse(
                this.userRepository.save(user)
        );
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {
        if (!this.userRepository.existsById(id)) {
            throw new NotFoundException(
                    CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                    new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, id},
                    null
            );
        }
        this.userRepository.deleteById(id);
    }

    @Transactional
    @Override
    public PagingResponse<UserResponse> getAllUsers(int page, int size) {
        Page<User> allUsers = this.userRepository.findAll(PageRequest.of(page, size));
        return userMapper.toPagingResponse(allUsers);
    }

    @Override
    public User getRawUserById(String id) throws NotFoundException {
        return this.userRepository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(
                                CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_TYPE, ID_FIELD_NAME, id},
                                null
                        )
                );
    }

    @Override
    public User getRawUserByUsername(String username) throws NotFoundException {
        return this.userRepository.findByUsername(
                        username.toLowerCase()
                )
                .orElseThrow(
                        () -> new NotFoundException(
                                CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_TYPE, "username", username},
                                null
                        )
                );
    }

    @Override
    public User getRawUserByEmail(String email) throws NotFoundException {
        return this.userRepository.findByEmail(
                        email.toLowerCase()
                )
                .orElseThrow(
                        () -> new NotFoundException(
                                CoreConstants.BusinessExceptionMessage.NOT_FOUND,
                                new Object[]{ELEMENT_TYPE, "email", email},
                                null
                        )
                );
    }

    @Override
    public User getRawUserByUsernameOrEmail(String usernameOrEmail) throws NotFoundException {
        if (usernameOrEmail.contains("@")) {
            return this.getRawUserByEmail(usernameOrEmail);
        }
        return this.getRawUserByUsername(usernameOrEmail);
    }

    @Override
    public boolean existsById(String id) {
        return this.userRepository.existsById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return this.userRepository.existsByUsername(username.toLowerCase());
    }

    @Override
    public boolean existsByEmail(String email) {
        return this.userRepository.existsByEmail(email.toLowerCase());
    }

    private void encodePassword(User user) {
        user.setPassword(
                this.passwordEncoder.encode(
                        user.getPassword()
                )
        );
    }

    private void verifyPassword(User user, String password) {
        if (!this.checkPassword(user, password)) {
            throw new InvalidPasswordException();
        }
    }

    private boolean checkPassword(User user, String password) {
        return this.passwordEncoder.matches(password, user.getPassword());
    }


    private void checkRoles(Set<Long> roles) {
        this.roleService.checkExistence(roles);
    }

}
