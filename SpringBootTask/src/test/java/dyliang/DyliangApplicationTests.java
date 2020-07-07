package dyliang;

import com.sun.corba.se.spi.monitoring.MonitoredAttributeInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class DyliangApplicationTests {

    @Autowired
    JavaMailSenderImpl javaMailSender;

    @Test
    public void testSendSimpleMessage() {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setSubject("test");
        message.setText("Hello World...");

        message.setTo("18dyliang@stu.edu.cn");
        message.setFrom("1002457567@qq.com");
        javaMailSender.send(message);
    }

    @Test
    public void testSendComplexMessage() throws MessagingException {
        // 创建复杂的消息邮件
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        helper.setSubject("test more...");
        helper.setText("<b style='color:red'>HELLO WORLD...</b>",true);
        helper.setTo("18dyliang@stu.edu.cn");
        helper.setFrom("1002457567@qq.com");

        //上传文件
        helper.addAttachment("444873.jpg", new File("C:\\Users\\dyliang\\Pictures\\444873.jpg"));

        javaMailSender.send(mimeMessage);

    }
}
