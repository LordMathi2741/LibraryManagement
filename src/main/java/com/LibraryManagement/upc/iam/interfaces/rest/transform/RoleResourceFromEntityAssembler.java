package com.LibraryManagement.upc.iam.interfaces.rest.transform;


import com.LibraryManagement.upc.iam.domain.model.entities.Role;
import com.LibraryManagement.upc.iam.interfaces.rest.resources.RoleResource;

public class RoleResourceFromEntityAssembler {
    public static RoleResource toResourceFromEntity(Role role) {
        return new RoleResource(role.getId(), role.getStringName());
    }
}