//显示学生成绩
<template>
  <div class="table">
    <div class="title">
      我的分数
    </div>
    <section class="content">
      <el-table ref="filterTable" :data="score" v-loading="loading">
<!--        考试日期-->
        <el-table-column
          prop="answerDate"
          label="考试日期"
          sortable
          width="120"
          column-key="answerDate"
          :filters="filter"
          :filter-method="filterHandler">
        </el-table-column>
<!--        试卷名称-->
        <el-table-column prop="subject" label="课程名称" width="120" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag>{{scope.row.subject}}</el-tag>
          </template>
        </el-table-column>
<!--        试卷ID-->
        <el-table-column prop="examCode" label="试卷ID" width="120" filter-placement="bottom-end">
          <template slot-scope="scope">
            <el-tag>{{scope.row.examCode}}</el-tag>
          </template>
        </el-table-column>
<!--        考试分数-->
        <el-table-column prop="etScore" label="前三部分分数" width="120"></el-table-column>
<!--        简答题分数-->
        <el-table-column prop="shortScore" label="简答题分数" width="120"></el-table-column>
<!--        总分-->
        <el-table-column prop="finalScore" label="总分" width="100"></el-table-column>
<!--        是否及格-->
        <el-table-column label="是否及格" width="100">
          <template slot-scope="scope">
            <el-tag :type="scope.row.etScore>= 60 ? 'success' : 'danger'">{{scope.row.etScore >= 60 ? "及格" : "不及格"}}</el-tag>
          </template>
        </el-table-column>
<!--        我的排名-->
        <el-table-column label="我的排名" width="100">
          <el-button type="success" @click="rank()">
            <span>点击查看</span>
          </el-button>
        </el-table-column>
      </el-table>
    </section>
  </div>
</template>

<script>
export default {
  data() {
    return {
      loading: false, //加载标识符
      score: [], //学生成绩
      shortScore: [],//学生简答题分数
      filter: null //过滤参数
    }
  },
  created() {
    this.getScore()
    //this.getShortSumScore()
    this.loading = true //数据加载则遮罩表格
  },
  methods: {
    getScore() {
      let studentId = this.$cookies.get("cid")
      this.$axios(`/api/score/${studentId}`).then(res => {
        if(res.data.code == 200) {
          this.loading = false //数据加载完成去掉遮罩
          this.score = res.data.data
          let mapVal = this.score.map((element,index) => { //通过map得到 filter:[{text,value}]形式的数组对象
            let newVal = {}
            newVal.text = element.answerDate
            newVal.value = element.answerDate
            return newVal
          })
          let hash = []
          const newArr = mapVal.reduce((item, next) => { //对新对象进行去重操作
            hash[next.text] ? '' : hash[next.text] = true && item.push(next);
            return item
          }, []);
          this.filter = newArr
        }
      })
    },
    getShortSumScore(){
      let studentId = this.$cookies.get("cid")
      this.$axios(`/api/sumShort/${studentId}`).then(res => {
        if(res.data.code == 200){
          ths.loading = false //数据加载完成去掉遮蔽
          this.shortScore = res.data.data
          let mapVal = this.score.map((element,index) => { //通过map得到 filter:[{text,value}]形式的数组对象
            let newVal = {}
            newVal.text = element.answerDate
            newVal.value = element.answerDate
            return newVal
          })
          let hash = []
          const newArr = mapVal.reduce((item, next) => { //对新对象进行去重操作
            hash[next.text] ? '' : hash[next.text] = true && item.push(next);
            return item
          }, []);
          this.filter = newArr
        }
      })
    },
    formatter(row, column) {
      return row.address;
    },
    filterTag(value, row) {
      return row.tag === value;
    },
    filterHandler(value, row, column) {
      const property = column["property"];
      return row[property] === value;
    },
    //我的排名
    rank() {
      this.$alert('第名', '我的排名', {
        confirmButtonText: '确定',
        callback: action => {
          this.$message({
            type: 'info',
            message: `action: ${ action }`
          });
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.table {
  width: 980px;
  margin: 0 auto;
  .title {
    margin: 20px;
  }
  .content {
    background-color: #fff;
    padding: 20px;
  }
}
</style>
