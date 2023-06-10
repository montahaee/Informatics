package OpticalAutocorrelation.action;


//import java.io.File;
import java.util.Stack;

/**
 * @author Montahaee
 * @version 1.0
 * @created 11-May-2022 11:47:15 AM
 */
public class ActionHistory {

	private final Stack<Action> history = new Stack<>();

	public boolean isEmpty(){
//		if (history.contains(File))
		return history.isEmpty();
	}

//	public boolean isFile (Action //action) {
//		return history.contains(action.);
//	}


	public Action pop(){
		return history.pop();
	}

	/**
	 * 
	 * @param action is added at the head of the history.
	 */
	public void push(Action action){
		history.push(action);
	}
}//end ActionHistory