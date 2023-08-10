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
<style type="text/css">
.container {
 margin-top: 50px;
}
.row {
 margin: 0px auto;
 width:450px;
}
</style>
</head>
<body>
	<div class="container">
	 <h3 class="text-center">Login</h3>
	 <div class="row">
	   <table class="table">
	    <tr>
	     <th width=25% class="text-right">ID</th>
	     <td width=75%>
	       <input type=text ref="id" size=15 class="input-sm" v-model="id"> 
	       <!-- 
	        new Vue({
			  el:'.container',
		      data: {
			    id:'', >> 얘랑 연결해주는게 v-model
			    pwd:''
		     },
	        -->
	     </td>
	    </tr>
	    <tr>
	     <th width=25% class="text-right">Password</th>
	     <td width=75%>
	       <input type=password ref="pwd" size=15 class="input-sm" v-model="pwd">
	     </td>
	    </tr>
	    <tr>
	     <td colspan="2" class="text-center">
	      <input type=button value="로그인" class="btn btn-sm btn-danger" @click="login()">
	      <a href="../member/join.do" class="btn btn-sm btn-primary">회원가입</a>
	     </td>
	    </tr>
	   </table>
	 </div>
	 <%--
	 	POST => params
	 	axios.post(url,config,params)
	 	               ------multipart/form-data
	    axios.post(url,null,params:{})	               
	  --%>
	</div>
	<!-- 로그인처리 -->
	<script>
	  new Vue({
		el:'.container',
		data: {
			id:'',	//v-model
			pwd:''
		},
		methods:{
			//로그인버튼 눌렀을떄 실행 >> 로그인버튼태그쪽에 @click
			login:function() {
				if(this.id.trim()==="") {
					this.$refs.id.focus();	//this.$refs >> 태그
					return;
				}
				if(this.pwd.trim()==="") {
					this.$refs.pwd.focus();
					return;
				}
				
				 axios.post('../member/login_ok.do',null,{	//값 보내는부분 (요청)
					params: {
						id:this.id,
						pwd:this.pwd
					}	
				 }).then(response=>{	//RestController에서 결과값(return값) 받는 위치임  (응답)
					 let res = response.data;
					 if(res==="NOID") {
						 alert("ID가 존재하지 않습니다")
						 this.id=''
						 this.pwd=''
						 this.$refs.id.focus()
					 }
					 else if(res==="NOPWD") {
						 alert("비밀번호가 틀립니다")
						 this.pwd='';
						 this.$refs.pwd.focus()
					 }
					 else {
						 location.href="../food/food_category.do"
					 }
				 }) 
				} 
			} 
		
	  })
	</script>
	
</body>
</html>