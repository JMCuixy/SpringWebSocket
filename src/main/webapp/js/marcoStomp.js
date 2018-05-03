//检查浏览器是否支持WebSocket
if (window.WebSocket) {
    console.log('This browser supports WebSocket');
} else {
    console.log('This browser does not supports WebSocket');
}

/*--正文--*/
var count = 0;

/*SockJS*/
var url = 'http://localhost:8080/stomp';
var sock = new SockJS(url);
var stomp = Stomp.over(sock);

var strJson = JSON.stringify({'message':'Marco!'});

//第二个参数是一个头信息的Map，它会包含在STOMP的帧中
stomp.connect('guest', 'guest', function (frame) {
   stomp.send("/marco", {}, strJson);
});

