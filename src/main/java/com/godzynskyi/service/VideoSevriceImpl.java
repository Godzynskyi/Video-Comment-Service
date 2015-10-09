package com.godzynskyi.service;

import com.godzynskyi.dao.VideoDAO;
import com.godzynskyi.domain.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Java Developer on 07.10.2015.
 */
@Service
public class VideoSevriceImpl implements VideoService {
    @Autowired
    VideoDAO videoDAO;

    @Override
    public Video getVideo(String url) {
        Video video = videoDAO.addVideo(url);
        if(video == null) {
            video = videoDAO.getVideo(url);
        }
        return video;
    }

    @Override
    public Video getVideo(long id) {
        return videoDAO.getVideo(id);
    }
}
