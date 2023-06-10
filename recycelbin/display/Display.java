package recycelbin.display;


import GroProOnlinestrategiespiel.framework.*;


/**
 * @<code>Display</code> as base 'factory' is merely a role for the class.
 * It creates {@link #< Description >} and use its rendering to {@link Display}
 * this.
 * @version 1.0
 * @created 26-July-2022 12:37:40 PM
 */
public abstract class Display  {


	public void display (){

		Description description = createDescription();
		description.render();
	}

	/**
	 *
	 * @return {@link Description}. This will be overridden in order to create
	 * specific Description namely {@link InputDescription #{@link OutputDescription }}.
	 */
	public abstract Description createDescription();


}//end Display 