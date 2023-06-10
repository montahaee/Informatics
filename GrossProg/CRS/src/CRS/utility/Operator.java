package CRS.utility;

import CRS.frameWork.BaseDescription;
import CRS.frameWork.Description;
import CRS.frameWork.InputDescription;
import CRS.frameWork.OutputDescription;

import javax.swing.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class Operator {

    private DomainOperation<?, ?> domain;
    private boolean operating = false;
//    TODO think about input parameter typs Sting or description?
    private final Map<String, Pair<InputDescription,OutputDescription>> descriptions = new LinkedHashMap<>();
//    private final Map<InputDescription, OutputDescription> descriptions = new HashMap<>();
    private String current_context;

//    private String name;

    public Operator() {
        this.domain = new Preparation<>(this);
        setOperating(true);
        current_context = JOptionPane.showInputDialog("This program can do production/addition." +
                " Which kind of them do you wish? P/A: ");

        Description description = new BaseDescription(current_context);
        Pair<InputDescription, OutputDescription> input_output = new Pair<>(
                new InputDescription(description), new OutputDescription(description));
        descriptions.put(current_context, input_output);
    }

    public void setOperating(boolean new_operating) {
        this.operating = new_operating;
    }

    public boolean isOperating() {
        return this.operating;
    }

    public DomainOperation<?,?> getDomain() {
        return this.domain;
    }

    public void changeOperate(DomainOperation<?,?> newDomain) {
        this.domain = newDomain;
    }

    public Pair<InputDescription,OutputDescription> startOperatorBack() {
///        Description description = new BaseDescription(variable_name);
///        description = new InputDescription(description);
///        OutputDescription output = descriptions.get(description);
///        Map.Entry<Description,Description> set =  descriptions.entrySet();
//        Description description = descriptions.entrySet();

        return descriptions.get(current_context);
    }

    public void setCurrent_context(String that_current) {
        this.current_context = that_current;
    }

    public void reset() {
//        descriptions.
    }

}
