package ru.geekbrains.market.core.services;

import ru.geekbrains.market.core.models.UserInfo;

public interface ITokenService {
    String generateToken(UserInfo userInfo);
    UserInfo parseToken(String token);
}
