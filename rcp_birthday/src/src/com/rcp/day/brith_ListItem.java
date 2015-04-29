package src.com.rcp.day;

/***
 * 
 * 生日联系人属性
 * 
 * @author toshiba
 * 
 */
public class brith_ListItem {

	private long id;
	/** 表名 */
	String TABLE_brith_name;

	/** 生日人的姓名 */
	String brithPer_name;

	/** 生日人的年龄 */
	int brithPer_age;
	/** 生日人的性别 */
	String brithPer_sex;
	/** 生日人的头像 */
	String brithPer_photo;

	/** 公历年 */
	int gregorianYear;
	/** 公历月 */
	int gregorianMouth;
	/** 公历日 */
	int gregorianDate;
	/** 生日人手机号码 **/
	String brithPer_phone;
	/** 生日人的生肖 **/
	String brithPer_animals;
	/** 生日人的星座 **/
	String brithPer_constellation;

	String brithPer_beizhuInfo;

	public String getBrithPer_beizhuInfo() {
		return brithPer_beizhuInfo;
	}

	public void setBrithPer_beizhuInfo(String brithPer_beizhuInfo) {
		this.brithPer_beizhuInfo = brithPer_beizhuInfo;
	}

	public String getTABLE_brith_name() {
		return TABLE_brith_name;
	}

	public void setTABLE_brith_name(String tABLE_brith_name) {
		TABLE_brith_name = tABLE_brith_name;
	}

	public String getBrithPer_name() {
		return brithPer_name;
	}

	public void setBrithPer_name(String brithPer_name) {
		this.brithPer_name = brithPer_name;
	}

	public int getBrithPer_age() {
		return brithPer_age;
	}

	public void setBrithPer_age(int brithPer_age) {
		this.brithPer_age = brithPer_age;
	}

	public String getBrithPer_sex() {
		return brithPer_sex;
	}

	public void setBrithPer_sex(String brithPer_sex) {
		this.brithPer_sex = brithPer_sex;
	}



	public String getBrithPer_photo() {
		return brithPer_photo;
	}

	public void setBrithPer_photo(String brithPer_photo) {
		this.brithPer_photo = brithPer_photo;
	}

	public int getGregorianYear() {
		return gregorianYear;
	}

	public void setGregorianYear(int gregorianYear) {
		this.gregorianYear = gregorianYear;
	}

	public int getGregorianMouth() {
		return gregorianMouth;
	}

	public void setGregorianMouth(int gregorianMouth) {
		this.gregorianMouth = gregorianMouth;
	}

	public int getGregorianDate() {
		return gregorianDate;
	}

	public void setGregorianDate(int gregorianDate) {
		this.gregorianDate = gregorianDate;
	}

	public String getBrithPer_phone() {
		return brithPer_phone;
	}

	public void setBrithPer_phone(String brithPer_phone) {
		this.brithPer_phone = brithPer_phone;
	}

	public String getBrithPer_animals() {
		return brithPer_animals;
	}

	public void setBrithPer_animals(String brithPer_animals) {
		this.brithPer_animals = brithPer_animals;
	}

	public String getBrithPer_constellation() {
		return brithPer_constellation;
	}

	public void setBrithPer_constellation(String brithPer_constellation) {
		this.brithPer_constellation = brithPer_constellation;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

}
