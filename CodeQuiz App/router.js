Router.onBeforeAction(function (pause) {
  if (!Meteor.user()) {
    // render the login template but keep the url in the browser the same
    Router.go('welcomePage');
  }
  this.next();
});

Router.route('/', { 
	name: 'welcomePage',
  waitOn: function() {
    return Meteor.subscribe('quiz')
  },
  action: function(){
    this.render('welcomePage', {
    data: function() {
      return {
        'quizes': Quizes.find()
      }
    }
    });  
  } 
});

Router.route( '/mainPage',{ 
  name: 'mainPage',
  waitOn: function(){
    return Meteor.subscribe('quiz')
  },
  action: function(){
    this.render('mainPage', {
      data: function(){
        var d = new Date();
        var todaysDate = d.getFullYear() + "-" + d.getMonth() + "-" + d.getDate();

        var quizes = Quizes.find({
                                  $or: [{expiringDate: {$gt: todaysDate}},
                                   {expiringDate: ""}], //yyyy-mm-dd
                                  'quizReady': true,
                                  'isPrivate': false
                                 }, {
                                  sort: {
                                    quizName: 1
                                  }
                                 })//.sort({$natural: 1})
     
        var userQuizes = Quizes.find({'createdBy': Meteor.user()._id
                                      }, {
                                         sort: { 
                                           quizName: 1
                                         }
                                    })
        return {
          'quizes': quizes,
          'userQuizes': userQuizes
        }
      }
    });  
  }
});

Router.route('/createQuizPage', { 
  name: 'createQuizPage',
  waitOn: function(){
    quizId = Session.get('QuizId');
    if(typeof(quizId) !== 'undefined'){
      return [
        Meteor.subscribe('quiz', { quizId: quizId})
      ];
    }
  },
  data: function(){
    if(typeof(quizId) !== 'undefined'){
      var quiz = Quizes.findOne({_id:quizId});
      return {
        'quiz': quiz
      };
    }
  },
   action: function(){
    this.render('createQuizPage');
  } 
});

Router.route('/createQuestionPage', { 
  name: 'createQuestionPage',
   waitOn: function(){
    quizId = Session.get('QuizId');
    if(typeof(quizId) !== 'undefined'){
      return [
        Meteor.subscribe('quiz', { quizId: quizId})
      ];
    }
  },
  data: function(){
    if(typeof(quizId) !== 'undefined'){
      var quiz = Quizes.findOne({_id:quizId});
      return {
        'quiz': quiz
      };
    }
  },
   action: function(){
    this.render('createQuestionPage');
  } 
});
