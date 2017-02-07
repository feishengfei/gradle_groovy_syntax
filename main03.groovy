import org.codehaus.groovy.runtime.InvokerHelper

class Main extends Script {                     
	def run() {                                 
		println 'Groovy world!'                 
	}
	static void main(String[] args) {           
		InvokerHelper.runScript(Main, args)     
	}
}
