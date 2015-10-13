package com.godzynskyi.dao;

import com.godzynskyi.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * Created by Java Developer on 07.10.2015.
 */
@Repository
public class VideoDAOImpl implements VideoDAO {

    @Autowired
    private EntityManager em;

    @Override
    public boolean addVideo(Video video) {
        try {
            em.getTransaction().begin();
            em.persist(video);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if(em.getTransaction().isActive()) em.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Video addVideo(String url) {
        try {
            Video video = new Video(url);
            addVideo(video);
            return video;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Video getVideo(long id) {
        return em.find(Video.class, id);
    }

    @Override
    public Video getVideo(String url) {
        Query query = em.createQuery("SELECT v FROM Video v WHERE v.url= :url");
        query.setParameter("url", url);
        List<Video> list = (List<Video>) query.getResultList();
        if(list.size() == 0) return null;
        return list.get(0);
    }
}
