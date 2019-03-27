package one.example.com.mysample;

import android.os.Bundle
import android.support.v4.app.FragmentActivity
import android.widget.Button
import android.widget.LinearLayout
import one.example.com.mysample.main.db.AppDatabase
import one.example.com.mysample.main.db.entity.SubjectsEntity
import one.example.com.mysample.utile.Logs

class DbTestActivity : FragmentActivity() {
    val insertBtnId = 100
    val queryBtnId = 101
    val deleteBtnId = 102
    val updateBtnId = 103
    var db: AppDatabase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var rlLLParents = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT)
        setContentView(getView(), rlLLParents)
        db = AppDatabase.getInstance()
    }

    fun getView(): LinearLayout {
        var llParents = LinearLayout(this.applicationContext)
        llParents.orientation = LinearLayout.VERTICAL

        var btn = Button(this.applicationContext)
        btn.id = insertBtnId
        var lpBtnInsert = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)

        var btn1 = Button(this.applicationContext)
        btn1.id = queryBtnId
        var lpBtnquery = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lpBtnquery.topMargin = 20

        var btn2 = Button(this.applicationContext)
        btn2.id = deleteBtnId
        var lpBtndelect = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lpBtndelect.topMargin = 20

        var btn3 = Button(this.applicationContext)
        btn3.id = updateBtnId
        var lpBtnupdate = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        lpBtnupdate.topMargin = 20

        btn.setText("插入数据")
        btn1.setText("查询数据")
        btn2.setText("删除数据")
        btn3.setText("修改数据")

        llParents.addView(btn, lpBtnInsert)
        llParents.addView(btn1, lpBtnquery)
        llParents.addView(btn2, lpBtndelect)
        llParents.addView(btn3, lpBtnupdate)
        setOnclickListener(btn, btn1, btn2, btn3)
        return llParents
    }

    fun setOnclickListener(vararg btns: Button) {//可变参数
        for (btn in btns) {
            btn.setOnClickListener { view ->
                click(view.getId())
            }
        }
    }

    fun click(id: Int) {
        when (id) {
            insertBtnId -> {
                Logs.eprintln("click  insertBtnId")
                var subjectEntity = SubjectsEntity()
                subjectEntity.id = "one"
                subjectEntity.title = "title1"
                subjectEntity.collect_count = 1000
                subjectEntity.original_title = "original_title1"
                subjectEntity.subtype = "subtype1"
                subjectEntity.year = "year1"
                subjectEntity.alt = "alt1"


                var subjectEntity2 = SubjectsEntity()
                subjectEntity2.id = "two"
                subjectEntity2.title = "title1"
                subjectEntity2.collect_count = 1000
                subjectEntity2.original_title = "original_title1"
                subjectEntity2.subtype = "subtype1"
                subjectEntity2.year = "year1"
                subjectEntity2.alt = "alt1"

                db?.subjectsDao()!!.insertSubjects(subjectEntity, subjectEntity2)
            }
            queryBtnId -> {
                Logs.eprintln("click  queryBtnId")
//                var subjectEnt = db?.subjectsDao()!!.queryList(10, 0)
//                Logs.eprintln(" subjectEntityList  lenth id="+subjectEnt.size)
//
//                for (se in subjectEnt) {
//                    Logs.eprintln(" se id="+se.id)
//                }
            }
            deleteBtnId -> {
                Logs.eprintln("click  deleteBtnId")
            }
            updateBtnId -> {
                Logs.eprintln("click  updateBtnId")
            }
            else -> {
                Logs.eprintln("click  的其他选择")
            }
        }
    }
}
