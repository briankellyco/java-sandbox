package co.bk.supplier;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActionListTest {

    List<String> names = Arrays.asList(new String[]{"one", "two", "three"});

    @Test
    public void addActionsToList() {

        ActionList actionList = names.stream()
                .map( item -> new Action(item))
                .collect(Collectors.toCollection(ActionList::new));

        assertEquals(3, actionList.size());
    }

}
