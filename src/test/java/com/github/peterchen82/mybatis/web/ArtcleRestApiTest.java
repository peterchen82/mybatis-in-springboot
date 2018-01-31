package com.github.peterchen82.mybatis.web;

import com.github.peterchen82.mybatis.ArtcleBaseTest;
import com.github.peterchen82.mybatis.entity.ArtcleEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


@RunWith(SpringRunner.class)
public class ArtcleRestApiTest extends ArtcleBaseTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testGetArtcles() {
        Map<String, Integer> params = new HashMap();
        params.put("start", 0);
        params.put("limit", 3);
        List<ArtcleEntity> artclesPageOne = template.getForObject("/artcles?start={start}&limit={limit}", List.class, params);
        assertEquals(3, artclesPageOne.size());
        params.put("start", 3);
        params.put("limit", 20);
        List<ArtcleEntity> artclesPageTwo = template.getForObject("/artcles?start={start}&limit={limit}", List.class, params);
        assertEquals(7, artclesPageTwo.size());
    }

    @Test
    public void testGetOne() {
        ArtcleEntity artcleSaved = insertOne();

        Map<String, Long> params = new HashMap();
        params.put("id", artcleSaved.getId());
        ArtcleEntity artcle = template.getForObject("/artcles/{id}", ArtcleEntity.class, params);

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());
    }

    @Test
    public void testCreate() {
        ArtcleEntity artcleVO=new ArtcleEntity();
        artcleVO.setTitle("create-by-api");
        artcleVO.setContent("create-by-api");
        ArtcleEntity artcleSaved=template.postForObject("/artcles",artcleVO,ArtcleEntity.class);

        Map<String, Long> params = new HashMap();
        params.put("id", artcleSaved.getId());
        ArtcleEntity artcle = template.getForObject("/artcles/{id}", ArtcleEntity.class, params);

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());

        List<ArtcleEntity> artcles = template.getForObject("/artcles?start=0&limit=20", List.class);
        assertEquals(11, artcles.size());
    }

    @Test
    public void testUpdate() {
        ArtcleEntity artcleSaved = insertOne();
        ArtcleEntity artcle = template.getForObject("/artcles/"+artcleSaved.getId(), ArtcleEntity.class);

        artcle.setTitle("test-update");
        artcle.setContent("content-update");

        template.put("/artcles",artcle);

        ArtcleEntity artcleUpdated = template.getForObject("/artcles/"+artcle.getId(), ArtcleEntity.class);

        assertEquals(Long.valueOf(artcle.getId()), artcleUpdated.getId());
        assertEquals(artcle.getTitle(), artcleUpdated.getTitle());
        assertEquals(artcle.getContent(), artcleUpdated.getContent());
    }

    @Test
    public void testDelete() {
        ArtcleEntity artcleSaved = insertOne();

        Map<String, Long> params = new HashMap();
        params.put("id", artcleSaved.getId());
        ArtcleEntity artcle = template.getForObject("/artcles/{id}", ArtcleEntity.class, params);

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());

        List<ArtcleEntity> artcles = template.getForObject("/artcles?start=0&limit=20", List.class);
        assertEquals(11, artcles.size());

        template.delete("/artcles/"+artcle.getId());
        List<ArtcleEntity> artclesDeleted = template.getForObject("/artcles?start=0&limit=20", List.class);
        assertEquals(10, artclesDeleted.size());


    }

}
