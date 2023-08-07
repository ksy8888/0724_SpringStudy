<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.min.js"></script> <%--vueJS 라이브러리 --%>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script> <%--서버연결 --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid {
 	margin-top: 50px;
}
.row {
	margin: 0px auto;
	width: 100%;
	
}
</style>
</head>
<body>
	<div class="container-fluid">
	 <h1 class="text-center">{{title}}</h1> <%--4 --%>
	 <div class="col-sm-6">
	  <table class="table" v-show="isShow">
	   <tr>
	    <td width=30% class="text-center" rowspan="7">
	      <img :src="'https://www.kobis.or.kr/'+movie_detail.thumbUrl" style="width: 100%">	      
	    </td>
	    <td width=70%>
	      <h3>{{movie_detail.movieNm}}</h3>
	    </td>
	   </tr>
	   <tr>
	    <td width=70%>{{movie_detail.director}}</td>
	   </tr>
	   <tr>
	    <td width=70%>{{movie_detail.genre}}</td>
	   </tr>
	   <tr>
	    <td width=70%>{{movie_detail.watchGradeNm}}</td>
	   </tr>
	   <tr>
	    <td width=70%>{{movie_detail.repNationCd}}</td>
	   </tr>
	   <tr>
	    <td width=70%>{{movie_detail.rank}}</td>
	   </tr>
	   <tr>
	    <td width=70%>{{movie_detail.showTm}}</td>
	   </tr>
	   <tr>
	    <td colspan="2">{{movie_detail.synop}}</td>
	   </tr>
	  </table>
	 </div>
	  <div class="col-sm-6">
	   <table class="table">
	    <tr>
	     <td class="text-center">
	       <button class="btn btn-sm btn-danger" v-on:click="selectMovie(1,'일별 박스오피스')">일별 박스오피스</button> <%--1 --%>
	       <button class="btn btn-sm btn-success" v-on:click="selectMovie(2,'실시간 예매율')">실시간 예매율</button>
	       <button class="btn btn-sm btn-info" v-on:click="selectMovie(3,'좌석 점유율')">좌석 점유율</button>
	     </td>
	    </tr>
	   </table>
	  	<table class="table">
	  	 <thead>
	  	  <tr class="danger">
	  	   <th class="text-center">순위</th>
	  	   <th class="text-center"></th>
	  	   <th class="text-center">영화명</th>
	  	   <th class="text-center">감독</th>
	  	   <th class="text-center">장르</th>
	  	  </tr>
	  	 </thead>
	  	 <tbody>
	  	  <tr v-for="m in movie_list" v-on:click="detailData(m)">
	  	   <td class="text-center">{{m.rank}}</td>
	  	   <td class="text-center">
	  	     <img :src="'https://www.kobis.or.kr/'+m.thumbUrl" style="width:30px; height: 30px">
	  	   </td>
	  	   <td>{{m.movieNm}}</td>
	  	   <td class="text-center">{{m.director}}</td>
	  	   <td class="text-center">{{m.genre}}</td>
	  	  </tr>
	  	 </tbody>
	  	</table>
	  </div>
	</div>
	<script>
	  new Vue({
		  el:'.container-fluid',
		  data:{
			  no:1,
			  movie_list:[],
			  title:'',	//3
			  movie_detail:{},
			  isShow:false
		  },
		  mounted:function() {
			  // 시작과 동시에 서버에서 데이러 읽기
			  //http://localhost/web/movie/movie_vue.do?no=1
			  this.title="일별 박스오피스";
			  let _this=this;
			  axios.get("http://localhost/web/movie/movie_vue.do",{
				  // parameter 'no' is present but cannot be translated into a null value due to being declared as a primitive type. Consider declaring it as object wrapper for the corresponding primitive type.
				  params: {
					  no:_this.no	//no를 this.no설정X >> 위에 parameter no..error
				  }
			  }).then(function(response){ //값 받는 부분
				  _this.movie_list=response.data;
			  })
		  },
		  methods: {
			  selectMovie:function(no,title) {	//매개변수로 들어왔기때문에 thisX 
				  this.title=title //2
				  axios.get("http://localhost/web/movie/movie_vue.do", {
					  params:{
						  no:no
					  }
				  }).then(response=>{
					  console.log(response.data);
					  this.movie_list=response.data;
				  })
			  },
			  detailData:function(m){
				  this.isShow=true;
				  this.movie_detail=m;
			  }
		  }
		  
	  })
	</script>
</body>
</html>