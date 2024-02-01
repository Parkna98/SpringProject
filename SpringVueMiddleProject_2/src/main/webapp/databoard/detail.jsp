<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container{
  margin-top: 50px;
}
.row{
  margin: 0px auto;
  width: 700px;
}
</style>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
</head>
<body>
  <%-- View:data()에 설정된 데이터 값에 따라 변경 (자동) --%>
  <div class="container">
    <h3 class="text-center">내용보기</h3>
    <div class="row">
      <table class="table">
        <tr>
          <th width="20%" class="text-center success">번호</th>
          <td width="30%" class="text-center">{{detail_data.no}}</td>
          <th width="20%" class="text-center success">작성일</th>
          <td width="30%" class="text-center">{{detail_data.dbday}}</td>
        </tr>
        <tr>
          <th width="20%" class="text-center success">작성자</th>
          <td width="30%" class="text-center">{{detail_data.name}}</td>
          <th width="20%" class="text-center success">조회수</th>
          <td width="30%" class="text-center">{{detail_data.hit}}</td>
        </tr>
         <tr>
           <th width=20% class="text-center success">제목</th>
           <td colspan="3">{{detail_data.subject}}</td>
         </tr>
        <tr v-if="filecount!=0">
          <th width="20%" class="text-center success">첨부파일</th>
          <td colspan="3">
            <ul>
              <li v-for="(fn,index) in filenames">
                 <a :href="'../databoard/download.do?fn='+fn">{{fn}}</a>&nbsp;({{filesizes[index]}}Bytes)
              </li>
            </ul> 
          </td>
        </tr>
        <tr>
          <td colspan="4" class="text-left" valign="top" height="200">
            <pre style="white-space: pre-wrap;background-color: white;border: none;"></pre>
          </td> 
        </tr>
        <tr>
          <td colspan="4" class="text-right">
            <button class="btn-xs btn-primary">수정</button>
            <button class="btn-xs btn-danger">삭제</button>
            <button class="btn-xs btn-success" @click="dataList()">목록</button>
          </td>
        </tr>
      </table>
    </div>
  </div>
</body>
<script>
 let app=Vue.createApp({
	 // View에 전송할 데이터를 저장
	 data(){
		 return{
			detail_data:{},
			no:${no},
			filenames:[],
			filesizes:[],
			filecount:0
		 }
	 },
	 mounted(){
		// window.onload => 브라우저에 화면이 완성  ==> $(function(){})
		axios.get('../databoard/detail_vue.do',{
			params:{
				no:this.no
			}
		}).then(response=>{
			this.detail_data=response.data
			let leng=response.data.filecount;
			if(leng>0){
				this.filenames=response.data.filename.split(",");
				this.filesizes=response.data.filesize.split(",");
			}
			
			this.filecount=leng;
		})
		
	 },
	 // ViewModel => MVVM 구조 (Model View ViewModel)
	 methods:{
		 // 사용자의 행위 => 사용자 정의 함수
		 dataList(){
			 location.href="../databoard/list.do"
		 }
	 },
	 updated(){ 
		// window.onload => 브라우저에 화면이 완성 
	 }
 }).mount('.container')
</script>
</html>