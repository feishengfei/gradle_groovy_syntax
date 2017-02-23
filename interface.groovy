interface Greeter {
	void greet(String name)

	//Method 'greet01' is protected but should be public in interface 'Greeter'.
	//protected void greet01(String name)
}

class SystemGreeter implements Greeter{
	void greet(String name) {
		println "Hello $name"
	}
}

interface ExtendedGreeter extends Greeter {
	void sayBye(String name)
}

class DefaultGreeter {
	void greet(String name) {
		println "Hello $name"
	}
}

def greeter = new SystemGreeter()
assert greeter instanceof Greeter
assert greeter instanceof SystemGreeter

greeter.greet("Frank")

greeter = new DefaultGreeter()
assert !(greeter instanceof Greeter)
coerced = greeter as Greeter
assert coerced instanceof Greeter
coerced.greet("Alex")

