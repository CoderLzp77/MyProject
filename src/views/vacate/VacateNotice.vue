<template>
  <div id="vacate-notice">
    <notification>
      <div class="vacate-notice-table" slot="notification-vacate">
        <p>我的最新的请假信息</p>
        <hr>
        <template>
          <el-table
              :data="tableData"
              border
              style="width: 100%">
            <el-table-column prop="startTime" label="申请请假开始时间"  align="center" >
            </el-table-column>
            <el-table-column prop="endTime" label="申请请假结束时间"   align="center">
            </el-table-column>
            <el-table-column prop="duration" label="请假天数"  align="center">
            </el-table-column>
            <el-table-column prop="category" label="请假科目"  align="center">
            </el-table-column>
            <el-table-column prop="state" label="状态"  align="center" :formatter="state">
            </el-table-column>
            <el-table-column prop="reason" label="请假原因" align="center">
            </el-table-column>
            <el-table-column label="查看详情"  align="center">
              <template slot-scope="scope">
                <el-button type="warning" @click="handleClick(scope.row)">详情</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="block">
            <span class="demonstration">每页显示条数</span>
            <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :page-sizes="[1, 2, 3, 4, 5]"
                :page-size="3"
                layout="sizes,prev, pager, next"
                :total="30">
            </el-pagination>
          </div>
        </template>
      </div>
    </notification>
    <el-dialog
        title="详情"
        :visible.sync="dialogVisible"
        width="50%">
      <el-table
          :data="showData"
          style="width: 100%">
        <el-table-column
            prop="staffName"
            label="请假人姓名"
            width="180">
        </el-table-column>
        <el-table-column
            prop="state"
            label="权限"
            width="180">
        </el-table-column>
        <el-table-column
            prop="directBoss"
            label="顶头上司">
        </el-table-column>
      </el-table>
      <span slot="footer" class="dialog-footer">
            <el-button @click="dialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="dialogVisible = false">确 定</el-button>
          </span>
    </el-dialog>
  </div>
</template>

<script>
import Notification from "@/components/content/Notification";
import axios from "axios";
export default {
  name: "VacateNotice",
  components: {
    Notification
  },
  data(){
    return {
      tableData: [],
      showData: [],
      dialogVisible:false,
      pageNum: 1,
      pageSize: 1
    }
  },
  methods: {
    handleClick(row){
      console.log(row)
    /*  this.showData = row*/
      this.dialogVisible=true
    },
    handleCurrentChange(val){
      this.pageNum = val
      this.getData()
    },
    handleSizeChange(val){
      this.pageSize = val
      this.getData()
    },
    async getData(){
    axios.get("http://localhost:8081/Staff/queryStaffById",{
      params:{
        id:localStorage.getItem("staffId"),
        pageNum:this.pageNum,
        pageSize:this.pageSize
      }
    }).then(res=>{
      console.log(res)
      this.tableData=[]
      this.showData=res.data.data
      console.log(this.showData)
      for (let i = 0; i < res.data.data.length; i++) {
        this.tableData=res.data.data[i].askforleave
      }

    })
    },
    state(row,column){
      switch (row.state){
        case 0:
          return '待审批'
        case 1:
          return '已批准'
        case 2:
          return "未批准"
      }
    }
  },
  created() {
    this.getData()
  }
}
</script>

<style scoped>
p{
  text-align: center;
  font-size: 25px;
}
.details{
  font-size: 16px;
}
</style>