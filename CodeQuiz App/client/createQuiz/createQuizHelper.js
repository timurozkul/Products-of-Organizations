Template.quizCreateBody.onRendered(function(){
	var quiz = Quizes.findOne({_id:Session.get('QuizId')});

	if(typeof(quiz) !== 'undefined'){//Fills the Fields
		var time = timeConvertor2(quiz.QuesTime);
		$('#titleField').val(quiz.quizName);
		$('#themeField').val(quiz.theme);
		$('#minuteField').val(time.substring(0, 2)); //turning into minute/second(jquery)
		$('#secondField').val(time.substring(3, quiz.QuesTime.length));
		if(quiz.isPrivate){$('#isPrivate').prop('checked', true);}
		if(quiz.expiringDate !== ""){
			$('#expires').prop('checked', true);
			$('#expiringDate').show();
			$('#expiringDate').val(quiz.expiringDate);
		}
	}		
});

Template.quizCreateBody.events({//Toggles for expire
	'click #expires': function(e){
		
		if($(e.currentTarget).prop('checked')){
			$('#expiringDate').show();
		}
		else{
			$('#expiringDate').hide();
			$('#expiringDate').val("");	
		}
	}
});

Template.displayQuestions.helpers({//Displays questions & answers
	'displayer': function(){
		return Quizes.findOne({_id:Session.get('QuizId')});
	}
});







