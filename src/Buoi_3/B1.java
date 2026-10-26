package Buoi_3;

import java.util.Scanner;

class PhanSoToanHoc {
    private long tu;
    private long mau;

    public PhanSoToanHoc() {
        this(0, 1);
    }

    public PhanSoToanHoc(long tu, long mau) {
        if (mau == 0) {
            throw new IllegalArgumentException("Mẫu số không được phép bằng 0.");
        }
        this.tu = tu;
        this.mau = mau;
        toiGian(); // Tự động tối giản ngay khi tạo phân số mới
    }

    // Thuật toán Euclid tìm ước chung lớn nhất (UCLN)
    private long timUCLN(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a == 0 ? 1 : a;
    }

    private void toiGian() {
        if (this.mau < 0) {
            this.tu = -this.tu;
            this.mau = -this.mau;
        }
        long uoc = timUCLN(this.tu, this.mau);
        this.tu /= uoc;
        this.mau /= uoc;
    }

    // Các hàm tính toán trả về đối tượng mới, dùng trực tiếp toán tử cơ bản
    public PhanSoToanHoc congPhanSo(PhanSoToanHoc pKhac) {
        long tMoi = this.tu * pKhac.mau + pKhac.tu * this.mau;
        long mMoi = this.mau * pKhac.mau;
        return new PhanSoToanHoc(tMoi, mMoi);
    }

    public PhanSoToanHoc truPhanSo(PhanSoToanHoc pKhac) {
        long tMoi = this.tu * pKhac.mau - pKhac.tu * this.mau;
        long mMoi = this.mau * pKhac.mau;
        return new PhanSoToanHoc(tMoi, mMoi);
    }

    public PhanSoToanHoc nhanPhanSo(PhanSoToanHoc pKhac) {
        return new PhanSoToanHoc(this.tu * pKhac.tu, this.mau * pKhac.mau);
    }

    public PhanSoToanHoc chiaPhanSo(PhanSoToanHoc pKhac) {
        if (pKhac.tu == 0) throw new ArithmeticException("Không thể chia cho 0.");
        return new PhanSoToanHoc(this.tu * pKhac.mau, this.mau * pKhac.tu);
    }

    // Hàm so sánh trả về kết quả số nguyên
    public int soSanhKichThuoc(PhanSoToanHoc pKhac) {
        long giaTriTrai = this.tu * pKhac.mau;
        long giaTriPhai = pKhac.tu * this.mau;
        return Long.compare(giaTriTrai, giaTriPhai);
    }

    // Phân số đã tối giản nên nếu mẫu = 1 thì chắc chắn là số nguyên
    public boolean khongPhaiSoNguyen() {
        return this.mau != 1;
    }

    @Override
    public String toString() {
        if (tu == 0) return "0";
        return (mau == 1) ? String.valueOf(tu) : tu + "/" + mau;
    }
}

public class B1 {
    public static void main(String[] args) {
        Scanner nhapLieu = new Scanner(System.in);
        System.out.print("Nhập số lượng phân số cần xử lý: ");
        int soLuong = nhapLieu.nextInt();

        if (soLuong <= 0) {
            System.out.println("Số lượng phải là một số dương!");
            nhapLieu.close();
            return;
        }

        PhanSoToanHoc[] mangPhanSo = new PhanSoToanHoc[soLuong];
        PhanSoToanHoc tongGiaTri = new PhanSoToanHoc();
        PhanSoToanHoc psLonNhat = null;
        int demLe = 0;

        // Vòng lặp duy nhất để vừa nhập vừa xử lý dữ liệu
        for (int i = 0; i < soLuong; i++) {
            System.out.print("Nhập [tử số] và [mẫu số] của phân số thứ " + (i + 1) + ": ");
            long t = nhapLieu.nextLong();
            long m = nhapLieu.nextLong();
            mangPhanSo[i] = new PhanSoToanHoc(t, m);

            PhanSoToanHoc psHienTai = mangPhanSo[i];

            // 1. Tính tổng cộng dồn
            tongGiaTri = tongGiaTri.congPhanSo(psHienTai);

            // 2. Cập nhật phân số lớn nhất
            if (psLonNhat == null || psHienTai.soSanhKichThuoc(psLonNhat) > 0) {
                psLonNhat = psHienTai;
            }

            // 3. Kiểm tra số nguyên
            if (psHienTai.khongPhaiSoNguyen()) {
                demLe++;
            }
        }

        System.out.println("\n--- KẾT QUẢ TỔNG HỢP ---");
        System.out.println("Phân số có giá trị lớn nhất : " + psLonNhat);
        System.out.println("Tổng của tất cả các phân số : " + tongGiaTri);
        System.out.println("Số lượng PS KHÔNG phải nguyên : " + demLe);

        nhapLieu.close();
    }
}