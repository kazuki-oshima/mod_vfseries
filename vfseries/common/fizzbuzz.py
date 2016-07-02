# -*- conding: utf-8 -*-
def fizzBuzz():

	for i in range(100):
		

		if i % 15 == 0:
			print "FizzBuzz"
		elif i % 5 == 0:
			print "Fizz"
		elif i %3 == 0:
			print "Buzz"
		else:
			print i

def fizzBuzz2():

	i = 1
	while(i <= 100):

		if i % 15 == 0:
			print "FizzBuzz"

		elif i % 5 == 0:
			print "Fizz"

		elif i % 3 == 0:
			print "Buzz"
		else:
			print i

		i += 1

if __name__ == '__main__':
	fizzBuzz2()