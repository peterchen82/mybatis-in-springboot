package com.github.peterchen82.mybatis.web;

import com.github.peterchen82.mybatis.ArtcleBaseTest;
import com.github.peterchen82.mybatis.entity.Artcle;
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

/**
 * 文章rest api测试
 *
 * @author peterchen
 */

@RunWith(SpringRunner.class)
public class ArtcleRestApiTest extends ArtcleBaseTest {
    @Autowired
    private TestRestTemplate template;

    @Test
    public void testGetArtcles() {
        Map<String, Integer> params = new HashMap();
        params.put("start", 0);
        params.put("limit", 3);
        List<Artcle> artclesPageOne = template.getForObject("/artcles?start={start}&limit={limit}", List.class, params);
        assertEquals(3, artclesPageOne.size());
        params.put("start", 3);
        params.put("limit", 20);
        List<Artcle> artclesPageTwo = template.getForObject("/artcles?start={start}&limit={limit}", List.class, params);
        assertEquals(7, artclesPageTwo.size());
    }

    @Test
    public void testGetOne() {
        Artcle artcleSaved = insertOne();

        Map<String, Long> params = new HashMap();
        params.put("id", artcleSaved.getId());
        Artcle artcle = template.getForObject("/artcles/{id}", Artcle.class, params);

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());
    }

    @Test
    public void testCreate() {
        Artcle artcleVO = new Artcle();
        artcleVO.setTitle("create-by-api");
        artcleVO.setContent("create-by-api");
        Artcle artcleSaved = template.postForObject("/artcles", artcleVO, Artcle.class);

        Map<String, Long> params = new HashMap();
        params.put("id", artcleSaved.getId());
        Artcle artcle = template.getForObject("/artcles/{id}", Artcle.class, params);

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());

        List<Artcle> artcles = template.getForObject("/artcles?start=0&limit=20", List.class);
        assertEquals(11, artcles.size());
    }

    @Test
    public void testUpdate() {
        Artcle artcleSaved = insertOne();
        Artcle artcle = template.getForObject("/artcles/" + artcleSaved.getId(), Artcle.class);

        artcle.setTitle("test-update");
        artcle.setContent("content-update");

        template.put("/artcles", artcle);

        Artcle artcleUpdated = template.getForObject("/artcles/" + artcle.getId(), Artcle.class);

        assertEquals(Long.valueOf(artcle.getId()), artcleUpdated.getId());
        assertEquals(artcle.getTitle(), artcleUpdated.getTitle());
        assertEquals(artcle.getContent(), artcleUpdated.getContent());
    }

    @Test
    public void testDelete() {
        Artcle artcleSaved = insertOne();

        Map<String, Long> params = new HashMap();
        params.put("id", artcleSaved.getId());
        Artcle artcle = template.getForObject("/artcles/{id}", Artcle.class, params);

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());

        List<Artcle> artcles = template.getForObject("/artcles?start=0&limit=20", List.class);
        assertEquals(11, artcles.size());

        template.delete("/artcles/" + artcle.getId());
        List<Artcle> artclesDeleted = template.getForObject("/artcles?start=0&limit=20", List.class);
        assertEquals(10, artclesDeleted.size());


    }

}
