# Spring

## Attendance-check
- attend/check - testDB의 ischeck값 반환
- attend/attend - json로 code값을 받아서 codeDB의 code값과 비교하여 isCheck 변환
- attend/login - 토큰에 맞는 그날의 testDB 생성
- attend/code - Role이 MANAGER인 경우 codeDB의 오늘 날짜의 team code를 변경가능


## UnityContent
- unityContent/insertContent - unity컨텐츠에 대한 점수를 받아 testDB에 저장, isCheck가 true일 시에만 적용
- unityContent/allInfo - 토큰의 email을 통해 testDB의 현재 날짜에 대한 모든 정보를 Json으로 반환 
