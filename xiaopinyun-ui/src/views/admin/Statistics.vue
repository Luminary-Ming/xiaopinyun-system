<template>
    <div class="statistics-container">
        <div class="filter-bar">
            <el-date-picker v-model="dateRange" type="daterange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" @change="loadChartData" />
        </div>

        <div class="chart-grid">
            <!-- 用户增长趋势 -->
            <el-card class="chart-card">
                <div class="chart-header">
                    <h3>用户增长趋势</h3>
                    <el-select v-model="userChartType" @change="renderCharts">
                        <el-option label="折线图" value="line" />
                        <el-option label="柱状图" value="bar" />
                    </el-select>
                </div>
                <div ref="userChart" class="chart-container"></div>
            </el-card>

            <!-- 企业注册统计 -->
            <el-card class="chart-card">
                <h3>企业注册统计</h3>
                <div ref="companyChart" class="chart-container"></div>
            </el-card>

            <!-- 就业率分布 -->
            <el-card class="chart-card">
                <h3>就业率分布</h3>
                <div ref="employmentChart" class="chart-container"></div>
            </el-card>
        </div>
    </div>
</template>

<script setup>
import { ref, onMounted, onBeforeUnmount, watchEffect } from "vue";
import * as echarts from "echarts";

// 图表实例
const userChart = ref(null);
const companyChart = ref(null);
const employmentChart = ref(null);
let userChartInstance = null;
let companyChartInstance = null;
let employmentChartInstance = null;

// 数据状态
const dateRange = ref([new Date().setMonth(new Date().getMonth() - 1), new Date()]);
const userChartType = ref("line");

// 模拟数据
const mockData = {
    userGrowth: [
        { date: "2025-04-01", count: 150 },
        { date: "2025-04-02", count: 180 },
        { date: "2025-04-03", count: 220 },
        { date: "2025-04-04", count: 260 },
        { date: "2025-04-05", count: 310 },
        { date: "2025-04-06", count: 350 },
        { date: "2025-04-07", count: 410 },
    ],
    companyReg: [
        { industry: "互联网", count: 85 },
        { industry: "金融", count: 65 },
        { industry: "教育", count: 45 },
        { industry: "医疗", count: 55 },
        { industry: "制造业", count: 75 },
        { industry: "服务业", count: 60 },
    ],
    employmentRate: [
        { status: "已就业", rate: 68 },
        { status: "求职中", rate: 22 },
        { status: "未就业", rate: 10 },
    ],
};

const chartData = ref(mockData);

// 初始化图表
const initCharts = () => {
    userChartInstance = echarts.init(userChart.value);
    companyChartInstance = echarts.init(companyChart.value);
    employmentChartInstance = echarts.init(employmentChart.value);
    window.addEventListener("resize", handleResize);
};

// 加载数据(模拟异步请求)
const loadChartData = async () => {
    try {
        // 模拟API调用延迟
        await new Promise((resolve) => setTimeout(resolve, 500));
        chartData.value = mockData;
        renderCharts();
    } catch (error) {
        console.error("数据加载失败:", error);
    }
};

// 渲染所有图表
const renderCharts = () => {
    renderUserChart();
    renderCompanyChart();
    renderEmploymentChart();
};

// 用户增长趋势图
const renderUserChart = () => {
    const option = {
        title: {
            text: "用户增长趋势",
            left: "center",
        },
        tooltip: {
            trigger: "axis",
            formatter: "{b}<br/>{a}: {c}人",
        },
        xAxis: {
            type: "category",
            data: chartData.value.userGrowth.map((i) => i.date),
            axisLabel: {
                rotate: 45,
            },
        },
        yAxis: {
            type: "value",
            name: "用户数量",
        },
        series: [
            {
                name: "用户数量",
                type: userChartType.value,
                data: chartData.value.userGrowth.map((i) => i.count),
                smooth: true,
                areaStyle:
                    userChartType.value === "line"
                        ? {
                              opacity: 0.3,
                              color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                                  { offset: 0, color: "#00B38A" },
                                  { offset: 1, color: "#fff" },
                              ]),
                          }
                        : null,
                itemStyle: { color: "#00B38A" },
            },
        ],
    };
    userChartInstance.setOption(option);
};

// 企业注册统计图
const renderCompanyChart = () => {
    const option = {
        title: {
            text: "企业行业分布",
            left: "center",
        },
        tooltip: {
            trigger: "axis",
            formatter: "{b}<br/>{a}: {c}家",
        },
        xAxis: {
            type: "category",
            data: chartData.value.companyReg.map((i) => i.industry),
            axisLabel: {
                interval: 0,
                rotate: 45,
            },
        },
        yAxis: {
            type: "value",
            name: "企业数量",
        },
        series: [
            {
                name: "企业数量",
                type: "bar",
                data: chartData.value.companyReg.map((i) => i.count),
                barWidth: "40%",
                itemStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        { offset: 0, color: "#00B38A" },
                        { offset: 1, color: "#00B38A66" },
                    ]),
                    borderRadius: [5, 5, 0, 0],
                },
            },
        ],
    };
    companyChartInstance.setOption(option);
};

// 就业率分布图
const renderEmploymentChart = () => {
    const option = {
        title: {
            text: "就业情况分布",
            left: "center",
        },
        tooltip: {
            trigger: "item",
            formatter: "{b}: {c}%",
        },
        legend: {
            orient: "vertical",
            left: "right",
            top: "center",
        },
        series: [
            {
                name: "就业率",
                type: "pie",
                radius: ["40%", "70%"],
                avoidLabelOverlap: true,
                data: chartData.value.employmentRate.map((i) => ({
                    value: i.rate,
                    name: i.status,
                })),
                itemStyle: {
                    borderRadius: 5,
                    borderColor: "#fff",
                    borderWidth: 2,
                },
                label: {
                    show: true,
                    formatter: "{b}: {c}%",
                },
                emphasis: {
                    label: {
                        show: true,
                        fontSize: 16,
                        fontWeight: "bold",
                    },
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: "rgba(0, 0, 0, 0.5)",
                    },
                },
            },
        ],
    };
    employmentChartInstance.setOption(option);
};

// 自适应处理
const handleResize = () => {
    userChartInstance?.resize();
    companyChartInstance?.resize();
    employmentChartInstance?.resize();
};

// 生命周期
onMounted(async () => {
    initCharts();
    await loadChartData();
});

onBeforeUnmount(() => {
    window.removeEventListener("resize", handleResize);
    userChartInstance?.dispose();
    companyChartInstance?.dispose();
    employmentChartInstance?.dispose();
});

// 监听图表类型变化
watchEffect(() => {
    if (userChartInstance) renderUserChart();
});
</script>

<style scoped lang="scss">
.statistics-container {
    padding: 20px;
    background-color: #f5f7fa;
    min-height: 100vh;
}

.chart-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(450px, 1fr));
    gap: 20px;
    margin-top: 20px;

    .chart-card {
        background: #fff;
        border-radius: 8px;
        box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
        padding: 20px;

        .chart-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;

            h3 {
                margin: 0;
                color: #333;
                font-size: 18px;
            }
        }

        .chart-container {
            height: 400px;
            width: 100%;
        }
    }
}

.filter-bar {
    margin: 20px 0;
    display: flex;
    justify-content: flex-end;
}
</style>
