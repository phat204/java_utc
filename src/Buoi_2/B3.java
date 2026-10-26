import java.util.Scanner;

class Diem {
    private double x;
    private double y;

    public Diem(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double tinhKhoangCach(Diem dKhac) {
        return Math.sqrt(Math.pow(this.x - dKhac.x, 2) + Math.pow(this.y - dKhac.y, 2));
    }
}

// BÀI TẬP 1: LỚP ĐƯỜNG THẲNG
class DuongThang {
    private Diem A;
    private Diem B;

    public DuongThang(Diem a, Diem b) {
        this.A = a;
        this.B = b;
    }

    public Diem getA() {
        return A;
    }

    public Diem getB() {
        return B;
    }

    public double tinhDoDai() {
        return A.tinhKhoangCach(B);
    }
}

// BÀI TẬP 2: CÁC LỚP HÌNH HỌC
class TamGiac {
    private Diem A, B, C;

    public TamGiac(Diem a, Diem b, Diem c) {
        this.A = a;
        this.B = b;
        this.C = c;
    }

    public double tinhChuVi() {
        double ab = A.tinhKhoangCach(B);
        double bc = B.tinhKhoangCach(C);
        double ca = C.tinhKhoangCach(A);
        return ab + bc + ca;
    }

    public double tinhDienTich() {
        double ab = A.tinhKhoangCach(B);
        double bc = B.tinhKhoangCach(C);
        double ca = C.tinhKhoangCach(A);
        double p = tinhChuVi() / 2;

        return Math.sqrt(p * (p - ab) * (p - bc) * (p - ca));
    }
}

class ChuNhat {
    private double chieuDai;
    private double chieuRong;

    public ChuNhat(double chieuDai, double chieuRong) {
        this.chieuDai = chieuDai;
        this.chieuRong = chieuRong;
    }

    public double tinhChuVi() {
        return (chieuDai + chieuRong) * 2;
    }

    public double tinhDienTich() {
        return chieuDai * chieuRong;
    }
}

class HinhTron {
    private Diem O;
    private double banKinh;

    public HinhTron(Diem o, double banKinh) {
        this.O = o;
        this.banKinh = banKinh;
    }

    public double tinhChuVi() {
        return 2 * Math.PI * banKinh;
    }

    public double tinhDienTich() {
        return Math.PI * banKinh * banKinh;
    }
}

// CHƯƠNG TRÌNH CHÍNH (MAIN)
public class B3 {

    // Hàm phụ trợ giúp việc nhập Điểm từ bàn phím gọn gàng hơn
    public static Diem nhapDiem(Scanner scanner, String tenDiem) {
        System.out.println("  Nhập tọa độ cho " + tenDiem + ":");
        System.out.print("    - x = ");
        double x = scanner.nextDouble();
        System.out.print("    - y = ");
        double y = scanner.nextDouble();
        return new Diem(x, y);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // BÀI TẬP 1
        System.out.println("=== BÀI TẬP 1: NHẬP ĐƯỜNG THẲNG ===");
        Diem d1 = nhapDiem(scanner, "Điểm A");
        Diem d2 = nhapDiem(scanner, "Điểm B");
        DuongThang dt = new DuongThang(d1, d2);
        System.out.printf("=> Độ dài đường thẳng AB là: %.2f\n\n", dt.tinhDoDai());

        // BÀI TẬP 2
        System.out.println("=== BÀI TẬP 2: NHẬP CÁC HÌNH HỌC ===");

        // 1. TAM GIÁC
        System.out.println("--- Nhập Tam Giác 1 ---");
        TamGiac tg1 = new TamGiac(nhapDiem(scanner, "Đỉnh 1"), nhapDiem(scanner, "Đỉnh 2"), nhapDiem(scanner, "Đỉnh 3"));

        System.out.println("--- Nhập Tam Giác 2 ---");
        TamGiac tg2 = new TamGiac(nhapDiem(scanner, "Đỉnh 1"), nhapDiem(scanner, "Đỉnh 2"), nhapDiem(scanner, "Đỉnh 3"));

        // 2. CHỮ NHẬT
        System.out.println("--- Nhập Chữ Nhật 1 ---");
        System.out.print("  Nhập chiều dài: ");
        double dai1 = scanner.nextDouble();
        System.out.print("  Nhập chiều rộng: ");
        double rong1 = scanner.nextDouble();
        ChuNhat cn1 = new ChuNhat(dai1, rong1);

        System.out.println("--- Nhập Chữ Nhật 2 ---");
        System.out.print("  Nhập chiều dài: ");
        double dai2 = scanner.nextDouble();
        System.out.print("  Nhập chiều rộng: ");
        double rong2 = scanner.nextDouble();
        ChuNhat cn2 = new ChuNhat(dai2, rong2);

        // 3. HÌNH TRÒN
        System.out.println("--- Nhập Hình Tròn 1 ---");
        Diem tam1 = nhapDiem(scanner, "Tâm O1");
        System.out.print("  Nhập bán kính: ");
        double bk1 = scanner.nextDouble();
        HinhTron ht1 = new HinhTron(tam1, bk1);

        System.out.println("--- Nhập Hình Tròn 2 ---");
        Diem tam2 = nhapDiem(scanner, "Tâm O2");
        System.out.print("  Nhập bán kính: ");
        double bk2 = scanner.nextDouble();
        HinhTron ht2 = new HinhTron(tam2, bk2);

        // KẾT QUẢ ĐẦU RA
        System.out.println("\n================ KẾT QUẢ ===============");
        System.out.printf("[Tam Giác 1] Chu vi: %.2f | Diện tích: %.2f\n", tg1.tinhChuVi(), tg1.tinhDienTich());
        System.out.printf("[Tam Giác 2] Chu vi: %.2f | Diện tích: %.2f\n", tg2.tinhChuVi(), tg2.tinhDienTich());
        System.out.println("----------------------------------------");
        System.out.printf("[Chữ Nhật 1] Chu vi: %.2f | Diện tích: %.2f\n", cn1.tinhChuVi(), cn1.tinhDienTich());
        System.out.printf("[Chữ Nhật 2] Chu vi: %.2f | Diện tích: %.2f\n", cn2.tinhChuVi(), cn2.tinhDienTich());
        System.out.println("----------------------------------------");
        System.out.printf("[Hình Tròn 1] Chu vi: %.2f | Diện tích: %.2f\n", ht1.tinhChuVi(), ht1.tinhDienTich());
        System.out.printf("[Hình Tròn 2] Chu vi: %.2f | Diện tích: %.2f\n", ht2.tinhChuVi(), ht2.tinhDienTich());

        scanner.close();
    }
}