package com.example.lab_3;

import com.example.lab_3.dataaccess.JsonToyRepository;
import com.example.lab_3.exceptions.InvalidPriceRangeException;
import com.example.lab_3.exceptions.NotEnoughBudgetException;
import com.example.lab_3.playroom.Playroom;
import com.example.lab_3.toys.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

// Singleton
public final class Lab {
    public static void solve() {
        var scanner = new Scanner(System.in);

        System.out.println("Введіть бюджет для кімнати іграшок:");
        var budget = scanner.nextDouble();

        var playRoom = new Playroom(budget);
        var jsonRepository = new JsonToyRepository();

        var shouldExit = false;

        while (!shouldExit) {
            System.out.println("1. Додати іграшку");
            System.out.println("2. Показати іграшки");
            System.out.println("3. Відсортувати іграшки за ціною");
            System.out.println("4. Знайти іграшки в ціновому діапазоні");
            System.out.println("5. Зберегти список іграшок у файл");
            System.out.println("6. Завантажити список іграшок з файлу");
            System.out.println("7. Вийти");

            var choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Введіть ім'я іграшки: ");
                    var name = scanner.next();

                    System.out.println("Введіть ціну іграшки: ");
                    var price = scanner.nextDouble();

                    System.out.println("Введіть розмір іграшки: ");
                    var size = scanner.next();

                    System.out.println("Введіть вікову категорію для іграшки: ");
                    var ageGroup = scanner.next();

                    System.out.println("Оберіть іграшку, яку хочете створити:");
                    System.out.println("1. Машинка");
                    System.out.println("2. М'ячик");
                    System.out.println("3. Лялька");

                    var toyChoice = scanner.nextInt();

                    Toy toy;

                    switch (toyChoice) {
                        case 1:
                            System.out.println("Введіть тип машинки (грузова, гоночна і т.д):");

                            var type = scanner.next();
                            toy = new Car(price, size, name, ageGroup, type);

                            break;
                        case 2:
                            System.out.println("Введіть колір м'ячика:");

                            var color = scanner.next();
                            toy = new Ball(price, size, name, ageGroup, color);

                            break;
                        case 3:
                            System.out.println("Введіть колір волосся ляльки:");

                            var hairColor = scanner.next();

                            System.out.println("Чи вміє лялька розмовляти? (так/ні):");

                            var canTalk = scanner.next().trim();

                            if (canTalk.equalsIgnoreCase("так")) {
                                System.out.println("Введіть мову, якою буде розмовляти лялька:");
                                var language = scanner.next();

                                System.out.println("Введіть фразу, яку буде казати лялька");
                                var phrase = scanner.next();

                                toy = new TalkingDoll(price, size, name, ageGroup, hairColor, language, phrase);
                            } else {
                                toy = new Doll(price, size, name, ageGroup, hairColor);
                            }

                            break;
                        default:
                            System.out.println("Невідома іграшка, спробуйте ще...");

                            continue;
                    }

                    try {
                        playRoom.addToy(toy);
                        System.out.println("Іграшку успішно додано");
                        System.out.println(STR."Грошей залишилося: \{playRoom.getBudget()}");
                    } catch (NotEnoughBudgetException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 2:
                    playRoom.displayToys();

                    break;
                case 3:
                    playRoom.sortToysByPrice();

                    System.out.println("Іграшки успішно відсортовано");

                    break;
                case 4:
                    System.out.println("Введіть початок цінового діапазону:");
                    var start = scanner.nextDouble();

                    System.out.println("Введіть кінець цінового діапазону:");
                    var end = scanner.nextDouble();

                    try {
                        var foundToys = playRoom.findToysInPriceRange(start, end);

                        System.out.println("Знайдені іграшки:");
                        for (var foundToy : foundToys) {
                            System.out.println(foundToy);
                        }
                    } catch (InvalidPriceRangeException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case 5:
                    jsonRepository.Write("playroom.json", playRoom.getToys().stream().toList());

                    break;
                case 6:
                    try {
                        var toys = jsonRepository.Read("playroom.json");

                        playRoom.setToys(toys);
                    } catch (FileNotFoundException e) {
                        System.out.println(e.getMessage());
                        System.out.println("Спробуйте додати іграшки у файл");
                    }

                    break;
                case 7:
                    shouldExit = true;

                    break;
                default:
                    System.out.println("Невідома опція, спробуйте ще...");
            }
        }

        scanner.close();
    }
}
