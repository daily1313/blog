## 블로그 프로젝트
<hr></hr>

### Skills
- Java 11
- Spring Boot 2.7.2
- Gradle
- Spring Data JPA
- Spring Security
- Spring Web
- Mysql 8.0.23
- Lombok
- Thymeleaf

### Git Convention
- feat : 기능 추가
- fix : 버그 수정
- refactor : 리팩토링, 코드 수정
- style : formatting, 세미콜론 추가
- chore : 라이브러리 설치, 빌드 작업 업데이트
- docs : 주석 추가 삭제

### 프로젝트 기능 설명 
- 기본적인 회원가입(Spring Security), 로그인(OAuth 카카오 로그인), CRUD(게시글, 댓글, 회원), 관리자 기능 구현
  - 회원가입 시 요청에 대한 응답을 html이 아닌, Json으로 받기 위해 Ajax 사용
  - 비동기 통신(비절차적 로직, 작업 순서에 대해 구애 받지 않음)을 하기 위함
- 도메인 설계에서 무한 참조 문제를 해결하기 위해 @JsonIgnoreProperties 어노테이션 적용
- 카카오 소셜로그인 Rest API 구현(참조 : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api) 
- 클라이언트(프론트)에게 편의를 제공하기 위해 API 명세서 작성(링크 : )
- Controller의 요청에 대한 응답 화면을 보기 위해 Thymeleaf 이용
- domain의 민감 정보들을 유출시키지 않기 위해 ResponseDto 설계

### 최종 결과물
