package com.college.upgradetest;

import com.college.sms.MessageSender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import jakarta.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Replaces every outbound integration with an in-memory recorder, so the tests never send mail or SMS.
 * Firebase is only initialised offline at startup (no network until a query runs), and the tests never call
 * {@code /rn} or {@code /rs}, the endpoints that would reach Firebase or Google Calendar.
 */
@Configuration
public class TestDoublesConfig {

    @Bean
    @Primary
    public RecordingMailSender recordingMailSender() {
        return new RecordingMailSender();
    }

    @Bean
    @Primary
    public RecordingMessageSender recordingMessageSender() {
        return new RecordingMessageSender();
    }

    /** JavaMailSender that keeps sent messages instead of talking to SMTP. */
    public static class RecordingMailSender extends JavaMailSenderImpl implements JavaMailSender {
        public final List<SimpleMailMessage> simpleMessages = Collections.synchronizedList(new ArrayList<SimpleMailMessage>());
        public final List<MimeMessage> mimeMessages = Collections.synchronizedList(new ArrayList<MimeMessage>());

        @Override
        public void send(SimpleMailMessage... messages) throws MailException {
            Collections.addAll(simpleMessages, messages);
        }

        @Override
        protected void doSend(MimeMessage[] messages, Object[] originals) throws MailException {
            Collections.addAll(mimeMessages, messages);
        }

        public void clear() {
            simpleMessages.clear();
            mimeMessages.clear();
        }
    }

    /** MessageSender that runs the group logic but records SMS instead of calling the Textlocal API. */
    public static class RecordingMessageSender extends MessageSender {
        public final List<String> sent = Collections.synchronizedList(new ArrayList<String>());

        @Override
        public String sendSMS(List<String> nums, String template, String msg) {
            sent.add(nums + "|" + template + "|" + msg);
            return "recorded";
        }
    }
}
