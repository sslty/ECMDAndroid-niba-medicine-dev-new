/*
 * Copyright (c) 2015 张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.nibatech.ecmd.common.contact;


import java.util.List;

public class Department implements Comparable<Department> {
    private String department;
    private List<String> subDepartmentList;

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String text) {
        this.department = text;

    }

    public List<String> getSubDepartmentList(){
        return this.subDepartmentList;
    }

    public void setSubDepartment(List<String> subDepartmentList){
        this.subDepartmentList = subDepartmentList;
    }




    @Override
    public int compareTo(Department another) {
        return this.department.compareTo(another.getDepartment());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Department) {
            return this.department.equals(((Department) o).getDepartment());
        } else {
            return super.equals(o);
        }
    }

}
