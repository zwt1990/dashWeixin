$(function(){
	initEvent();
	initData();
	
})
function initEvent(){
	 $('.i-checks').iCheck({
         checkboxClass: 'icheckbox_square-green',
         radioClass: 'iradio_square-green',
     });
	$("#sumbitBtn").click(function(){
		submitExam();
	});
      
}
function initData(){
	  $.ajax({
		     type: 'POST',
		     url: '../exam/getQuestions' ,
		    data: {
		    		examId:1
		    } ,
		    dataType: 'json',
		    success: function(data){
			    if(!data.status){
				    alert("请求错误");
				    return;
			    }
			    $("#examName").html(data.exam.name)
		        var t=_.template($("#questionTemplate").html(),data.questions);
		        $("#container").html(t);
		    } 
		});
}


function getAnswers(){
	var questions=[];
	 $(".form-control-static").each(function(){
		    var id=$(this).attr("id");
		    var optNames="opt_"+id;
			var value  = $('input[name=\"'+optNames+'\"]:checked').val(); 
			if(!value){
				alert("当前还有未完成的题目!");
				questions=[];
				return false ;
			}
		    var question={
		    		id:id,
		    		answer:value
		    }
		    questions.push(question);
		  });
	 return questions;
}

function submitExam(){
	var questions=getAnswers();
	if(questions.length==0){
		return;
	}
	 var exma={userId:'ocOIPwJ20if-n2urZW-AEr6o7RXM',examId:1,questions:questions}
	  $.ajax({
		     type: 'POST',
		     url: '../exam/submitExam' ,
		     data:JSON.stringify(exma), 
		    dataType:"json",      
            contentType:"application/json",  
		    success: function(exma){
			    if(!data.status){
				    alert("请求错误");
				    return;
			    }
			    alert("请求错误");
		    } 
		});
	 
	 
	 
}