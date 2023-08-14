<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<input type="text" id="uiId" placeholder="아이디">
	<br>
	<input type="text" id="uiName" placeholder="이름">
	<br>
	<input type="password" id="uiPwd" placeholder="비밀번호">
	<br>
	<textarea id="uiDesc" placeholder="소개"></textarea>
	<br>
	<input type="date" id="uiBirth" placeholder="생년월일">
	<br>
	<button type="button" onclick="sendObj()">등록</button>
	<button type="reset">취소</button>
	<script>
		function sendObj() {
			const param = {
				uiName : document.querySelector('#uiName').value,
				uiId : document.querySelector('#uiId').value,
				uiPwd : document.querySelector('#uiPwd').value,
				uiDesc : document.querySelector('#uiDesc').value,
				uiBirth : document.querySelector('#uiBirth').value
			}
			const json = JSON.stringify(param);

			const xhr = new XMLHttpRequest();
			xhr.open('POST', '/views/user-info/insert');
			xhr.setRequestHeader('Content-Type', 'application/json');
			xhr.onreadystatechange = function() {
				if (xhr.readyState === 4) {
					if (xhr.status === 200) {
						console.log(xhr.responseText);
						if (xhr.responseText === '1') {
							alert('정상적으로 등록 되었습니다.');
							location.href = '/views/user-info/list';
						} else {
							alert('오류가 발생하였습니다. 다시 시도해주시기 바랍니다.');
						}
					}
				}
			}
			xhr.send(json);
		}
	</script>
</body>
</html>
