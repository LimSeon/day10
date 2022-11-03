package com.kh.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RequestGetServlet
 */
@WebServlet("/test1.do")
public class RequestGetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RequestGetServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//System.out.println("진짜야?? 이승철이 뻥치는거 아니야???");
		
		/*
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
		 */
		
		/*
		 * request의 parameter영역으로부터 전달된 데이터를 뽑는 메소드
		 * 
		 * - request.getParameter("키값") : String(그에 해당하는 value값);
		 * => 무조건 문자열 형태로 반환되기 때문에
		 *   	다른 자료형으로 변경하려면 파싱해야 함
		 *   
		 * - request.getParameterValues("키값") : String[] (그에 해당하는 value값)
		 * => 하나의 key값으로 여러 개의 value들을 받는 경우(checkbox)
		 * 		String 배열형으로 반환 가능
		 */
		
		// 1단계
		String name = request.getParameter("name");
		// "홍길동" / ""(텍스트 상자가 빈 경우 빈문자열이 넘어온다)
		
		String gender = request.getParameter("gender");
		// "M" / "F" / null(라디오 버튼의 경우 체크된 것이 없을 경우 null이 넘어온다)
		
		int age = Integer.parseInt(request.getParameter("age"));	// : String
		// "15" / ""
		// "15" -> 15
		// Wrapper 클래스로 파싱
		// "" : NumberFormatException 발생 
		
		String city = request.getParameter("city");
		// "서울특별시", "경기도", "강원도", "충청도", "전라도", "경상도", "제주도"
		// 기본적으로 빈문자열이 들어올 수 없음 (option>select)
		
		double height = Double.parseDouble(request.getParameter("height"));
		// 170.0
		
		String[] foods = request.getParameterValues("food");
		// ["한식", "일식"]
		// null(체크박스의 경우 체크된 것이 하나도 없으면 null이 넘어옴)
		
		// name=홍길동&gender=M&age=2&city=서울시&height=170&food=한식&food=중식&food=일식
		/*
		// 출력해보기
		System.out.println("name : " + name);
		System.out.println("gender : " + gender);
		System.out.println("age : " + age);
		System.out.println("city : " + city);
		System.out.println("height : " + height);

		if(foods == null){
			System.out.println("foods : 없음");
		} else {
			System.out.println("foods : " + Arrays.toString(foods));
		}
		*/
		
		// 자주보는 오류
		// 404 : 파일이나 요청을 받아주는 서블릿을 못찾았을 때 발생 => 경로가 잘못되었거나 또는 파일명에 오타가 났을 때
		// 500 : 자바 소스코드상의 오류(예외발생)
		
		// 2단계
		// 뽑아낸 값들 가지고 요청 처리해야된다(DB와 상호작용 => JDBC)
		// 보통의 흐름 : Controller단에서 Service단의 메소드를 호출 값은 전달 - DAO 호출 - DB SQL문 실행 - 결과반환
		/*
		Person p = new Person(name, gender, age, city, height, foods);
		
		int result = new PersonService().inserPerson();
		
		if(result > 0) {	// 성공시
			성공페이지 포워딩
		} else { // 실패시
			
		}
		*/
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
		
		out.printf("<span id='name'>%s</span>님은 <br>", name);
		out.printf("<span id='age'>%d</span>살이며 <br>", age);
		out.printf("<span id='city'>%s</span>에 삽니다. <br>", city);
		out.printf("키는<span id='height'>%.1f</span>cm 이고 <br>", height);
		out.print("성별은");
		
		if(gender == null) {
			out.println("선택을 안했습니다.");
		} else {
			if(gender.equals("M")) {
				out.println("<span id='gender'>남자</span>입니다. <br>");
			} else {
				out.println("<span id='gender'>여자/span>입니다. <br>");
			}
		}
		
		out.print("좋아하는 음식은");
		if(foods == null) {
			out.println("없습니다.");
		} else {
			out.println("<ul>");
			
			for(int i = 0; i < foods.length; i++) {
				out.printf("<li>%s</li>", foods[i]);
			}
			out.println("</ul>");
		}
		out.println("</body>");
		out.print("</html>");
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
