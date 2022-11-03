# day10
Servlet&amp;JSP 수업내용

## 0_Servley/JSP 개요
### 네트워크 통신
#### Client - Server Model
* 클라이언트가 어떤 '요청'을 하는지에 따라 서버가 그 요청에 맞는 '응답'을 해주어야 한다.
* 요청 : Request / 응답 : Response

#### Server의 종류
* 웹 서버
	+ 웹 브라우저와 HTTP 프로토콜을 사용
* 메일 서버
* FTP Server
* Telnet Server
* DatabaseServer

### 웹 통신 개요
#### 웹서버(정적인 페이지)의 종류
* Apache : 우리가 사용
* Windows IIS : 윈도우말고 호환성이 안좋아서 거의 사장
* NGINX

### WAS(동적인 페이지)의 종류
* TOMCAT
	- 우리는 TOMCAT을 사용
	- 전세계에서 가장 보편적으로 널리 사용
* WILDFLY
* JEUS
	- 국산 WAS
	- 별로임...
* LUNA
	- LG CMS에서 만든 것
	- 굉장히 좋음
  
## 1_Servlet
* web.xml 파일
  - 배포 서술자(DD, Deployment Descriptor)라고 해서 해당 웹 어플리케이션의 기본적인 설정을 위해 작성하는 파일
  - 해당 웹 어플리케이션을 구동시키는 서버가 Start시 제일 먼저 읽히는 파일
  - 개발자가 web.xml파일을 수정하지 않고도 개발 및 운영이 가능하지만 규모가 커지고, <br>
    다양한 API들을 사용하게 되면 직접 수정을 해야하는 경우가 생김
   
* welcome-file : 처음에 url로 해당 어플리케이션 루트 요청 시 제일 처음 보여지게되는 메인페이지를 지정해놓은 것

>  	http://구동중인서버의ip주소:포트번호/  <br>
>  	=> 각자 본인 컴퓨터에 구축해놓은 서버의 IP주소를 요청 : 127.0.0.1 == localhost <br>
>  	=> 서버의 포트번호 : 8888 <br>
>  	=> 서버내의 구동중인 context root 지정 : 1_Servlet <br>

1. 새로 워크스페이스 만들어서 이클립스로 열기
2. 워크스페이스 설정(워크스페이스 : 작업환경
   1) 웹 에플리케이션 작업을 하기 위해 Java EE환경으로 설정<br>
   2) 보여질 UI탭들 세팅 [Window] - [Show View]<br>
        (Navigator, Console, Problems, Servers) <br>
   3) 인코딩 설정 및 서버 Runtime Environments [Window] - [Preferences]
       1) 인코딩 설정 : 영어, 숫자, 한글 등 문자셋을 사용할 수 있도록 UTF-8로 설정
       2) 서버 Runtime 잡기 : 이클립스에서 서버를 실행할 수 있도록 연동하는 과정
3. 서버 생성하기    
  1) [New] - [Server]
  2) 창에 기본적으로 2_3_2번 과정에서 세팅해놓은 Runtime이 잡혀있을 것(Server Name 변경 가능)
  3) Finish
  4) 생성된 서버 수정하기(만들어진 서버 더블클릭)
      1) 포트번호 재설정(기본값으로 잡힌 8080포트와 오라클 리스너 포트가 동일하므로 충돌 발생) 8080 -> 8888로 바꿨음
      2) 왼쪽 하단 Server Option에 Server modules without publishing 체크<br>
      => 다음단계에서 지정할 output folder / file upload/ download 경로 지정이 안될 때가 있음 마치고 나면 반드시 저장
4. Dynamic Web Project 만들기 (동적인 웹 어플리케이션)      
  1) 프로젝트명 신중하게 작성할 것 - Next
  2) default output folder 경로 재설정 : WebContent/WEB-INF/classes - Next <br>
  => output folder로 지정된 classes폴더에 컴파일 된 .class파일들이 들어가는 폴더이다. <br>
          실제로 프로젝트 배포 시 WebContent폴더가 배포된다.(즉, 이 안에 .class파일들이 존재해야함) <br>
					기본경로(bulid/classes)로 지정해 놓으면 요 폴더가 WebContent안에 만들어지지 않는다 주의!!!!! <br>
  3) Context root : 이 어플리케이션의 고유한 이름으로 지어줄 것(기본적으로는 프로젝트명/ 보통은 재정의해서 씀) <br>
  => 하나의 서버로 여러 개의 어플리케이션을 구동할 수 있음 <br>
  => 고유한 이름을 통해 해당 어플리케이션에 접근하는 경로로 사용 가능하고 각 어플리케이션을 구분할 수 있음 <br>
  
  Content directory : 실제로 배포되는 폴더(즉, 서버에 올라가는 폴더)의 최상위 폴더명을 지정하는 것 <br>
  => 변경 시 default output folder로 돌아가서 그 쪽도 변경해 줄 것 <br>
  Generate web.xml deployment descriptor : 무조건 체크할 것(기본적으로는 체크X) <br>
 
 
 * Servlet
서블릿이란?
웹 서비스를 위한 "자바 클래스"를 말하며 자바를 이용해서 웹을 만들기 위해 필요한 기술이다.
- 사용자의 요청을 받아서 처리하고 그에 해당하는 응답페이지를 만들어 다시 사용자에게 전송해주는 역할울 허눈 자바 클래스(컨트롤러)
- 즉, 웹에서 동적인 페이지를 JAVA로 구현할 수 있게 해주는 서버측 프로그램(WAS에서 구동됨)
=> JAVA클래스에서 웹페이지 구현을 위해서 HTML이 들어갈 수 있음

### GET 방식 테스트
 
 GET방식으로 요청 후 응답페이지 받아보기
특징 1. GET방식으로 요청하는 것은 URL의 Header영역에 데이터들을 포함시켜서 요청함
=> 사용자가 입력한 값(데이터)들이 URL에 노출됨
=> 보안에 취약함
=> 즉, 로그인이나 회원가입 같은 경우 GET방식이 부적합하다.

특징 2. Header영역은 전송하는 데이터의 길이에 제한이 있음
=> 방대한 데이터를 담았을 경우 초과된 데이터는 절단되서 넘어감
=> 즉, 게시글 작성 같은 경우 GET방식이 부적합하다.

특징 3. 장점이라고 한다면 URL에 데이터가 노출되기 때문에 즐겨찾기(북마크)가능 (즐겨찾기에 등록해놓았다가 해당 URL을 재요청 가능)
=> 검색 기능 같은 경우 GET방식이 가장 적합

+ 쿼리 스트링 :
?name=홍길동&gender=F&age=15&ctiy=서울시&height=170&food=중식&food=후식

		 * Get방식으로 요청하면 doGet메소드가 호출됨
		 * 
		 * 첫 번째 매개변수인 HttpServletRequest request에는 요청 시 전달된 내용들이 담김
		 * => 사용자가 입력한 값, 요청전송방식, 요청한 사용자의 ip주소 등..
		 * 
		 * 두 번째 매개변수인 HttpServletResponse response에는 요청 처리 후 응답을 할 때 사용하는 객체
		 * 
		 * 요청 처리 스텝
		 * 
		 * 1. 우선, 요청을 처리하기 위해 요청 시 전달된 값(사용자가 입력한 값)들을 뽑는다.
		 * => request의 parameter라는 영역에 값이 존재
		 * => key-value 세트로 담겨있음!!!!!(name속성값 = value속성값)
		 * 
		 * 2. 뽑아낸 값들을 가지고 요청 처리해야함(Service => DAO => DB)
		 * 
		 * 3. 처리 결과에 따른 성공 / 실패 페이지 응답

request의 parameter영역으로부터 전달된 데이터를 뽑는 메소드

+ 쿼리 스트링 :
?name=홍길동&gender=F&age=15&ctiy=서울시&height=170&food=중식&food=후식

		 * Get방식으로 요청하면 doGet메소드가 호출됨
		 * 
		 * 첫 번째 매개변수인 HttpServletRequest request에는 요청 시 전달된 내용들이 담김
		 * => 사용자가 입력한 값, 요청전송방식, 요청한 사용자의 ip주소 등..
		 * 
		 * 두 번째 매개변수인 HttpServletResponse response에는 요청 처리 후 응답을 할 때 사용하는 객체
		 * 
		 * 요청 처리 스텝
		 * 
		 * 1. 우선, 요청을 처리하기 위해 요청 시 전달된 값(사용자가 입력한 값)들을 뽑는다.
		 * => request의 parameter라는 영역에 값이 존재
		 * => key-value 세트로 담겨있음!!!!!(name속성값 = value속성값)
		 * 
		 * 2. 뽑아낸 값들을 가지고 요청 처리해야함(Service => DAO => DB)
		 * 
		 * 3. 처리 결과에 따른 성공 / 실패 페이지 응답

request의 parameter영역으로부터 전달된 데이터를 뽑는 메소드

		 * 요청 처리 스텝
		 * 
		 * 1. 우선, 요청을 처리하기 위해 요청 시 전달된 값(사용자가 입력한 값)들을 뽑는다.
		 * => request의 parameter라는 영역에 값이 존재
		 * => key-value 세트로 담겨있음!!!!!(name속성값 = value속성값)
		 * 
		 * 2. 뽑아낸 값들을 가지고 요청 처리해야함(Service => DAO => DB)
		 * 
		 * 3. 처리 결과에 따른 성공 / 실패 페이지 응답

  
  		 * request의 parameter영역으로부터 전달된 데이터를 뽑는 메소드
		 * 
		 * - request.getParameter("키값") : String(그에 해당하는 value값);
		 * => 무조건 문자열 형태로 반환되기 때문에
		 *   	다른 자료형으로 변경하려면 파싱해야 함
		 *   
		 * - request.getParameterValues("키값") : String[] (그에 해당하는 value값)
		 * => 하나의 key값으로 여러 개의 value들을 받는 경우(checkbox)
		 * 		String 배열형으로 반환 가능
     
     	// 3단계
		// 원래는 JSP가지고 해야하지만 Servlet으로 만들 것임
		// 자바를 이용해서 응답페이지 넘기기(Java코드 안에다가 HTML 코드를 넣는다)
		// 장점 : Java코드 내에서 작성하기 때문에 반복문이나 조건문, 유용한 메소드들을 활용 가능
		// 단점 : 복잡, 혹시라도 나중에 HTML을 수정하고자 할 때 java코드 내에서 수정이 이루어지기 때문에
		//		다시 수정한 내용을 반영시키려면 서버를 restart해야함
		
		// * response객체를 통해 사용자에게 html(응답화면) 전달
		// 1단계) 이제부터 내가 출력할 내용은 문서형태의 html이고 문자셋은 utf-8을 선언하겠다.
		response.setContentType("text/html; charset=UTF-8");
		
		// 2단계) 응답하고자하는 사용자와의 스트림을 연결(클라이언트와의 통로를 생성)
		PrintWriter out = response.getWriter();
		
		// 3단계) 생성된 스트림을 통해서 응답 HTML구문을 한 줄씩 출력
		out.println("<html>");
		out.println("<head>");
		out.println("<style>");
		
		out.println("h1{color : orangered}");
		out.println("#name{color : skyblue}");
		out.println("#age{color : red}");
		out.println("#city {color : yellowgreen}");
		out.println("#height{color : blue}");
		out.println("#gender{color : purple}");
		out.println("li{color : pink}");
		
		out.println("</style>");
		out.println("</head>");
		
		out.println("<body>");
		
		out.println("<h1>개인정보 응답화면</h1>");
		
		/*
		 * XXX님은
		 * XX살이며
		 * XXX에 삽니다.
		 * 키는 XXX cm이고
		 * 
		 * 성별 case1. 선택을 안했습니다.
		 * 	   case2. 남자/여자입니다.
		 * 좋아하는 음식은 case1. 없습니다.
		 * 			  case2. 뭐시기뭐시기뭐시기
		 */
     
  
  
