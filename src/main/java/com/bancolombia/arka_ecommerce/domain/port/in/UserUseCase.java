package com.bancolombia.arka_ecommerce.domain.port.in;

import java.util.List;

import com.bancolombia.arka_ecommerce.domain.model.User;

public interface UserUseCase {

    User createUser(User user);
    List<User> getAllUsers();
    boolean existsByUserName(String userName);                                                                                                                                  
    
}
