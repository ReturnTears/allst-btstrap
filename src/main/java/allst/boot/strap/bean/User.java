package allst.boot.strap.bean;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author June 2019/04/02 下午 03:58
 * @version 1.0
 */
public class User implements Serializable {
    private String userName;
    private MultipartFile head;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public MultipartFile getHead() {
        return head;
    }

    public void setHead(MultipartFile head) {
        this.head = head;
    }
}
