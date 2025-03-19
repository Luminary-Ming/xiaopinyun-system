package com.xiaopinyun.controller;

import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.vo.HRVO;
import com.xiaopinyun.bean.dto.PageResult;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.service.HRInfoService;
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
public class HRInfoController {
    @Autowired
    private HRInfoService hrInformationService;

    /**
     * 根据 id 查询 HR 信息
     */
    @GetMapping("/{id}")
    public Result<HRVO> selectHRById(@PathVariable Integer id) {
        return hrInformationService.queryVOById(id);
    }

    /**
     * 条件查询、分页查询 HR 信息
     */
    @GetMapping
    public Result<PageResult<List<HRVO>>> selectHR(
            @RequestParam(name = "currentPage", defaultValue = "1") Integer currentPage,
            @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize,
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sex", required = false) Integer sex,
            @RequestParam(name = "status", required = false) Integer status) {
        return hrInformationService.queryVO(currentPage, pageSize, name, sex, status);
    }

    /**
     * 添加 HR 信息
     */
    @PostMapping
    public Result<HRVO> addHR(@RequestBody HR hr) {
        return hrInformationService.saveVO(hr);
    }

    /**
     * 修改 HR 信息
     */
    @PutMapping
    public Result<HRVO> updateHR(@RequestBody HR hr) {
        return hrInformationService.updateVO(hr);
    }

    /**
     * 根据 id 删除 HR 信息
     */
    @DeleteMapping("/{id}")
    public Result<Void> deleteHRById(@PathVariable Integer id) {
        return hrInformationService.deleteVOById(id);
    }
}
