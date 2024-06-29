package com.LibraryManagement.upc.iam.interfaces.rest.resources;

public record AuthenticatedUserResource(Long id, String username, String token) {

}