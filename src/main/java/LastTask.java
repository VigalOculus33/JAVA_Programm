import java.util.*;

class PhoneBook {
    private final Map<String, List<String>> phoneMap = new HashMap<>();

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

    // Поиск по имени
    public List<String> searchByName(String name) {
        return phoneMap.getOrDefault(name, Collections.emptyList());
    }

    public static void main(String[] args) {
        PhoneBook phoneBook = new PhoneBook();

        phoneBook.add("Иван", "+71234567890");
        phoneBook.add("Мария", "+71234567891");

        System.out.println("Номера Ивана: " + phoneBook.searchByName("Иван"));
    }
}

