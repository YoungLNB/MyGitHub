#include<stdio.h>
#define N 8

int main(void)
{
	int a[N] = {3,2,5,8,4,7,6,9};
	void BuddleSont(int a[]);
	BuddleSont( a );
} 

void BuddleSont(int a[])
{
	int i,j,tag = 1,t;
	for(i = 0; tag&&i<N-1; i++)
	{
		tag = 0;
		for(j = N-1; j>i; j--)
		{
			if(a[j]<a[j-1])
			{
				t = a[j];
				a[j] = a[j-1];
				a[j-1] = t;
				tag = 1;
			}
		}
	}
	for(i = 0; i<N; i++)
		printf("%3d",a[i]); 
}
