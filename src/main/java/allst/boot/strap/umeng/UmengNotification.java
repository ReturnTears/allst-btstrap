package allst.boot.strap.umeng;

import com.alibaba.fastjson.JSONObject;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author June 2019/03/17 下午 05:39
 * @version 1.0
 */
public abstract class UmengNotification {
    protected final JSONObject rootJson = new JSONObject();
    protected String appMasterSecret;
    protected static final HashSet<String> ROOT_KEYS = new HashSet<String>(
            Arrays.asList(new String[] { "appkey", "timestamp", "type", "device_tokens", "alias", "alias_type", "file_id", "filter", "production_mode", "feedback", "description", "thirdparty_id" }));
    protected static final HashSet<String> POLICY_KEYS = new HashSet<String>(Arrays.asList(new String[] { "start_time", "expire_time", "max_send_num" }));
    public abstract boolean setPredefinedKeyValue(String key, Object value) throws Exception;

    public void setAppMasterSecret(String secret) {
        appMasterSecret = secret;
    }

    public String getPostBody() {
        return rootJson.toString();
    }

    protected final String getAppMasterSecret() {
        return appMasterSecret;
    }

    protected void setProductionMode(Boolean prod) throws Exception {
        setPredefinedKeyValue("production_mode", prod.toString());
    }

    /// 正式模式
    public void setProductionMode() throws Exception {
        setProductionMode(true);
    }

    /// 测试模式
    public void setTestMode() throws Exception {
        setProductionMode(false);
    }

    /// 发送消息描述，建议填写。
    public void setDescription(String description) throws Exception {
        setPredefinedKeyValue("description", description);
    }

    /// 定时发送时间，若不填写表示立即发送。格式: "YYYY-MM-DD hh:mm:ss"。
    public void setStartTime(String startTime) throws Exception {
        setPredefinedKeyValue("start_time", startTime);
    }

    /// 消息过期时间,格式: "YYYY-MM-DD hh:mm:ss"。
    public void setExpireTime(String expireTime) throws Exception {
        setPredefinedKeyValue("expire_time", expireTime);
    }

    /// 发送限速，每秒发送的最大条数。
    public void setMaxSendNum(Integer num) throws Exception {
        setPredefinedKeyValue("max_send_num", num);
    }
}
