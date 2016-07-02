# -*- conding: utf-8 -*-

import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation

import time
import math
import random

def sin_plot():
    fig,ax = plt.subplots(1,1)
    x = np.arange(-np.pi,np.pi,0.1)
    y = np.sin(x)



    print np.pi
    print x
    print y

    lines, = ax.plot(x,y)
    # print lines

    count = 1
    while True:
        x += 0.1
        y = np.sin(x)

        print x
        print y
        # lines.set_data(x,y)
        
        if count == 5:
            lines.set_data(x,y)
            # ax.set_xlim((x.min(),x.max()))
            count = 1

        # ax.set_xlim((x.min(),x.max()))

        plt.pause(.01)
        count += 1

def los_test():
    fig,ax = plt.subplots(1,1)
    
    enemy = [random.randint(1,100),random.randint(1,100)]
    player = [random.randint(1,100),random.randint(1,100)]

    # player = [20,5]
    # enemy = [10,20]

    print "player %s"%(player)
    print "enemy %s"%(enemy)

    print "----------------------------"

    lines, = ax.plot(1,1)
    # lines = []

    count = 0
    while True:

        if player[0] > enemy[0]:
            player[0] -= 0.1
            lines.set_data(player[0]*0.01,player[1]*0.01)
            print "x = %d"%(player[0])

        elif player[0] < enemy[0]:
            player[0] += 0.1
            lines.set_data(player[0]*0.01,player[1]*0.01)
            print "x = %d"%(player[0])

        if player[1] > enemy[1]:
            player[1] -= 0.1
            lines.set_data(player[0]*0.01,player[1]*0.01)
            print "y = %d"%(player[1])

        elif player[1] < enemy[1]:
            player[1] += 0.1
            lines.set_data(player[0]*0.01,player[1]*0.01)
            print "y = %d"%(player[1])

        count += 1
        print "[player.pos = %d:%d] [enemy.pos = %d:%d] count = %d"%(player[0],player[1],enemy[0],enemy[1],count)


        if player[0] == enemy[0] and player[1] == enemy[1]:
            break


        plt.pause(.09)
        
        # print enemy
        # print player
        # print "[player.pos = %d:%d] [enemy.pos = %d:%d] count = %d"%(player[0],player[1],enemy[0],enemy[1],count)

    # print count


def plot():

    fig = plt.figure()

    x = [0,0]
    y = [0,0]

    enemy = [random.randint(1,100),random.randint(1,100)]
    player = [random.randint(1,100),random.randint(1,100)]

    ax = fig.add_subplot(1,1,1)
    ax.grid(True,linestyle='-',color='0.75')

    ax.set_xlim([0,200])
    ax.set_ylim([0,200])

    scat = plt.scatter(x,y,c=x)

    scat.set_alpha(0.4)


    anim = animation.FuncAnimation(fig,update_plot,fargs=(fig,scat,enemy,player),frames=100,interval=100)

    plt.show()

def update_plot(i,fig,scat,enemy,player):

    print enemy,player
    # print player

    while True:

        if player[0] > enemy[0]:
            player[0] -= 1
            scat.set_offsets(([player[0],player[1]],[enemy[0],enemy[1]]))
            return scat

        elif player[0] < enemy[0]:
            player[0] += 1
            scat.set_offsets(([player[0],player[1]],[enemy[0],enemy[1]]))
            return scat

        if player[1] > enemy[1]:
            player[1] -= 1
            scat.set_offsets(([player[0],player[1]],[enemy[0],enemy[1]]))
            return scat

        elif player[1] < enemy[1]:
            player[1] += 1
            scat.set_offsets(([player[0],player[1]],[enemy[0],enemy[1]]))
            return scat

        if player[0] == enemy[0] and player[1] == enemy[1]:
            break
  
    print "end count = %d"%(i)
    return scat

if __name__ == '__main__':
    # sin_plot()
    # main()


    # los_test()
    plot()

