package cn.com.bean;


public class UserEcAccount {
    private Integer id;

    private String usertel;

    private String subaccountsid;

    private String voipaccount;

    private String datecreated;

    private String voippwd;

    private String subtoken;
    
    /** 下午4:40:05
	 * @author zhangyh2
	 */
	public UserEcAccount() {
		// TODO Auto-generated constructor stub
	}
	
    public UserEcAccount(String usertel, String subaccountsid,
			String voipaccount, String datecreated, String voippwd,
			String subtoken) {
		this.usertel = usertel;
		this.subaccountsid = subaccountsid;
		this.voipaccount = voipaccount;
		this.datecreated = datecreated;
		this.voippwd = voippwd;
		this.subtoken = subtoken;
	}


	public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsertel() {
        return usertel;
    }

    public void setUsertel(String usertel) {
        this.usertel = usertel == null ? null : usertel.trim();
    }

    public String getSubaccountsid() {
        return subaccountsid;
    }

    public void setSubaccountsid(String subaccountsid) {
        this.subaccountsid = subaccountsid == null ? null : subaccountsid.trim();
    }

    public String getVoipaccount() {
        return voipaccount;
    }

    public void setVoipaccount(String voipaccount) {
        this.voipaccount = voipaccount == null ? null : voipaccount.trim();
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getVoippwd() {
        return voippwd;
    }

    public void setVoippwd(String voippwd) {
        this.voippwd = voippwd == null ? null : voippwd.trim();
    }

    public String getSubtoken() {
        return subtoken;
    }

    public void setSubtoken(String subtoken) {
        this.subtoken = subtoken == null ? null : subtoken.trim();
    }
}