#include<stdio.h>
#define R 7
#define C 8
void Print();
void Maze(int x,int y);
//创建迷宫 
int M[R+2][C+2]={{1,1,1,1,1,1,1,1,1,1},
				 {1,0,0,1,1,0,0,0,0,1},
				 {1,1,0,0,1,0,1,1,0,1},
				 {1,0,0,1,0,0,1,0,0,1},
				 {1,1,0,0,0,1,0,0,1,1},
				 {1,1,1,0,0,0,0,0,1,1},
				 {1,0,0,1,0,1,0,0,0,1},
				 {1,0,0,0,0,0,1,1,0,1},	
				 {1,1,1,1,1,1,1,1,1,1},	
				};
int Move[4][2] = {{1,0},{0,1},{-1,0},{0,-1}};
int a[R*C][2];
int count  = 0; //记录路线种类 

int main(void)
{
	Maze(1,1);
} 

void Maze(int x,int y)
{
	int a,b;
	M[x][y] = 2;
	if(x==7&&y==8)
	{
		printf("这是第%d种路线:\n",++count);
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
				Maze(a,b);
			}
		}
	}
	M[x][y] = 0;
}

void Print()
{
	for(int i = 0; i<R+2; i++)
	{
		for(int j = 0; j<C+2; j++)
		{
			printf("%2d",M[i][j]);
		}
		printf("\n"); 
	}
	printf("********************************************\n");
}


