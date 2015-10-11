package com.godzynskyi.service;

import com.godzynskyi.domain.Document;

import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
public interface DocumentService {
    boolean createDocument(Document document);
    Document getDocument(long id);
    List<Document> findDocuments(long userId);
    List<Document> findDocuments(long userId, String pattern);
    boolean deleteDocument(Document document);

}
