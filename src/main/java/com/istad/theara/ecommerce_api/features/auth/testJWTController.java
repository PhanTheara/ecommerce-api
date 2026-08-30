    package com.istad.theara.ecommerce_api.features.auth;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
public class testJWTController {

    @RequestMapping("testJwt")
    public Map<String,Object> getJwt(@AuthenticationPrincipal Jwt jwt){
        IO.println(" Access Token :" + jwt.getTokenValue());
        IO.println("KeyLoak User Id :" + jwt.getSubject());
        IO.println(jwt.getClaim("name"));

        Map<String,Object> realm_access = jwt.getClaim("realm_access");
        assert realm_access != null;
        IO.println("Role" + realm_access.get("roles"));

        return Map.of(
                "user_id", Objects.requireNonNull(jwt.getSubject()),
                "name", Objects.requireNonNull(jwt.getClaim("name")),
                "roles", Objects.requireNonNull(jwt.getClaim("roles"))
        );
    }
}
