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

import com.stg.demo.model.Order;
import com.stg.demo.model.OrderItems;
import com.stg.demo.model.Customer;
import com.stg.demo.reponsitory.CustomerRepository;
import com.stg.demo.reponsitory.OrderItemsRepository;
import com.stg.demo.reponsitory.OrderRepository;

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
	OrderRepository orderRepository;

	@Autowired
	OrderItemsRepository orderItemsRepository;

	public void sendMailWithOrderId(int orderId) {

		// lay data
		Order order = orderRepository.getById(orderId);
		List<OrderItems> orderItems = orderItemsRepository.findOrderItems(orderId);

		// tao noi dung
		String content = "<!DOCTYPE html>\n" + "<html lang=\"en\">\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "    <title>Document</title>\n" + "</head>\n" + "<body>\n"
				+ "    <div style= \"background: #ddd;padding : 20px; margin : 20px\">\n"
				+ "        <h3>Thông tin đơn hàng</h3>\n" + "        <p>\n" + "Mã order : <b>" + order.getId()
				+ "</b>\n" + " </p>\n" + "<p>\n" + "Tên người nhận : <b>" + order.getCustomer().getName() + "</b>\n"
				+ "  </p>\n" + "<p>\n" + " Số điện thoại: <b>" + order.getCustomer().getPhoneNumber() + "</b>\n"
				+ "</p>\n" + "<p>\n" + "            Địa chỉ : <b>" + order.getCustomer().getAddress() + "</b>\n"
				+ "</p>\n" + " <p>\n" + "Sản phẩm bao gồm : </p>\n";

		for (int i = 0; i < orderItems.size(); i++) {
			OrderItems orderItem = orderItems.get(i);
			content = content + " <p>\n- " + orderItem.getProducts().getName() + " :  <b>" + orderItem.getPrice()
					+ "VND</b>  <i>(x " + orderItem.getAmount() + ")</i>\n        </p>\n        " + "</p>\n";
		}
		content += "<p>\n" + "Tổng tiền : <b>" + order.getTotal(orderItems) + "</b>\n" + "  </p>\n" + "    </div>\n"
				+ "</body>\n" + "</html>";

		MimeMessage message = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("FleeBounce <lvhungbk@gmail.com>");
			helper.setTo(order.getCustomer().getEmail());
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
