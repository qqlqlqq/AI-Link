<template>
    <div>
      <el-row>
        <el-button plain>全部</el-button>
        <el-button plain>进行中</el-button>
        <el-button plain>已完成</el-button>
        <el-button plain>暂停</el-button>
        <el-button plain>终止</el-button>
      </el-row>
      <el-row>
        <el-button @click="showAddProject" plain>新增项目</el-button>
      </el-row>

      <el-dialog
        title="新增项目"
        :visible.sync="addProjects.addProjectDialogVisible"
        width="30%">
        <el-form :model="addProjectForm" :rules="addProjectFormRules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="项目输入人" prop="nickname">
            <el-input :disabled="true" v-model="addProjectForm.nickname"></el-input>
          </el-form-item>
          <el-form-item label="项目名称" prop="pname">
            <el-input  v-model="addProjectForm.pname"></el-input>
          </el-form-item>

          <el-form-item label="预计完成时间" prop="expectTime">
            <el-date-picker type="date" placeholder="选择日期" v-model="addProjectForm.expectTime" style="width: 100%;"></el-date-picker>
          </el-form-item>

          <el-form-item label="任务负责人" prop="region">
            <el-select v-model="addProjectForm.selectUsers" multiple placeholder="请选择">
              <el-option
                v-for="item in addProjectForm.users"
                :key="item.uid"
                :label="item.username"
                :value="item.uid">
              </el-option>
            </el-select>
          </el-form-item>

          <el-form-item label="公司级项目" prop="isCompany">
            <el-radio-group v-model="addProjectForm.isCompany">
              <el-radio label="1">是</el-radio>
              <el-radio label="0">否</el-radio>
            </el-radio-group>
          </el-form-item>
            <el-form-item label="IDH项目" prop="isIdh">
              <el-radio-group v-model="addProjectForm.isIdh">
                <el-radio label="1">是</el-radio>
                <el-radio label="0">否</el-radio>
              </el-radio-group>
            </el-form-item>
          <el-form-item label="附件" prop="expectTime">
            <el-button icon="el-icon-upload2" @click="uploadFile.uploadFileDialogVisible = true"  plain>上传文件</el-button>
          </el-form-item>

          <el-form-item label="客户联系方式" prop="desc">
            <el-input type="textarea" v-model="addProjectForm.customerContact"></el-input>
          </el-form-item>

          <el-form-item label="项目备注" prop="desc">
            <el-input type="textarea" v-model="addProjectForm.remarks"></el-input>
          </el-form-item>
        </el-form>

        <span slot="footer" class="dialog-footer">
          <el-button @click="addProjects.addProjectDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addProjec">确 定</el-button>
        </span>
      </el-dialog>

      <el-dialog title="上传附件" :visible.sync="uploadFile.uploadFileDialogVisible">
        <el-form :model="addProjectForm">
          <el-form-item label="描述" >
            <el-input v-model="addProjectForm.introduce"></el-input>
          </el-form-item>
          <el-form-item label="上传" >
            <el-upload
              class="upload-demo"
              drag
              action="http://localhost:8080/upload"
              multiple
              :on-success="uploadFileSuccess"
              :headers="headers"
            >
              <i class="el-icon-upload"></i>
              <div class="el-upload__text">将文件拖到此处，或<em>点击上传</em></div>
              <div class="el-upload__tip" slot="tip">只能上传jpg/png文件，且不超过500kb</div>
            </el-upload>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="uploadFile.uploadFileDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="uploadFile.uploadFileDialogVisible = false">确 定</el-button>
        </div>
      </el-dialog>
    </div>
</template>

<script>
    import {getUserByDepartment,addProject} from "../request/api";

    export default {
        name: "Projects",
        data(){
          return {
            headers:{},
            addProjects:{
              addProjectDialogVisible:false
            },
            uploadFile:{
              uploadFileDialogVisible:false
            },
            addProjectForm: {
              nickname: '',
              pname: '',
              expectTime: '',
              users:[],//选择任务负责人的待选项
              selectUsers:[],//选中的任务负责人
              customerContact: '',
              isCompany: 1,
              isIdh: 1,
              remarks: '',
              introduce:"",
              path:""
            },
            addProjectFormRules: {
              pname: [
                { required: true, message: '请输入项目名称', trigger: 'blur' },
                // { min: 3, max: 5, message: '长度在 3 到 5 个字符', trigger: 'blur' }
              ],
              selectUsers: [
                { required: true, message: '请选择任务负责人', trigger: 'change' }
              ],
              expectTime: [
                { type: 'date', required: true, message: '请选择日期', trigger: 'change' }
              ],
              isCompany: [
                {  required: true, message: '请选择是否公司级项目', trigger: 'change' }
              ],
              isIdh: [
                {  required: true, message: '请选择是否IDH项目', trigger: 'change' }
              ]
            }
          }
        },
        created() {
          this.addProjectForm.nickname = this.$store.state.curUser.nickname;
          this.headers.token = this.$store.state.token;
        },
      methods:{
        uploadFileSuccess(response, file, fileList){
          console.log("文件上传" + JSON.stringify(response))
          if (response.code == 0){
            this.addProjectForm.path = response.data;
          }
        },
        showAddProject(){ // addProject.addProjectDialogVisible = true
          //查询同部门的人作为项目的负责人
          getUserByDepartment().then(response => {
            let result = response.data;
            if (result.code == 0){
              this.addProjectForm.users = result.data;
              this.addProjects.addProjectDialogVisible = true;
            }
          })
        },
        addProjec(){//添加项目
          addProject(this.addProjectForm).then(response => {
            let result = response.data;
            if (result.code == 0){//添加成功
              this.addProjects.addProjectDialogVisible = false;
              //要重新加载一次新的数据
            }
          })
        }
      }
    }
</script>

<style scoped>

</style>
