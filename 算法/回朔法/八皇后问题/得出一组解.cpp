#include<stdio.h>
int col[8] = {0};//存放第j列是否有皇后  1代表有,0代表没有 
int left[15] = {0};//存放斜线是否有皇后 左斜线(i+j) 
int right[15] = {0};//存放斜线是否有皇后 右斜线(7+i-j) 
int Q[8];      //用栈存放皇后的位置,下标为行号,存放的值为列号 

void Queen();

int main(void)
{
	Queen();
	for(int i = 0; i<8; i++)
	{
		for(int j =0; j<8; j++)
		{
			if(Q[i] == j){
				printf("%3d",Q[i]);
			}
			else{
				printf("%3c",'x');
			}	
		}
		printf("\n");
	}
		
}

void Queen()
{
	int top,i,j;
	i = j = 0;
	top = -1;
	//栈不空 
	while(top!=7){  
		for(; j<8; j++)
		{
			//能放 
			if(!col[j]&&!left[i+j]&&!right[7+i-j])
			{
				//把j放入Q中
				Q[++top] = j;   
				//改变列  斜线的值 说明列  斜线已经有皇后 
				col[j] = left[i+j] = right[7+i-j] = 1;
				//做下一行    
				i++;
				j = 0;
				//这一行放了皇后之后,要break跳出循环,否则在该行后面还会继续放 
				break;   
			}
		}
		//回朔,i行不能放 
		if(j==8)
		{
			i--;
			//top回到不能放皇后的下一行的下一行 
			j = Q[top--];
			col[j] = left[i+j] = right[7+i-j] = 0;
			j++;
		}
	}
} 


