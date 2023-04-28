package src.Pekerjaan;

public class Pekerjaan {
    private String namaPekerjaan;
    private int gaji;
    private int timesWorked; // satuan sekon

    public Pekerjaan(String namaPekerjaan) {
        this.namaPekerjaan = namaPekerjaan;

        if (namaPekerjaan.equals("Badut Sulap")) this.gaji = 15;
        else if (namaPekerjaan.equals("Koki")) this.gaji = 30;
        else if (namaPekerjaan.equals("Polisi")) this.gaji =  35;
        else if (namaPekerjaan.equals("Programmer")) this.gaji = 45;
        else if (namaPekerjaan.equals("Dokter")) this.gaji = 50;

        this.timesWorked = 0;
    }

    public Pekerjaan(int hasilNomorAcak) {
        if (hasilNomorAcak == 1) {this.namaPekerjaan = "Badut Sulap"; this.gaji = 15;}
        else if (hasilNomorAcak == 2) {this.namaPekerjaan = "Koki"; this.gaji = 30;}
        else if (hasilNomorAcak == 3) {this.namaPekerjaan = "Polisi"; this.gaji = 35;}
        else if (hasilNomorAcak == 4) {this.namaPekerjaan = "Programmer"; this.gaji = 45;}
        else if (hasilNomorAcak == 5) {this.namaPekerjaan = "Dokter"; this.gaji = 50;}

        this.timesWorked = 0;
    }

    public String getPekerjaan() {
        return namaPekerjaan;
    }

    public int getGaji() {
        return gaji;
    }

    public int getTimesWorked() {
        return timesWorked;
    }

    public void addTimesWorked(int timesWorked) {
        this.timesWorked += timesWorked;
    }
}
