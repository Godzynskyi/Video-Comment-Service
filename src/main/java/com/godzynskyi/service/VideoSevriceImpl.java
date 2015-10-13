package com.godzynskyi.service;

import com.godzynskyi.dao.VideoDAO;
import com.godzynskyi.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by Java Developer on 07.10.2015.
 */
@Service
public class VideoSevriceImpl implements VideoService {
    @Autowired
    VideoDAO videoDAO;

    @Transactional
    @Override
    public Video getVideo(String url) {
        Video video = videoDAO.getVideo(url);
        if(video == null) {
            videoDAO.addVideo(url);
        }
        return videoDAO.getVideo(url);
    }

    @Override
    public Video getVideo(long id) {
        return videoDAO.getVideo(id);
    }
}
