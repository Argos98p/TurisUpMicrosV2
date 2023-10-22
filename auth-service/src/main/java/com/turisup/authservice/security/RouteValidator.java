package com.turisup.authservice.security;

import com.turisup.authservice.dto.RequestDto;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Getter
@Component
@ConfigurationProperties(prefix = "admin-paths")
public class RouteValidator {

    private List<RequestDto> paths =new ArrayList<>();

    public void setPaths(List<RequestDto> paths) {
        this.paths = paths;
    }

    public boolean isAdminPath(RequestDto dto) {
        return paths.stream().anyMatch(p ->
                Pattern.matches(p.getUri(), dto.getUri()) && p.getMethod().equals(dto.getMethod()));
    }
}
