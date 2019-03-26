package com.dwiariyanto.objectbox.testrx

import com.dwiariyanto.objectbox.BaseActivity
import com.dwiariyanto.objectbox.ObjectBox
import com.dwiariyanto.objectbox.testonetomany.Student
import com.dwiariyanto.objectbox.testonetomany.Student_
import io.objectbox.android.AndroidScheduler
import io.objectbox.kotlin.query
import io.objectbox.reactive.DataSubscriptionList
import io.objectbox.reactive.Scheduler
import io.objectbox.rx.RxQuery
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Dwi Ariyanto on 25/03/19.
 */
class TestRxActivity : BaseActivity() {
    private val query = Student.box.query {
        equal(Student_.name, "Student 1")
    }

    override fun onViewCreated() {
        prepareData()
        addContent()
    }

    private fun addContent() {
        layContent.addView(createViewAddNew())
    }

    override fun onStart() {
        super.onStart()
        init()
    }

    private fun init() {
        simpleObserve()
        generalObserve()
        transformObserve()
        singleObserve()
        onlyChangeObserve()
        realRx()
    }

    override fun onStop() {
        super.onStop()
        subscriptions.cancel()
    }

    private val subscriptions = DataSubscriptionList()
    private fun simpleObserve() {
        query.subscribe(subscriptions)
            .on(AndroidScheduler.mainThread())
            .observer { data ->
                print("Simple Observe")
                print(data)
            }
    }

    private fun generalObserve() {
        val subs = ObjectBox.get().subscribe(Student::class.java)
            .observer { data ->
                print("General Observe")
                print(data)
            }
        subscriptions.add(subs)
    }

    private fun transformObserve() {
        val subs = ObjectBox.get().subscribe()
            .transform { clz ->
                ObjectBox.get().boxFor(clz).count()
            }
            .observer {
                print("Transform Observe")
                print(it)
            }
        subscriptions.add(subs)
    }

    private fun singleObserve() {
        query.subscribe(subscriptions)
            .on(AndroidScheduler.mainThread())
            .single()
            .observer { data ->
                print("Single Observe")
                print(data)
            }
    }

    private fun onlyChangeObserve() {
        query.subscribe(subscriptions)
            .on(AndroidScheduler.mainThread())
            .onlyChanges()
            .observer { data ->
                print("Only Change Observe")
                print(data)
            }
    }

    private val compositeDisposable = CompositeDisposable()
    private fun realRx() {
        val subs = RxQuery.observable(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                print("Real Rx")
                print(it)
            }
        compositeDisposable.add(subs)
    }

    private fun createViewAddNew() =
        createView("Add New") {
            addNew()
        }

    private fun addNew() {
        Student.box.put(Student(name = "New Student"))
    }
}