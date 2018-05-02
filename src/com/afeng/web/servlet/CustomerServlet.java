package com.afeng.web.servlet;

import com.afeng.domain.Customer;
import com.afeng.service.CustomerService;
import com.afeng.service.impl.CustomerServiceImpl;
import com.afeng.web.base.BaseServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


public class CustomerServlet extends BaseServlet {
    //创建service层方法
    private CustomerService customerService = new CustomerServiceImpl();
    public String list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //直接调用service层对象 使用service层对象中的findAll方法
       List<Customer> list =  customerService.findAll();
        request.setAttribute("list",list);

        return "jsp/customer/list.jsp";
    }
}
