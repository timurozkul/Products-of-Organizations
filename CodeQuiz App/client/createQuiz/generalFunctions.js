
		uploadQuizData = function(){
			
			if(typeof Session.get('QuizId') === 'undefined'){
				var quiz =  {//Validation Required cannot be blank
					quizName: $('#titleField').val(),
					createdBy: Meteor.userId(),
					theme: $('#themeField').val(),
					isPrivate: $('#isPrivate').prop('checked'),
					expiringDate:  $('#expiringDate').val(),
					quizReady: false,
					createdOn: new Date(),
					QuesTime: timeConvertor(),
					questions:[]
				}

				Meteor.call('addQuiz', quiz, function(err, quizId){
					Session.set('QuizId', quizId);
					Router.go('/createQuestionPage');
				});
			}else{//update
				var quiz ={ 
						    '$set': { quizName: $('#titleField').val(),
					  			    theme: $('#themeField').val(),
					  			    isPrivate: $('#isPublic').prop('checked'),
					  			    expiringDate: $('#expiringDate').val(),
					  			    QuesTime: timeConvertor(),
					  			    createdOn: new Date()
				  			}
				}

				Meteor.call('updateQuiz', quiz, Session.get('QuizId'));
			}
		}


		// ----------------------------  VALIDATIONS  -----------------------------

		fieldValidator = function(){
			var validated = false;
			console.log($('#secondField').val() + "-" + $('#minuteField').val());
			if(($('#titleField').val()).length < 3)
					alert('Your title needs to be greater than 4 characters.');
				 else if(($('#themeField').val()).length === 0)
				 	alert('Please pick a theme.');
				 else if(checkExpiration() === false){}
				 	//alerts executed in checkExpiration(
				 else if(parseInt($('#minuteField').val()) < 1 || $('#minuteField').val() === ""  ){
				 		console.log("inside minute");
				 		if($('#secondField').val() === "" || parseInt($('#secondField').val()) < 1){
				 			alert("choose please a time for your questions");
				 		}else{validated = true;}
				 }		
			     else
			 		validated = true;
			 			
			return validated 
		}

		checkExpiration = function(){
			var d = new Date();
			var tomorrowDate =  d.getMonth() + '-' +  d.getDate() + '-' + d.getFullYear();
			console.log(tomorrowDate);
			var validated = true;

			if($('#expires').prop('checked')){
				
				if($('#expiringDate').val() === '' ){
					alert('Please pick a date')
					validated = false;
				}else if($('#expiringDate').val() < tomorrowDate){
					alert('Please pick a date that is today onwards');
					validated = false;
				}
			}
			return validated;
		}//checkExpiration

		timeConvertor = function(){//turning into minute/second for jquery
			var minute = $('#minuteField').val() * 60000; 
			var second = $('#secondField').val() * 1000;
			return minute + second;
		}

		timeConvertor2 = function(time){//turning back to fill field
			if(time >= 60000){//60000 = 1 min
				var minute = Math.floor(time/60000);//round down
					if(minute < 10){minute = "0" + minute}
				var second = (time -(minute * 60000))/1000;
			}else{
				var minute = "00";
				var second = time/1000;
			}

			if(second < 10){
				second = "0" + second;
			}
			return minute + ":" + second;
		}