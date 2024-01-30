package com.delivero.customer.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.delivero.customer.adapters.RequestsAdapter
import com.delivero.customer.databinding.RecyclerLayoutBinding
import com.delivero.customer.helpers.ItemDecoration
import com.delivero.customer.helpers.hide
import com.delivero.customer.helpers.show
import com.delivero.customer.helpers.toast
import com.delivero.customer.interfaces.AdapterListener
import com.delivero.customer.interfaces.Collections
import com.delivero.customer.models.Request
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RequestsFragment:Fragment(),LifecycleOwner{
private lateinit var binding:RecyclerLayoutBinding
private lateinit var requestsList:ArrayList<Request>
private lateinit var adapter: RequestsAdapter
private var lastDocument:DocumentSnapshot?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lastDocument=null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
      binding= RecyclerLayoutBinding.inflate(inflater, container, false)
        binding.recycler.layoutManager= LinearLayoutManager(requireContext())
        binding.recycler.setHasFixedSize(true)
        binding.recycler.addItemDecoration(ItemDecoration())
        requestsList= arrayListOf()
        adapter= RequestsAdapter(requireContext(),requestsList,object :AdapterListener{
            override fun onRequestDetails(request: Request) {

            }
        })
        binding.recycler.adapter=adapter
        binding.recycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {

                    if (lastDocument != null) {

                        getRequests()

                    }

                }
            }
        })
        getRequests()
        setUpMenu()

        return binding.root
    }

    private fun setUpMenu() {
        (requireActivity() as MenuHost).addMenuProvider(object :MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {

            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                if (menuItem.itemId== android.R.id.home){
                    requireActivity().onBackPressedDispatcher.onBackPressed()
                }
                return true
            }
        },viewLifecycleOwner,Lifecycle.State.RESUMED)
    }

    private fun getRequests(){
        var query= Firebase.firestore.collection(Collections.REQUESTS)
            .orderBy("createdAt", Query.Direction.DESCENDING)
            .whereEqualTo("customerId",Firebase.auth.currentUser!!.uid)

        lastDocument?.let {
            query=query.startAfter(it)
        }

        query.limit(20L)
            .addSnapshotListener{
                    shopsSnapshot,exception->
                if (exception!=null){
                    binding.progress.hide()
                    requireActivity().toast("Unable to get shipment at this time")
                    return@addSnapshotListener
                }
                if (shopsSnapshot!!.isEmpty){
                    if (requestsList.isEmpty()){
                        binding.noData.show()
                    }else{
                        binding.noData.hide()
                    }
                    binding.progress.hide()
                    return@addSnapshotListener
                }
                lastDocument = if ((shopsSnapshot.size() * 1L) == 20L) {

                    shopsSnapshot.documents[shopsSnapshot.size() - 1]

                } else {

                    null

                }

                binding.progress.hide()
                for (change in shopsSnapshot.documentChanges){
                    val job=change.document.toObject(Request::class.java)
                    when(change.type){
                        DocumentChange.Type.ADDED, DocumentChange.Type.MODIFIED->{
                            requestsList.removeAll {
                                it.requestId==job.requestId
                            }
                            requestsList.add(job)
                            requestsList.sortByDescending {
                                it.createdAt
                            }
                            adapter.notifyDataSetChanged()

                        }
                        DocumentChange.Type.REMOVED ->{
                            requestsList.removeAll {
                                it.requestId==job.requestId
                            }
                            adapter.notifyDataSetChanged()
                        }

                    }
                }
                if (requestsList.isEmpty()){
                    binding.noData.show()
                }else{
                    binding.noData.hide()
                }
            }

    }
}