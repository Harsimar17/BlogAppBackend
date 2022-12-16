package com.example.ytproj.serviceimpl;

import java.util.List;

import org.springframework.aop.AopInvocationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ytproj.entities.Likes;
import com.example.ytproj.repositries.LikeRepo;
import com.example.ytproj.service.LikeService;

@Service
public class LikeServiceImpl implements LikeService {
    @Autowired
    LikeRepo lr;

    public boolean check(int uid, int pid) {
        List<Likes> findByUid = lr.findByUid(uid);
        List<Likes> findByPid = lr.findByPid(pid);
        boolean u = false;
        boolean p = false;
        for (Likes l : findByUid) {
            if (l.getUid() == uid && l.getPid() == pid) {
                u = true;
            }
        }
        for (Likes l : findByPid) {
            if (l.getPid() == pid && l.getUid() == uid) {
                p = true;
            }
        }
        if (u == false) {
            return false;
        }
        if (u == true && p == false) {
            return false;
        }
        return true;
    }

    @Override
    public int getLikesOfPost(int pid) {
        // TODO Auto-generated method stub
        List<Likes> findAll = lr.findAll();
        int cnt = 0;
        for (Likes l : findAll) {
            if (l.getPid() == pid) {
                cnt++;
            }
        }
        return cnt;
    }

    @Override
    public Likes addLike(Likes l) {
        // TODO Auto-generated method stub
        if (check(l.getUid(), l.getPid()) == false) {
            Likes save = lr.save(l);
            return save;
        }
        List<Likes> findAll = lr.findAll();
        for (Likes l1 : findAll) {
            if (l1.getPid() == l.getPid() && l1.getUid() == l.getUid()) {
                lr.delete(l1);
            }
        }
        return null;
    }

    public boolean isLiked(int pid, int uid) {
        List<Likes> findAll = lr.findAll();
        for (Likes l : findAll) {
            if (l.getPid() == pid && l.getUid() == uid) {
                return true;
            }
        }
        return false;
    }
}
