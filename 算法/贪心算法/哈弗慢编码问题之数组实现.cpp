#include<stdio.h>
#include<stdlib.h>

typedef struct{
	char word;
	int weight;
	int left,right,parent;
	int *code;
}HuffNode;

HuffNode *CreateSort(int n);
void HuffmanNode(HuffNode *F,int n);
void CreateHuffmanCode(HuffNode* F, int n);
void PrintHuffmanCode(HuffNode* F,int n);

int main(void)
{
	int n;
	printf("请输入叶子的大小n:");
	scanf("%d",&n);
	fflush(stdin);
	HuffNode *F;
	F = CreateSort(n);
	HuffmanNode(F,n);
	CreateHuffmanCode(F,n);
	PrintHuffmanCode(F,n);
}

//创建并初始化结构体数组 
HuffNode *CreateSort(int n) 
{
	HuffNode *F;
	F = (HuffNode*)malloc((2*n-1)*sizeof(HuffNode));
	char ch;
	int w;
	for(int i = 0 ;i<n; i++)
	{
		printf("请输入一个字母:");
		scanf("%c",&ch);
		printf("请输入一个长度:");
		scanf("%d",&w);
		fflush(stdin);
		F[i].word = ch;
		F[i].weight = w;
		F[i].left = F[i].right = F[i].parent = -1;	
		F[i].code = NULL;
	}
	return F;
}
//创建哈弗慢树 
void HuffmanNode(HuffNode *F,int n)
{
	int loop,k1,k2,i;
	for(loop = 0; loop<n-1; loop++)
	{
		for(k1 = 0; k1<n+loop&&F[k1].parent!=-1; k1++);
		for(k2 = k1+1; k2<n+loop&&F[k2].parent!=-1; k2++);
		for(i = k2; i<n+loop; i++)
		{
			if(F[i].parent == -1)
			{
				if(F[i].weight<F[k1].weight){
					k2 = k1;
					k1 = i;
				}
				else if(F[i].weight<F[k2].weight){
					k2 = i;
				}
			}
		}
		F[i].word = 'x';
		F[i].weight = F[k1].weight+F[k2].weight;
		F[i].left = k1;
		F[i].right = k2;
		F[i].parent = -1;
		F[i].code = NULL;
		F[k1].parent = F[k2].parent = i;
	}
} 
//编码 
void CreateHuffmanCode(HuffNode *F, int n)
{
	int c,pa,i;
	int *p;
	for(i = 0; i<n; i++)
	{
		p = F[i].code = (int*)malloc(n*sizeof(int));
		p[0] = 0;
		c = i;
		while(F[c].parent!=-1){
			pa = F[c].parent;
			if(F[pa].left == c){
				p[++p[0]] = 0;
			}
			else{
				p[++p[0]] = 1;
			}
			c = pa;
		}
		
	}
}
//输出Huffman编码 
void PrintHuffmanCode(HuffNode *F,int n)
{
	int *p;
	int i,j;
	for(i = 0; i<n; i++)
	{
		p = F[i].code;
		for(j = p[0]; j>0; j--)
		{
			printf("%d",p[j]);
		}
		printf("\n");
	}
}




