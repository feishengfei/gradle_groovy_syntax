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
