package jp.kosuke.kinoshita.roomapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mUserDao: UserDao
    lateinit var mAdapter: ArrayAdapter<String>

    private var mUserList: List<User> = listOf()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayListOf())
        list_view.adapter = mAdapter

        insert_button.setOnClickListener { insertUser() }
        update_button.setOnClickListener { updateUser() }
        delete_button.setOnClickListener { deleteUser() }
    }

    override fun onResume() {
        super.onResume()

        mUserDao = UserDatabase.getInstance(this).userDao()
        getUser()
    }

    private fun getUser() {
        mUserList = mUserDao.getAll()
        val userInfoList = mUserList.map { it.id.toString() + " / " + it.name + " / " + it.age.toString() }
        mAdapter.clear()
        mAdapter.addAll(userInfoList)
    }

    private fun insertUser() {
        val newUser = User(0, "新規さん", 20)
        mUserDao.insert(newUser)
        getUser()
    }

    private fun updateUser() {
        if (mUserList.isEmpty()) return

        val editUser = mUserList.last()
        editUser.name = "更新されました"
        mUserDao.update(editUser)
        getUser()
    }

    private fun deleteUser() {
        if (mUserList.isEmpty()) return

        val deleteUser = mUserList.last()
        mUserDao.delete(deleteUser)
        getUser()
    }
}
