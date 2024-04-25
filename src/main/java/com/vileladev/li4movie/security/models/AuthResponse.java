package com.vileladev.li4movie.security.models;

import java.time.Instant;

public record AuthResponse(String token, Instant expiresIn) {
}
