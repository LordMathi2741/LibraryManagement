package com.LibraryManagement.upc.iam.domain.services;


import com.LibraryManagement.upc.iam.domain.model.entities.Role;
import com.LibraryManagement.upc.iam.domain.model.queries.GetAllRolesQuery;
import com.LibraryManagement.upc.iam.domain.model.queries.GetRoleByNameQuery;

import java.util.List;
import java.util.Optional;

public interface RoleQueryService {
    List<Role> handle(GetAllRolesQuery query);
    Optional<Role> handle(GetRoleByNameQuery query);
}
