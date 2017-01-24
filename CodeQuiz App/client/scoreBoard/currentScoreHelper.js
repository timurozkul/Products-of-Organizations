
Template.score.helpers({//Displays questions & answers
	'isUser': function(param){
	
		if(Meteor.userId() === param){
			
		return true;
	}else{return false;}
	}
});