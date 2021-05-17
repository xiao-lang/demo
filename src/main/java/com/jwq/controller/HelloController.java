package com.jwq.controller;

import com.jwq.model.TestUser;
import com.jwq.service.TestUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//@RestController
@Controller
@RequestMapping("test")
@Api("HelloController的相关api")
public class HelloController {
    @Autowired
    private TestUserService testUserService;
    @RequestMapping("hello")
    @ApiOperation("跳转页面的接口")
    public String HelloTest1(Model model) {
        TestUser testUser = new TestUser();
        testUser.setTestAge((short) 10);
        testUser.setTestName("LiMing");
        testUser.setTestSex((short) 0);
        testUser.setTestBirthday(new Date());
        TestUser user = testUserService.getTestUser(testUser);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = simpleDateFormat.format(user.getTestBirthday());
        user.setTestDate(s);

        model.addAttribute("user", user);
        model.addAttribute("testBirthday", s);
        return "index";
    }

    /**
     *
     * @param model
     * @return
     */
    @RequestMapping("helloTest2")
    @ResponseBody
    @ApiOperation("获取用户信息的接口")
    public List<TestUser> HelloTest2(Model model) {
        List<TestUser> testUserList = testUserService.selectTestUserList();
        for (TestUser tesrUser: testUserList) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String s = simpleDateFormat.format(tesrUser.getTestBirthday());
            tesrUser.setTestDate(s);
        }
        return testUserList;
    }

    /**
     *
     * @param testID
     * @return
     */
    @RequestMapping("helloTest3")
    @ResponseBody
    @ApiOperation("获取用户父级信息的接口")
    public String HelloTest3(Long testID){
        String name =null;
        String querycategoryName = testUserService.querycategoryName(testID, name);
        return  querycategoryName;
    }

    /**
     *
     * @param request
     * @param response
     * @throws UnsupportedEncodingException
     * @throws FileNotFoundException
     */
    @RequestMapping("downLoad")
    @ResponseBody
    @ApiOperation("下载的接口")
    public void downLoad(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, FileNotFoundException {
        String path="C:\\Users\\独客\\Desktop\\社保公积金缴费明细表.xlsx";
//        String path="C:\\Users\\独客\\Desktop\\sdsada.xlsx";
        try {
            // path是指欲下载的文件的路径。
            File file = new File(path);
            // 取得文件名。
            String filename = file.getName();
            // 以流的形式下载文件。
            InputStream fis = new BufferedInputStream(new FileInputStream(path));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            String realName=filename.substring(filename.indexOf("_")+2,filename.length());
            //判断浏览器是否为火狐
            if ("FF".equals(getBrowser(request))) {
                // 火狐浏览器 设置编码new String(realName.getBytes("GB2312"), "ISO-8859-1");
                realName = new String(realName.getBytes("GB2312"), "ISO-8859-1");
            }else{
                realName = URLEncoder.encode(realName, "UTF-8");//encode编码UTF-8 解决大多数中文乱码
                realName = realName.replace("+", "%20");//encode后替换空格  解决空格问题
            }
            response.addHeader("Content-Disposition", "attachment;filename="+realName+"");
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    /**
     * @Title: getBrowser
     * @Description: 判断客户端浏览器
     * @return String
     * @author
     * @date
     */

    private static String getBrowser(HttpServletRequest request) {
        String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
        if (UserAgent != null) {
            if (UserAgent.indexOf("msie") != -1)
                return "IE";
            if (UserAgent.indexOf("firefox") != -1)
                return "FF";
            if (UserAgent.indexOf("safari") != -1)
                return "SF";
        }
        return null;
    }
}
