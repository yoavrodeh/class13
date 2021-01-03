# Lambda Expressions
### Functional Programming


---

## Important

@box[rounded](This Lecture **will not** be included in the test)
In fact, you are not allowed to use the techniques here in the test!

@css[fragment](*but do use them everywhere else...*)

---
## Today
1. A warmup question.
1. Lambda expressions.
1. Java standard functional interfaces.
1. Method references.
1. A peek into streams.

---
## Resources
1. Oracle Java Tutorials - [Lambda Expressions](https://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html)
1. Jayant Sahu - [Lamdba Expressions in Java 8](https://www.nagarro.com/en/blog/post/26/lambda-expressions-in-java-8-why-and-how-to-use-them)
1. Dzone - [Java Streams](https://dzone.com/articles/a-guide-to-streams-in-java-8-in-depth-tutorial-wit?utm_source=dzone&utm_medium=article&utm_campaign=java-streams-cluster)
by Eugen Paraschiv.



---
### Warmup Question

In a program we wrote we declared one exception class called `MyException`, but there are several exception conditions we would like to support, and when we catch `MyException` we want to be able to differentiate between them.

@css[fragment](*How would you solve this?*)


---
### First Solution

As we learned, define a subclass of `MyException` for every type of situation.
```java code-noblend
class MyUserException extends MyException {
}
```
```java code-noblend
class MyAdminException extends MyException {
}
```
And when catching we can differentiate using a different catch clause.

@css[fragment](*And what if you don't want to add new classes?*)

---
### Second solution

Let `MyException` hold its own private variables. One natural possibility is to use an `enum`. The best would be to have it defined inside the `MyException` class.

@css[fragment](*Try and write `MyException` this way. Also write code that throws it, and code that catches and can tell what happened.*)

---
@code[java code-noblend](src/MyException.java)
Each `MyException` instance has a field describing its type.



---
@code[java code-noblend](src/MainForMyException.java)
Can you understand why we write `MyException.Type.SERVER`?


---
# Lambda Expressions



---
### Functional Interfaces
Many interfaces have only one method:
`Runnable`, `Comparator`, `EventListener`, etc.
Such an interface is called a *functional interface*.

Because they are so common, Java 8 introduced **Lambda Expressions** which make writing classes implementing functional interfaces very simple.

@css[fragment](*You will see this a lot when you look up JavaFX examples for your project next semester.*) 



--- 
### Story

Let's say we are designing a program for processing a list of people. We will start with things we already know, slowly improving on them.


---
@code[java code-noblend](src/Person.java)
@[6-14](We define an inner `enum`. We also use the java class `LocalDate`.)
@[16-21](Standard Constructor.)
@[23-34](Notice how to use the `LocalDate` class. It has many useful methods.)
@[36-39](This method will be used in the following examples.)
@[41-52](This `public static` list is available for our examples.)


---

We'll start with a couple of methods that print some of the people on the list according to an age criterion.

---
@code[java code-noblend](src/Print1.java)
@[5-10](Prints all people in the list with age above `age`.)
@[12-17](Slightly more general.)


---

Let's write a more general version, where the criterion can be given as a parameter to the method.


---
@code[java code-noblend](src/Print2.java)
@[5-14](Our normal strategy pattern. How would you use it to print only the females of the list?)
@[16-22](By defining a new class implementing `CheckPerson`.)
@[24-29](By using an anonymous class.)
@[31-34](Lambda expressions are even shorter to write.)
@[36-38](When type is clear, it is not needed. Also, when the only statement is a `return`, we can drop it!)


---
### Standard Functional Interfaces
The interface `CheckPerson` contains only one method:

`boolean test(Person p)`
  
That is why a lambda expression can be used to generate an instance for it.

Such an interface is called a **functional interface**, and because they are so common. Java has many standard ones. For example:
```java code-noblend
public interface Predicate<T> {
	boolean test(T t);
}
```



---
@code[java code-noblend](src/Print3.java)
@[7-12](It is used exactly like the previous example.)
@[13-20](`Consumer<T>` has a method that takes a `T` and returns void.)
@[22-28](How does Java know that `p` has a `getName()` method?)



---
@code[java code-noblend](src/RunnableExample.java)
@[1-20](No need to even mention the interface `Runnable`. What are the possible executions of this code?)



---
@code[java code-noblend](src/OneButton.java)
@[25-28](It is simpler and more intuitive to write an event handler with Lambda expressions.)



---
### Standard Functional Interfaces
Searching google for *"Java 8 Functional Interface"* we find [this](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html).

Follow this link, and take a quick look to see what sort of interfaces Java already has for us.

---
### A Deeper Dive
Let's look at the [documentation](https://docs.oracle.com/javase/8/docs/api/java/util/function/Predicate.html#t0) of `Predicate<T>`. 

Surprisingly, it has more than one method. So why is it considered a functional interface?

A good exercise is to write each one of these methods by ourselves. We'll have two versions: one with lambda expressions, and one without. We start with:
```java code-noblend
public interface Predicate1<T> {
	boolean test(T t);
}
```
---
### `isEqual`

The signature is:
```java code-noblend
static <T> Predicate1<T> isEqual(Object targetRef)
```
First notice that the method declares its own generic type, why?
What does the signature tell you?
@ul
+ It is static and so cannot call `test`.
+ Its return type is `Predicate1<T>`, so it actually creates a predicate for us.
+ can you guess what it should do?
@ulend


---
### `isEqual`

Returns a new `Predicate1<T>`, whose method `test(T t)` returns true if `t` is equal to `targetRef` in the sense of `equals`.

@css[fragment](*Let's write the code of this method without lamdba expressions.*)


---
@code[java code-noblend](src/Predicate1.java)
@[5-14](Why is the `null` case separate? How would you do this with lambda expressions?)
@[16-20](Lambda expressions really let us think of functions as objects we can create and pass around.)


---
### `Negate`
```java code-noblend
default Predicate1<T> negate()
```
The documentation is:
*Returns a predicate that represents the logical negation of this predicate*.

It is a default method, and is non-static. So the code appears in the interface, and we do have access to `test`.

@css[fragment](*Let's first write it without lambda expressions.*)


---
A pretty good attempt would be:
```java code-noblend
default Predicate1<T> negate1() {
	return new Predicate1<T>() {
		@Override
		public boolean test(T t) {
			return !test(t);
		}        	
    };
}
```
What is the problem? 


---
@code[java code-noblend](src/Predicate1.java)
@[22-29](This is the way an inner class can access *shadowed* members of its containing class.)
@[31-33](This avoids the previous problem completely.) 



---
### `and`
```java code-noblend
default Predicate1<T> and(Predicate1<? super T> other)
```
The documentation is:
*Returns a composed predicate that represents a short-circuiting logical AND of this predicate and another.*

@css[fragment](What does the `super` mean here?)

@css[fragment](Again, first without lamdba expressions:)

---
@code[java code-noblend](src/Predicate1.java)
@[35-46](And now using lambda expressions...)
@[48-53]


---
### Notes:
1. When a lambda expression uses `this` it actually refers to the containing class. 
1. Making a recursive lambda expression is not directly possible (but there are workarounds).
1. Lambda expressions for us are really just like an anonymous class instance. However, Java actually compiles them into private methods.


---
### Lambda vs. Anonymous

1. Lambda expressions cannot have a state (no field variables).
1. They can only have one method.
1. Different meaning for `this`. 



---
## Method References

We use lambda expressions to create anonymous methods. Sometimes, however, a lambda expression simply calls an existing method.




---
@code[java code-noblend](src/References.java)
@[6-10](The `Consumer<T>` interface has one method: `void accept(T)`.)
@[12-14](Just a static method.)
@[16-22](This is how a reference to a static method looks like.)
@[24-30](You can do the same with a non-static method of a specific object.)
@[32-37](Referencing a non-static method with the class name will take the first parameter and run the method on it.)

---
### Another example

Recall the standard library interface:
```java code-noblend
interface Comparator<T> {
	int compare(T o1, T o2);
}
```
It is a functional interface, and so we can use lambda functions or method references when it is needed.

It is used in one of the sorting methods of the standard library class `Arrays`:
```java code-noblend
static <T> void sort(T[] a, Comparator<? super T> c)
```


---
@code[java code-noblend](src/Point.java)
@[3-13](A simple class.)
@[15-27](`compare2()` should have actually been static. It is here just for demonstration purposes.)
@[29-36](With a static method it is simple.)
@[38-42](This is not so natural in this case, but it works.) 
@[44-47](The first parameter becomes to object on which the method is called.)
@[49-52]



---
## Streams

Streams were introduced in java 8, and they come from the world of Functional programming. 

Don't confuse these with I/O streams in java. They are very different.

A stream is a wrapper around a data source.

---
@code[java code-noblend](src/Streams.java)
@[5-10](We generate a stream from a list, and then use the `forEach` method.)
@[12-19](Many stream operations return a new stream. We have to create a new stream, because a stream can be traversed only once, like an iterator.)
@[21-25](We can just concatenate these operations.)
@[27-32]
@[34-37](There are many stream operations that don't return a stream.)
@[39-42](`reduce` takes an initial value and keeps applying the given method on the accumulated value with the new value from the stream. Here we could have just used `sum`.) 


---
This was just a very quick look into Java streams. To learn more, read [here](https://dzone.com/articles/a-guide-to-streams-in-java-8-in-depth-tutorial-wit?utm_source=dzone&utm_medium=article&utm_campaign=java-streams-cluster) for a good explanation of what can be done with streams.


---
We learned a lot in this course about Java, but not everything...


---?image=resources/Java.jpg
<h2 class="text-white">This is the island of Java</h2>
<p class="text-white fragment">See you there!</h2>
