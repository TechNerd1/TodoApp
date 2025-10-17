# Task Manager spring boot project
This project showcases my skills in CRUD and REST operations. Users can create task lists that contain all related tasks, enabling them to complete, modify, or delete them.

**Link to project:** https://v0-todo-list-frontend-five.vercel.app/

![img](https://i.imgur.com/ctdgOVO.png)

## How It's Made:

**Tech used:** Java, Spring Boot, Spring Web, Spring data JPA, PostgreSQL, React.js, Docker

The first thing I did after setting the project up was define all the entities. I knew by planning this project out that I would need 2 entities, TaskList and Tasks. Both of these entities were very similar in definition with the primary difference being the way they reference each other. TaskList defines a type List<> named tasks that stores all the tasks within that list, and Task has a TaskList type that references the TaskList it belongs to. 

Next, I went to work on creating the repositories. Repositories are responsible for saving our entities to the Postgres database. This was simple as all I needed to do was create an interface for each entity that extends JpaRepository. For the Tasks Repository I learned how to create a custom method called findByTaskList_Id that allows us to filter tasks by their TaskList. 

After that was complete I got to work on the service layer. The first thing I did was create the interfaces for each entity type defining all the methods we would need for each entity. This involves the declaration of get, create, update, and delete methods. After that simple step I could create the implementations of each of these interfaces. This is where we define what each method we named does. These methods speak directly to the repositories we created in the previous step. For instance, when the createTaskList method gets called, it takes a parameter of CreateTaskListRequest (this is a DTO we create in the next step) which returns all the information we recieved from the client about what to name the TaskList and a description if they put one. This then communicates to our Mapper class, a class with static methods, which translates the JSON to a Java object, or in this case our entity TaskList we created. We then set the createdAt and updatedAt variable with LocalDateTime.now(), then we use the save() method of our TaskListRepository to save our object to the database.

Once I had that done I went on to 

## Optimizations
*(optional)*

You don't have to include this section but interviewers *love* that you can not only deliver a final product that looks great but also functions efficiently. Did you write something then refactor it later and the result was 5x faster than the original implementation? Did you cache your assets? Things that you write in this section are **GREAT** to bring up in interviews and you can use this section as reference when studying for technical interviews!

## Lessons Learned:

No matter what your experience level, being an engineer means continuously learning. Every time you build something you always have those *whoa this is awesome* or *wow I actually did it!* moments. This is where you should share those moments! Recruiters and interviewers love to see that you're self-aware and passionate about growing.

## Examples:
Take a look at these couple examples that I have in my own portfolio:

**Palettable:** https://github.com/alecortega/palettable

**Twitter Battle:** https://github.com/alecortega/twitter-battle

**Patch Panel:** https://github.com/alecortega/patch-panel
