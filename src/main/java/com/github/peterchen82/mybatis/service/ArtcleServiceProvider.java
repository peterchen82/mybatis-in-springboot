package com.github.peterchen82.mybatis.service;

import com.github.peterchen82.mybatis.entity.ArtcleEntity;
import com.github.peterchen82.mybatis.mapper.ArtcleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
public class ArtcleServiceProvider implements ArtcleService {

    private final static Logger log = LoggerFactory.getLogger(ArtcleServiceProvider.class);

    @Autowired
    private ArtcleMapper artcleMapper;

    @Override
    public List<ArtcleEntity> getArtcles(int start, int limit) {
        long s = System.currentTimeMillis();
        List<ArtcleEntity> artcles = artcleMapper.getAll(start, limit);
        long e = System.currentTimeMillis();
        log.debug("耗时:" + (e - s) + "毫秒");
        log.debug("条数:" + artcles.size());
        return artcles;
    }

    @Override
    public ArtcleEntity get(Long id) {
        ArtcleEntity artcle = artcleMapper.getOne(id);
        return artcle;
    }

    @Override
    public ArtcleEntity save(ArtcleEntity artcleEntity) {
        if (artcleEntity.getId() != null) {
            artcleEntity.setId(null);
        }
        if (artcleEntity.getCreated() == null) {
            artcleEntity.setCreated(new Date());
        }
        artcleMapper.insert(artcleEntity);
        return artcleEntity;
    }

    @Override
    public void saveMany(List<ArtcleEntity> artcles) {
        if (artcles == null || artcles.size() <= 0) {
            return;
        }
        for (ArtcleEntity artcle : artcles) {
            if (artcle.getId() != null) {
                artcle.setId(null);
            }
            if (artcle.getCreated() == null) {
                artcle.setCreated(new Date());
            }
        }
        artcleMapper.insertMany(artcles);

    }

    @Override
    public ArtcleEntity update(ArtcleEntity artcleEntity) {
        if (artcleEntity.getId() == null || artcleEntity.getId() <= 0) {
            return null;
        }
        artcleMapper.update(artcleEntity);
        return artcleEntity;
    }

    @Override
    public void delete(Long id) {
        if (id == null || id <= 0) {
            return;
        }
        artcleMapper.delete(id);
    }

    @Override
    public void deleteAll() {
        artcleMapper.deleteAll();
    }
}
