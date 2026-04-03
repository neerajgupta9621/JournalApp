package com.edigest.my.first.project.service;

import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String sentiment){

        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            String html = "<div style='background:#f0f2f5; padding:30px; font-family:Segoe UI, Arial;'>"

                    + "<div style='max-width:600px; margin:auto; background:#ffffff; border-radius:15px; "
                    + "box-shadow:0 8px 20px rgba(0,0,0,0.1); overflow:hidden;'>"

                    // Header
                    + "<div style='background:linear-gradient(135deg,#4CAF50,#2196F3); padding:20px; color:white;'>"
                    + "<h2 style='margin:0; text-align:center; font-weight:600;'>📊 Weekly Sentiment Report</h2>"
                    + "</div>"

                    // Body
                    + "<div style='padding:25px; text-align:center;'>"
                    + "<p style='font-size:18px; margin-bottom:10px;'>Hello 👋</p>"
                    + "<p style='font-size:15px; color:#555;'>Here is your sentiment summary for last 7 days:</p>"

                    // Sentiment Card
                    + "<div style='margin:20px auto; padding:20px; width:80%; "
                    + "border-radius:12px; background:#f9fafc; "
                    + "box-shadow: inset 0 0 10px rgba(0,0,0,0.05);'>"

                    + "<h1 style='color:#2196F3; font-size:32px; margin:0;'>"
                    + sentiment
                    + "</h1>"

                    + "</div>"

                    + "<p style='font-size:14px; color:#777;'>Keep journaling and stay positive 😊</p>"
                    + "</div>"

                    // Button (hover limited support)
                    + "<div style='text-align:center; padding-bottom:25px;'>"
                    + "<a href='#' style='text-decoration:none;'>"
                    + "<button style='background:#4CAF50; color:white; border:none; padding:12px 25px; "
                    + "border-radius:8px; font-size:14px; cursor:pointer;'>View Dashboard</button>"
                    + "</a>"
                    + "</div>"

                    // Footer
                    + "<div style='background:#f1f1f1; padding:10px; text-align:center;'>"
                    + "<p style='font-size:12px; color:#888;'>Auto Generated • Journal App</p>"
                    + "</div>"

                    + "</div></div>";

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true); // ✅ HTML enable

            javaMailSender.send(message);

        }catch (Exception e){
            log.error("Exception while sending Email ", e);
        }
    }
}