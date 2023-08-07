package org.elbouchouki.hectify.core.users.service;


import org.elbouchouki.hectify.core.exception.AlreadyExistsException;
import org.elbouchouki.hectify.core.users.entitie.Credential;
import org.elbouchouki.hectify.core.users.entitie.User;

public interface CredentialService {

    Credential createCredential(User user, String newPassword) throws AlreadyExistsException;

}
