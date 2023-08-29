# Contruction-API 

## Attendance
  - /attend/check : testDB의 ischeck값 반환
  - /attend/attend : json로 code값을 받아서 codeDB의 code값과 비교하여 isCheck 변환
  - /attend/login : 토큰에 맞는 그날의 testDB 생성
  - /attend/code : Role이 MANAGER인 경우 codeDB의 오늘 날짜의 team code를 변경가능
  
## UnityContent
  - /unityContent/insertContent : unity컨텐츠에 대한 점수를 받아 testDB에 저장, isCheck가 true일 시에만 적용
  - /unityContent/allInfo : 토큰의 email을 통해 testDB의 현재 날짜에 대한 모든 정보를 Json으로 반환
  
## Survey_dass
  - /survey/update : dass-21 점수 업데이트
  - /survey/check : 금일 근로자의 dass-21 검사 확인

## Worker
  - /worker/wait : 회원가입한 근로자의 승인대기 목록 출력
  - /worker/approval : 근로자의 회원가입 승인
  - /worker/team : 어떤 팀의 속한 모든 근로자의 정보 출력
  - /worker/info : 어떤 근로자의 기본정보 출력
  - /worker/score : 어떤 근로자의 검사점수 출력

## Sign
  - /sign/save : 웹에서 응답받은 전자서명 encoding 문자열 저장
  - /sign/signs : 어떤 유저의 전자서명 encoding 문자열 모두 출력

## Member 
  - /auth/login : 호출 시 응답받은 정보를 확인하여 토큰과 함께 로그인
  - /auth/signup : 중복유저 검사 후 회원가입
