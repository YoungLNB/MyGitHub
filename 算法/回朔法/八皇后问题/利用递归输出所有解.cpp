#include<stdio.h>
int col[8] = {0};
int left[15] = {0};
int right[15] = {0};
int Q[8];
int count = 0;

void Queen(int i);
void Print(int Q[]);

int main()
{
	int i = 0; 
	Queen(i);
}

void Queen(int i)
{
	for(int j = 0; j<8; j++)
	{
		if(!col[j]&&!left[i+j]&&!right[7+i-j])
		{
			Q[i] = j;
			col[j] = left[i+j] = right[7+i-j] = 1;
			if(i<7){
				Queen(i+1); //放完之后放第i+1行 
			}
			else{
				Print(Q);
			}
			col[j] = left[i+j] = right[7+i-j] = 0;
		}
	}
}

void Print(int Q[])
{
	printf("--------------------\n");
	printf("输出第%d组解\n",++count);
	for(int i = 0; i<8; i++)
	{
		for(int j = 0; j<8; j++)
		{
			if(Q[i] == j)
				printf("%2d",Q[i]);
			else
				printf("%2c",'x');
		}
		printf("\n");
	}
}
