package com.stg.demo.service;

import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.stg.demo.model.Customer;
import com.stg.demo.reponsitory.CustomerRepository;

@Service
public class MailService {
	@Autowired
	JavaMailSender sender;

	private List<MimeMessage> mailList = new ArrayList<>();

	public void pushMail(String email) {
		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("FleeBounce <lvhungbk@gmail.com>");
			helper.setTo(email);
			helper.setSubject("Xác nhận đơn hàng của bạn!");
			helper.setText("Đơn hàng của bạn đang được xử lý, cảm ơn bạn đã đặt hàng!");
		} catch (MessagingException e) {
			System.out.println("Không thể thêm mail vào hàng đợi");
			e.printStackTrace();
		}
		mailList.add(message);
	}

	@Autowired
	CustomerRepository customerRepository;

	public void sendMailWithCustomerID(int customer_id) {
		Customer customer = this.customerRepository.getById(customer_id);
		String content = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "    <meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Document</title>\n" + "</head>\n" + "<body>\n"
				+ "    <div style= \"background: #ddd;padding : 20px; margin : 20px\">\n"
				+ "        <h3>Thông tin đơn hàng</h3>\n" + "        <p>\n" + "            Tên Người Nhận : <b>"
				+ customer.getName() + "</b>\n" + "        </p>\n" + "        <p>\n"
				+ "            Số điện thoại  : <b>" + customer.getPhoneNumber() + "</b>\n" + "        </p>\n"
				+ "        <p>\n" + "            Địa chỉ : <b>" + customer.getAddress() + "</b>\n" + "        </p>\n";

		content += "    </div>\n" + "</body>\n" + "</html>";

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("Flee Bounce <lvhungbk@gmail.com>");
			helper.setTo(customer.getEmail());
			helper.setSubject("Cảm ơn bạn đã đặt hàng");
			helper.setText(content, true);
		} catch (MessagingException e) {
			System.out.println("Không thể thêm mail vào hàng đợi");
			e.printStackTrace();
		}
		mailList.add(message);
		// Send Message!
		this.sender.send(message);

	}

	@Scheduled(fixedDelay = 2000)
	public void run() {
		while (!mailList.isEmpty()) {
			MimeMessage message = mailList.remove(0);
			try {
				sender.send(message);
			} catch (Exception e) {
				System.out.println("Gửi mail lỗi");
				e.printStackTrace();
			}
		}
	}
}
