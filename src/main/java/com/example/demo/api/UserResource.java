package com.example.demo.api;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.demo.dormain.Role;
import com.example.demo.dormain.User;
import com.example.demo.mapper.DormaintoSpringUserMapper;
import com.example.demo.service.IUserService;
import com.example.demo.util.HandleResponseError;
import com.example.demo.util.TokenUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class UserResource {

    private final IUserService userService;

    @GetMapping("/users")
    public ResponseEntity<List<User>> getUser() {
        return ResponseEntity.ok().body(userService.getUsers());
    }

    @GetMapping("/roles")
    public ResponseEntity<List<Role>> getRoles() {
        return ResponseEntity.ok().body(userService.getRoles());
    }

    @PostMapping("/user/save")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/user/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveUser(user));
    }

    @PostMapping("/role/save")
    public ResponseEntity<Role> saveRole(@RequestBody Role role) {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("api/role/save").toUriString());
        return ResponseEntity.created(uri).body(userService.saveRole(role));
    }

    @PostMapping("/role/addtouser")
    public ResponseEntity<?> saveRole(@RequestBody RoleToUser roleToUser) {
        userService.addRoleToUser(roleToUser.rolename, roleToUser.username);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/token/refresh")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {

        var authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        System.out.println(authorization);
        if (authorization != null && authorization.startsWith("Bearer ")) {

            try {

                var refresh_token = authorization.substring("Bearer ".length());
                System.out.println(refresh_token);
                var algorithm = Algorithm.HMAC256("secret".getBytes());
                var verifier = JWT.require(algorithm).build();
                var decodedJWT = verifier.verify(refresh_token);
                var username = decodedJWT.getSubject();
                var user = userService.getUser(username);
                var springUser = DormaintoSpringUserMapper.dormainToSpringUserMapper(user);

                var access_token = TokenUtil.createAccessToken(request, springUser, algorithm);

                TokenUtil.sendUserTokens(response, refresh_token, access_token);

            } catch (Exception exception) {
                response.setStatus(500);
                HandleResponseError.sendError(response, exception);
            }

        } else {
            throw new RuntimeException("Refresh token is missing");
        }

    }


    @Data
    static class RoleToUser {
        private String username;
        private String rolename;
    }

}
