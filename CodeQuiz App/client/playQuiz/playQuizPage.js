

Template.playQuizPage.events({
	'click #submitAnswer': function(e){
		var selectedAnswer = $('#AnsField').find('input[type=radio]:checked');
		Session.set('ansNotSelected', -1);//sets choosen index(ans) to -1(not picked)

		if(Session.get('timeup') === undefined && selectedAnswer.length === 0){//if time is not up and no answer is selected
			alert('select an answer');
		}else{
		//if time is up 

			if(selectedAnswer.length !== 0){//if answer is selected
				var index = selectedAnswer[0].id.split('_')[1];//id = answIndex_1 
				console.log("answer is selected");
			}

			uploadscore(index);
		}

		function uploadscore(index){

				var quiz = Template.currentData().quiz._id;
				var newIndex = Template.currentData().currentQuestion + 1;//adds 1 to change question
			
				Meteor.call('validateAnswer', quiz, index, Template.currentData().currentQuestion, function(err, data){
					Session.set('timeup');//sets back to default
					Session.set('ansNotSelected');

					if(data){//if last question
						Session.set('currentQuestion');
						Router.go('/scoreBoard');
					}else{
						$('input[type=radio]').attr('checked', false);//sets all radio to blank
						Session.set('currentQuestion', newIndex);
					}
				})
		}
	}
});