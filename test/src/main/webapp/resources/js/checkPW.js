//onsubmit에 작동하는 함수이기 때문에 비밀번호가 아닌 모든 데이터도 다룰 수 있다.
//data check

//emailCheck 에서 emailExist == true로 변경되었다면 userdataCheck에서
//사용할 수 없는 이메일임을 알린다.
const ckKor = /[ㄱ-ㅎㅏ-ㅣ가-힣]/g; //한글 체크
const ckEng = /[a-zA-Z]/g; // 영어 체크
const ckSpc = /[!?@#$%^&*():;+-=~{}<>\_\[\]\|\\\"\'\,\.\/\`\₩]/g;// 특수문자 체크
const ckNum = /[^0-9]/g; // 숫자가 아닌 문자 체크
const ckEngNum = /[a-zA-Z0-9]/g; // 영어+숫자 체크
const ckSpace = /[\s]/g;// 공백 체크

function userDataCheck(obj) {
	//password check
	
	// alert('passwordCheck()');
	if (obj.userPassword.value != obj.userPassword1.value) {
		alert("비밀번호가 일치하지 않습니다");
		
		//다시 입력한 비밀번호만 지운다.
		obj.userPassword1.value = '';
		obj.userPassword1.focus();
		return false;
	}
	// 입력한 비밀번호가 8자 이상 12자 이하인가 검사한다.
	var len = obj.userPassword.value.trim().length;
	if (len < 8 || len > 12) {
		alert('비밀번호는 8자 이상 12자 이하로 입력해야 합니다.');
		obj.userPassword.value = '';
		obj.userPassword1.value = '';
		obj.userPassword.focus();
		return false;
	}

	// 영문자, 숫자, 특수문자가 각각 1개 이상 입력되었나 검사한다.
	var alphaCount = 0;
	var numberCount = 0;
	var etcCount = 0;
	// 입력된 비밀번호의 문자 개수만큼 반복하며 영문자, 숫자, 특수문자의 개수를 센다.
	var pw = obj.userPassword.value.trim();
	for (var i = 0; i < len; i++) {
		if (pw.charAt(i) >= 'a' && pw.charAt(i) <= 'z' || pw.charAt(i) >= 'A'
				&& pw.charAt(i) <= 'Z') {
			alphaCount++;
		} else if (pw.charAt(i) >= '0' && pw.charAt(i) <= '9') {
			numberCount++;
		} else {
			etcCount++;
		}
	}
	// alert('영문자: ' + alphaCount + "개\n숫자: " + numberCount + "개\n특수문자: " +
	// etcCount);
	if (alphaCount == 0 || numberCount == 0 || etcCount == 0) {
		alert('영문자, 숫자, 특수문자는 각각 1개 이상 입력해야 합니다.');
		obj.userPassword.value = '';
		obj.userPassword1.value = '';
		obj.userPassword.focus();
		return false;
	}
	//기존 패스워드와 같은지 확인
	var pw = obj.password.value;
	if(pw === obj.userPassword.value){
		alert('이미 사용중인 패스워드입니다.');
		obj.userPassword.value = '';
		obj.userPassword1.value = '';
		obj.userPassword.focus();
		return false;
	}

	//true로 변경하지 않으면 submit 할 때 값이 넘어가지 않는다.
	return true;

}

// 기준에 맞지 않으면 입력했던 데이터를 지우고 커서옯기기
function error(result) {
	result.value = '';
	result.focus();
}

//비밀번호가 일치하지 않으면 텍스트 출력
function passwordCheck2() {
	var userPassword = $('#userPassword').val();
	var userPassword1 = $('#userPassword1').val();
	if (userPassword != userPassword1) {
		$('#checkMessage').html('비밀번호가 서로 일치하지 않습니다.');
	} else {
		$('#checkMessage').html('');
	}
}
//회원 탈퇴
function deleteUser(id){
    var con = confirm('정말 탈퇴하시겠습니까?');
    var data = {
        userID : id.value
    }
    if(con){
        $.ajax({
            type: 'GET',
            //url: 'https://toogether.me/spotRegistAction',
            url: '/user/delete',
            data: data,
            async:false,
            success: function (response) {
                if (response === 'fail') {
                    //console.log('Spot registration successful:', response);
                    alert('탈퇴 오류');
                }else if(response === 'success'){
                    location.href="/community";
                }
            },
            error: function (xhr, status, error) {
                //console.error('Spot registration error:', error);
                alert('오류');
            }
        });
    }
}
/*
 * 비밀번호 암호화 function loginRSA(pw){ // rsa 암호화 var rsa = new RSAKey();
 * rsa.setPublic($('#RSAModulus').val(),$('#RSAExponent').val());
 * 
 * $("#userPassword").val(rsa.encrypt(pw));
 * 
 * return true; }
 */
