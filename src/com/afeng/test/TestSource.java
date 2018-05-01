package com.afeng.test;

import com.afeng.domain.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.Test;



public class TestSource {

    /**
     *测试环境的基本搭建是否成功
     * 已经配置好的环境有 hibernate.cfg.xml 和pojo类Customer以及其对应的映射文件
     * 结果：测试一切正常
     */
    @Test
    public void testSourceOne(){
        //加载配置文件 以下为源码中的数据 也就是默认加载hibernate.cfg.xml
        // public Configuration configure() throws HibernateException {
        //        return this.configure("hibernate.cfg.xml");
        //    }
        Configuration configure = new Configuration().configure();
        //创建一个sessionFactory对象 这并不可取 因为sessionFactory对象本身作为一个重量级的对象 占用较大存储空间
        SessionFactory factory = configure.buildSessionFactory();
        //使用创建的sessionFactory创建session会话对象
        Session session = factory.openSession();
        //开启事务
        Transaction transaction = session.beginTransaction();

        //在事务的中间进行相对应的操作
        //创建customer对象
        Customer customer = new Customer();
        //设置对象的属性
        customer.setCust_level("2");
        customer.setCust_industry("Beijing Opera");
        customer.setCust_name("Zhang Fei");
        //设置一个中文属性 看看是否可以正确提交
        customer.setCust_source("三国演义");//可以 一般也只有web才可能出现乱码
        //将对象保存大session中 否则就相当于什么都没做
        session.save(customer);//这句话的意义也就是个相当于将该对象从瞬时态转变为持久态
        //提交事务
        transaction.commit();
        //关闭session对象
        session.close();
    }
}
