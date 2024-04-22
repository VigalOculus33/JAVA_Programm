import java.util.List;
import java.util.Scanner;

public class Main {
    private static PetRegister register = new PetRegister();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("Выберите действие:");
            System.out.println("1. Добавить животное");
            System.out.println("2. Показать команды животного");
            System.out.println("3. Обучить животное новой команде");
            System.out.println("4. Показать список животных по дате рождения");
            System.out.println("5. Показать общее количество животных");
            System.out.println("6. Выйти");
            System.out.print("Введите номер действия: ");

            int action = scanner.nextInt();
            scanner.nextLine(); // Перехват перевода строки после nextInt

            switch (action) {
                case 1:
                    addAnimal();
                    break;
                case 2:
                    showAnimalCommands();
                    break;
                case 3:
                    teachAnimalCommand();
                    break;
                case 4:
                    register.showAnimalsByBirthDate();
                    break;
                case 5:
                    System.out.println("Общее количество животных: " + Animal.getAnimalCount());
                    break;
                case 6:
                    System.out.println("Программа завершена.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Некорректный ввод, попробуйте снова.");
            }
        }
    }

    private static void addAnimal() {
        System.out.println("Какое животное вы хотите добавить? (1 - Кошка, 2 - Собака, 3 - Хомяк)");
        int choice = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Введите имя животного: ");
        String name = scanner.nextLine();

        System.out.print("Введите дату рождения животного (например, 01/01/2020): ");
        String birthDate = scanner.nextLine();

        Animal animal = null;

        switch (choice) {
            case 1:
                animal = new Cat(name, birthDate);
                break;
            case 2:
                animal = new Dog(name, birthDate);
                break;
            case 3:
                animal = new Hamster(name, birthDate);
                break;
            default:
                System.out.println("Неверный выбор. Попробуйте снова.");
                return;
        }

        register.addAnimal(animal);
        System.out.println(name + " успешно добавлен(а) в реестр!");
    }

    private static void showAnimalCommands() {
        System.out.print("Введите имя животного, чтобы увидеть его команды: ");
        String name = scanner.nextLine();

        List<Animal> animals = register.getAnimals();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                if (animal instanceof DomesticAnimal) {
                    ((DomesticAnimal) animal).showCommands();
                    return;
                }
            }
        }

        System.out.println("Животное с именем " + name + " не найдено.");
    }

    private static void teachAnimalCommand() {
        System.out.print("Введите имя животного, которому хотите обучить команду: ");
        String name = scanner.nextLine();

        List<Animal> animals = register.getAnimals();
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(name)) {
                if (animal instanceof DomesticAnimal) {
                    System.out.print("Введите команду для обучения: ");
                    String command = scanner.nextLine();
                    ((DomesticAnimal) animal).learnCommand(command);
                    System.out.println(animal.getName() + " теперь знает команду: " + command);
                    return;
                } else {
                    System.out.println(name + " не является домашним животным и не может учить командам.");
                    return;
                }
            }
        }

        System.out.println("Животное с именем " + name + " не найдено.");
    }
}

