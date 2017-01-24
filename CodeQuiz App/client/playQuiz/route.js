Router.route('/play/:_id', { 
  name: 'playQuiz',
   waitOn: function(){
    var params = this.params;
    quizId = params._id;
    Session.set('quizId', quizId);
    
    if(typeof(quizId) !== 'undefined'){
      return [
        Meteor.subscribe('quiz', { 'quizId': quizId})
      ];
    }
  },
   action: function(){
   
    var currentQuestion = Session.get('currentQuestion') || 0;
    this.render('playQuizPage', {
      data: function(){
        
        var quiz = Quizes.findOne({_id:quizId});
        
        
        var question = quiz.questions[currentQuestion];


        return {
          'quiz': quiz,
          'question': question,
          'currentQuestion': currentQuestion,
        };
      },
    });
  } 
});