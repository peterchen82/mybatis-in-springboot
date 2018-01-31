package com.github.peterchen82.mybatis.service;

import com.github.peterchen82.mybatis.entity.ArtcleEntity;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ArtcleService {
    @Cacheable(cacheNames = "artcles")
    List<ArtcleEntity> getArtcles(int start, int limit);

    ArtcleEntity get(Long id);

    @CacheEvict(cacheNames="artcles", allEntries=true)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    ArtcleEntity save(ArtcleEntity artcleEntity);

    @CacheEvict(cacheNames="artcles", allEntries=true)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    void saveMany(List<ArtcleEntity> artcles);

    @CacheEvict(cacheNames="artcles", allEntries=true)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    ArtcleEntity update(ArtcleEntity artcleEntity);

    @CacheEvict(cacheNames="artcles", allEntries=true)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    void delete(Long id);

    @CacheEvict(cacheNames="artcles", allEntries=true)
    @Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT,timeout=36000,rollbackFor=Exception.class)
    void deleteAll();
}
