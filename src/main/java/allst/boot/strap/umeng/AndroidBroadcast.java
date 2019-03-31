package allst.boot.strap.umeng;

import java.util.Arrays;
import java.util.HashSet;

/**
 * @author June 2019/03/17 下午 05:34
 * @version 1.0
 */
public class AndroidBroadcast extends AndroidNotification {
    protected static final HashSet<String> PAYLOAD_KEYS = new HashSet<String>(Arrays.asList(new String[] { "display_type" }));
}
