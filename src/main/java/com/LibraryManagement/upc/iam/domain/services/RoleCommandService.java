package com.LibraryManagement.upc.iam.domain.services;


import com.LibraryManagement.upc.iam.domain.model.commands.SeedRolesCommand;

public interface RoleCommandService {
    void handle(SeedRolesCommand command);
}
