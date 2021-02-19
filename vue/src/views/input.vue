<template>
  <div style="background:#f5f4f1;position: absolute; width:100%; height: 100%;">
    <div class="titleBox">
      <div class="title">ASEGCN System</div>
      <el-button type="text" @click="open" class="feed">Feedback</el-button>
    </div>

    <div class="wscn-http404">
      <div class="bullshit">
        <div class="bullshit__oops">Input:</div>
        <el-input
          placeholder="Please input a word"
          v-model="input"
          v-loading="loading"
          clearable>
        </el-input>
        <div style="margin: 20px 0;"></div>
        <el-input
          type="textarea"
          v-loading="loading"
          :rows="8"
          placeholder="Please input your paragraph"
          v-model="textarea"
          clearable>
        </el-input>
<!--        <div class="bullshit__headline">{{ message }}</div>-->
<!--        <div class="bullshit__info">请检查您输入的网址是否正确，请点击以下按钮返回主页或者发送错误报告</div>-->
        <div class="bullshit__return-home" @click="onSubmit">submit</div>
      </div>
      <div class="pic-404">
        <div class="bullshit__oops">Result:</div>
        <div style="margin: 80px 0;"></div>
        <div class="word" style="color: #F56C6C; float: left;">negative</div>
        <div class="word" style="color: #67C23A; float: right;">positive</div>
        <el-progress v-loading="loading" :text-inside="true" :stroke-width="30" :percentage="percentage" :color="customColors"></el-progress>
      </div>
    </div>
  </div>
</template>

<script>

 export default {
   data() {
     return {
       input:'',
       textarea: '',
       obj:{},
       feedback:{},
       loading: false,
       percentage: 50,
       customColors: [
         {color: '#f56c6c', percentage: 0},
         {color: '#6f7ad3', percentage: 51},
         {color: '#5cb87a', percentage: 100}
       ]
     }
   },
   computed: {
     message() {
       return '页面找不到了......'
     }
   },
   methods:{
     onSubmit(){
       this.loading = true
       console.log(this.input)
       console.log(this.textarea)
       this.obj.word = this.input
       this.obj.paragraph = this.textarea
       this.api({
         url: "/comment/addComment",
         method: "post",
         data: this.obj
       }).then((res) => {
         console.log(res)
         this.percentage = res.percentage
       })
       this.input=''
       this.textarea=''
       this.loading = false

     },
     open() {
       this.$prompt('Please input your suggestion: ', 'Feedback', {
         confirmButtonText: 'OK',
         cancelButtonText: 'Cancel',
         inputType: 'textarea'
       }).then(({ value }) => {
         this.feedback.content = value
         this.$message({
           type: 'success',
           message: 'We have received your suggestions'
         });
         this.api({
           url: "/comment/feedback",
           method: "post",
           data: this.feedback
         }).then((res) => {
           console.log(res)
         })
       }).catch(() => {
         this.$message({
           type: 'info',
           message: 'Cancel input'
         });
       });
     }
   }
 }
</script>

<style rel="stylesheet/scss" lang="scss" scoped>
.feed{
  float: right;
  font-size: 20px;
  color: #ffffff;
  display: inline-block;
  vertical-align: middle;
  height: 80px;
  margin-right: 40px;
  line-height: 80px;
}
.title{
  display: inline-block;
  vertical-align: middle;
  margin-right: -40px;
  font-size: 50px;
  font-weight: bolder;
  color: #ffffff;
}
.titleBox{
  background: #1482f0;
  text-align: center;
  width: 100%;
  height: 100px;
  line-height: 100px;
}
.word{
  font-size: 30px;
  font-weight: bold;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
  line-height: 30px;
  float: left;
  margin-bottom: 50px;
  vertical-align: middle;
  display:inline-block;
}
.wscn-http404 {
  position: relative;
  width: 100%;
  margin: 0px auto;
  padding: 50px 100px;
  overflow: hidden;
  .pic-404 {
    position: relative;
    float: right;
    width: 45%;
    opacity: 0;
    padding: 150px 20px;
    overflow: hidden;
    animation-name: slideUp;
    animation-duration: 0.5s;
    animation-delay: 0.2s;
    animation-fill-mode: forwards;
    &__parent {
      width: 100%;
    }
    &__child {
      position: absolute;
      &.left {
        width: 80px;
        top: 17px;
        left: 220px;
        opacity: 0;
        animation-name: cloudLeft;
        animation-duration: 2s;
        animation-timing-function: linear;
        animation-fill-mode: forwards;
        animation-delay: 1s;
      }
      &.mid {
        width: 46px;
        top: 10px;
        left: 420px;
        opacity: 0;
        animation-name: cloudMid;
        animation-duration: 2s;
        animation-timing-function: linear;
        animation-fill-mode: forwards;
        animation-delay: 1.2s;
      }
      &.right {
        width: 62px;
        top: 100px;
        left: 500px;
        opacity: 0;
        animation-name: cloudRight;
        animation-duration: 2s;
        animation-timing-function: linear;
        animation-fill-mode: forwards;
        animation-delay: 1s;
      }
      @keyframes cloudLeft {
        0% {
          top: 17px;
          left: 220px;
          opacity: 0;
        }
        20% {
          top: 33px;
          left: 188px;
          opacity: 1;
        }
        80% {
          top: 81px;
          left: 92px;
          opacity: 1;
        }
        100% {
          top: 97px;
          left: 60px;
          opacity: 0;
        }
      }
      @keyframes cloudMid {
        0% {
          top: 10px;
          left: 420px;
          opacity: 0;
        }
        20% {
          top: 40px;
          left: 360px;
          opacity: 1;
        }
        70% {
          top: 130px;
          left: 180px;
          opacity: 1;
        }
        100% {
          top: 160px;
          left: 120px;
          opacity: 0;
        }
      }
      @keyframes cloudRight {
        0% {
          top: 100px;
          left: 500px;
          opacity: 0;
        }
        20% {
          top: 120px;
          left: 460px;
          opacity: 1;
        }
        80% {
          top: 180px;
          left: 340px;
          opacity: 1;
        }
        100% {
          top: 200px;
          left: 300px;
          opacity: 0;
        }
      }
    }
  }
  .bullshit {
    position: relative;
    float: left;
    width: 40%;
    padding: 150px 0;
    overflow: hidden;
    &__oops {
      font-size: 40px;
      font-weight: bold;
      line-height: 40px;
      color: #1482f0;
      opacity: 0;
      margin-bottom: 50px;
      animation-name: slideUp;
      animation-duration: 0.5s;
      animation-fill-mode: forwards;
    }
    &__headline {
      font-size: 20px;
      line-height: 24px;
      color: #1482f0;
      opacity: 0;
      margin-bottom: 10px;
      animation-name: slideUp;
      animation-duration: 0.5s;
      animation-delay: 0.1s;
      animation-fill-mode: forwards;
    }
    &__info {
      font-size: 13px;
      line-height: 21px;
      color: grey;
      opacity: 0;
      margin-bottom: 30px;
      animation-name: slideUp;
      animation-duration: 0.5s;
      animation-delay: 0.2s;
      animation-fill-mode: forwards;
    }
    &__return-home {
      display: block;
      float: left;
      margin-top: 40px;
      width: 150px;
      height: 40px;
      background: #1482f0;
      border-radius: 100px;
      text-align: center;
      color: #ffffff;
      opacity: 0;
      font-size: 25px;
      line-height: 40px;
      cursor: pointer;
      animation-name: slideUp;
      animation-duration: 0.5s;
      animation-delay: 0.3s;
      animation-fill-mode: forwards;
    }
    @keyframes slideUp {
      0% {
        transform: translateY(60px);
        opacity: 0;
      }
      100% {
        transform: translateY(0);
        opacity: 1;
      }
    }
  }
}

</style>
