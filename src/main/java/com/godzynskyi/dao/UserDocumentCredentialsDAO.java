package com.godzynskyi.dao;

import com.godzynskyi.domain.Credentials;
import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;

import java.util.List;

/**
 * Created by Java Developer on 11.10.2015.
 */
public interface UserDocumentCredentialsDAO {
    Credentials getCredentials(long userId, long docId);
    UserDocumentCredential getCredentials(User user, Document document);
    List<UserDocumentCredential> getCredentials(User user);
    List<UserDocumentCredential> getCredentials(Document document);
    boolean addCredentials(UserDocumentCredential credentials);
    boolean update(UserDocumentCredential credentials);
    boolean delete(UserDocumentCredential userDocumentCredential);
}
