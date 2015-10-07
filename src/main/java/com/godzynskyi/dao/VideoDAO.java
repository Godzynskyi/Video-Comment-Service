package com.godzynskyi.dao;

import com.godzynskyi.domain.Video;

/**
 * Created by Java Developer on 07.10.2015.
 */
public interface VideoDAO {
    boolean addVideo(Video video);
    Video addVideo(String url);
    Video getVideo(long id);
    Video getVideo(String url);
}
