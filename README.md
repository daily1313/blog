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
- Swagger

### Git Convention
- feat : 기능 추가
- fix : 버그 수정
- refactor : 리팩토링, 코드 수정
- style : formatting, 세미콜론 추가
- chore : 라이브러리 설치, 빌드 작업 업데이트
- docs : 주석 추가 삭제

### 프로젝트 기능 설명 
- 기본적인 회원가입(Spring Security), 로그인(OAuth 카카오 로그인), CRUD(게시글, 댓글, 회원) 기능 구현
  - 회원가입 시 요청에 대한 응답을 html이 아닌, Json으로 받기 위해 Ajax 사용
  - 비동기 통신(비절차적 로직, 작업 순서에 대해 구애 받지 않음)을 하기 위함
- 게시글 조회에서 Pagination 적용 
- 도메인 설계에서 무한 참조 문제를 해결하기 위해 @JsonIgnoreProperties 어노테이션 적용
- 카카오 소셜로그인 Rest API 구현(참조 : https://developers.kakao.com/docs/latest/ko/kakaologin/rest-api) 
- 클라이언트(프론트)에게 편의를 제공하기 위해 API 명세서 작성
- 실시간으로 API 문서를 자동화하고, API를 테스트 하기 위해 Swagger 사용
- Controller의 요청에 대한 응답 화면을 보기 위해 Thymeleaf 이용
- domain의 민감 정보들을 유출시키지 않기 위해 ResponseDto 설계

### 최종 결과물
- 블로그 화면(localhost:8080/blog 접속시)

  <img width="700" alt="p1" src="https://user-images.githubusercontent.com/88074556/219098287-3b153b44-7c73-4ae8-97e5-443da08dd29b.png">


- 회원가입 화면

  <img width="700" alt="p3" src="https://user-images.githubusercontent.com/88074556/219098219-f15b987a-7c57-4e82-948c-2292a95f056f.png">


- 게시글 화면

  <img width="700" alt="p4" src="https://user-images.githubusercontent.com/88074556/219098351-8177643e-e423-4b0c-affb-7861a0e977fc.png">


- 게시글 목록 조회 화면

  <img width="700" alt="p6" src="https://user-images.githubusercontent.com/88074556/219098396-090c8c61-f596-4729-a030-f2407cae0c91.png">


- 댓글창 화면

  <img width="700" alt="p5" src="https://user-images.githubusercontent.com/88074556/219098965-abbae431-e806-418f-9102-43b93dc85555.png">
