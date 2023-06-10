package Cartography.framework;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public abstract class Description {

    /**
     * This attribute contains a list of pairs with country denotation
     * and its corresponding orbit.
     */
    private List<Map.Entry<String,Orbit>> content;
    private String subject;

    static int count = 0;

    /**
     * Regular constructor.
     */
    public Description() {
        this.content = new ArrayList<>();
        this.subject = "";

    }

    /**
     * The prototyp constructor(copy constructor)
     * @param target will assign its values to a fresh object
     *               as it will be initialized.
     */
    public Description(Description target) {
        if (target != null) {
            this.content = new ArrayList<>(target.content.size());
            this.content.addAll(target.content);
            this.subject = target.subject;
        }
    }

    /**
     *
     * @return one of the Description subclass
     */
    public abstract Description clone();

    /**
     *
     * @param target is existing object.
     * @return true if existing object exact the same with current one.
     */
    @Override
    public boolean equals(Object target) {
        if (!(target instanceof Description description)) {
            return false;
        }
        return description.content == this.content && description.subject.equals(this.subject);
    }

    /**
     *
     * @return the content to next
     */
    public List<Map.Entry<String,Orbit>> getContent() {
        return this.content;
    }


    /**
     *
     * @return the subject of a statistical map.
     */
    public String getSubject() {
        return this.subject;
    }

    public static int count() {
//        var  t = this.content;
//        var t = get
        return 0;
    }

    public void setContent(List<Map.Entry<String, Orbit>> content) {

        this.content = content;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
