package org.songdan.drools.research.model;

/**
 * @author: Songdan
 * @create: 2019-11-06 18:02
 **/
public class SpCheckContext {

    private boolean specialDiscount;

    private boolean discountExpired;

    private boolean openServiceDiscountCls;

    private boolean discountChange;

    public boolean isSpecialDiscount() {
        return specialDiscount;
    }

    public void setSpecialDiscount(boolean specialDiscount) {
        this.specialDiscount = specialDiscount;
    }

    public boolean isDiscountExpired() {
        return discountExpired;
    }

    public void setDiscountExpired(boolean discountExpired) {
        this.discountExpired = discountExpired;
    }

    public boolean isOpenServiceDiscountCls() {
        return openServiceDiscountCls;
    }

    public void setOpenServiceDiscountCls(boolean openServiceDiscountCls) {
        this.openServiceDiscountCls = openServiceDiscountCls;
    }

    public boolean isDiscountChange() {
        return discountChange;
    }

    public void setDiscountChange(boolean discountChange) {
        this.discountChange = discountChange;
    }

    @Override
    public String toString() {
        return "SpCheckContext{" +
                "specialDiscount=" + specialDiscount +
                ", discountExpired=" + discountExpired +
                ", openServiceDiscountCls=" + openServiceDiscountCls +
                ", discountChange=" + discountChange +
                '}';
    }
}
