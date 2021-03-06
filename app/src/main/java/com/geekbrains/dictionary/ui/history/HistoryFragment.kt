package com.geekbrains.dictionary.ui.history

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.geekbrains.dictionary.R
import com.geekbrains.dictionary.databinding.FragmentHistoryBinding
import org.koin.android.ext.android.inject
import org.koin.android.scope.AndroidScopeComponent
import org.koin.androidx.scope.fragmentScope
import org.koin.core.scope.Scope

class HistoryFragment : Fragment(), AndroidScopeComponent {

    private lateinit var binding: FragmentHistoryBinding
    private val adapter = HistoryAdapter()

    override val scope: Scope by fragmentScope()
    private val vm: HistoryViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHistoryBinding.bind(view)
        binding.historyRecyclerView.adapter = adapter

        vm.apply {
            getHistory()
            liveData.observe(viewLifecycleOwner) {
                adapter.setData(it)
            }
        }
    }

    companion object {
        fun newInstance(): HistoryFragment {
            return HistoryFragment()
        }
    }
}


