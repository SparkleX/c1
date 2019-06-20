# Jpatis [[Chinese](doc/readme_zh_CN.md)]

Jpatis is a database access library combine the advantage of JPA and mybatis.

This library is still in development (with bugs).

## Introduction

1. **SQL based, no ORM, easy to lean**
1. **Integrated with spring-boot**

## Examples

1. CRUD with spring repository
    
    Declare repository extends  JpatisRepository
    ```java
    public interface UserRepository extends JpatisRepository<User,Integer> {
    }

    ```
    Call repository in service layer
    ```java 
    @Service
    public class UserService {
    
        @Autowired
        UserRepository repoUser;
        
        public Integer create(User entity) {
            repoUser.insert(entity);
            return entity.getId();
        }
        public User update(Integer id, User entity) {
            repoUser.updateById(id, entity);
            return repoUser.findById(id).get();
        }
        public void delete(Integer id) {
            repoUser.deleteById(id);		
        }
    } 
    ```
1. Query with spring repository
    Declare SQL in Native SQL or use @SQL
    ```java 
    public interface UserRepository extends JpatisRepository<User,Integer>{
        @SQL("select * from User where id=?")
        List<User> getUserListById(Integer id);
    
        @SQL("select * from User where id=?")
        User getUserById(Integer id);
    }
    ```