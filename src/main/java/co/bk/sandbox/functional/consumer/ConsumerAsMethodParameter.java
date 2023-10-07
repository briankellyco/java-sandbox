package co.bk.sandbox.functional.consumer;

import co.bk.sandbox.util.Developer;
import co.bk.sandbox.util.PrinterService;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerAsMethodParameter {

    public static void main(String[] args) {

        ConsumerAsMethodParameter example = new ConsumerAsMethodParameter();

        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer("tom", 10));
        developers.add(new Developer("beatrice", 20));
        developers.add(new Developer("felicity", 30));

        example.doProcessingBeforeExecutingAction(developers);

    }

    private PrinterService printerService;

    public ConsumerAsMethodParameter() {
        printerService = new PrinterService();
    }

    private void doProcessingBeforeExecutingAction(List<Developer> developers) {

        for (Developer developer: developers) {

            // Specify what "consumers" (actions essentially) act on the processedDeveloper object
            this.processDeveloper(developer, processedDeveloper-> {
                printerService.updateConsoleToLowercase(processedDeveloper);
                printerService.updateConsoleToUppercase(processedDeveloper);
            });
        }
    }

    /**
     *  The actionToRunOnDeveloper can actually be a series of actions.
     */
    public void processDeveloper(Developer input, Consumer<Developer> actionToRunOnDeveloper) {

            Developer developerChanged = input; // making it obvious when we read through this example
            long salary = developerChanged.getSalary();
            developerChanged.setSalary(salary * 2); //developer processing has finished... ready to go to the Consumer for further processing...

            actionToRunOnDeveloper.accept(developerChanged);
    }
}
