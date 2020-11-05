<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
</head>
<body>

	<h1 align="center">Spring에서의 Ajax 사용 테스트 페이지</h1>
	<button onclick="location.href='testtest.do'">하이</button>
	
	<ol>
		<li>
			서버 쪽 컨트롤러로 값 보내기
			<button id="test1">테스트1</button>
			<script>
				$('#test1').on('click', function(){
					$.ajax({
						url: 'test1',
						data: {name: '김연우', age: 10},
						success: function(data){
							if(data == 'ok'){
								alert('전송 성공');
							} else{
								alert('전송 실패');							
							}
						}
					});
				});
			</script>
		</li>
		<li>
			컨트롤러에서 리턴하는 json객체 받아서 출력하기1
			<button id="test2">테스트2</button>
			<div id="d2"></div>
			<script>
				$('#test2').on('click', function(){
					$.ajax({
						url: 'test2',
						success: function(data){
							console.log(data);
							$('#d2').html('번호 : ' + data.no + "<br>"
										+ '제목 : ' + data.title + "<br>"
										+ '작성자 : ' + data.writer + "<br>"
										+ '내용 : ' + data.content + "<br>");
						}
					});
				});
			</script>
		</li>
	
		<li>
			컨트롤러에서 리턴하는 json객체 받아서 출력하기2
			<button id="test3">테스트3</button>
			<div id="d3"></div>
			<script>
				$('#test3').on('click', function(){
					$.ajax({
						url: 'test3',
						dataType: 'json', 
						success: function(data){
							console.log(data);
							$('#d3').html('번호 : ' + data.no + "<br>"
										+ '제목 : ' + data.title + "<br>"
										+ '작성자 : ' + data.writer + "<br>"
										+ '내용 : ' + data.content + "<br>");
						}
					});
				});
			</script>
		</li>
		
		<li>
			컨트롤러에서 리턴하는 json배열을 받아서 출력하기
			<button id="test4">테스트4</button>
			<div id="d4"></div>
			<script>
				$('#test4').on('click', function(){
					$.ajax({
						url: 'test4',
						success: function(data){
							console.log(data);
							var values = $('#d4').html();
							
							for(var i in data){
								values += data[i].userId + ', '
										+ data[i].userPwd + ', '
										+ data[i].userName + ', '
										+ data[i].age + ', '
										+ data[i].email + ', '
										+ data[i].phone + '<br>';
							}
							
							$('#d4').html(values);
						}
					});
				});
			</script>
		</li>
		
		
	</ol>
</body>
</html>
