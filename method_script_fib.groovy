int fib(int n) {
	    n<2?1:fib(n-1)+fib(n-2)
}
assert fib(10)==89


for (int i = 0; i < 20; i++) {
println 'fib(' + i + ') = ' + fib(i)
}
