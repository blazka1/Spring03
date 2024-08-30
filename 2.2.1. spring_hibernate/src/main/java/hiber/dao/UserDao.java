package hiber.dao;

import hiber.model.Car;
import hiber.model.User;

import java.util.List;

public interface UserDao {
   void add(User user);

   void saveCar(Car car);

   User findUserByCarModelAndSeries(String model, int series);

   List<User> listUsers();
}
