package com.garay.backend.views;


public class PostViews {
	
	//Enclosing type to define User views
	 public static interface UserView {
	      //External View for User 
	      public static interface ShortView {
	      }
	      
	      /*
	      //External View for User 
	      public static interface ShortView {
	      }    
	      */
	      
	      //Intenal View for User, will inherit all filds in External
	      public static interface FullView extends ShortView {
	      }
	      
	      public static interface ConfirmUser extends ShortView {
	      }
	  }
}