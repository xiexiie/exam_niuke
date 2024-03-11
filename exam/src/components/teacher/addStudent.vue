<!-- 添加学生 -->
<template>
  <section class="add">
    <el-form ref="form" :model="form" label-width="80px">
      <el-form-item label="姓名">
        <el-input v-model="form.studentName"></el-input>
      </el-form-item>
      <el-form-item label="性别">
        <el-input v-model="form.sex"></el-input>
      </el-form-item>
      <el-form-item label="学院">
        <el-input v-model="form.institute"></el-input>
      </el-form-item>
      <el-form-item label="所属专业">
        <el-input v-model="form.major"></el-input>
      </el-form-item>
      <el-form-item label="年级">
        <el-input v-model="form.grade"></el-input>
      </el-form-item>
      <el-form-item label="班级">
        <el-input v-model="form.clazz"></el-input>
      </el-form-item>
      <el-form-item label="电话号码">
        <el-input v-model="form.tel"></el-input>
      </el-form-item>
      <el-form-item label="身份证号">
        <el-input v-model="form.cardId"></el-input>
      </el-form-item>
      <el-form-item label="邮箱">
        <el-input v-model="form.email"></el-input>
      </el-form-item>
      <el-form-item label="密码">
        <el-input v-model="form.pwd"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="success" size="mini" @click="onSubmit()">创建</el-button>
<!--        <el-button type="primary" @click="excel_export()">导出</el-button>-->
        <el-button class="btn-upload" size="mini" type="success" @click="handleUpdate">批量上传</el-button>
          <el-dialog
            title="提示"
            :visible.sync="dialogVisible"
            width="30%">
            <span>
              <el-upload class="upload-demo"
                         ref="upload"
                         drag
                         action="/api/upload"
                         multiple
                         :auto-upload="false"
                         :limit="5"
                         :on-success="handleFilUploadSuccess"
                         :on-remove="handleRemove">
                <i class="el-icon-upload"></i>
                <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
                <div class="el-upload__tip" slot="tip">只能上传 Excel 文件，且不超过500kb</div>
              </el-upload>
            </span>
            <span slot="footer" class="dialog-footer">
              <el-button @click="dialogVisible = false">取 消</el-button>
              <el-button type="primary" @click="handleUpload">确 定</el-button>
            </span>
          </el-dialog>
        <el-button class="btn-down" size="mini" type="primary" style= "margin-left: 5%;margin-bottom: 20px;">
          <a  class='download' href='api/download/student.xlsx' download="student.xlsx" title="excel模板下载" style="color: white">模板下载</a>
        </el-button>
        <el-button type="text" size="mini" @click="cancel()">取消</el-button>
      </el-form-item>
    </el-form>
  </section>
</template>

<script>
export default {
  data() {
    return {
      form: { //表单数据初始化
        studentName: null,
        grade: null,
        major: null,
        clazz: null,
        institute: null,
        tel: null,
        email: null,
        pwd: null,
        cardId: null,
        sex: null,
        role: 2
      },
      dialogVisible: false,
    };
  },
  methods: {
    //数据提交
    onSubmit() {
      this.$axios({
        url: '/api/student',
        method: 'post',
        data: {
          ...this.form
        }
      }).then(res => {
        if(res.data.code == 200) {
          this.$message({
            message: '数据添加成功',
            type: 'success'
          })
          this.$router.push({path: '/studentManage'})
        }
      })
    },
    //取消按钮
    cancel() {
      this.form = {}
    },

    handleRemove(file,fileList) {
      console.log(file,fileList);
    },
    submitUpload() {
      this.$refs.upload.submit();
    },
    // 文件上传成功时的函数
    handleFilUploadSuccess (res,file,fileList) {
      console.log(res,file,fileList)
      this.$message.success("上传成功")
    },
    handleUpdate () {
      this.dialogVisible = true;
    },
    // 处理文件上传的函数
    handleUpload () {
      // console.log(res,file)
      this.submitUpload()
      this.dialogVisible = false
    }

  }
};
</script>
<style lang="scss" scoped>
.add {
  padding: 0px 40px;
  width: 400px;
}
.btn-upload {
  top: 70px;
  right: 40px;
  z-index: 100;
  box-shadow: 0 2px 12px 0 #67c23a;
}

</style>

