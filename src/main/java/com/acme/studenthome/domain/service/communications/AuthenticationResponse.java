package com.acme.studenthome.domain.service.communications;

import lombok.Data;

import java.io.Serializable;

@Data
public class AuthenticationResponse implements Serializable {
    private String username;
    private String token;

    public AuthenticationResponse(String username, String token) {
        this.username = username;
        this.token = token;
    }
}
