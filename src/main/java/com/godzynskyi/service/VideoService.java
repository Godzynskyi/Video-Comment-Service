package com.godzynskyi.service;

import com.godzynskyi.domain.Video;

/**
 * Created by Java Developer on 07.10.2015.
 */
public interface VideoService {

    //add video with this url to DB if not exist and return it
    Video getVideo(String url);

    Video getVideo(long id);
}
