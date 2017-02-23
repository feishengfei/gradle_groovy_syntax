/* Class */

class Person {
	String name
	Integer age

	def increaseAge(Integer y) {
		this.age += y
	}
}

def p = new Person()
p.age = 1
println "p.age = " + p.age
println "p.@age = " + p.@age
p.increaseAge(1)
println "p.age = " + p.age

/* Inner Class */

class Outer01 {
	private String privateStr

	def callInnerMethod01() {
		new Inner().methodA()
	}

	def callInnerMethod02() {
		new Inner().methodB()
	}

	class Inner {
		def methodA() {
			println "privateStr = " + "${privateStr}."
		}
		def String methodB() {
			"privateStr = " + "${privateStr}."
		}
	}
}

out01 = new Outer01()
out01.privateStr = "ABC"
out01.callInnerMethod01()
println "out01.callInnerMethod02() :" + out01.callInnerMethod02()

/*	
	In several cases, inner classes are 
	implementation of interfaces whose 
	methods are needed by the outer class. 
	The code below illustrates this with 
	the usage of threads, which are very common. 
*/

class Outer02 {
	private String privateStr = 'class Outer02 : some string'

	def startThread() {
		new Thread(new Inner2()).start()
	}

	class Inner2 implements Runnable {
		void run() {
			println "${privateStr}."
		}
	}
}

def out02 = new Outer02()
out02.startThread()

/* Anonymous inner class */

class Outer03 {
	private String privateStr = 'class Outer03 : some string'

	def startThread() {
		new Thread(new Runnable() {
			void run() {
				println "${privateStr}."
			}
		}).start()
	}
}

def out03 = new Outer03()
out03.startThread()

abstract class Abstract {
	String name
	
	abstract def abstractMethod()

	def concreteMethod() {
		println 'concrete'
	}
}

class Concrete extends Abstract {
	def abstractMethod() {
		println 'abstract'
	}
}

abstractD = new Concrete()
abstractD.concreteMethod()
abstractD.abstractMethod()
