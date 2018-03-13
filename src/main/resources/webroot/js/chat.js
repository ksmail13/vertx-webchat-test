var sock = new SockJS('http://127.0.0.1:8080/talk');

sock.onopen = function() {
  console.log('open');
};

sock.onmessage = function(e) {
  console.log('message', e.data);
  var html = $('#log').html();
  $('#log').html(html+'<br/>'+e.data);

};

sock.onclose = function() {
  console.log('close');
};

var $msg = $('#msg');

function sendMsg() {
    sock.send($msg.val());
    $msg.val("");
}

$('#send').click(function () {
    sendMsg();
});

$msg.keyup(function(e) {
    if(e.which == 13) {
        sendMsg();
    }
});