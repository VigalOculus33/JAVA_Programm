public abstract class Animal {
    private static int animalCount = 0;
    private String name;
    private String birthDate;

    protected Animal(String name, String birthDate) {
        this.name = name;
        this.birthDate = birthDate;
        animalCount++;
    }

    public abstract void showCommands();

    public static int getAnimalCount() {
        return animalCount;
    }

    public String getName() {
        return name;
    }

    public String getBirthDate() {
        return birthDate;
    }
}

