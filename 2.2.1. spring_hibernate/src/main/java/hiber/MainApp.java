package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
              new AnnotationConfigApplicationContext(AppConfig.class);

      SessionFactory sessionFactory = context.getBean(SessionFactory.class);
      sessionFactory.openSession().close();

      UserService userService = context.getBean(UserService.class);

      Car car1 = new Car("Toyota", 4);
      Car car2 = new Car("BMW", 5);
      Car car3 = new Car("Audi", 6);
      Car car4 = new Car("Mercedes", 7);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", car1));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", car2));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", car3));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", car4));

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());
         System.out.println("Car = " + (user.getCar() != null
                 ? user.getCar().getModel() + " " + user.getCar().getSeries()
                 : "No car"));
         System.out.println();
      }

      context.close();
   }
}
