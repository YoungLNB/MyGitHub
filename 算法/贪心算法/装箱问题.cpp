#include<stdio.h>
#include<stdlib.h>
#define V 35
#define N 5
//物品信息
typedef struct{
	int gnum;//物品编号 
	int gv;//物品体积 
}ElemG; 

//物品结点 
typedef struct node{
	int gnum;
	struct node *next;
}GoodsLink; 

//箱子结点
typedef struct box{
	int remainder;  //用来存放剩余体积
	GoodsLink *hg;
	struct box *next; 
}BoxLink;
ElemG *CreateG();
ElemG *BuddleSont(ElemG *g);
BoxLink * Enchase(ElemG *g);
void Print(BoxLink *hbox);


int main(void)
{
	ElemG *g;
	BoxLink *hbox;
	g = CreateG();
	g = BuddleSont(g);
	hbox = Enchase(g);
	Print(hbox);	
}
//创建物品信息数组,然后初始化 
ElemG *CreateG()
{
	ElemG *g;
	int vol = 0; 
	g = (ElemG*)malloc(N*sizeof(ElemG));
	for(int i = 0; i<N; i++)
	{
		g[i].gnum = i+1;
		printf("请输入体积:");
		scanf("%d",&vol);
		g[i].gv = vol;
	}
	return g;
}
//将所有的物品按体积降序排列 
ElemG* BuddleSont(ElemG *g)
{
	int i,j;
	ElemG t;
	int tag = 1;  //标志变量 
	for(i = 0; tag&&i<N-1; i++)
	{
		
		tag = 0;
		for(j = N-1; j>i; j--)
		{
			if(g[j-1].gv<g[j].gv)
			{
				t = g[j-1];
				g[j-1] = g[j];
				g[j] = t;
				tag = 1;
			}
		}
	}
	return g;
}

//装箱子 
BoxLink* Enchase(ElemG *g)
{
	BoxLink *p,*hbox = NULL,*tail; 
	GoodsLink *newGoods,*q; 
 
	for(int i = 0; i<N; i++)
	{
		//遍历箱子链 
		for(p = hbox; p&&p->remainder<g[i].gv; p = p->next);
		if(!p){
			//创建箱子 
			p = (BoxLink*)malloc(sizeof(BoxLink));
			//初始化箱子
			p->remainder = V;
			p->next = NULL;
			p->hg = NULL;
			//挂箱子	
			if(!hbox){
				hbox = tail = p;
			} 
			else{
				tail = tail->next = p;
			}
		}
		p->remainder -=g[i].gv;
		//放物品
		newGoods = (GoodsLink*)malloc(sizeof(GoodsLink));
		newGoods->gnum = g[i].gnum;
		newGoods->next = NULL;
		if(!p->hg){  //新箱子 
			p->hg = newGoods;
		}
		else{  //旧箱子    
			for(q=p->hg;q->next;q = q->next);
				q->next = newGoods;
		}
	}
	return hbox;
}

void Print(BoxLink *hbox)
{
	BoxLink *p;
	GoodsLink *q;
	int cut = 0;
	for(p = hbox; p; p = p->next)
	{
		printf("第%d个箱子",++cut);
		for(q = p->hg; q; q = q->next)
			printf("存放编号为%d的物品,",q->gnum);
		printf("\n");
	}
}

