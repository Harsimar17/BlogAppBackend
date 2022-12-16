package com.example.ytproj.service;

import java.util.List;

import com.example.ytproj.entities.Likes;

public interface LikeService {
 

    int getLikesOfPost(int pid);

    Likes addLike(Likes l);
}
