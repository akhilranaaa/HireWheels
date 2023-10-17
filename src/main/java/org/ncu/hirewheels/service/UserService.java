package org.ncu.hirewheels.service;

import lombok.RequiredArgsConstructor;
import org.ncu.hirewheels.entities.Users;
import org.ncu.hirewheels.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor // helps in generating constructor dynamically at run time
public class UserService {
    private final UserRepository userRepository;
    public Users createUser(Users user)
    {
        if (user.getFirstName() == null || user.getLastName() == null || user.getPassword() == null)
            throw new IllegalArgumentException("Full name & Password is required");

        return userRepository.save(user);
    }

    public Users getUserByEmail(String email, String password)
    {
        Users user = userRepository.findByEmailIgnoreCase(email);

        if (user == null)
        {
            System.out.println("User is not registered!");
            return null;
        }

        if (!user.getPassword().equals(password))
        {
            System.out.println("User is not authorized!");
            return null;
        }

        return user;
    }
}
