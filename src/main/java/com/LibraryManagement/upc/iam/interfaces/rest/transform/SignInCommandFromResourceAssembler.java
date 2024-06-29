package com.LibraryManagement.upc.iam.interfaces.rest.transform;


import com.LibraryManagement.upc.iam.domain.model.commands.SignInCommand;
import com.LibraryManagement.upc.iam.interfaces.rest.resources.SignInResource;

public class SignInCommandFromResourceAssembler {
    public static SignInCommand toCommandFromResource(SignInResource signInResource) {
        return new SignInCommand(signInResource.username(), signInResource.password());
    }
}