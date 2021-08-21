$(document).ready(function (){
    lockManager();
    startTimer();
})

function  startTimer(){
    let interval = setInterval(function imWriting() {
        var postsId = $('#id').val();

        $.ajax({
            type: 'POST',
            url: '/api/v1/posts/imWriting/' + postsId,
        }).done(function () {
            console.log("프론트에서 내가 쓰고있음을 알리는 요청을 보냄")
        }).fail(function (error) {
            console.log("imwrting:fail")
            alert(JSON.stringify(error));
        });
    }, 5000);
}

function lockManager(){

    var postsId = $('#id').val();

    $.ajax({
        type: "POST",
        url: "/api/v1/test/"+ postsId,
        success: function (data) {
            console.log("프론트에서 락 매니저의 함수 호출이 응답을 받음.")
        }
    });
}


