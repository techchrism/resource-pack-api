package me.techchrism.resourcepackapi.pack.model;

import com.google.gson.annotations.SerializedName;

public class ModelDisplays
{
    @SerializedName("thirdperson_righthand")
    private ModelDisplay thirdpersonRighthand;
    @SerializedName("thirdperson_lefthand")
    private ModelDisplay thirdpersonLefthand;
    @SerializedName("firstperson_righthand")
    private ModelDisplay firstpersonRighthand;
    @SerializedName("firstperson_lefthand")
    private ModelDisplay firstpersonLefthand;
    private ModelDisplay gui;
    private ModelDisplay head;
    private ModelDisplay ground;
    private ModelDisplay fixed;
    
    public ModelDisplay getThirdpersonRighthand()
    {
        return thirdpersonRighthand;
    }
    
    public void setThirdpersonRighthand(ModelDisplay thirdpersonRighthand)
    {
        this.thirdpersonRighthand = thirdpersonRighthand;
    }
    
    public ModelDisplay getThirdpersonLefthand()
    {
        return thirdpersonLefthand;
    }
    
    public void setThirdpersonLefthand(ModelDisplay thirdpersonLefthand)
    {
        this.thirdpersonLefthand = thirdpersonLefthand;
    }
    
    public ModelDisplay getFirstpersonRighthand()
    {
        return firstpersonRighthand;
    }
    
    public void setFirstpersonRighthand(ModelDisplay firstpersonRighthand)
    {
        this.firstpersonRighthand = firstpersonRighthand;
    }
    
    public ModelDisplay getFirstpersonLefthand()
    {
        return firstpersonLefthand;
    }
    
    public void setFirstpersonLefthand(ModelDisplay firstpersonLefthand)
    {
        this.firstpersonLefthand = firstpersonLefthand;
    }
    
    public ModelDisplay getGui()
    {
        return gui;
    }
    
    public void setGui(ModelDisplay gui)
    {
        this.gui = gui;
    }
    
    public ModelDisplay getHead()
    {
        return head;
    }
    
    public void setHead(ModelDisplay head)
    {
        this.head = head;
    }
    
    public ModelDisplay getGround()
    {
        return ground;
    }
    
    public void setGround(ModelDisplay ground)
    {
        this.ground = ground;
    }
    
    public ModelDisplay getFixed()
    {
        return fixed;
    }
    
    public void setFixed(ModelDisplay fixed)
    {
        this.fixed = fixed;
    }
}
