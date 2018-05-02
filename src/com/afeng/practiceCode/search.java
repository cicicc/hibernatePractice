package com.afeng.practiceCode;

import com.afeng.domain.Customer;
import com.afeng.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;


public class search {
    /**
     * 这个类的作用就是为了能够获取连续hibernate的查询语言
     *
     */
    @Test
    public void testQueryOne(){
        //训练 不使用工具类 进行代码的书写
        //1.首先加载配置文件
        Configuration configure = new Configuration().configure();
        //2.获得SessionFactory对象
        SessionFactory sessionFactory = configure.buildSessionFactory();
        //3.开启会话对象
        Session session = sessionFactory.openSession();
        //4.开启事务
        Transaction transaction = session.beginTransaction();
        //5.在事务的开启和关闭之间执行对数据库的操作
        Customer customer = new Customer();
        customer.setCust_name("hello");
        session.save(customer);
        //6.关闭事务
        transaction.commit();

    }
    @Test
    public void testHQL2(){
      //使用工具类 获取session对象 开启事务
        Session session = HibernateUtils.getCurrentSession();
        HibernateUtils.startTransaction();
        //Customer customer = new Customer();
        //String hql = "from Customer where cust_lever = ?";

        Query query = session.createQuery("from Customer where cust_level=?");
        List<Customer> list = query.setParameter(0, "9").list();
        for (Customer customer :
                list) {
            System.out.println(customer);
        }
        HibernateUtils.commitTransaction();
    }
    @Test
    public void testHQl3(){
        //使用工具类 获取session并开启事务
        Session session = HibernateUtils.getCurrentSession();
        HibernateUtils.startTransaction();
        //在事务中进行操作
        //执行新增操作
        Customer customer = new Customer();
        session.createQuery("");


        //提交事务
        HibernateUtils.commitTransaction();
    }
}
