package Database;

public class KegiatanHandler {
    private int id;
    private String judul, deskripsi, tanggal_mulai, tanggal_akhir;

    public int getId() {return id;}

    public void setId (int id) {this.id = id;}

    public String getJudul(){return judul;}

    public void setJudul(String judul) {this.judul = judul;}

    public String getDeskripsi(){return deskripsi;}

    public void setDeskripsi(String deskripsi) {this.deskripsi = deskripsi;}

    public String getTanggal_Mulai(){return tanggal_mulai;}

    public void setTanggal_Mulai(String tanggal_mulai) {this.tanggal_mulai = tanggal_mulai;}

    public String getTanggal_Akhir(){return tanggal_akhir;}

    public void setTanggal_Akhir(String tanggal_akhir) {this.tanggal_akhir = tanggal_akhir;}
}
