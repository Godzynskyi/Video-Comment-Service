package com.godzynskyi.service;

import com.godzynskyi.dao.UserDocumentCredentialsDAO;
import com.godzynskyi.domain.Credentials;
import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Java Developer on 11.10.2015.
 */
@Repository
public class UserDocumentCredentialsServiceImpl implements UserDocumentCredentialsService {

    @Autowired
    private UserDocumentCredentialsDAO userDocumentCredentialsDAO;

    @Override
    public Credentials getCredentials(long userId, long docId) {
        return userDocumentCredentialsDAO.getCredentials(userId, docId);
    }

    @Override
    public UserDocumentCredential getUDCredentials(User user, Document document) {
        return userDocumentCredentialsDAO.getCredentials(user, document);
    }

    @Override
    public List<UserDocumentCredential> getCredentials(User user) {
        return userDocumentCredentialsDAO.getCredentials(user);
    }

    @Override
    public List<UserDocumentCredential> getCredentials(Document document) {
        return userDocumentCredentialsDAO.getCredentials(document);
    }

    @Override
    public boolean addCredentials(UserDocumentCredential credentials) {
        return userDocumentCredentialsDAO.addCredentials(credentials);
    }

    @Override
    public boolean updateCredentials(UserDocumentCredential credentials) {
        return userDocumentCredentialsDAO.update(credentials);
    }

    @Override
    public boolean delete(UserDocumentCredential userDocumentCredential) {
        return userDocumentCredentialsDAO.delete(userDocumentCredential);
    }
}
