import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Создаем экземпляр продукта
        Product product = new Product();

        // Ввод размеров
        product.inputSideLengths();

        product.displayInfo();

        product.totalEdgeLength();

    }

}