package com.tiji.center.controller;

import com.tiji.center.pojo.Url;
import com.tiji.center.service.UrlService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * url控制器层
 *
 * @author 贰拾壹
 */
@RestController
@CrossOrigin
@RequestMapping("/url")
public class UrlController {

    @Autowired
    private UrlService urlService;


    /**
     * 查询全部数据
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll() {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findAll());
    }

    /**
     * 根据ID查询
     *
     * @param id ID
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable String id) {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findById(id));
    }


    /**
     * 分页+多条件查询
     *
     * @param searchMap 查询条件封装
     * @param page      页码
     * @param size      页大小
     * @return 分页结果
     */
    @RequestMapping(value = "/search/{page}/{size}", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap, @PathVariable int page, @PathVariable int size) {
        Page<Url> pageList = urlService.findSearch(searchMap, page, size);
        return new Result(true, StatusCode.OK, "查询成功", new PageResult<Url>(pageList.getTotalElements(), pageList.getContent()));
    }

    /**
     * 根据条件查询
     *
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap) {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findSearch(searchMap));
    }

    /**
     * 增加
     *
     * @param url
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Url url) {
        urlService.add(url);
        return new Result(true, StatusCode.OK, "增加成功");
    }

    /**
     * 修改
     *
     * @param url
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Url url, @PathVariable String id) {
        url.setId(id);
        urlService.update(url);
        return new Result(true, StatusCode.OK, "修改成功");
    }

    /**
     * 删除
     *
     * @param id
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        urlService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }


    /**
     * 根据ids批量删除
     *
     * @param ids
     */
    @RequestMapping(value = "/deleteids", method = RequestMethod.POST)
    public Result deleteAllByIds(@RequestBody List<String> ids) {
        urlService.deleteAllByIds(ids);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    /**
     * 根据webinfoid查询
     *
     * @param webinfoid webinfoid
     * @return
     */
    @RequestMapping(value = "/webinfo/{webinfoid}", method = RequestMethod.GET)
    public Result findAllByWebinfoid(@PathVariable String webinfoid) {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findAllByWebinfoid(webinfoid));
    }

    /**
     * 根据webinfoids查询所有链接pojo
     *
     * @param webinfoids
     * @return
     */
    @RequestMapping(value = "/webinfo", method = RequestMethod.POST)
    public Result findAllByWebinfoIds(@RequestBody String[] webinfoids) {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findAllByWebinfoids(webinfoids));
    }

    /**
     * 根据webinfoids查询所有链接pojo，webinfoid替换成端口
     *
     * @param webinfoids
     * @return
     */
    @RequestMapping(value = "/webinfoids2port", method = RequestMethod.POST)
    public Result findAllByWebinfoIds2Port(@RequestBody String[] webinfoids) {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findAllByWebinfoIds2Port(webinfoids));
    }

    /**
     * 根据webinfoid查询
     *
     * @param webinfoid webinfoid
     * @return
     */
    @RequestMapping(value = "/links/{webinfoid}", method = RequestMethod.GET)
    public Result findLinksByWebinfoId(@PathVariable String webinfoid) {
        return new Result(true, StatusCode.OK, "查询成功", urlService.findLinksByWebinfoId(webinfoid));
    }

}
