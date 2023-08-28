package com.example.notepadapplication.general

import com.example.notepadapplication.widget.base.BasePresenter
import com.google.firebase.database.*


class GeneralPresenter : BasePresenter<GeneralContract.View>(), GeneralContract.Presenter,
    GeneralContract {
    private var dbRef: DatabaseReference = FirebaseDatabase.getInstance().getReference("User")

    override fun attach(view: GeneralContract.View) {
        super.attach(view)
    }

    override fun getListItem(phone: String) {
        dbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (postSnapshot in snapshot.children) {
                    if (postSnapshot.key == phone){

//                        view?.showUser(it. value .toString())
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}