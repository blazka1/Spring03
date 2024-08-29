package hiber.service;

import hiber.dao.UserDao;
import hiber.model.Car;
import hiber.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   @Autowired
   private SessionFactory sessionFactory;

   @Autowired
   private UserDao userDao;

   @Transactional
   @Override
   public void add(User user) {
      userDao.add(user);
   }

   @Transactional
   @Override
   public void saveCar(Car car) {
      userDao.saveCar(car);
   }

   @Override
   @Transactional(readOnly = true)
   public User findUserByCarModelAndSeries(String model, int series) {
      Session session = sessionFactory.getCurrentSession();
      return (User) session.createQuery("SELECT u FROM User u JOIN u.car c WHERE c.model = :model AND c.series = :series")
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }

   @Transactional(readOnly = true)
   @Override
   public List<User> listUsers() {
      return userDao.listUsers();
   }

}
