package com.example.todo

import com.example.todo.data.Include

class IncludeManager {

    companion object {
    private var includeList: ArrayList<Include> = ArrayList()
        fun addInclude(include: Include) {
            //TODO include ekle
            includeList.add(include)
        }

        fun removeInclude(include: Include) {
            //TODO include çıkar
            includeList.remove(include)
        }

        fun replaceInclude() {
            //TODo include yer değiştir
        }

        fun getAll(): ArrayList<Include> {
            return includeList
        }
    }
}