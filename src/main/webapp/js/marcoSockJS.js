//检查浏览器是否支持WebSocket
if (window.WebSocket) {
    console.log('This browser supports WebSocket');
} else {
    console.log('This browser does not supports WebSocket');
}

/*--正文--*/
var count = 0;

/*SockJS*/
var url = 'http://localhost:8080/marcoSockJS';
var sock = new SockJS(url);

sock.onopen = function (ev) {
    console.log("正在建立连接...");
    sayMarco();
};

sock.onmessage = function (ev) {
    console.log("接收并处理消息：" + ev.data);
    if (count == 10) {
        sock.close();
    }
    setTimeout(
        function () {
            sayMarco();
            count++;
        }, 2000);
};

sock.onclose = function (ev) {
    console.log("连接关闭...");
};

function sayMarco() {
    console.log('Sending Marco !');
    sock.send("Marco!")
}
