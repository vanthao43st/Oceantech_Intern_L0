package constant;

public enum LEVEL {
    KEM("Kem"),
    YEU("Yeu"),
    TRUNG_BINH("Trung binh"),
    KHA("kha"),
    GIOI("Gioi"),
    XUAT_SAC("Xuat sac");

    private final String vietnamese;

    LEVEL(String vietnamese){
        this.vietnamese = vietnamese;
    }
    public String getVietnamese() {
        return vietnamese;
    }
}
