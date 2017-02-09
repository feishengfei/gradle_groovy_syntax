import org.codehaus.groovy.runtime.InvokerHelper
class Main extends Script {
	int power(int n) { 2** n}                   
	def run() {
		println 'power demo'                         
		for (int i = 0; i < 20; i++) {
			println 'power(' + i + ') = ' + power(i)
		}
	}
	static void main(String[] args) {
		InvokerHelper.runScript(Main, args)
	}
}
