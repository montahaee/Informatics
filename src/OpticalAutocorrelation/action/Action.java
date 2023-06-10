package OpticalAutocorrelation.action;

import OpticalAutocorrelation.receiver.*;
import OpticalAutocorrelation.source.Pair;

/**
 * @author Montahaee
 * @version 1.0
 * @created 11-May-2022 11:47:15 AM
 *
 * The class {@link Action} plays the role of sender/invoker
 * like to command pattern and is responsible for initiating a
 * requests. This helps to track the history of executed/activated
 * operations and makes it possible to revert an operation if needed.
 * The actions {@link #<Detector>}{@link #<Evaluator>}
 * {@link #<Simulator>}which result in changing the state of the Receiver
 * @see OpticalAutocorrelation.receiver.Receiver make a backup of the
 * receiver state before excecuting an operation associated with the action.
 * After an actinon is exceuted/activated, it's placed into the action history
 * @see OpticalAutocorrelation.action.ActionHistory alonge with the backup
 * action of the receiver's state at that point.
 */
public abstract class Action {

///	enum Type {
//		TEXT,
//		FILE
//	}

///	private String backupStr;
//	private Map<?, ?> backup;
	private Pair<?, ?> backup ;
//	private DataSource backup1 ;
	public Receiver receiver;

	/**
	 * 
	 * @param thatReceiver to construct
	 */
	Action(Receiver thatReceiver){
		this.receiver = thatReceiver;
	}

//	Make a backup of the receiver's state.
	void backup(){

		this.backup = receiver.transformation.miner();
	}
// 	void backup1(){
//		this.backupFile = this.receiver.;
//	}

	public abstract boolean activate();

	public void undo(){
		receiver.processor = backup;
///		receiver.processor = receiver.transfer.miner();
///		backup.forEach((k, v) -> receiver.result.put(k,v) ) ;
///		switch (receiver.type) {
//
//			case TEXT -> {
////				backup = new HashMap<String, String>();
//				receiver.setPriority(2);
//				receiver.result.putAll(backup);
//			}
//			case FILE -> {
//			}
//			default -> throw new IllegalStateException("Unexpected value: " + receiver.type);
//		}
//		receiver.result.putAll(backup);
	}
//	public abstract Pair<?,?> createSource(); //{
////		DataSource dataSource =
////	}
//	public

}//end Action