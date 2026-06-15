//package com.edigest.my.first.project.service;
//
//import jakarta.mail.internet.MimeMessage;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//
//@Slf4j
//@Service
//public class EmailService {
//
//    @Autowired
//    private JavaMailSender javaMailSender;
//
//    public void sendEmail(String to, String subject, String sentiment){
//
//        try{
//            MimeMessage message = javaMailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//
//            String html = "<div style='background:#f0f2f5; padding:30px; font-family:Segoe UI, Arial;'>"
//
//                    + "<div style='max-width:600px; margin:auto; background:#ffffff; border-radius:15px; "
//                    + "box-shadow:0 8px 20px rgba(0,0,0,0.1); overflow:hidden;'>"
//
//                    // Header
//                    + "<div style='background:linear-gradient(135deg,#4CAF50,#2196F3); padding:20px; color:white;'>"
//                    + "<h2 style='margin:0; text-align:center; font-weight:600;'>📊 Weekly Sentiment Report</h2>"
//                    + "</div>"
//
//                    // Body
//                    + "<div style='padding:25px; text-align:center;'>"
//                    + "<p style='font-size:18px; margin-bottom:10px;'>Hello 👋</p>"
//                    + "<p style='font-size:15px; color:#555;'>Here is your sentiment summary for last 7 days:</p>"
//
//                    // Sentiment Card
//                    + "<div style='margin:20px auto; padding:20px; width:80%; "
//                    + "border-radius:12px; background:#f9fafc; "
//                    + "box-shadow: inset 0 0 10px rgba(0,0,0,0.05);'>"
//
//                    + "<h1 style='color:#2196F3; font-size:32px; margin:0;'>"
//                    + sentiment
//                    + "</h1>"
//
//                    + "</div>"
//
//                    + "<p style='font-size:14px; color:#777;'>Keep journaling and stay positive 😊</p>"
//                    + "</div>"
//
//                    // Button (hover limited support)
//                    + "<div style='text-align:center; padding-bottom:25px;'>"
//                    + "<a href='#' style='text-decoration:none;'>"
//                    + "<button style='background:#4CAF50; color:white; border:none; padding:12px 25px; "
//                    + "border-radius:8px; font-size:14px; cursor:pointer;'>View Dashboard</button>"
//                    + "</a>"
//                    + "</div>"
//
//                    // Footer
//                    + "<div style='background:#f1f1f1; padding:10px; text-align:center;'>"
//                    + "<p style='font-size:12px; color:#888;'>Auto Generated • Journal App</p>"
//                    + "</div>"
//
//                    + "</div></div>";
//
//            helper.setTo(to);
//            helper.setSubject(subject);
//            helper.setText(html, true); // ✅ HTML enable
//
//            javaMailSender.send(message);
//
//        }catch (Exception e){
//            log.error("Exception while sending Email ", e);
//        }
//    }
//}

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

    public void sendEmail(String to, String subject, String sentiment) {
        try {

            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true, "UTF-8");

            String html =
                    "<!DOCTYPE html>"
                            + "<html>"
                            + "<head>"
                            + "<meta charset='UTF-8'>"
                            + "<meta name='viewport' content='width=device-width, initial-scale=1.0'>"

                            + "<style>"

                            + "body{"
                            + "margin:0;"
                            + "padding:0;"
                            + "background:#f3f4f8;"
                            + "font-family:'Segoe UI',Arial,sans-serif;"
                            + "}"

                            + ".container{"
                            + "max-width:700px;"
                            + "margin:30px auto;"
                            + "background:#ffffff;"
                            + "border-radius:25px;"
                            + "overflow:hidden;"
                            + "box-shadow:0 12px 35px rgba(0,0,0,0.18);"
                            + "}"

                            + ".header{"
                            + "background:linear-gradient(135deg,#ff4b91,#ff7676,#ff1744);"
                            + "padding:45px 30px;"
                            + "text-align:center;"
                            + "color:white;"
                            + "}"

                            + ".header h1{"
                            + "margin:0;"
                            + "font-size:38px;"
                            + "font-weight:bold;"
                            + "}"

                            + ".header p{"
                            + "margin-top:10px;"
                            + "font-size:18px;"
                            + "opacity:0.95;"
                            + "}"

                            + ".content{"
                            + "padding:45px 40px;"
                            + "text-align:center;"
                            + "}"

                            + ".title{"
                            + "font-size:34px;"
                            + "font-weight:bold;"
                            + "color:#ff4b91;"
                            + "margin-bottom:20px;"
                            + "}"

                            + ".message{"
                            + "font-size:18px;"
                            + "line-height:2;"
                            + "color:#444;"
                            + "}"

                            + ".highlight{"
                            + "color:#e91e63;"
                            + "font-weight:bold;"
                            + "}"

                            + ".card{"
                            + "margin-top:35px;"
                            + "padding:30px;"
                            + "background:linear-gradient(135deg,#fff0f5,#ffe4ec);"
                            + "border-radius:20px;"
                            + "box-shadow:0 8px 20px rgba(255,105,180,0.2);"
                            + "}"

                            + ".card h2{"
                            + "margin:0;"
                            + "font-size:36px;"
                            + "color:#ff1744;"
                            + "}"

                            + ".card p{"
                            + "margin-top:15px;"
                            + "font-size:17px;"
                            + "color:#666;"
                            + "line-height:1.8;"
                            + "}"

                            + ".shayari{"
                            + "margin-top:35px;"
                            + "padding:25px;"
                            + "background:#fff5f8;"
                            + "border-left:6px solid #ff4b91;"
                            + "border-radius:15px;"
                            + "font-size:18px;"
                            + "line-height:2;"
                            + "color:#555;"
                            + "font-style:italic;"
                            + "}"

                            + ".btn-area{"
                            + "text-align:center;"
                            + "padding:10px 0 45px;"
                            + "}"

                            + ".love-btn{"
                            + "display:inline-block;"
                            + "padding:16px 36px;"
                            + "background:linear-gradient(135deg,#ff4b91,#ff1744);"
                            + "color:white;"
                            + "text-decoration:none;"
                            + "font-size:18px;"
                            + "font-weight:bold;"
                            + "border-radius:14px;"
                            + "box-shadow:0 10px 20px rgba(255,75,145,0.35);"
                            + "}"

                            + ".footer{"
                            + "background:#fafafa;"
                            + "padding:18px;"
                            + "text-align:center;"
                            + "font-size:14px;"
                            + "color:#888;"
                            + "}"

                            + "</style>"
                            + "</head>"

                            + "<body>"

                            + "<div class='container'>"

                            + "<div class='header'>"
                            + "<h1>💌 A Message From Neeraj</h1>"
                            + "<p>Some feelings are forever ❤️</p>"
                            + "</div>"

                            + "<div class='content'>"

                            + "<div class='title'>Hey Mala ❤️</div>"

                            + "<div class='message'>"

                            + "Tumhare bina reh pana mere liye bahut mushkil hai yrr... 🥺💔<br><br>"

                            + "Main tumko kaise chhod sakta hu? ❤️<br><br>"

                            + "Kabhi kabhi lagta hai ki caste aur society sab kuch decide kar dete hain... 😔💔<br><br>"

                            + "Par mera dil aaj bhi sirf tumhe hi maanta hai ❤️✨<br><br>"

                            + "<span class='highlight'>"
                            + "Mai tum se bahut pyar karta hu ❤️🫶"
                            + "</span><br><br>"

                            + "Tumhari smile 😊, tumhari baatein 💖 aur tumhari care 🌸 "
                            + "mere liye duniya ki sabse special cheez hai ✨<br><br>"

                            + "Chahe duniya kuch bhi kahe... "
                            + "meri feelings kabhi fake nahi thi ❤️🥺<br><br>"

                            + "Main bas tumhari happiness chahta hu 🌷❤️"

                            + "Mala, tumne mujhe har jagah block kar diya hai 🌷❤️\n"
                            + "Par apne dil se mat dur karna… 💘 ek baar bas baat kar lo, main kaisa hoon tum khud samajh jaogi 🥺❤️"

                            + "</div>"

                            + "<div class='card'>"

                            + "<h2>❤️ I Miss You ❤️</h2>"

                            + "<p>"
                            + "Har heartbeat me bas tumhara hi naam aata hai 💓<br>"
                            + "Aur har dua me bas tumhari khushi maangta hu 🌸"
                            + "</p>"

                            + "</div>"

                            + "<div class='shayari'>"

                            + "✨ Shayari ✨<br><br>"

                            + "Mohabbat itni khamoshi se ki hai tumse ❤️<br>"
                            + "Ki aaj bhi har saans me tum mehsoos hoti ho 🥺<br><br>"

                            + "Duniya chahe jitni door kar de hume 💔<br>"
                            + "Par mere dil me hamesha tum hi rahogi ❤️✨"

                            + "</div>"

                            + "</div>"

                            + "<div class='btn-area'>"

                            + "<a class='love-btn' href='#'>"
                            + "💖 Forever In My Heart 💖"
                            + "</a>"

                            + "</div>"

                            + "<div class='footer'>"
                            + "Made with ❤️ by Neeraj"
                            + "</div>"

                            + "</div>"

                            + "</body>"
                            + "</html>";

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(html, true);

            javaMailSender.send(message);

            log.info("Email Sent Successfully");

        } catch (Exception e) {
            log.error("Exception while sending Email ", e);
        }
    }

}
