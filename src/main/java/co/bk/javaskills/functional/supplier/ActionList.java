package co.bk.javaskills.functional.supplier;

import java.util.ArrayList;
import java.util.function.Supplier;

/**
 * Use a supplier function to instantiate the custom list.
 */
public class ActionList extends ArrayList<Action> {

    public static final Supplier<ActionList> factory = ActionList::new;

}
