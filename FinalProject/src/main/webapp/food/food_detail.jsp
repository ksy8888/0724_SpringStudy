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
 <style type="text/css">
 .row{
   margin: 0px auto;
   width: 100%
 }
 </style>
</head>
<body>
<div class="wrapper row3">
  <main class="container clear">
  <h2 class="sectiontitle">맛상 집세보기</h2>
  <div class="row">
    <table class="table">
     <tr>
      <td class="text-center" v-for="image in posters">
        <img :src="image" style="width: 100%">
      </td>
     </tr>
    </table>
  </div>
  <div class="row">
   <div class="col-sm-8">
     <table class="table">
      <tr>
       <td colspan="2">
        <h4>{{food_detail.name}}&nbsp;<span style="color:orange">{{food_detail.score}}</span></h4>
       </td>
      </tr>
      <tr>
       <th width=15%>주소</th>
       <td width=85%>{{food_detail.address}}</td>
      </tr>
      <tr>
       <th width=15%>전화</th>
       <td width=85%>{{food_detail.phone}}</td>
      </tr>
      <tr>
       <th width=15%>종류</th>
       <td width=85%>{{food_detail.type}}</td>
      </tr>
      <tr>
       <th width=15%>가격</th>
       <td width=85%>{{food_detail.price}}</td>
      </tr>
      <tr>
       <th width=15%>영업시간</th>
       <td width=85%>{{food_detail.time}}</td>
      </tr>
      <tr>
       <td colspan="2" class="text-right">
         <a :href="'../food/food_list.do?cno='+food_detail.cno" class="btn btn-xs btn-danger">목록</a>
       </td>
      </tr>
     </table>
     <div style="height: 20px"></div>
     <table class="table">
      <tr>
       <td>
        <table class="table" v-for="rvo in reply_list">
          <tr>
           <td class="text-left">
            ★{{rvo.name}}&nbsp;({{rvo.dbday}})
           </td>
           <td class="text-right">
             <span v-if="sessionId==rvo.id">
             <button class="btn btn-xs btn-primary ups" :id="'up'+rvo.no" @click="replyUpdateForm(rvo.no)">수정</button>
             <button class="btn btn-xs btn-danger" @click="replyDelete(rvo.no)">삭제</button>
            <!--  <a :href="'../reply/reply_delete_vue.do?no='+rvo.no+'&fno='+rvo.fno" class="btn btn-xs btn-success">삭제</a> -->
             </span>
           </td>
          </tr>
          <tr>
            <td colspan="2"><pre style="white-space: pre-wrap; background-color: white; border: none;">{{rvo.msg}}</pre></td>
          </tr>
          <tr :id="'u'+rvo.no" class="updates" style="display:none">
          <td>
            <textarea rows="4" cols="60" :id="'msg'+rvo.no" style="float: left;">{{rvo.msg}}</textarea>
            <button style="float: left; background-color: blue; color: white; width: 100px; height: 87px;" @click="replyUpdate(rvo.no)">댓글수정</button>
          </td>
         </tr>
        </table>
       </td>
      </tr>
     </table>
     <table class="table" v-if="sessionId!=''">
         <tr>
          <td>
            <textarea rows="4" cols="60" ref="msg" v-model="msg" style="float: left;"></textarea>
            <button style="float: left; background-color: blue; color: white; width: 100px; height: 87px;" @click="replyWrite()">댓글쓰기</button>
          </td>
         </tr>
         
        </table>
   </div>
   <div class="col-sm-4">
   
   </div>
  </div>
  </main>
</div>
<script>
let foodDetail=new Vue({
	el:'.container',
	data:{
		fno:${fno},
		food_detail:{},
		posters:[],
		reply_list:[],
		sessionId:'${id}',
		msg:'',
		isShow:false,
		no:0
	},
	mounted:function(){
		axios.get('../food/food_house_detail_vue.do',{
			params:{
				fno:this.fno
			}
		}).then(response=>{
			// 결과값 받기
			console.log(response.data)
			this.food_detail=response.data
			this.posters=this.food_detail.poster.split("^");
		}).catch(error=>{
			// 오류처리
			console.log(error.response)
		})
		
		//댓글읽기
		this.replyRead()
	},
	methods:{
		replyRead:function(){
		   axios.get('../reply/reply_read_vue.do',{
			   params:{
				   fno:this.fno
			   }
		   }).then(response=>{
			   console.log(response.data)
			   this.reply_list=response.data
		   }).catch(error=>{
			   console.log(error.response)
		   })
		},
		replyWrite:function(){
			if(this.msg==="") {
				this.$refs.msg.focus()
				return
			}
			axios.post('../reply/reply_insert_vue.do',null,{
				params:{
					fno:this.fno,
					msg:this.msg
				}
			}).then(response=>{
				console.log(response.data)
				this.reply_list=response.data
				this.msg=''; //댓글 insert가 되면 공백으로 처리
			}).catch(error=>{
				console.log(error.response)
			})
		},
		replyDelete:function(no){
			axios.get('../reply/reply_delete_vue.do',{
				params:{
					no:no,
					fno:this.fno
				}
			}).then(response=>{
				console.log(response.data)
				this.reply_list=response.data
			}).catch(error=>{
				console.log(error.response)
			})
		},
		replyUpdateForm:function(no) {
			//Jquery => Vue,React 호환
			$('.updates').hide()
			$('.ups').text('수정');
			if(this.no==0) {
				$('#u'+no).show();
				$('#up'+no).text('취소')
				this.no = 1
			} else {
				$('#u'+no).hide();
				$('#up'+no).text('수정')
				this.no = 0
			}
		},
		replyUpdate:function(no) {
			//let msg = $('#msg').val()
			let msg = $('#msg'+no).val()
			axios.post('../reply/reply_update_vue.do',null,{
				params: {
					no:no,
					fno:this.fno,
					msg:msg
				}
			}).then(response=>{
				console.log(response.data)
				this.reply_list = response.data
				$('#u'+no).hide()
				$('#up'+no).text('수정')
			}).catch(error=>{
				console.log(error.response)
			})
		}
	}
})
</script>
</body>
</html>