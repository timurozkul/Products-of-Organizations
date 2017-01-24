Router.route('/scoreBoard', { 
  name: 'scoreBoard',
  waitOn: function() {
    quizId = Session.get('quizId');

  	Meteor.subscribe('scores', {quizId: quizId});
    Meteor.subscribe('quiz');
  },
  action: function(){
    this.render('scoreBoard', {
      data: function(){ 

        var score = Scores.findOne({quizId: quizId});  
        var quiz = Quizes.findOne({_id: quizId});

        return {
          'quiz': quiz,
          'score' : score
        };
          
      }
    }); 
  }
});