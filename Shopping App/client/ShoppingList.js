Template.ShoppingList.onCreated(function(){//vid #19
	var self = this;
	self.autorun(function(){ //autorun unsubscribes from any old subscription
		self.subscribe('recipes');
	});
});

Template.ShoppingList.helpers({
	shoppingList: ()=> { 
		return Recipes.find({inMenu: true});//Gets from the collection
	}
});