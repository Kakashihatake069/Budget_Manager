package com.example.budgettracker

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast

class DatabaseHelperClass(var context: Context) : SQLiteOpenHelper(context, "CategoryTb", null, 1) {
    var addlist = ArrayList<ModelClass>()
    var datastorage = ArrayList<Valuemodel>()

    override fun onCreate(db: SQLiteDatabase?) {
        var sql = "create table CategoryTb(category_id integer primary key autoincrement,name text)"
        db?.execSQL(sql)
        var sqll =
            "create table StorageTb(storage_id integer primary key autoincrement,type interger,amount integer,categoryselect text,adddate text,addtime text,selectmode text,note text)"
        db?.execSQL(sqll)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
    }


    fun insertData(name: String) {

        addlist.clear()

        var db1 = writableDatabase
        var c = ContentValues()

        c.put("name", name)

        db1.insert("CategoryTb", null, c)
    }

    fun displaydata(addlist: ArrayList<ModelClass>): ArrayList<ModelClass> {
        addlist.clear()
        val db1 = readableDatabase
        val sql = "select * from CategoryTb"
        val cursor = db1.rawQuery(sql, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                val name = cursor.getString(1)
                var model = ModelClass(name)
                this.addlist.add(model)
                Log.e("TAG", "display: " + name)
            } while (cursor.moveToNext())

        } else {
            Log.e("TAG", "display: " + "no data found")
        }

        return this.addlist
    }

    fun insertincomeExpencedata(
        type: Int,
        amount: String,
        categoryselect: String,
        adddate: String,
        addtime: String,
        selectmode: String,
        note: String
    ) {
        datastorage.clear()

        var db2 = writableDatabase
        var d = ContentValues()

        d.put("type", type)
        d.put("amount", amount)
        d.put("categoryselect", categoryselect)
        d.put("adddate", adddate)
        d.put("addtime", addtime)
        d.put("selectmode", selectmode)
        d.put("note", note)

        db2.insert("StorageTb", null, d)
    }

    fun displayincomeExpencedata(): ArrayList<Valuemodel> {
        datastorage.clear()
        val db2 = readableDatabase
        val sqll = "select * from StorageTb"
        val cursor = db2.rawQuery(sqll, null)
        if (cursor.count > 0) {
            cursor.moveToFirst()
            do {
                var id = cursor.getInt(0)
                var type = cursor.getInt(1)
                var amount = cursor.getString(2)
                var categoryselect = cursor.getString(3)
                var adddate = cursor.getString(4)
                var addtime = cursor.getString(5)
                var selectmode = cursor.getString(6)
                var note = cursor.getString(7)

                val modeltwo =
                    Valuemodel(id, type, amount, categoryselect, adddate, addtime, selectmode, note)
                datastorage.add(modeltwo)


                Log.e(
                    "TAG",
                    "display: " + type + " " + amount + " " + categoryselect + " " + adddate + " " + addtime + " " + selectmode + " " + note
                )


            } while (cursor.moveToNext())
        } else {
            Log.e("TAG", "working:" + "no data found")
        }
        return this.datastorage
    }


    fun updaterecord(amount: String, note: String, storage_id: Int) {
        val db = writableDatabase
        val sql1 =
            "update StorageTb set amount='$amount',note='$note' where storage_id ='$storage_id'"
        db.execSQL(sql1)
        Toast.makeText(context, "update success", Toast.LENGTH_SHORT).show()
    }

    fun deleteData(id: Int) {
        var db = writableDatabase
        val delete = "delete from StorageTb where  storage_id ='$id'"
        db.execSQL(delete)
    }


}