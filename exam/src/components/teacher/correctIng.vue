// 学生管理页面
<template>
  <div class="all">
    <el-table :data="pagination.records" border>
      <el-table-column fixed="left" prop="studentName" label="姓名" width="120"></el-table-column>
      <el-table-column prop="studentId" label="学号" width="120"></el-table-column>
      <el-table-column prop="source" label="试卷名" width="120"></el-table-column>
      <el-table-column prop="questionId" label="试题Id" width="120"></el-table-column>
      <el-table-column prop="questionNumber" label="题目序号" width="120"></el-table-column>
      <el-table-column prop="questionMessage" label="题目信息" width="200"></el-table-column>
      <el-table-column prop="shortScore" label="分数" width="120"></el-table-column>
      <el-table-column prop="state" label="问题状态" width="100"></el-table-column>
      <el-table-column prop="answer" label="学生答案" width="500"></el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template slot-scope="scope">
          <el-button @click="checkShortAnswer(scope.row.questionId)" type="primary" size="small">批改</el-button>
          <el-button @click="deleteById(scope.row.questionId)" type="danger" size="small">删除</el-button>
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
      class="page">
    </el-pagination>
    <!-- 编辑对话框-->
    <el-dialog
      title="批改分数"
      :visible.sync="dialogVisible"
      width="30%"
      :before-close="handleClose">
      <section class="update">
        <el-form ref="form" :model="form" label-width="80px">
          <el-form-item label="试卷名">
            <el-input v-model="form.source" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="考生姓名">
            <el-input v-model="form.studentName" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="题目信息">
            <el-input v-model="form.questionMessage" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="学生答案">
            <el-input v-model="form.answer" :disabled="true"></el-input>
          </el-form-item>
          <el-form-item label="批改分数">
            <el-input v-model="form.shortScore"></el-input>
          </el-form-item>
          <el-tag type="success">请阅卷老师谨慎批改！谢谢！！</el-tag>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
  export default {
    data() {
      return {
        pagination: {
          //分页后的考试信息
          current: 1, //当前页
          total: null, //记录条数
          size: 6, //每页条数
        },
        dialogVisible: false, //对话框
        form: {}, //保存点击以后当前试卷的信息
      };
    },
    created() {
      this.getStudentInfo();
    },
    methods: {
      getStudentInfo() {
        //分页查询所有试卷信息
        this.$axios(`/api/shortAnswer/${this.pagination.current}/${this.pagination.size}`).then(res => {
          console.log("状态为："+this.form.state);
          this.pagination = res.data.data;
        }).catch(error => {});
      },
      //改变当前记录条数
      handleSizeChange(val) {
        this.pagination.size = val;
        this.getStudentInfo();
      },
      //改变当前页码，重新发送请求
      handleCurrentChange(val) {
        this.pagination.current = val;
        this.getStudentInfo();
      },
      //批改简答题答案
      checkShortAnswer(questionId) {
        this.dialogVisible = true
        this.$axios(`/api/shortAnswer/${questionId}`).then(res => {
          this.form = res.data.data
        })
      },
      //删除当前学生
      deleteById(questionId) {
        this.$confirm("确定删除当前学生吗？删除后无法恢复","Warning",{
          confirmButtonText: '确定删除',
          cancelButtonText: '算了,留着吧',
          type: 'danger'
        }).then(()=> { //确认删除
          this.$axios({
            url: `/api/shortAnswer/${questionId}`,
            method: 'delete',
          }).then(res => {
            this.getStudentInfo()
          })
        }).catch(() => {
        })
      },
      //修改题目状态
      updateShortAnswerState(){
        this.$axios({
          url: `/api/updateState`,
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          // this.form = res.data.data
          console.log(this.form.state);
          this.pagination = res.data.data()
        }).catch(error => {});
      },
      //确定更改
      submit() {
        this.dialogVisible = false
        this.$axios({
          url: '/api/updateScore',
          method: 'put',
          data: {
            ...this.form
          }
        }).then(res => {
          this.updateShortAnswerState()
          console.log(res)
          if(res.data.code ==200) {
            this.$message({
              message: '更新成功',
              type: 'success'
            })
          }
          this.getStudentInfo()
        })
      },
      //关闭提醒
      handleClose(done) {
        this.$confirm('确认关闭？')
          .then(_ => {
            done();
          }).catch(_ => {});
      },
    }
  };
</script>
<style lang="scss" scoped>
  .all {
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
      background-color: #dd5862 !important;
    }
  }
  .el-table .warning-row {
    background: #000 !important;
  }

  .el-table .success-row {
    background: #dd5862;
  }
</style>

