package rcp.com.backup;

import android.os.Handler;

/***
 * 
 * 百度个人云存贮常量类
 * 
 * @author toshiba
 * 
 */
public class PCSDemoInfo {
	
	public static  int index=0;

	/** PCS上存储的绝对路径 **/
	public static final String mbRootpath = "/apps/rcp_brithday/";
	// "3.257bfa2eb88bb4ea4ffd7dd9f29d790c.2592000.1367411117.1193815910-644507";
	/*** 保存用户授权凭证 */
	public static String access_token = null;
	/** API Key **/
	public static final String app_key = "8BMxXWp2oVroxjYmxAWxHIMK";

	// The path to the file storage on cloud
	public static String sourceFile = null;



	public static String fileContent = null;

	public static int statu = 2;

}
