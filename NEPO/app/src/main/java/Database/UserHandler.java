package Database;

public class UserHandler {
    private int id;
    private String nama_lengkap, username, password;

    public int getId() {return id;}

    public void setId (int id) {this.id = id;}

    public String getNama_Lengkap(){return nama_lengkap;}

    public void setNama_Lengkap(String nama_lengkap) {this.nama_lengkap = nama_lengkap;}

    public String getPassword(){return password;}

    public void setPassword(String password) {this.password = password;}

    public String getUsername(){return username;}

    public void setUsername(String username) {this.username = username;}

}
