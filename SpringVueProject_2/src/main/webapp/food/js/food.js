 let app=Vue.createApp({
	   data(){
		   return {
			   food_list:[], // ArrayList [{},{},{},...,{}]
			   food_detail:{}, // VO
			   fno:0,
			   isShow:false
		   }
	   },
	   // 브라우저 실행 처리 => window.onload / $(function(){})
	   mounted(){
			axios.get('http://localhost:8080/web/food/list_vue.do').then(response=>{
				console.log(response.data) // response에 여러값이 있는데 data만 들고와라
				this.food_list=response.data // this. 을 반드시찍어야함!
			})
	   },
	   methods:{
		   detail(fno){
			   this.fno=fno;
			   let_this=this
			   axios.get("http://loacalhost:8080/web/food/detail_vue.do",{
				   params:{
					   fno:this.fno // 물음표대신 이런식으로 값을 넣는다! (axios의 형식) => ajax에서 data: 와 비슷한 느낌
				   }
			   }).then(function(response){
				   console.log(response.data)
				   this.food_detail=response.data
			   })
					// 람다식				   
			  
		   }
	   }
	   
   }).mount('#app')