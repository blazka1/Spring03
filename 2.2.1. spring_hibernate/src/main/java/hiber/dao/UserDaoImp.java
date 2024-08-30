package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Transactional
   @Override
   public void saveCar(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   @org.springframework.transaction.annotation.Transactional(readOnly = true)
   public User findUserByCarModelAndSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      return (User) session.createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series")
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("from User");
      return query.getResultList();
   }

}
