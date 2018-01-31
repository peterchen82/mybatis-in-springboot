package com.github.peterchen82.mybatis.service;

import com.github.peterchen82.mybatis.ArtcleBaseTest;
import com.github.peterchen82.mybatis.entity.ArtcleEntity;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
public class ArtcleServiceTest extends ArtcleBaseTest {

    @Test
    public void testSaveMany() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
    }

    @Test
    public void testSaveManyForIdNotNull() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        List<ArtcleEntity> artcles=new ArrayList();
        for(int i=1;i<=10;i++){
            ArtcleEntity artcle=new ArtcleEntity();
            artcle.setId(Long.valueOf(i));
            artcle.setTitle("test-many-"+i);
            artcle.setContent("content-many-"+i);
            artcles.add(artcle);
        }
        artcleService.saveMany(artcles);
        assertEquals(20, artcleService.getArtcles(0, 20).size());
    }
    @Test
    public void testSaveManyForCreatedNotNull() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        List<ArtcleEntity> artcles=new ArrayList();
        for(int i=1;i<=10;i++){
            ArtcleEntity artcle=new ArtcleEntity();
            artcle.setTitle("test-many-"+i);
            artcle.setContent("content-many-"+i);
            artcle.setCreated(new Date());
            artcles.add(artcle);
        }
        artcleService.saveMany(artcles);
        assertEquals(20, artcleService.getArtcles(0, 20).size());
    }

    @Test
    public void testSaveManyForNull() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        artcleService.saveMany(null);
        assertEquals(10, artcleService.getArtcles(0, 20).size());
    }
    @Test
    public void testSaveManyForZero() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        artcleService.saveMany(new ArrayList());
        assertEquals(10, artcleService.getArtcles(0, 20).size());
    }
    @Test
    public void testDeleteAll() {
        artcleService.deleteAll();
        assertEquals(0, artcleService.getArtcles(0, 20).size());
    }

    @Test
    public void testGetArtcles() {
        List<ArtcleEntity> artcles = artcleService.getArtcles(0, 20);
        assertEquals(10, artcles.size());
        for (ArtcleEntity artcle : artcles) {
            assertNotNull(artcle);
            assertNotNull(artcle.getId());
            assertNotNull(artcle.getCreated());
            assertTrue(artcle.getTitle().startsWith("test-"));
            assertTrue(artcle.getContent().startsWith("content-"));
        }
    }

    @Test
    public void testGetArtclesCache() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        ArtcleEntity one=insertOne();
        assertEquals(11, artcleService.getArtcles(0, 20).size());
        ArtcleEntity two=insertOne();
        assertEquals(12, artcleService.getArtcles(0, 20).size());
        artcleService.delete(one.getId());
        assertEquals(11, artcleService.getArtcles(0, 20).size());
        artcleService.delete(two.getId());
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        artcleService.deleteAll();
        assertEquals(0, artcleService.getArtcles(0, 20).size());
    }

    @Test
    public void testSaveAndGetOne() {
        ArtcleEntity artcleSaved = insertOne();
        ArtcleEntity artcle = artcleService.get(artcleSaved.getId());

        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
//        assertEquals(artcleSaved.getCreated(),artcle.getCreated());
        assertNotNull(artcle.getCreated());
    }

    @Test
    public void testSaveCustomId() {

        ArtcleEntity artcleSaved=new ArtcleEntity();
        artcleSaved.setId(1000L);   //手工设置id的保存后应被忽略
        artcleSaved.setTitle("test-one");
        artcleSaved.setContent("content-one");
        artcleService.save(artcleSaved);

        ArtcleEntity artcle = artcleService.get(artcleSaved.getId());

        assertNotNull(artcle.getId());
        assertNotEquals(Long.valueOf(1000),artcle.getId());
        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());
    }

    @Test
    public void testSaveCustomCreated() {

        ArtcleEntity artcleSaved=new ArtcleEntity();
        artcleSaved.setCreated(new Date());
        artcleSaved.setTitle("test-one");
        artcleSaved.setContent("content-one");
        artcleService.save(artcleSaved);

        ArtcleEntity artcle = artcleService.get(artcleSaved.getId());

        assertNotNull(artcle.getId());
        assertNotEquals(Long.valueOf(1000),artcle.getId());
        assertEquals(Long.valueOf(artcleSaved.getId()), artcle.getId());
        assertEquals(artcleSaved.getTitle(), artcle.getTitle());
        assertEquals(artcleSaved.getContent(), artcle.getContent());
        assertNotNull(artcle.getCreated());
    }

    @Test
    public void testUpdate() {
        ArtcleEntity artcleSaved = insertOne();
        ArtcleEntity artcle = artcleService.get(artcleSaved.getId());
        artcle.setTitle("test-update");
        artcle.setContent("content-update");
        artcleService.update(artcle);
        ArtcleEntity artcleUpdated = artcleService.get(artcle.getId());

        assertEquals(Long.valueOf(artcleUpdated.getId()), artcle.getId());
        assertEquals(artcleUpdated.getTitle(), artcle.getTitle());
        assertEquals(artcleUpdated.getContent(), artcle.getContent());

    }
    @Test
    public void testUpdateForIdIsNull() {
        ArtcleEntity artcleSaved = insertOne();
        ArtcleEntity artcle = artcleService.get(artcleSaved.getId());
        artcle.setId(null);
        artcle.setTitle("test-update");
        artcle.setContent("content-update");
        artcleService.update(artcle);
        ArtcleEntity artcleUpdated = artcleService.get(artcleSaved.getId());

        assertEquals(Long.valueOf(artcleUpdated.getId()), artcleSaved.getId());
        assertNotEquals(artcleUpdated.getTitle(), artcle.getTitle());
        assertNotEquals(artcleUpdated.getContent(), artcle.getContent());

    }
    @Test
    public void testUpdateForIdIsZero() {
        ArtcleEntity artcleSaved = insertOne();
        ArtcleEntity artcle = artcleService.get(artcleSaved.getId());
        artcle.setId(0L);
        artcle.setTitle("test-update");
        artcle.setContent("content-update");
        artcleService.update(artcle);
        ArtcleEntity artcleUpdated = artcleService.get(artcleSaved.getId());

        assertEquals(Long.valueOf(artcleUpdated.getId()), artcleSaved.getId());
        assertNotEquals(artcleUpdated.getTitle(), artcle.getTitle());
        assertNotEquals(artcleUpdated.getContent(), artcle.getContent());

    }
    @Test
    public void testDelete() {
        ArtcleEntity artcleSaved = insertOne();
        assertEquals(11, artcleService.getArtcles(0, 20).size());
        artcleService.delete(artcleSaved.getId());
        assertEquals(10, artcleService.getArtcles(0, 20).size());
    }
    @Test
    public void testDeleteForIdNull() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        artcleService.delete(null);
        assertEquals(10, artcleService.getArtcles(0, 20).size());
    }

    @Test
    public void testDeleteForZero() {
        assertEquals(10, artcleService.getArtcles(0, 20).size());
        artcleService.delete(0L);
        assertEquals(10, artcleService.getArtcles(0, 20).size());
    }

}
