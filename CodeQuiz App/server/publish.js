Meteor.publish('scores', function(params){
	var query = {}, filters = {};

	if(typeof(params) !== 'undefined'){
		if('quizId' in params){
			query['quizId'] = params['quizId'];
		}
	}

	return Scores.find(query, filters);
});

Meteor.publish('quiz', function(params){
	var query = {}, filters = {};

	if(typeof(params) !== 'undefined'){
		if('quizId' in params){
			query['_id'] = params['quizId'];
		}
	}

	return Quizes.find(query, filters);
});