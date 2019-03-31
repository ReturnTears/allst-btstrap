package allst.boot.strap.apppush;

import java.text.SimpleDateFormat;
import java.util.Date;

import allst.boot.strap.apppush.android.AndroidBroadcast;
import allst.boot.strap.apppush.android.AndroidGroupcast;
import allst.boot.strap.apppush.android.AndroidUnicast;
import allst.boot.strap.apppush.ios.IOSBroadcast;
import allst.boot.strap.apppush.ios.IOSGroupcast;
import allst.boot.strap.apppush.ios.IOSUnicast;
import org.json.JSONArray;
import org.json.JSONObject;

public class Demo {
	private String appkey = null;
	private String appMasterSecret = null;
	private PushClient client = new PushClient();

	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public Demo(String key, String secret) {
		try {
			appkey = key;
			appMasterSecret = secret;
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public void addTag(String deviceToken, String tag) throws Exception {
		client.operateTag(appkey, appMasterSecret, deviceToken, "add", tag);
	}

	public void delTag(String deviceToken, String tag) throws Exception {
		client.operateTag(appkey, appMasterSecret, deviceToken, "delete", tag);
	}

	public void clearTag(String deviceToken, String tag) throws Exception {
		client.operateTag(appkey, appMasterSecret, deviceToken, "clear", tag);
	}

	public int sendAndroidBroadcast(String title, String text, String startTime) throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast(appkey, appMasterSecret);
		broadcast.setTicker(title);// ticker 提示文字设置和标题一致
		broadcast.setTitle(title);
		broadcast.setText(text);
		broadcast.setStart_time(startTime);
		broadcast.goAppAfterOpen();
		// broadcast.setStartTime(sdf.format(date));
		broadcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		// broadcast.setTestMode();
		broadcast.setProductionMode(true);
		// Set customized fields
		// broadcast.setExtraField("test", "helloworld");
		return client.send(broadcast);
	}

	public int sendAndroidUnicast(String deviceToken, String ticker, String title, String text, Date date) throws Exception {
		AndroidUnicast unicast = new AndroidUnicast(appkey, appMasterSecret);
		// TODO Set your device token
		unicast.setDeviceToken(deviceToken);
		unicast.setTicker(ticker);// 通知栏提示文字
		unicast.setTitle(title);
		unicast.setText(text);
		unicast.goAppAfterOpen();
		System.out.println(sdf.format(date));
		// unicast.setStartTime(sdf.format(date));
		unicast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		// unicast.setTestMode();
		unicast.setProductionMode(true);
		// Set customized fields自定义字段，需要时可以设置
		// unicast.setExtraField("test", "helloworld");
		return client.send(unicast);
	}

	public int sendAndroidGroupcast(JSONObject tagJson, String ticker, String title, String contents, String startTime) throws Exception {
		AndroidGroupcast groupcast = new AndroidGroupcast(appkey, appMasterSecret);
		/*
		 * TODO Construct the filter condition: {"where": {"and": [{"tag":"test"}, {"tag":"Test"} ] }
		 */
		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		tagArray.put(tagJson);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());

		groupcast.setStartTime(startTime);
		groupcast.setFilter(filterJson);
		groupcast.setTicker(ticker);
		groupcast.setTitle(title);
		groupcast.setText(contents);
		groupcast.goAppAfterOpen();
		groupcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
		// TODO Set 'production_mode' to 'false' if it's a test device.
		// For how to register a test device, please see the developer doc.
		groupcast.setProductionMode();
		return client.send(groupcast);
	}

	/*
	 * public void sendAndroidCustomizedcast() throws Exception {
	 * AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,appMasterSecret);
	 * // TODO Set your alias here, and use comma to split them if there are multiple alias.
	 * // And if you have many alias, you can also upload a file containing
	 * // these alias, then // use file_id to send customized notification.
	 * customizedcast.setAlias("alias", "alias_type");
	 * customizedcast.setTicker("Android customizedcast ticker");
	 * customizedcast.setTitle("中文的title");
	 * customizedcast.setText("Android customizedcast text");
	 * customizedcast.goAppAfterOpen();
	 * customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	 * // TODO Set 'production_mode' to 'false' if it's a test device.
	 * // For how to register a test device, please see the developer doc.
	 * customizedcast.setProductionMode();
	 * client.send(customizedcast);
	 * }
	 */

	/*
	 * public void sendAndroidCustomizedcastFile() throws Exception {
	 * AndroidCustomizedcast customizedcast = new AndroidCustomizedcast(appkey,
	 * appMasterSecret);
	 * // TODO Set your alias here, and use comma to split
	 * them if there are multiple alias.
	 * // And if you have many alias, you can also upload a file containing
	 * // these alias, then use file_id to send customized notification.
	 * String fileId = client.uploadContents(appkey, appMasterSecret, "aa" + "\n" + "bb" + "\n" "alias");
	 * customizedcast.setFileId(fileId, "alias_type");
	 * customizedcast.setTicker("Android customizedcast ticker");
	 * customizedcast.setTitle("中文的title");
	 * customizedcast.setText("Android customizedcast text");
	 * customizedcast.goAppAfterOpen();
	 * customizedcast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	 * // TODO Set 'production_mode' to 'false' if it's a test device.
	 * // For how to register a test device, please see the developer doc.
	 * customizedcast.setProductionMode();
	 * client.send(customizedcast);
	 * }
	 */

	/*
	 * public void sendAndroidFilecast() throws Exception {
	 * AndroidFilecast filecast = new AndroidFilecast(appkey, appMasterSecret);
	 * // TODO upload your device tokens, and use '\n' to split them if there are multiple tokens
	 * String fileId = client.uploadContents(appkey, appMasterSecret, "aa" + "\n" + "bb");
	 * filecast.setFileId(fileId);
	 * filecast.setTicker("Android filecast ticker");
	 * filecast.setTitle("中文的title");
	 * filecast.setText("Android filecast text");
	 * filecast.goAppAfterOpen();
	 * filecast.setDisplayType(AndroidNotification.DisplayType.NOTIFICATION);
	 * client.send(filecast);
	  * }
	 */

	public int sendIOSBroadcast(String alert, String startTime) throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast(appkey, appMasterSecret);
		broadcast.setAlert(alert);
		broadcast.setBadge(1);
		broadcast.setSound("default");
		broadcast.setStart_time(startTime);
		// broadcast.setStartTime(sdf.format(date));
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		// broadcast.setTestMode();
		broadcast.setProductionMode(true);
		// Set customized fields
		// broadcast.setCustomizedField("test", "helloworld");
		return (client.send(broadcast));
	}

	public int sendIOSUnicast(String deviceToken, String alert, Date date) throws Exception {
		IOSUnicast unicast = new IOSUnicast(appkey, appMasterSecret);
		// TODO Set your device token
		unicast.setDeviceToken(deviceToken);
		unicast.setAlert(alert);
		unicast.setBadge(1);
		unicast.setSound("default");
		// unicast.setStartTime(sdf.format(date));
		// TODO set 'production_mode' to 'true' if your app is under production
		// mode
		// 如果是测试模式unicast.setTestMode();
		// unicast.setTestMode();
		unicast.setProductionMode(true);
		// Set customized fields

		// 自定义字段。有需要可以设置
		// unicast.setCustomizedField("test", "helloworld");
		return client.send(unicast);
	}

	public int sendIOSGroupcast(JSONObject tagJson, String alert, String startTime) throws Exception {
		IOSGroupcast groupcast = new IOSGroupcast(appkey, appMasterSecret);

		// TODO Construct the filter condition: "where": { "and": [
		// {"tag":"iostest"} ] } //

		JSONObject filterJson = new JSONObject();
		JSONObject whereJson = new JSONObject();
		JSONArray tagArray = new JSONArray();
		tagArray.put(tagJson);
		whereJson.put("and", tagArray);
		filterJson.put("where", whereJson);
		System.out.println(filterJson.toString());

		// Set filter condition into rootJson
		groupcast.setStart_time(startTime);
		groupcast.setFilter(filterJson);
		groupcast.setAlert(alert);
		groupcast.setBadge(1);
		groupcast.setSound("default");
		// TODO set 'production_mode' to 'true' if
		// your app is under production mode
		groupcast.setProductionMode();
		return (client.send(groupcast));
	}

	/*
	 * public int sendIOSCustomizedcast() throws Exception { IOSCustomizedcast
	 * customizedcast = new IOSCustomizedcast(appkey, appMasterSecret); // TODO
	 * Set your alias and alias_type here, and use comma to split them // if
	 * there are multiple alias. // And if you have many alias, you can also
	 * upload a file containing // these alias, then // use file_id to send
	 * customized notification. customizedcast.setAlias("alias", "alias_type");
	 * customizedcast.setAlert("IOS 个性化测试"); customizedcast.setBadge(0);
	 * customizedcast.setSound("default"); // TODO set 'production_mode' to
	 * 'true' if your app is under production // mode
	 * customizedcast.setTestMode(); return (client.send(customizedcast)); }
	 */

	/*
	 * public int sendIOSFilecast() throws Exception { IOSFilecast filecast =
	 * new IOSFilecast(appkey, appMasterSecret); // TODO upload your device
	 * tokens, and use '\n' to split them if there // are multiple tokens String
	 * fileId = client.uploadContents(appkey, appMasterSecret, "aa" + "\n" +
	 * "bb"); filecast.setFileId(fileId); filecast.setAlert("IOS 文件播测试");
	 * filecast.setBadge(1); filecast.setSound("default"); // TODO set
	 * 'production_mode' to 'true' if your app is under production // mode
	 * filecast.setTestMode(); return (client.send(filecast)); }
	 */

	public static void main(String[] args) {
		String appkey = "";
		String appMasterSecret = "";
		Demo demo = new Demo(appkey, appMasterSecret);

	}
}
