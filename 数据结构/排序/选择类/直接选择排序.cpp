#include<stdio.h>

#define N 8
void SelectedSont(int a[]);

int main(void)
{
	int a[N] = {3,2,5,8,4,7,6,9};
	SelectedSont(a);
}

void SelectedSont(int a[])
{
	int t,kmin;  //t用来交换 
	
	for(int i = 0; i<N-1; i++) 
	{
		kmin = i;   
		
		//遍历数组  寻找数组中最小值的下标 
		for(int j = i+1; j<N; j++)
		{
			if(a[j]<a[kmin])
				kmin = j;
			
		}
		//判断最小值是不是第i个  如果是什么也不做  如果不是  就交换 
		if(i - kmin)
			{
				t = a[i];
				a[i] = a[kmin];
				a[kmin] = t;
			}
	}
	
	for(int i = 0; i<N; i++)
		printf("%5d",a[i]);
}
