<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ include file="../layout/header.jsp" %>


<div class="container my-3">
    <c:if test="${boardDto.userId == principal.id}">
        <div class="mb-3">
            <a href="/board/${boardDto.id}/updateForm" class="btn btn-warning">수정</a>
            <button onclick="deleteById(${boardDto.id})" class="btn btn-danger">삭제</button>
        </div>
    </c:if>


    <div class="mb-2 d-flex justify-content-end">
        글 번호 :
        <span id="id" class="me-3">
            <i>${boardDto.id}</i>
        </span>
        작성자 :
        <span class="me-3">
            <i>${boardDto.username} </i>
        </span>
    </div>

    <div>
        <h1><b>${boardDto.title}</b></h1>
    </div>
    <hr/>
    <div>
        <div>${boardDto.content}</div>
    </div>
    <hr/>
    <i id="heart" class="fa-regular fa-heart fa-lg"></i>

    <div class="card mt-3">
        <form action="/reply" method="post">
            <input type="hidden" name="boardId" value="${boardDto.id}">
            <div class="card-body">
                <textarea name="comment" id="reply-comment" class="form-control" rows="1"></textarea>
            </div>
            <div class="card-footer">
                <button type="submit" id="btn-reply-save" class="btn btn-primary">등록</button>
            </div>
        </form>
    </div>
    <br/>
    <div class="card">
        <div class="card-header">댓글 리스트</div>
        <ul id="reply-box" class="list-group">       
                <li id="reply-1" class="list-group-item d-flex justify-content-between">
                    <div>댓글1</div>
                    <div class="d-flex">
                        <div class="font-italic">작성자 : ssar &nbsp;</div>
                        <button class="badge bg-secondary">삭제</button>
                    </div>
                </li>
        </ul>
    </div>
</div>


<%@ include file="../layout/footer.jsp" %>