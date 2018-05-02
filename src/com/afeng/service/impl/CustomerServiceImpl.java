package com.afeng.service.impl;

import com.afeng.dao.CustomerDao;
import com.afeng.dao.impl.CustomerDaoImpl;
import com.afeng.domain.Customer;
import com.afeng.service.CustomerService;
import com.afeng.utils.HibernateUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CustomerServiceImpl implements CustomerService {
//创建customerDao层对象
    CustomerDao customerDao = new CustomerDaoImpl();

    @Override
    public List<Customer> findAll() {
        Session session = HibernateUtils.getCurrentSession();
        Transaction transaction = session.beginTransaction();
        List<Customer> list = customerDao.findAll();
        transaction.commit();
        return list;
    }
}
