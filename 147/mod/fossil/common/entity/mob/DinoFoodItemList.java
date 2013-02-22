package mod.fossil.common.entity.mob;

import mod.fossil.common.fossilEnums.EnumDinoFoodItem;

public class DinoFoodItemList {
	
	EnumDinoFoodItem Items[];
	
	public int index;	
	
	public DinoFoodItemList()
	{
		index=0;
		this.Items = new EnumDinoFoodItem[100];
	}
	public void addItem(EnumDinoFoodItem item)
	{
		this.Items[index] = item;
		index++;
	}
	public boolean CheckItemById(int ID)
	{
		for (int i=0;i<index;i++)
		{
			if (Items[i].itemID == ID)
				return true;
		}
		return false;
	}
	public int getItemFood(int ID)
	{
		for (int i=0;i<index;i++)
		{
			if (Items[i].itemID == ID)
				return Items[i].FoodValue;
		}
		return 0;	
	}
	public int getItemHeal(int ID)
	{
		for (int i=0;i<index;i++)
		{
			if (Items[i].itemID == ID)
				return Items[i].HealValue;
		}
		return 0;	
	}
	public boolean IsEmpty()
	{
		return index==0;
	}

}
