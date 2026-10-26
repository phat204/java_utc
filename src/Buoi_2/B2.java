import java.util.Arrays;
import java.util.Scanner;

public class B2 {

    // Bài 1: Nhập vào n phần tử int của 1 mảng (n nhập từ bàn phím). Tính và in ra tổng
    public static void bai1(Scanner scanner) {
        System.out.println("=== BÀI 1: NHẬP MẢNG N PHẦN TỬ VÀ TÍNH TỔNG ===");
        System.out.print("Nhập số phần tử n: ");
        int n = scanner.nextInt();

        int[] arr = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            System.out.printf("Nhập phần tử thứ %d: ", i + 1);
            arr[i] = scanner.nextInt();
            sum += arr[i];
        }

        System.out.println("Mảng vừa nhập: " + Arrays.toString(arr));
        System.out.println("Tổng các phần tử trong mảng: " + sum);
        System.out.println();
    }

    // Bài 2: Viết hàm nhận vào 2 mảng và in ra mảng tổng (mỗi phần tử là tổng của 2 phần tử thuộc mảng đầu vào, có số phần tử bằng mảng nhiều phần tử hơn)
    public static int[] tinhMangTong(int[] a, int[] b) {
        int maxLength = Math.max(a.length, b.length);
        int[] result = new int[maxLength];

        for (int i = 0; i < maxLength; i++) {
            int valA = (i < a.length) ? a[i] : 0;
            int valB = (i < b.length) ? b[i] : 0;
            result[i] = valA + valB;
        }

        return result;
    }

    public static void bai2(Scanner scanner) {
        System.out.println("=== BÀI 2: TỔNG HAI MẢNG ===");
        System.out.print("Nhập số phần tử của mảng 1: ");
        int n1 = scanner.nextInt();
        int[] arr1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            System.out.printf("arr1[%d] = ", i);
            arr1[i] = scanner.nextInt();
        }

        System.out.print("Nhập số phần tử của mảng 2: ");
        int n2 = scanner.nextInt();
        int[] arr2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            System.out.printf("arr2[%d] = ", i);
            arr2[i] = scanner.nextInt();
        }

        int[] sumArr = tinhMangTong(arr1, arr2);
        System.out.println("Mảng 1: " + Arrays.toString(arr1));
        System.out.println("Mảng 2: " + Arrays.toString(arr2));
        System.out.println("Mảng tổng: " + Arrays.toString(sumArr));
        System.out.println();
    }

    // Bài 3: Nhập mảng các số nguyên bằng 1 chuỗi duy nhất cách nhau bởi dấu cách. In ra số phần tử và tổng các phần tử
    public static void bai3(Scanner scanner) {
        System.out.println("=== BÀI 3: NHẬP MẢNG TỪ CHUỖI CÁCH NHAU BỞI DẤU CÁCH ===");
        System.out.print("Nhập dãy số nguyên (cách nhau bởi khoảng trắng): ");
        scanner.nextLine();
        String line = scanner.nextLine().trim();

        if (line.isEmpty()) {
            System.out.println("Số lượng phần tử: 0");
            System.out.println("Tổng các phần tử: 0");
            System.out.println();
            return;
        }

        String[] tokens = line.split("\\s+");
        int count = tokens.length;
        int sum = 0;
        int[] arr = new int[count];

        for (int i = 0; i < count; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
            sum += arr[i];
        }

        System.out.println("Mảng các phần tử: " + Arrays.toString(arr));
        System.out.println("Số phần tử: " + count);
        System.out.println("Tổng các phần tử: " + sum);
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        bai1(scanner);
        bai2(scanner);
        bai3(scanner);

        scanner.close();
    }
}