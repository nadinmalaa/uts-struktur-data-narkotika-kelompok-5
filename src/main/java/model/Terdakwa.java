package model;

public class Terdakwa {
    private String caseId;
    private String kodeTerdakwa;
    private int tuntutanBulan;
    private int vonisBulan;
    private String catatan;

    public Terdakwa() {
    }

    public Terdakwa(String caseId, String kodeTerdakwa, int tuntutanBulan, int vonisBulan, String catatan) {
        this.caseId = caseId;
        this.kodeTerdakwa = kodeTerdakwa;
        this.tuntutanBulan = tuntutanBulan;
        this.vonisBulan = vonisBulan;
        this.catatan = catatan;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getKodeTerdakwa() {
        return kodeTerdakwa;
    }

    public void setKodeTerdakwa(String kodeTerdakwa) {
        this.kodeTerdakwa = kodeTerdakwa;
    }

    public int getTuntutanBulan() {
        return tuntutanBulan;
    }

    public void setTuntutanBulan(int tuntutanBulan) {
        this.tuntutanBulan = tuntutanBulan;
    }

    public int getVonisBulan() {
        return vonisBulan;
    }

    public void setVonisBulan(int vonisBulan) {
        this.vonisBulan = vonisBulan;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}