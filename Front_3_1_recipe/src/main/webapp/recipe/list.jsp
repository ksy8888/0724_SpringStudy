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
.container {
 	margin-top: 50px;
}
.row {
	margin: 0px auto;
	width: 100%;
}
</style>
</head>
<body>
	<div class="container">
	 <div class="row">
	   <div class="col-md-3" v-for="vo in recipe_list">
			    <div class="thumbnail">
			    
			        <img :src="vo.poster" :title="vo.title" style="width:100%">
			        <div class="caption">
			          <p style="font-size:9px">{{vo.chef}}</p>
			        </div>

			  </div>
		  </div>
	 </div>
	 <div style="height: 10px"></div>
	 <div class="row">
	 <div class="text-center">
	  <ul class="pagination">
	   <li v-if="startPage>1"><a href="#" v-on:click="prev()">&lt;</a></li> <%--v-on:event --%>
	   <li v-for="i in range(startPage,endPage)" :class="i==curpage?'active':''"><a href="#" v-on:click="selectPage(i)">{{i}}</a></li>
	   <li v-if="endPage<totalpage"><a href="#" @click="next()">&gt;</a></li>
	  </ul>
	  </div>
	 </div>
	
	 <hr>
	 <div class="row">
	 
	 </div>
	</div>
	<script>
		new Vue({
			el:'.container',
			data: {
				recipe_list:[],
				curpage:1,
				totalpage:0,
				startPage:0,
				endPage:0
			},
			mounted:function() {
				this.send();
			},
			methods: {
				send:function() {
					axios.get("http://localhost/web/recipe/list_vue.do", {
						params: {
							page:this.curpage
						}
					}).then(response=>{
						console.log(response.data)
						this.recipe_list=response.data
						this.curpage=response.data[0].curpage;
						this.totalpage=response.data[0].totalpage;
						this.startPage = response.data[0].startPage;
						this.endPage = response.data[0].endPage;
					})
				},
				range:function(start,end) {
					let arr=[];
					let length=end-start;
					for(let i=0; i<=length; i++){
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
					this.send();
				},
				next:function() {
					this.curpage = this.endPage+1;
					this.send();
				}
				
			}
		})
	</script>

</body>
</html>