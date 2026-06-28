package com.github.Luiztins1.controller.dtos;

import java.util.List;

public record UserAuthDTO(
        String login,
        String password,
        List<String> roles) {
}
