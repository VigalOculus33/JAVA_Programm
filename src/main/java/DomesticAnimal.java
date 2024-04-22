import java.util.ArrayList;
import java.util.List;

public abstract class DomesticAnimal extends Animal {
    private List<String> commands;

    protected DomesticAnimal(String name, String birthDate) {
        super(name, birthDate);
        this.commands = new ArrayList<>();
    }

    public void learnCommand(String command) {
        commands.add(command);
    }

    @Override
    public void showCommands() {
        if (commands.isEmpty()) {
            System.out.println(getName() + " пока не знает команд.");
        } else {
            System.out.println("Команды для " + getName() + ": " + String.join(", ", commands));
        }
    }

    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }
}

