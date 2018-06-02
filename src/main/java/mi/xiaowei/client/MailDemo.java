package mi.xiaowei.client;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

/**
 * @program: SendCloudMailSDK
 * @description: 邮件测试类
 * @author: Mr.xweiba
 * @create: 2018-06-01 22:47
 **/

public class MailDemo {
    public static void send_common() throws IOException {

        final String url = "http://api.sendcloud.net/apiv2/mail/send";

        final String apiUser = "";
        final String apiKey = "";
        final String rcpt_to = "";

        String subject = "测试邮件";
        String html = "<html><H1>测试邮件 MailDemo</H1></html>";
        String fromName = "MailDemoTest";

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient httpClient = HttpClients.createDefault();

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("apiUser", apiUser));
        params.add(new BasicNameValuePair("apiKey", apiKey));
        params.add(new BasicNameValuePair("to", rcpt_to));
        params.add(new BasicNameValuePair("from", "sendcloud@sendcloud.org"));
        params.add(new BasicNameValuePair("fromName", fromName));
        params.add(new BasicNameValuePair("subject", subject));
        params.add(new BasicNameValuePair("html", html));

        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));

        HttpResponse response = httpClient.execute(httpPost);


        System.out.println(response.getStatusLine());
        // System.out.println(EntityUtils.toString(response.getEntity()));
        System.out.println(response.toString());

        System.out.println(response.getEntity().getContent());

            // 获取请求返回的内容值
            JSONObject jsonObject = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

            // 判断请求是否发送成功
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 判断邮件是否发送成功
                if ((Integer)jsonObject.get("statusCode") == 200){
                    System.out.println("邮件发送成功~");
                    System.out.println("返回信息: " + jsonObject.toJSONString());
                } else {
                    System.out.println("邮件发送成功~");
                    System.out.println("返回信息: " + jsonObject.toJSONString());
                }
            } else {
                System.out.println("请求发送失败 ~");
            }
        httpPost.releaseConnection();
    }

    public static void main(String[] args) {
        try {
            MailDemo.send_common();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
