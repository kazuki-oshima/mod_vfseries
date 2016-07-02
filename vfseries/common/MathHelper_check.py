# -*- conding: utf-8 -*-

import math


SIN_TABLE = []

def MathHelper_sin(p1):

	p2 = (p1 * float(10430.378))
	p2 = int(p2) & 65535

	return SIN_TABLE[int(p2)]

def MathHelper_cos(p1):

	p2 = (p1 * float(10430.378) + float(16384.0))
	p2 = int(p2) & 65535

	return SIN_TABLE[int(p2)]

if __name__ == '__main__':
	

	SIN_TABLE = []
	print "TEST"

	i = 0
	for i in range(65536):
		SIN_TABLE.append(float(math.sin(float(i) * math.pi * float(2.0) / float(65536.0) )))
		
	pitch = 20.549992

	cosPitch = -MathHelper_cos((-pitch) * float(0.017453292))
	print cosPitch