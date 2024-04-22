import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PetRegister {
    private List<Animal> animals;

    public PetRegister() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void showAnimalsByBirthDate() {
        animals.stream()
                .sorted((a1, a2) -> a1.getBirthDate().compareTo(a2.getBirthDate()))
                .forEach(a -> System.out.println(a.getName() + " - " + a.getBirthDate()));
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
}

