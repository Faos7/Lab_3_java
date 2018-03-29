package com.example.lab3.service.current.user;

import com.example.lab3.domain.security.CurrentUser;

public interface CurrentUserService {
    boolean canAccessUser(CurrentUser currentUser, Long userId);
}
