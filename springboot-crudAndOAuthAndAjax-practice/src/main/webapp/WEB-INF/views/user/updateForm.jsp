<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>  <!--layout폴더의 header 머스테치 파일을 포함한다.라는 뜻-->

<div className="container">
    <form>
        <input type="hidden" id="id" value="${principal.user.id}"/>

        <div class="form-group">
            <label for="username">UserName:</label>
            <input type="text" value="${principal.user.username}" class="form-control" placeholder="Enter username" id="username" readonly>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" class="form-control" placeholder="Enter password" id="password">
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" value="${principal.user.email}" class="form-control" placeholder="Enter email" id="email">
        </div>


    </form>
    <button id="btn-update" type="submit" class="btn btn-primary">회원정보 수정</button>
</div>

<script src="/js/user/user.js"></script>

<%@ include file="../layout/footer.jsp"%>