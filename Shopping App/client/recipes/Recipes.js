Template.Recipes.onCreated(function(){
	var self = this;
	self.autorun(function(){ //autorun unsubscribes from any old subscription
		self.subscribe('recipes');
	});
});

Template.Recipes.helpers({
	recipes: ()=> { 
		return Recipes.find({});//Gets from the collection
	}
});