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

    public String findByUserInput(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取用户在输入框中输入的内容
        String custName = request.getParameter("custName");
        System.out.println(custName);
        //对custName进行判断
        if (custName != null) {
            List<Customer> list =customerService.findByUserInput(custName);
            //将查询所得的数据放入request域中
            request.setAttribute("list", list);
            //将用户输入的查询内容放入request域中,以便回写到输入框中
            request.setAttribute("custName", custName);

        }
        return "jsp/customer/list.jsp";
    }
}
