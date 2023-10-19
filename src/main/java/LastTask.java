import java.util.*;

class PhoneBook {
        private final Map<String, List<String>> phoneMap = new HashMap<>();
        private static final Scanner scanner = new Scanner(System.in);

        // Добавить номер телефона для имени
        public void add(String name, String phoneNumber) {
            if (isValidPhoneNumber(phoneNumber)) {
                phoneMap.computeIfAbsent(name, k -> new ArrayList<>()).add(phoneNumber);
            } else {
                System.out.println("Неверный формат номера: " + phoneNumber);
            }
        }

        // Простая проверка на длину номера телефона
        private boolean isValidPhoneNumber(String phoneNumber) {
            return phoneNumber.startsWith("+7") && phoneNumber.length() == 12;
        }

        // Поиск по последним 4 цифрам номера
        public Map<String, List<String>> searchByLastFourDigits(String digits) {
            Map<String, List<String>> result = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : phoneMap.entrySet()) {
                for (String phone : entry.getValue()) {
                    if (phone.endsWith(digits)) {
                        result.computeIfAbsent(entry.getKey(), k -> new ArrayList<>()).add(phone);
                    }
                }
            }
            return result;
        }

        public static void main(String[] args) {
            PhoneBook phoneBook = new PhoneBook();

            while (true) {
                System.out.println("1. Добавить контакт");
                System.out.println("2. Поиск по последним 4 цифрам номера");
                System.out.println("3. Выход");
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
                        System.out.print("Введите последние 4 цифры номера: ");
                        String digits = scanner.nextLine();
                        Map<String, List<String>> contacts = phoneBook.searchByLastFourDigits(digits);
                        if (contacts.isEmpty()) {
                            System.out.println("Контактов не найдено.");
                        } else {
                            for (Map.Entry<String, List<String>> entry : contacts.entrySet()) {
                                System.out.println("Имя: " + entry.getKey() + ", Номера: " + entry.getValue());
                            }
                        }
                        break;
                    case 3:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Неверный выбор, попробуйте снова.");
                }
            }
        }
    }
