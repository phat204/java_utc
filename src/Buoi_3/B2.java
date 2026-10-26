package Buoi_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Diem2D {
    protected double x, y;

    public Diem2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double tinhKhoangCach(Diem2D d) {
        return Math.sqrt(Math.pow(this.x - d.x, 2) + Math.pow(this.y - d.y, 2));
    }

    public Diem2D layDoiXung() {
        return new Diem2D(-this.x, -this.y);
    }

    public void dichChuyen(double dx, double dy) {
        this.x += dx;
        this.y += dy;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + "]";
    }
}

class Diem3D extends Diem2D {
    protected double z;

    public Diem3D(double x, double y, double z) {
        super(x, y);
        this.z = z;
    }

    public double tinhKhoangCach(Diem3D d) {
        return Math.sqrt(Math.pow(this.x - d.x, 2) + Math.pow(this.y - d.y, 2) + Math.pow(this.z - d.z, 2));
    }

    @Override
    public Diem3D layDoiXung() {
        return new Diem3D(-this.x, -this.y, -this.z);
    }

    public void dichChuyen(double dx, double dy, double dz) {
        super.dichChuyen(dx, dy);
        this.z += dz;
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z + "]";
    }
}

public class B2 {
    public static void main(String[] args) {
        List<Diem2D> ds = new ArrayList<>(Arrays.asList(
                new Diem2D(0, 0), new Diem3D(1, 2, 2), new Diem2D(3, 4),
                new Diem3D(4, 6, 5), new Diem2D(-1, 2), new Diem3D(-2, 1, 3)
        ));

        double tong2D = 0, tong3D = 0;
        Diem2D truoc2D = null;
        Diem3D truoc3D = null;

        for (Diem2D d : ds) {
            if (d instanceof Diem3D) {
                if (truoc3D != null) tong3D += truoc3D.tinhKhoangCach((Diem3D) d);
                truoc3D = (Diem3D) d;
            } else {
                if (truoc2D != null) tong2D += truoc2D.tinhKhoangCach(d);
                truoc2D = d;
            }
        }

        System.out.printf("Tổng khoảng cách 2D: %.2f\n", tong2D);
        System.out.printf("Tổng khoảng cách 3D: %.2f\n\n", tong3D);

        int sizeCu = ds.size();
        for (int i = 0; i < sizeCu; i++) {
            ds.add(ds.get(i).layDoiXung());
        }

        System.out.println("Danh sách điểm lúc sau:");
        for (int i = 0; i < ds.size(); i++) {
            String loai = (ds.get(i) instanceof Diem3D) ? "3D" : "2D";
            System.out.println((i + 1) + ". " + loai + " -> " + ds.get(i));
        }
    }
}