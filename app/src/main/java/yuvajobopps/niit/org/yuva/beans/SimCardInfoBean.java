package yuvajobopps.niit.org.yuva.beans;

/**
 * Created by Deepak Upadhyay on 19-Jul-16.
 */
public class SimCardInfoBean {
    private String simID;
    private String simCompanyName;
    private String phoneEMINo;

    public SimCardInfoBean() {

    }

    public SimCardInfoBean(String simID, String simCompanyName, String phoneEMINo) {
        this.simID = simID;
        this.simCompanyName = simCompanyName;
        this.phoneEMINo = phoneEMINo;
    }

    public String getSimID() {
        return simID;
    }

    public void setSimID(String simID) {
        this.simID = simID;
    }

    public String getSimCompanyName() {
        return simCompanyName;
    }

    public void setSimCompanyName(String simCompanyName) {
        this.simCompanyName = simCompanyName;
    }

    public String getPhoneEMINo() {
        return phoneEMINo;
    }

    public void setPhoneEMINo(String phoneEMINo) {
        this.phoneEMINo = phoneEMINo;
    }
}
