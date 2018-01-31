package com.github.peterchen82.mybatis;


import com.github.peterchen82.mybatis.entity.Artcle;
import com.github.peterchen82.mybatis.service.ArtcleService;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * 文章单元测试基类
 *
 * @author peterchen
 */
public abstract class ArtcleBaseTest extends BaseTest{

    @Autowired
    protected ArtcleService artcleService;

    @Before
    public void before() {
        artcleService.deleteAll();
        insertArtcles(10);
    }

    protected void insertArtcles(int insertCount){
        if(insertCount<1) insertCount=1;
        List<Artcle> artcles=new ArrayList();
        for(int i=1;i<=insertCount;i++){
            Artcle artcle=new Artcle();
            artcle.setTitle("test-"+i);
            artcle.setContent("content-"+i);
            artcles.add(artcle);
        }
        artcleService.saveMany(artcles);
    }
    protected Artcle insertOne(){
        Artcle artcle=new Artcle();
        artcle.setTitle("test-one");
        artcle.setContent("content-one");
        return artcleService.save(artcle);
    }
}
