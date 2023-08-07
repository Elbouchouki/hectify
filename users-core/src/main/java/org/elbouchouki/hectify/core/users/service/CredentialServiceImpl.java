package org.elbouchouki.hectify.core.users.service;


import lombok.RequiredArgsConstructor;
import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.users.entitie.Credential;
import org.elbouchouki.hectify.core.users.entitie.User;
import org.elbouchouki.hectify.core.users.repository.CredentialRepository;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {

    private final CredentialRepository repository;

    @Override
    public Credential createCredential(User user, String oldPassword) throws AlreadyExistsException {
        return this.repository.save(
                Credential.builder()
                        .lastPassword(oldPassword)
                        .passwordUpdatedAt(user.getPasswordUpdatedAt())
                        .version(user.getPasswordVersion())
                        .build()
        );
    }
}
