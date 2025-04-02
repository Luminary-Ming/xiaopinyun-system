<!-- 统计分析 -->
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
const chartData = ref({
    userGrowth: [],
    companyReg: [],
    employmentRate: [],
});

// 初始化图表
const initCharts = () => {
    userChartInstance = echarts.init(userChart.value);
    companyChartInstance = echarts.init(companyChart.value);
    employmentChartInstance = echarts.init(employmentChart.value);
    window.addEventListener("resize", handleResize);
};

// 加载数据
const loadChartData = async () => {
    try {
        const params = {
            start: dateRange.value[0].toISOString().split("T")[0],
            end: dateRange.value[1].toISOString().split("T")[0],
        };
        const res = await adminApi.getStatistics(params);
        chartData.value = res.data;
        renderCharts();
    } catch (error) {
        console.error("数据加载失败:", error);
    }
};

// 渲染图表
const renderCharts = () => {
    renderUserChart();
    renderCompanyChart();
    renderEmploymentChart();
};

const renderUserChart = () => {
    const option = {
        tooltip: { trigger: "axis" },
        xAxis: {
            type: "category",
            data: chartData.value.userGrowth.map((i) => i.date),
        },
        yAxis: { type: "value" },
        series: [
            {
                name: "用户增长",
                type: userChartType.value,
                data: chartData.value.userGrowth.map((i) => i.count),
                smooth: true,
                areaStyle: userChartType.value === "line" ? {} : null,
                itemStyle: { color: "#00B38A" },
            },
        ],
    };
    userChartInstance.setOption(option);
};

const renderCompanyChart = () => {
    const option = {
        tooltip: { trigger: "axis" },
        xAxis: {
            type: "category",
            data: chartData.value.companyReg.map((i) => i.industry),
        },
        yAxis: { type: "value" },
        series: [
            {
                name: "企业数量",
                type: "bar",
                data: chartData.value.companyReg.map((i) => i.count),
                itemStyle: {
                    color: new echarts.graphic.LinearGradient(0, 0, 0, 1, [
                        { offset: 0, color: "#00B38A" },
                        { offset: 1, color: "#00B38A66" },
                    ]),
                },
            },
        ],
    };
    companyChartInstance.setOption(option);
};

const renderEmploymentChart = () => {
    const option = {
        tooltip: { trigger: "item" },
        legend: { orient: "vertical", left: "right" },
        series: [
            {
                name: "就业率",
                type: "pie",
                radius: ["40%", "70%"],
                data: chartData.value.employmentRate.map((i) => ({
                    value: i.rate,
                    name: i.status,
                })),
                itemStyle: {
                    borderRadius: 5,
                    borderColor: "#fff",
                    borderWidth: 2,
                },
                emphasis: {
                    label: { show: true, fontSize: 16 },
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
.chart-grid {
    display: grid;
    grid-template-columns: repeat(auto-fit, minmax(450px, 1fr));
    gap: 20px;
    margin-top: 20px;

    .chart-header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        margin-bottom: 15px;
    }

    .chart-container {
        height: 400px;
    }
}

.filter-bar {
    margin: 20px 0;
    display: flex;
    justify-content: flex-end;
}
</style>
