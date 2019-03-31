package allst.boot.strap.umeng;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author June 2019/03/17 下午 05:38
 * @version 1.0
 */
public class AndroidNotification extends UmengNotification {

    protected static final HashSet<String> PAYLOAD_KEYS = new HashSet<String>(Arrays.asList(new String[] { "display_type" }));

    protected static final HashSet<String> BODY_KEYS = new HashSet<String>(Arrays.asList(
            new String[] { "ticker", "title", "text", "builder_id", "icon", "largeIcon", "img", "play_vibrate", "play_lights", "play_sound", "sound", "after_open", "url", "activity", "custom" }));

    protected static final HashSet<String> POLICY_KEYS = new HashSet<String>(Arrays.asList(new String[] { "start_time", "expire_time", "max_send_num", "out_biz_no" }));

    public enum DisplayType {
        /**
         *
         */
        NOTIFICATION {
            @Override
            public String getValue() {
                return "notification";
            }
        },
        /**
         * 通知:消息送达到用户设备后，由友盟SDK接管处理并在通知栏上显示通知内容。
         */
        MESSAGE {
            @Override
            public String getValue() {
                return "message";
            }
        };

        /**
         * 消息:消息送达到用户设备后，消息内容透传给应用自身进行解析处理。
         * @return
         */
        public abstract String getValue();
    }

    public enum AfterOpenAction {
        go_app, // 打开应用
        go_url, // 跳转到URL
        go_activity, // 打开特定的activity
        go_custom// 用户自定义内容。
    }

    @Override
    public boolean setPredefinedKeyValue(String key, Object value) throws Exception {
        if (ROOT_KEYS.contains(key)) {
            // This key should be in the root level
            rootJson.put(key, value);
        } else if (PAYLOAD_KEYS.contains(key)) {
            // This key should be in the payload level
            JSONObject payloadJson = null;
            if (rootJson.containsKey("payload")) {
                payloadJson = rootJson.getJSONObject("payload");
            } else {
                payloadJson = new JSONObject();
                rootJson.put("payload", payloadJson);
            }
            payloadJson.put(key, value);
        } else if (BODY_KEYS.contains(key)) {
            // This key should be in the body level
            JSONObject bodyJson = null;
            JSONObject payloadJson = null;
            // 'body' is under 'payload', so build a payload if it doesn't exist
            if (rootJson.containsKey("payload")) {
                payloadJson = rootJson.getJSONObject("payload");
            } else {
                payloadJson = new JSONObject();
                rootJson.put("payload", payloadJson);
            }
            // Get body JSONObject, generate one if not existed
            if (payloadJson.containsKey("body")) {
                bodyJson = payloadJson.getJSONObject("body");
            } else {
                bodyJson = new JSONObject();
                payloadJson.put("body", bodyJson);
            }
            bodyJson.put(key, value);
        } else if (POLICY_KEYS.contains(key)) {
            // This key should be in the body level
            JSONObject policyJson = null;
            if (rootJson.containsKey("policy")) {
                policyJson = rootJson.getJSONObject("policy");
            } else {
                policyJson = new JSONObject();
                rootJson.put("policy", policyJson);
            }
            policyJson.put(key, value);
        } else {
            if (key == "payload" || key == "body" || key == "policy" || key == "extra") {
                throw new Exception("You don't need to set value for " + key + " , just set values for the sub keys in it.");
            } else {
                throw new Exception("Unknown key: " + key);
            }
        }
        return true;
    }
}
