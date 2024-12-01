import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Product {

    private Map<Double, Integer> sideLengths; // Длины сторон и их количество
    private final double waste; // отходы

    // Конструктор
    public Product() {
        this.sideLengths = new HashMap<>();
        this.waste = 40; // фиксированные отходы в мм
    }

    // Геттеры и сеттеры
    public Map<Double, Integer> getSideLengths() {
        return sideLengths;
    }

    public double getWaste() {
        return waste;
    }

    // Метод для ввода длин сторон детали
    public void inputSideLengths() {
        Scanner scanner = new Scanner(System.in);
        int numberOfDistinctSides = 0;

        while (true) {
            System.out.print("Введите сколько повторяющихся длин в одном комплекте: ");
            try {
                numberOfDistinctSides = scanner.nextInt();
                if (numberOfDistinctSides <= 0) {
                    throw new IllegalArgumentException("Количество должно быть положительным.");
                }
                break; // Выходим из цикла, если ввод корректен
            } catch (Exception e) {
                System.out.println("Ошибка: введите корректное положительное целое число.");
                scanner.next(); // Очищаем неверный ввод
            }
        }

        for (int i = 0; i < numberOfDistinctSides; i++) {
            double length = 0;
            int count = 0;

            while (true) {
                System.out.print("Введите длину стороны " + (i + 1) + " (в мм): ");
                try {
                    length = scanner.nextDouble();
                    if (length <= 0) {
                        throw new IllegalArgumentException("Длина должна быть положительной.");
                    }
                    break; // Выходим из цикла, если ввод корректен
                } catch (Exception e) {
                    System.out.println("Ошибка: введите корректное положительное число для длины.");
                    scanner.next(); // Очищаем неверный ввод
                }
            }

            while (true) {
                System.out.print("Введите количество сторон длиной " + length + " (в шт.): ");
                try {
                    count = scanner.nextInt();
                    if (count < 0) {
                        throw new IllegalArgumentException("Количество не может быть отрицательным.");
                    }
                    break; // Выходим из цикла, если ввод корректен
                } catch (Exception e) {
                    System.out.println("Ошибка: введите корректное целое число для количества.");
                    scanner.next(); // Очищаем неверный ввод
                }
            }
            sideLengths.put(length, sideLengths.getOrDefault(length, 0) + count);
        }
    }

    // Метод для расчета общей длины с переводом в метры
    public double calculateTotalLength() {
        double total = 0;
        for (Map.Entry<Double, Integer> entry : sideLengths.entrySet()) {
            double length = entry.getKey();
            int count = entry.getValue();
            total += (length * count) + getWaste() * count; // добавляем длину стороны и отходы
        }
        return total / 1000; // перевод в метры
    }

    // Метод для отображения информации о продукте
    public void displayInfo() {
        System.out.println("Длины сторон и их количество:");
        for (Map.Entry<Double, Integer> entry : sideLengths.entrySet()) {
            System.out.println("Длина: " + entry.getKey() + " мм, Количество: " +
                    entry.getValue() + " шт.");
        }
        System.out.println("Общая длина (с учетом отходов): " +
                calculateTotalLength() + " м.");
    }

    // Метод расчёта общего расхода на все изделия
    public void totalEdgeLength() {
        Scanner scanner = new Scanner(System.in);
        int numberProducts = 0;

        while (true) {
            System.out.print("Количество изделий: ");
            try {
                numberProducts = scanner.nextInt();
                if (numberProducts <= 0) {
                    throw new IllegalArgumentException("Количество должно быть положительным.");
                }
                break; // Выходим из цикла, если ввод корректен
            } catch (Exception e) {
                System.out.println("Ошибка: введите корректное положительное целое число.");
                scanner.next(); // Очищаем неверный ввод
            }
        }

        double result = numberProducts * calculateTotalLength();
        System.out.printf("Общий метраж: %.2f п.м.%n", result);
    }

}