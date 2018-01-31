package com.github.peterchen82.mybatis.service;

import com.github.peterchen82.mybatis.entity.Artcle;
import com.github.peterchen82.mybatis.mapper.ArtcleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 文章服务的实现类
 *
 * @author peterchen
 */
@Service
public class ArtcleServiceImpl implements ArtcleService {

    private final static Logger log = LoggerFactory.getLogger(ArtcleServiceImpl.class);

    @Autowired
    private ArtcleMapper artcleMapper;

    @Override
    public List<Artcle> getArtcles(int start, int limit) {
        long s = System.currentTimeMillis();
        List<Artcle> artcles = artcleMapper.list(start, limit);
        long e = System.currentTimeMillis();
        log.debug("耗时:" + (e - s) + "毫秒");
        log.debug("条数:" + artcles.size());
        return artcles;
    }

    @Override
    public Artcle get(Long id) {
        Artcle artcle = artcleMapper.getById(id);
        return artcle;
    }

    @Override
    public Artcle save(Artcle artcle) {
        if (artcle.getId() != null) {
            artcle.setId(null);
        }
        if (artcle.getCreated() == null) {
            artcle.setCreated(new Date());
        }
        artcleMapper.insert(artcle);
        return artcle;
    }

    @Override
    public void saveMany(List<Artcle> artcles) {
        if (artcles == null || artcles.size() <= 0) {
            return;
        }
        for (Artcle artcle : artcles) {
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
    public Artcle update(Artcle artcle) {
        if (artcle.getId() == null || artcle.getId() <= 0) {
            return null;
        }
        artcleMapper.update(artcle);
        return artcle;
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
