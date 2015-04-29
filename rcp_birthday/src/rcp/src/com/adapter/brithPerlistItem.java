package rcp.src.com.adapter;

/***
 * 
 * 生日联系人单例
 * 
 * @author toshiba
 * 
 */
public class brithPerlistItem {
	/** 头像 */
	String photo;
	/* 名字* */
	String name;
	/** 农历 **/
	int gongrong;
	/** 生日 **/
	String brithday;
	/** 离他生日还有多少天 **/
	String brithdayInfo;
	/** 具体到的天数 */
	String brithdayInfo_day;
	
	int ItemID;
	
	String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getItemID() {
		return ItemID;
	}

	public void setItemID(int itemID) {
		ItemID = itemID;
	}

	int brithdayInfo_day1;

	public int getBrithdayInfo_day1() {
		return brithdayInfo_day1;
	}

	public void setBrithdayInfo_day1(String brithdayInfo_day2) {
		this.brithdayInfo_day1 = Integer.parseInt(brithdayInfo_day2);
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGongrong() {
		return gongrong;
	}

	public void setGongrong(int gongrong) {
		this.gongrong = gongrong;
	}

	public String getBrithday() {
		return brithday;
	}

	public void setBrithday(String brithday) {
		this.brithday = brithday;
	}

	public String getBrithdayInfo() {
		return brithdayInfo;
	}

	public void setBrithdayInfo(String brithdayInfo) {
		this.brithdayInfo = brithdayInfo;
	}

	public String getBrithdayInfo_day() {
		return brithdayInfo_day;
	}

	public void setBrithdayInfo_day(String brithdayInfo_day) {
		this.brithdayInfo_day = brithdayInfo_day;
	}

}
