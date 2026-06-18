package com.sebas.mx.API_Tareas.security;

import tools.jackson.databind.ObjectMapper;
import com.sebas.mx.API_Tareas.dto.ErrorResponseDto;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;


//Esta parte fue hecha con ayuda de la IA, ya que no conocia ciertos aspectos de los interceptores

@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${api.auth.token}")
    private String apiToken;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String requestPath = request.getServletPath();

        // Solo protegemos las rutas que empiezan con /api
        if (requestPath.startsWith("/api")) {
            String headerToken = request.getHeader("Authorization");

            // Soporte para "Bearer <token>" o simplemente "<token>" en el header Authorization
            if (headerToken != null && headerToken.startsWith("Bearer ")) {
                headerToken = headerToken.substring(7);
            }

            if (apiToken.equals(headerToken)) {
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.setContentType("application/json;charset=UTF-8");
                
                ErrorResponseDto error = new ErrorResponseDto(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        "Token de autenticación invalido"
                );
                
                ObjectMapper mapper = new ObjectMapper();
                response.getWriter().write(mapper.writeValueAsString(error));
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }
}
