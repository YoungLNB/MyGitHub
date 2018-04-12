#include<stdio.h>
#include<stdlib.h>
#define N 9
typedef struct node{
	int data;
	struct node *left,*right;
}BTNode;

BTNode* CreateBTree(int a[]);
void HeapSort(BTNode* root);


int main(void)
{
	int a[N] = {3,2,5,8,4,7,6,9,1};
	BTNode *root;
	//创建完全二叉树
	root = CreateBTree(a);
	//堆排序 
	HeapSort(root);	

}
//创建完全二叉树
BTNode* CreateBTree(int a[])
{
	BTNode* root,*pa,*p;
	BTNode** Q;
	int front,rear;
	front = rear = 0;
	Q = (BTNode**)malloc((N+2)*sizeof(BTNode*));
	//创建根节点
	pa = root = (BTNode*)malloc(sizeof(BTNode));
	root->data = a[0];
	root->left = root->right = NULL;
	//创建其它节点
	for(int i = 1; i<N; i++)
	{
		p = (BTNode*)malloc(sizeof(BTNode));
		p->data = a[i];
		p->left = p->right = NULL;
		Q[++rear] = p;
		if(pa->left == NULL)
			pa->left = p;
		else{
			pa->right = p;
			pa = Q[++front];
		}
	}
	free(Q); 
	return root;
}

//堆排序 
void HeapSort(BTNode* root)
{
	//生成队列 
	BTNode** Q;
	Q = (BTNode**)malloc((N+1)*sizeof(BTNode*));
	int front,rear;
	front = rear = 0; 
	BTNode* p,*pmin;
	p = pmin = NULL;
	/*
	  t用于交换双亲结点和子女中最小值。
	  s用于最后一个叶子节点与根节点的交换
	  tag为标志变量，检验是否成堆
	*/ 
	int t,s,tag;
	//将完全二叉树放入队列中 
	Q[++rear] = root;
	while(1){
		p = Q[++front];
		if(p->left == NULL&&p->right == NULL)
		{
			break;
		}
		else {
			if(p->left){
				Q[++rear] = p->left;
			}
			if(p->right){
				Q[++rear] = p->right;
			}
		}
	}
	//查看入队是否正确,双亲结点和所有叶子节点的位置 
	printf("rear:%d  front:%d\n",rear,front);
	//end用来保证可以找到队列 
	int end = rear;
	//调整成堆 
	while(front >1){
		/*一次小根堆形成之后，交换根与最后一个叶子结点的data之后,
		又需要重新调整成堆 */ 
		tag = 1;
		while(tag){
			//没有值交换 说明已经成堆 
			tag = 0;
			for(int k = front-1; k>0; k--)
			{
				//出队元素-双亲结点 
				p = Q[k];
				//找出最小值结点 
				pmin = p;
				if(p->left->data < pmin->data){
					pmin = p->left;
				}
				if(p->right&&p->right->data < pmin->data){
					pmin = p->right;
				}
				//若pmin不是双亲结点,则交换 
				if(p-pmin){
					t = p->data;
					p->data = pmin->data;
					pmin->data = t;
					tag = 1;
				}	
			}
		}
		//交换根节点与最后一个叶子结点 
		s = root->data;
		root->data = Q[rear]->data;
		Q[rear]->data = s;
		rear--;
		//砍掉最后一个叶子
		if(Q[front-1]->right){
			Q[front-1]->right = NULL;
		}	
		else{
			Q[front-1]->left = NULL;
			front--;
		} 
		
	}
	while(end)
		printf("%5d",Q[end--]->data);
}



