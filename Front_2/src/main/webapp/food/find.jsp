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
.container-fluid  {
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
	  <div ckass="row">
	  <!-- 검색기 -->
	   <input type=text ref="fd" size=30 class="input-sm" v-model="fd">
	   <input type=button class="btn btn-sm btn-primary" value="검색" v-on:click="findData()">
	  </div>	  
	  <div style="height: 20px"></div>
	  <!-- 검색 부분 출력 -->
	  <div class="row">
	   <div class="col-md-3" v-for="vo in food_list">
			    <div class="thumbnail">
			      <a href="#">
			        <img :src="vo.poster" alt="Lights" style="width:100%">
			        <div class="caption">
			          <p style="font-size:9px">{{vo.name}}&nbsp;<span style="color:orange">{{vo.score}}</span></p>
			        </div>
			      </a>
			  </div>
		  </div>
	  </div>
	  <div style="height: 10px"></div>
	  <div class="row">
	  <%--class="active" --%>
	   <div class="text-center">
	     <ul class="pagination">
		  <li v-if="startPage>1"><a href="#" v-on:click="prev()">&lt;</a></li>	  
		  <li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#" v-on:click="selectPage(i)">{{i}}</a></li>
		  <li v-if="endPage<totalpage"><a href="#" v-on:click="next()">&gt;</a></li>
		</ul>
	   </div>
	  </div>
	</div>
	<!-- VueJS -->
	<script>
	  new Vue({
		el:'.container-fluid',	//제어할 영역
		// react = state
		// React:라이브러리 (완제품), VueJS:Javascript 프레임워크(레고)
	  //멤버변수 => 화면 이동전까지 유지
		data: {		//초기값 주는 부분
			fd:'마포',
			food_list:[],
			curpage:1,
			totalpage:0,
			startPage:0,
			endPage:0,
			
		},
		// 시작과 동시에 데이터를 읽어서 출력 >> 마포에 대해 출력
		// Callback 함수 => 시스템에 의해 자동으로 호출되는 함수 => 생명주기함수
		// window.onload => $(function(){}) => componentDidMount => useEffect()
		mounted:function() {
			this.send();
			
		},
		//멤버 함수 => 사용자 정의
		methods:{
			send:function() {
				// 스프링 서버를 연결해서 필요한 데이터를 전송 , 결과값을 받는다
				axios.get("http://localhost/web/food/find_vue.do",{
					//데이터 넘겨주는 부분
					// List => Array []
					// VO => Object {no:1,name:'',sex:''}
					//               ------------------- 동일하지 않을 수 있다
					params: {
						page:this.curpage,
						fd:this.fd
					}
				}).then(response=>{
					console.log(response.data)
					this.food_list=response.data
					this.curpage=response.data[0].curpage;
					this.totalpage=response.data[0].totalpage;
					this.startPage=response.data[0].startPage;
					this.endPage=response.data[0].endPage;
				})
			},
			findData:function() {
				this.curpage=1;
				this.send();
			},
			range:function(start,end) {
				let arr=[];
				let length=end-start;
				for(let i=0; i<=length; i++) {
					arr[i] = start;
					start++;
				}
				return arr;
			},
			selectPage:function(page) {
				this.curpage=page;
				this.send();
			},
			prev:function() {
				this.curpage=this.startPage-1;
				this.send()
			},
			next:function() {
				this.curpage=this.endPage+1;
				this.send();
			}
		}
	  })
	</script>
</body>
</html>