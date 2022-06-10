<template>
  <div>
    <el-container style="height: 100%;">

      <el-aside width="200px" style="background-color: rgb(238, 241, 246); height: 100%; ">

        <el-menu :default-openeds="['1', '3']" style="height: 100%; overflow-x: hidden"
                 background-color="rgb(48, 65, 86)"
                 text-color="#ffffff"
                 active-text-color="#ffd344"
        >

          <div style="height: 60px; line-height: 60px; text-align: center">
              <img src="../assets/logo.png"  alt=""  style="width: 20px; position: relative; top: 5px;margin-right: 6px ">
              <b style="color: #fff">管理系统</b>

          </div>


          <el-submenu index="1">

            <template slot="title"><i class="el-icon-message"></i>导航一</template>

            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="1-1">选项1</el-menu-item>
              <el-menu-item index="1-2">选项2</el-menu-item>
            </el-menu-item-group>

            <el-menu-item-group title="分组2">
              <el-menu-item index="1-3">选项3</el-menu-item>
            </el-menu-item-group>

            <el-submenu index="1-4">
              <template slot="title">选项4</template>
              <el-menu-item index="1-4-1">选项4-1</el-menu-item>
            </el-submenu>

          </el-submenu>

          <el-submenu index="2">
            <template slot="title"><i class="el-icon-menu"></i>导航二</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="2-1">选项1</el-menu-item>
              <el-menu-item index="2-2">选项2</el-menu-item>
            </el-menu-item-group>

          </el-submenu>

          <el-submenu index="3">
            <template slot="title"><i class="el-icon-setting"></i>导航三</template>
            <el-menu-item-group>
              <template slot="title">分组一</template>
              <el-menu-item index="3-1">选项1</el-menu-item>
              <el-menu-item index="3-2">选项2</el-menu-item>
            </el-menu-item-group>


          </el-submenu>
        </el-menu>
      </el-aside>


      <el-container>
        <el-header style="text-align: right; font-size: 12px; border-bottom: 1px solid #ccc; line-height: 60px">
          <el-dropdown style="width: 100px; cursor:pointer" >
            <span>王小虎</span><i class="el-icon-arrow-down" style="margin-left: 8px" ></i>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item>个人信息</el-dropdown-item>
              <el-dropdown-item>退出</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-header>

        <el-main>

          <div style="margin-bottom: 30px">
            <el-breadcrumb separator="/">
              <el-breadcrumb-item :to="{path:'/'}">首页</el-breadcrumb-item>
              <el-breadcrumb-item>用户管理</el-breadcrumb-item>
            </el-breadcrumb>
          </div>

          <div style="padding: 10px 0">
            <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入要查询的用户名" class="mL-5" v-model="username"></el-input>
            <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入要查询的邮箱" class="mL-5" v-model="email"></el-input>
            <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入要查询的地址" class="mL-5" v-model="address"></el-input>
            <el-button class="mL-5" type="primary" @click="load">搜索</el-button>
            <el-button class="mL-5" type="warning" @click="reset">重置</el-button>
          </div>

          <div style="padding: 10px 0">
            <el-button type="primary" @click="handleAdd" class="mr-5">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-popconfirm
                  confirm-button-text='确定'
                  cancel-button-text='算了'
                  icon="el-icon-info"
                  icon-color="red"
                  title="您确定批量删除吗？"
                  @confirm="delmuch"
                  class="mr-5">
                    <el-button type="danger" slot="reference">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            </el-popconfirm>  
            
            <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-top"></i></el-button>

          </div>


          <el-table :data="tableData" 
                     border stripe 
                    :header-cell-class-name="headerBg"  
                    @selection-change="handleSelectionChange">

            <el-table-column type="selection" width="55"></el-table-column>
            <el-table-column prop="id" label="id" width="150"></el-table-column>
            <el-table-column prop="username" label="用户名" width="140"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="190"></el-table-column>
            <el-table-column prop="phone" label="电话" width="160"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="190"></el-table-column>
            <el-table-column prop="address" label="地址" width="340"></el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success" @click="handleEdit(scope.row)" class="mr-5">编辑<i class="el-icon-edit"></i></el-button>

                <el-popconfirm
                  confirm-button-text='确定'
                  cancel-button-text='算了'
                  icon="el-icon-info"
                  icon-color="red"
                  title="您确定删除吗？"
                  @confirm="del(scope.row.id)">
                    <el-button type="danger" slot="reference">删除<i class="el-icon-remove-outline"></i></el-button>
                </el-popconfirm>  

              </template>
            </el-table-column>

          </el-table>

          <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[5, 10, 20, 30]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
          </div>

         <!-- 弹窗效果-->
          <el-dialog title="用户信息" :visible.sync="dialogFormVisible" width="30%">
            <el-form label-width="85px" size="small">
              <el-form-item label="用户名">
                <el-input v-model="form.username" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="邮箱">
                <el-input v-model="form.email" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="电话">
                <el-input v-model="form.phone" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="昵称">
                <el-input v-model="form.nickname" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item label="地址">
                <el-input v-model="form.address" autocomplete="off"></el-input>
              </el-form-item>
              
            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>

        </el-main>

      </el-container>

    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'
import request from '@/utils/request'

export default {
  name: 'HomeView',
  components: {
    HelloWorld
  },


  data() {

    return{
      tableData: [],
      total: 0,
      pageNum: 1,
      pageSize: 5,
      username: "",
      email: "",
      address: "",
      dialogFormVisible: false,
      form: {},
      multipleSelection: [],
      headerBg: "headerBg"
    }
  },

  created() {
    //请求分页查询数据
   this.load()
  },

  methods: {
    handleSizeChange(pageSize) {
      console.log(pageSize)
      this.pageSize = pageSize
      this.load()
    },

    handleCurrentChange(pageNum) {
      console.log(pageNum)
      this.pageNum = pageNum
      this.load()
    },

    load() {
      request.get("/user/searchByPage",{
        params: {
          pageNum: this.pageNum,
          pageSize: this.pageSize,
          username: this.username,
          email: this.email,
          address: this.address

        }
      }).then(res =>{
        console.log(res)

        //将后端的数据拿到前端
        this.tableData = res.records 
        this.total = res.total
      })    
    },

    reset() {
        this.username="",
        this.email="",
        this.address="",
        this.load()
    },

    handleAdd() {
      this.dialogFormVisible = true,
      this.form = {}
    },

    save() {
       request.post("/user/insert",this.form).then(res =>{
         if (res){
            this.$message.success("保存成功"),
            this.dialogFormVisible = false,
            this.load()
         } else {
            this.$message.error("保存失败")
         }
       })
    },

    handleEdit(row) {
      this.form = row,
      this.dialogFormVisible = true
    },

    del(id) {
      request.delete("/user/delete/"+id).then(res =>{
         if (res){
            this.$message.success("删除成功"),
            this.load()
         } else {
            this.$message.error("删除失败")
         }
      })
    },

    handleSelectionChange(val) {
        console.log(val)
        this.multipleSelection = val
    },
    
    delmuch() {
      let ids = this.multipleSelection.map(v => v.id)  //把一个对象数组 [{},{},{}] 变成类似 只有id的数组 [1,2,3] 这样的
       request.post("/user/deleteMuch",ids).then(res =>{
         if (res){
            this.$message.success("批量删除成功"),
            this.load()
         } else {
            this.$message.error("批量删除失败")
         }
      })
    }

    
  }
}



</script>

<style>
  .headerBg{
    background: lightgoldenrodyellow!important;
  }
</style>