<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
     <div class="row">
       <c:forEach var="vo" items="${list }">
    
	      <div class="col-md-3">
		    <div class="thumbnail">
		      <a href="/w3images/lights.jpg">
		        <img src="${vo.poster }" alt="Lights" style="width:380px; height:200px">
		        <div class="caption">
		          <p style="font-size:9px">${vo.title }</p>
		        </div>
		      </a>
		  </div> 
	  </div>
      </c:forEach>      
     </div>
     <div style="height:20px"></div>
     <div class="row">
      <div class="text-center">
        <ul class="pagination">
         <c:if test="${curpage>1 }">
		  <li><a href="../seoul/shop.do?page=${curpage>1?curpage-1:curpage }">&lt;</a></li>
		 </c:if>
		 
		  <c:forEach var="i" begin="${startpage }" end="${endpage }">
		   <li ${i==curpage?"class=active":"" }><a href="../seoul/shop.do?page=${i }">${i }</a></li>
		  </c:forEach>
		  
		 <c:if test="${curpage<totalpage }">
		  <li><a href="../seoul/shop.do?page=${curpage<totalpage?curpage+1:curpage }">&gt;</a></li>
		  </c:if>
	   </ul>
      </div>
     </div>
</body>
</html>