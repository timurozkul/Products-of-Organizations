scoreCorrect = 0; 
scoreFalse = 0;

Meteor.methods({
	'validateAnswer': function(quizId, ansIndex, questIndex){
		var quiz = Quizes.findOne({
			_id: quizId,
		});

		//Checks for correct answer && keeps score
		var question = quiz.questions[questIndex];
		_.each(question.answers, function(ans, key){
			if(ans.index === parseInt(ansIndex)){

				if(ans.radioCorrect){
					console.log('increase correct count');
					scoreCorrect ++;
				}
				else{
					console.log('increase incorrect count');
					scoreFalse ++;
				}
			}
		});

		//If the last question -> add new data to collection
		var lastQues = false;
		if(quiz.questions.length -1 === questIndex){
			lastQues = true;
			var score = Scores.findOne({
				'quizId': quizId,
				'usersWhoPlayed.user': this.userId
			});

			if(typeof(score) === 'undefined'){

				var playersQuizData = {
			       user: this.userId,
		           dateOfPlay: new Date(),
		           score: Math.round((scoreCorrect/(scoreCorrect + scoreFalse))*100) + "%",
		           correct: scoreCorrect,
		           wrong: scoreFalse
				}
				Scores.update({
					'quizId': quizId
				},{
					$push:{
						'usersWhoPlayed': playersQuizData
					}
				});
			}
			else{
				Scores.update({
					'quizId': quizId,
					'usersWhoPlayed.user': this.userId
				},{
					$set:{
						'usersWhoPlayed.$.dateOfPlay': new Date(),
						'usersWhoPlayed.$.score': Math.round((scoreCorrect/(scoreCorrect + scoreFalse))*100) + "%",
						'usersWhoPlayed.$.correct': scoreCorrect,
						'usersWhoPlayed.$.wrong': scoreFalse
					}
				});
			}	
			scoreCorrect = 0; //reseting score
			scoreFalse = 0;
			return lastQues;
		}	
	}
});
