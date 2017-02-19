package bluemonster122.simplethings.tileentity;

import bluemonster122.simplethings.tileentity.things.IHaveBattery;
import bluemonster122.simplethings.tileentity.things.IHaveInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nullable;

public class TileEntityST extends TileEntity
{

    public void dropInventory()
    {
        if (getWorld().isRemote)
        {
            return;
        }
        if (this instanceof IHaveInventory)
        {
            for (int i = 0; i < ((IHaveInventory)this).getInventory().getSlots(); ++i)
            {
                ItemStack itemstack = ((IHaveInventory)this).getInventory().getStackInSlot(i);
                if (itemstack != ItemStack.field_190927_a)
                {
                    InventoryHelper.spawnItemStack(getWorld(), getPos().getX(), getPos().getY(), getPos().getZ(), itemstack);
                }
            }
        }
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound)
    {
        if (this instanceof IHaveBattery)
        {
            compound.setInteger("powerStored", ((IHaveBattery) this).getBattery().getEnergyStored());
        }
        if (this instanceof IHaveInventory)
        {
            compound.setTag("inventory", ((IHaveInventory) this).getInventory().serializeNBT());
        }
        return super.writeToNBT(compound);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound)
    {
        if (this instanceof IHaveBattery)
        {
            ((IHaveBattery) this).getBattery().receiveEnergy(compound.getInteger("powerStored"), false);
        }
        if (this instanceof IHaveInventory)
        {
            ((IHaveInventory) this).getInventory().deserializeNBT(compound.getCompoundTag("inventory"));
        }
        super.readFromNBT(compound);
    }

    @Override
    public boolean hasCapability(Capability<?> capability, @Nullable EnumFacing facing)
    {
        if (capability.equals(CapabilityEnergy.ENERGY))
        {
            return this instanceof IHaveBattery;
        }
        if (capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
        {
            return this instanceof IHaveInventory;
        }
        return super.hasCapability(capability, facing);
    }

    @Nullable
    @Override
    public <T> T getCapability(Capability<T> capability, @Nullable EnumFacing facing)
    {
        if (capability.equals(CapabilityEnergy.ENERGY))
        {
            return this instanceof IHaveBattery ? CapabilityEnergy.ENERGY.cast(((IHaveBattery) this).getBattery()) : null;
        }
        if (capability.equals(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY))
        {
            return this instanceof IHaveBattery ? CapabilityItemHandler.ITEM_HANDLER_CAPABILITY.cast(((IHaveInventory) this).getInventory()) : null;
        }
        return super.getCapability(capability, facing);
    }
}