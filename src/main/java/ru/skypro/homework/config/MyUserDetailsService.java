package ru.skypro.homework.config;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class MyUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        return new UserSecurityDTO(user);
    }
}
