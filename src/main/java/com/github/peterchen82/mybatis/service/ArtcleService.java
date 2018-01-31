package com.github.peterchen82.mybatis.service;

import com.github.peterchen82.mybatis.entity.Artcle;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 文章的Service对象，实现了文章的业务逻辑。
 * 添加了事务和缓存支持
 *
 * @author peterchen
 */
public interface ArtcleService {


    /**
     * 根据指定的起止位置获取文章列表列表,这里对表内千万级记录做了分页sql优化
     *
     * @param start 开始的记录行数,从0开始
     * @param limit 获取的记录条数
     * @return 文章列表
     */
    @Cacheable(cacheNames = "artcles")
    List<Artcle> getArtcles(int start, int limit);

    /**
     * 根据id获取文章
     *
     * @param id 文章id
     * @return 文章
     */
    Artcle get(Long id);

    /**
     * 新增一篇文章
     *
     * @param artcle 文章对象
     * @return 保存后的文章对象，包含自动生成的id
     */
    @CacheEvict(cacheNames = "artcles", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    Artcle save(Artcle artcle);


    /**
     * 批量新增一组文章
     *
     * @param artcles 需要新增的文章列表
     */
    @CacheEvict(cacheNames = "artcles", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    void saveMany(List<Artcle> artcles);


    /**
     * 更新一篇文章
     *
     * @param artcle 文章对象，根据artcle.id更新
     * @return 保存后的文章对象
     */
    @CacheEvict(cacheNames = "artcles", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    Artcle update(Artcle artcle);

    /**
     * 删除一篇文章
     *
     * @param id 根据指定id删除
     */
    @CacheEvict(cacheNames = "artcles", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    void delete(Long id);

    /**
     * 删除所有文章
     */
    @CacheEvict(cacheNames = "artcles", allEntries = true)
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, timeout = 36000, rollbackFor = Exception.class)
    void deleteAll();
}
