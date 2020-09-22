<%@page import="com.nbbang.customer.model.vo.CustomerCenter"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
	<link rel="stylesheet" href="<%=request.getContextPath() %>/css/cstmcss/main.css" type="text/css">
<link href="https://fonts.googleapis.com/css2?family=Gothic+A1:wght@600&family=Song+Myung&display=swap" rel="stylesheet">
	
	
	<div id="kdh-container">
  <div id="kdh-sidebar-left">
    
    <ul class="side-bar-kdh">
    <li class="cstmt-list" ><a class="main-a"
    style="font-family: 'Gothic A1', sans-serif;"
    href="<%=request.getContextPath() %>/customerMain">Nbbang소개</a></li>
    </ul>
      <hr>
    <ul class="side-bar-kdh">
	
      <li class="cstmt-list"> <a class="main-a" style="font-family: 'Gothic A1', sans-serif;"
          href="<%=request.getContextPath() %>/customer/customerQnA">문의하기</a></li>

      <li class="cstmt-list"><a class="main-a" style="font-family:  'Gothic A1', sans-serif;"
          href="<%=request.getContextPath() %>/customer/customerNews">소식보기</a></li>

      <li class="cstmt-list"><a class="main-a" style="font-family: 'Gothic A1', sans-serif;"
          href="<%=request.getContextPath() %>/customer/customerAsk">자주묻는 질문</a></li>
	
    </ul>
    <hr>
    <ul class="side-bar-kdh">
      <li class="list-hiper"><a href="" class="hiper-tag" style="font-family: 'Gothic A1', sans-serif;">홈</a>
      </li>
      <li class="list-hiper"><a href="" class="hiper-tag" style="font-family: 'Gothic A1', sans-serif;">구경하기</a>
      </li>
      <li class="list-hiper"><a href="" class="hiper-tag" style="font-family: 'Gothic A1', sans-serif;">해외직구</a>
      </li>
      <li class="list-hiper"><a href="" class="hiper-tag"
          style="font-family: 'Gothic A1', sans-serif;">마이페이지</a></li>
    </ul>
    <hr>
    <ul class="side-bar-kdh">
      <p id="img-title" style="font-family: 'Gothic A1', sans-serif;">협업 회사</p>
      
      <li id="list-image1"><a href="" >
          <image src="<%=request.getContextPath()%>/images/we.png" style="width: 150px; height: auto;">
        </a></li>
      <li id="list-image2"><a href="">
          <image src="<%=request.getContextPath()%>/images/pang.png" style="width: 150px; height: auto;">
        </a></li>
      <li id="list-image3"><a href="">
          <image src="<%=request.getContextPath()%>/images/fnk.png" style="width: 150px; height: auto;">
        </a></li>
        <li id="list-image4"><a href="">
          <image src="<%=request.getContextPath()%>/images/auc.gif" style="width: 150px; height: auto;">
        </a></li>

    </ul>
  </div>
</div>