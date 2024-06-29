package com.LibraryManagement.upc.iam.domain.model.queries;


import com.LibraryManagement.upc.iam.domain.model.valueobjects.Roles;

public record GetRoleByNameQuery(Roles name) {
}
