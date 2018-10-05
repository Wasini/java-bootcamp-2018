# Java-bootcamp-2018-Goal4-Rodrigo-Grazini

#### Topic 4: SQL - MySql (2 days)

##### Reading:
1. [MySql basis][my-sql]

2. [Jdbc basis][jdbc]

##### Extra documentation:

1. [MySql optimization][myslq-optimisation]


##### Practice:
Do not solve the practice using JDBC, please just send us the sql scripts.

1. Create a database named 'high-school' and modelate:
 
   - Student: first name, last name, registration number, date of birth)
   - Teacher: first name, last name, date of birth)
   - Course: name, assigned teacher, hours by week, schedule time (they can be dictated several times during the week)

   Notes:
   - An student can assist several courses during the same year.
   - A teacher can be assigned to several courses.
   - For each course, each student has 3 partial notes and a final note.
   - Create all relationship that you think they are required.

2. Insert information for 3 teachers, 3 courses and 10 students per course.
3. List students and teachers for a given course. The output format should be:

        Course: <course-name>
        Teacher: <last-name>, <first-name>
        Students:
          <last-name>, <first-name> (ordered by alphabetically by last name)

4. Percentage of students that passed/failed a given course.
5. For a given teacher, list the timeline for each course that he is assigned to (ordered by date), and the course name. The format should be:

        Teacher: <last-name>, <first-name>
        Schedule:
          Monday 09:00 - 11:00: <course-name>
          Monday 15:00 - 17:30: <course-name>
          Friday 08:45 - 10:40: <course-name>

6. Identify and Optimize all queries.
7. Connect to MySQL using Java JDBC and perform the query you have developed in excercise 5.

##### Key Points:

1,2,3,5,7

##### Commit:

Commit your practice code.



[sdk]: https://www.oracle.com/technetwork/java/javase/downloads/index.html
[eclipse]: https://www.eclipse.org/downloads/
[eclipse-j2ee]: https://www.eclipse.org/downloads/packages/release/photon/r/eclipse-ide-java-ee-developers
[github]: https://github.com/
[github-guide]: https://help.github.com/articles/set-up-git
[try-git]: https://try.github.io/
[learn-git]: https://pcottle.github.io/learnGitBranching/
[fork-repo]: https://help.github.com/articles/fork-a-repo/
[team]: https://www.dummies.com/how-to/content/ten-qualities-of-an-effective-team-player.html
[formatter]: https://raw.githubusercontent.com/spring-projects/spring-batch/master/spring-eclipse-code-conventions.xml
[importing-formatter]: https://che.eclipse.org/release-note-5-18-993516476315
[java-definitions]: https://www.oracle.com/webfolder/technetwork/tutorials/obe/java/gc01/index.html#t1s1
[java-concepts]: https://docs.oracle.com/javase/tutorial/java/concepts/
[introduction-to-java]: https://www.ibm.com/developerworks/java/tutorials/j-introtojava1/
[design-patterns]: https://www.avajava.com/tutorials/categories/design-patterns
[design-patterns-cheat-sheet]: http://www.mcdonaldland.info/files/designpatterns/designpatternscard.pdf
[mock-objects]: https://en.wikipedia.org/wiki/Mock_object
[abstract-factory]: https://www.tutorialspoint.com/design_pattern/abstract_factory_pattern.htm
[what-is-maven]: https://maven.apache.org/what-is-maven.html
[maven-in-5]: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
[maven-best-practices]: https://books.sonatype.com/mvnref-book/reference/pom-relationships-sect-pom-best-practice.html
[tdd]: https://technologyconversations.com/2013/12/24/test-driven-development-tdd-best-practices-using-java-examples-2/
[java-service]: https://spring.io/guides/gs/rest-service/
[api-design]: https://www.youtube.com/watch?v=aAb7hSCtvGw
[my-sql]: https://dev.mysql.com/doc/refman/en/
[jdbc]: https://www.oracle.com/technetwork/java/javase/jdbc/index.html
[myslq-optimisation]: https://www.arsys.es/blog/bases-de-datos/como-optimizar-bases-de-datos-mysql/
[no-sql]: https://www.thegeekstuff.com/2014/01/sql-vs-nosql-db/
[mongo]: https://docs.mongodb.com/manual/installation/
[sql-to-mongo]: https://docs.mongodb.com/manual/reference/sql-comparison/
[java-to-mongo]: https://dzone.com/articles/using-morphia-map-java-objects
[rest]: https://www.youtube.com/watch?v=YCcAE2SCQ6k
[spring-rest]: https://spring.io/guides/gs/rest-service/
[spring-boot-rest]: http://spring.io/guides/tutorials/bookmarks/
[swagger]: https://swagger.io/getting-started/
