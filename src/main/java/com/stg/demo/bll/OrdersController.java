package com.stg.demo.bll;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.stg.demo.model.Order;
import com.stg.demo.model.SearchForm;
import com.stg.demo.reponsitory.OrderRepository;

@Controller
@RequestMapping("order")
public class OrdersController {
	@Autowired
	OrderRepository orderRepository;
	// Cài đặt tối đa 5 sản phẩm trên 1 trang Dashboard
	private static final int MAX_ITEMS = 5;

	// Danh sách sản phẩm trong Dashboard
	@GetMapping("list")
	public String list(@ModelAttribute(name = "searchForm") SearchForm sf, Model model) {

		Pageable pagin = PageRequest.of(sf.getPage(), MAX_ITEMS, sf.isIndex() ? Direction.ASC : Direction.DESC,
				sf.getSortBy());
		// lấy sản phẩm
		Page<Order> orderPage = orderRepository.findByNameContainingIgnoreCase(sf.getName(), pagin);
		model.addAttribute("order", orderPage.getContent());
		model.addAttribute("maxPage", orderPage.getTotalPages());
		return "order/listitems";
	}
}
