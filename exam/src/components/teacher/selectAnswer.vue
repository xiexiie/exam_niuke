//题库管理
<template>
  <div class="exam">
    <el-table :data="pagination.records" border :row-class-name="tableRowClassName">
      <el-table-column fixed="left" prop="subject" label="试卷名称" width="180"></el-table-column>
      <el-table-column prop="questionId" label="题目Id" width="100"></el-table-column>
      <el-table-column prop="question" label="题目信息" width="490"></el-table-column>
      <el-table-column prop="section" label="所属章节" width="200"></el-table-column>
      <el-table-column prop="type" label="题目类型" width="200"></el-table-column>
      <el-table-column prop="score" label="试题分数" width="150"></el-table-column>
      <el-table-column prop="level" label="难度等级" width="133"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="edit(scope.row.type,scope.row.questionId)" type="primary" size="small">编辑</el-button>
          <el-button @click="deleteRecord(scope.row.questionId,scope.row.type)" type="danger" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pagination.current"
      :page-sizes="[6, 10]"
      :page-size="pagination.size"
      layout="total, sizes, prev, pager, next, jumper"
      :total="pagination.total"
      class="page"
    ></el-pagination>
<!--编辑对话框-->
    <el-dialog
      title="编辑试题信息"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="试卷名称">
            <el-input v-model="form.subject"></el-input>
          </el-form-item>
          <el-form-item label="题目信息">
            <el-input v-model="form.question"></el-input>
          </el-form-item>
          <el-form-item label="所属章节">
            <el-input v-model="form.section"></el-input>
          </el-form-item>
          <el-form-item label="答案解析">
            <el-input v-model="form.analysis"></el-input>
          </el-form-item>
          <el-form-item label="试题分数">
            <el-input v-model="form.score"></el-input>
          </el-form-item>
          <el-form-item label="难度等级">
            <el-input v-model="form.level"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
export default {
  data() {
    return {
      a:0,
      form: {}, //保存点击以后当前试题的信息
      pagination: {
        //分页后的考试信息
        current: 1, //当前页
        total: null, //记录条数
        size: 4 //每页条数

      },
      dialogVisible: false
    };
  },
  created() {
    this.getAnswerInfo();
  },
  methods: {
    //分页查询所有试卷信息
    getAnswerInfo() {
      this.$axios(
        `/api/answers/${this.pagination.current}/${this.pagination.size}`
      ).then(res => {
          this.pagination = res.data.data;
          console.log(res.data);
        })
        .catch(error => {});
    },
    //编辑试题信息
    edit(type,questionId) {
      this.dialogVisible = true
      console.log(questionId);
      console.log(type);
      if(type==="选择题"){
        console.log("进入选择题的弹窗");
        this.a = 1;
        console.log("a=1");
        this.$axios(`/api/multiQuestion/${questionId}`).then(res => { //根据试卷id请求后台
          if(res.data.code == 200) {
            console.log("success");
            console.log(res.data);
            this.form = res.data.data
          }
        })
      }
      else if(type==="判断题"){
        console.log("进入判断题的弹窗");
        this.a = 2;
        console.log("a=2");
        this.$axios(`/api/judgeQuestion/${questionId}`).then(res => { //根据试卷id请求后台
          if(res.data.code == 200) {
            console.log("success");
            console.log(res.data);
            this.form = res.data.data
          }
        })
      }
      else if(type==="填空题"){
        console.log("进入填空题的弹窗");
        this.a = 3;
        console.log("a=3");
        this.$axios(`/api/fillQuestion/${questionId}`).then(res => { //根据试卷id请求后台
          if(res.data.code == 200) {
            console.log("success");
            console.log(res.data);
            this.form = res.data.data
          }
        })
      }
      else{
        console.log("进入简答题的弹窗");
        this.a = 4;
        console.log("a=4");
        this.$axios(`/api/shortQuestion/${questionId}`).then(res => { //根据试卷id请求后台
          if(res.data.code == 200) {
            console.log("success");
            console.log(res.data);
            this.form = res.data.data
          }
        })
      }
    },
    //删除试题信息
    deleteRecord(questionId,type) {
      if(type==="选择题"){
        console.log("要删除选择题了"+questionId);
        this.$confirm("确定删除该记录吗,该操作不可逆！！！","删除提示",{
          confirmButtonText: '确定删除'+questionId,
          cancelButtonText: '算了,留着',
          type: 'danger'
        }).then(()=> { //确认删除
          this.$axios({
            url: `/api/multiQuestion/${questionId}`,
            method: 'delete',
          }).then(res => {
            this.getAnswerInfo()
          })
        }).catch(() => {

        })
      }
      else if(type==="填空题"){
        console.log("要删除填空题了"+questionId);
        this.$confirm("确定删除该记录吗,该操作不可逆！！！","删除提示",{
          confirmButtonText: '确定删除'+questionId,
          cancelButtonText: '算了,留着',
          type: 'danger'
        }).then(()=> { //确认删除
          this.$axios({
            url: `/api/fillQuestion/${questionId}`,
            method: 'delete',
          }).then(res => {
            this.getAnswerInfo()
          })
        }).catch(() => {

        })
      }
      else if(type==="判断题"){
        console.log("要删除判断题了"+questionId);
        this.$confirm("确定删除该记录吗,该操作不可逆！！！","删除提示",{
          confirmButtonText: '确定删除'+questionId,
          cancelButtonText: '算了,留着',
          type: 'danger'
        }).then(()=> { //确认删除
          this.$axios({
            url: `/api/judgeQuestion/${questionId}`,
            method: 'delete',
          }).then(res => {
            this.getAnswerInfo()
          })
        }).catch(() => {

        })
      }
      else{
        console.log("要删除简答题了"+questionId);
        this.$confirm("确定删除该记录吗,该操作不可逆！！！","删除提示",{
          confirmButtonText: '确定删除'+questionId,
          cancelButtonText: '算了,留着',
          type: 'danger'
        }).then(()=> { //确认删除
          this.$axios({
            url: `/api/shortQuestion/${questionId}`,
            method: 'delete',
          }).then(res => {
            this.getAnswerInfo()
          })
        }).catch(() => {

        })
      }
    },
    //提交
    submit() { //提交修改后的试卷信息
      console.log("a的值为"+this.a);
      if(this.a===1){
        this.dialogVisible = false
        console.log("将要提交修改后的选择题了");
        this.$axios({
          url: '/api/multiQuestion',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({ //成功修改提示
              message: '更新成功',
              type: 'success'
            })
          }
          this.getAnswerInfo()
        })
      }
      else if(this.a===2){
        this.dialogVisible = false
        console.log("将要提交修改后的判断题了");
        this.$axios({
          url: '/api/judgeQuestion',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({ //成功修改提示
              message: '更新成功',
              type: 'success'
            })
          }
          this.getAnswerInfo()
        })
      }
      else if(this.a===3){
        this.dialogVisible = false
        console.log("将要提交修改后的填空题了");
        this.$axios({
          url: '/api/fillQuestion',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({ //成功修改提示
              message: '更新成功',
              type: 'success'
            })
          }
          this.getAnswerInfo()
        })
      }
      else{
        this.dialogVisible = false
        console.log("将要提交修改后的填简答题了");
        this.$axios({
          url: '/api/shortQuestion',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          if(res.data.code == 200) {
            this.$message({ //成功修改提示
              message: '更新成功',
              type: 'success'
            })
          }
          this.getAnswerInfo()
        })
      }
    },
    //关闭提醒
    handleClose(done) { //关闭提醒
      this.$confirm('确认关闭？')
        .then(_ => {
          done();
        }).catch(_ => {});
    },
    //改变当前记录条数
    handleSizeChange(val) {
      this.pagination.size = val;
      this.getAnswerInfo();
    },
    //改变当前页码，重新发送请求
    handleCurrentChange(val) {
      this.pagination.current = val;
      this.getAnswerInfo();
    },
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex % 2 == 0) {
        return "warning-row";
      } else {
        return "success-row";
      }
    }
  }
};
</script>
<style lang="scss" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit {
    margin-left: 20px;
  }
  .el-table tr {
    background-color: #DD5862 !important;
  }
}
  .el-table .warning-row {
    background: #000 !important;

  }

  .el-table .success-row {
    background: #DD5862;
  }

</style>
