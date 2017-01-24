//MANGO NOTES
//The $set operator replaces the value of a field with the specified value.
//To specify a <field> in an embedded document or in an array, use dot notation.

Meteor.methods({
	'addQuiz': function(quiz){
		return Quizes.insert(quiz);
	},

	'addQuizQuestion': function(quizId, question){
		var quiz = Quizes.findOne({ _id: quizId });
		var index = quiz.questions.length
		question['index'] = index
		Quizes.update({
			_id: quizId
		},{
			$push:{
				questions: question
			}
		})
	},

	'updateQuiz': function(quiz, quizId){
		return Quizes.update({_id: quizId}, quiz);
	},

	'updateQuestion': function(quizId, index1, question){
		Quizes.update({
				_id: quizId,
				'questions.index': parseInt(index1),
			},
			{
				$set:{
					'questions.$.question': question.question,
					'questions.$.answers': question.answers,
				}
			});
	},

	'deleteQuestion': function(quizId, index1){//Makes first null, then removes null
		var query = {"_id": quizId}, 
		update = { "$unset": { } };

	    update["$unset"]["questions." + index1] = 1;
	    
	     	Quizes.update({
				_id: quizId,
				'questions.index': parseInt(index1),
			},{$unset: {'questions.$': null}})

	    Quizes.update({
						_id: quizId
					},{
						$pull: {questions: {$in: [null]} }
					});
		},

	'quizReady': function(quizId){
		Quizes.update({_id: quizId},
				{$set: {'quizReady': true}})

	 	Scores.insert({	
	 		quizId: quizId,
	  	 	usersWhoPlayed: []
	  	})

	},

	'deleteQuiz': function(quizId){
		Quizes.remove({
							_id: quizId
						}, {
					    	justOne: true
						})
	}
})