<img src = https://github.com/balsikandar/keeping-up-with-java/blob/master/assets/keeping-up-with-kotlin.jpeg>

# keeping-up-with-kotlin

[![Open Source Love](https://badges.frapsoft.com/os/v1/open-source.svg?v=102)](https://opensource.org/licenses/Apache-2.0)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/balsikandar/Android-Studio-Plugins/blob/master/LICENSE)

#### Spread Some :heart:

[![GitHub stars](https://img.shields.io/github/stars/balsikandar/java8-android-tutorial.svg?style=social&label=Star)](https://github.com/balsikandar/Android-Studio-Plugins) [![GitHub forks](https://img.shields.io/github/forks/balsikandar/java8-android-tutorial.svg?style=social&label=Fork)](https://github.com/balsikandar/Android-Studio-Plugins/fork) [![GitHub watchers](https://img.shields.io/github/watchers/balsikandar/java8-android-tutorial.svg?style=social&label=Watch)](https://github.com/balsikandar/Android-Studio-Plugins)[![GitHub followers](https://img.shields.io/github/followers/balsikandar.svg?style=social&label=Follow)](https://github.com/balsikandar)
[![Twitter Follow](https://img.shields.io/twitter/follow/balsikandar.svg?style=social)](https://twitter.com/balsikandar)


This is a list of all currently supported java 8 APIs for Android, other Open source libraries and plugin that helps java to be used at best in android platform. This repo will be updated regularly for new entries and everyone can contribute by using [issue tracker](https://github.com/balsikandar/keeping-up-with-kotlin/issues).

Here is an [article](https://medium.com/mindorks/keeping-up-with-kotlin-4c0bf7ffacc6) related to this repo.

## Table of Contents

   * [Lambda Expressions](#lambda-expressions)
   * [Default and static interface methods](#default-and-static-interface-methods)
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
   *  [Lombok](#lombok)
   *  [Android studio plugins](#android-studio-plugins)
   
 ## Lambda Expressions
 The Lambda expression is used to provide the implementation of an interface which has functional interface. A functional interface is an interface with one and only one abstract method.
 
Lambda expressions provides these functionalities.

* Enables to treat functionality as a method argument, or code as data.
* A function that can be created without belonging to any class.
* A lambda expression can be passed around as if it was an object and executed on demand.


Lambda expressions introduce the new arrow operator `->` into Java. It divides the lambda expressions in two parts:

```
n -> n + n
```
The left side of arrow operator specifies the parameters required by the expression, which could also be empty `() -> {}` if no parameters are required. The right side of arrow operator is the actual expression.

Check setOnClickListener statement with lambda expressions

```
   buttonView.setOnClickListener(view -> dismiss());
```
previously same was achieved like this
```
buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
```

 ### Lambda Expressions can be used for lot of things for example
 
 #### For sorting given arrayList of items
 ```
 List<String> mItems = new ArrayList<>(Arrays.asList("bmw", "maruti", "audi", "lamborghini"));
 ```
 We can use Collections API with lambda expression like this
 ```
 Collections.sort(items, (s1, s2) -> s1.compareToIgnoreCase(s2));
 ```
 It can be further transformed using method references(explained below) like this
 ```
 Collections.sort(items, String::compareToIgnoreCase);
 ```
 
 before to do the same we did
 ```
 Collections.sort(items, new Comparator<String>() {
      @Override
      public int compare(String s1, String s2) {
        return s1.compareToIgnoreCase(s2);
      }
    });
 ```
 
 #### For creating Runnables
 
 ```
   new Handler().postDelayed(() -> {
      //statement1
      //statement2
      //...
  }, 1000);
        
```    

Before same was done as follows
```
new Handler().postDelayed(new Runnable() {
    @Override
    public void run() {
      //statement1
      //statement2
      //...
    }
}, 1000);
```
 
 ## Default and static interface methods
Java 8 provides the ability to add non abstract methods using `default` and `static` keyword. 

#### Java default interface method
Deafult interface methods are methods with implementation i.e non-abstract. 

```
public interface Log {
    String logLevel();
    
    default void log(String message) {
        System.out.println(message);
    }
    
    static boolean isLoggingEnabled() {
        return true;
    }
}
```

First thing first default method are not forced to be implemented in every implementations and that's also the reason **why they were introduced**. Previously adding any new method in interface required changes in all it's implementations. A long time headache is resolved with it.
```
public class LogObserver implements Log {

    @Override
    public void logLevel(String logLevel) {
        
    }
    
    //implementing default log method is optional
}
```
And you can access default methods without implementing it.

```
Log logObject = new Log() {
      @Override
      public void logLevel(String logLevel) {

      }
  };

  logObject.log("I am default method");
```        
#### Java static interface method
Java static interface methods can't be overriden. They behave just like class static methods. They can be accessed using dot operator. For ex: `isLoggingEnabled()` is a static method in Log interface and can be accessed as

```
Log.isLoggingEnabled();
```

Java interface static method is visible to interface methods only. So we can access `isLoggingEnabled` from other interface methods. They can't be accessed via object instance.

#### 
 
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
  
  If we have to just call say getCar() method from Vehicle class then with lambda it'll be 
  
  ```
  vehicle.flatMap(vehicleObject -> vehicleObject.getCar())
  ```
  for such cases we can create more compact and clearer lambda expression with Scope Resolution Operator :: as below
  ```
    vehicle.flatMap(Vehicle::getCar)
  ```
 
 ## Optional
 Tony Hoare introduced Null references in ALGOL W back in 1965 "simply because it was so easy to implement", says Mr. Hoare.    He talks about that decision considering it "my billion-dollar mistake". To deal with it we got Optional class in java 8 which is "A container object which may or may not contain a non-null value. If a value is present, isPresent() will return true and get() will return the value". 
 
 For Optional integration use [this](https://github.com/dmstocking/support-optional) library for backward compatibility. You can also refer to the [article](https://medium.com/mindorks/keeping-up-with-kotlin-4c0bf7ffacc6) related to this for alternate options.
 
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
 Returns true if there is a value present, otherwise false. If value is present then we can use `get()` method in Optional class to access the value contained in the Optional object. So `carOptional.get()` returns Car object.
 
```
Optional<Car> carOptional = Optional.ofNullable(carObject);
if (carOptional.isPresent()) {
    System.out.println(carOptional.get());
}
```
Previously same was achived by this
```
Car car = ..//car object initialisation
if(car != null) {
    System.out.println(car);
}
```
 
 ### orElse
 Returns the value if present, otherwise returns other. "other" is basically an alternate value that we can provide in the absence of the original value.
 
 ```
Optional<Car> carOptional = Optional.ofNullable(carObject);
if (carOptional.isPresent()) {
    System.out.println(carOptional.orElse(other));
}
```
 
 ### orElseGet
 Returns the value if present, otherwise invokes other and returns the result of that invocation. "other" here is an functional interface. A functional interface is an interface with one and only one abstract method. So here if carObject is null then `orElseGet()` will return the result from `getNewObject()` call. 
 
  ```
Optional<Car> carOptional = Optional.ofNullable(carObject);
if (carOptional.isPresent()) {
    System.out.println(carOptional.orElseGet(() -> getNewObject()));
}
```
 
 ### filter
 If a value is present, and the value matches the given predicate, return an Optional describing the value, otherwise return an empty Optional. Suppose we had to check if a car is automatic then we would do something like this
 ```
 Car car = ..//car object initialisation
 if(car != null && car.isAutomatic()) {
     System.out.println(car.getName());
 }
```
 
 With Optional we can use `filter()` method with required predicate as an argument
 
 ```
Optional<Car> carOptional = Optional.ofNullable(carObject)
.filter(Car::isAutoMatic);

if (carOptional.isPresent()) {
    System.out.println(carOptional.get().getCarName());
}
```
 
 
 ### map
 If a value is present, applies the provided mapping function to it, and if the result is non-null, returns an Optional describing the result otherwise returns an empty Optional. It throws NullPointerException if the mapping function is null
 
 If you have to access price of a model with heirarchy like this
 ```
 String version = vehicle.getCar().getModel().getPrice();
 ```
 
 you'll have to add nested checks something similar to
 ```
  if (vehicle != null) {
        Car car = vehicle.getCar();
        if (car != null) {
            Model model = car.getModel();
            if (model != null) {
                int price = model.getPrice();
            }

        }
    }
 ```
 Not only this creates reading disability it's also not that safe to manage, with `map` funtion in Optional class now we can remove this nesting of `null` checks 
 ```
 int price = vehicle.flatMap(Vehicle::getCar)
        .flatMap(Car::getModel)
        .map(Model::getPrice)
        .orElse(0);
 ```
 Quite readable and if any of these object is null then `Optional.empty()` will be returned. For ex: if car object is null then `Optional.empty()` will be returned and methods below `flatMap(Car::getModel)` will not be executed.
 
 ### flatMap
 If a value is present, apply the provided Optional-bearing mapping function to it, return that result, otherwise return an empty Optional. Throws NullPointerException if the mapping function is null or returns a null result
 
 `map` and `flatMap` works same with the only difference that `map` returns value by wrapping it with Optional class first 
 so if we would have accessed above price value using `map` function only, we would have got this
 
 ```
 Optional<Optional<Opional<Integer>>> price = ... 
 ```
 So use flatMap to avoid nested wrapping of Optional class.
 
 ## Lombok
 Lombok is a java library that automatically plugs into your editor and build tools, spicing up your java. Never write another getter or equals method again. It uses annotations to generate boilerplate code for you. Check this [link](https://projectlombok.org/setup/android) for lombok setup instruction
 
 #### @Data 
 How to use Lombok to remove getter/setters, toString, equals all the other mess of Pojo for given class.
 
 ```
 public class Student {
    int id;
    String name, branch;

    public Student(int id, String name, String branch) {
        this.id = id;
        this.name = name;
        this.branch = branch;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    //override equals, hashCode,toString
}
 ```
 
Above Student class can be transformed using Lombok @Data Annotation concisely

```
import lombok.Data;
@Data
public class Student {
    private int id;
    private String name, branch;
}
```

## [Android studio plugins](https://github.com/balsikandar/Android-Studio-Plugins)
This is a repo that maintains list of plugins that helps every android developer to be productive. It has plugins to help with debugging, UI design, Code generation and for adding support for newer platfroms like flutter and kotlin directly to their Android studio.

### Contributing to this Repo
If you feel something is missing. you can use [issue tracker](https://github.com/balsikandar/keeping-up-with-kotlin/issues) to include it. You can also create a pull request to add missing pieces.

### Check out another awesome library to capture your crashes instantly and locally.
[CrashReporter](https://github.com/MindorksOpenSource/CrashReporter) on your duty.


### Find this project useful ? :heart:
* Support it by clicking the :star: button on the upper right of this page. :v:

### License

   ```
   Copyright (C) 2016 Bal Sikandar
   Copyright (C) 2011 Android Open Source Project

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
