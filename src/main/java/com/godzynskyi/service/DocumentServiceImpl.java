package com.godzynskyi.service;

import com.godzynskyi.dao.DocumentDAO;
import com.godzynskyi.domain.Comment;
import com.godzynskyi.domain.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
@Service
public class DocumentServiceImpl implements DocumentService {

    @Autowired
    DocumentDAO documentDao;

    @Override
    public boolean createDocument(Document document) {
        return documentDao.addDocument(document);
    }

    @Override
    public Document getDocument(long id) {
        Document res = documentDao.getDocument(id);
        List<Comment> comments = res.getComments();
        Collections.sort(comments);
        res.setComments(comments);
        return res;
    }

    @Override
    public List<Document> findDocuments(long userId) {
        return null;
    }

    @Override
    public List<Document> findDocuments(long userId, String pattern) {
        return documentDao.getDocuments(userId, pattern);
    }
}
