# -*- conding: utf-8 -*-

import math

if __name__ == '__main__':
	
	v0 = 0
	g = 9.80665
	dosuu = 1

	for v0 in range(100):
		for dosuu in range(90+1):

			rad = dosuu * math.pi / 180
			R = (math.pow(v0,2) * 2 * math.sin(rad))/g
			
			# print "-dosuu %d------- v0 %d------"%(dosuu,v0)
			# print "R = %f m"%(R)

	
	# dosuu = 2
	g = 9.80665
	for dosuu in range(180):

		rad = dosuu * math.pi / 180
		v = math.sqrt((11911000 * g)/2 * math.sin(rad))
		print "dosuu %d --v %d"%(dosuu,v)
		# print dosuu

		# v = float(v)
		# print "%f "%(v2)


