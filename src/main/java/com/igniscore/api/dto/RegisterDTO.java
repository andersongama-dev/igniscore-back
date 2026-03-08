package com.igniscore.api.dto;

import com.igniscore.api.model.UserRole;

public record RegisterDTO(String name, String email, String password, UserRole role) {

}
