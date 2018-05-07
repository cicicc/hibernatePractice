package com.afeng.test;

import com.afeng.domain.Customer;
import com.afeng.domain.LinkMan;
import com.afeng.utils.HibernateUtils;
import org.hibernate.Session;
import org.junit.Test;

public class TestTableRelation {
    @Test
    public void testLkmAndCust(){
        //测试代码的书写
        //册数关系是否配置准确
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        //创建Customer和LinkMan对象 并进行保存
        Customer customer = new Customer();
        customer.setCust_name("test1");
        customer.setCust_mobile("25656542");
        customer.setCust_industry("公务员");
        customer.setCust_source("网络");
        LinkMan linkMan = new LinkMan();
        linkMan.setLkm_name("linkman1");
        linkMan.setLkm_email("156548@qq.com");
        linkMan.setLkm_gender("1");
        customer.getLinkMans().add(linkMan);
        linkMan.setCustomer(customer);//不保存linkMan 则linkman并未进行保存 因为在这里的linkman仍旧是游离态而不是持久态
        session.save(customer);
        session.save(linkMan);

        //提交事务
        HibernateUtils.commitTransaction();
    }
    /**
     * 如下执行 会报此异常 原因:一个持久化对象关联了一个瞬时态对象
     * org.hibernate.TransientObjectException: 瞬时态对象异常
     * object references an unsaved transient instance -
     * save the transient instance before flushing: com.afeng.domain.Customer
     * 解决此问题的方法:设置级联保存 在主控方set标签或者one-to-many标签上设置级联保存属性为 cascade save-update
     * 这样保存的时候仅需要保存主控方 当然 仅保存被控方也是会报错的
     * 级联操作是指当主控方执行增删改操作时,其关联对象(被控方)也执行相同操作
     */
    @Test
    public void testLkmAndCust2(){
         //测试代码的书写
         //册数关系是否配置准确
         //获取session对象
         Session session = HibernateUtils.getCurrentSession();
         //开启事务
         HibernateUtils.startTransaction();
         //创建Customer和LinkMan对象 并进行保存
         Customer customer = new Customer();
         LinkMan linkMan = new LinkMan();
         linkMan.setLkm_name("linkman1");
         linkMan.setLkm_email("1565414748@163.com");
         linkMan.setLkm_gender("0");
         customer.setCust_name("test2");
         customer.setCust_mobile("156854695");
         customer.setCust_industry("公务员");
         customer.setCust_source("交友圈");
         customer.getLinkMans().add(linkMan);
         linkMan.setCustomer(customer);//不保存linkMan 则linkman并未进行保存 因为在这里的linkman仍旧是游离态而不是持久态
         //session.save(linkMan);
         session.save(customer);
         //提交事务
         HibernateUtils.commitTransaction();
    }

    /**
     * 级联删除相关操作 默认情况下会把与删除对象进行了外键关联的对象 外键属性设为null 此时执行的hibernate代码如下
     *  select *(简化)
     *     from
     *         customer customer0_
     *     where
     *         customer0_.cust_id=?
     * Hibernate:
     *     update
     *         linkman
     *     set
     *         lkm_cust_id=null
     *     where
     *         lkm_cust_id=?
     * Hibernate:
     *     delete
     *     from
     *         customer
     *     where
     *         cust_id=?
     */
    @Test
    public void testCascadeDelete1(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        Customer customer = session.get(Customer.class, 11);
        session.delete(customer);
        //提交事务
        HibernateUtils.commitTransaction();
    }

    /**
     * 级联删除操作 此时将一方的cascade属性设置为delete
     * 此时hibernate生成的代码如下
     *     select *
     *     from
     *         customer customer0_
     *     where
     *         customer0_.cust_id=?
     * Hibernate:
     *     select *
     *     from
     *         linkman linkmans0_
     *     where
     *         linkmans0_.lkm_cust_id=?
     * Hibernate:
     *     update
     *         linkman
     *     set
     *         lkm_cust_id=null
     *     where
     *         lkm_cust_id=?
     * Hibernate:
     *     delete
     *     from
     *         linkman
     *     where
     *         lkm_id=?
     * Hibernate:
     *     delete
     *     from
     *         customer
     *     where
     *         cust_id=?
     *         阅读以上的SQL代码 可知 当设置了级联删除以后 会把所有相关对象查询出来 再将被控方的相关外键字段全都设为null
     *         最后将两部分代码全都删除掉
     */
    //如果想要同时设置级联保存和级联删除属性 可如下方式写cascade
    //cascade="delete,save-update"; 不同属性中间以逗号割开
    @Test
    public void testCascadeDelete2(){
        //获取session对象
        Session session = HibernateUtils.getCurrentSession();
        //开启事务
        HibernateUtils.startTransaction();
        Customer customer = session.get(Customer.class, 12);
        session.delete(customer);
        //提交事务
        HibernateUtils.commitTransaction();
    }

  /**
   * 测试双向关联产生的多余代码问题
   *当我们进行双向关联的时候 会产生多余代码
   *未进行修改时产生的代码如下
   * Hibernate:
   *     update
   *         linkman
   *     set
   *         lkm_name=?,
   *         lkm_gender=?,
   *         lkm_phone=?,
   *         lkm_mobile=?,
   *         lkm_email=?,
   *         lkm_qq=?,
   *         lkm_position=?,
   *         lkm_memo=?,
   *         lkm_cust_id=?
   *     where
   *         lkm_id=?
   * Hibernate:
   *     update
   *         linkman
   *     set
   *         lkm_cust_id=?
   *     where
   *         lkm_id=?
   *         此时由于操作的两个对象都是持久化对象 所以每次操作后对象都会自动操作数据库 这样就会产生多余的操作
   *         解决方法 设置 inverse="true" 也就是放弃外键维护 一般将主控方放弃外键维护 也就是少的那一方 并且多方也无法放弃外键维护
   *         此时hibernate产生代码
   *          update linkman
   *     set
   *         lkm_name=?,
   *         lkm_gender=?,
   *         lkm_phone=?,
   *         lkm_mobile=?,
   *         lkm_email=?,
   *         lkm_qq=?,
   *         lkm_position=?,
   *         lkm_memo=?,
   *         lkm_cust_id=?
   *     where
   *         lkm_id=?
   */
  @Test
  public void testInverse1(){
      //获取session对象
      Session session = HibernateUtils.getCurrentSession();
      //开启事务
      HibernateUtils.startTransaction();
      Customer customer = session.get(Customer.class, 3);
      LinkMan linkMan = session.get(LinkMan.class, 3L);
      //互相修改外键的相关属性值
      customer.getLinkMans().add(linkMan);
      linkMan.setCustomer(customer);
      //提交事务
      HibernateUtils.commitTransaction();
  }

}

