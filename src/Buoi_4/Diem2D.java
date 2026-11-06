package Buoi_4;

/**
 * Lớp biểu diễn một điểm trong không gian 2 chiều (2D).
 */
public class Diem2D {
    // Thuộc tính: Sử dụng access modifier 'protected' để lớp con Diem3D có thể kế thừa và truy cập trực tiếp
    protected double x;
    protected double y;

    // Constructors
    public Diem2D() {
        this(0.0, 0.0);
    }

    public Diem2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Diem2D(Diem2D d) {
        if (d != null) {
            this.x = d.x;
            this.y = d.y;
        }
    }

    // Getters và Setters
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

    /**
     * 1. Tính khoảng cách giữa điểm hiện tại và một điểm 2D khác:
     *    d = sqrt((x2 - x1)^2 + (y2 - y1)^2)
     */
    public double tinhKhoangCach(Diem2D d) {
        if (d == null) {
            throw new IllegalArgumentException("Điểm không được để null");
        }
        return Math.sqrt(Math.pow(this.x - d.x, 2) + Math.pow(this.y - d.y, 2));
    }

    /**
     * 2. Xác định điểm đối xứng qua gốc tọa độ O(0, 0):
     *    Điểm đối xứng của (x, y) là (-x, -y)
     */
    public Diem2D layDoiXung() {
        return new Diem2D(-this.x, -this.y);
    }

    public Diem2D timDoiXung() {
        return layDoiXung();
    }

    /**
     * 3. Tịnh tiến điểm đi một vị trí với độ lệch Δx, Δy:
     *    x_moi = x + dx, y_moi = y + dy
     */
    public void tinhTien(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    public void dichChuyen(double dx, double dy) {
        tinhTien(dx, dy);
    }

    /**
     * Định dạng số thực gọn đẹp (loại bỏ số 0 thừa ở phần thập phân và dùng dấu chấm)
     */
    protected static String formatNum(double num) {
        if (num == (long) num) {
            return String.valueOf((long) num);
        }
        String s = String.format(java.util.Locale.US, "%.4f", num);
        while (s.contains(".") && (s.endsWith("0") || s.endsWith("."))) {
            s = s.substring(0, s.length() - 1);
        }
        return s;
    }

    @Override
    public String toString() {
        return "(" + formatNum(x) + ", " + formatNum(y) + ")";
    }

    // Cho phép chạy trực tiếp từ file Diem2D
    public static void main(String[] args) {
        Main.main(args);
    }
}
