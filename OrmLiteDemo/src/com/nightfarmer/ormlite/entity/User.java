package com.nightfarmer.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * 数据库对应的pojo类，注意一下三点
 * 1、填写表的名称 @DatabaseTable
 * 2、填写表中持久化项的 @DatabaseField 还可使顺便设置其属性
 * 3、保留一个无参的构造函数
 */
//表名称
@DatabaseTable(tableName = "user")
public class User
{
    // 主键 id 自增长
    @DatabaseField(generatedId = true)
    private int id;
    // 映射
    @DatabaseField(canBeNull = false)
    private String username;
    // 不为空
    @DatabaseField(canBeNull = false)
    private String password;
    
    @DatabaseField(defaultValue = "")
    private String nickname ;
    
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Dept dept;
    
    public User()
    {
        // ORMLite 需要一个无参构造
    }

//--------------------------以下是get、set方法-------------------
    
    
    /**
     * @return the id
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id)
    {
        this.id = id;
    }

    /**
     * @return the username
     */
    public String getUsername()
    {
        return this.username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username)
    {
        this.username = username;
    }

    /**
     * @return the password
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password)
    {
        this.password = password;
    }

  
    public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	@Override
    public String toString()
    {
        String text = "";

        text += "\nid = " + id;
        text += "\nusername = " + username;
        text += "\npassword = " + password;
        return text;
    }

}