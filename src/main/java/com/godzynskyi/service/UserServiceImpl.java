package com.godzynskyi.service;

import com.godzynskyi.dao.UserDAO;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserRole;
import com.godzynskyi.validation.AddUserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.hierarchicalroles.UserDetailsWrapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by Java Developer on 04.10.2015.
 */
@Service("userDetailsService")
public class UserServiceImpl implements UserService, UserDetailsService {

    @Autowired
    EntityManager em /*= new SpringContext().entityManager()*/;
    @Autowired
    UserDAO userDAO /*= new SpringContext().userDao()*/;

    @Override
    public boolean isAuthorized(String login, String password) {

        User user = userDAO.getUser(login);
        if(user == null) return false;
        String pass = user.getPassword();
        if(!BCrypt.checkpw(password, pass)) return false;
        if(!pass.equals(password)) return false;
        return true;
    }

    @Override
    public AddUserStatus addNewUser(User user) {
        AddUserStatus res = new AddUserStatus();
        if(getUser(user.getLogin())!=null) {
            res.setNotUserExist(false);
            return res;
        }
        res.setAdded(userDAO.addUser(user));
        return res;
    }

    @Override
    public User getUser(String login) {
        return userDAO.getUser(login);
    }

    @Override
    public User getUser(long id) {
        return getUser(id);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userDAO.getUser(login);

        List<GrantedAuthority> authorities = buildUserAuthority(user.getRoles());

        return buildUserForAuthentication(user, authorities);

    }

    private org.springframework.security.core.userdetails.User buildUserForAuthentication(User user, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), true, true, true, true, authorities);
    }



    private List<GrantedAuthority> buildUserAuthority(List<UserRole> userRoles) {

        Set<GrantedAuthority> setAuths = new HashSet<GrantedAuthority>();

        // Build user's authorities
        for (UserRole userRole : userRoles) {
            setAuths.add(new SimpleGrantedAuthority(userRole.getRole()+""));
        }

        List<GrantedAuthority> Result = new ArrayList<GrantedAuthority>(setAuths);

        return Result;
    }
}
