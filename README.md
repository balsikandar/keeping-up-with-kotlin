# java8-android-tutorial

[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/balsikandar/Android-Studio-Plugins/blob/master/LICENSE)

## This is a guide for all java 8 APIs that are currently supported in android. 

#### Spread Some :heart:

[![GitHub stars](https://img.shields.io/github/stars/balsikandar/java8-android-tutorial.svg?style=social&label=Star)](https://github.com/balsikandar/Android-Studio-Plugins) [![GitHub forks](https://img.shields.io/github/forks/balsikandar/java8-android-tutorial.svg?style=social&label=Fork)](https://github.com/balsikandar/Android-Studio-Plugins/fork) [![GitHub watchers](https://img.shields.io/github/watchers/balsikandar/java8-android-tutorial.svg?style=social&label=Watch)](https://github.com/balsikandar/Android-Studio-Plugins)[![GitHub followers](https://img.shields.io/github/followers/balsikandar.svg?style=social&label=Follow)](https://github.com/balsikandar)
[![Twitter Follow](https://img.shields.io/twitter/follow/balsikandar.svg?style=social)](https://twitter.com/balsikandar)


This is a list of all currently supported java 8 APIs for Android. This repo will be updated regularly for new entries.

Here is an [article](...) related to this repo.

## Table of Contents

   * [Lambda Expressions](#lambda-expressions)
   * [Try with resource](#try-with-resource)
   * [Method references](#method-references)
   * [Optional](#optional)
      * [of](#of)
      * [ofNullable](#ofnullable)
      * [isPresent](#ispresent)
      * [orElse](#orelse)
      * [orElseGet](#orelseget)
      * [filter](#filter)
      * [map](#map)
      * [flatMap](#flatmap)
   
 ## Lambda Expressions
 
 ## Try with resource
 The try-with-resources statement is a `try` statement that declares one or more resources. The resource is as an object that must be closed after finishing the program. The try-with-resources statement ensures that each resource is closed at the end of the statement execution.

We can pass any object that implements java.lang.AutoCloseable, which includes all objects which implement java.io.Closeable.

```
static String readFileInJava8(String path) throws IOException {
    try (BufferedReader br =
                   new BufferedReader(new FileReader(path))) {
        return br.readLine();
    }
}
```

In java 7 it was achived like this
```
public String readFileInJava7() throws IOException {
    BufferedReader br = new BufferedReader(new FileReader(""));
    try {
        return br.readLine();
    } finally {
        if (br != null) br.close();
    }
}
```
 
 
 ## Method References
 
  You use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression does nothing but call an  existing method. In those cases, it's often clearer to refer to the existing method by name. Method references enable you to   do this; they are compact, easy-to-read lambda expressions for methods that already have a name.
 
 ## Optional
 Tony Hoare introduced Null references in ALGOL W back in 1965 "simply because it was so easy to implement", says Mr. Hoare.    He talks about that decision considering it "my billion-dollar mistake". To deal with it we got Optional class in java 8 which is "A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value". 
 
 ### of
 
 Returns an Optional with the specified present non-null value. If value is null it throws `NullPointerException`
 
 ```
 Optional.of(someObject)
 ```
 
 ### ofNullable
 Returns an Optional describing the specified value, if non-null, otherwise returns an empty Optional.
 ```
 Optional.ofNullable(someObject)
 ```
 
 ### isPresent
 Return true if there is a value present, otherwise false. If value is present then we can use `get()` method in Optional class to access the value contained in the Optional object. So `carOptional.get()` returns Car object.
 
```
Optional<Car> carOptional = Optional.ofNullable(carObject);
if (carOptional.isPresent()) {
    System.out.println(carOptional.get());
}
```
Previously same was achived like this
```
Car car = ..//car object initialisation
if(car != null) {
    System.out.println(car);
}
```
 
 ### orElse
 Return the value if present, otherwise returns other. "other" is basically an alternate value that we can supply in the absence of  original value.
 
 ```
Optional<Car> carOptional = Optional.ofNullable(carObject);
if (carOptional.isPresent()) {
    System.out.println(carOptional.orElse(other));
}
```
 
 ### orElseGet
 Return the value if present, otherwise invoke other and return the result of that invocation. "other" here is an functional interface whose functional method is `get()`.
 
  ```
Optional<Car> carOptional = Optional.ofNullable(carObject);
if (carOptional.isPresent()) {
    System.out.println(carOptional.orElseGet(() -> anotherCarObject));
}
```
 
 ### filter
 If a value is present, and the value matches the given predicate, return an Optional describing the value, otherwise return an empty Optional. Suppose we had to check if a car is automatic then we would do something like this
 ```
 Car car = ..//car object initialisation
 if(car != null && car.isAutomatic()) {
     System.out.println(car);
 }
```
 
 With Optional we can use `filter()` method with required predicate as an argument
 
 ```
Optional<Car> carOptional = Optional.ofNullable(carObject)
.filter(Car::isAutoMatic);

if (carOptional.isPresent()) {
    System.out.println(carOptional.get());
}
```
 
 
 ### map
 If a value is present, apply the provided mapping function to it, and if the result is non-null, return an Optional describing the result. Otherwise return an empty Optional. Throws NullPointerException if the mapping function is null
 
 ### flatMap
 If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional. Throws NullPointerException if the mapping function is null or returns a null result
 
 
