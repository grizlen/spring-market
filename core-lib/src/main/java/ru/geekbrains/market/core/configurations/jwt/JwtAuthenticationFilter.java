package ru.geekbrains.market.core.configurations.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import ru.geekbrains.market.core.services.ITokenService;
import ru.geekbrains.market.core.models.UserInfo;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final ITokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authHeader = httpServletRequest.getHeader("Authorization");
        if (authHeader == null ||
                        !authHeader.startsWith("Bearer ")
        // TODO: 17.09.2021 redisRepository.Haskey()
        ) {
            filterChain.doFilter(httpServletRequest, httpServletResponse);
            return;
        }
        UsernamePasswordAuthenticationToken authenticationToken =
                createToken(authHeader.replace("Bearer ", ""));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    private UsernamePasswordAuthenticationToken createToken(String authHeader) {
        UserInfo userInfo = tokenService.parseToken(authHeader);
        List<GrantedAuthority> authorities = userInfo.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());
        return new UsernamePasswordAuthenticationToken(userInfo, null, authorities);
    }
}
