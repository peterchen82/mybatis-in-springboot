package com.github.peterchen82.mybatis.mapper;

import com.github.peterchen82.mybatis.entity.ArtcleEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface ArtcleMapper {

    //优化分页查询
    @Select("select artcles.* from artcles inner join ( select id from artcles order by created desc limit #{param1},#{param2} ) as tmp on tmp.id=artcles.id;")
    List<ArtcleEntity> getAll(int start,int limit);

    @Select("SELECT * FROM artcles WHERE id = #{id}")
    ArtcleEntity getOne(Long id);

    @Insert("INSERT INTO artcles(title,content,created) VALUES(#{title}, #{content}, #{created})")
    @Options(useGeneratedKeys=true, keyProperty="id", keyColumn="id")
    void insert(ArtcleEntity artcleEntity);

    void insertMany(List<ArtcleEntity> artcles);

    @Update("UPDATE artcles SET title=#{title},content=#{content},created=#{created} WHERE id =#{id}")
    void update(ArtcleEntity user);

    @Delete("DELETE FROM artcles WHERE id =#{id}")
    void delete(Long id);

    @Delete("TRUNCATE TABLE artcles")
    void deleteAll();
}
