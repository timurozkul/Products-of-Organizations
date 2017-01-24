Template.RecipeSingle.onCreated(function(){
	var self = this;
	self.autorun(function(){ //autorun unsubscribes from any old subscription
		var id = FlowRouter.getParam('id');
		self.subscribe('singleRecipe', id);
	});
});

Template.RecipeSingle.helpers({
	recipe: ()=> { 
		var id = FlowRouter.getParam('id'); // Gets this from the router
		return Recipes.findOne({_id: id});//Gets from the collection
	}
});