package com.LibraryManagement.upc.iam.application.internal.commandservices;

import com.LibraryManagement.upc.iam.application.internal.outboundservices.hashing.HashingService;
import com.LibraryManagement.upc.iam.application.internal.outboundservices.tokens.TokenService;
import com.LibraryManagement.upc.iam.domain.model.aggregates.User;
import com.LibraryManagement.upc.iam.domain.model.commands.SignInCommand;
import com.LibraryManagement.upc.iam.domain.model.commands.SignUpCommand;
import com.LibraryManagement.upc.iam.domain.model.entities.Role;
import com.LibraryManagement.upc.iam.domain.model.valueobjects.Roles;
import com.LibraryManagement.upc.iam.domain.services.UserCommandService;
import com.LibraryManagement.upc.iam.infrastructure.persistence.jpa.repositories.RoleRepository;
import com.LibraryManagement.upc.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {
    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;
    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }

    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var stringRoles = command.roles();
        var roles = new ArrayList<Role>();
        if (stringRoles == null || stringRoles.isEmpty()) {
            var storedRole = roleRepository.findByName(Roles.MEMBER);
            storedRole.ifPresent(roles::add);
        } else {
            stringRoles.forEach(role -> {
                try {
                    Roles roleEnum = Roles.valueOf(role.getStringName());
                    var storedRole = roleRepository.findByName(roleEnum);
                    storedRole.ifPresent(roles::add);
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Invalid role");
                }
            });
        }
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty()) throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }
}