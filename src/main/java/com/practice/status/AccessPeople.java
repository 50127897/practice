package com.practice.status;

/**
 * @author hzq
 * @date 2019/12/13
 */
public enum AccessPeople {

    /**
     * 类型
     */
    Admin(1),
    Teacher(2),
    Student(3);

    private int type;

    AccessPeople(int type){
        this.type = type;
    }

    public int getType(){
        return type;
    }

}
