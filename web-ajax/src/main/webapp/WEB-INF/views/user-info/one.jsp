<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Info View</title>
</head>
<body>
	<div class="container">
		번호 : <span id="num">${param.uiNum}</span><br> 
		이름 : <span id="uiName"></span><br> 
		아이디 : <span id="uiName"></span><br> 
		비밀번호 : <span id="uiName"></span><br> 
		생년월일 : <span id="uiBirth"></span><br>
		가입일 : <span id="credat"></span><br>
		상태 : <span id="active"></span><br>
		<button onclick='/views/user-info/update?uiNum=${param.uiNum}'>수정</button>
		<button onclick="deleteUs()">삭제</button>
	</div>

	<script>
	window.addEventListener('load', function(){
		const xhr = new XMLHttpRequest();
		xhr.open('GET', '/user-info/one?uiNum=${uiNum}');
		xhr.onreadystatechange = function() {
			if (xhr.readyState === 4) {
				if (xhr.status === 200) {
					const user = JSON.parse(xhr.responseText);
					for (const key in user) {
						if(document.querySelectoer('#' + key)) {
							document.querySelector('#' + key).innerText = user[key];
						}
					}
					/* document.querySelector('#uiName').innerText = userInfo.uiName;
					document.querySelector('#uiBirth').innerText = userInfo.uiBirth;
					document.querySelector('#uiDesc').innerText = userInfo.uiDesc;
					document.querySelector('#credat').innerText = userInfo.credat; */
				}
			}
			xhr.send();
		};
	})
		/* const uiNum = ${param.uiNum}; */

		/* function loadUserInfo() {
			
			
			xhr.send();
		} */

		function goToUpdatePage() {
			location.href = `/views/user-info/update?uiNum=${param.uiNum}`;
		}

		// 사용자 삭제
		function deleteUser() {
			if (confirm('사용자를 삭제하시겠습니까?')) {
				const xhr = new XMLHttpRequest();
				xhr.open('POST', '/views/user-info/delete?uiNum=${param.uiNum}');
				xhr.onreadystatechange = function() {
					if (xhr.readyState === 4) {
						if (xhr.status === 200) {
							const responseText = JSON.parse(xhr.responseText);
							if (responseText.result === '1') {
								alert('사용자가 삭제되었습니다.');
								location.href = '/views/user-info/list';
							} else {
								alert('이미 삭제된 내용입니다.');
							}
						}
					}
				};
				xhr.send();
			}
		}
		
		function deleteUs() {
			if (confirm('사용자를 삭제하시겠습니까?')) {
				const xhr = new XMLHttpRequest();
			
			const json = JSON.stringify(param);
			xhr.open('POST', 'user-info/delete');
			xhr.onreadystatechange = function() {
				if(xhr.readyState === 4) {
					if(xhr.status === 200) {
						console.log(xhr.responseText);
						if(xhr.responseText === 1) {
							alert('사용자가 삭제되었습니다.');
							location.href = '/views/user-info/list';
						} else {
							alert('이미 삭제된 내용입니다.');
						}
					}
				}
			}
		}
	</script>
</body>
</html>
