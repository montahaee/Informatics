package OpticalAutocorrelation.receiver;

import OpticalAutocorrelation.action.*;
import OpticalAutocorrelation.action.Action;
import OpticalAutocorrelation.source.DataSource;
import OpticalAutocorrelation.source.Pair;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Receiver {


//    enum Type {
//        TEXT,
//        FILE,
////		NUMBER
//    }
//    private LinkedList<String> paths = new LinkedList<>();
    private final ActionHistory history = new ActionHistory();
    //	public  File file;
//	public StringBuilder result;
//    public Pair<?, ?> communicator;
    public Pair<?, ?> processor;
    ///	public Map<Object, Object> result;
//    public Type type;
    public DataSource transformation;

    /**
     *
     * @param action could be ready to doing.
     */
    public void activity(Action action){

        if (action.activate()) {
            history.push(action);
        }
    }

    public void init () {
//        System.out.println("Enter the folder path to read data source");
//        java.util.Scanner input = new java.util.Scanner(System.in);
//        String path = input.nextLine();
        JFrame frame = new JFrame("**Enter the folder path to read data source: ");
        JPanel content = new JPanel();
        frame.setContentPane(content);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        JTextArea textField = new JTextArea();
        textField.setLineWrap(true);
        content.add(textField);
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton enter = new JButton("Enter");
        JButton reset = new JButton("Reset");
        String path = textField.getText();
        Receiver receiver =this;
        enter.addActionListener(
         new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                activity(new Detector(receiver,path));
            }
        });
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                undo();
            }
        });

    }

//    @Override
    public void run(){


//        Thread.currentThread().getName()

///        switch (type) {
//            case FILE -> {
//                result = new HashMap<File, String>();
//            }
//            case TEXT -> result = new HashMap<String, String>();
//        }


//		result = new HashMap<>();

    }


    private void undo () {
        if (history.isEmpty()) {
            return;
        }
        Action action = history.pop();
        if (action != null) {
            action.undo();
        }
    }

    public void setProcessor (){
        this.processor = this.transformation.miner();
    }

    public void setTransformation(DataSource transformation) {
        this.transformation = transformation;
    }

    //    private LinkedList<File> getFiles (final String folderPath) {
//        LinkedList<File> circularFiles = new LinkedList<>();
//        File folder = new File(folderPath);
//        File[] files = folder.listFiles();
//        for (File file :
//                Objects.requireNonNull(files)) {
//            if (file.isFile() && file.getName().endsWith(".txt")) {
//                circularFiles.add(file);
//            } else if (file.isDirectory()) {
//                getFiles(file.getPath());
//            }
//        }
//        //		circularFiles.
//        return circularFiles;
//    }
}
