package com.example.ytproj.repositries;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ytproj.entities.Likes;

public interface LikeRepo extends JpaRepository<Likes, Integer> {
    List<Likes> findByUid(int uid);

    List<Likes> findByPid(int pid);

    @Transactional
    void deleteByPid(int pid);
}
