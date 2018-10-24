<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>catpp_websocket</title>
    <script src="/catpp/resources/js/sockjs.min.js"></script>
    <script src="/catpp/resources/js/stomp.min.js"></script>
    <script src="/catpp/resources/js/jquery-3.3.1.min.js"></script>
</head>
<body onload="disconnect()">
    <button id="connect" onclick="connect()">连接</button>
    <button id="disconnect" onclick="disconnect()" disabled="disabled">断开连接</button>
    <div id="inputDiv">
        输入名称：<input type="text" id="name" /></br>
        <button id="sendName" onclick="sendName()">发送</button></br>
        <p id="response"></p>
    </div>
</body>

<script type="text/javascript">
    var stompClient = null;

    // 连接
    function connect() {
        var socket = new SockJS("/endpointWisely");
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            setConnected(true);
            console.log("connected：" + frame);
            // 通过注册/topic/getReponse路径来获取服务端向浏览器端的请求数据内容
            stompClient.subscribe("/topic/getResponse", function(res) {
                showResponse(JSON.parse(res.body).responseMessage);
            })
        })
    }

    // 断开连接
    function disconnect() {
        if (null != stompClient) {
            stompClient.disconnect();
        }
        setConnected(false);
        console.log("disconnected");
    }

    // 发送名称到服务器
    function sendName() {
        var name = $("#name").val();
        stompClient.send("/welcome", {}, JSON.stringify({'name':name}));
    }

    // 显示socket返回的消息内容
    function showResponse(message) {
        $("#response").html(message);
    }

    // 设置连接状态按钮是否可用
    function setConnected(connected) {
        $("#connect").attr("disabled", connected);
        $("#disconnect").attr("disabled",!connected);
        if (!connected) {
            $("#inputDiv").hide();
        } else {
            $("#inputDiv").show();
        }
        $("#reponse").html("");
    }
</script>
</html>