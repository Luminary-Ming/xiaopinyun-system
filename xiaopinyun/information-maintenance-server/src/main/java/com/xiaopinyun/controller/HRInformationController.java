package com.xiaopinyun.controller;

import com.xiaopinyun.pojo.HR;
import com.xiaopinyun.pojo.vo.PageResult;
import com.xiaopinyun.pojo.vo.Result;
import com.xiaopinyun.service.HRInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/hr")
public class HRInformationController {
    @Autowired
    private HRInformationService hrInformationService;

    /**
     * 根据 id 查询 HR 信息
     */
    @GetMapping("/{id}")
    public Result<HR> selectHRById(@PathVariable Integer id) {
        return hrInformationService.selectHRById(id);
    }

    /**
     * 条件查询、分页查询 HR 信息
     */
    @GetMapping
    public Result<PageResult<List<HR>>> selectHR(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sex", required = false) Integer sex,
            @RequestParam(name = "status", required = false) Integer status) {
        return hrInformationService.selectHR(currentPage, pageSize, name, sex, status);
    }

    /**
     * 添加 HR 信息
     */
    @PostMapping
    public Result<HR> addHR(@RequestBody HR hr) {
        return hrInformationService.addHR(hr);
    }

    /**
     * 修改 HR 信息
     */
    @PutMapping
    public Result<HR> updateHR(@RequestBody HR hr) {
        return hrInformationService.updateHR(hr);
    }

    /**
     * 根据 id 删除 HR 信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteHRById(@PathVariable Integer id) {
        return hrInformationService.deleteHRById(id);
    }
}