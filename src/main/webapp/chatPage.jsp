<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="./resources/jquery.js"></script>
<script type="text/javascript">
	var ws = null;
	var username = '${sessionScope.username}';
	var target = 'ws://localhost:8080/websocketDemo/multichat?username='+username;
	
	window.onload = function(){
		if ('WebSocket' in window) {
			ws = new WebSocket(target);
		} else if ('MozWebSocket' in window) {
			ws = new MozWebSocket(target);
		} else {
			alert('WebSocket is not supported by this browser.');
			return;
		}
		ws.onopen=function(){
			$('#content').html('connection established...<br>');
		}
		ws.onclose=function(){
			$('#content').append('connection closed...');
		}
		ws.onmessage=function(event){
			eval("var msg = "+event.data+";");
			console.info(msg);
			if(undefined!=msg.msg){
				$('#content').append(msg.msg+'<br>');	
			}
			if(undefined!=msg.to){
				$('#userlist').html('');
				$(msg.to).each(function(){
					$('#userlist').append(this+'<br>');	
				});
			}
		}
	}
	function sendMsg(){
		var sendMsg = $('#inputArea').val();
		ws.send(sendMsg);
		$('#inputArea').val('');
	}
	</script>
</head>
<body>
	【${sessionScope.username}】的聊天室
	<hr>
	<div id="container" style="width:400px;height:400px;border:1px solid black;float:left" >
		<div id="content" style="width:400px;height:330px;border:1px solid black"></div>
		<div  style="width:400px;height:68px;border:0px solid black">
			<input id="inputArea" type="text" style="width:340px;height:64px;border:1px solid black"/>
			<input id="sendBtn" type="submit" style="height: 70px;width: 50px;" onclick="sendMsg();"/>
		</div>
	</div>
	<div id="userlist" style="width:100px;height:400px;border:1px solid black;float:left">
		
	</div>
</body>
</html>