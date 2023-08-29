
    $(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var bno = [[${board.bno}]];

    var listGroup  = $(".replyList");

    $(".replyCount").click(function(){

    $.getJSON('/replies/board/'+bno, function(arr){

    console.log(arr);

})//end getJSON

})//end click


    //댓글이 추가될 영역
    var listGroup  = $(".replyList");

    //날짜 처리를 위한 함수
    function formatTime(str){
    var date = new Date(str);

    return date.getFullYear() + '/' +
    (date.getMonth() + 1) + '/' +
    date.getDate() + ' ' +
    date.getHours() + ':' +
    date.getMinutes();
}

    //특정한 게시글의 댓글을 처리하는 함수
    function loadJSONData() {
    $.getJSON('/replies/board/'+bno, function(arr){

    console.log(arr);

    var str ="";

    $('.replyCount').html(" Reply Count  " + arr.length);

    $.each(arr, function(idx, reply){
    console.log(reply);
    str += '    <div class="card-body" data-rno="'+reply.rno+'"><b>'+reply.rno +'</b>';
    str += '    <h5 class="card-title">'+reply.text+'</h5>';
    str += '    <h6 class="card-subtitle mb-2 text-muted">'+reply.replyer+'</h6>';
    str += '    <p class="card-text">'+ formatTime(reply.regTime) +'</p>';
    str += '    </div>';
})

    listGroup.html(str);

});
}

    // 댓글 리스트 조회
    loadJSONData();
    // $(".replyCount").click(function(){

    // loadJSONData();
    // })//end click

    //모달 창
    var modal = $('.modal');

    $(".addReply").click(function () {

    modal.modal('show');

    //댓글 입력하는 부분 초기화 시키기
    $('input[name="replyText"]').val('');
    $('input[name="replyer"]').val('');


    $(".modal-footer .btn").hide(); //모달 내의 모든 버튼을 안 보이도록
    $(".replySave, .replyClose").show(); //필요한 버튼들만 보이도록

});

    $(".replySave").click(function() {

    var reply = {
    bno: bno,
    text: $('input[name="replyText"]').val(),
    replyer: $('input[name="replyer"]').val()
}
    console.log(reply);
    $.ajax({
    url: '/replies/',
    method: 'post',
    data:  JSON.stringify(reply),
    beforeSend : function(xhr)
{   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
    xhr.setRequestHeader(header, token);
},
    contentType: 'application/json; charset=utf-8',
    dataType: 'json',
    success: function(data){
    console.log(data);

    var newRno = parseInt(data);

    alert(newRno +"번 댓글이 등록되었습니다.")
    modal.modal('hide');
    loadJSONData();
}
})
});

    $('.replyList').on("click", ".card-body", function(){

    var rno = $(this).data("rno");

    $("input[name='replyText']").val( $(this).find('.card-title').html());
    $("input[name='replyer']").val( $(this).find('.card-subtitle').html());
    $("input[name='rno']").val(rno);

    $(".modal-footer .btn").hide();
    $(".replyRemove, .replyModify, .replyClose").show();

    modal.modal('show');

});

    $(".replyRemove").on("click", function(){

    var rno = $("input[name='rno']").val(); //모달 창에 보이는 댓글 번호 hidden처리되어 있음

    $.ajax({
    url: '/replies/' + rno,
    method: 'delete',
    beforeSend : function(xhr)
{   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
    xhr.setRequestHeader(header, token);
},
    success: function(result){
    console.log("result: " + result);
    if(result ==='success'){
    alert("댓글이 삭제되었습니다");
    modal.modal('hide');
    loadJSONData();
}
}
})
});

    $(".replyModify").click(function() {

    var rno = $("input[name='rno']").val();

    var reply = {
    rno: rno,
    bno: bno,
    text: $('input[name="replyText"]').val(),
    replyer: $('input[name="replyer"]').val()
}

    console.log(reply);
    $.ajax({
    url: '/replies/' + rno,
    method: 'put',
    data:  JSON.stringify(reply),
    beforeSend : function(xhr)
{   /*데이터를 전송하기 전에 헤더에 csrf값을 설정한다*/
    xhr.setRequestHeader(header, token);
},
    contentType: 'application/json; charset=utf-8',
    success: function(result){

    console.log("RESULT: " + result);

    if(result ==='success'){
    alert("댓글이 수정되었습니다");
    modal.modal('hide');
    loadJSONData();
}
}
});
});
    $("#file1").load("../uploadEx.html");
});