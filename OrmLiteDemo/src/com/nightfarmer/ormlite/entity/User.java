package com.nightfarmer.ormlite.entity;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * ���ݿ��Ӧ��pojo�࣬ע��һ������
 * 1����д������� @DatabaseTable
 * 2����д���г־û���� @DatabaseField ����ʹ˳������������
 * 3������һ���޲εĹ��캯��
 */
//������
@DatabaseTable(tableName = "user")
public class User
{
    // ���� id ������
    @DatabaseField(generatedId = true)
    private int id;
    // ӳ��
    @DatabaseField(canBeNull = false)
    private String username;
    // ��Ϊ��
    @DatabaseField(canBeNull = false)
    private String password;
    
    @DatabaseField(defaultValue = "")
    private String nickname ;
    
    @DatabaseField(foreign=true, foreignAutoRefresh=true)
    private Dept dept;
    
    public User()
    {
        // ORMLite ��Ҫһ���޲ι���
    }

//--------------------------������get��set����-------------------
    
    
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