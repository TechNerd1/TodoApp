# Task Manager spring boot project
This project showcases my skills in CRUD and REST operations. Users can create task lists that contain all related tasks, enabling them to complete, modify, or delete them.

**Link to project:** https://v0-todo-list-frontend-five.vercel.app/

![img](https://i.imgur.com/ctdgOVO.png)
![img](https://i.imgur.com/NjAA0Os.png)

## How It's Made:

**Tech used:** Java, Spring Boot, Spring Web, Spring data JPA, PostgreSQL, React.js, Docker

The first thing I did after setting the project up was define all the entities. I knew by planning this project out that I would need 2 entities, TaskList and Tasks. Both of these entities were very similar in definition with the primary difference being the way they reference each other. TaskList defines a type List<> named tasks that stores all the tasks within that list, and Task has a TaskList type that references the TaskList it belongs to. 

Next, I went to work on creating the repositories. Repositories are responsible for saving our entities to the Postgres database. This was simple as all I needed to do was create an interface for each entity that extends JpaRepository. For the Tasks Repository I learned how to create a custom method called findByTaskList_Id that allows us to filter tasks by their TaskList. 

After that was complete I got to work on the service layer. The first thing I did was create the interfaces for each entity type defining all the methods we would need for each entity. This involves the declaration of get, create, update, and delete methods. After that simple step I could create the implementations of each of these interfaces. This is where we define what each method we named does. These methods speak directly to the repositories we created in the previous step. For instance, when the createTaskList method gets called, it takes a parameter of CreateTaskListRequest (this is a DTO we create in the next step) which returns all the information we received from the client about what to name the TaskList and a description if they put one. This then communicates to our Mapper class, a class with static methods, which translates the JSON to a Java object, or in this case our entity TaskList we created. We then set the createdAt and updatedAt variable with LocalDateTime.now(), then we use the save() method of our TaskListRepository to save our object to the database.

Once I had that done, I went on to create the DTOs or Data Transfer Objects. These are responsible for converting and mapping objects to send back to the client. This allows for more control over the data and what gets sent to the client. This is more useful when there are data fields that are more sensitive, like omitting passwords from user tables and such. With these created, it's finally time to move on to the REST controllers. 

This project ended up having 3 REST controllers. One for Tasks, TaskLists, and a third for displaying health information at the /health endpoint. The Task and TaskList controllers expose all the endpoints the client can make requests to. Most of these methods will return a ResponseEntity that way we can have proper messaging returning back to the client. Create and Update methods are triggered when the client makes a Post or Put request to the /api/task and /api/task/{id} endpoints. They take the DTOs we made earlier as the arguments for each method.


## Lessons Learned:

This was my first solo project with Spring Boot, so I really learned a ton. From DTOs, which I haven’t done before, to pushing this project to production via Railway. This project really helped me to fork down my Spring Boot skills and allowed me to exercise best practices when it comes to Java code.

