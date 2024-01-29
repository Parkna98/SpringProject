<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://unpkg.com/vue@3"></script>
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<style type="text/css">
.container-fluid{
	margin-top: 50px;
}
.row{
	margin: 0px auto;
	width: 100%;
}
.images:hover{
	cursor: pointer;
}
</style>
</head>
<body>
  <div class="container-fluid" id="app">
  	<div class="row">
  	  <div class="col-sm-8">
  	    <div class="col-md-3" v-for="vo in food_list">
		    <div class="thumbnail">
		    <!-- :src  클론 잊지않기!!! -->
		        <img :src="'https://www.menupan.com'+vo.poster" alt="Lights" style="width:100%" class="images" 
		          v-on:click="detail(vo.fno)">
		        <div class="caption">
		          <p>{{vo.name}}</p>
		        </div>
		    </div>
		  </div>
  	  </div>
  	  <div class="col-sm-4" v-show="isShow">
  	     <table>
  	       <tr>
  	         <td width=30% class="text-center" rowspan="1"> 
  	          </td>
  	          
  	       </tr>
  	     </table>
  	  </div>
  	</div>
  </div>
  <script src="js/food.js"></script>  
</body>
</html>


