package com.golda.fitapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.golda.fitapp.R
import com.golda.fitapp.databinding.MainFragmentBinding
import com.golda.fitapp.ui.dialog.BaseDialogInput
import com.golda.fitapp.ui.init
import com.golda.fitapp.ui.setParams
import com.golda.fitapp.ui.toTimeFormatted
import kotlinx.coroutines.launch


class MainFragment : Fragment(), BaseDialogInput.Callback {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        initView()
        initObservers()
    }

    private fun initObservers() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.listSettlement.collect {

                    binding.cardBreakfast.kcalories.setAnimatedText(it.breakfast)
                    binding.cardLunch.kcalories.setAnimatedText(it.lunch)
                    binding.cardDinner.kcalories.setAnimatedText(it.dinner)

                    binding.titleCaloriesGoalValue.setAnimatedText(it.goal)
                    binding.titleEatingValue.setAnimatedText(it.eating)
                    binding.titleBurnValue.setAnimatedText(it.burn)

                    binding.titleTotalValue.setAnimatedText(it.total)


                    it.breakfastTime?.let {
                        binding.cardBreakfast.time.text = it.toTimeFormatted()
                        binding.cardBreakfast.card.isChecked(it > 0L)
                    }
                    it.lunchTime?.let {
                        binding.cardLunch.time.text = it.toTimeFormatted()
                        binding.cardLunch.card.isChecked(it > 0L)
                    }
                    it.dinnerTime?.let {
                        binding.cardDinner.time.text = it.toTimeFormatted()
                        binding.cardDinner.card.isChecked(it > 0L)
                    }
                    binding.cgv.setParams(it.breakfast, it.lunch, it.dinner)
                }
            }
        }
    }

    private fun initView() {
        binding.cardBreakfast.name.text = getString(R.string.breakfast)
        binding.cardLunch.name.text = getString(R.string.lunch)
        binding.cardDinner.name.text = getString(R.string.dinner)

        binding.cardBreakfast.card.setOnClickListener(clickOwner)
        binding.cardLunch.card.setOnClickListener(clickOwner)
        binding.cardDinner.card.setOnClickListener(clickOwner)
        binding.cgv.init()
    }

    private val clickOwner = View.OnClickListener { view ->
        when (view.id) {
            R.id.card_breakfast -> {
                showDialogInputParams(R.string.breakfast)
            }
            R.id.card_lunch -> {
                showDialogInputParams(R.string.lunch)
            }
            R.id.card_dinner -> {
                showDialogInputParams(R.string.dinner)
            }
        }
    }

    private fun showDialogInputParams(numberParams: Int) {
        val dialog = BaseDialogInput.newInstance(numberParams)
            .setCallback(this)
        dialog.show(childFragmentManager, "")
    }

    override fun applyValue(numberEat: Int?, value: Int) {
        when (numberEat) {
            R.string.breakfast -> viewModel.applyKcalBreakfast(value)
            R.string.lunch -> viewModel.applyKcalLunch(value)
            R.string.dinner -> viewModel.applyKcalDinner(value)
        }
    }

}