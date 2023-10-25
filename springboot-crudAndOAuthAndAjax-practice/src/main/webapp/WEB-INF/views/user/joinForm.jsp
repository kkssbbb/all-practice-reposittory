<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="../layout/header.jsp"%>

<div className="container">
<form>
    <div class="form-group">
        <label for="username">UserName:</label>
        <input type="username" class="form-control" placeholder="Enter username" id="username">
    </div>

    <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" class="form-control" placeholder="Enter password" id="password">
    </div>

    <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" class="form-control" placeholder="Enter email" id="email">
    </div>


</form>
    <button id="btn-save" type="submit" class="btn btn-primary">회원가입</button>
</div>
<!--user.js 참조-->
<script src="/js/user/user.js"></script>

<%@ include file="../layout/footer.jsp"%>