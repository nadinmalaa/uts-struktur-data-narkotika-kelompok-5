package model;

public class Terdakwa {
    private String caseId;
    private String kodeTerdakwa;
    private int tuntutanBulan;
    private int vonisBulan;
    private String catatan;

    public String getCaseId() {
        return this.caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getKodeTerdakwa() {
        return this.kodeTerdakwa;
    }

    public void setKodeTerdakwa(String kodeTerdakwa) {
        this.kodeTerdakwa = kodeTerdakwa;
    }

    public int getTuntutanBulan() {
        return this.tuntutanBulan;
    }

    public void setTuntutanBulan(int tuntutanBulan) {
        this.tuntutanBulan = tuntutanBulan;
    }

    public int getVonisBulan() {
        return this.vonisBulan;
    }

    public void setVonisBulan(int vonisBulan) {
        this.vonisBulan = vonisBulan;
    }

    public String getCatatan() {
        return this.catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }
}
