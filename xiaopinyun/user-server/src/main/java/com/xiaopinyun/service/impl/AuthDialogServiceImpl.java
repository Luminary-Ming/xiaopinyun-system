package com.xiaopinyun.service.impl;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xiaopinyun.bean.dto.LoginDTO;
import com.xiaopinyun.bean.dto.Result;
import com.xiaopinyun.bean.dto.UserDTO;
import com.xiaopinyun.bean.po.Advantage;
import com.xiaopinyun.bean.po.Applicant;
import com.xiaopinyun.bean.po.Educational;
import com.xiaopinyun.bean.po.HR;
import com.xiaopinyun.bean.po.JobExpectation;
import com.xiaopinyun.bean.po.ProjectExperience;
import com.xiaopinyun.bean.po.User;
import com.xiaopinyun.bean.po.WorkExperience;
import com.xiaopinyun.bean.vo.UserVO;
import com.xiaopinyun.client.ApplicantFeignClient;
import com.xiaopinyun.mapper.AdvantageMapper;
import com.xiaopinyun.mapper.ApplicantInfoMapper;
import com.xiaopinyun.mapper.AuthDialogMapper;
import com.xiaopinyun.mapper.EducationalMapper;
import com.xiaopinyun.mapper.HRInfoMapper;
import com.xiaopinyun.mapper.JobExpectationMapper;
import com.xiaopinyun.mapper.ProjectExperienceMapper;
import com.xiaopinyun.mapper.WorkExperienceMapper;
import com.xiaopinyun.service.AuthDialogService;
import com.xiaopinyun.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthDialogServiceImpl extends ServiceImpl<AuthDialogMapper, User> implements AuthDialogService {
    @Autowired
    private AuthDialogMapper authDialogMapper;
    @Autowired
    private ApplicantInfoMapper applicantInfoMapper;
    @Autowired
    private JobExpectationMapper jobExpectationMapper;
    @Autowired
    private AdvantageMapper advantageMapper;
    @Autowired
    private EducationalMapper educationalMapper;
    @Autowired
    private WorkExperienceMapper workExperienceMapper;
    @Autowired
    private ProjectExperienceMapper projectExperienceMapper;
    @Autowired
    private HRInfoMapper hrInfoMapper;
    @Autowired
    private ApplicantFeignClient applicantFeignClient;

    @Override
    public Result<UserDTO> login(LoginDTO login) {
        if (StrUtil.isBlank(login.getUsername()) && StrUtil.isBlank(login.getPassword())) {
            return Result.paramError("请填写账号或密码");
        }
        if (StrUtil.isBlank(login.getRole())) {
            return Result.paramError("请选择身份");
        }
        String username = login.getUsername();
        String password = login.getPassword();
        String role = login.getRole();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username).eq("password", password).eq("role", role);
        User user = authDialogMapper.selectOne(wrapper);
        if (user == null) {
            return Result.error("登录失败：账号或密码错误！");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserVO(new UserVO(user));
        Applicant applicant = applicantInfoMapper.selectById(user.getPkApplicant());
        userDTO.setName(applicant.getName());
        userDTO.setProfileImg(applicant.getProfileImg());
        Map<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        String token = TokenUtil.getToken(map);
        userDTO.setToken(token);
        return Result.ok(userDTO);
    }

    @Override
    public Result<UserDTO> register(LoginDTO login) {
        if (StrUtil.isBlank(login.getUsername()) && StrUtil.isBlank(login.getPassword())) {
            return Result.paramError("请填写账号或密码");
        }
        if (StrUtil.isBlank(login.getRole())) {
            return Result.paramError("请选择身份");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("role", login.getRole()).eq("username", login.getUsername());
        List<User> users = authDialogMapper.selectList(wrapper);
        if (!users.isEmpty()) {
            return Result.error("注册失败：账号已经被使用！");
        }

        User user = new User();
        user.setUsername(login.getUsername());
        user.setPassword(login.getPassword());
        user.setRole(Integer.valueOf(login.getRole()));
        authDialogMapper.insert(user);
        if (login.getRole().equals("0")) {
            // 这里本来想使用OpenFeign调用，但是被token过滤器拦截了，报401了，所以只能换种方法了
            // Applicant applicant = applicantFeignClient.save();
            Applicant applicant = new Applicant();
            applicant.setDr(0);
            applicantInfoMapper.insert(applicant);
            user.setPkApplicant(applicant.getId());
            authDialogMapper.updateById(user);
            // 新增空记录
            insertOfRegister(applicant.getId());
        } else if (login.getRole().equals("1")) {
            HR hr = new HR();
            hr.setDr(0);
            hrInfoMapper.insert(hr);
            user.setPkHr(hr.getId());
            authDialogMapper.updateById(user);
        } else {
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setUserVO(new UserVO(user));
        HashMap<String, Object> map = new HashMap<>();
        map.put("userId", user.getId());
        String token = TokenUtil.getToken(map);  // 获得token
        userDTO.setToken(token);
        return Result.ok(userDTO);
    }

    /**
     * 注册完，新增一条个人信息、求职期望、个人优势（专业技能、获奖荣誉、自我评价）、教育背景、工作实习/经历、项目经历空记录
     * 后续点完成按钮只执行修改操作，点添加按钮还是走添加逻辑
     */
    private void insertOfRegister(Long pkApplicant) {
        // 新增求职期望
        JobExpectation jobExpectation = new JobExpectation();
        jobExpectation.setPkApplicant(pkApplicant);
        jobExpectationMapper.insert(jobExpectation);
        // 新增个人优势
        Advantage advantage = new Advantage();
        advantage.setPkApplicant(pkApplicant);
        advantageMapper.insert(advantage);
        // 新增教育背景
        Educational educational = new Educational();
        educational.setPkApplicant(pkApplicant);
        educationalMapper.insert(educational);
        // 新增工作实习/经历
        WorkExperience workExperience = new WorkExperience();
        workExperience.setPkApplicant(pkApplicant);
        workExperienceMapper.insert(workExperience);
        // 新增项目经历
        ProjectExperience projectExperience = new ProjectExperience();
        projectExperience.setPkApplicant(pkApplicant);
        projectExperienceMapper.insert(projectExperience);
    }
}
