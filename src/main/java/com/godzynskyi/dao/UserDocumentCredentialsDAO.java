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
    List<UserDocumentCredential> getCredentials(User user);
    List<UserDocumentCredential> getCredentials(Document document);
}
