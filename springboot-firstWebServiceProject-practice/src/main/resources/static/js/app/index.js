var main = {

    //기능 버튼
    init: function () {
        var _this = this;

        $('#btn-save').on('click', function () {
            _this.save();
        });

        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click' , function (){
            _this.delete();
        });
    },

    //저장기능
    save: function () {
        var data = {
            title: $('#title').val(),
            author: $('#author').val(),
            content: $('#content').val()
        };
        $.ajax({
            type: 'POST', //생성 맵핑인 post 사용
            url: '/api/v1/posts',
            datatype: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function () {
            alert(('글이 등록되었습니다.'));
            window.location.href = '/';  //글 등록 성공시 (/)메인페이지로 이동
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    //수정기능
    update: function (){
        var data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        var id = $('#id').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            alert('글이 수정되었습니다.');
            window.location.href = '/';
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    //삭제기능
    delete : function (){
        var id = $('#id').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/posts/'+id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
        }).done(function (){
            alert('글이 삭제되었습니다.');
            window.location.href ='/';
        }).fail(function (){
            alert(JSON.stringify(error));
        });

    }


};
main.init();

