<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
		Vue : Evan You (구글 => AngularJS:복잡)
			  IBM => OS2, MS 도스창
			   ㅣ단순한 프레임워크 VueJS => 1. 단순하다, 가벼운 프레임워크
			   							2. 데이터를 효율적으로 처리
			   							3. 속도가 빠르다
			   							4. 코드의 재사용
			   							5. 컴포넌트 기반
			   				=> 전자상 거래, 대시보드, 블로그, 뉴스 사이트
			   
		
		사용 : MVVM
			  M (Model) : 데이터 저장 => data()
			  V (View) : 화면 출력
			  			 {{}}, v-for, v-model, v-if, v-show, v-if, v-else
			  VM (ViewModel) : 상태(데이터 관리, 연산처리) 
			  				   생명주기
			  				   1. mounted : onLoad
			  				   2. updated : 수정
			  				   3. 사용자 정의
			  				   		methods:{
			  				   			=> 이벤트 처리
			  				   		}
			  				   4. components:{
			  				   			기능 => 이미지 카드, 애니메이션 => 재사용이 가능
			  				   	  }
			  				   5. filter : 10000 => 10,000
			  				   		=> computed : 계산된 경우
			 --------------------------------------------------
			 1. Vue객체 생성 => 여러개 생성이 가능
			    ------
			    ㅣ범위 지정 ==> mount('태그명|클래스명|ID명')
			    
			    let app=Vue.createApp({
			    	--------------------
						Model => 데이터			    	
						data(){
							return {
								fno:0, (Number)
								fd:'', (String)
								list:[], (Array)
								obj:{}, Object => 자바스크립트 객체
								isShow:true, Boolean
							} ==> 선언 / 초기화 : 서버읽기는 불가능 (Spring / NodeJS)
						}
			    	------------------------------ 위의 값을 갖고올때 this!!!!!!!****
			    		ViewModel => 데이터 처리
			    		1) 변수의 초기화
			    			=> 서버나 파일
			    			=> 이미 만들어진 메소드 (CallBack) => Vue동작시 자동으로 호출
			    			=> components
			    			mounted(){  ==> 시작과 동시에 (main())
			    				서버나 파일 읽기 => data에 저장된 변수의 초기화
			    				===
			    				**axios.get("서버URL",{
			    								서버가 요청하는 데이터 설정
			    								params:{
			    									fno:1,
			    									id:'admin'
			    								}
			    				  			}).then(response=>{
			    				  					멤버변수에 대입
			    				  			})
			    				**axios.post("서버URL",{
			    								서버가 요청하는 데이터 설정 => get과 달리 params를 쓰지 않는다
			    									fno:1,
			    									id:'admin'
			    				  			}).then(response=>{
			    				  					멤버변수에 대입
			    				  			})
			    			}
			    	------------------------------
			    		2)	사용자정의 메소드
			    		이벤트 (버튼 클릭, 마우스 오버,...)
			    		methods:{
			    			btnClick(){
			    			},
			    			mouseClick(){
			    			}
			    		}
			    		재사용을 목적으로 
			    		components:{
			    			template:'<div></div>'
			    		}
			    	
			    })
			    
			    ======================================
			    화면 출력
			    
			    출력 형식
			    <div>{{data()에 설정}}</div> => text()
			    <div :data-no="fd"> ==> :속성명="변수명" => 변수명은 data()블럭에 선언된 멤버변수명만!!!
			    	 =  				= 클론필수!
			    	 
			   	디렉티브
			   		=> v-for="vo in 배열" => v-for="(vo,index) in 배열" => 동시에쓰면 index도 가능
			   		=> v-if="true/false"
			   		=> v-show="true/false" => display:none, display:''
			   		=> v-if ~ v-else
			   		=> v-if ~ v-elseif ~ v-else
			   		=> v-model => 입력값 => 멤버변수에 전송 => 멤버변수 설정을 해놔야함	
			   	=> 이벤트 
			   		v-on:click	==> @click => 사용자 정의 메소드 호출
			   		v-on:change	==> @change
			   		v-on:keyup	==> @keyup.enter, space ... 무슨키인지 설정가능
			   		v-on:keydown ==> @keydown
			  
			    => 프로그램
			    	=> 반복수행을 할 때 : 메소드를 제작 (사용자 정의 메소드)
			    	
			    	=> 시작과 동시에 데이터 읽기  
			    	   ==================== mounted()
			    	=> 이전 this.curpage-1
			    	=> 다음 this.curpage+1
			    	=> 블록별 번호 this.curpage=값 대입
			    
			    class A{
			    	state={
			    		data(0
			    	}
			    	componentDidMount(){} {mounted()}
			    	btnClick(){
			    	
			    }
			    
			    => react : 단독처리
			    
			    => JSP / Spring 
 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 960px;
}
.images:hover{
	cursor: pointer;
}
</style>
</head>
<body>
  <%-- View --%>
  <div class="container">
    <div class="row">
      <h3 class="text-center">{{message}}</h3>
      <input type="button" value="클릭" @click="change()"><br>
      <input type="text" size="20" v-model="message">
    </div>
  </div>
  <%--  --%>
  <script>
   let app=Vue.createApp({
		// 데이터 설정 => Model
		data(){
			return {
				message:'Hello Vue'	
			}
		},
		// 데이터 처리 / 초기화 => ViewModel => MVVM
		methods:{
			change(){
				this.message="변경됨"
			}
		}
   }).mount('.container')
   
  </script>
</body>
</html>



