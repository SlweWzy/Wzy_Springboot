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
            <el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入要查询的内容" class="mL-5" v-model="username"></el-input>
            <!--<el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入要查询的名字" class="mL-5"></el-input>-->
            <!--<el-input style="width: 200px" suffix-icon="el-icon-search" placeholder="请输入要查询的邮箱" class="mL-5"></el-input>-->
            <el-button class="mL-5" type="primary" @click="load">搜索</el-button>
          </div>

          <div style="padding: 10px 0">
            <el-button type="primary">新增 <i class="el-icon-circle-plus-outline"></i></el-button>
            <el-button type="danger">批量删除 <i class="el-icon-remove-outline"></i></el-button>
            <el-button type="primary">导入<i class="el-icon-bottom"></i></el-button>
            <el-button type="primary">导出<i class="el-icon-top"></i></el-button>

          </div>


          <el-table :data="tableData" border stripe :header-cell-class-name="headerBg">

            <el-table-column prop="id" label="id" width="90"></el-table-column>
            <el-table-column prop="username" label="用户名" width="140"></el-table-column>
            <el-table-column prop="nickname" label="昵称" width="120"></el-table-column>
            <el-table-column prop="phone" label="电话" width="120"></el-table-column>
            <el-table-column prop="email" label="邮箱" width="120"></el-table-column>
            <el-table-column prop="address" label="地址"></el-table-column>

            <el-table-column label="操作">
              <template slot-scope="scope">
                <el-button type="success">编辑<i class="el-icon-edit"></i></el-button>
                <el-button type="danger">删除<i class="el-icon-remove-outline"></i></el-button>
              </template>
            </el-table-column>

          </el-table>

          <div style="padding: 10px 0">
            <el-pagination
                    @size-change="handleSizeChange"
                    @current-change="handleCurrentChange"
                    :current-page="pageNum"
                    :page-sizes="[2, 5, 10, 20]"
                    :page-size="pageSize"
                    layout="total, sizes, prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
          </div>

        </el-main>

      </el-container>

    </el-container>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

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
      pageSize: 2,
      username: "",
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
      fetch("http://localhost:9090/user/searchByPage?pageNum=" + this.pageNum + "&pageSize=" + this.pageSize + "&username=" + this.username)
              .then(res => res.json()).then(res => {
        console.log(res)
        this.tableData = res.data
        this.total = res.total
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