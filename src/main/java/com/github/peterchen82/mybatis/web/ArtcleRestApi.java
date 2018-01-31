package com.github.peterchen82.mybatis.web;


import com.github.peterchen82.mybatis.entity.Artcle;
import com.github.peterchen82.mybatis.service.ArtcleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 文章的rest api实现
 *
 * @author peterchen
 */

@Api(value = "artcle", description = "账户文章的api")
@RestController
@RequestMapping(value = "/artcles")
public class ArtcleRestApi {

    @Autowired
    private ArtcleService artcleService;

    @ApiOperation(value = "获取文章列表", notes = "获取所有文章")
    @GetMapping("")
    public List<Artcle> getArtcles(@RequestParam int start, @RequestParam int limit) {
        List<Artcle> artcles = artcleService.getArtcles(start, limit);
        return artcles;
    }

    @ApiOperation(value = "获取文章详细信息", notes = "根据id来获取文章详细信息")
    @ApiImplicitParam(name = "id", paramType = "path", value = "文章ID", required = true)
    @GetMapping("/{id}")
    public Artcle get(@PathVariable Long id) {
        Artcle artcle = artcleService.get(id);
        return artcle;
    }

    @ApiOperation(value = "创建文章", notes = "创建文章")
    @ApiImplicitParam(name = "artcle", value = "文章对象", required = true, dataTypeClass = Artcle.class)
    @PostMapping("")
    public Artcle save(@RequestBody Artcle artcle) {
       return artcleService.save(artcle);
    }

    @ApiOperation(value = "更新文章", notes = "更新文章")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "artcle", value = "文章对象", required = true, dataTypeClass = Artcle.class)
    })
    @PutMapping
    public void update(@RequestBody Artcle artcle) {
        artcleService.update(artcle);
    }

    @ApiOperation(value = "删除文章", notes = "根据id来删除指定的文章")
    @ApiImplicitParam(name = "id", paramType = "path", value = "文章ID", required = true)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {
        artcleService.delete(id);
    }

}
