package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);


      Car car1 = new Car("BMV", 1);
      Car car2 = new Car("Ford", 2);
      Car car3 = new Car("Huawei", 3);
      Car car4 = new Car("Chery", 4);
      userService.saveCar(car1);
      userService.saveCar(car2);
      userService.saveCar(car3);
      userService.saveCar(car4);


      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car = "+user.getCar());
         System.out.println();
      }

      System.out.println("FOUNDED_USER " + userService.findUserByCarModelAndSeries(car1.getModel(), car1.getSeries()));
      System.out.println("FOUNDED_USER " + userService.findUserByCarModelAndSeries(car2.getModel(), car2.getSeries()));
      System.out.println("FOUNDED_USER " + userService.findUserByCarModelAndSeries(car3.getModel(), car3.getSeries()));
      System.out.println("FOUNDED_USER " + userService.findUserByCarModelAndSeries(car4.getModel(), car4.getSeries()));

      context.close();
   }
}