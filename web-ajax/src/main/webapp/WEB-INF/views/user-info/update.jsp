<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	번호 : ${param.uiNum}<br>
	이름 : <input type="text" id="uiName"><br>
	생년월일 : <input type="text" id="uiBirth"><br>
	설명 : <input type="text" id="uiDesc"><br>
	<button onclick="sendObj()">수정</button>
<script>
	
	function sendObj(){
		const param = {
				uiNum : ${param.uiNum},
				uiName : document.querySelector('#uiName').value,
				uiBirth : document.querySelector('#uiBirth').value,
				uiDesc : document.querySelector('#uiDesc').value
		}
		const json = JSON.stringify(param);
		
		const xhr = new XMLHttpRequest();
		xhr.open('POST','/views/user-info/update');
		xhr.setRequestHeader('Content-Type','application/json');
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4){
				if(xhr.status===200){
					if(xhr.responseText==='1'){
						alert('정상적으로 등록 되었습니다.');
						location.href='/views/user-info/list';
					}else{
						alert('오류가 발생하였습니다. 다시 시도해주시기 바랍니다.');
					}
				}
			}
		}
		xhr.send(json);
	}

	window.addEventListener('load',function(){
		const xhr = new XMLHttpRequest();
		xhr.open('GET','/views/user-info/one?uiNum=' + ${param.uiNum});
		xhr.onreadystatechange = function(){
			if(xhr.readyState===4){
				if(xhr.status===200){
					const obj = JSON.parse(xhr.responseText);
					for(const key in obj){
						if(document.querySelector('#' + key)){
							document.querySelector('#' + key).value = obj[key];
						}
					}
				}
			}
		}
		xhr.send();
	})
</script>
</body>
</html>