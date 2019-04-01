package com.ssheld.giflib.service;

import com.ssheld.giflib.dao.GifDao;
import com.ssheld.giflib.model.Gif;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Author: Stephen Sheldon 4/1/2019
 */

@Service
public class GifServiceImpl implements GifService {
    @Autowired
    private GifDao gifDao;

    @Override
    public List<Gif> findAll() {
        return gifDao.findAll();
    }

    @Override
    public Gif findById(Long id) {
        return gifDao.findById(id);
    }

    @Override
    public void save(Gif gif, MultipartFile file) {
        try {
            gif.setBytes(file.getBytes());
            gifDao.save(gif);
        } catch (IOException e) {
            System.out.println("Unable to get byte array from uploaded file.");
        }
    }

    @Override
    public void delete(Gif gif) {
        gifDao.delete(gif);
    }
}
