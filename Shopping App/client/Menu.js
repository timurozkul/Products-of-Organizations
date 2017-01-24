Template.Menu.onCreated(function(){//vid #19
	var self = this;
	self.autorun(function(){ //autorun unsubscribes from any old subscription
		self.subscribe('recipes');
	});
});

Template.Menu.helpers({
	recipes: ()=> { 
		return Recipes.find({inMenu: true});//Gets from the collection
	}
});