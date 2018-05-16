#include<stdio.h>
int Move[8][2]={2,1,1,2,-1,2,-2,1,-2,-1,-1,-2,1,-2,2,-1};
int cnt = 0;
int H[12][12];
int count  = 0;   //记录解的个数 
void CreateH()
{
	for(int i = 2; i<10; i++)
	{
		for(int j = 2; j<10; j++)
		{
			H[i][j] = 0;
		}
	}
	
	for(int j = 0; j<12; j++)
	{
		H[0][j] = H[1][j] = H[10][j] = H[11][j] = -1; 
	}
	
	for(int i = 0; i<12; i++)
	{
		H[i][0] = H[i][1] = H[i][10] = H[i][11] = -1;
	}
}



void Print()
{
	for(int i = 0; i<12; i++)
	{
		for(int j = 0; j<12; j++)
		{
			printf("%3d",H[i][j]);
		}
		printf("\n");
	}
}

void Horse(int x,int y)
{
	int a,b; 
	for(int i = 0; i<8; i++)
	{
		a = x+Move[i][0];
		b = y+Move[i][1];
		if(H[a][b]==0)
		{
			H[a][b] = ++cnt;
			if(cnt == 64){
				printf("第%d组解\n",++count);
				Print();
				printf("\n");
			}
			else{
				Horse(a,b);
			}
			
			H[a][b] = 0;
			cnt--;
		}
		
	}
}
int main(void)
{
	CreateH();
	for(int i = 2; i<10; i++)
	{
		for(int j= 2; j<10; j++)
		{
			H[i][j] = ++cnt;
			Horse(i,j);
		}
	}
}
