package Buoi_4;

/**
 * Lớp biểu diễn một điểm trong không gian 3 chiều (3D), kế thừa từ lớp Diem2D.
 */
public class Diem3D extends Diem2D {
    // Thuộc tính bổ sung trong không gian 3 chiều
    protected double z;

    // Constructors
    public Diem3D() {
        super(0.0, 0.0);
        this.z = 0.0;
    }

    public Diem3D(double x, double y, double z) {
        super(x, y); // Gọi constructor của lớp cha Diem2D để khởi tạo x, y
        this.z = z;
    }

    public Diem3D(Diem3D d) {
        super(d != null ? d.x : 0, d != null ? d.y : 0);
        if (d != null) {
            this.z = d.z;
        }
    }

    // Getter và Setter cho z
    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    /**
     * 1. Tính khoảng cách giữa 2 điểm 3D:
     *    d = sqrt((x2 - x1)^2 + (y2 - y1)^2 + (z2 - z1)^2)
     */
    public double tinhKhoangCach(Diem3D d) {
        if (d == null) {
            throw new IllegalArgumentException("Điểm không được để null");
        }
        return Math.sqrt(Math.pow(this.x - d.x, 2) + Math.pow(this.y - d.y, 2) + Math.pow(this.z - d.z, 2));
    }

    /**
     * Ghi đè phương thức tinhKhoangCach(Diem2D) để hỗ trợ đa hình khi danh sách chứa kiểu Diem2D
     */
    @Override
    public double tinhKhoangCach(Diem2D d) {
        if (d instanceof Diem3D) {
            return tinhKhoangCach((Diem3D) d);
        }
        // Trường hợp tính với điểm 2D (coi z của điểm 2D là 0)
        return Math.sqrt(Math.pow(this.x - d.x, 2) + Math.pow(this.y - d.y, 2) + Math.pow(this.z, 2));
    }

    /**
     * 2. Định nghĩa lại (Override) hàm xác định điểm đối xứng qua gốc tọa độ O(0, 0, 0):
     *    Điểm đối xứng của (x, y, z) là (-x, -y, -z)
     */
    @Override
    public Diem3D layDoiXung() {
        return new Diem3D(-this.x, -this.y, -this.z);
    }

    @Override
    public Diem3D timDoiXung() {
        return layDoiXung();
    }

    /**
     * 3. Tịnh tiến điểm đi một vị trí với độ lệch Δx, Δy và Δz:
     *    Sử dụng super.tinhTien(dx, dy) để tái sử dụng code của lớp cha
     */
    public void tinhTien(double dx, double dy, double dz) {
        super.tinhTien(dx, dy); // Tịnh tiến x và y theo lớp cha
        this.z += dz;           // Tịnh tiến thêm trục z
    }

    public void dichChuyen(double dx, double dy, double dz) {
        tinhTien(dx, dy, dz);
    }

    @Override
    public String toString() {
        return "(" + formatNum(x) + ", " + formatNum(y) + ", " + formatNum(z) + ")";
    }
}

