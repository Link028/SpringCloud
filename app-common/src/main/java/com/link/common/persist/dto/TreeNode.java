package com.link.common.persist.dto;

import java.io.Serializable;
import java.util.List;

/**
 * tree树结构实体类
 */
public class TreeNode implements Serializable
{  
    /** 节点ID */
    private String id;

    /** 节点父ID */
    private String pId;

    /** 节点名称 */
    private String name;

    /** 节点标题 */
    private String title;

    /** 是否勾选 */
    private boolean checked = false;

    /** 是否展开 */
    private boolean open = false;

    /** 是否能勾选 */
    private boolean nocheck = false;

    private List<TreeNode> children = null ;
    
    
    public TreeNode()
	{
    	//children = new ArrayList<TreeNode>() ;
	}
    
    public TreeNode(String id, String name)
	{
		this.id = id;
		this.name = name;
		//children = new ArrayList<TreeNode>() ;
	}

	public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getpId()
    {
        return pId;
    }

    public void setpId(String pId)
    {
        this.pId = pId;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public boolean isChecked()
    {
        return checked;
    }

    public void setChecked(boolean checked)
    {
        this.checked = checked;
    }

    public boolean isOpen()
    {
        return open;
    }

    public void setOpen(boolean open)
    {
        this.open = open;
    }

    public boolean isNocheck()
    {
        return nocheck;
    }

    public void setNocheck(boolean nocheck)
    {
        this.nocheck = nocheck;
    }

	public List<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<TreeNode> children) {
		this.children = children;
	}
    
    private static final long serialVersionUID = 1L;
    
}
