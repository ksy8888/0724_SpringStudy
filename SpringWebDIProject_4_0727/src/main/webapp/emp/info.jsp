<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
   margin-top: 50px;
}
.row {
  margin: 0px auto;
  width:800px;
}
</style>
</head>
<body>
	  <div class="container">
    <h1 class="text-center">스프링 게시판</h1>
    <div class="row">
      
      <table class="table">
       <tr class="success">
         <th width="10%" class="text-center">사번</th>
         <th width="45%" class="text-center">이름</th>
         <th width="15%" class="text-center">직위</th>
         <th width="20%" class="text-center">입사일</th>
         <th width="10%" class="text-center">급여</th>
       </tr>
      
       <c:forEach var="vo" items="${list }">
         <tr>
           <td width="10%" class="text-center">${vo.empno }</td>
           <td width="45%" class="text-center">${vo.ename }</td>
           <td width="15%" class="text-center">${vo.job }</td>
           <td width="20%" class="text-center">${vo.dbday }</td>
           <td width="10%" class="text-center">${vo.sal }</td>
         </tr>
       
       </c:forEach>
      </table>
      <table class="table">
        <tr>
          <td>
           <a href="insert.do" class="btn btn-sm btn-danger">새글</a>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
</html>