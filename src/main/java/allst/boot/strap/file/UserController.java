package allst.boot.strap.file;

import allst.boot.strap.bean.User;
import org.apache.commons.io.FileUtils;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @author June 2019/04/02 下午 03:49
 * @version 1.0
 */
@Controller
public class UserController {

    @RequestMapping("/registerForm")
    public String registerForm() {
        return "file/register";
    }

    @RequestMapping("/upload")
    public String upload(HttpServletRequest request, @ModelAttribute User user, Model model) throws IllegalStateException, IOException {
        if (!user.getHead().isEmpty()) {
            // 获取
            String path=request.getServletContext().getRealPath("/upload");
            System.out.println("path - : > " + path);
            String fileName=user.getHead().getOriginalFilename();
            File filePath=new File(path,fileName);
            if(!filePath.getParentFile().exists()) {
                filePath.getParentFile().mkdirs();
            }
            user.getHead().transferTo(new File(path+File.separator+fileName));
            model.addAttribute("user", user);
            return "file/userInfo";
        } else {
            return "error";
        }
    }

    @RequestMapping(value="/download")
    public ResponseEntity<byte[]> download(HttpServletRequest request, @RequestParam("filename") String filename,
                                           @RequestHeader("User-Agent") String userAgent, Model model)throws Exception{
        // 下载文件路径
        String path = request.getServletContext().getRealPath("/upload/");
        // 构建File
        File file = new File(path+File.separator+ filename);
        // ok表示Http协议中的状态 200
        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        // 内容长度
        builder.contentLength(file.length());
        // application/octet-stream ： 二进制流数据（最常见的文件下载）。
        builder.contentType(MediaType.APPLICATION_OCTET_STREAM);
        // 使用URLDecoder.decode对文件名进行解码
        filename = URLEncoder.encode(filename, "UTF-8");
        // 设置实际的响应文件名，告诉浏览器文件要用于【下载】、【保存】attachment 以附件形式
        // 不同的浏览器，处理方式不同，要根据浏览器版本进行区别判断
        if (userAgent.indexOf("MSIE") > 0) {
            // 如果是IE，只需要用UTF-8字符集进行URL编码即可
            builder.header("Content-Disposition", "attachment; filename=" + filename);
        } else {
            // 而FireFox、Chrome等浏览器，则需要说明编码的字符集
            // 注意filename后面有个*号，在UTF-8后面有两个单引号！
            builder.header("Content-Disposition", "attachment; filename*=UTF-8''" + filename);
        }
        return builder.body(FileUtils.readFileToByteArray(file));
    }
}
