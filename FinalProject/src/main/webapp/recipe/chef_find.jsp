<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap/dist/css/bootstrap.min.css"/>
<link type="text/css" rel="stylesheet" href="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.css"/>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue@2.5.16/dist/vue.js"></script>
<script src="https://unpkg.com/babel-polyfill@latest/dist/polyfill.min.js"></script>
<script src="https://unpkg.com/bootstrap-vue@latest/dist/bootstrap-vue.js"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">   <!--  -->
  <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
</head>
<body>
	<div class="wrapper row3">
  <main class="container clear"> 
    <!-- main body --> 
    <table class="table">
      <tr>
       <td width=30% class="text-center" rowspan="2">
          <img :src="chef_info.poster" style="width:90px; height: 90px" class="img-circle">
       </td>
       <td width=70% colspan="4"><h4 style="color: orange;">{{chef_info.chef}}</h4></td>
      </tr>
      <tr>
       <td class="text-center">
         <img src="../recipe/mem1.png">&nbsp;{{chef_info.mem_cont1}}
       </td>
       <td class="text-center">
         <img src="../recipe/mem2.png">{{chef_info.mem_cont2}}
       </td>
       <td class="text-center">
         <img src="../recipe/mem3.png">{{chef_info.mem_cont4}}
       </td>
       <td class="text-center">
         <img src="../recipe/mem7.png">{{chef_info.mem_cont3}}
       </td>
      </tr>
    </table>
    <!-- ################################################################################################ -->
    <div class="content"> 
      <!-- ################################################################################################ -->
      <div id="gallery">
        <figure>
          <header class="heading inline">
            <input type=text size=20 ref="fd" class="input-sm" v-model="fd">
            <input type=button value="검색" class="btn btn-sm btn-danger" @click="find()">
          </header>
          <ul class="nospace clear" v-if="count==0">
            <li>검색된 결과가 없습니다</li>           
          </ul>
          <ul class="nospace clear" v-else>
            <li v-for="vo,index in recipe_list" :class="index%4==0?'one_quarter first':'one_quarter'"><a href="#"><img :src="vo.poster" :title="vo.title+'('+vo.chef+')'"></a></li>           
          </ul>
        </figure>
      </div>
      <!-- ################################################################################################ --> 
      <!-- ################################################################################################ -->
      <nav class="pagination">
        <ul>
          <li v-if="startPage>1"><a href="#" @click="prev()">&laquo; Previous</a></li>
          <li v-for="i in range(startPage,endPage)" :class="i==curpage?'current':''"><a href="#" @click="pageChange(i)">{{i}}</a></li>
          
          <li v-if="endPage<totalpage"><a href="#" @click="next()">Next &raquo;</a></li>
        </ul>
      </nav>
      <!-- ################################################################################################ --> 
    </div>
    <!-- ################################################################################################ --> 
    <!-- / main body -->
    <div class="clear"></div>
  </main>
</div>
<script>
  new Vue({
	  el:'.container',
	  //멤버변수 => this.
	  data:{
		  chef_info:{},
		  recipe_list:[], //count X
		  page_info:{},
		  curpage:1,
		  totalpage:0,
		  startPage:0,
		  endPage:0,
		  chef:'${chef}',
		  fd:'all',
		  count:0    //검색한 결과 유무확인
	  },
	  // EL,JSTL => 자바스크립트에서 사용 가능
	  mounted:function() {
		  axios.get('http://localhost/web/recipe/chef_info_vue.do',{
			  params: {
				  chef:this.chef
			  }
		  }).then(response=>{
			  console.log(response.data)
			  this.chef_info=response.data
		  }).catch(error=>{
			  console.log(error.response)
		  })
		  // -------------페이지 변경시 , 검색
		  //1. 쉐프의 레시피
		  //2. 페이지
		  this.dataRecv();
		  //this를 사용 => data:{}
	  },
	  methods: {
		  //멤버메소드 => this.
		  // 공통: 스프링 , 자바 , Front => 반복 코딩
		  // 공통 모듈 / 핵심 모듈
		  dataRecv:function() {
			  // 레시피 읽기
			  axios.post('http://localhost/web/recipe/chef_find_vue.do',null,{
				  params: {
					  page: this.curpage,
					  fd: this.fd,
					  chef:this.chef
				  }
			  }).then(response=>{
				  console.log(response.data)
				  this.recipe_list=response.data
				  
			  }).catch(error=>{
				  console.log(error.response)  
			  })
			  
			  //페이지 정보 읽기
			  axios.get('http://localhost/web/recipe/page_info_vue.do', {
				  params: {
					  page:this.curpage,
					  fd:this.fd,
					  chef: this.chef
				  }
			  }).then(response=>{
				   console.log(response.data)
				   this.page_info = response.data
				   this.curpage=this.page_info.curpage
				   this.totalpage=this.page_info.totalpage
				   this.startPage=this.page_info.startPage
				   this.endPage=this.page_info.endPage
				   this.count=Number(this.page_info.count)
				   
			  }).catch(error=>{
				  console.log(error.response)  
			  })
		  },
		  range:function(start,end) {
			  let arr=[]
			  let length = end-start
			  for(let i=0; i<=length; i++) {
				  arr[i]=start
				  start++
			  }
			  return arr;
		  },
		  //검색
		  find:function() {
			  this.curpage=1
			  this.dataRecv()
		  },
		  
		  //페이지 관련
		  pageChange:function(page) {
			  this.curpage=page
			  this.dataRecv()
		  },
		  prev:function() {			  
		      this.curpage=this.startPage-1;
		      this.dataRecv
		  },
		  next:function() {
			  this.curpage=this.endPage+1
			  this.dataRecv()
		  }
	  }
		  
  })
</script>
</body>
</html>