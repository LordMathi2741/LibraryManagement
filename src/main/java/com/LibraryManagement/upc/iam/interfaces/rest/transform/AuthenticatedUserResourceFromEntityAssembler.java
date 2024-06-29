package com.LibraryManagement.upc.iam.interfaces.rest.transform;


import com.LibraryManagement.upc.iam.domain.model.aggregates.User;
import com.LibraryManagement.upc.iam.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(User user, String token) {
        return new AuthenticatedUserResource(user.getId(), user.getUsername(), token);
    }
}