package com.afeng.practiceCode;

import com.afeng.domain.Customer;
import com.afeng.utils.HibernateUtils;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import java.util.List;


public class practiceHQL {
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
    //增
    @Test
    public void testHQl3(){
        //使用工具类 获取session并开启事务
        Session session = HibernateUtils.getCurrentSession();
        HibernateUtils.startTransaction();
        //在事务中进行操作
        //执行新增操作
        Customer customer = new Customer();
        //这一句话是用来执行查询操作的
        // session.createQuery("");
        customer.setCust_name("鼻涕佛纯");
        customer.setCust_source("家庭成员");
        customer.setCust_industry("老师");
        customer.setCust_mobile("111111111111");
        //保存对象 将对象从游离态转变为持久态
        session.save(customer);

        //提交事务
        HibernateUtils.commitTransaction();
        //关闭session session会自动关闭
       // session.close();
    }


    //再写一遍完整的测试hibernate的方法 查
    @Test
    public void testHQL4(){
        //先加载配置文件
        Configuration configure = new Configuration().configure();
        //再开启相对应的sessionFactory对象
        SessionFactory sessionFactory = configure.buildSessionFactory();
        //使用sessionFactory对象进行操作
        Session session = sessionFactory.openSession();
        List<Customer> customers = session.createQuery("from Customer").list();
        for (Customer customer : customers) {
            System.out.println(customer);
        }

    }
    //删
    @Test
    public void testHQL5(){
        //调用工具类获得session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //session.delete(Customer,7);
        //先get后删除
        Customer customer = session.get(Customer.class, 7);
        session.delete(customer);
        //关闭事务
        HibernateUtils.commitTransaction();
    }
    //改
    @Test
    public void testHQL6(){
        //调用工具类获得session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //先get后更新
        Customer customer = session.get(Customer.class, 9);
        customer.setCust_mobile("22222222");//对于持久化对象的操作无需再进行保存 会自动保存

        //关闭事务
        HibernateUtils.commitTransaction();
    }
    //使用冒号占位符进行查询操作
    @Test
    public void testHQL7(){
        //获得当前线程绑定的session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        Query query = session.createQuery("from Customer where cust_id = :cust_id");
        Customer customer = (Customer)query.setParameter("cust_id", 5).list().get(0);
        System.out.println(customer);
        //提交事务
        HibernateUtils.commitTransaction();
    }

    //分页查询
    @Test
    public void testHQL8(){
        //获得当前线程绑定的session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        Query query = session.createQuery("from Customer order by cust_id");
        //设置查询的最大数据数目 以及设置开始的条数
        query.setMaxResults(3);
        query.setFirstResult(0);
        List<Customer> list = query.list();
        for (Customer customer : list) {
            System.out.println(customer);
        }
        //提交事务
        HibernateUtils.commitTransaction();
    }

}
