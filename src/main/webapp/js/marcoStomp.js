/*STOMP*/
var url = 'http://localhost:8080/stomp';
var sock = new SockJS(url);
var stomp = Stomp.over(sock);

var strJson = JSON.stringify({'message': 'Marco!'});

//默认的和STOMP端点连接
/*stomp.connect("guest", "guest", function (franme) {

});*/

var headers={
    username:'admin',
    password:'admin'
};

stomp.connect(headers, function (frame) {

    //发送消息
    //第二个参数是一个头信息的Map，它会包含在STOMP的帧中
    //事务支持
    var tx = stomp.begin();
    stomp.send("/app/marco", {transaction: tx.id}, strJson);
    tx.commit();


    //订阅服务端消息 subscribe(destination url, callback[, headers])
    stomp.subscribe("/topic/marco", function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("订阅的服务端消息：" + obj.message);
    }, {});


    stomp.subscribe("/app/getShout", function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("订阅的服务端直接返回的消息：" + obj.message);
    }, {});


    /*以下是针对特定用户的订阅*/
    var adminJSON = JSON.stringify({'message': 'ADMIN'});
    /*第一种*/
    stomp.send("/app/singleShout", {}, adminJSON);
    stomp.subscribe("/user/queue/shouts",function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("admin用户特定的消息1：" + obj.message);
    });
    /*第二种*/
    stomp.send("/app/shout", {}, adminJSON);
    stomp.subscribe("/user/queue/notifications",function (message) {
        var content = message.body;
        var obj = JSON.parse(content);
        console.log("admin用户特定的消息2：" + obj.message);
    });

    /*订阅异常消息*/
    stomp.subscribe("/user/queue/errors", function (message) {
        console.log(message.body);
    });

    //若使用STOMP 1.1 版本，默认开启了心跳检测机制（默认值都是10000ms）
    stomp.heartbeat.outgoing = 20000;

    stomp.heartbeat.incoming = 0; //客户端不从服务端接收心跳包
});
