//				To Do List

// QUESTIONS for Mr. Law
// name of files
// global function

Template.quizCreateBody.events({

	'click #addQues': function(){
		
		if(fieldValidator()){//checks field requirments are met
			uploadQuizData();//create or update quiz to database
			counter = 2;
			Router.go('/createQuestionPage');
		}
	},//click #addQues

	'click #finishQues': function(){
		if(fieldValidator()){
			uploadQuizData();

			var quizId = Session.get('QuizId');
			Meteor.call('quizReady', quizId, function(){
				Session.set('QuizId');
				Router.go('/mainPage');
			});
		}
	}
});//QuizCreateBody.events
		//	----------------------------  //VALIDATIONS  -----------------------------

Template.displayQuestions.events({
	'click .fa-trash': function(e){
		var index = $(e.currentTarget).attr('id');
		var quizId = Session.get('QuizId');

		Meteor.call('deleteQuestion', quizId, index);		
	},

	'click .fa-pencil': function(e){

		var index = $(e.currentTarget).attr('id');
		Session.set('editQuesIndex', index);
		Router.go('/createQuestionPage');
	}
})



