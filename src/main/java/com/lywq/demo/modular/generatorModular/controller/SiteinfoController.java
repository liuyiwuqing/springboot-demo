package com.lywq.demo.modular.generatorModular.controller;

import com.lywq.demo.common.modle.RetResult;
import com.lywq.demo.common.modle.RetResponse;
import com.lywq.demo.modular.generatorModular.model.Siteinfo;
import com.lywq.demo.modular.generatorModular.service.SiteinfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.annotations.*;

import javax.annotation.Resource;
import java.util.List;

/**
* @Description: SiteinfoController类
* @author lywq WED
* @date 2020/02/17 16:59
*/
@RestController
@RequestMapping("/siteinfo")
@Api(value = "Siteinfo", tags = {"Siteinfo操作接口"}, description = "Siteinfo")
public class SiteinfoController {

@Resource
private SiteinfoService siteinfoService;

    @PostMapping("/insert")
    @ApiOperation(value = "新增Siteinfo", tags = {"Siteinfo操作接口"}, notes = "新增Siteinfo")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "siteinfo", value = "siteinfo", required = true)
    })
    public RetResult<Integer> insert(Siteinfo siteinfo) throws Exception{
        Integer state = siteinfoService.insert(siteinfo);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/deleteById")
    @ApiOperation(value = "删除Siteinfo", tags = {"Siteinfo操作接口"}, notes = "删除Siteinfo")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public RetResult<Integer> deleteById(@RequestParam String id) throws Exception {
        Integer state = siteinfoService.deleteById(id);
        return RetResponse.makeOKRsp(state);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改Siteinfo", tags = {"Siteinfo操作接口"}, notes = "修改Siteinfo")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "siteinfo", value = "siteinfo", required = true)
    })
    public RetResult<Integer> update(Siteinfo siteinfo) throws Exception {
        Integer state = siteinfoService.update(siteinfo);
        return RetResponse.makeOKRsp(state);
    }

    @GetMapping("/selectById")
    @ApiOperation(value = "查询Siteinfo", tags = {"Siteinfo操作接口"}, notes = "查询Siteinfo")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "id", value = "id", required = true)
    })
    public RetResult<Siteinfo> selectById(@RequestParam String id) throws Exception {
        Siteinfo siteinfo = siteinfoService.selectById(id);
        return RetResponse.makeOKRsp(siteinfo);
    }

    /**
    * @Description: 分页查询
    * @param page 页码
    * @param size 每页条数
    * @Reutrn RetResult<PageInfo<Siteinfo>>
    */
    @GetMapping("/list")
    @ApiOperation(value = "分页查询Siteinfo", tags = {"Siteinfo操作接口"}, notes = "分页查询Siteinfo")
    @ApiImplicitParams({
    @ApiImplicitParam(name = "page", value = "当前页码"),
    @ApiImplicitParam(name = "size", value = "每页显示条数")
    })
    public RetResult<PageInfo<Siteinfo>> list(@RequestParam(defaultValue = "1") Integer page,
                                                            @RequestParam(defaultValue = "10") Integer size) throws Exception {
        PageHelper.startPage(page, size);
        List<Siteinfo> list = siteinfoService.selectAll();
        PageInfo<Siteinfo> pageInfo = new PageInfo<Siteinfo>(list);
        return RetResponse.makeOKRsp(pageInfo);
    }
}