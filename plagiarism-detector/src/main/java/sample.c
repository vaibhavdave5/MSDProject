int main()
{
	scanf("%d",&t);
	while(t--)
	{
		int z=0,y=301,w=0,v=0,u=0,p=0,q=0;
		int a[3][3];
		int b[3];
		for(i=0;i<3;i++)
		{
			b[i]=0;
		}
		for(i=0;i<3;i++)
		{
			for(j=0;j<3;j++)
			{
				scanf("%d",&a[i][j]);
				b[i]=b[i]+a[i][j];
			}
		}
		for(i=0;i<3;i++)
		{
			if(b[i]>z)
			{
				z=b[i];
				c=i;
			}
			if(b[i]<y)
			{
				y=b[i];
				d=i;
			}
		}
		for(i=0;i<3;i++)
		{
			if(i!=c && i!=d)
			{
				e=i;
			}
		}
		for(i=0;i<3;i++)
		{
			if(a[d][i]<=a[e][i])
			{
				w=w+1;
			}
			if(a[e][i]>a[d][i])
			{
				v=v+1;
			}
		}
		if(w==3 && v>=1)
		{
			u=u+1;
		}
		for(i=0;i<3;i++)
		{
			if(a[e][i]<=a[c][i])
			{
				p=p+1;
			}
			if(a[c][i]>a[e][i])
			{
				q=q+1;
			}
		}
		if(p==3 && q>=1)
		{
			u=u+1;
		}
		if(u == 2)
		{
			printf("yes\n");
		}
		else
		{
			printf("no\n");
		}
	}
} 