<!-- 我的简历 -->
<template>
    <div class="resume-box">
        <div class="resume-list">
            <div class="resume-list-box">
                <el-col :span="12">
                    <el-menu active-text-color="#000" background-color="#fff" class="el-menu-vertical-demo" default-active="2" text-color="#000" @open="handleOpen" @close="handleClose">
                        <div class="mb-2">
                            <h3>简历目录</h3>
                        </div>
                        <el-menu-item index="1" @click="scrollToSection('section1')">
                            <el-icon><User /></el-icon>
                            <span>个人信息</span>
                        </el-menu-item>
                        <el-menu-item index="2" @click="scrollToSection('section2')">
                            <el-icon><Histogram /></el-icon>
                            <span>专业技能</span>
                        </el-menu-item>
                        <el-menu-item index="3" @click="scrollToSection('section3')">
                            <el-icon><document /></el-icon>
                            <span>求职期望</span>
                        </el-menu-item>
                        <el-menu-item index="4" @click="scrollToSection('section4')">
                            <el-icon><School /></el-icon>
                            <span>教育背景</span>
                        </el-menu-item>
                        <el-menu-item index="5" @click="scrollToSection('section5')">
                            <el-icon><OfficeBuilding /></el-icon>
                            <span>工作/实习经历</span>
                        </el-menu-item>
                        <el-menu-item index="6" @click="scrollToSection('section6')">
                            <el-icon><Monitor /></el-icon>
                            <span>项目经历</span>
                        </el-menu-item>
                        <el-menu-item index="7" @click="scrollToSection('section7')">
                            <el-icon><GoldMedal /></el-icon>
                            <span>获奖荣誉</span>
                        </el-menu-item>
                        <el-menu-item index="8" @click="scrollToSection('section8')">
                            <el-icon><EditPen /></el-icon>
                            <span>自我评价</span>
                        </el-menu-item>
                    </el-menu>
                </el-col>
            </div>
        </div>
        <div class="resume-editor">
            <div class="resume-editor-header">
                <h3>我的在线简历</h3>
            </div>

            <div class="resume-editor-person" id="section1">
                <div v-if="personFlag">
                    <div class="resume-editor-person-image">
                        <el-upload :show-file-list="false" :action="uploadUrl" :headers="headers" :on-success="handleAvatarSuccess" :before-upload="beforeAvatarUpload">
                            <el-avatar shape="square" :size="80" :src="applicantView.profileImgUrl" style="vertical-align: middle; border-radius: 10px" />
                        </el-upload>
                    </div>
                    <div class="resume-editor-person-info">
                        <div class="op">
                            <a class="link-edit">
                                <span @click="onUpdate(0)">
                                    <el-icon><EditPen /></el-icon>
                                    编辑
                                </span>
                            </a>
                        </div>
                        <div class="resume-editor-person-name">{{ applicantView.name }}</div>
                        <div class="info-flex">
                            <div class="info-flex-item">
                                <div class="info-labels">
                                    <p>
                                        <span class="prev-line"><i class="fz-resume fz-experience"></i>{{ applicantView.identity }}</span>
                                        <span class="prev-line"><i class="fz-resume fz-degree"></i>{{ applicantView.education }}</span>
                                        <span class="prev-line"><i class="fz-resume fz-status"></i>{{ convertStatus(applicantView.status) }}</span>
                                    </p>
                                    <p>
                                        <span class="prev-line"><i class="fz-resume fz-tel"></i>{{ applicantView.telephone }}</span>
                                        <span class="prev-line"><i class="fz-resume fz-weixin"></i>{{ applicantView.telephone }}</span>
                                        <span class="prev-line"><i class="fz-resume fz-mail"></i>{{ applicantView.email }}</span>
                                    </p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- 个人信息表单 -->
                <div v-else>
                    <el-form :model="applicantView" style="max-width: 800px">
                        <el-form-item label="姓名">
                            <el-input v-model="applicantView.name" placeholder="输入姓名" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <el-form-item label="性别">
                            <el-select v-model="applicantView.sex" placeholder="选择性别">
                                <el-option label="男" value="0" />
                                <el-option label="女" value="1" />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="出生年月">
                            <el-input v-model="applicantView.birthday" placeholder="出生年月" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <el-form-item label="居住地">
                            <el-input v-model="applicantView.address" placeholder="居住地" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <el-form-item label="求职状态">
                            <el-select v-model="applicantView.status" placeholder="选择求职状态">
                                <el-option label="离校-随时到岗" value="0" />
                                <el-option label="在校-月内到岗" value="1" />
                                <el-option label="在校-看看机会" value="2" />
                                <el-option label="在校-暂不考虑" value="3" />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="手机号">
                            <el-input v-model="applicantView.telephone" placeholder="输入手机号" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <el-form-item label="电子邮箱">
                            <el-input v-model="applicantView.email" placeholder="输入电子邮箱" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <el-form-item>
                            <el-button @click="onCancel(0)">取消</el-button>
                            <el-button @click="onSubmit(0)" class="success">完成</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div class="resume-editor-skill" id="section2">
                <div class="title">专业技能</div>
                <div class="resume-editor-skill-info" v-if="skillFlag">
                    <div class="op">
                        <a class="link-edit">
                            <span @click="onUpdate(1)">
                                <el-icon><EditPen /></el-icon>
                                编辑
                            </span>
                        </a>
                    </div>
                    <div class="info-text">{{ advantage.majorSkill }}</div>
                </div>
                <div v-else>
                    <el-form :model="advantage" style="max-width: 800px">
                        <el-form-item>
                            <el-input v-model="advantage.majorSkill" maxlength="1000" rows="10" spellcheck="false" style="width: 700px" placeholder="输入专业技能" show-word-limit type="textarea" />
                            <el-button @click="onCancel(1)" class="cancel">取消</el-button>
                            <el-button @click="onSubmit(1)" class="success">完成</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>

            <div class="resume-editor-expectation" id="section3">
                <div class="title">
                    求职期望
                    <span @click="addJobExpectation()">
                        <el-icon class="add-icon"><CirclePlus /></el-icon>
                        添加
                    </span>
                </div>
                <div class="resume-editor-expectation-info" v-if="expectationFlag">
                    <div v-for="(jobExpectation, index) in jobExpectationView" :key="index" class="info-item">
                        <div class="info-text">
                            <el-icon><Briefcase /></el-icon>
                            {{ jobExpectation.expectedJob }}&emsp;|&nbsp;&nbsp;
                            <el-icon><Coin /></el-icon>
                            {{ jobExpectation.salary }}&emsp;|&nbsp;&nbsp;
                            <el-icon><Location /></el-icon>
                            {{ jobExpectation.district }}
                        </div>
                        <div class="op">
                            <a class="link-edit">
                                <span @click="onJobExpectationUpdate(index)">
                                    <el-icon><EditPen /></el-icon>
                                    编辑
                                </span>
                                <span @click="onJobExpectationDelete(index)" class="del-icon">
                                    <el-icon><DeleteFilled /></el-icon>
                                    删除
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <el-form :model="currentJobExpectation" style="max-width: 800px">
                        <el-form-item>
                            <el-form-item label="求职类型">
                                <el-select v-model="currentJobExpectation.jobType" placeholder="选择求职类型">
                                    <el-option label="全职" value="0" />
                                    <el-option label="兼职" value="1" />
                                    <el-option label="实习" value="2" />
                                </el-select>
                            </el-form-item>
                            <el-form-item label="期望行业">
                                <el-input v-model="currentJobExpectation.expectedIndustry" placeholder="期望行业" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="期望城市">
                                <el-input v-model="currentJobExpectation.district" placeholder="期望城市" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="期望职位">
                                <el-input v-model="currentJobExpectation.expectedJob" placeholder="期望职位" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="薪资要求">
                                <el-select v-model="currentJobExpectation.salary" placeholder="选择薪资">
                                    <el-option label="面议" value="面议" />
                                    <el-option label="1k - 3k" value="1k - 3k" />
                                    <el-option label="3k - 5k" value="3k - 5k" />
                                    <el-option label="5k - 7k" value="5k - 7k" />
                                    <el-option label="7k - 9k" value="7k - 9k" />
                                    <el-option label="9k - 11k" value="9k - 11k" />
                                    <el-option label="11k - 13k" value="11k - 13k" />
                                    <el-option label="13k - 15k" value="13k - 15k" />
                                    <el-option label="15k 以上" value="15k 以上" />
                                </el-select>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="onCancel(2)" class="cancel">取消</el-button>
                                <el-button @click="onjobExpectationViewubmit()" class="success">完成</el-button>
                            </el-form-item>
                        </el-form-item>
                    </el-form>
                </div>
            </div>

            <div class="resume-editor-education" id="section4">
                <div class="title">教育背景</div>
                <div class="resume-editor-education-info" v-if="educationFlag">
                    <div class="op">
                        <a class="link-edit">
                            <span @click="onUpdate(3)">
                                <el-icon><EditPen /></el-icon>
                                编辑
                            </span>
                        </a>
                    </div>
                    <div class="info-text">
                        <h3>
                            <el-icon><School /></el-icon>{{ backgroundView.name }}
                        </h3>
                        <div class="time">{{ backgroundView.startTime }} - {{ backgroundView.endTime }}</div>
                        <div></div>
                        <div class="major">{{ backgroundView.major }}</div>
                        <div class="qualification">{{ convertQualification(backgroundView.qualification) }}</div>
                        <div class="majorCourse">主修课程：{{ backgroundView.majorCourse }}</div>
                    </div>
                </div>
                <div v-else>
                    <el-form :model="backgroundView" style="max-width: 800px">
                        <el-form-item label="学校名称">
                            <el-input v-model="backgroundView.name" placeholder="输入学校名称" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <el-form-item label="学历">
                            <el-select v-model="backgroundView.qualification" placeholder="选择学历">
                                <el-option label="初中及以下" value="0" />
                                <el-option label="中专" value="1" />
                                <el-option label="高中" value="2" />
                                <el-option label="大专" value="3" />
                                <el-option label="本科" value="4" />
                                <el-option label="硕士" value="5" />
                                <el-option label="博士" value="6" />
                            </el-select>
                        </el-form-item>
                        <el-form-item label="专业">
                            <el-input v-model="backgroundView.major" placeholder="输入专业" style="max-width: 300px; height: 40px" />
                        </el-form-item>
                        <div class="time">
                            <el-form-item label="入学时间" class="startTime">
                                <el-select v-model="backgroundView.startTime" placeholder="选择入学时间">
                                    <el-option label="2019" value="2019" />
                                    <el-option label="2020" value="2020" />
                                    <el-option label="2021" value="2021" />
                                    <el-option label="2022" value="2022" />
                                    <el-option label="2023" value="2023" />
                                    <el-option label="2024" value="2024" />
                                    <el-option label="2025" value="2025" />
                                </el-select>
                            </el-form-item>
                            <el-form-item label="毕业时间" class="endTime">
                                <el-select v-model="backgroundView.endTime" placeholder="选择毕业时间">
                                    <el-option label="2022" value="2022" />
                                    <el-option label="2023" value="2023" />
                                    <el-option label="2024" value="2024" />
                                    <el-option label="2025" value="2025" />
                                    <el-option label="2026" value="2026" />
                                    <el-option label="2027" value="2027" />
                                    <el-option label="2028" value="2028" />
                                </el-select>
                            </el-form-item>
                        </div>
                        <el-form-item label="主修课程">
                            <el-input
                                v-model="backgroundView.majorCourse"
                                maxlength="200"
                                rows="5"
                                spellcheck="false"
                                style="width: 700px"
                                placeholder="输入主修课程"
                                show-word-limit
                                type="textarea"
                            />
                        </el-form-item>
                        <el-form-item>
                            <el-button @click="onCancel(3)" class="cancel">取消</el-button>
                            <el-button @click="onSubmit(3)" class="success">完成</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div class="resume-editor-work" id="section5">
                <div class="title">
                    工作/实习经历
                    <span @click="addWorkExperience()">
                        <el-icon class="add-icon"><CirclePlus /></el-icon>
                        添加
                    </span>
                </div>
                <div class="resume-editor-work-info" v-if="workFlag">
                    <div v-for="(workExperience, index) in workExperienceView" :key="index" class="info-item">
                        <div class="info-text">
                            <div class="info-text-top">
                                <div>
                                    <div class="work-name">{{ workExperience.companyName }}</div>
                                    <div class="job-name">{{ workExperience.jobName }}</div>
                                </div>
                                <div class="time">{{ workExperience.startTime }} - {{ workExperience.endTime }}</div>
                            </div>
                            <div class="description-box">
                                <div class="neirong">内容：</div>
                                <div class="description-text">{{ workExperience.jobContent }}</div>
                            </div>
                        </div>
                        <div class="op">
                            <a class="link-edit">
                                <span @click="onWorkExperienceUpdate(index)">
                                    <el-icon><EditPen /></el-icon>
                                    编辑
                                </span>
                                <span @click="onWorkExperienceDelete(index)" class="del-icon">
                                    <el-icon><DeleteFilled /></el-icon>
                                    删除
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <el-form :model="currentWorkExperience" style="max-width: 800px">
                        <el-form-item>
                            <el-form-item label="公司名称">
                                <el-input v-model="currentWorkExperience.companyName" placeholder="输入公司名称" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="所属行业">
                                <el-input v-model="currentWorkExperience.industryType" placeholder="输入所属行业" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="所属部门">
                                <el-input v-model="currentWorkExperience.department" placeholder="输入所属部门" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="职位名称">
                                <el-input v-model="currentWorkExperience.jobName" placeholder="输入职位名称" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="入职时间">
                                <el-input v-model="currentWorkExperience.startTime" placeholder="入职时间" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="离职时间">
                                <el-input v-model="currentWorkExperience.endTime" placeholder="离职时间" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="工作内容">
                                <el-input
                                    v-model="currentWorkExperience.jobContent"
                                    maxlength="3000"
                                    rows="10"
                                    spellcheck="false"
                                    style="width: 690px"
                                    placeholder="输入工作内容"
                                    show-word-limit
                                    type="textarea"
                                />
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="onCancel(5)" class="cancel">取消</el-button>
                                <el-button @click="onworkExperienceViewubmit()" class="success">完成</el-button>
                            </el-form-item>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div class="resume-editor-project" id="section6">
                <div class="title">
                    项目经历
                    <span @click="addProjectExperience()">
                        <el-icon class="add-icon"><CirclePlus /></el-icon>
                        添加
                    </span>
                </div>
                <div class="resume-editor-project-info" v-if="projectFlag">
                    <div v-for="(projectExperience, index) in projectExperienceView" :key="index" class="info-item">
                        <div class="info-text">
                            <div class="info-text-top">
                                <div>
                                    <div class="project-name">{{ projectExperience.projectName }}</div>
                                    <div class="project-role">{{ projectExperience.projectRole }}</div>
                                </div>
                                <div class="time">{{ projectExperience.startTime }} - {{ projectExperience.endTime }}</div>
                            </div>
                            <div class="description-box">
                                <div class="neirong">内容：</div>
                                <div class="description-text">{{ projectExperience.description }}</div>
                            </div>
                        </div>
                        <div class="op">
                            <a class="link-edit">
                                <span @click="onProjectExperienceUpdate(index)">
                                    <el-icon><EditPen /></el-icon>
                                    编辑
                                </span>
                                <span @click="onProjectExperienceDelete(index)" class="del-icon">
                                    <el-icon><DeleteFilled /></el-icon>
                                    删除
                                </span>
                            </a>
                        </div>
                    </div>
                </div>
                <div v-else>
                    <el-form :model="currentProjectExperience" style="max-width: 800px">
                        <el-form-item>
                            <el-form-item label="项目名称">
                                <el-input v-model="currentProjectExperience.projectName" placeholder="输入项目名称" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="项目角色">
                                <el-input v-model="currentProjectExperience.projectRole" placeholder="输入项目角色" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="项目开始时间">
                                <el-input v-model="currentProjectExperience.startTime" placeholder="开始时间" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="项目结束时间">
                                <el-input v-model="currentProjectExperience.endTime" placeholder="结束时间" style="max-width: 300px; height: 40px" />
                            </el-form-item>
                            <el-form-item label="主修课程">
                                <el-input
                                    v-model="currentProjectExperience.description"
                                    maxlength="3000"
                                    rows="10"
                                    spellcheck="false"
                                    style="width: 690px"
                                    placeholder="输入项目描述"
                                    show-word-limit
                                    type="textarea"
                                />
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="onCancel(4)" class="cancel">取消</el-button>
                                <el-button @click="onprojectExperienceViewubmit()" class="success">完成</el-button>
                            </el-form-item>
                        </el-form-item>
                    </el-form>
                </div>
            </div>

            <div class="resume-editor-honor" id="section7">
                <div class="title">获奖荣誉</div>
                <div class="resume-editor-honor-info" v-if="honorFlag">
                    <div class="op">
                        <a class="link-edit">
                            <span @click="onUpdate(6)">
                                <el-icon><EditPen /></el-icon>
                                编辑
                            </span>
                        </a>
                    </div>
                    <div class="info-text">{{ advantage.honor }}</div>
                </div>
                <div v-else>
                    <el-form :model="advantage" style="max-width: 800px">
                        <el-form-item>
                            <el-input v-model="advantage.honor" maxlength="1000" rows="5" spellcheck="false" style="width: 700px" placeholder="输入获奖荣誉" show-word-limit type="textarea" />
                        </el-form-item>
                        <el-form-item>
                            <el-button @click="onCancel(6)" class="cancel">取消</el-button>
                            <el-button @click="onSubmit(6)" class="success">完成</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
            <div class="resume-editor-evaluate" id="section8">
                <div class="title">自我评价</div>
                <div class="resume-editor-evaluate-info" v-if="evaluateFlag">
                    <div class="op">
                        <a class="link-edit">
                            <span @click="onUpdate(7)">
                                <el-icon><EditPen /></el-icon>
                                编辑
                            </span>
                        </a>
                    </div>
                    <div class="info-text">{{ advantage.selfEvaluation }}</div>
                </div>
                <div v-else>
                    <el-form :model="advantage" style="max-width: 800px">
                        <el-form-item>
                            <el-input v-model="advantage.selfEvaluation" maxlength="1000" rows="5" spellcheck="false" style="width: 700px" placeholder="输入自我评价" show-word-limit type="textarea" />
                        </el-form-item>
                        <el-form-item>
                            <el-button @click="onCancel(7)" class="cancel">取消</el-button>
                            <el-button @click="onSubmit(7)" class="success">完成</el-button>
                        </el-form-item>
                    </el-form>
                </div>
            </div>
        </div>
        <div class="resume-file">
            <el-col :span="12">
                <el-menu active-text-color="#000" background-color="#fff" class="el-menu-vertical-demo_resume-file" default-active="2" text-color="#000" @open="handleOpen" @close="handleClose">
                    <div class="mb-2">
                        <h3>
                            附件管理<el-icon><Paperclip /></el-icon>
                        </h3>
                        <el-upload :show-file-list="false" :action="uploadUrl" :headers="headers" :on-success="handleFileSuccess" :before-upload="beforeFileUpload">
                            <el-icon style="font-size: 20px"><Plus /></el-icon>
                        </el-upload>
                    </div>

                    <div class="resume-wenjian">文件（{{ attachmentView.length }}/3）</div>
                    <el-menu-item index="9" v-for="(attachment, index) in attachmentView" :key="index" v-if="attachmentView.length">
                        <img class="pdf_img" src="/src/assets/images/icon-img/icon-pdf.png" @click="previewFile(index)" />
                        <div class="resume-info" :title="attachment.name" @click="previewFile(index)">
                            <div class="resume-name">{{ attachment.name }}</div>
                            <div class="resume-font">{{ attachment.size }}kb 更新于 {{ attachment.ts }}</div>
                        </div>
                        <el-popconfirm class="box-item" :icon="InfoFilled" icon-color="#626AEF" title="对此附件的操作" placement="right">
                            <template #reference>
                                <span class="iconfont icon-file-menu"> </span>
                            </template>
                            <template #actions="{ confirm, cancel }">
                                <el-button @click="download(index)" style="width: 43px; height: 24px; border-radius: 4px">下载</el-button>
                                <el-button @click="confirmRemove(index)" style="width: 43px; height: 24px; border-radius: 4px" type="danger">删除</el-button>
                            </template>
                        </el-popconfirm>
                    </el-menu-item>
                </el-menu>
            </el-col>
        </div>
    </div>
</template>

<script setup>
import { ref, reactive, onMounted } from "vue";
import { Document, Menu as IconMenu, Location, Setting } from "@element-plus/icons-vue";
import { ElMessage } from "element-plus";
import { InfoFilled } from "@element-plus/icons-vue";
import { studentApi } from "@/api/student";
import { uploadApi } from "@/api/upload";
import { computed } from "vue";
import { useUserStore } from "@/store/userStore";
const userStore = useUserStore();
/* ------------------------------------- 查询API -------------------------------------- */
onMounted(async () => {
    // await 异步操作的关键字，它的作用是 等待一个 Promise 对象完成
    const response = await studentApi.getApplicant(userStore.pkApplicant);
    // 复制属性，直接给 applicantView={...} 赋值会失去响应式
    if (response.code == 200) {
        Object.assign(applicantView, {
            ...response.data.applicantVO,
            identity: response.data.identity || "",
            education: response.data.education || "",
        });
    }

    // 个人优势（专业技能、获奖荣誉、自我评价）
    const respAdvantage = await studentApi.getAdvantage(userStore.pkApplicant);
    if (respAdvantage.code == 200) {
        Object.assign(advantage, respAdvantage.data);
    }

    // 求职期望
    const respJobExpectation = await studentApi.getJobExpectation(userStore.pkApplicant);
    if (respJobExpectation.code == 200) {
        Object.assign(jobExpectationView, respJobExpectation.data);
    }

    // 教育背景
    const respBackground = await studentApi.getBackground(userStore.pkApplicant);
    if (respBackground.code == 200) {
        Object.assign(backgroundView, respBackground.data);
    }

    // 工作/实习经历
    const respWorkExperience = await studentApi.getWorkExperience(userStore.pkApplicant);
    if (respWorkExperience.code == 200) {
        Object.assign(workExperienceView, respWorkExperience.data);
    }

    // 项目经历
    const respProjectExperience = await studentApi.getProjectExperience(userStore.pkApplicant);
    if (respProjectExperience.code == 200) {
        Object.assign(projectExperienceView, respProjectExperience.data);
    }

    // 附件
    const respAttachment = await uploadApi.getAttachment(userStore.pkApplicant);
    if (respAttachment.code == 200) {
        Object.assign(attachmentView, respAttachment.data);
    }

    // 如果是新注册用户，则展开所有表单
    if (userStore.flag == "register") {
        personFlag.value = false;
        skillFlag.value = false;
        honorFlag.value = false;
        evaluateFlag.value = false;
        expectationFlag.value = false;
        educationFlag.value = false;
        projectFlag.value = false;
        workFlag.value = false;

        // 个人信息
        if (response.data.applicantVO.name) {
            personFlag.value = true;
        }

        // 个人优势（专业技能、获奖荣誉、自我评价）
        if (respAdvantage.data.majorSkill) {
            skillFlag.value = true;
        }
        if (respAdvantage.data.honor) {
            honorFlag.value = true;
        }
        if (respAdvantage.data.selfEvaluation) {
            evaluateFlag.value = true;
        }

        // 求职期望
        if (respJobExpectation.data[0].expectedJob) {
            expectationFlag.value = true;
        }

        // 教育背景
        if (respBackground.data.name) {
            educationFlag.value = true;
        }

        // 工作/实习经历
        if (respWorkExperience.data[0].companyName) {
            workFlag.value = true;
        }

        // 项目经历
        if (respProjectExperience.data[0].projectName) {
            projectFlag.value = true;
        }
    }
});

/* ------------------------------------- 锚点 -------------------------------------- */
const scrollToSection = (sectionId) => {
    const element = document.getElementById(sectionId);
    if (element) {
        element.scrollIntoView({
            behavior: "smooth",
            block: "start",
        });
    }
};

/* ------------------------------------- 表单显示隐藏标记 -------------------------------------- */
let personFlag = ref(true);
let skillFlag = ref(true);
let expectationFlag = ref(true);
let educationFlag = ref(true);
let projectFlag = ref(true);
let workFlag = ref(true);
let honorFlag = ref(true);
let evaluateFlag = ref(true);

/*------------------------------------- 换头像 ------------------------------------- */
// 计算属性获取headers
const headers = computed(() => ({
    Authorization: userStore.token,
}));
const uploadUrl = "http://127.0.0.1:9999/upload-server/upload";
const handleAvatarSuccess = async (response, uploadFile) => {
    // applicantView.profileImgUrl = URL.createObjectURL(uploadFile.raw);
    applicantView.profileImgUrl = response.data;
    const resp = await studentApi.updateApplicant(applicantView);
    if (resp.code == 200) {
        localStorage.setItem("profileImg", response.data);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};
const beforeAvatarUpload = (rawFile) => {
    if (rawFile.type !== "image/jpeg" && rawFile.type !== "image/png") {
        ElMessage.error("不是图片格式！");
        return false;
    } else if (rawFile.size / 1024 / 1024 > 5) {
        ElMessage.error("大小超过 5MB!");
        return false;
    }
    return true;
};
/*------------------------------------- 上传附件 -------------------------------------*/
let attachmentView = reactive([]);

let addAttachmentView = reactive({
    id: "",
    pkApplicant: userStore.pkApplicant,
    resumePDF: "",
    ts: "",
    name: "",
    size: "",
});

const handleFileSuccess = async (response, uploadFile) => {
    addAttachmentView.resumePDF = response.data;
    addAttachmentView.name = uploadFile.name;
    addAttachmentView.size = (uploadFile.size / 1024).toFixed(1); // 保留一位小数
    const resp = await uploadApi.addAttachment(addAttachmentView);
    if (resp.code == 200) {
        addAttachmentView.ts = resp.data.ts;
        attachmentView.push({ ...addAttachmentView, id: resp.data.id });
        await uploadApi.getAttachment(userStore.pkApplicant);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

const beforeFileUpload = (rawFile) => {
    if (rawFile.type !== "application/pdf") {
        ElMessage.error("不是 PDF 格式！");
        return false;
    } else if (rawFile.size / 1024 / 1024 > 5) {
        ElMessage.error("大小超过 5MB!");
        return false;
    }
    return true;
};

// 删除附件
const confirmRemove = async (index) => {
    const resp = await uploadApi.deleteAttachmentById(attachmentView[index].id);
    if (resp.code == 200) {
        const pdfUrl = attachmentView[index].resumePDF;
        const filename = pdfUrl.split("/filebucket/")[1];
        await uploadApi.deleteFile(filename);
        attachmentView.splice(index, 1);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

// 下载附件
const download = async (index) => {
    // 获取文件名
    const pdfUrl = attachmentView[index].resumePDF;
    const filename = pdfUrl.split("/filebucket/")[1];
    const resp = await uploadApi.downloadFile(filename);

    // 创建Blob对象
    const blob = new Blob([resp], { type: "application/pdf" });

    // 创建下载链接
    const url = URL.createObjectURL(blob);
    const a = document.createElement("a");
    a.href = url;
    a.download = attachmentView[index].name; // 设置下载文件名

    // 触发点击下载
    document.body.appendChild(a);
    a.click();

    // 清理
    setTimeout(() => {
        document.body.removeChild(a);
        URL.revokeObjectURL(url);
    }, 100);
};

// 预览附件
const previewFile = async (index) => {
    const pdfUrl = attachmentView[index].resumePDF;
    const filename = pdfUrl.split("/filebucket/")[1];
    const resp = await uploadApi.previewFile(filename);
    if (resp.code == 200) {
        window.open(resp.data);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};
/* ------------------------------------- 个人信息 -------------------------------------- */
let applicantView = reactive({
    id: userStore.pkApplicant,
    profileImgUrl: userStore.profileImg,
    name: "",
    sex: "",
    birthday: "",
    address: "",
    status: "",
    telephone: "",
    email: "",
    identity: "",
    education: "",
});

// 转换求职状态
const convertStatus = function (status) {
    switch (status) {
        case "0":
            return "离校-随时到岗";
        case "1":
            return "在校-月内到岗";
        case "2":
            return "在校-看看机会";
        case "3":
            return "在校-暂不考虑";
    }
};

// 完成按钮
const applicantSubmit = async function () {
    if (applicantView.id) {
        const resp = await studentApi.updateApplicant(applicantView);
        if (resp.code == 200) {
            localStorage.setItem("name", resp.data.applicantVO.name);
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    } else {
        const resp = await studentApi.addApplicant(applicantView);
        if (resp.code == 200) {
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    }
};

/* ------------------------------------- 求职期望 -------------------------------------- */
let jobExpectationView = reactive([]);

let currentJobExpectation = reactive({
    id: "",
    pkApplicant: userStore.pkApplicant,
    jobType: "",
    expectedIndustry: "",
    district: "",
    expectedJob: "",
    salary: "",
});

let currentIndex = ref(0);

// 求职期望添加、编辑、删除、完成按钮
const addJobExpectation = function () {
    Object.assign(currentJobExpectation, {
        id: "",
        pkApplicant: userStore.pkApplicant,
        jobType: "",
        expectedIndustry: "",
        district: "",
        expectedJob: "",
        salary: "",
    });
    currentIndex.value = null;
    expectationFlag.value = false;
};

const onJobExpectationUpdate = function (index) {
    expectationFlag.value = false;
    currentIndex.value = index;
    Object.assign(currentJobExpectation, jobExpectationView[index]);
    console.log("编辑", index);
};
const onJobExpectationDelete = async function (index) {
    const resp = await studentApi.deleteJobExpectation(jobExpectationView[index].id);
    if (resp.code == 200) {
        jobExpectationView.splice(index, 1);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

const onjobExpectationViewubmit = async function () {
    if (currentIndex.value == null) {
        const resp = await studentApi.addJobExpectation(currentJobExpectation);
        jobExpectationView.push({ ...currentJobExpectation, id: resp.data.id });
        if (resp.code == 200) {
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    } else {
        if (userStore.flag == "register") {
            currentJobExpectation.id = jobExpectationView[currentIndex.value].id;
            jobExpectationView[currentIndex.value] = { ...currentJobExpectation };
        } else {
            jobExpectationView[currentIndex.value] = { ...currentJobExpectation };
        }
        const resp = await studentApi.updateJobExpectation(currentJobExpectation);
        if (resp.code == 200) {
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    }
    expectationFlag.value = true;
};

/* ------------------------------------- 教育经历 -------------------------------------- */
let backgroundView = reactive({
    id: "",
    pkApplicant: userStore.pkApplicant,
    name: "",
    qualification: "",
    major: "",
    startTime: "",
    endTime: "",
    majorCourse: "",
});

// 转换学历
const convertQualification = function (status) {
    switch (status) {
        case "0":
            return "初中及以下";
        case "1":
            return "中专";
        case "2":
            return "高中";
        case "3":
            return "大专";
        case "4":
            return "本科";
        case "5":
            return "硕士";
        case "6":
            return "博士";
    }
};

const educationSubmit = async () => {
    const resp = await studentApi.updateBackground(backgroundView);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};
/* ------------------------------------- 项目经历 -------------------------------------- */
let projectExperienceView = reactive([]);

let currentProjectExperience = reactive({
    id: "",
    pkApplicant: userStore.pkApplicant,
    projectName: "",
    projectRole: "",
    startTime: "",
    endTime: "",
    description: "",
});

let currentProjectExperienceIndex = ref(0);

// 项目经历添加、编辑、删除、完成按钮
const addProjectExperience = function () {
    Object.assign(currentProjectExperience, {
        id: "",
        pkApplicant: userStore.pkApplicant,
        projectName: "",
        projectRole: "",
        startTime: "",
        endTime: "",
        description: "",
    });
    currentProjectExperienceIndex.value = null;
    projectFlag.value = false;
};

const onProjectExperienceUpdate = function (index) {
    projectFlag.value = false;
    currentProjectExperienceIndex.value = index;
    Object.assign(currentProjectExperience, projectExperienceView[index]);
};

const onProjectExperienceDelete = async function (index) {
    const resp = await studentApi.deleteProjectExperienceById(projectExperienceView[index].id);
    if (resp.code == 200) {
        projectExperienceView.splice(index, 1);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

const onprojectExperienceViewubmit = async function () {
    if (currentProjectExperienceIndex.value == null) {
        const resp = await studentApi.addProjectExperience(currentProjectExperience);
        if (resp.code == 200) {
            projectExperienceView.push({ ...currentProjectExperience, id: resp.data.id });
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    } else {
        if (userStore.flag == "register") {
            currentProjectExperience.id = projectExperienceView[currentProjectExperienceIndex.value].id;
            projectExperienceView[currentProjectExperienceIndex.value] = { ...currentProjectExperience };
        } else {
            projectExperienceView[currentProjectExperienceIndex.value] = { ...currentProjectExperience };
        }
        const resp = await studentApi.updateProjectExperience(currentProjectExperience);
        if (resp.code == 200) {
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    }
    projectFlag.value = true;
};

/* ------------------------------------- 工作/实习经历 -------------------------------------- */
let workExperienceView = reactive([]);

let currentWorkExperience = reactive({
    id: "",
    pkApplicant: userStore.pkApplicant,
    companyName: "",
    industryType: "",
    department: "",
    jobName: "",
    startTime: "",
    endTime: "",
    jobContent: "",
});

let currentWorkExperienceIndex = ref(0);

// 工作/实习经历添加、编辑、删除、完成按钮
const addWorkExperience = function () {
    Object.assign(currentWorkExperience, {
        id: "",
        pkApplicant: userStore.pkApplicant,
        companyName: "",
        industryType: "",
        department: "",
        jobName: "",
        startTime: "",
        endTime: "",
        jobContent: "",
    });
    currentWorkExperienceIndex.value = null;
    workFlag.value = false;
};

const onWorkExperienceUpdate = function (index) {
    workFlag.value = false;
    currentWorkExperienceIndex.value = index;
    Object.assign(currentWorkExperience, workExperienceView[index]);
};

const onWorkExperienceDelete = async function (index) {
    const resp = await studentApi.deleteWorkExperienceById(workExperienceView[index].id);
    if (resp.code == 200) {
        workExperienceView.splice(index, 1);
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};

const onworkExperienceViewubmit = async function () {
    if (currentWorkExperienceIndex.value == null) {
        const resp = await studentApi.addWorkExperience(currentWorkExperience);
        if (resp.code == 200) {
            workExperienceView.push({ ...currentWorkExperience, id: resp.data.id });
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    } else {
        if (userStore.flag == "register") {
            currentWorkExperience.id = workExperienceView[currentWorkExperienceIndex.value].id;
            workExperienceView[currentWorkExperienceIndex.value] = { ...currentWorkExperience };
        } else {
            workExperienceView[currentWorkExperienceIndex.value] = { ...currentWorkExperience };
        }
        const resp = await studentApi.updateWorkExperience(currentWorkExperience);
        if (resp.code == 200) {
            ElMessage.success(resp.message);
        } else {
            ElMessage.error(resp.message);
        }
    }
    workFlag.value = true;
};
/* ------------------------------------- 个人优势（专业技能、获奖荣誉、自我评价） -------------------------------------- */
let advantage = reactive({
    id: "",
    pkApplicant: userStore.pkApplicant,
    majorSkill: "",
    honor: "",
    selfEvaluation: "",
});

const advantageSubmit = async () => {
    const resp = await studentApi.updateAdvantage(advantage);
    if (resp.code == 200) {
        ElMessage.success(resp.message);
    } else {
        ElMessage.error(resp.message);
    }
};
/* ------------------------------------- 编辑、确定、取消按钮 -------------------------------------- */
const onUpdate = function (index) {
    switch (index) {
        case 0:
            return (personFlag.value = false);
        case 1:
            return (skillFlag.value = false);
        case 2:
            return (expectationFlag.value = false);
        case 3:
            return (educationFlag.value = false);
        case 4:
            return (projectFlag.value = false);
        case 5:
            return (workFlag.value = false);
        case 6:
            return (honorFlag.value = false);
        case 7:
            return (evaluateFlag.value = false);
    }
};

const onSubmit = function (index) {
    switch (index) {
        case 0:
            applicantSubmit();
            return (personFlag.value = true);
        case 1:
            advantageSubmit();
            return (skillFlag.value = true);
        case 2:
            return (expectationFlag.value = true);
        case 3:
            educationSubmit();
            return (educationFlag.value = true);
        case 4:
            return (projectFlag.value = true);
        case 5:
            return (workFlag.value = true);
        case 6:
            advantageSubmit();
            return (honorFlag.value = true);
        case 7:
            advantageSubmit();
            return (evaluateFlag.value = true);
    }
};

const onCancel = function (index) {
    switch (index) {
        case 0:
            return (personFlag.value = true);
        case 1:
            return (skillFlag.value = true);
        case 2:
            return (expectationFlag.value = true);
        case 3:
            return (educationFlag.value = true);
        case 4:
            return (projectFlag.value = true);
        case 5:
            return (workFlag.value = true);
        case 6:
            return (honorFlag.value = true);
        case 7:
            return (evaluateFlag.value = true);
    }
};
</script>

<style scoped>
.resume-box {
    width: 100%;
    height: auto;
    display: flex;
    justify-content: center;
    background: linear-gradient(to bottom, #e1f2f4, #f6f6f8);
    padding-top: 20px;
}

.resume-editor {
    width: 800px;
    height: 100%;
    border-radius: 15px;
    margin: 0 20px 80px 20px;
    background-color: #ffffff;
    margin-bottom: 300px;
}

.resume-editor .resume-editor-header {
    box-sizing: border-box;
    width: 100%;
    height: 53px;
    background-color: #f6f6f8;
    border-radius: 15px 15px 0 0;
}

.resume-editor .resume-editor-header h3 {
    font-size: 14px;
    color: #999;
    font-weight: 400;
    padding: 20px 0 0 40px;
}

/* ---------------------------- 个人信息start --------------------------------------- */
.resume-editor .resume-editor-person {
    position: relative;
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 40px;
}

/* --------------------------- applicant 表单 -------------------------------- */
.resume-editor-person .el-form {
    background-color: #f8f8f8;
    padding-left: 40px;
    padding-top: 10px;
    border-radius: 15px;
}
.resume-editor-person .el-form .el-form-item {
    width: 50%;
    display: inline-block;
}

.el-form .el-form-item .el-select {
    width: 300px;
    top: -5px;
}

:deep(.el-select__wrapper) {
    min-height: 40px;
    margin-top: 5px;
}

.el-button {
    width: 100px;
    height: 35px;
    border-radius: 8px;
    margin-right: 10px;
}

.success {
    background-color: #00bebd;
    color: #fff;
}

/* --------------------------- applicant 表单end -------------------------------- */

.resume-editor .resume-editor-person .resume-editor-person-image {
    display: block;
    position: absolute;
    right: 80px;
    top: 40px;
    z-index: 1;
}

.resume-editor .resume-editor-person .resume-editor-person-name {
    color: #222;
    height: 33px;
    line-height: 33px;
    font-size: 24px;
    font-weight: 500;
}

.op {
    opacity: 0;
    position: absolute;
    display: block;
    right: 15px;
    top: 12px;
}

.op a {
    display: inline-block;
    line-height: 24px;
    padding: 0 5px;
    color: #98a9a9;
}

.op a:hover {
    color: #00a7a7;
}

.op .link-edit {
    font-size: 14px;
}

.op .link-edit span .el-icon {
    margin-right: 3px;
}

.resume-editor-person-info {
    position: relative;
    width: 550px;
    margin-right: 116px;
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}

.resume-editor-person-info:hover {
    background-color: #f8f8f8;
}

.resume-editor-person-info:hover .op {
    opacity: 1;
}

.resume-editor-person .resume-editor-person-info .info-flex {
    display: flex;
}

.resume-editor-person .info-flex .info-flex-item {
    flex: 1;
}

.resume-editor-person .info-flex .info-labels {
    position: relative;
    margin-top: 16px;
}

.resume-editor-person .info-flex .info-labels span {
    font-size: 14px;
    color: #333;
}

.resume-editor-person .info-flex .prev-line {
    line-height: 20px;
    height: 40px;
    min-width: 150px;
    max-width: 260px;
}

.prev-line {
    display: inline-block;
    vertical-align: middle;
    max-width: 140px;
    overflow: hidden;
    overflow-x: hidden;
    overflow-y: hidden;
    text-overflow: ellipsis;
    white-space: nowrap;
    white-space-collapse: collapse;
}

.fz-resume {
    position: relative;
    top: -1px;
    width: 16px;
    height: 16px;
    display: inline-block;
    vertical-align: middle;
    margin-right: 8px;
    background: url(/src/assets/images/icon-img/elf.png) 0 0 / 16px auto no-repeat;
}

.fz-experience {
    background-position: 0 -32px;
}
.fz-degree {
    background-position: 0 -80px;
}
.fz-status {
    background-position: 0 -48px;
}

.fz-tel {
    background-position: 0 -16px;
}

.fz-weixin {
    background-position: 0 -64px;
}
.fz-mail {
    background-position: 0 0;
}

i {
    font-style: italic;
}

.resume-editor .title {
    position: relative;
    padding-left: 14px;
    font-size: 20px;
    height: 27px;
    line-height: 27px;
    font-weight: 500;
    margin-bottom: 12px;
    margin-left: 16px;
    color: #333;
}

.resume-editor .title:before {
    content: "";
    position: absolute;
    width: 4px;
    height: 17px;
    background: #00bebd;
    border-radius: 3px;
    left: 0;
    top: 5px;
}
/* ---------------------------- 个人信息end --------------------------------------- */
/* ---------------------------- 专业技能start --------------------------------------- */
.resume-editor .resume-editor-skill {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 24px;
}

.resume-editor-skill-info {
    position: relative;
    width: 700px;
    margin-right: 116px;
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}

.resume-editor-skill .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    padding-left: 20px;
    padding-top: 30px;
    border-radius: 15px;
}

.resume-editor-skill .el-button {
    margin-top: 20px;
    margin-bottom: 20px;
}

.resume-editor-skill .cancel {
    margin-left: 480px;
}

.resume-editor-skill .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 8px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    white-space: pre-wrap; /* 保留换行符和空白符 */
    line-height: 26px;
}

.resume-editor-skill .info-text:hover {
    background-color: #f8f8f8;
}

.resume-editor-skill-info .op:hover ~ .info-text {
    background-color: #f8f8f8;
}

.resume-editor-skill .op {
    top: 8px;
}

.resume-editor-skill-info:hover .op {
    opacity: 1;
}

/* ---------------------------- 专业技能end --------------------------------------- */
/* ---------------------------- 求职期望start --------------------------------------- */
.resume-editor .resume-editor-expectation {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 25px;
}
.resume-editor-expectation-info {
    width: 750px;
    margin-right: 116px;
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}

.resume-editor-expectation:hover span {
    opacity: 1;
}

.resume-editor-expectation:hover .op {
    opacity: 1;
}

.resume-editor-expectation span {
    opacity: 0;
    font-size: 14px;
    cursor: pointer;
    color: #98a9a9;
}

.resume-editor-expectation span:hover {
    color: #00a7a7;
}

.resume-editor-expectation span .add-icon {
    font-size: 14px;
    vertical-align: -5%;
    margin-left: 545px;
    margin-right: 3px;
}

.del-icon {
    margin-left: 10px;
}

.resume-editor-expectation-info .info-item {
    position: relative;
}

.resume-editor-expectation-info .op {
    position: absolute;
    right: 50px;
}

.resume-editor-expectation .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    border-radius: 15px;
    display: flex;
    flex-wrap: wrap;
    padding-left: 10px;
}

.resume-editor-expectation .el-form .el-form-item {
    display: inline-block;
    flex: 1 1 50%;
    box-sizing: border-box;
    padding: 10px;
}

.resume-editor-expectation .el-form .el-button {
    margin-top: 25px;
}

.resume-editor-expectation .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 8px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    line-height: 26px;
}

.resume-editor-expectation .info-text:hover {
    background-color: #f8f8f8;
}

.resume-editor-expectation-info .info-item:hover .info-text {
    background-color: #f8f8f8;
}
.resume-editor-expectation .info-text .el-icon {
    color: #9aaaaa;
    font-size: 16px;
    cursor: pointer;
    top: 2px;
}

/* ---------------------------- 求职期望end --------------------------------------- */
/* ---------------------------- 教育背景start 标记1--------------------------------------- */
.resume-editor .resume-editor-education {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 24px;
}

.resume-editor-education-info {
    position: relative;
    width: 700px;
    margin-right: 116px;
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}
.resume-editor-education-info:hover .op {
    opacity: 1;
}

.resume-editor-education-info:hover {
    background-color: #f8f8f8;
}

.resume-editor-education .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    border-radius: 15px;
    display: flex;
    flex-wrap: wrap;
    padding-left: 10px;
}

.resume-editor-education .el-form .el-form-item {
    display: inline-block;
    flex: 1 1 50%;
    box-sizing: border-box;
    padding: 10px;
}

.resume-editor-education .el-form .time .el-select {
    width: 130px;
}

.resume-editor-education .el-form .endTime .el-select {
    margin-right: 70px;
}

.resume-editor-education .el-form .el-form-item .cancel {
    margin-left: 480px;
}

.resume-editor-education .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 8px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    line-height: 40px;
}

.resume-editor-education .info-text .el-icon {
    margin-right: 5px;
    vertical-align: middle;
}

.resume-editor-education .info-text h3 {
    display: inline-block;
    margin-right: 15px;
    font-size: 16px;
}
.resume-editor-education .info-text .time {
    display: inline-block;
    font-size: 14px;
    color: #999999;
}

.resume-editor-education .info-text .major {
    display: inline-block;
}
.resume-editor-education .info-text .qualification {
    display: inline-block;
    margin-left: 20px;
}
/* ---------------------------- 教育背景end --------------------------------------- */

/* ---------------------------- 项目经历start --------------------------------------- */
.resume-editor .resume-editor-project {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 24px;
}
.resume-editor-project-info {
    width: 550px;
    margin-right: 116px;
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}

.resume-editor-project-info:hover .op {
    opacity: 1;
}

.resume-editor-project:hover span {
    opacity: 1;
}

.resume-editor-project span {
    opacity: 0;
    font-size: 14px;
    cursor: pointer;
    color: #98a9a9;
}

.resume-editor-project span:hover {
    color: #00a7a7;
}

.resume-editor-project span .add-icon {
    font-size: 14px;
    vertical-align: -5%;
    margin-left: 545px;
    margin-right: 3px;
}

.del-icon {
    margin-left: 10px;
}

.resume-editor-project-info .info-item {
    position: relative;
}

.resume-editor-project-info .op {
    position: absolute;
    right: -30px;
}

.resume-editor-project .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    border-radius: 15px;
    display: flex;
    flex-wrap: wrap;
    padding-left: 10px;
}

.resume-editor-project .el-form .el-form-item {
    display: inline-block;
    flex: 1 1 50%;
    box-sizing: border-box;
    padding: 10px;
}

.resume-editor-project .el-form .el-button {
    margin-top: 25px;
}

.resume-editor-project .el-form .cancel {
    margin-left: 465px;
}

.resume-editor-project .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 8px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    line-height: 26px;
}

.resume-editor-project .info-text:hover {
    background-color: #f8f8f8;
}

.resume-editor-project-info .info-item:hover .info-text {
    background-color: #f8f8f8;
}

.resume-editor-project .info-text .info-text-top {
    display: flex;
    justify-content: space-between;
}

.resume-editor-project .info-text .project-name {
    display: inline-block;
    font-size: 16px;
    color: #222;
}

.resume-editor-project .info-text .project-role {
    display: inline-block;
    font-size: 16px;
    color: #333;
    margin-left: 16px;
}

.resume-editor-project .info-text .time {
    font-size: 14px;
    color: #999;
}

.resume-editor-project .info-text .description-box {
    display: flex;
    width: 700px;
    height: auto;
    margin-top: 20px;
    cursor: pointer;
}
.resume-editor-project .info-text .neirong {
    width: 45px;
    font-size: 14px;
    color: #222;
    font-weight: 800;
}
.resume-editor-project .info-text .description-text {
    width: 700px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    white-space: pre-wrap; /* 保留换行符和空白符 */
    line-height: 26px;
}

/* ---------------------------- 项目经历end --------------------------------------- */

/* ---------------------------- 工作/实习经历start --------------------------------------- */
.resume-editor .resume-editor-work {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 24px;
}

.resume-editor-work-info {
    width: 550px;
    margin-right: 116px;
    padding: 8px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}
.resume-editor-work-info:hover .op {
    opacity: 1;
}

.resume-editor-work:hover span {
    opacity: 1;
}

.resume-editor-work span {
    opacity: 0;
    font-size: 14px;
    cursor: pointer;
    color: #98a9a9;
}

.resume-editor-work span:hover {
    color: #00a7a7;
}

.resume-editor-work span .add-icon {
    font-size: 14px;
    vertical-align: -5%;
    margin-left: 495px;
    margin-right: 3px;
}

.resume-editor-work-info .info-item {
    position: relative;
}

.resume-editor-work-info .op {
    position: absolute;
    right: -30px;
}

.resume-editor-work .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    border-radius: 15px;
    display: flex;
    flex-wrap: wrap;
    padding-left: 10px;
}

.resume-editor-work .el-form .el-form-item {
    display: inline-block;
    flex: 1 1 50%;
    box-sizing: border-box;
    padding: 10px;
}

.resume-editor-work .el-form .el-button {
    margin-top: 25px;
}

.resume-editor-work .el-form .cancel {
    margin-left: 465px;
}

.resume-editor-work .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 8px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    line-height: 26px;
}

.resume-editor-work .info-text:hover {
    background-color: #f8f8f8;
}

.resume-editor-work-info .info-item:hover .info-text {
    background-color: #f8f8f8;
}

.resume-editor-work .info-text .info-text-top {
    display: flex;
    justify-content: space-between;
}

.resume-editor-work .info-text .work-name {
    display: inline-block;
    font-size: 16px;
    color: #222;
}

.resume-editor-work .info-text .job-name {
    display: inline-block;
    font-size: 16px;
    color: #333;
    margin-left: 16px;
}

.resume-editor-work .info-text .time {
    font-size: 14px;
    color: #999;
}

.resume-editor-work .info-text .description-box {
    display: flex;
    width: 700px;
    height: auto;
    margin-top: 20px;
    cursor: pointer;
}
.resume-editor-work .info-text .neirong {
    width: 45px;
    font-size: 14px;
    color: #222;
    font-weight: 800;
}
.resume-editor-work .info-text .description-text {
    width: 700px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    white-space: pre-wrap; /* 保留换行符和空白符 */
    line-height: 26px;
}
/* ---------------------------- 工作/实习经历end --------------------------------------- */

/* ---------------------------- 获奖荣誉start --------------------------------------- */
.resume-editor .resume-editor-honor {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 24px;
}
.resume-editor-honor-info {
    position: relative;
    width: 700px;
    margin-right: 116px;
    padding: 0px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}
.resume-editor-honor-info:hover .op {
    opacity: 1;
}

.resume-editor-honor .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    padding-left: 20px;
    padding-top: 30px;
    border-radius: 15px;
}

.resume-editor-honor .el-button {
    margin-top: 20px;
    margin-bottom: 20px;
}

.resume-editor-honor .cancel {
    margin-left: 480px;
}

.resume-editor-honor .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 12px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    white-space: pre-wrap; /* 保留换行符和空白符 */
    line-height: 26px;
}

.resume-editor-honor .info-text:hover {
    background-color: #f8f8f8;
}

.resume-editor-honor-info .op:hover ~ .info-text {
    background-color: #f8f8f8;
}

.resume-editor-honor .op {
    top: 8px;
}
/* ---------------------------- 获奖荣誉end --------------------------------------- */
/* ---------------------------- 个人评价start --------------------------------------- */
.resume-editor .resume-editor-evaluate {
    width: 100%;
    height: auto;
    box-sizing: border-box;
    padding: 12px 24px;
}
.resume-editor-evaluate-info {
    position: relative;
    width: 700px;
    margin-right: 116px;
    padding: 0px 16px;
    border-radius: 8px;
    transition: all 0.2slinear;
    cursor: pointer;
}

.resume-editor-evaluate .el-form {
    background-color: #f8f8f8;
    width: 750px;
    box-sizing: border-box;
    padding-left: 20px;
    padding-top: 30px;
    border-radius: 15px;
}

.resume-editor-evaluate .el-button {
    margin-top: 20px;
    margin-bottom: 20px;
}

.resume-editor-evaluate .cancel {
    margin-left: 480px;
}

.resume-editor-evaluate .info-text {
    width: 700px;
    margin-top: -10px;
    padding: 12px 16px;
    border-radius: 8px;
    cursor: pointer;
    color: #333;
    font-size: 14px;
    white-space: pre-wrap; /* 保留换行符和空白符 */
    line-height: 26px;
}

.resume-editor-evaluate .info-text:hover {
    background-color: #f8f8f8;
}

.resume-editor-evaluate-info .op:hover ~ .info-text {
    background-color: #f8f8f8;
}

.resume-editor-evaluate .op {
    top: 8px;
}

.resume-editor-evaluate .resume-editor-evaluate-info:hover .op {
    opacity: 1;
}

/* ---------------------------- 个人评价end --------------------------------------- */
.resume-list {
    width: 200px;
    height: 280px;
}

.resume-list-box {
    position: fixed;
}

.resume-file {
    width: 260px;
    height: 280px;
    border: 1px #e6e7ec solid;
    border-radius: 15px;
}

.pdf_img {
    width: 44px;
    height: 44px;
    margin: 0px 8px 0px 0px;
}

.resume-info {
    display: flex;
    flex-direction: column;
}
.resume-name {
    margin-bottom: -35px;
    color: #414a60;
    max-width: 150px; /* 设置最大宽度 */
    white-space: nowrap; /* 禁止换行 */
    overflow: hidden; /* 隐藏溢出部分 */
    text-overflow: ellipsis; /* 显示省略号 */
}
.resume-font {
    font-size: 12px;
    color: #8d92a1;
}
.resume-wenjian {
    font-size: 13px;
    color: #414a60;
    margin-left: 20px;
    margin-top: 10px;
    margin-bottom: 5px;
}

.mb-2 {
    display: flex;
    justify-content: space-between;
    height: 35px;
    background-color: #f6f6f8;
    border-radius: 15px 15px 0 0;
    padding-top: 18px;
    padding-left: 25px;
    padding-right: 30px;
}

.el-menu-vertical-demo:not(.el-menu--collapse) {
    width: 200px;
    min-height: 400px;
    border-radius: 15px;
}
.el-menu-vertical-demo_resume-file:not(.el-menu--collapse) {
    width: 300px;
    min-height: 280px;
    border-radius: 15px;
}

.section-title {
    font-size: 16px;
    color: var(--primary-color);
    margin: 20px 0;
    padding-bottom: 8px;
    border-bottom: 2px solid #eee;
}

.action-buttons {
    margin-top: 30px;
    text-align: center;
}
</style>
