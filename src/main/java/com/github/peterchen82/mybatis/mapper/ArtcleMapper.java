package com.github.peterchen82.mybatis.mapper;

import com.github.peterchen82.mybatis.entity.Artcle;
import org.apache.ibatis.annotations.*;

import java.util.List;


/**
 * 文章实体的DAO
 *
 * @author peterchen
 */
public interface ArtcleMapper {

    /**
     * 根据指定的起止位置获取文章列表列表,这里对表内千万级记录做了分页sql优化
     *
     * @param start 开始的记录行数,从0开始
     * @param limit 获取的记录条数
     * @return 文章列表
     */
    @Select("select artcles.* from artcles inner join ( select id from artcles order by created desc limit #{param1},#{param2} ) as tmp on tmp.id=artcles.id;")
    List<Artcle> list(int start, int limit);

    /**
     * 根据id获取文章
     *
     * @param id 文章id
     * @return 文章
     */
    @Select("SELECT * FROM artcles WHERE id = #{id}")
    Artcle getById(Long id);

    /**
     * 新增一篇文章
     *
     * @param artcle 文章对象，包含自动生成的id
     */
    @Insert("INSERT INTO artcles(title,content,created) VALUES(#{title}, #{content}, #{created})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(Artcle artcle);

    /**
     * 批量新增一组文章
     *
     * @param artcles 需要新增的文章列表
     */
    void insertMany(List<Artcle> artcles);

    /**
     * 更新一篇文章
     *
     * @param artcle 文章对象，根据artcle.id更新
     */
    @Update("UPDATE artcles SET title=#{title},content=#{content},created=#{created} WHERE id =#{id}")
    void update(Artcle artcle);

    /**
     * 删除一篇文章
     *
     * @param id 根据指定id删除
     */
    @Delete("DELETE FROM artcles WHERE id =#{id}")
    void delete(Long id);

    /**
     * 删除所有文章
     */
    @Delete("TRUNCATE TABLE artcles")
    void deleteAll();
}
