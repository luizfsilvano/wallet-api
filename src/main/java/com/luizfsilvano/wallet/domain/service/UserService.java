package com.luizfsilvano.wallet.domain.service;

import com.luizfsilvano.wallet.domain.model.User;
import com.luizfsilvano.wallet.web.dto.RegisterDTO;

public interface UserService {
    User registerUser(RegisterDTO registerDTO);
    User findByUsername(String username);
}