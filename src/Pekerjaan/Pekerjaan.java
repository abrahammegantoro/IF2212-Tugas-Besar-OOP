package src.Pekerjaan;

public class Pekerjaan {
    private String namaPekerjaan;
    private int gaji;
    private int timedWorked;
    
    public Pekerjaan(String namaPekerjaan){
        this.namaPekerjaan = namaPekerjaan;
        if (namaPekerjaan.equals("Badut Sulap")){
            gaji = 15;
        } else if (namaPekerjaan.equals("Koki")){
            gaji = 30;
        } else if (namaPekerjaan.equals("Polisi")){
            gaji = 35;
        } else if (namaPekerjaan.equals("Programmer")){
            gaji = 45;
        } else if (namaPekerjaan.equals("Dokter")){
            gaji = 50;
        } else {
            System.out.println("Pekerjaan yang dipilih tidak valid, pekerjaan yang dapat dipilih adalah sebagai berikut");
            System.out.println("Badut sulap __ Gaji = 15");
            System.out.println("Koki        __ Gaji = 35");
            System.out.println("Polisi      __ Gaji = 35");
            System.out.println("Programmer  __ Gaji = 45");
            System.out.println("Dokter      __ Gaji = 50");
            
        }
    }

    public Pekerjaan(int randomnumber){
        if (randomnumber == 1){
            
        }
    }

    public String getPekerjaan(){
        return namaPekerjaan;
    }

    public int getGaji(){
        return gaji;
    }

    public int getTimedWorked(){
        return 0;
    }

    public void addTimedWorked(){

    }
}
