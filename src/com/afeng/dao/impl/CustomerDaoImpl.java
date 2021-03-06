package com.afeng.dao.impl;

import com.afeng.dao.CustomerDao;
import com.afeng.domain.Customer;
import com.afeng.utils.HibernateUtils;
import org.hibernate.Session;

import java.util.List;

public class CustomerDaoImpl implements CustomerDao {
    @Override
    public List<Customer> findAll() {
        //在数据库中进行查询
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //进行查询并且获取数据的list集合 将集合返回
        List<Customer> customers = session.createQuery("from Customer ").list();
        return customers;
    }

    @Override
    public List<Customer> findByUserInput(String custName) {
        //获得session对象
        Session session = HibernateUtils.getCurrentSession();
        //进行HQL模糊查询并返回结果
        List<Customer> list = session.createQuery("from Customer where cust_name=? ").setParameter(0, "%" + custName + "%").list();
        return list;
    }
}
