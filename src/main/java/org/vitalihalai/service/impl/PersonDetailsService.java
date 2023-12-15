package org.iseedeadpeopleq.service.impl;

import org.iseedeadpeopleq.DAO.UserDAO;
import org.iseedeadpeopleq.DAO.exception.DatabaseQueryException;
import org.iseedeadpeopleq.beans.User;
import org.iseedeadpeopleq.beans.security.PersonDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonDetailsService implements UserDetailsService {

    private final UserDAO userDAO;

    public PersonDetailsService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Optional<User> user = userDAO.getUserByEmail(username);
            if(user.isEmpty()){
                throw new UsernameNotFoundException("User not found");
            }
            return new PersonDetails(user.get());
        } catch (DatabaseQueryException e) {
            throw new RuntimeException(e);
        }
    }
}
