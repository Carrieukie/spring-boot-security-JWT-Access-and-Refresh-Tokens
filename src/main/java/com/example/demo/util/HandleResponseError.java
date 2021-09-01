package com.example.demo.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
public class HandleResponseError {
    public static void sendError(HttpServletResponse response, Exception exception) throws IOException {
        log.error("Error occurred during login {}", exception.getMessage());
        var errorMessage = exception.getMessage();
        Map<String, String> error = new HashMap<>();
        error.put("message", errorMessage);
        response.setContentType(APPLICATION_JSON_VALUE);
        new ObjectMapper().writeValue(response.getOutputStream(), error);
    }
}
