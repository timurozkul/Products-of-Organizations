Template.quizCreateQues.onRendered(function(){
		var quiz = Quizes.findOne({_id:Session.get('QuizId')});
		var editQuesIndex = Session.get('editQuesIndex');
		var question;

		if(typeof(editQuesIndex) !== 'undefined'){//fills the fields if pressed on edit Question
		
			_.each(quiz.questions, function(val, key){
				if(val.index === parseInt(editQuesIndex)){
					question = val;
				}
			});
			counter = 2
			$('#questionField').val(question.question);
			
			for(var i = 0; i < question.answers.length; i++){

				if(i > 1){//Add correct amount of Answer fields to fill
					$('#addAns').click();
				}//if
				
				//Fill answer fields
				$('.row .ansField:eq('+ i +')').val(question.answers[i].answer);		
			}//for
		}//if typeof
})
