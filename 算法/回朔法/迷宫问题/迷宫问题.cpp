#include<stdio.h>
#define R 7
#define C 8
void Print();
void Maze(int x,int y);
//创建迷宫 
int M[R+2][C+2]={{1,1,1,1,1,1,1,1,1,1},
				 {1,0,0,1,1,1,1,1,1,1},
				 {1,1,0,1,1,1,1,1,1,1},
				 {1,0,0,1,0,0,1,0,1,1},
				 {1,1,0,0,1,1,0,0,0,1},
				 {1,1,1,0,0,0,0,0,1,1},
				 {1,0,0,1,1,1,0,0,0,1},
				 {1,0,0,0,0,0,1,1,0,1},	
				 {1,1,1,1,1,1,1,1,1,1},	
				};
//初始化东西南北四个方向 
int Move[4][2] = {{1,0},{0,1},{-1,0},{0,-1}};


int main(void)
{
	Maze(1,1);
} 
/*
函数作用： 试探一种路线 
无参数
无返回值
*/ 
void Maze(int x,int y)
{
	int a,b;
	if(x==7&&y==8)
	{
		Print();
	}
	//遍历四个方向 
	else{
		for(int i = 0; i<4; i++)
		{
			a = x+Move[i][0];
			b = y+Move[i][1];
			if(!M[a][b])
			{
				M[a][b] = 2;
				Maze(a,b);
			}
		
		}
	}
}
/*
函数作用：输出迷宫图
无参数  
无返回值
1为墙,0为可走的路,2为走过的路 
*/ 
void Print()
{
	for(int i = 0; i<R+2; i++)
	{
		for(int j = 0; j<C+2; j++)
		{
			if(i==1&&j==1)
				printf(" 起"); 
			else if(i==R&&j==C)
				printf(" 终");
			else 
				printf("%3d",M[i][j]);
		}
		printf("\n");
	}
}


