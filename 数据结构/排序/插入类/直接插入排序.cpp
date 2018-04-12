#include<stdio.h>

#define N 8
void InsertSont(int a[]);

int main(void)
{
	int a[N] = {3,2,5,8,4,7,6,9};
	InsertSont(a);
	return 0;
} 

//直接插入排序法 
void InsertSont(int a[])
{
	int i,j,x;
	
	for(i = 1; i<N; i++)
	{ 
		x = a[i];
		for(j = i-1; j>-1; j--)
		{
			if(a[j] > x)
				a[j+1] = a[j];
			else 
				break;
		}
//		for(j = i-1; j>-1&&a[j]>x; a[j+1] = a[j],j--);
		a[j+1] = x;
	} 
	for(int i = 0; i<N; i++)
		printf("%5d",a[i]);
	
}
