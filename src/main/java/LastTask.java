import java.util.*;
import java.util.stream.Collectors;

class PhoneBook {
    private final Map<String, Set<String>> phoneMap = new HashMap<>();
    private static final Scanner scanner = new Scanner(System.in);

    // Добавить номер телефона для имени
    public void add(String name, String phoneNumber) {
        if (isValidPhoneNumber(phoneNumber)) {
            phoneMap.computeIfAbsent(name, k -> new HashSet<>()).add(phoneNumber);
        } else {
            System.out.println("Неверный формат номера: " + phoneNumber);
        }
    }

    // Простая проверка на длину номера телефона
    private boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.startsWith("+7") && phoneNumber.length() == 12;
    }

    // Поиск контактов по последним 4 цифрам номера
    public Map<String, Set<String>> searchByLastFourDigits(String digits) {
        return phoneMap.entrySet().stream()
                .filter(entry -> entry.getValue().stream().anyMatch(phone -> phone.endsWith(digits)))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    // Вывести контакты, отсортированные по убыванию числа телефонов
    public void displayContacts() {
        phoneMap.entrySet().stream()
                .sorted(Map.Entry.<String, Set<String>>comparingByValue(Comparator.comparingInt(Set::size)).reversed())
                .forEach(entry -> System.out.println("Имя: " + entry.getKey() + ", Номера: " + entry.getValue()));
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        while (true) {
            System.out.println("1. Добавить контакт");
            System.out.println("2. Показать контакты");
            System.out.println("3. Поиск по последним 4 цифрам номера");
            System.out.println("4. Выход");
            System.out.print("Выберите действие: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Clear the buffer

            switch (choice) {
                case 1:
                    System.out.print("Введите имя: ");
                    String name = scanner.nextLine();
                    System.out.print("Введите номер телефона: ");
                    String phone = scanner.nextLine();
                    phoneBook.add(name, phone);
                    break;
                case 2:
                    phoneBook.displayContacts();
                    break;
                case 3:
                    System.out.print("Введите последние 4 цифры номера: ");
                    String digits = scanner.nextLine();
                    Map<String, Set<String>> contacts = phoneBook.searchByLastFourDigits(digits);
                    if (contacts.isEmpty()) {
                        System.out.println("Контактов не найдено.");
                    } else {
                        contacts.forEach((contactName, phones) -> System.out.println("Имя: " + contactName + ", Номера: " + phones));
                    }
                    break;
                case 4:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Неверный выбор, попробуйте снова.");
            }
        }
    }
}

