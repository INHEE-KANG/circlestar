<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/views/common/header.jsp" %>
<%
	Member m=(Member)request.getAttribute("member");
%>
<style>
    div#myPageSideBar{
        position: relative;
        background: white;
        text-align: center;
        margin: 3% 3% 3% 20%;
        /* 세로정렬꽉차게 가운데:center */
        align-items: stretch;
        /* 가로정렬꽉차게 가운데:center */
        justify-items: stretch;
        width: 14%;
        float: left;
        font-family: 'Do Hyeon', sans-serif;
    }
    div#sideBarTitleContainer{
        background-color: rgb(243, 183, 24);
        padding: 6%;
        color: white;
        border: 2px black solid;
    }
    p#sideBarTitle{
        font-size: 2.5em;
        margin-top: 10%;
    }
    p#sideBarText{
        font-size: 1.3em;
        margin-bottom: 10%;
    }
    div#sideBarLinkContainer{
        border: darkgrey 1px solid;
        border-top: none;
        padding: 10%;
        border: 2px black solid;
        border-top: none;
    }
    .postList{
        font-size: 1.6em;
        text-align: left;
    }
    .postList:hover{
        color: rgb(243, 183, 24);
    }

    div#modifyProfile{
        font-family: 'Do Hyeon', sans-serif;
    }
    div#memberInfoContainer{
        position: relative;
	    background: #FFFFFF;
	    text-align: center;
        margin: 3% 21% 5% 2%;
        /* 세로정렬꽉차게 가운데:center */
        align-items: stretch;
        /* 가로정렬꽉차게 가운데:center */
        justify-items: stretch;
        width: 40%;
        float: left;
    }
    div .item *{
        width:100%;
        text-align: left;
        font-size: 16px;
    }
    button.button{
        outline: none;
        background: #735020;
        width: 10%;
        min-width: 40px;
        border: none;
        padding: 5px;
        color: #FFFFFF;
        font-size: 14px;
        cursor: pointer;
        text-align: center;
        margin: 5px 0;
    }
    div.constrain{
        margin-top: -10px;
        margin-bottom: -15px;
        margin-left: 5px;
        font-size: 12px;
        color: red;
        display: none;
        text-align: left;
	}
    div.fieldCapsule{
        display: flex;
        position: relative;
    }
    div.capsuleLeft{
        width: 20%;
        min-height: 70px;
        float: left;
        /* border-right: black 1px solid; */
        border-bottom: black 1px solid;
        padding: 10px;
    }
    div.capsuleRight{
        width: 70%;
        min-height: 70px;
        float: right;
        border-bottom: black 1px solid;
        padding: 10px;
    }
    a.active{
        font-weight: bold;
    }
</style>
<section>
    <div id="myPageWrapper">
        <div id="myPageSideBar">
            <div id="sideBarTitleContainer">
                <p id="sideBarTitle">마이 페이지</p>
                <p id="sideBarText">N빵은 고객님의<br>돈과 시간을<br>N빵해드립니다.</p>
            </div>
            <div id="sideBarLinkContainer">
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/iCreateList?usid=<%=loginnedMember.getUsid()%>">내가 만든 거래</a>
                </div>
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/waitList?usid=<%=loginnedMember.getUsid()%>">신청한 거래</a>
                </div>
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/onGoingList?usid=<%=loginnedMember.getUsid()%>">시작된 거래</a>
                </div>
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/pastList?usid=<%=loginnedMember.getUsid()%>">과거 거래 내역</a>
                </div>
                <div style="margin-bottom: 1.3em;">
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/likeList?usid=<%=loginnedMember.getUsid()%>">좋아요 한 게시글</a>
                </div>
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/modifyProfile?usid=<%=loginnedMember.getUsid()%>">프로필 수정하기</a>
                </div>
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/updatePw?usid=<%=loginnedMember.getUsid()%>">비밀번호 수정하기</a>
                </div>
                <div style="margin-bottom: 1.3em;">
                    <a class="nav-link postList active" href="<%=request.getContextPath()%>/member/memberInfo?usid=<%=loginnedMember.getUsid()%>">개인정보 확인하기</a>
                </div>
                <div>
                    <a class="nav-link postList" href="<%=request.getContextPath()%>/member/reportList?usid=<%=loginnedMember.getUsid()%>">내 신고 내역</a>
                </div>
            </div>
        </div>
        <div id="memberInfoContainer">
            <div class="item textField" id="containerTitle">
                <div id="modifyProfile" style="margin-bottom: 20px; font-size: 24px; padding: 5px;" >나의 정보</div>
            </div>

            <div class="item textField">
                <div class="fieldCapsule">
                    <div class="capsuleLeft" style="border-top: black 1px solid;">아이디</div>
                    <div class="capsuleRight" style="border-top: black 1px solid;"><%=m.getMemberId()%></div>
                </div>
            </div>

            <div class="item textField">
                <div class="fieldCapsule">
                    <div class="capsuleLeft">이름</div>
                    <div class="capsuleRight"><%=m.getMemberName()%></div>
                </div>
            </div>

            <div class="item textField">
                <div class="fieldCapsule">
                    <div class="capsuleLeft">성별</div>
                    <div class="capsuleRight">
                        <%if(m.getGender().equals("F")){%>
                            여성
                        <%}else{%>
                            남성
                        <%}%>
                    </div>
                </div>
            </div>

            <div class="item textField">
                <div class="fieldCapsule">
                    <div class="capsuleLeft">생년월일</div>
                    <div class="capsuleRight"><%=m.getBirthday()%></div>
                </div>
            </div>

            <div class="item textField">
                <div class="fieldCapsule">
                    <div class="capsuleLeft">휴대폰 번호</div>
                    <div class="capsuleRight">
                        <div>
                            <%=m.getPhone()%>
                        </div>
                        <div>
                            <button type="button" class="button" id="phoneBtn">수정</button>
                        </div>
                        <div id="updatePhone"></div>
                    </div>
                </div>
            </div>
            <div class="item textField">
                <div class="fieldCapsule">
                    <div class="capsuleLeft">주소</div>
                    <div class="capsuleRight">
                        <div>
                            <%=m.getAddress()%>
                        </div>
                        <div>
                            <button type="button" class="button" id="addressBtn">수정</button>
                        </div>
                        <div id="updateAddress"></div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <script>
	    $("#phoneBtn").click(e=>{
	    	$.ajax({
                url:"<%=request.getContextPath()%>/member/updatePhone?usid="+<%=m.getUsid()%>,
                type:"get",
                dataType:"html",
                success: data =>{
                    $("div#updatePhone").html(data);
                }
            })
            $("#phoneBtn").css({"display":"none"});
            $("#updatePhone").css({"display":"block"});
	    });
	    
	    $("#addressBtn").click(e=>{
	    	$.ajax({
                url:"<%=request.getContextPath()%>/member/updateAddress?usid="+<%=m.getUsid()%>,
                type:"get",
                dataType:"html",
                success: data =>{
                    $("div#updateAddress").html(data);
                }
            })
            $("#addressBtn").css({"display":"none"});
            $("#updateAddress").css({"display":"block"});
	    });
    </script>
</section>
<%@ include file="/views/common/footer.jsp" %>