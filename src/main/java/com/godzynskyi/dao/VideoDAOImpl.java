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
            em.persist(video);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Video addVideo(String url) {
        try {
            Video video = new Video(url);
            em.persist(video);
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
        Query query = em.createQuery("SELECT v FROM Video v WHERE v.url= \"" + url + "\"");
        List<Video> list = (List<Video>) query.getResultList();
        return list.get(0);
    }
}
