# 2015_Graphics_project
This code is for Lesson 2015_Graphics @Fudan.
The target is to create a cube and show its projection. The progamme should not use any third library but drawpixel function provided by Java.

Coach Han wrote Drawline function and Fillpolygon function.I wrote the cube and rotated it.
# The problem I met
1.At first, the cube was reducing its size while it was rotating. It caused by lossing accuracy.
So I changed the policy of rotating 1 degree every press to recording the angle and then rotate one time.
2.Flickering was solved by buffering before repaint the graph.

# Usage
Press "q" "e" Rotate on z axis;
Press "w" "s" Rotate on x axis;
Press "a" "d" Rotate on y axis;
![] (https://github.com/layumi/2015_Graphics_project/blob/master/show.png)
