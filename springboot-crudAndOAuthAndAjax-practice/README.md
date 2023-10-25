## 개발목적
- 블로그 간단한 CRUD 게시판 만들기
-  스프링MVC 모델 방식 동작원리 이해
-  OAuth 동작원리 이해
-  AJAX 통신 방식 이해
<br>

## 개발 스펙
- Java 8
- Gradle 7.4
- Spring Boot 2.6.3
<br>

## 사용 기술
- Spring MVC
- Spring boot security
- kakao OAuth2.0
<br>

<br>

## 기능
- BoardService
   - 글 상세보기
   - 글 삭제하기
   - 글 수정하기
   - 댓글 쓰기
   - 댓글 삭제 
- UserService
    -  회원찾기
    -  회원가입
    - 회원수정
    - 카카오톡 로그인

## 내용
## 📌DB설계
![ㅁㅁㅁㅁ](https://user-images.githubusercontent.com/89888075/170504911-4cb73f24-4a24-442e-8ead-27a497bdab57.PNG)

<br>
<br>

## 📌회원가입 화면
![1](https://user-images.githubusercontent.com/89888075/170506291-52023a17-354a-4132-a2b7-fb4c5a766e0e.PNG)
<br>

### 구현기능설명  
- 포스트요청으로 리퀘스트바디에 유저데이터를 받아 유저서비스의 회원가입함수가 실행되어서 유저레파지토리(db)에 저장을 해준다. 

<br>
<br>

## 📌회원 수정 화면
![2](https://user-images.githubusercontent.com/89888075/170506298-89e43fc2-8117-4a9c-8eff-1d97ebbe956f.PNG)
<br>

### 구현기능설명  
- 회원수정시 영속성 컨텍스트 User 오브젝트를 영속화시키도, 영속화된 User 오브젝트를 수정합니다. 영속화된 오브젝트를 변경하면 더티체킹으로 자동으로 DB에 update문을 날려줍니다. 

<br>
<br>

## 📌화면설명: 카카오톡 로그인 화면입니다. 
![3](https://user-images.githubusercontent.com/89888075/170506300-35a5fd72-cd2e-421f-bc41-21ee4d635354.PNG)
![4](https://user-images.githubusercontent.com/89888075/170506303-11cb3a6d-3eba-45d7-9118-4f9896feefa9.PNG)
<br>

### 구현기능설명  

카카오톡 로그인 클릭  

1. Resorce Owner 카카오로그인 요청 -> blogServer -> kakao API 카카오 로그인요청 

2. blogServer 카카오로그인 (클릭)페이지 응답 -> Resorce Owner 

3. 카카오로그인 클릭시 Resorce Owner-> kakao API 로그인 요청 

4. kakao API 카카오 로그인 동의 페이지(로그인이 안되어있을시 카카오로그인페이지)응답 ->Resorce Owner 

5. ResourceOwner 카카오로그인 동의 클릭시 kakao API (Resource Owner)인증코드 응답 -> blogServer 

6. blogServer가 응답받은 Resorce Owner인증코드로 kakaoAPI에 리소스오너 권한요청 kakaoAPI는 kakao resorce server에 있는 Resorce Owner 인증코드와 동일한지 확인후 동일하면 ->Resource Owner권한토큰(Access token 권한 부여)응답 


