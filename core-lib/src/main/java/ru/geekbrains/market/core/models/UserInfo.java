package ru.geekbrains.market.core.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
    private Long id;
    private String login;
    private List<String> roles;
}
