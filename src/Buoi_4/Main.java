package Buoi_4;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("======================================================================");
        System.out.println("              BÀI TẬP BUỔI 4: CLASS - INHERITANCE / MODIFIERS         ");
        System.out.println("======================================================================");

        // -------------------------------------------------------------------------
        // 1. Tạo danh sách điểm bao gồm cả 2D và 3D (Minh họa tính Đa hình
        // Polymorphism)
        // -------------------------------------------------------------------------
        List<Diem2D> dsDiem = new ArrayList<>();
        dsDiem.add(new Diem2D(0, 0));
        dsDiem.add(new Diem3D(1, 2, 2));
        dsDiem.add(new Diem2D(3, 4));
        dsDiem.add(new Diem3D(4, 6, 5));
        dsDiem.add(new Diem2D(-1, 2));
        dsDiem.add(new Diem3D(-2, 1, 3));

        System.out.println("\n1. DANH SÁCH ĐIỂM BAN ĐẦU:");
        inDanhSach(dsDiem);

        // -------------------------------------------------------------------------
        // 2. Tính tổng khoảng cách các điểm 2D và tổng khoảng cách các điểm 3D
        // -------------------------------------------------------------------------
        List<Diem2D> ds2D = new ArrayList<>();
        List<Diem3D> ds3D = new ArrayList<>();

        for (Diem2D d : dsDiem) {
            if (d instanceof Diem3D) {
                ds3D.add((Diem3D) d);
            } else {
                ds2D.add(d);
            }
        }

        System.out.println("\n2. TÍNH TỔNG KHOẢNG CÁCH:");
        System.out.printf("   - Số lượng điểm 2D thuần túy: %d điểm\n", ds2D.size());
        System.out.printf("   - Số lượng điểm 3D:           %d điểm\n", ds3D.size());

        // Cách A: Khoảng cách giữa các điểm liên tiếp trong danh sách (d1 -> d2 ->
        // d3...)
        double tongKc2D_LienTiep = 0.0;
        for (int i = 0; i < ds2D.size() - 1; i++) {
            tongKc2D_LienTiep += ds2D.get(i).tinhKhoangCach(ds2D.get(i + 1));
        }

        double tongKc3D_LienTiep = 0.0;
        for (int i = 0; i < ds3D.size() - 1; i++) {
            tongKc3D_LienTiep += ds3D.get(i).tinhKhoangCach(ds3D.get(i + 1));
        }

        System.out.println("\n   [Cách 1] Tính khoảng cách giữa các điểm liên tiếp:");
        System.out.printf(java.util.Locale.US, "   + Tổng khoảng cách giữa các điểm 2D liên tiếp: %.4f\n",
                tongKc2D_LienTiep);
        System.out.printf(java.util.Locale.US, "   + Tổng khoảng cách giữa các điểm 3D liên tiếp: %.4f\n",
                tongKc3D_LienTiep);

        // Cách B: Tổng khoảng cách giữa mọi cặp điểm (tất cả các cặp i < j)
        double tongKc2D_TatCaCap = 0.0;
        for (int i = 0; i < ds2D.size(); i++) {
            for (int j = i + 1; j < ds2D.size(); j++) {
                tongKc2D_TatCaCap += ds2D.get(i).tinhKhoangCach(ds2D.get(j));
            }
        }

        double tongKc3D_TatCaCap = 0.0;
        for (int i = 0; i < ds3D.size(); i++) {
            for (int j = i + 1; j < ds3D.size(); j++) {
                tongKc3D_TatCaCap += ds3D.get(i).tinhKhoangCach(ds3D.get(j));
            }
        }

        System.out.println("\n   [Cách 2] Tổng khoảng cách giữa mọi cặp điểm (Tổ hợp tất cả các cặp):");
        System.out.printf(java.util.Locale.US, "   + Tổng khoảng cách tất cả các cặp điểm 2D:     %.4f\n",
                tongKc2D_TatCaCap);
        System.out.printf(java.util.Locale.US, "   + Tổng khoảng cách tất cả các cặp điểm 3D:     %.4f\n",
                tongKc3D_TatCaCap);

        // -------------------------------------------------------------------------
        // 3. Đưa các điểm đối xứng vào danh sách và hiển thị danh sách các điểm
        // -------------------------------------------------------------------------
        int soLuongBanDau = dsDiem.size();
        for (int i = 0; i < soLuongBanDau; i++) {
            // Cơ chế đa hình: phương thức layDoiXung() tương ứng của Diem2D hoặc Diem3D sẽ
            // được gọi tự động
            Diem2D diemDoiXung = dsDiem.get(i).layDoiXung();
            dsDiem.add(diemDoiXung);
        }

        System.out.println("\n3. DANH SÁCH ĐIỂM SAU KHI THÊM CÁC ĐIỂM ĐỐI XỨNG QUA GỐC TỌA ĐỘ:");
        for (int i = 0; i < dsDiem.size(); i++) {
            Diem2D d = dsDiem.get(i);
            String loai = (d instanceof Diem3D) ? "3D" : "2D";
            String ghiChu;
            if (i < soLuongBanDau) {
                ghiChu = "[Điểm gốc ban đầu]";
            } else {
                int gocIndex = i - soLuongBanDau + 1;
                ghiChu = "[Điểm đối xứng của điểm #" + gocIndex + "]";
            }
            System.out.printf("   %2d. [%s] %-18s %s\n", (i + 1), loai, d, ghiChu);
        }

        // -------------------------------------------------------------------------
        // 4. Minh họa bổ sung: Phương thức tịnh tiến (độ lệch Δx, Δy và Δz)
        // -------------------------------------------------------------------------
        System.out.println("\n4. MINH HỌA PHƯƠNG THỨC TỊNH TIẾN ĐIỂM:");
        Diem2D d2D = new Diem2D(2, 3);
        System.out.println("   - Điểm 2D ban đầu: " + d2D);
        d2D.tinhTien(1.5, -0.5);
        System.out.println("     -> Sau khi tịnh tiến (Δx = 1.5, Δy = -0.5): " + d2D);

        Diem3D d3D = new Diem3D(1, 2, 3);
        System.out.println("   - Điểm 3D ban đầu: " + d3D);
        d3D.tinhTien(2, 1, -1);
        System.out.println("     -> Sau khi tịnh tiến (Δx = 2, Δy = 1, Δz = -1): " + d3D);

        System.out.println("\n======================================================================");
        System.out.println("                          HOÀN THÀNH CHƯƠNG TRÌNH                     ");
        System.out.println("======================================================================");
    }

    /**
     * Hàm tiện ích hiển thị danh sách các điểm
     */
    private static void inDanhSach(List<Diem2D> ds) {
        for (int i = 0; i < ds.size(); i++) {
            Diem2D d = ds.get(i);
            String loai = (d instanceof Diem3D) ? "3D" : "2D";
            System.out.printf("   %2d. [%s] %s\n", (i + 1), loai, d);
        }
    }
}
