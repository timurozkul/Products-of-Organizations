counter = 2;// 0 & 1 are already in the html, below the rest of the answer fields are created dynamicaly


Template.quizCreateQues.events({

	'click #addAns': function(e){//Creates extra answer fields
		e.preventDefault();
		
		if(counter < 8){
		$('#AnsField').append('<div class="row dynamicAns"> <div class="col-md-10"> <label for="">Answer</label> <div class="input-group">' 
			+ ' <span class="input-group-addon" > <input type="radio"  name="answer" class="radio"> </span> <input type="text" class="form-control ansField" placeholder="" maxlength="250"> '
			+ ' <span class="input-group-btn" ><button class="btn btn-default" type="button" id="deleteButton"> <i class="fa fa-trash"></i> </button> </span></div></div></div>');

		counter ++;//Used in iteration of rows below
		}else{ alert("Reached the maximum amount of answers per question."); }
	},

	'click #deleteButton': function(e){
		e.preventDefault();
		$(e.currentTarget).closest( ".dynamicAns" ).remove();	
		counter --;
	},

	'submit #creationForm': function(e){//Get & fill object for collection push
		e.preventDefault(); 
		if(fieldValidator()){//Checks field requirments are met
				uploadData();//Adds question/answers to the quiz
		}

		function uploadData(){
			
			var ansArray = [];//Iteration will fill this array
	
			for(var i = 0; counter > i; i++){
				
				var radioBoolean;//Setting boolean for object
				if($('input[name="answer"]:checked')[i] !== undefined){
					radioBoolean = true;
				}else{radioBoolean = false;}
				
				var objAns = {//Object going in ansArray
					answer: $('#creationForm').find('.row .ansField:eq('+ i +')').val(),
					radioCorrect: radioBoolean,
					index: i
				}
				ansArray.push(objAns);
			}//for loop

			var quizQuestion =  {
					question: $('#creationForm').find('#questionField').val(),
					index: newIndexNo(),
					answers: ansArray
					
			}

			
			if(typeof(Session.get('editQuesIndex')) !== 'undefined'){//update Question if editing question
				
				Meteor.call('updateQuestion', Session.get('QuizId'), Session.get('editQuesIndex'), quizQuestion, function(err){
						Session.set('editQuesIndex');
						Router.go('/createQuizPage');
						});
			}else{//adds the question to the collection
				Meteor.call('addQuizQuestion', Session.get('QuizId'), quizQuestion, function(err){
						Router.go('/createQuizPage');
						});
			}
		}//uploadData

		function newIndexNo(){
			var quiz = Quizes.findOne({_id:Session.get('QuizId')});

			return quiz.questions.length + 1;
		}

		// ----------------------------  VALIDATIONS  -----------------------------
		function fieldValidator(){
			var validated = true;
			validated = validateQues($('#questionField')[0].value);
			for(var i = 0; counter > i; i++){
				if(validated)

				validated = validateAns($('#creationForm').find('.row .ansField:eq('+ i +')').val(), i);
			}
			return validated;
		}

		function validateAns(text, ansNumber){

		var validated = true;
		ansNumber + 1; // for client side numbering
		
			if(text.length < 4){
					alert('Your input for answer '+ ansNumber +' must be greater than 4 characters.');
					validated = false;
				}			 		
			return validated; 
		}

		function validateQues(text){
			var validated = true;

			if(text.length < 4){
					alert('Your question must be greater than 4 characters.');
					validated = false;
				}
			return validated
		}
	}//submit #creationForm
});


Template.quizCreateQuesHead.events({

	'click .fa-chevron-right': function(e){
		Session.set('editQuesIndex');
		Router.go('/createQuizPage');
	},

	'click .page-title h1': function(e){
		Session.set('editQuesIndex');
		Router.go('/mainPage');
	}
})