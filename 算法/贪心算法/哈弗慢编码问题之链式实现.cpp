#include<stdio.h>
#include<stdlib.h>
#define N 5
typedef struct node{
	char word;
	int weight;
	struct node *left,*right;
}HuffNode;

HuffNode ** CreateHuffForst(); 
HuffNode* CreateHuffNode(HuffNode **F);
void Print(HuffNode* root);

int main(void)
{
	HuffNode **F;
	HuffNode *p;
	F = CreateHuffForst();
	p = CreateHuffNode(F);
	Print(p);
}
//创建并初始化森林 
HuffNode ** CreateHuffForst()
{
	HuffNode **F;
	char ch;
	int w;
	F = (HuffNode**)malloc(N*sizeof(HuffNode*));
	for(int i = 0; i<N ;i++)
	{
		F[i] = (HuffNode*)malloc(sizeof(HuffNode));
		F[i]->left = F[i]->right = NULL;
		printf("请输入一个字母:");
		scanf("%c",&ch);
		F[i]->word = ch;
		printf("请输入一个长度:");
		scanf("%d",&w);
		F[i]->weight = w;
		fflush(stdin);
	} 
	return F;
} 

//创建并实现HuffmanTree 
HuffNode* CreateHuffNode(HuffNode **F)
{
	int loop,i,k1,k2;
	HuffNode *p;
	//n-1次循环
	for(loop = 1; loop<N; loop++)
	{
		//找最小 找次小  k1指最小  k2指次小
		//将k1指向第一个不空的数组元素上
		for(k1 = 0; k1<N&&!F[k1]; k1++);
		//将k2指向第二个不空的数组元素上
		for(k2 = k1+1; k2<N&&!F[k2]; k2++);
		for(i = k2; i<N; i++)
		{
			if(F[i]){
				if(F[i]->weight<F[k1]->weight){
					k2 = k1;
					k1 = i;
				}
				else if(F[i]->weight<F[k2]->weight){
					k2 = i;	
				}
			}
		}
		//生成新的二叉树 
		p = (HuffNode*)malloc(sizeof(HuffNode));
		p->weight = F[k1]->weight+F[k2]->weight;
		p->word = 'x';
		p->left = F[k1];
		p->right = F[k2];
		F[k1] = p;
		F[k2] = NULL; 
	}
	return p; 
}

void Print(HuffNode* root)
{
	if(root){
		printf("%5d",root->weight);
		Print(root->left);
		Print(root->right);
	}
}


