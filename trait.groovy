/* Trait */

//Public methods
trait Flyable {
	String fly() {"I'm flying!"}
}

//Abstract methods
trait Greetable {
	abstract String name()
	String greeting() { "Hello, ${name()}!" }
}

//Private methods
trait Greeter {
	private String greetingMessage() {
		'Hello from a private method~~~'
	}
	String greet() {
		def m = greetingMessage()
		println m
		m
	}
}

//The meaning of this
//this represents the implementing instance. Think of a trait as a superclass. This means that when you write:

trait Introspector {
	def whoAmI() { this }
}

//Interfaces

interface Named {
	String name()
}

trait Greetable02 implements Named {
	String greeting() {"Hello, ${name()}!" }
}

//Properties
trait Named02 {
	String name
}

//Fields

class Bird implements Flyable {}

class Boy implements Greetable {
	String name() { 'Boy' }
}

class GreetingMachine implements Greeter {}

class Foo implements Introspector{}

class Boy02 implements Greetable02 {
	String name() { 'Boy' }
}

class Person02 implements Named02 {}

def bird = new Bird()
println "bird.fly():" + bird.fly();

def boy = new Boy()
println "boy.greeting():" + boy.greeting()

def gm = new GreetingMachine()
println "gm.greet():" + gm.greet()

try {
	assert gm.greetingMessage()
} catch (MissingMethodException e) {
	println "greetingMessage() is private in trait"
}

/*
Traits only support public and private methods.
Neither protected nor package private scopes are supported. 
*/

def foo = new Foo()
assert foo.whoAmI().is(foo)

def boy02 = new Boy02()
println "boy02.greeting():" + boy02.greeting()
assert boy02.greeting() == "Hello, Boy!"
assert boy02 instanceof Named
assert boy02 instanceof Greetable02
assert !(boy02 instanceof Greetable)

def p02 = new Person02(name:'Bob')
assert p02.name == 'Bob'
assert p02.getName() == 'Bob'
println "p02.name:" + p02.name
println "p02.getName():" + p02.getName()

/* Fields */

//Private fields
trait Counter {
	private int count = 0
	int count() { count += 1;count }
}

class Foo01 implements Counter {}

def foo01 = new Foo01()

println "foo01.count() = " + foo01.count()
println "foo01.count() = " + foo01.count()
println "foo01.count() = " + foo01.count()
println "foo01.count() = " + foo01.count()
println "foo01.count() = " + foo01.count()

//Public fields
trait Named03 {
	public String name
}

class Person03 implements Named03 {}

def p03 = new Person03()
p03.Named03__name = 'Bob'

//Convention : String my_package_Foo__bar

//Composition of behaviors
trait FlyingAbility {
	String fly() { "I'm flying!" }
}

trait SpeakingAbility {
	String speak() { "I'm speaking!" }
}

class Duck implements FlyingAbility, SpeakingAbility {}

def duck = new Duck()

println "duck.fly() = " + duck.fly();
println "duck.speak() = " + duck.speak();

//Overriding default methods
class Duck02 implements FlyingAbility, SpeakingAbility {
	String quack() { "Quack!" }
	String speak() { quack() }
}

def duck02 = new Duck02()
println "duck02.fly() = " + duck02.fly();
println "duck02.quack() = " + duck02.quack();
println "duck02.speak() = " + duck02.speak();

/* Extending traits */

//Simple inheritance
//Traits may extend another trait, in which case you must use the extends keyword:
trait Named04 {
	String name
}

trait Polite extends Named04 {
	String introduce() { "Hello, I am $name" }
}

class Person04 implements Polite {}

def p04 = new Person04(name: 'Alice')

println "p04.introduce() = " + p04.introduce();

//Multiple inheritance
//Alternatively, a trait may extend multiple traits. In that case, all super traits must be declared in the implements clause:
trait WithId {
	Long id
}

trait WithName {
	String name
}

trait Identified implements WithId, WithName {}

/* Duck typing and traits */
//Dynamic code

//Traits can call any dynamic code, like a normal Groovy class. This means that you can, in the body of a method, call methods which are supposed to exist in an implementing class, without having to explicitly declare them in an interface. This means that traits are fully compatible with duck typing:
trait SpeakingDuck {
	String speak() { quack() }
}

class Duck03 implements SpeakingDuck {
	String methodMissing(String name, args) {
		"${name.capitalize()}!!!"
	}
}

def duck03 = new Duck03()
println "duck03.speak() = " + duck03.speak()
/*
	the SpeakingDuck expects the quack method to be defined
	the Duck class does implement the method using methodMissing
	calling the speak method triggers a call to quack which is handled by methodMissing
*/

//Dynamic methods in a trait
/* It is also possible for a trait to implement MOP methods like methodMissing or propertyMissing, in which case implementing classes will inherit the behavior from the trait, like in this example:
*/

trait DynamicObject {
	private Map props = [:]
	def methodMissing(String name, args) {
		name.toUpperCase()
	}

	def propertyMissing(String prop) {
		props[prop]
	}

	void setProperty(String prop, Object value) {
		props[prop] = value
	}
}

class Dynamic implements DynamicObject {
	String existingProperty = 'ok'
	String existingMethod() { 'ok' }
}

def dynamic = new Dynamic()
println "dynamic.existingProperty = " + dynamic.existingProperty
println "dynamic.foo = " + dynamic.foo
dynamic.foo = 'bar'
println "dynamic.foo = " + dynamic.foo
println "dynamic.existingMethod() = " + dynamic.existingMethod()
println "dynamic.someMethod() = " + dynamic.someMethod()

/* Multiple inheritance conflicts */
//Default conflict resolution

//It is possible for a class to implement multiple traits. If some trait defines a method with the same signature as a method in another trait, we have a conflict:

trait A {
	String exec() {
		println 'A'
		'A'
	}
	String methodFromA() {
		println "methodFromA"
		"methodFromA"
	}
}

trait B {
	String exec() { 
		println 'B'
		'B'
	}
	String methodFromB() {
		println "methodFromB"
		"methodFromB"
	}
}

class C implements A, B {}

def c = new C()
println 'c.exec() = ' + c.exec()
/*
In this case, the default behavior is that methods from the last declared trait wins. Here, B is declared after A so the method from B will be picked up:
*/

//User conflict resolution
/*
In case this behavior is not the one you want, you can explicitly choose which method to call using the Trait.super.foo syntax. In the example above, we can force to choose the method from trait A, by writing this:
*/
class D implements A, B {
	String exec() {A.super.exec()}
}

def d = new D()
println 'd.exec() = ' + d.exec()

/* Runtime implementation of traits */
//Implementing a trait at runtime
trait Extra {
	String extra() { "I'm an extra method" }
}

class Something {
	String doSomething() { 'Something' }
}

/*
 It is possible to do it at runtime with the following syntax:
	use of the as keyword to coerce an object to a trait at runtime
	then extra can be called on the object
	and doSomething is still callable
*/

def something = new Something() as Extra
println "something.extra() = " + something.extra()
println "something.doSomething() = " + something.doSomething()

//Implementing multiple traits at once

class E {}
def e = new E()
//e.exec()		//TODO fail
//e.exec()		//TODO fail
/*
	withTrait will wrap c into something which implements A and B
*/
def f = e.withTraits A, B
f.exec()
f.methodFromA()
f.methodFromB()

/*
When coercing an object to multiple traits, the result of the operation is not the same instance. It is guaranteed that the coerced object will implement both the traits and the interfaces that the original object implements, but the result will not be an instance of the original class. 
*/

/* Chaining behavior */
