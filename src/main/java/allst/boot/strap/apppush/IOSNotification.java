package allst.boot.strap.apppush;

import java.util.Arrays;
import java.util.HashSet;

import org.json.JSONObject;

public abstract class IOSNotification extends UmengNotification {

	// Keys can be set in the aps level
	protected static final HashSet<String> APS_KEYS = new HashSet<String>(Arrays.asList(new String[] { "alert", "badge", "sound", "content-available" }));

	// Keys can be set in the policy level
	protected static final HashSet<String> POLICY_KEYS = new HashSet<String>(Arrays.asList(new String[] { "start_time", "expire_time", "max_send_num", "out_biz_no" }));

	@Override
	public boolean setPredefinedKeyValue(String key, Object value) throws Exception {
		if (ROOT_KEYS.contains(key)) {
			// This key should be in the root level
			rootJson.put(key, value);
		} else if (APS_KEYS.contains(key)) {
			// This key should be in the aps level
			JSONObject apsJson = null;
			JSONObject payloadJson = null;
			if (rootJson.has("payload")) {
				payloadJson = rootJson.getJSONObject("payload");
			} else {
				payloadJson = new JSONObject();
				rootJson.put("payload", payloadJson);
			}
			if (payloadJson.has("aps")) {
				apsJson = payloadJson.getJSONObject("aps");
			} else {
				apsJson = new JSONObject();
				payloadJson.put("aps", apsJson);
			}
			apsJson.put(key, value);
		} else if (POLICY_KEYS.contains(key)) {
			// This key should be in the body level
			JSONObject policyJson = null;
			if (rootJson.has("policy")) {
				policyJson = rootJson.getJSONObject("policy");
			} else {
				policyJson = new JSONObject();
				rootJson.put("policy", policyJson);
			}
			policyJson.put(key, value);
		} else {
			if (key == "payload" || key == "aps" || key == "policy") {
				throw new Exception("You don't need to set value for " + key + " , just set values for the sub keys in it.");
			} else {
				throw new Exception("Unknownd key: " + key);
			}
		}

		return true;
	}

	// Set customized key/value for IOS notification
	public boolean setCustomizedField(String key, String value) throws Exception {
		// rootJson.put(key, value);
		JSONObject payloadJson = null;
		if (rootJson.has("payload")) {
			payloadJson = rootJson.getJSONObject("payload");
		} else {
			payloadJson = new JSONObject();
			rootJson.put("payload", payloadJson);
		}
		payloadJson.put(key, value);
		return true;
	}

	/// 发送时间
	public void setStart_time(String start_time) throws Exception {
		setPredefinedKeyValue("start_time", start_time);
	}

	/// 过期时间
	public void setExpire_time(String expire_time) throws Exception {
		setPredefinedKeyValue("expire_time", expire_time);
	}

	/// 发送限速，每秒发送的最大条数。
	public void setMax_send_num(String max_send_num) throws Exception {
		setPredefinedKeyValue("max_send_num", max_send_num);
	}

	/// 开发者对消息的唯一标识，服务器会根据这个标识避免重复发送。
	public void setOut_biz_no(String out_biz_no) throws Exception {
		setPredefinedKeyValue("out_biz_no", out_biz_no);
	}

	public void setAlert(String token) throws Exception {
		setPredefinedKeyValue("alert", token);
	}

	public void setBadge(Integer badge) throws Exception {
		setPredefinedKeyValue("badge", badge);
	}

	public void setSound(String sound) throws Exception {
		setPredefinedKeyValue("sound", sound);
	}

	public void setContentAvailable(Integer contentAvailable) throws Exception {
		setPredefinedKeyValue("content-available", contentAvailable);
	}
}
