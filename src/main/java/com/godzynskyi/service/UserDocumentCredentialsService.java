package com.godzynskyi.service;

import com.godzynskyi.domain.Credentials;
import com.godzynskyi.domain.Document;
import com.godzynskyi.domain.User;
import com.godzynskyi.domain.UserDocumentCredential;

import java.util.List;

/**
 * Created by Java Developer on 11.10.2015.
 */
public interface UserDocumentCredentialsService {
    Credentials getCredentials(long userId, long docId);
    UserDocumentCredential getUDCredentials(User user, Document document);
    List<UserDocumentCredential> getCredentials(User user);
    List<UserDocumentCredential> getCredentials(Document document);
    boolean addCredentials(UserDocumentCredential uDCredentials);
    boolean updateCredentials(UserDocumentCredential credentials);
    boolean delete(UserDocumentCredential userDocumentCredential);
}
