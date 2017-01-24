Template.mainPageBody.events({
	'click #createBtn': function(){
		Router.go('/createQuizPage');
	},

	'click #playBtn': function(){
		Router.go('/quizListingPage');
	}
})



Template.singleQuizEdit.events({

	'click .fa-pencil': function(e){
		var quizId = $(e.currentTarget).attr('id');
		Session.set('QuizId', quizId);
		Router.go('/createQuizPage');
	},

	'click .fa-trash': function(e){
		var quizId = $(e.currentTarget).attr('id');
		Meteor.call('deleteQuiz', quizId);		
	}
	
})