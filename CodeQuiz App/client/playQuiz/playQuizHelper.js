var clock = function(){//substracts 1s 

	var time = Session.get('clockDisplay')
	time = time - 1000;
	Session.set("clockDisplay", time); 
}

Template.quizQuestion.helpers({
	clock: function(){ 
		var quiz = Template.currentData().quiz;
		var time = quiz.QuesTime;
		
		Session.set('clockDisplay', time);
		
		var timer = Meteor.setInterval(clock, 1000);//runs every 1s

		var finishQuiz = function(){
			Session.set('timeup', true);

			alert("time up");//Create CSS instead
			Meteor.clearInterval(timer)
			$('#submitAnswer').click();
		}

		var timeTillNextQues = Meteor.setTimeout(finishQuiz, time);//runs after 'time' variable (seconds)
	},//clock

	clockDisplay: function(){

		var time = Session.get("clockDisplay");

		return timeConvertor2(time);
	}
});
